package com.troila.tjsmesp.spider.crawler.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.nodes.Element;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.constant.FromSiteEnum;
import com.troila.tjsmesp.spider.constant.PolicyLevelEnum;
import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.constant.UrlRegexConst;
import com.troila.tjsmesp.spider.model.primary.PolicySpider;
import com.troila.tjsmesp.spider.service.ProcessorService;
import com.troila.tjsmesp.spider.util.MD5Util;
import com.troila.tjsmesp.spider.util.ReduceHtml2Text;
import com.troila.tjsmesp.spider.util.StringUtils;
import com.troila.tjsmesp.spider.util.TimeUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectors;

/**
 * 爬取政策一点通最新政策的部分（经分析对比，最新政策与政策包目录内容大致相同，最新政策从2015年到现在的政策，政策包目录收录的更多些，最早到1988年）
 * http://zcydt.fzgg.tj.gov.cn/gllm/zxzc/
 * @author xuanguojing
 *
 */
@Component("policyNewestPageProcessor")
public class PolicyNewestPageProcessor implements PageProcessor {
	private static final Logger logger = LoggerFactory.getLogger(PolicyNewestPageProcessor.class);
	
    /**
     * 最新政策详情页的正则表达式
     */
//    private static final String ARTICLE_URL = "http://zcydt\\.fzgg\\.tj\\.gov\\.cn/zcb/(\\w+)/(\\w+)/(\\d+)/(\\w+)\\.shtml";
    private static final String ARTICLE_URL_REX = "http://zcydt\\.fzgg\\.tj\\.gov\\.cn/zcb/(\\w+)/.*";
    /**
     * 最新政策文章列表页的正则表达式
     */
    private static final String LIST_URL_REX = "http://zcydt\\.fzgg\\.tj\\.gov\\.cn/gllm/zxzc/index(_\\d+)*\\.shtml"; 
   
    @Autowired
    private ProcessorService processorService;
    
