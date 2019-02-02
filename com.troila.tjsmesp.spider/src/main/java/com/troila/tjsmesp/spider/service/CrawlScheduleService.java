package com.troila.tjsmesp.spider.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.troila.tjsmesp.spider.config.SpiderConfig;
import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.crawler.downloader.SeleniumDownloader;
import com.troila.tjsmesp.spider.crawler.pipeline.RedisPipiline;
import com.troila.tjsmesp.spider.crawler.processor.PolicyNewestPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.PolicyReadingPageProcessor;

import us.codecraft.webmagic.Spider;

@Service
public class CrawlScheduleService implements Runnable{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RedisPipiline redisPipeline;
	@Autowired
	private SeleniumDownloader seleniumDownloader;
	@Autowired
	private SpiderConfig spiderConfig;
	@Autowired
	private PolicyService policyService;	
	@Autowired
	private PolicyNewestPageProcessor policyNewestPageProcessor;
	@Autowired
	private PolicyReadingPageProcessor policyReadingPageProcessor;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	private Spider spiderNewest = null;
	
	private Spider spiderReading = null;
	
	//标记上次的定时任务是否已经完成
	private boolean lastIsCompleted = true;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		logger.info("数据爬取任务现在开始，请稍候……");
		crawlPolicyDataAll();
		logger.info("本次数据爬取任务结束,用时{}ms",(System.currentTimeMillis()-start));
	}
			
	private void init() {
		//初始化最新政策的爬虫实例
		spiderNewest = Spider.create(policyNewestPageProcessor)
				.addPipeline(redisPipeline)
				.setDownloader(seleniumDownloader)
				.addUrl(spiderConfig.getPolicyNewestStartUrl())
				.thread(spiderConfig.getSpiderThreadNumber());
		//删除原缓存中的内容
		redisTemplate.delete(SpiderModuleEnum.POLICY_NEWEST.getKey());
//		map.put(SpiderModuleEnum.POLICY_NEWEST.getKey(), spiderNewest);
		
		//初始化政策解读的爬虫实例
		spiderReading = Spider.create(policyReadingPageProcessor)
				.addPipeline(redisPipeline)
				.setDownloader(seleniumDownloader)
				.addUrl(spiderConfig.getPolicyReadingStartUrl())
				.thread(spiderConfig.getSpiderThreadNumber());	
		//删除原缓存中的内容
		redisTemplate.delete(SpiderModuleEnum.POLICY_READING.getKey());
//		map.put(SpiderModuleEnum.POLICY_READING.getKey(), spiderReading);
		lastIsCompleted = false;
	}
	/**
	 * 定期执行某项定时任务
	 * 从0分钟开始，每隔一小时查看一次，定时任务的执行频率由数据库Cron表
	 */
	public void crawlPolicyDataAll() {
		if(lastIsCompleted) {
			logger.info("{}开始执行定时爬取任务，……",new Date());
			try {
				//初始化爬虫实例
				init();		
				//启动最新政策的爬虫，爬取数据				
				spiderNewest.run();
				logger.info("本次爬取最新政策任务已完成，共爬取记录数："+spiderNewest.getPageCount());
				policyService.dataUpdate(SpiderModuleEnum.POLICY_NEWEST);
				
				spiderReading.run();
				logger.info("本次爬取政策解读任务已完成，共爬取记录数："+spiderReading.getPageCount());
				policyService.dataUpdate(SpiderModuleEnum.POLICY_READING);				
				lastIsCompleted = true;
			} catch (Exception e) {
				logger.error("执行定时爬取任务时发生异常……",e);
			} finally {
				spiderNewest = null;
				spiderReading = null;
				lastIsCompleted = true;
			}
		}else {
			logger.info("{}上次任务还未完成，本次任务略过……",new Date());
		}
	}
}
