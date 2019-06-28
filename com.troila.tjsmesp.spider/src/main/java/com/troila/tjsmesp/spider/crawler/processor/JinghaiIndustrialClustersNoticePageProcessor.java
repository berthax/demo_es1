package com.troila.tjsmesp.spider.crawler.processor;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.troila.tjsmesp.spider.constant.CrawlConst;
import com.troila.tjsmesp.spider.constant.FromSiteEnum;
import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.constant.UrlRegexConst;
import com.troila.tjsmesp.spider.crawler.service.NewsProcessorService;
import com.troila.tjsmesp.spider.model.primary.NewsSpider;
import com.troila.tjsmesp.spider.util.MD5Util;
import com.troila.tjsmesp.spider.util.TimeUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectors;
@Component
public class JinghaiIndustrialClustersNoticePageProcessor implements PageProcessor{
//	http://ziya.tjjh.gov.cn/zhengwu/yuanquxinwen/3795-tian-jin-zi-ya-jing-ji-ji-zhu-kai-fa-qu-guan-che
	/**
     * 静海产业集聚，子牙循环经济网园区公告详情页的正则表达式   
     */
    private static final String ARTICLE_URL = "http://ziya.tjjh.gov.cn/zhengwu/yuanqugonggao/(\\d+)(-(\\w+))*";
    
    /**
     *静海产业集聚，子牙循环经济网园区公告列表页的正则表达式   
     */    
    private static final String LIST_URL = "http://ziya.tjjh.gov.cn/zhengwu/yuanqugonggao\\?page=(\\d+)";
	   
    private static final String channelid = "http://ziya.tjjh.gov.cn";
    //http://ziya.tjjh.gov.cn/attachments/807/download?locale=cn
    private String ATTACHMENTS_URL_ADJUST_REX = "http://ziya.tjjh.gov.cn/attachments/(\\d+)/download\\?locale=cn";
    //http://ziya.tjjh.gov.cn/system/attached_images/images/846/001_original.jpg?1560827790
    private String IMAGE_URL_ADJUST_REX = "http://ziya.tjjh.gov.cn/system/attached_images/images/846/001_original.jpg?1560827790";
    
//    /system/attached_images/images/847/%E6%9C%AA%E6%A0%87%E9%A2%98-2_original.jpg?1561431525
    
    @Autowired
    private NewsProcessorService newsProcessorService;
	
    private  List<String> pastCrawledUrls;
	
