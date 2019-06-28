package com.troila.tjsmesp.spider.scheduled;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.troila.tjsmesp.spider.config.SpiderSettings;
import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.constant.SpiderStartUrlConst;
import com.troila.tjsmesp.spider.crawler.pipeline.BaseRedisPipeline;
import com.troila.tjsmesp.spider.crawler.processor.PolicyNewsFocusBuweiPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.PolicyNewsFocusGuojiaPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.PolicyNewsFocusTianjinPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.PolicyNewsIndustryInfoPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.PolicyNewsRegionalDynamicPageProcessor;
import com.troila.tjsmesp.spider.service.NewsService;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

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
	
//	@Autowired
//	private SeleniumDownloader seleniumDownloader;
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
			
	private Spider newsFocusGuojia = null;
	
	private Spider newsFocusBuwei = null;
	
	private Spider newsFocusTianjin = null;
	
	private Spider newsIndustryInfo = null;
	
	private Spider newsRegionalDynamic = null;
	
	//标记上次的定时任务是否已经完成
	//private boolean lastIsCompleted = true;
	
	/**
	 * 定时爬取新闻类的各种信息的定时任务时间表达式
	 */
	private static final String CRAWL_NEWS_CRON_STR = "0 0/30 * * * ? ";
	/**
	 * 定时同步新闻类的各种信息的定时任务时间表达式
	 */
	private static final String UPDATE_NEWS_CRON_STR = "0 0/10 * * * ? ";
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		logger.info("数据爬取任务现在开始，请稍候……");
		//crawlNewsDataAll();
		logger.info("本次数据爬取任务结束,用时{}ms",(System.currentTimeMillis()-start));
	}
					
	
	/**
	 * 定期执行某项定时任务
	 * 定时任务的执行频率由数据库Cron表的第一条记录决定
	 */
