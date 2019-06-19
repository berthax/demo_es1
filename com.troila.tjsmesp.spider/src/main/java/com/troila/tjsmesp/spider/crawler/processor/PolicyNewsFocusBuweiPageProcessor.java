package com.troila.tjsmesp.spider.crawler.processor;

import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.troila.tjsmesp.spider.constant.CrawlConst;
import com.troila.tjsmesp.spider.constant.FromSiteEnum;
import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.crawler.processor.abs.AbstractPolicyPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.abs.PageSettings;
import com.troila.tjsmesp.spider.crawler.service.NewsProcessorService;
import com.troila.tjsmesp.spider.crawler.site.SpiderProcess;
import com.troila.tjsmesp.spider.model.primary.NewsSpider;
import com.troila.tjsmesp.spider.util.MD5Util;
import com.troila.tjsmesp.spider.util.TimeUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectors;
/**
 * 中小企业信息网-》政务频道-》部委动态相关政策的爬取
 * 网址http://www.sme.gov.cn/cms/news/100000/0000000224/0000000224.shtml
 * 网站修改后的地址
 *  http://sme.miit.gov.cn/cms/news/100000/0000000224/0000000224.shtml
 * 此部分对应中小企网站的部委模块的内容
 * @author xuanguojing
 *
 */
@Component("policyMinistriesDynamicPageProcessor")
public class PolicyNewsFocusBuweiPageProcessor extends AbstractPolicyPageProcessor{
	private static final Logger logger = LoggerFactory.getLogger(PolicyNewsFocusBuweiPageProcessor.class);
	 /**
     * 部委动态文章详情页的正则表达式
     */
    private static final String ARTICLE_URL = "http://sme.miit.gov.cn/cms/news/100000/0000000224/(\\d+)/(\\d+)/(\\d+)/(\\w+)\\.shtml";
    
    /**
     * 部位动态列表页的正则表达式
     */    
    private static final String LIST_URL = "http://sme.miit.gov.cn/cms/news/100000/0000000224/0000000224(_\\d+)*\\.shtml";
    
    private static final String channelid = "http://sme.miit.gov.cn/cms/news/100000/0000000224/0000000224_";
    
    @Autowired
    private NewsProcessorService newsProcessorService;
    
    
    @Override
	protected void configure(PageSettings pageSettings) {
		pageSettings.setArticleUrlRegex(ARTICLE_URL);
		pageSettings.setListUrlRegex(LIST_URL);
		//TODO 1.需要配置一个SpiderProcess
		//     2.可以优化一个pageSettings配置
	}
    
//	@Override
//	public void process(Page page) {
//		if(page.getUrl().regex(LIST_URL).match()) {
//			listProcess(page);
//		}else if(page.getUrl().regex(ARTICLE_URL).match()){
//			detailProcess(page);
//		}else {
//			return;
//		}		
//	}
//
//	@Override
//	public Site getSite() {
//		return Site.me().setRetryTimes(3).setSleepTime(1000).setDomain("http://sme.miit.gov.cn");
//	}
    
	/**
	 * 对爬取的列表页的处理
	 */
//	@Override
//	public void listProcess(Page page) {
//		List<String> list =  page.getHtml().xpath("//div[@class='new_title']").links().all();
//		System.out.println(list);
//		//将当前列表页所有的最新政策文章详情页加入到后续的url地址，有待继续爬取
//		List<String> articleList = list.stream().filter(p->p.matches(ARTICLE_URL)).collect(Collectors.toList());
//		page.addTargetRequests(articleList);
//		
//		// 过滤掉以前已经爬取过的记录，不再重复爬取
//		List<String> pastCrawledUrls = newsProcessorService.getCrawledUrls(SpiderModuleEnum.POLICY_NEWS_FOCUS_BUWEI.getIndex());	
//		if(pastCrawledUrls != null  && pastCrawledUrls.size()>0) {
//			articleList = articleList.stream().filter(p->!pastCrawledUrls.contains(p)).collect(Collectors.toList());
//		}			
//		page.addTargetRequests(articleList);
//		
//		//获取底部分页栏的所有a标签
//		List<Element> urlList = Selectors.xpath("//div[@class='fl_page2']/a").selectElements(page.getHtml().toString());
//		List<String> urls = urlList.stream()
//				//取出a链接的页码
//				.map(e->{return Selectors.xpath("//a/text()").select(e.toString()).replace(".", "");})
//				// 筛除掉1和跳转两个值，此两种拼接后是无效的
//				.filter(p->!(p.equals("1") || p.equals("跳转")))
//				// 将页码拼接成有效的链接地址
//				.map(e->channelid + e +".shtml")  
//				.collect(Collectors.toList());
//		//将其他列表页加入后续地址列表中
//		page.addTargetRequests(urls);		
//	}

