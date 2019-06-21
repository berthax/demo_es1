package com.troila.tjsmesp.spider.crawler.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.crawler.processor.base.AbstractPolicyPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.base.PageSettings;
import com.troila.tjsmesp.spider.crawler.service.NewsProcessorService;
import com.troila.tjsmesp.spider.crawler.site.SiteProcessorFactory;
import com.troila.tjsmesp.spider.crawler.site.SouSouGovCn;

/**
 * 中国政府网-》新闻-》政务联播-》地方	的相关政策爬取
 * 网址：http://sousuo.gov.cn/column/30902/0.htm
 * 此部分对应中小企网站区域动态模块的内容
 * @author xuanguojing
 *
 */
@Component("policyNewsRegionalDynamicPageProcessor")
//public class PolicyNewsRegionalDynamicPageProcessor implements PageProcessor{
public class PolicyNewsRegionalDynamicPageProcessor extends AbstractPolicyPageProcessor{
	
	 /**
     * 区域动态详情页的正则表达式
     */
    private static final String ARTICLE_URL = "http://www.gov.cn/xinwen/(\\d+)-(\\d+)/(\\d+)/content_(\\d+).htm";
    											
    /**
     * 区域动态列表页的正则表达式
     */ 
    private static final String LIST_URL = "http://sousuo.gov.cn/column/30902/(\\d+).htm";
    
    @Autowired
    private NewsProcessorService newsProcessorService;

	@Override
	protected void configure(PageSettings pageSettings) {
		pageSettings.setArticleUrlRegex(ARTICLE_URL)
		.setListUrlRegex(LIST_URL)
		// 1.需要配置一个SpiderProcess
		.setSpiderProcess(SiteProcessorFactory.create(SouSouGovCn.class))
		.setWebSiteListPrefix("")
		.setModule(SpiderModuleEnum.POLICY_REGIONAL_DYNAMIC)
		.setDomain("http://www.gov.cn")
		.setProcessorService(newsProcessorService);	
		
	}
		
/*	@Override
	public void process(Page page) {
		if(page.getUrl().regex(LIST_URL).match()) {
			List<String> list =  page.getHtml().xpath("//div[@class='news_box']").links().all();
			//将当前列表页所有的最新政策文章详情页加入到后续的url地址，有待继续爬取
			List<String> articleList = list.stream().filter(p->p.matches(ARTICLE_URL)).collect(Collectors.toList());
			
			//查看已经爬取过的链接记录
			List<String> pastCrawledUrls = newsProcessorService.getCrawledUrls(SpiderModuleEnum.POLICY_REGIONAL_DYNAMIC.getIndex());
			if(pastCrawledUrls != null  && pastCrawledUrls.size()>0) {
				articleList = articleList.stream().filter(p->!pastCrawledUrls.contains(p)).collect(Collectors.toList());
			}			
			page.addTargetRequests(articleList);
			//将其他的最新政策列表页加入到后续的url地址，有待继续爬取,该网站列表页的连接是用js函数去做的，不能直接抓取

			//将下一页的列表页链接加入到后续的url地址，有待继续爬取
			List<String> urlList = list.stream().filter(p->p.matches(LIST_URL)).collect(Collectors.toList());
			page.addTargetRequests(urlList); 			
		}else {
//			System.out.println("当前下载的页面url为："+page.getUrl());
			//具体页面内容获取，具体字段拆分待完成
			NewsSpider spider = new NewsSpider();
			String title = page.getHtml().xpath("//div[@class='article oneColumn pub_border']/h1/tidyText()").toString();
			spider.setTitle(title);
			
			// 处理时间和来源字段
			String page_date = page.getHtml().xpath("//div[@class='pages-date']/tidyText()").toString();			
			String pages_print = page.getHtml().xpath("//div[@class='pages_print']/tidyText()").toString();			
			String dateSourceStr = page_date.replaceFirst(pages_print, "");	
			String source = page.getHtml().xpath("//div[@class='pages-date']/span/tidyText()").toString();
			String dateStr = dateSourceStr.replaceFirst(source, "").trim();			
			// 设置来源
			spider.setSource(source.replaceAll("来源：", "").trim());
			// 设置时间
			if(!StringUtils.isEmpty(dateStr)) {
				spider.setPublishDate(TimeUtils.getFormatDate(new SimpleDateFormat("yyyy-MM-dd HH:mm"), dateStr));
			}
//			String dateSourceStrArray[] = dateSourceStr.split("来源：");
//			if(dateSourceStrArray.length > 2) {				
//				spider.setPublishDate(TimeUtils.getFormatDate(new SimpleDateFormat("yyyy-MM-dd HH:mm"), dateSourceStrArray[0]));
//			}
									
//			String content = page.getHtml().xpath("//div[@class='pages_content']").toString();
			spider.setContent(page.getHtml().xpath("//div[@class='pages_content']").toString());
			
			spider.setPublishUrl(page.getUrl().toString());
			spider.setFromLink(FromSiteEnum.ZHONGGUOZHENGFUWANG.getLink());
			spider.setFromSite(FromSiteEnum.ZHONGGUOZHENGFUWANG.getName());
			
			spider.setSpiderCode(MD5Util.getMD5(spider.getPublishUrl()));   //根据特定的内容生成MD5，作为该条记录的id
			spider.setSpiderModule(SpiderModuleEnum.POLICY_REGIONAL_DYNAMIC.getIndex());
			page.putField(CrawlConst.CRAWL_ITEM_KEY, spider);
		}		
	}

	@Override
	public Site getSite() {		
		return Site.me().setRetryTimes(3).setSleepTime(1000).setDomain("http://www.sme.gov.cn");
	}
	
	public static void main(String[] args) {
		String listStr1 = "http://sousuo.gov.cn/column/30902/0.htm";
		System.out.println(listStr1.matches(LIST_URL));
		String listStr2 = "http://sousuo.gov.cn/column/30902/2.htm";
		System.out.println(listStr2.matches(LIST_URL));
		
		String articleStr1="http://www.gov.cn/xinwen/2019-06/18/content_5401108.htm";
		String articelStr2="http://www.gov.cn/xinwen/2019-06/17/content_5400938.htm";
		System.out.println(articleStr1.matches(ARTICLE_URL));
		System.out.println(articelStr2.matches(ARTICLE_URL));
		
		String str = "2019-06-17 09:42  来源： 海南日报";
		String[] strArray = str.split("来源：");
		System.out.println(strArray);
	}*/

}
