package com.troila.tjsmesp.spider.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.config.SpiderSettings;
import com.troila.tjsmesp.spider.crawler.downloader.SeleniumDownloader;
import com.troila.tjsmesp.spider.crawler.pipeline.InformixPipeline;
import com.troila.tjsmesp.spider.crawler.pipeline.NewsMysqlPipeline;
import com.troila.tjsmesp.spider.crawler.pipeline.PolicyMysqlPipeline;
import com.troila.tjsmesp.spider.crawler.pipeline.RedisPipiline;
import com.troila.tjsmesp.spider.crawler.processor.PolicyNewestPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.PolicyNewsFocusTianjinPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.PolicyReadingPageProcessor;
import com.troila.tjsmesp.spider.repository.mysql.PolicySpiderRepositoryMysql;
import com.troila.tjsmesp.spider.service.PolicyService;
import com.troila.tjsmesp.spider.service.ProcessorService;

import us.codecraft.webmagic.Spider;

@Component
@Order(1)
public class SpiderStarter implements CommandLineRunner{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PolicyMysqlPipeline policyMysqlPipeline;
	@Autowired
	private InformixPipeline informixPipeline;
	@Autowired
	private SeleniumDownloader seleniumDownloader;
	@Autowired
	private PolicySpiderRepositoryMysql policySpiderRepositoryMysql;
	
	@Autowired
	private RedisPipiline redisPipeline;
	@Autowired
	private SpiderSettings spiderSettings;
	@Autowired
	private PolicyService policyService;
	@Autowired
	private ProcessorService processorService;
	@Autowired
	private PolicyNewestPageProcessor policyNewestPageProcessor;
	@Autowired
	private PolicyReadingPageProcessor policyReadingPageProcessor;
	
	@Autowired
	private PolicyNewsFocusTianjinPageProcessor policyNewsFocusTianjinPageProcessor;
	@Autowired
	private NewsMysqlPipeline newsMysqlPipeline;
	
