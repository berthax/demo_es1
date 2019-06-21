package com.troila.tjsmesp.spider.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import com.troila.tjsmesp.spider.repository.mysql.CronRepository;
import com.troila.tjsmesp.spider.scheduled.CrawlScheduleService;
import com.troila.tjsmesp.spider.scheduled.DataSyncService;
import com.troila.tjsmesp.spider.scheduled.NewsCrawlScheduleService;
import com.troila.tjsmesp.spider.scheduled.NewsDataSyncService;



/**
 * 定时器配置类，此种情况，可以在不停止服务器的情况下，修改数据库中的定时任务时间，暂时不用了
 * @author xuanguojing
 *
 */
//@Configuration
//@EnableScheduling
public class ScheduleConfigRevisable implements SchedulingConfigurer{

	@Autowired
	private CronRepository cronRepository;
	@Autowired
	private CrawlScheduleService crawlScheduleService;
	@Autowired
	private DataSyncService dataSyncService;
	@Autowired
	private NewsCrawlScheduleService newsCrawlScheduleService;
	@Autowired
	private NewsDataSyncService newsDataSyncService;
				
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		// 注册政策爬取定时任务
		taskRegistrar.addTriggerTask(crawlScheduleService,getTrigger(1));  
		// 注册政策数据同步定时任务
		taskRegistrar.addTriggerTask(dataSyncService,getTrigger(2));
		
		// 注册新闻信息爬取定时任务
		taskRegistrar.addTriggerTask(newsCrawlScheduleService,getTrigger(7));  
		// 注册新闻信息数据同步定时任务
		taskRegistrar.addTriggerTask(newsDataSyncService,getTrigger(8));
	}
	
	/**
     * 业务触发器
     * @return
     */
    private Trigger getTrigger(int id) {
        return new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
            	String cron = cronRepository.findById(id).get().getCron();
            	if("".equals(cron)||cron==null) {
            		return null;    		
            	}
                // 触发器
                CronTrigger trigger = new CronTrigger(cron);
                return trigger.nextExecutionTime(triggerContext);
            }
        };
    }
}