	@Override
	public void process(Page page) {
		//如果当前是文章列表页，获取下一页的链接
		if(page.getUrl().regex(LIST_URL_REX).match()) {
			List<String> list =  page.getHtml().xpath("//div[@class='list_jd_content']").links().all();
			//获取到过去已经成功爬取的链接记录
			List<String> pastCrawledUrls = processorService.getCrawledUrls(SpiderModuleEnum.POLICY_NEWEST);			
			//将所有的最新政策文章详情页加入到后续的url地址，有待继续爬取
			List<String> articleList = list.stream().filter(p->p.matches(ARTICLE_URL_REX)).collect(Collectors.toList());
			if(pastCrawledUrls != null  && pastCrawledUrls.size()>0) {
				articleList = articleList.stream().filter(p->!pastCrawledUrls.contains(p)).collect(Collectors.toList());
			}
			page.addTargetRequests(articleList);
			//将其他的最新政策列表页加入到后续的url地址，有待继续爬取
			List<String> urlList = list.stream().filter(p->p.matches(LIST_URL_REX)).collect(Collectors.toList());
			page.addTargetRequests(urlList);
		}else {
			//具体页面内容获取，具体字段拆分待完成
			PolicySpider spider = new PolicySpider();
			
//			List<Element> tdList = Selectors.xpath("//div[@class='JD_key02']/table/tbody/tr/td").selectElements(page.getHtml().toString());;
////					Selectors.xpath("//div[@class='JD_key02']/table/tbody/tr[1]/td[2]/text()").select(page.getHtml().toString());
//			System.out.println(tdList);
			
			String selectedDiv = page.getHtml().xpath("//div[@class='JD_key02']").toString();
			if(selectedDiv == null) {
				//skip this page,没有该项内容，说明本页下载可能出错了
	            page.setSkip(true);
	            logger.info("文章链接页{}，未找到指定的页面内容，跳过该页面",page.getUrl());
			}else {
				//获取主题分类
				String category = Selectors.xpath("//table/tbody/tr[1]/td[4]/tidyText()").select(selectedDiv);
				spider.setCategory(category);
				//拆分发文字号
				String publishNo = Selectors.xpath("//table/tbody/tr[2]/td[2]/text()").select(selectedDiv);
				spider.setPublishNo(publishNo);
				//获取成文日期
				String publishDateStr = Selectors.xpath("//table/tbody/tr[2]/td[4]/text()").select(selectedDiv);
				spider.setPublishDate(TimeUtils.getShortFormatDate(publishDateStr));
				
				//拆分标题行
				String title = Selectors.xpath("//table/tbody/tr[3]/td[2]/tidyText()").select(selectedDiv);
				spider.setTitle(title);
				
				//拆分报送单位
				String publishUnit = Selectors.xpath("//table/tbody/tr[1]/td[2]/text()").select(selectedDiv);
				//如果该项未填任何内容，则为国家政策,需要仔细拆分，获取发文部分信息
				String dealPublishUnit = dealPublishUnit(page,publishUnit);
				if(dealPublishUnit == null || dealPublishUnit.contains("国家政策")) {
					dealPublishUnit = dealPublishUnitLike(title, publishNo);					
				}
				spider.setPublishUnit(dealPublishUnit == null ? "国家政策": dealPublishUnit);
				if(publishUnit.equals("")) {
					spider.setPolicyLevel(0);
				}else {
					spider.setPolicyLevel(PolicyLevelEnum.getLevelByMultiNames(dealPublishUnit));					
				}
				
				//拆分政策内容
//			spider.setContent(page.getHtml().xpath("//div[@class='JD_article']/tidyText()").toString());
				//有关原文是否有对应的政策解读，需要再仔细斟酌解析一下
				String content = Selectors.xpath("//div[@class='JD_article']").select(page.getHtml().toString());
				String removeScriptTagContent = ReduceHtml2Text.removeScriptTag(content);	
				String jieduStr = Selectors.xpath("//div[@class='JD_article_jiedu']").select(page.getHtml().toString());
				if(jieduStr != null && !jieduStr.equals("")) {
					//将获取的content中的有关解读的div隐藏
					String jieduDiv = "<div class=\"JD_article_jiedu\">";
					String jieduDivReplace = "<div class=\"JD_article_jiedu\" style=\"display:none\">";
					removeScriptTagContent = removeScriptTagContent.replaceAll(jieduDiv, jieduDivReplace);
				}
								
				spider.setStripContent(page.getHtml().xpath("//div[@class='JD_article']/tidyText()").toString());
				spider.setPublishUrl(page.getUrl().toString());
				spider.setFromLink(FromSiteEnum.TIANJINZHEGNCEYIDIANTONG.getName());
				spider.setFromSite(FromSiteEnum.TIANJINZHEGNCEYIDIANTONG.getLink());
				spider.setForwardTime(new Date());	
				spider.setId(MD5Util.getMD5(page.getUrl().toString()));   //根据特定的内容生成MD5，作为该条记录的id,对于每篇文章，下载链接是唯一的
				spider.setSpiderModule(SpiderModuleEnum.POLICY_NEWEST.getIndex());  
				spider.setParentId("-1");
							
				//查看是否有对应的政策解读			
				List<String> list = page.getHtml().xpath("//div[@class='JD_article_jiedu']/div[@class='JD_article_jiedu_newslist']").links().all();
				if(list != null && list.size()>0) {
					spider.setArticleReading(list.toString().substring(1,list.toString().length()-1));
					//将政策解读也替换成完整路径地址，以便可以查看
					removeScriptTagContent = processorService.replaceArticelReadingUrlForContent(removeScriptTagContent, list);				
				}
				//查看是否有附件
//				String str1 =  Selectors.xpath("//div[text()='附件下载:']//following::*[1]").select(content);
//				JXDocument jxDocument = JXDocument.create(page.getHtml().toString());
//				JXNode jxNode= jxDocument.selNOne("//p[contains(text(),'附件下载')]//following-sibling::a[1]//@href");
				List<String> attachmentList =  page.getHtml().xpath("//div[@class='JD_article']").links().all();
				
				List<String> attachmentListFilter = attachmentList.stream().filter(p->p.matches(UrlRegexConst.ATTACHMENT_URL_REX)).collect(Collectors.toList());
				if(attachmentListFilter !=null && attachmentListFilter.size()>0) {	
					spider.setAttachment(attachmentListFilter.toString().substring(1,attachmentListFilter.toString().length()-1));
					removeScriptTagContent = processorService.replaceAttachmentsUrlForContent(removeScriptTagContent, attachmentListFilter);
				}
				
				//将一些图片链接也需要矫正(此处的list得到的是img标签)
				List<Element> imageUrlList = Selectors.xpath("//img/@src").selectElements(content);
				if(imageUrlList != null && imageUrlList.size() > 0) {
					List<String> srcList = imageUrlList.stream()
							.map(e->{return Selectors.xpath("img/@src").select(e);})
							.collect(Collectors.toList());
					removeScriptTagContent = processorService.replaceImageUrlForContent(removeScriptTagContent, srcList, page.getUrl().toString());    					
				}
				
				spider.setContent(removeScriptTagContent);
				page.putField("policy", spider);				
			}					
		}
	}

	@Override
	public Site getSite() {
		// 失败重试3次
		return Site.me().setRetryTimes(3).setSleepTime(1000).setDomain("http://zcydt.fzgg.tj.gov.cn");
	}

