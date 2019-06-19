package com.troila.tjsmesp.spider.scheduled;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.troila.tjsmesp.spider.config.SpiderSettings;
import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.constant.SpiderStartUrlConst;
import com.troila.tjsmesp.spider.crawler.downloader.SeleniumDownloader;
import com.troila.tjsmesp.spider.crawler.pipeline.BaseRedisPipeline;
import com.troila.tjsmesp.spider.crawler.pipeline.NewsMysqlPipeline;
import com.troila.tjsmesp.spider.crawler.processor.PolicyNewsFocusBuweiPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.PolicyNewsFocusGuojiaPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.PolicyNewsFocusTianjinPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.PolicyNewsIndustryInfoPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.PolicyNewsRegionalDynamicPageProcessor;
import com.troila.tjsmesp.spider.service.NewsService;

import us.codecraft.webmagic.Spider;

/**
 * 
 * @ClassName:  NewsCrawlScheduleService   
 * @Description:定时爬取新闻类信息
 * @author: xgj
 * @date:   2019年6月18日 上午11:41:29   
 *
 */
@Service
public class NewsCrawlScheduleService implements Runnable{
	
	private static final Logger logger = LoggerFactory.getLogger(NewsCrawlScheduleService.class);
	
	@Autowired
	private SeleniumDownloader seleniumDownloader;
	@Autowired
	private SpiderSettings spiderSettings;
//	@Autowired
//	private NewsMysqlPipeline newsMysqlPipeline;	
	@Autowired
	private BaseRedisPipeline baseRedisPipeline;
	@Autowired
	private PolicyNewsRegionalDynamicPageProcessor policyNewsRegionalDynamicPageProcessor;
	@Autowired
	private PolicyNewsFocusTianjinPageProcessor policyNewsFocusTianjinPageProcessor;
	@Autowired
	private PolicyNewsFocusGuojiaPageProcessor policyNewsFocusGuojiaPageProcessor;
	@Autowired
	private PolicyNewsFocusBuweiPageProcessor policyNewsFocusBuweiPageProcessor;
	@Autowired
	private PolicyNewsIndustryInfoPageProcessor policyNewsIndustryInfoPageProcessor;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	private Spider newsRegionalDynamic = null;
		
	private Spider newsFocusGuojia = null;
	
	private Spider newsFocusBuwei = null;
	
	private Spider newsFocusTianjin = null;
	
	private Spider newsIndustryInfo = null;
	
	//标记上次的定时任务是否已经完成
	private boolean lastIsCompleted = true;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		logger.info("数据爬取任务现在开始，请稍候……");
		crawlNewsDataAll();
		logger.info("本次数据爬取任务结束,用时{}ms",(System.currentTimeMillis()-start));
	}
				
	private void init() {		
		newsRegionalDynamic = Spider.create(policyNewsRegionalDynamicPageProcessor)
				.setDownloader(seleniumDownloader)
				.addPipeline(baseRedisPipeline)
				.addUrl(SpiderStartUrlConst.NEWS_REGION_DYNAMIC_START_URL)
				.thread(spiderSettings.getThreadNumber());
		redisTemplate.delete(SpiderModuleEnum.POLICY_REGIONAL_DYNAMIC.getKey());
		
		newsFocusTianjin = Spider.create(policyNewsFocusTianjinPageProcessor)
				.addPipeline(baseRedisPipeline)
				.setDownloader(seleniumDownloader)
				.addUrl(SpiderStartUrlConst.NEWS_FOCUS_TIANJIN_START_URL)
				.thread(spiderSettings.getThreadNumber());		
		redisTemplate.delete(SpiderModuleEnum.POLICY_NEWS_FOCUS_TIANJIN.getKey());
		
		newsFocusBuwei = Spider.create(policyNewsFocusBuweiPageProcessor)
				.addPipeline(baseRedisPipeline)
				.setDownloader(seleniumDownloader)
				.addUrl(SpiderStartUrlConst.NEWS_FOCUS_BUWEI_START_URL)
				.thread(spiderSettings.getThreadNumber());		
		redisTemplate.delete(SpiderModuleEnum.POLICY_NEWS_FOCUS_BUWEI.getKey());
		lastIsCompleted = false;
		
		newsFocusGuojia = Spider.create(policyNewsFocusGuojiaPageProcessor)
				.addPipeline(baseRedisPipeline)
				.setDownloader(seleniumDownloader)
				.addUrl(SpiderStartUrlConst.NEWS_FOCUS_GUOJIA_START_URL)
				.thread(spiderSettings.getThreadNumber());		
		redisTemplate.delete(SpiderModuleEnum.POLICY_NEWS_FOCUS_GUOJIA.getKey());
		lastIsCompleted = false;
		
		newsIndustryInfo = Spider.create(policyNewsIndustryInfoPageProcessor)
				.addPipeline(baseRedisPipeline)
				.setDownloader(seleniumDownloader)
				.addUrl(SpiderStartUrlConst.NEWS_INDUSTRY_INFO_START_URL)
				.thread(spiderSettings.getThreadNumber());		
		redisTemplate.delete(SpiderModuleEnum.POLICY_INDUSTRY_INFO.getKey());
		lastIsCompleted = false;
	}
	
	/**
	 * 定期执行某项定时任务
	 * 定时任务的执行频率由数据库Cron表的第一条记录决定
	 */
	public void crawlNewsDataAll() {
		if(lastIsCompleted) {
			logger.info("{}开始执行定时爬取任务，……",new Date());
			try {
				//初始化爬虫实例
				init();		
				
				newsFocusGuojia.run();
				logger.info("本次爬取要闻焦点（国家）任务已完成，共爬取记录数：{}",newsFocusGuojia.getPageCount());
				newsService.dataUpdate(SpiderModuleEnum.POLICY_NEWS_FOCUS_GUOJIA);
							
				newsFocusBuwei.run();
				logger.info("本次爬取要闻焦点（部委）任务已完成，共爬取记录数：{}",newsFocusBuwei.getPageCount());
				newsService.dataUpdate(SpiderModuleEnum.POLICY_NEWS_FOCUS_BUWEI);
				
				newsFocusTianjin.run();
				logger.info("本次爬取要闻焦点（天津）任务已完成，共爬取记录数：{}",newsFocusTianjin.getPageCount());
				newsService.dataUpdate(SpiderModuleEnum.POLICY_NEWS_FOCUS_TIANJIN);
				
				newsRegionalDynamic.run();
				logger.info("本次爬取区域资讯已完成，共爬取记录数：{}",newsRegionalDynamic.getPageCount());
				newsService.dataUpdate(SpiderModuleEnum.POLICY_REGIONAL_DYNAMIC);
				
				newsIndustryInfo.run();
				logger.info("本次爬取产业资讯任务已完成，共爬取记录数：{}",newsIndustryInfo.getPageCount());
				newsService.dataUpdate(SpiderModuleEnum.POLICY_INDUSTRY_INFO);
				
				lastIsCompleted = true;
			} catch (Exception e) {
				logger.error("执行定时爬取任务时发生异常……",e);
			} finally {
				newsFocusTianjin = null;
				newsFocusBuwei = null;
				newsFocusGuojia = null;
				newsRegionalDynamic = null;
				newsIndustryInfo = null;
				lastIsCompleted = true;
			}
		}else {
			logger.info("{}上次任务还未完成，本次任务略过……",new Date());
		}
	}
	
}
