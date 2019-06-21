package com.troila.tjsmesp.spider.crawler.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.crawler.processor.base.AbstractPolicyPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.base.PageSettings;
import com.troila.tjsmesp.spider.crawler.service.NewsProcessorService;
import com.troila.tjsmesp.spider.crawler.site.SiteProcessorFactory;
import com.troila.tjsmesp.spider.crawler.site.TjGovCnProcessor;

/**
 * 天津政务网-》新闻-》各区动态相关政策的爬取
 * 网址http://www.tj.gov.cn/xw/qx1/
 * 此部分对应中小企网站的要闻焦点-》天津模块的内容
 * @author xuanguojing
 *
 */
@Component
public class PolicyNewsFocusTianjinPageProcessor extends AbstractPolicyPageProcessor{
	
	/**
     * 要闻焦点-天津详情页的正则表达式
     */
    private static final String ARTICLE_URL = "http://www.tj.gov.cn/xw/qx1/(\\d+)/(\\w+)\\.html";
    		
    /**
     *要闻焦点-天津列表页的正则表达式
     */    
    private static final String LIST_URL = "http://www\\.tj\\.gov\\.cn/xw/qx1/index(_\\d+)*\\.html";
    
    @Autowired
    private  NewsProcessorService  newsProcessorService;

	@Override
	protected void configure(PageSettings pageSettings) {
		pageSettings.setArticleUrlRegex(ARTICLE_URL)
		.setListUrlRegex(LIST_URL)
		// 1.需要配置一个SpiderProcess
		.setSpiderProcess(SiteProcessorFactory.create(TjGovCnProcessor.class))
		.setWebSiteListPrefix("")
		.setModule(SpiderModuleEnum.POLICY_NEWS_FOCUS_TIANJIN)
		.setDomain("http://sme.miit.gov.cn")
		.setProcessorService(newsProcessorService);		
	}
		
	/*@Override
	public void process(Page page) {
		if(page.getUrl().regex(LIST_URL).match()) {
			List<String> list =  page.getHtml().xpath("//div[@class='left leftlist']").links().all();
			
//			for(String str : list)
//				System.out.println(str);
			
			//	将当前列表页所有的最新政策文章详情页加入到后续的url地址，有待继续爬取
			List<String> articleList = list.stream().filter(p->p.matches(ARTICLE_URL)).collect(Collectors.toList());
			
			// 过滤掉以前已经爬取过的记录，不再重复爬取
			List<String> pastCrawledUrls = newsProcessorService.getCrawledUrls(SpiderModuleEnum.POLICY_NEWS_FOCUS_TIANJIN.getIndex());	
			if(pastCrawledUrls != null  && pastCrawledUrls.size()>0) {
				articleList = articleList.stream().filter(p->!pastCrawledUrls.contains(p)).collect(Collectors.toList());
			}
			
			page.addTargetRequests(articleList);
			//将下一页的列表页链接加入到后续的url地址，有待继续爬取
			List<String> urlList = list.stream().filter(p->p.matches(LIST_URL)).collect(Collectors.toList());
			page.addTargetRequests(urlList);  			
		}else if(page.getUrl().regex(ARTICLE_URL).match()){
			NewsSpider spider = new NewsSpider();
			String selectedDiv = page.getHtml().xpath("//div[@class='left leftlist']").toString();
			spider.setTitle(Selectors.xpath("//div[@class='title']/text()").select(selectedDiv));
			List<Element> list1 = Selectors.xpath("//div[@class='time xwlc pd']/span").selectElements(selectedDiv);
			String[] sourceStrArray =  Selectors.xpath("//span/text()").select(list1.get(0).toString()).split("来源：");
			// 注意判断一下，避免有的记录没有指定该字段，那么数据项为空，出现异常
			if(sourceStrArray.length > 1) {
				spider.setSource(sourceStrArray[1]);				
			}
			String[] dateStrArray = Selectors.xpath("//span/text()").select(list1.get(1).toString()).split("发布时间：");
			if(dateStrArray.length > 1) {				
				spider.setPublishDate(TimeUtils.getFormatDate(new SimpleDateFormat("yyyy-MM-dd HH:mm"), dateStrArray[1]));
			}
			String content = Selectors.xpath("//div[@class='concon']").select(selectedDiv);
			spider.setContent(content);
			spider.setPublishUrl(page.getUrl().toString());
			spider.setFromSite(FromSiteEnum.TIANJINZHENGWUWANG.getName());
			spider.setFromLink(FromSiteEnum.TIANJINZHENGWUWANG.getLink());
			spider.setSpiderCode(MD5Util.getMD5(spider.getPublishUrl()));   //根据特定的内容生成MD5，作为该条记录的id
			spider.setSpiderModule(SpiderModuleEnum.POLICY_NEWS_FOCUS_TIANJIN.getIndex());
			page.putField(CrawlConst.CRAWL_ITEM_KEY, spider);
		}else {
			return;
		}
	}

	@Override
	public Site getSite() {
		return Site.me().setRetryTimes(3).setSleepTime(1000).setDomain("http://www.tj.gov.cn");
	}

	public static void main(String[] args) {
		String url1 = "http://www.tj.gov.cn/xw/qx1/201812/t20181207_3646000.html";
		String url2 = "http://www.tj.gov.cn/xw/qx1/201812/t20181207_3645997.html";
		String url3 = "http://www.tj.gov.cn/xw/qx1/201812/t20181207_3646011.html";
		
		System.out.println(url1.matches(ARTICLE_URL));
		System.out.println(url2.matches(ARTICLE_URL));
		System.out.println(url3.matches(ARTICLE_URL));
	}*/
}
