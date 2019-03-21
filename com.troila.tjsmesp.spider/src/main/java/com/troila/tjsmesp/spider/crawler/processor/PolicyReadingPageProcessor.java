package com.troila.tjsmesp.spider.crawler.processor;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.constant.PolicyLevelEnum;
import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.constant.UrlRegexConst;
import com.troila.tjsmesp.spider.model.primary.PolicySpider;
import com.troila.tjsmesp.spider.service.ProcessorService;
import com.troila.tjsmesp.spider.util.MD5Util;
import com.troila.tjsmesp.spider.util.ReduceHtml2Text;
import com.troila.tjsmesp.spider.util.TimeUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectors;
/**
 * 爬取政策一点通政策解读的部分 
 * http://zcydt.fzgg.tj.gov.cn/zcbjd/
 * @author xuanguojing
 *
 */
@Component("policyReadingPageProcessor")
public class PolicyReadingPageProcessor implements PageProcessor{
	private static final Logger logger = LoggerFactory.getLogger(PolicyReadingPageProcessor.class);
		
	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setDomain("http://zcydt.fzgg.tj.gov.cn");
	    
    /**
     * 政策解读详情页的正则表达式
     */
    private static final String ARTICLE_URL = "http://zcydt\\.fzgg\\.tj\\.gov\\.cn/zcbjd/(\\w+)/((\\w+)/)*(\\d+)/(\\w+)\\.shtml";

    /**
     * 政策解读列表页的正则表达式
     */
    private static final String LIST_URL = "http://zcydt\\.fzgg\\.tj\\.gov\\.cn/zcbjd/index(_\\d+)*\\.shtml";
  
    //用于存储从列表页中获取的发布单位字段
    private Map<String,String> map = new ConcurrentHashMap<>();
    
    @Autowired
    @Qualifier("processorService")
    private ProcessorService processorService;
    
    @Autowired
	private RedisTemplate<String, Object> redisTemplate;
      
    String printStr = "<div class=\"function\"><a href=\"javascript:window.print();\">打印本页</a></div>";
    
