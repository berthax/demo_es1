package com.troila.tjsmesp.spider.crawler.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.crawler.processor.base.AbstractPolicyPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.base.PageSettings;
import com.troila.tjsmesp.spider.crawler.service.NewsProcessorService;
import com.troila.tjsmesp.spider.crawler.site.SiteProcessorFactory;
import com.troila.tjsmesp.spider.crawler.site.SmeMiitGovCnProcessor;
/**
 * 中小企业信息网-》新闻资讯-》焦点新闻相关政策的爬取
 * 网址 	http://sme.miit.gov.cn/cms/news/100000/0000000033/0000000033.shtml
 * 此部分对应中小企网站要闻焦点-》国家模块的内容
 * @author xuanguojing
 *
 */
@Component("policyNewsFocusGuojiaPageProcessor")
public class PolicyNewsFocusGuojiaPageProcessor extends AbstractPolicyPageProcessor{
//	private static final Logger logger = LoggerFactory.getLogger(PolicyNewsFocusGuojiaPageProcessor.class);
	 /**
     * 要闻焦点-国家详情页的正则表达式   
     */
    private static final String ARTICLE_URL = "http://sme.miit.gov.cn/cms/news/100000/0000000033/(\\d+)/(\\d+)/(\\d+)/(\\w+)\\.shtml";
    
    /**
     *要闻焦点-国家列表页的正则表达式  
     */    
    private static final String LIST_URL = "http://sme.miit.gov.cn/cms/news/100000/0000000033/0000000033(_\\d+)*\\.shtml";
	
    
    private static final String channelid = "http://sme.miit.gov.cn/cms/news/100000/0000000033/0000000033_";
    
    @Autowired
    private NewsProcessorService newsProcessorService;
    
//    @Autowired
//    private SmeMiitGovCnProcessor smeMiitGovCnProcessor;
    
    
    /*@Autowired
    private NewsProcessorService newsProcessorService;
	@Override
	public void process(Page page) {
		if(page.getUrl().regex(LIST_URL).match()) {			
			listProcess(page);
		}else if(page.getUrl().regex(ARTICLE_URL).match()){
			detailProcess(page);
		}else {
			page.setSkip(true);
			return;
		}
		
	}

	@Override
	public Site getSite() {
		return Site.me().setRetryTimes(3).setSleepTime(1000).setDomain("http://www.sme.gov.cn");
	}

	public void listProcess(Page page) {
		List<String> list =  page.getHtml().xpath("//div[@class='new_title']").links().all();
		System.out.println(list);
		//将当前列表页所有的最新政策文章详情页加入到后续的url地址，有待继续爬取
		List<String> articleList = list.stream().filter(p->p.matches(ARTICLE_URL)).collect(Collectors.toList());
		page.addTargetRequests(articleList);
					
		//获取底部分页栏的所有a标签
		List<Element> urlList = Selectors.xpath("//div[@class='fl_page2']/a").selectElements(page.getHtml().toString());
		//获取总页码数，倒数第2个a标签为最后一页的页码
		String totalStr = Selectors.xpath("//a/text()").select(urlList.get(urlList.size()-2).toString()).replace(".", "");  
		//获取当前选择的页码数
		String currentPage = page.getHtml().xpath("//div[@class='fl_page2']/a[@class='selected']/text()").toString();
		if(Integer.parseInt(currentPage)<Integer.parseInt(totalStr)) {
			//将当前页的下一个列表页加入到后续爬取的url地址中
			page.addTargetRequest("http://www.sme.gov.cn/cms/news/100000/0000000033/0000000033_"+(Integer.parseInt(currentPage)+1)+".shtml");
		}
		List<String> list =  page.getHtml().xpath("//div[@class='new_title']").links().all();
		System.out.println(list);
		//将当前列表页所有的最新政策文章详情页加入到后续的url地址，有待继续爬取
		List<String> articleList = list.stream().filter(p->p.matches(ARTICLE_URL)).collect(Collectors.toList());
		page.addTargetRequests(articleList);
		
		// 过滤掉以前已经爬取过的记录，不再重复爬取
		List<String> pastCrawledUrls = newsProcessorService.getCrawledUrls(SpiderModuleEnum.POLICY_NEWS_FOCUS_GUOJIA.getIndex());	
		if(pastCrawledUrls != null  && pastCrawledUrls.size()>0) {
			articleList = articleList.stream().filter(p->!pastCrawledUrls.contains(p)).collect(Collectors.toList());
		}			
		page.addTargetRequests(articleList);
		
		//获取底部分页栏的所有a标签
		List<Element> urlList = Selectors.xpath("//div[@class='fl_page2']/a").selectElements(page.getHtml().toString());
		List<String> urls = urlList.stream()
				//取出a链接的页码
				.map(e->{return Selectors.xpath("//a/text()").select(e.toString()).replace(".", "");})
				// 筛除掉1和跳转两个值，此两种拼接后是无效的
				.filter(p->!(p.equals("1") || p.equals("跳转")))
				// 将页码拼接成有效的链接地址
				.map(e->channelid + e +".shtml")  
				.collect(Collectors.toList());
		//将其他列表页加入后续地址列表中
		page.addTargetRequests(urls);	
	}

	public void detailProcess(Page page) {
		String title = page.getHtml().xpath("//div[@class='head_1']/a/font/tidyText()").toString();
		String content = page.getHtml().xpath("//div[@class='news_nav']").toString();
		if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
			logger.info("文章链接页{}，未找到指定的页面内容，跳过该页面",page.getUrl());
			page.setSkip(true);
			return;
		}
		//具体字段内容解析
		NewsSpider spider = new NewsSpider();
		spider.setTitle(title);
		spider.setContent(page.getHtml().xpath("//div[@class='news_nav']").toString());
		List<Element> list = Selectors.xpath("//div[@class='head_2']/span/a").selectElements(page.getHtml().toString()); 
		String dateStr = Selectors.xpath("//a/@title").select(list.get(0).toString());
//		spider.setPublishDate(TimeUtils.getLongFormatDate(dateStr));  
		//截取到的时间是2019-06-19 09:06:028格式的
		spider.setPublishDate(TimeUtils.getFormatDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:sss"), dateStr));
		String sourceStr = Selectors.xpath("//a/@title").select(list.get(1).toString());
		spider.setSource(sourceStr);
		spider.setPublishUrl(page.getUrl().toString());
		spider.setFromLink(FromSiteEnum.ZHONGXIAOQIYEXINXIWANG.getLink());
		spider.setFromSite(FromSiteEnum.ZHONGXIAOQIYEXINXIWANG.getName());
		spider.setSpiderCode(MD5Util.getMD5(spider.getPublishUrl()));   //根据特定的内容生成MD5，作为该条记录的id
		spider.setSpiderModule(SpiderModuleEnum.POLICY_NEWS_FOCUS_GUOJIA.getIndex());
		page.putField(CrawlConst.CRAWL_ITEM_KEY, spider);		
	}*/

	@Override
	protected void configure(PageSettings pageSettings) {
		pageSettings.setArticleUrlRegex(ARTICLE_URL)
			.setListUrlRegex(LIST_URL)
			.setDomain("http://sme.miit.gov.cn")
			.setWebSiteListPrefix(channelid)
			.setSpiderProcess(SiteProcessorFactory.create(SmeMiitGovCnProcessor.class))
			.setProcessorService(newsProcessorService)
			.setModule(SpiderModuleEnum.POLICY_NEWS_FOCUS_GUOJIA);
	}
}