	@Override
	public void run(String... args) throws Exception {
		
		//初始化政策解读的爬虫实例
//		Spider	spiderReading = Spider.create(policyReadingPageProcessor)
//						.addPipeline(mysqlPipeline)
//						.setDownloader(seleniumDownloader)
////						.addUrl(spiderConfig.getPolicyReadingStartUrl())
//						.addUrl("http://zcydt.fzgg.tj.gov.cn/zcbjd/sjbmjd/ssww_199/201804/t20180402_47347.shtml")
//						.thread(spiderSettings.getThreadNumber());	
//		spiderReading.runAsync();


		// TODO Auto-generated method stub
//		Spider spider = Spider.create(new PolicyReadingPageProcessor()).addPipeline(mysqlPipeline).thread(2)
//				.setDownloader(seleniumDownloader)
//				.setScheduler(new QueueScheduler())
//				.addUrl("http://zcydt.fzgg.tj.gov.cn/zcbjd/index.shtml");
//		spider.runAsync();  //异步启动
		
		//抓取中小企信息网地方政府相关政策
//		Spider spider_localGov = Spider.create(new PolicyLocalGovPageProcessor()).addPipeline(informixPipeline).thread(1)
//				.setDownloader(seleniumDownloader)
////				.addUrl("http://www.sme.gov.cn/cms/news/100000/0000000225/0000000225.shtml");
//				.addUrl("http://www.sme.gov.cn/cms/news/100000/0000000225/2018/12/12/f096cf558a754e4fb42b5b98208de1ad.shtml");
//		spider_localGov.runAsync();  	//异步启动
		
		
		
		//抓取中小企信息网行业热点相关政策
//		Spider spiderIndustryFocus = Spider.create(new PolicyIndustryFocusPageProcessor()).addPipeline(informixPipeline).thread(1)
//				.setDownloader(seleniumDownloader)
//				.addUrl("http://www.sme.gov.cn/cms/news/100000/0000000071/0000000071_20.shtml");
//		spiderIndustryFocus.runAsync();  //异步启动
		
//		List<PolicySpider> list = policySpiderRepositoryMysql.findByPublishUnitContaining("国家政策");
//		List<String> list1 = list.stream().map(PolicySpider::getPublishUrl)
//				.collect(Collectors.toList());
//		
//		
		//抓取天津政策一点通最新政策内容
//		Spider spiderNewest = Spider.create(policyNewestPageProcessor).addPipeline(mysqlPipeline).thread(1)
//				.setDownloader(seleniumDownloader)
//				.addPipeline(mysqlPipeline)
////				.addUrl("http://zcydt.fzgg.tj.gov.cn/gllm/zxzc/index.shtml");
//				.addUrl("http://zcydt.fzgg.tj.gov.cn/zcb/sjbm/ssww_131/201902/t20190214_52838.shtml");
////		for(String str : list1) {
////			spiderNewest.addUrl(str);
////		}
//		spiderNewest.runAsync();  //异步启动
//		http://zcydt.fzgg.tj.gov.cn/zcb/gjzc/201703/t20170322_20188.shtml    //财政部国家税务总局
//		Spider spiderNewest = Spider.create(policyNewestPageProcessor)
//				.addPipeline(mysqlPipeline)
//				.setDownloader(seleniumDownloader)
////				.addUrl(spiderConfig.getPolicyNewestStartUrl())
//				.addUrl("http://zcydt.fzgg.tj.gov.cn/zcb/gjzc/201703/t20170322_20188.shtml")
//				.thread(spiderSettings.getThreadNumber());
//		spiderNewest.runAsync();
//		logger.info("本次爬取最新政策任务已完成，共爬取记录数："+spiderNewest.getPageCount());
//		policyService.dataSync(SpiderModuleEnum.POLICY_NEWEST);
		
		//抓取中小企信息网焦点新闻的政策内容
//		Spider spiderNewestFocus = Spider.create(new PolicyNewsFocusPageProcessor()).addPipeline(informixPipeline).thread(1)
//				.setDownloader(seleniumDownloader)
//				.addUrl("http://www.sme.gov.cn/cms/news/100000/0000000033/0000000033.shtml");
//		spiderNewestFocus.runAsync();
		
//		Spider spiderMinistriesDynamic = Spider.create(new PolicyMinistriesDynamicPageProcessor()).addPipeline(informixPipeline).thread(2)
//				.setDownloader(seleniumDownloader)
//				.addUrl("http://www.sme.gov.cn/cms/news/100000/0000000224/0000000224.shtml");
//		spiderMinistriesDynamic.runAsync();
//	
		
//		Spider spiderNewsDistrict = Spider.create(policyNewsFocusTianjinPageProcessor).addPipeline(newsMysqlPipeline).thread(1)
//				.setDownloader(seleniumDownloader)
//				.addUrl("http://www.tj.gov.cn/xw/qx1/index.html");
//		spiderNewsDistrict.runAsync();
		
	}
	
		
	//以下内容是使用注解的方式，来爬取页面
/*   @Autowired
    private MySqlPageModelPipeline mySqlPageModelPipeline;

	@Override
	public void run(String... args) throws Exception {
		String url = "http://zcydt.fzgg.tj.gov.cn/zcbjd/index.shtml";
		OOSpider spider = (OOSpider) OOSpider.create(Site.me().setSleepTime(1000), mySqlPageModelPipeline, PolicyReading.class).thread(5);
		String urlTemplate = "http://zcydt.fzgg.tj.gov.cn/zcbjd/index%s.shtml";
		List<String> list = new ArrayList<String>();
        list.add(String.format(urlTemplate,""));
        list.add(String.format(urlTemplate,"_1"));
        list.add(String.format(urlTemplate,"_2"));
        list.add(String.format(urlTemplate,"_3"));
        
        
//        List<ResultItems> resultItemses = spider.<ResultItems>getAll(list);
        spider.startUrls(list);
        spider.run();
        
        
		OOSpider.create(Site.me().setSleepTime(1000)
	                , mySqlPageModelPipeline, PolicyReading.class)
//	                .addUrl("http://zcydt.fzgg.tj.gov.cn/zcbjd/sjbmjd/skw_201/201811/t20181115_51122.shtml")
	                .addUrl("http://zcydt.fzgg.tj.gov.cn/zcbjd/index_2.shtml")                
	                .thread(5).run();
		
	}*/
	
}