	/**
	 * 处理发文部门字段
	 * @param page
	 * @param tempPublishUnitStr
	 * @return
	 */
	public String dealPublishUnit(Page page,String publishUnitTempStr) {		
		//如果发文部门字段不为空
		if(!publishUnitTempStr.equals("")) {
			//如果有多个发文部门
			String[] array = publishUnitTempStr.split(" ");
			//发文部门是单个部门，直接返回
			if(array.length == 1) {
				return PolicyLevelEnum.getInfoByName(array[0].trim());
			}
			//发文部门是多个部门，需要挨个解析一遍
			List<String> list1 = Arrays.asList(array);
			List<String> listResult = list1.stream().map(e->PolicyLevelEnum.getInfoByName(e.trim())).collect(Collectors.toList());
			if(listResult.size()>0) {
				return listResult.toString().substring(1,listResult.toString().length()-1);
			}else {
				return "国家政策";
			}
			
		}
		
		//如果该项未填任何内容，则为国家政策,需要仔细拆分，获取发文部分信息
		//如果是国家政策
		JXDocument jxDocument = JXDocument.create(page.getHtml().toString());
		List<JXNode> jxNodeList = jxDocument.selN("//p[@style*='TEXT-ALIGN: right']|//p[@style*='text-align: right']");	
//		jxNodeList.addAll(jxDocument.selN("//p[@style*='text-align: right']"));
		if(jxNodeList == null ||jxNodeList.size()<=0) {
			//说明文章中也没有找到发文部门可能相关的内容
			return "国家政策";
		}

		//trim方法只能去掉半角空格，去不掉全角空格
		List<String> strListFilter = jxNodeList.stream()
												.map(e->StringUtils.replaceBlank(ReduceHtml2Text.removeHtmlTag(e.toString())))
												.filter(PolicyLevelEnum::isNameExists)
												.collect(Collectors.toList());
		if(strListFilter==null || strListFilter.size()<=0) {
			return "国家政策";
		}
		
		String[] tempArrayInner = null;
		List<String> listTemp = new ArrayList<String>();
		for(String str : strListFilter) {
			tempArrayInner = str.split(" ");
			listTemp.addAll(Arrays.asList(tempArrayInner));
		}
		
		String[] tempArray = null;
		tempArray = listTemp.toArray(new String[listTemp.size()]);
//		tempArray = strListFilter.get(0).split(" ");	
		
		if(tempArray == null || tempArray.length == 0) {
			return "国家政策";			
		}
		if(tempArray.length == 1) {
			return PolicyLevelEnum.getInfoByName(tempArray[0].trim());
		}		
		String publishUnit = "";
		for(int i=0; i<tempArray.length;i++) {
			if(tempArray[i].replaceAll("\\s*", "").equals("") || !PolicyLevelEnum.isNameExists(tempArray[i].replaceAll("\\s*", "")))
				continue;
			String tempName = PolicyLevelEnum.getInfoByName(tempArray[i].trim());
			publishUnit = publishUnit + (tempName==null?"国家政策":tempName)+",";
		}
		//去除最后的逗号
		publishUnit = publishUnit.substring(0,publishUnit.length()-1);
		return publishUnit;
	}
	
	/**
	 * 从题目或者发文字号中获取可能的发文部门
	 * @param title
	 * @param publishNo
	 * @return
	 */
	public String dealPublishUnitLike(String title,String publishNo) {
		String returnStr = PolicyLevelEnum.getInfoByNameLike(title);
		return returnStr == null ? PolicyLevelEnum.getInfoByNameLike(publishNo) : returnStr;
	}
		
	
	
	//方法测试
	public static void main(String[] args) {
//		Spider spider = Spider.create(new PolicyNewestPageProcessor())
//				.addPipeline(new ConsolePipeline()).thread(1)
//				.setDownloader(new SeleniumDownloader("D://chromedriver/chromedriver.exe"))
//				.addUrl("http://zcydt.fzgg.tj.gov.cn/gllm/zxzc/index.shtml");
//		
//		spider.run();
		String str1 = "http://hd.chinatax.gov.cn/guoshui/action/ShowAppend.do?id=16379";
		String str2 = "http://zcydt.fzgg.tj.gov.cn/zcb/sjbm/snw_147/201803/W020181128360995854812.txt";
		String str3 = "http://zcydt.fzgg.tj.gov.cn/zcb/qzf/wqq_189/201812/P020181229617402808143.docx";
		String str4 = "http://hd.chinatax.gov.cn/guoshui/action/ShowAppend.do?id=16340";
		String str5 = "http://zcydt.fzgg.tj.gov.cn/zcb/sjbm/sscjgw_154/201806/P020180629526320286027.docx";
		
		System.out.println(str1.matches(UrlRegexConst.ATTACHMENT_URL_ADJUST_REX));
		System.out.println(str2.matches(UrlRegexConst.ATTACHMENT_URL_ADJUST_REX));
		System.out.println(str3.matches(UrlRegexConst.ATTACHMENT_URL_ADJUST_REX));
		System.out.println(str4.matches(UrlRegexConst.ATTACHMENT_URL_REX));
		System.out.println(str5.matches(UrlRegexConst.ATTACHMENT_URL_ADJUST_REX));
		
		String str = "<a href="+"../../../../zcbjd/sjbmjd/ssww_199/201807/t20180726_49716.shtml>"+"市商务委 市财政局关于印发天津市2018年度市外经贸发展资金鼓励企业开展技术研发和创新项目申报指南政策解读</a>";
		String replaceStr = "http://zcydt.fzgg.tj.gov.cn/zcbjd/sjbmjd/ssww_199/201807/t20180726_49716.shtml";
		
	}
}
