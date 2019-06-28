package com.troila.tjsmesp.spider.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.config.SpiderSettings;
import com.troila.tjsmesp.spider.crawler.downloader.SeleniumDownloader;
import com.troila.tjsmesp.spider.crawler.pipeline.BaseRedisPipeline;
import com.troila.tjsmesp.spider.crawler.pipeline.NewsMysqlPipeline;
import com.troila.tjsmesp.spider.crawler.pipeline.PolicyMysqlPipeline;
import com.troila.tjsmesp.spider.crawler.pipeline.RedisPipeline;
import com.troila.tjsmesp.spider.crawler.processor.JinghaiIndustrialClustersNewsPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.JinghaiIndustrialClustersNoticePageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.PolicyNewestPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.PolicyNewsFocusBuweiPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.PolicyNewsFocusGuojiaPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.PolicyNewsFocusTianjinPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.PolicyNewsIndustryInfoPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.PolicyNewsRegionalDynamicPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.PolicyReadingPageProcessor;
import com.troila.tjsmesp.spider.repository.mysql.PolicySpiderRepositoryMysql;
import com.troila.tjsmesp.spider.scheduled.CrawlScheduleService;
import com.troila.tjsmesp.spider.scheduled.NewsCrawlScheduleService;
import com.troila.tjsmesp.spider.service.PolicyProcessorService;
import com.troila.tjsmesp.spider.service.PolicyService;

import us.codecraft.webmagic.Spider;

@Component
@Order(1)
public class SpiderStarter implements CommandLineRunner{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PolicyMysqlPipeline policyMysqlPipeline;
	@Autowired
	private SeleniumDownloader seleniumDownloader;
	@Autowired
	private PolicySpiderRepositoryMysql policySpiderRepositoryMysql;
	