	@Override
	public void process(Page page) {
				
        //如果是政策解读的详情页的话，解析页面，取到文章的各个详情信息
        if(page.getUrl().regex(ARTICLE_URL).match()){ 
	        	//如果是文章详情页的url，则解析页面，取到文章的各个详情信息
//	        	System.out.println("当前下载页面为："+url);
        		if(page.getHtml().xpath("//div[@class='jiedu_nr_1']")== null) {
        			page.setSkip(true);
        			logger.info("文章链接页{}，未找到指定的页面内容，跳过该页面",page.getUrl());
        		}else {
        			PolicySpider spider = new PolicySpider();
        			//获取标题
        			String title = page.getHtml().xpath("//div[@class='jiedu_nr_2']/text()").toString();
        			spider.setTitle(title);
        			//获取附带样式的内容
        			String content = page.getHtml().xpath("//div[@class='jiedu_nr_7']").toString();
        			String removePrintStr = content.replaceAll(printStr, "");
        			String removeScriptTagContent = ReduceHtml2Text.removeScriptTag(removePrintStr);
        			spider.setContent(removeScriptTagContent);
        			spider.setStripContent(page.getHtml().xpath("//div[@class='jiedu_nr_7']/tidyText()").toString());
        			String tempStr = page.getHtml().xpath("//div[@class='jiedu_nr_5']/tidyText()").toString();
        			//下边处理来源和政策发布时间字段
        			String str[] = tempStr.split("：");
        			String dateStr = str[2];
        			Date date = TimeUtils.getShortFormatDate(dateStr);
        			//设置发布时间
        			spider.setPublishDate(date);	    		
        			String str1 = str[1];
        			//政策解读详情页获取的发布单位经常是没有内容的，此处换成从列表页获取的内容
//        			String publishUnit = str1.substring(0, str1.length()-4).trim();   //分隔结果中第二部分呢，去除掉日期：这三个字符
        			
        			//设置来源
//	    		spider.setSource(source);
        			//发文单位(经常是获取到的是国家政策解读，此时需要再同步的时候，获取原文的发文单位)
        			String publishUnit = map.get(page.getUrl().toString());
        			spider.setPublishUnit(publishUnit);
        			spider.setPolicyLevel(PolicyLevelEnum.getLevelByMultiNames(publishUnit));   //需要整理出来，发文部门需要进一步处理
//	    		map.remove(title);   //将map中的该项内容去掉
        			spider.setPublishUrl(page.getUrl().toString());
        			spider.setFromLink("http://zcydt.fzgg.tj.gov.cn");
        			spider.setFromSite("政策一点通");
        			spider.setForwardTime(new Date());	
        			spider.setId(MD5Util.getMD5(page.getUrl().toString()));   //根据文章链接生成MD5，作为该条记录的id，因为文章链接是唯一的
        			spider.setSpiderModule(SpiderModuleEnum.POLICY_READING.getIndex());
        			//查看文章附件情况
    				List<String> attachmentList =  page.getHtml().xpath("//div[@class='jiedu_nr_7']").links().all();
    				List<String> attachmentListFilter = attachmentList.stream().filter(p->p.matches(UrlRegexConst.ATTACHMENT_URL_REX)).collect(Collectors.toList());
    				if(attachmentListFilter !=null && attachmentListFilter.size()>0) {	
    					spider.setAttachment(attachmentListFilter.toString().substring(1,attachmentListFilter.toString().length()-1));
    					removeScriptTagContent = processorService.replaceAttachmentsUrlForContent(removeScriptTagContent, attachmentListFilter);
    				}			
    				spider.setContent(removeScriptTagContent);        			
        			page.putField("policy", spider);       			
        		}
        	}else if(page.getUrl().regex(LIST_URL).match()){ 
        		//解读的文章详情页很多没有发布单位，需要从列表页爬取这个字段内容
        		List<Element> listLi = Selectors.xpath("//div[@class='list_jd_content']/ul/li").selectElements(page.getHtml().toString());
       		
        		//从列表页提取文章标题和发布单位字段存入map
//        		map = listLi.stream().collect(Collectors.toMap(
//        				e->{
////        					 String title = Selectors.xpath("//a[@target='_blank']/text()").select(e);
////        					//当标题行内容过长时，后边会附加...的内容，导致和详情页的标题不匹配，而取不到发布单位
////        					if(title.length()>TITLE_MAX_LENGTH) { 
////        						title = title.substring(0, title.length()-3);   //去掉最后的... 						
////        					}
////        					return title;
//        					String a_href = Selectors.xpath("//a[@target='_blank']/@href").select(e);
//        					return "http://zcydt.fzgg.tj.gov.cn/zcbjd"+a_href.substring(1);
////        					String a_href = page.getHtml().xpath("//div[@class='list_jd_content']").links().all();
//        					},
////        					return Selectors.xpath("//a[@target='_blank']/text()").select(e);}, 
//        				e->{return Selectors.xpath("//span/a/text()").select(e);},
//        				(oldValue, newValue) -> newValue)); 

        		//注意此处不能使用Java8的stream的方式来做，否则会由于其内部多线程的缘故，导致执行顺序出现问题，在后边爬取文章详情页时，从map中可能取不到值
        		//当对执行顺序有要求时，使用stream需谨慎
        		for(Element e : listLi) {
        			String a_href = Selectors.xpath("//a[@target='_blank']/@href").select(e);
        			String key = "http://zcydt.fzgg.tj.gov.cn/zcbjd"+a_href.substring(1);
        			String value = Selectors.xpath("//span/a/text()").select(e);
        			map.put(key, value);
        		}
     		
//        		List<Element> listArticle = Selectors.xpath("//div[@class='list_jd_content']/ul/li/a[@target='_blank']").selectElements(page.getHtml().toString());
//        		
//        		String urlBase = "http://zcydt.fzgg.tj.gov.cn/zcbjd";
//        		List<String> listArticleUrls = listArticle.stream().map(e->{return urlBase + e.attr("href").substring(1);}).collect(Collectors.toList());
//        		//将列表页中的所有文章详情页的链接加入到后续的url地址，有待继续爬取
//        		page.addTargetRequests(listArticleUrls);
//        		
//        		//获取当前列表页的下一个列表页链接
//        		List<Element> listNextPage= Selectors.xpath("//div[@class='page']/a").selectElements(page.getHtml().toString());
////        		System.out.println(listNextPage);
//        		List<String> listNextPageStr = listNextPage.stream()
//        				.filter(p->p.toString().contains("下一页"))
//        				.map(e->{return urlBase + "/" + e.attr("href");})
//        				.collect(Collectors.toList());
////        		System.out.println(listNextPageStr);
//        		//获取当前列表页的下一个列表页链接加入后续的url地址
//        		page.addTargetRequests(listNextPageStr);   
        		
        		
        		List<String> list =  page.getHtml().xpath("//div[@class='list_jd_content']").links().all();
    			//将所有的政策解读文章详情页链接加入到后续的url地址，有待继续爬取
    			List<String> articleList = list.stream().filter(p->p.matches(ARTICLE_URL)).collect(Collectors.toList());
    			//获取到过去已经成功爬取的链接记录
    			List<String> pastCrawledUrls = processorService.getCrawledUrls(SpiderModuleEnum.POLICY_READING);		
    			if(pastCrawledUrls != null  && pastCrawledUrls.size()>0) {
    				articleList = articleList.stream().filter(p->!pastCrawledUrls.contains(p)).collect(Collectors.toList());
    			}
    			page.addTargetRequests(articleList);
    			//将其他的政策解读列表页加入到后续的url地址，有待继续爬取
    			List<String> urlList = list.stream().filter(p->p.matches(LIST_URL)).collect(Collectors.toList());
    			page.addTargetRequests(urlList);	
        	}      
	}

	@Override
	public Site getSite() {		
		return site;
	}
}
