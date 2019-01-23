package com.troila.tjsmesp.spider.scheduled;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.config.SpiderConfig;
import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.crawler.downloader.SeleniumDownloader;
import com.troila.tjsmesp.spider.crawler.pipeline.MysqlPipeline;
import com.troila.tjsmesp.spider.crawler.pipeline.RedisPipiline;
import com.troila.tjsmesp.spider.crawler.processor.PolicyNewestPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.PolicyReadingPageProcessor;
import com.troila.tjsmesp.spider.service.PolicyService;

import us.codecraft.webmagic.Spider;

@Component
public class CrawlPolicyDataSchedule {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
			
	@Autowired
	private MysqlPipeline mysqlPipeline;
	@Autowired
	private RedisPipiline redisPipeline;
	@Autowired
	private SeleniumDownloader seleniumDownloader;
	@Autowired
	private SpiderConfig spiderConfig;
	@Autowired
	private PolicyService policyService;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	private Map<String,Spider> map = new ConcurrentHashMap<>();
	
	private Spider spiderNewest = null;
	
	private Spider spiderReading = null;
	
	//标记上次的定时任务是否已经完成
	private boolean lastIsCompleted = true;
		
	private void init() {
		//初始化最新政策的爬虫实例
		spiderNewest = Spider.create(new PolicyNewestPageProcessor())
				.addPipeline(redisPipeline)
				.setDownloader(seleniumDownloader)
				.addUrl(spiderConfig.getPolicyNewestStartUrl())
				.thread(spiderConfig.getSpiderThreadNumber());
		//删除原缓存中的内容
//		redisTemplate.delete(SpiderModuleEnum.POLICY_NEWEST.getKey());
//		map.put(SpiderModuleEnum.POLICY_NEWEST.getKey(), spiderNewest);
		
		//初始化政策解读的爬虫实例
		spiderReading = Spider.create(new PolicyReadingPageProcessor())
				.addPipeline(redisPipeline)
				.setDownloader(seleniumDownloader)
				.addUrl(spiderConfig.getPolicyReadingStartUrl())
				.thread(spiderConfig.getSpiderThreadNumber());	
		//删除原缓存中的内容
//		redisTemplate.delete(SpiderModuleEnum.POLICY_READING.getKey());
//		map.put(SpiderModuleEnum.POLICY_READING.getKey(), spiderReading);
		lastIsCompleted = false;
	}
	/**
	 * 定期执行某项定时任务
	 * 从0分钟开始，每隔3分钟查看一次
	 */
	@Scheduled(cron="0 0/5 * * * ? ")
	public void crawlPolicyDataAll() {
		if(lastIsCompleted) {
			logger.info("{}开始执行定时爬取任务，……",new Date());
			try {
				//初始化爬虫实例
				init();
				//问题，第二次进来的时候，已经记录了上一次的爬取记录，不会再去重新爬取
				//Spider spiderNewest = map.get(SpiderModuleEnum.POLICY_NEWEST.getKey());				
				//启动最新政策的爬虫，爬取数据				
				//spiderNewest.runAsync();
				
//				Spider spiderReading = map.get(SpiderModuleEnum.POLICY_READING.getKey());
				spiderReading.run();
				logger.info("本次爬取政策解读任务已完成，共爬取记录数："+spiderReading.getPageCount());
				policyService.dataSync(SpiderModuleEnum.POLICY_READING);
				
//				Thread.sleep(1000);
//				while(!spiderNewestFlag || !spiderReadingFlag) {	
//					if(spiderNewest.getStatus()==Spider.Status.Stopped && !spiderNewestFlag) {
//						logger.info("本次爬取最新政策任务已完成，共爬取记录数："+spiderNewest.getPageCount());
//						//由于爬虫实例每次爬取完成后，都会记录所有的爬取信息，无法恢复，所以每次爬取都要是一个新的爬虫对象
//						map.remove(SpiderModuleEnum.POLICY_NEWEST.getKey());
//						//每次爬完之后，要与之前数据库存储的进行比对，看看有哪些更新内容
//						policyService.dataSync(SpiderModuleEnum.POLICY_NEWEST);
//						spiderNewestFlag = true;
//					}	
//					if(spiderReading.getStatus()==Spider.Status.Stopped && !spiderReadingFlag) {
//						logger.info("本次爬取政策解读任务已完成，共爬取记录数："+spiderReading.getPageCount());
//						//由于爬虫实例每次爬取完成后，都会记录所有的爬取信息，无法恢复，所以每次爬取都要是一个新的爬虫对象
//						map.remove(SpiderModuleEnum.POLICY_READING.getKey());
//						//每次爬完之后，要与之前数据库存储的进行比对，看看有哪些更新内容
//						policyService.dataSync(SpiderModuleEnum.POLICY_READING);
//						spiderReadingFlag = true;
//					}					
//					Thread.sleep(10000);
//				}
				
				lastIsCompleted = true;
			} catch (Exception e) {
				logger.error("执行定时爬取任务时发生异常……",e);
			} finally {
				spiderNewest = null;
				spiderReading = null;
				lastIsCompleted = true;
			}
		}else {
			logger.info("上次任务还未完成，本次任务略过……");
		}
	}
}