	/**
	 * 对爬取的文章详情页的处理
	 */
//	@Override
//	public void detailProcess(Page page) {
//		//具体字段内容解析
//		NewsSpider spider = new NewsSpider();
//		String title = page.getHtml().xpath("//div[@class='head_1']/a/font/tidyText()").toString();
//		String content = page.getHtml().xpath("//div[@class='news_nav']").toString();
//		
//		// 如果当前页面无法获取到有效信息内容，此页面跳过
//		if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
//			logger.info("文章链接页{}，未找到指定的页面内容，跳过该页面",page.getUrl());
//			page.setSkip(true);
//			return;
//		}
//			
//		spider.setTitle(page.getHtml().xpath("//div[@class='head_1']/a/font/tidyText()").toString());
//		List<Element> list = Selectors.xpath("//div[@class='head_2']/span/a").selectElements(page.getHtml().toString()); 
//		if(list.size()>=1) {
//			String dateStr = Selectors.xpath("//a/@title").select(list.get(0).toString());				
//			spider.setPublishDate(TimeUtils.getLongFormatDate(dateStr));
//		}
//		if(list.size()>=2) {
//			String sourceStr = Selectors.xpath("//a/@title").select(list.get(1).toString());				
//			spider.setSource(sourceStr);
//		}
//		spider.setContent(page.getHtml().xpath("//div[@class='news_nav']").toString());
//		spider.setPublishUrl(page.getUrl().toString());
//		spider.setFromLink(FromSiteEnum.ZHONGXIAOQIYEXINXIWANG.getLink());
//		spider.setFromSite(FromSiteEnum.ZHONGXIAOQIYEXINXIWANG.getName());
//		spider.setSpiderCode(MD5Util.getMD5(spider.getPublishUrl()));   //根据特定的内容生成MD5，作为该条记录的id
//		spider.setSpiderModule(SpiderModuleEnum.POLICY_NEWS_FOCUS_BUWEI.getIndex());
//		page.putField(CrawlConst.CRAWL_ITEM_KEY, spider);		
//	}


/*	public static void main(String[] args) {
		String url1 = "http://sme.miit.gov.cn/cms/news/100000/0000000224/0000000224.shtml";
		String url2 = "http://sme.miit.gov.cn/cms/news/100000/0000000224/0000000224_2.shtml";
		String url3 = "http://sme.miit.gov.cn/cms/news/100000/0000000224/0000000224_3.shtml";
		
		System.out.println(url1.matches(LIST_URL));
		System.out.println(url2.matches(LIST_URL));
		System.out.println(url3.matches(LIST_URL));
		
		String url4 = "http://sme.miit.gov.cn/cms/news/100000/0000000224/2019/5/28/effa9f1c76934246a59271e13b98d01f.shtml";
		String url5 = "http://sme.miit.gov.cn/cms/news/100000/0000000224/2019/5/27/5b14d9b94a0541bbaafda588068b4110.shtml";
		System.out.println(url4.matches(ARTICLE_URL));
		System.out.println(url5.matches(ARTICLE_URL));
	}*/
}
