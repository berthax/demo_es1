//package com.troila.tjsmesp.spider.scheduled;
//
//import java.util.Date;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.troila.tjsmesp.spider.config.SpiderConfig;
//import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
//import com.troila.tjsmesp.spider.crawler.downloader.SeleniumDownloader;
//import com.troila.tjsmesp.spider.crawler.pipeline.RedisPipiline;
//import com.troila.tjsmesp.spider.crawler.processor.PolicyNewestPageProcessor;
//import com.troila.tjsmesp.spider.crawler.processor.PolicyReadingPageProcessor;
//import com.troila.tjsmesp.spider.service.PolicyService;
//
//import us.codecraft.webmagic.Spider;
//
//@Component
//public class CrawlPolicyDataSchedule {
//
//	private final Logger logger = LoggerFactory.getLogger(this.getClass());
//			
//	@Autowired
//	private RedisPipiline redisPipeline;
//	@Autowired
//	private SeleniumDownloader seleniumDownloader;
//	@Autowired
//	private SpiderConfig spiderConfig;
//	@Autowired
//	private PolicyService policyService;	
//	@Autowired
//	private PolicyNewestPageProcessor policyNewestPageProcessor;
//	@Autowired
//	private PolicyReadingPageProcessor policyReadingPageProcessor;
//	
//	@Autowired
//	private RedisTemplate<String, Object> redisTemplate;
//		
//	private Spider spiderNewest = null;
//	
//	private Spider spiderReading = null;
//	
//	//标记上次的定时任务是否已经完成
//	private boolean lastIsCompleted = true;
//		
//	private void init() {
//		//初始化最新政策的爬虫实例
//		spiderNewest = Spider.create(policyNewestPageProcessor)
//				.addPipeline(redisPipeline)
//				.setDownloader(seleniumDownloader)
//				.addUrl(spiderConfig.getPolicyNewestStartUrl())
//				.thread(spiderConfig.getSpiderThreadNumber());
//		//删除原缓存中的内容
//		redisTemplate.delete(SpiderModuleEnum.POLICY_NEWEST.getKey());
////		map.put(SpiderModuleEnum.POLICY_NEWEST.getKey(), spiderNewest);
//		
//		//初始化政策解读的爬虫实例
//		spiderReading = Spider.create(policyReadingPageProcessor)
//				.addPipeline(redisPipeline)
//				.setDownloader(seleniumDownloader)
//				.addUrl(spiderConfig.getPolicyReadingStartUrl())
//				.thread(spiderConfig.getSpiderThreadNumber());	
//		//删除原缓存中的内容
//		redisTemplate.delete(SpiderModuleEnum.POLICY_READING.getKey());
////		map.put(SpiderModuleEnum.POLICY_READING.getKey(), spiderReading);
//		lastIsCompleted = false;
//	}
//	/**
//	 * 定期执行某项定时任务
//	 * 从0分钟开始，每隔一小时查看一次
//	 */
////	@Scheduled(cron="0 0/60 * * * ? ")
//	public void crawlPolicyDataAll() {
//		if(lastIsCompleted) {
//			logger.info("{}开始执行定时爬取任务，……",new Date());
//			try {
//				//初始化爬虫实例
//				init();
//				//问题，第二次进来的时候，已经记录了上一次的爬取记录，不会再去重新爬取
//				//Spider spiderNewest = map.get(SpiderModuleEnum.POLICY_NEWEST.getKey());				
//				//启动最新政策的爬虫，爬取数据				
//				spiderNewest.run();
//				logger.info("本次爬取最新政策任务已完成，共爬取记录数："+spiderNewest.getPageCount());
//				policyService.dataUpdate(SpiderModuleEnum.POLICY_NEWEST);
//				
////				Spider spiderReading = map.get(SpiderModuleEnum.POLICY_READING.getKey());
//				spiderReading.run();
//				logger.info("本次爬取政策解读任务已完成，共爬取记录数："+spiderReading.getPageCount());
//				policyService.dataUpdate(SpiderModuleEnum.POLICY_READING);				
//				lastIsCompleted = true;
//			} catch (Exception e) {
//				logger.error("执行定时爬取任务时发生异常……",e);
//			} finally {
//				spiderNewest = null;
//				spiderReading = null;
//				lastIsCompleted = true;
//			}
//		}else {
//			logger.info("{}上次任务还未完成，本次任务略过……",new Date());
//		}
//	}
//	
//	/**
//	 * 定期执行数据同步操作
//	 * 
//	 * {秒}  {分}  {时}  {日}  {月}  {周}
//	 * 
//	 * 有关定时任务表达式以及使用范围说明https://blog.csdn.net/ukulelepku/article/details/54310035
//	 */
//	//指定周五、周六的晚九点五十分进行数据同步
//	
//	
//	
////	@Scheduled(cron="0 0/5 * ? 1-12 3,4,5,6 ")
////	@Scheduled(cron="0 0 8-12/1 ? 1-12 3,4,5,6 ")
////	@Scheduled(cron="0 0/10 * ? 1-12 3,4,5,6 ")
//	public void syncPolicyDataLastWeek() {
//		try {
//			logger.info("{}开始执行数据同步任务，……",new Date());  //数据查重问题
//			//同步政策原文
//			policyService.syncLatestPolicyData(SpiderModuleEnum.POLICY_NEWEST,10);
//			//同步政策解读
//			policyService.syncLatestPolicyData(SpiderModuleEnum.POLICY_READING,10);
//			logger.info("{}数据同步任务结束，……",new Date());    //数据查重问题
//		} catch (Exception e) {
//			logger.error("数据同步任务出现异常，异常信息如下：",e);
//		}		
//	}
//}