	@Autowired
	private RedisPipeline redisPipeline;
	@Autowired
	private SpiderSettings spiderSettings;
	@Autowired
	private PolicyService policyService;
	@Autowired
	private PolicyProcessorService policyProcessorService;
	@Autowired
	private PolicyNewestPageProcessor policyNewestPageProcessor;
	@Autowired
	private PolicyReadingPageProcessor policyReadingPageProcessor;	
	@Autowired
	private PolicyNewsFocusTianjinPageProcessor policyNewsFocusTianjinPageProcessor;
	@Autowired
	private PolicyNewsFocusBuweiPageProcessor policyNewsFocusBuweiPageProcessor;
	@Autowired
	private PolicyNewsRegionalDynamicPageProcessor policyNewsRegionalDynamicPageProcessor;
	@Autowired
	private NewsMysqlPipeline newsMysqlPipeline;
	@Autowired
	private BaseRedisPipeline baseRedisPipeline;
	@Autowired
	private PolicyNewsFocusGuojiaPageProcessor policyNewsFocusGuojiaPageProcessor;
	@Autowired
	private PolicyNewsIndustryInfoPageProcessor policyNewsIndustryInfoPageProcessor;
	@Autowired
	private NewsCrawlScheduleService newsCrawlScheduleService;
	@Autowired
	private CrawlScheduleService crawlScheduleService;
	@Autowired
	private JinghaiIndustrialClustersNewsPageProcessor jinghaiIndustrialClustersNewsPageProcessor;
	@Autowired
	private JinghaiIndustrialClustersNoticePageProcessor jinghaiIndustrialClustersNoticePageProcessor;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public void run(String... args) throws Exception {
		
		//抓取天津政策一点通最新政策内容
//		Spider spiderNewest = Spider.create(policyNewestPageProcessor)
//				.addPipeline(mysqlPipeline)
//				.setDownloader(seleniumDownloader)
////				.addUrl(spiderConfig.getPolicyNewestStartUrl())
//				.addUrl("http://zcydt.fzgg.tj.gov.cn/zcb/gjzc/201703/t20170322_20188.shtml")
//				.thread(spiderSettings.getThreadNumber());
//		spiderNewest.runAsync();
//		logger.info("本次爬取最新政策任务已完成，共爬取记录数："+spiderNewest.getPageCount());
//		policyService.dataSync(SpiderModuleEnum.POLICY_NEWEST);
		
		//初始化政策解读的爬虫实例
//		Spider	spiderReading = Spider.create(policyReadingPageProcessor)
//						.addPipeline(mysqlPipeline)
//						.setDownloader(seleniumDownloader)
////						.addUrl(spiderConfig.getPolicyReadingStartUrl())
//						.addUrl("http://zcydt.fzgg.tj.gov.cn/zcbjd/sjbmjd/ssww_199/201804/t20180402_47347.shtml")
//						.addUrl("http://zcydt.fzgg.tj.gov.cn/zcbjd/index.shtml");
//						.thread(spiderSettings.getThreadNumber());	
//		spiderReading.runAsync();
		
				
//		Spider spiderNewsFocusTianjin = Spider.create(policyNewsFocusTianjinPageProcessor).addPipeline(newsMysqlPipeline).thread(2)
//				.setDownloader(seleniumDownloader)
//				.addUrl("http://www.tj.gov.cn/xw/qx1/index.html");
//		spiderNewsFocusTianjin.runAsync();
				
//		http://sme.miit.gov.cn/cms/news/100000/0000000033/0000000033.shtml
		
//		Spider spiderNewsFocusBuwei = Spider.create(policyNewsFocusBuweiPageProcessor)
//		.addPipeline(newsMysqlPipeline)
//		.thread(2)
//		.setDownloader(seleniumDownloader)
//		.addUrl("http://sme.miit.gov.cn/cms/news/100000/0000000224/0000000224.shtml");
//		spiderNewsFocusBuwei.runAsync();
//		
//		Spider spiderNewsFocusGuojia = Spider.create(policyNewsFocusGuojiaPageProcessor)
//				.addPipeline(newsMysqlPipeline)
//				.thread(2)
////				.setDownloader(seleniumDownloader)
//				.addUrl("http://sme.miit.gov.cn/cms/news/100000/0000000033/0000000033.shtml");
//		spiderNewsFocusGuojia.runAsync();
		
//		Spider spiderNewsRegionalDynamic = Spider.create(policyNewsRegionalDynamicPageProcessor)
//		.addPipeline(newsMysqlPipeline)
////		.addPipeline(baseRedisPipeline)
//		.thread(2)
////		.setDownloader(seleniumDownloader)
//		.addUrl("http://sousuo.gov.cn/column/30902/0.htm");
//		spiderNewsRegionalDynamic.runAsync();
		
//		Spider spiderNewsIndustryInfo = Spider.create(policyNewsIndustryInfoPageProcessor)
//				.addPipeline(newsMysqlPipeline)
//				.thread(2)
//				.setDownloader(seleniumDownloader)
//				.addUrl("http://sme.miit.gov.cn/cms/news/100000/0000000071/0000000071.shtml");
//		spiderNewsIndustryInfo.runAsync();
		
//		Spider spiderJinghaiIndustrialClustersNews = Spider.create(jinghaiIndustrialClustersNewsPageProcessor)
//		.addPipeline(newsMysqlPipeline)
//		.thread(2)
//		.addUrl("http://ziya.tjjh.gov.cn/zhengwu/yuanquxinwen?page=1");
//		spiderJinghaiIndustrialClustersNews.runAsync();
		
//		Spider spiderJinghaiIndustrialClustersNotice = Spider.create(jinghaiIndustrialClustersNoticePageProcessor)
//				.addPipeline(newsMysqlPipeline)
//				.thread(1)
//				.addUrl("http://ziya.tjjh.gov.cn/zhengwu/yuanqugonggao/3963-guan-yu-zhuan-fa-shi-shang-wu-ju-shi-cai-zheng-ju-guan");
//		spiderJinghaiIndustrialClustersNotice.runAsync();
		
		
		// 系统启动时，将信息爬取一次
//		newsCrawlScheduleService.crawlNewsDataAll();		
		
		
//		long size = redisTemplate.opsForList().size(SpiderModuleEnum.POLICY_NEWS_FOCUS_BUWEI.getKey());
//		
//		//从redis中获取本次爬取的所有记录
//		List<Object> redisListObj = redisTemplate.opsForList().range(SpiderModuleEnum.POLICY_NEWS_FOCUS_BUWEI.getKey(), 0L, size-1);
//		//更新成功后，将redis中的这些已经同步的数据删除
//		for(Object obj : redisListObj) {
//			redisTemplate.opsForList().remove(SpiderModuleEnum.POLICY_NEWS_FOCUS_BUWEI.getKey(), 0, obj);			
//		}
//		System.out.println("删除完成");
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