/*	@Scheduled(cron="0 0/30 * * * ? ")
	public void crawlNewsDataAll() {
		if(lastIsCompleted) {
			logger.info("{}开始执行定时爬取任务，……",new Date());
			try {
				//初始化爬虫实例
				init();		
				long start = System.currentTimeMillis();
				newsFocusGuojia.run();
				logger.info("本次爬取要闻焦点（国家）任务已完成，共爬取记录数：{},耗时{}ms",newsFocusGuojia.getPageCount(),(System.currentTimeMillis()-start));
				
				start = System.currentTimeMillis();			
				newsFocusBuwei.run();
				logger.info("本次爬取要闻焦点（部委）任务已完成，共爬取记录数：{},耗时{}ms",newsFocusBuwei.getPageCount(),(System.currentTimeMillis()-start));
				
				start = System.currentTimeMillis();	
				newsFocusTianjin.run();
				logger.info("本次爬取要闻焦点（天津）任务已完成，共爬取记录数：{},耗时{}ms",newsFocusTianjin.getPageCount(),(System.currentTimeMillis()-start));
				
				start = System.currentTimeMillis();					
				newsIndustryInfo.run();
				logger.info("本次爬取产业资讯任务已完成，共爬取记录数：{},耗时{}ms",newsIndustryInfo.getPageCount(),(System.currentTimeMillis()-start));
				
				
				start = System.currentTimeMillis();
				newsRegionalDynamic.run();
				logger.info("本次爬取区域资讯已完成，共爬取记录数：{},耗时{}ms",newsRegionalDynamic.getPageCount(),(System.currentTimeMillis()-start));
								
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
	}*/
	
	
	public Spider createSpider(PageProcessor pageProcessor,Pipeline pipeline,String startUrl,int threadNumber,SpiderModuleEnum spiderModuleEnum) {
		Spider spider = Spider.create(pageProcessor)
				.addPipeline(pipeline)
				.addUrl(startUrl)
				.thread(threadNumber);		
		redisTemplate.delete(spiderModuleEnum.getKey());
		return spider;
	}
	
	
	@Scheduled(cron = CRAWL_NEWS_CRON_STR)
	public void crawlNewsFocusGuojia() {
		try {
			newsFocusGuojia = createSpider(policyNewsFocusGuojiaPageProcessor,baseRedisPipeline,
					SpiderStartUrlConst.NEWS_FOCUS_GUOJIA_START_URL,spiderSettings.getThreadNumber(),SpiderModuleEnum.POLICY_NEWS_FOCUS_GUOJIA);			
			long start = System.currentTimeMillis();					
			newsFocusGuojia.run();
			logger.info("本次爬取要闻焦点（国家）任务已完成，共爬取记录数：{},耗时{}ms",newsFocusGuojia.getPageCount(),(System.currentTimeMillis()-start));
			
		} catch (Exception e) {
			logger.error("执行定时爬取要问焦点（国家）任务时发生异常，异常信息如下:{}",e);
		} finally {
			newsFocusGuojia = null;
		}
	}
	
	@Scheduled(cron = CRAWL_NEWS_CRON_STR)
	public void crawlNewsFocusBuwei() {
		try {
			newsFocusBuwei = createSpider(policyNewsFocusBuweiPageProcessor,baseRedisPipeline,
					SpiderStartUrlConst.NEWS_FOCUS_BUWEI_START_URL,spiderSettings.getThreadNumber(),SpiderModuleEnum.POLICY_NEWS_FOCUS_BUWEI);			
			
			long start = System.currentTimeMillis();					
			newsFocusBuwei.run();
			logger.info("本次爬取要闻焦点（部委）任务已完成，共爬取记录数：{},耗时{}ms",newsFocusBuwei.getPageCount(),(System.currentTimeMillis()-start));
			
		} catch (Exception e) {
			logger.error("执行定时爬取要闻焦点（部委）任务时发生异常，异常信息如下:{}",e);
		} finally {
			newsFocusBuwei = null;
		}
	}
	
	@Scheduled(cron = CRAWL_NEWS_CRON_STR)
	public void crawlNewsFocusTianjin() {
		try {
			newsFocusTianjin = createSpider(policyNewsFocusTianjinPageProcessor,baseRedisPipeline,
					SpiderStartUrlConst.NEWS_FOCUS_TIANJIN_START_URL,spiderSettings.getThreadNumber(),SpiderModuleEnum.POLICY_NEWS_FOCUS_TIANJIN);
			
			long start = System.currentTimeMillis();					
			newsFocusTianjin.run();
			logger.info("本次爬取要闻焦点（天津）任务已完成，共爬取记录数：{},耗时{}ms",newsFocusTianjin.getPageCount(),(System.currentTimeMillis()-start));		
		} catch (Exception e) {
			logger.error("执行定时爬取要闻焦点（天津）任务时发生异常，异常信息如下:{}",e);
		} finally {
			newsFocusTianjin = null;
		}
	}
	
	
	/**
	 * 
	 * @Description 定时爬取产业资讯
	 */
	@Scheduled(cron = CRAWL_NEWS_CRON_STR)
	public void crawlNewsIndustryInfo() {
		try {			
			newsIndustryInfo = createSpider(policyNewsIndustryInfoPageProcessor,baseRedisPipeline,
					SpiderStartUrlConst.NEWS_INDUSTRY_INFO_START_URL,spiderSettings.getThreadNumber(),SpiderModuleEnum.POLICY_INDUSTRY_INFO);
			
			long start = System.currentTimeMillis();					
			newsIndustryInfo.run();
			logger.info("本次爬取产业资讯任务已完成，共爬取记录数：{},耗时{}ms",newsIndustryInfo.getPageCount(),(System.currentTimeMillis()-start));			
		} catch (Exception e) {
			logger.error("执行定时爬取产业资讯任务时发生异常，异常信息如下:{}",e);
		} finally {
			newsIndustryInfo = null;
		}
	}
	
	/**
	 * 
	 * @Description 定时爬取区域资讯
	 */
	@Scheduled(cron = CRAWL_NEWS_CRON_STR)
	public void crawlNewsRegionalDynamic() {
		try {
			newsRegionalDynamic = createSpider(policyNewsRegionalDynamicPageProcessor,baseRedisPipeline,
					SpiderStartUrlConst.NEWS_REGION_DYNAMIC_START_URL,spiderSettings.getThreadNumber()*2,SpiderModuleEnum.POLICY_REGIONAL_DYNAMIC);
			
			long start = System.currentTimeMillis();
			newsRegionalDynamic.run();
			logger.info("本次爬取区域资讯已完成，共爬取记录数：{},耗时{}ms",newsRegionalDynamic.getPageCount(),(System.currentTimeMillis()-start));			
		} catch (Exception e) {
			logger.error("执行定时爬取区域资讯任务时发生异常，异常信息如下:{}",e);
		} finally {
			newsRegionalDynamic = null;
		}
	}
	
	
	@Scheduled(cron = UPDATE_NEWS_CRON_STR)
	public void updateNewsDataAll() {
		logger.info("{}开始执行数据更新任务，从redis更新到数据库中，……", new Date());
		try {
			newsService.dataUpdate(SpiderModuleEnum.POLICY_NEWS_FOCUS_GUOJIA);
			newsService.dataUpdate(SpiderModuleEnum.POLICY_NEWS_FOCUS_BUWEI);
			newsService.dataUpdate(SpiderModuleEnum.POLICY_NEWS_FOCUS_TIANJIN);
			newsService.dataUpdate(SpiderModuleEnum.POLICY_INDUSTRY_INFO);
			newsService.dataUpdate(SpiderModuleEnum.POLICY_REGIONAL_DYNAMIC);		
		} catch (Exception e) {
			logger.error("执行数据更新任务时发生异常……", e);
		}
	}
}