	@Override
	public void process(Page page) {
		if(page.getUrl().regex(LIST_URL).match()) {
			List<String> list =  page.getHtml().xpath("//div[@class='span8 main']").links().all();
			List<String> articleList = list.stream().filter(p->p.matches(ARTICLE_URL)).collect(Collectors.toList());
			// 判断一下，避免NPE异常
			if(null != articleList && articleList.size() > 0) {
				//	查看已经爬取过的链接记录
				pastCrawledUrls = newsProcessorService.getCrawledUrls(SpiderModuleEnum.JINGHAI_INDUSTRIAL_CLUSTERS_NOTICE.getIndex());
				if(pastCrawledUrls != null  && pastCrawledUrls.size()>0) {
					articleList = articleList.stream().filter(p->!pastCrawledUrls.contains(p)).collect(Collectors.toList());
				}			
				page.addTargetRequests(articleList);			
			}
			
			//	将下一页的列表页链接加入到后续的url地址，有待继续爬取
			List<String> urlList = list.stream().filter(p->p.matches(LIST_URL)).collect(Collectors.toList());
			// 判断一下，避免NPE异常
			if(null != urlList && urlList.size() > 0) {
				page.addTargetRequests(urlList); 			
			}
			
			list = null;
			articleList = null;
			pastCrawledUrls = null;		
			urlList = null;
			System.gc();
		}else if(page.getUrl().regex(ARTICLE_URL).match()){
			NewsSpider spider = new NewsSpider();
			String title = page.getHtml().xpath("//div[@class='infos detail']/div[@class='title']/h4/tidyText()").toString();
			String content = page.getHtml().xpath("//div[@class='infos detail']/div[@class='content']").toString();
		
			if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
//				logger.info("文章链接页{}，未找到指定的页面内容，跳过该页面",page.getUrl());
				page.setSkip(true);
				return;
			}
			
			//具体页面内容获取，具体字段拆分待完成
			spider.setTitle(title);
//			spider.setContent(content);		
			
			// 处理时间和来源字段
			String dateStr = page.getHtml().xpath("//div[@class='title']/p[@class='time']/tidyText()").toString();			
			String dateStrTemp = dateStr.replaceFirst("发表于:", "").trim();
			// 设置时间
			if(!StringUtils.isEmpty(dateStrTemp)) {
				spider.setPublishDate(TimeUtils.getFormatDate(new SimpleDateFormat("yyyy年MM月dd日"), dateStrTemp));
			}
			
			spider.setPublishUrl(page.getUrl().toString());
			spider.setFromLink(FromSiteEnum.ZHONGGUOZIYAXUNHUANJINGJIWANG.getLink());
			spider.setFromSite(FromSiteEnum.ZHONGGUOZIYAXUNHUANJINGJIWANG.getName());
			
			spider.setSpiderCode(MD5Util.getMD5(spider.getPublishUrl()));   //根据特定的内容生成MD5，作为该条记录的id
			spider.setSpiderModule(SpiderModuleEnum.JINGHAI_INDUSTRIAL_CLUSTERS_NOTICE.getIndex());
			
			//获取内容中所有的链接
			List<String> urls =  page.getHtml().xpath("//div[@class='infos detail']/div[@class='content']").links().all();
			
			List<String> attachmentList = urls.stream().filter(p->p.matches(ATTACHMENTS_URL_ADJUST_REX)).collect(Collectors.toList());
			if(attachmentList !=null && attachmentList.size()>0) {	
				content = adjustAttachmentsUrls(content, attachmentList);
			}
			
			//将一些图片链接也需要矫正(此处的list得到的是img标签)
//			List<Element> imageUrlList = Selectors.xpath("//img/@src").selectElements(content);
//			if(imageUrlList != null && imageUrlList.size() > 0) {
//				List<String> srcList = imageUrlList.stream()
//						.map(e->{return Selectors.xpath("img/@src").select(e);})
//						.collect(Collectors.toList());
//				removeScriptTagContent = policyProcessorService.replaceImageUrlForContent(removeScriptTagContent, srcList, page.getUrl().toString());    					
//			}
			
			spider.setContent(content);
			page.putField(CrawlConst.CRAWL_ITEM_KEY, spider);
		}else {
			return;
		}	
		
	}

	@Override
	public Site getSite() {
		return Site.me().setRetryTimes(3).setSleepTime(1000).setDomain("http://ziya.tjjh.gov.cn").setCharset("UTF-8");
	}
	
	
	/**
	 * 获取到的政策文章中的附件链接下载地址处理，全部替换成完整地址
	 * @param content
	 * @param attachmentList
	 * 内容中获取到的/attachments/806/download?locale=cn
	 * 要替换成的完整的http://ziya.tjjh.gov.cn/attachments/806/download?locale=cn
	 */
	public String adjustAttachmentsUrls(String content,List<String> attachmentList) {
		if(content==null || "".equals(content) || attachmentList== null) {
			return "";
		}
		String returnStr = content;	
		try {
			//处理将content中的相对链接下载地址换成绝对地址
			for(String attachment : attachmentList) {
				if(attachment.matches(ATTACHMENTS_URL_ADJUST_REX)) {
					String[] attachIn =  attachment.split(channelid);
					String oldReplaceStr = attachIn[attachIn.length-1];
					// 处理问号的正则匹配，否则无法替换，因为正则不认识
					oldReplaceStr = oldReplaceStr.replaceFirst("\\?", "\\\\?"); 
					returnStr = returnStr.replaceAll(oldReplaceStr, attachment);
				}
			}			
		}catch(Exception e) {
//			logger.error("替换附件下载链接地址时出错：信息为:",e);
		}
		return returnStr;
	}

	
	private String adjustImageUrls(String content) {
		return null;
	}

	public static void main(String[] args) {
		String str1 = "http://ziya.tjjh.gov.cn/zhengwu/yuanquxinwen/3795-tian-jin-zi-ya-jing-ji-ji-zhu-kai-fa-qu-guan-che";		
		String str3 = "http://ziya.tjjh.gov.cn/zhengwu/yuanquxinwen/3766-tian-jin-zi-ya-jing-ji-ji-zhu-kai-fa-qu-zhao-kai";
		String str2 = "http://ziya.tjjh.gov.cn/zhengwu/yuanquxinwen?page=1";
		System.out.println(str3.matches(ARTICLE_URL));
		System.out.println(str2.matches(LIST_URL));
		
		String str = "<a href=\"/attachments/807/download?locale=cn\">附件下载</a>";
		String oldReplaceStr = "/attachments/807/download?locale=cn";
		oldReplaceStr = oldReplaceStr.replaceFirst("\\?", "\\\\?");
		System.out.println(oldReplaceStr);
		String replace = "http://ziya.tjjh.gov.cn/attachments/807/download?locale=cn";
		String strr = str.replaceAll(oldReplaceStr, replace);
		System.out.println(strr);
	}
}
