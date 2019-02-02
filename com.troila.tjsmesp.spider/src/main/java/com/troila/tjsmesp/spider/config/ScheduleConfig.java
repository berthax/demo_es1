package com.troila.tjsmesp.spider.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import com.troila.tjsmesp.spider.repository.mysql.CronRepository;
import com.troila.tjsmesp.spider.service.CrawlScheduleService;
import com.troila.tjsmesp.spider.service.DataSyncService;

/**
 * 定时器配置类
 * @author xuanguojing
 *
 */
@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer{

	@Autowired
	private CronRepository cronRepository;
	@Autowired
	private CrawlScheduleService crawlScheduleService;
	@Autowired
	private DataSyncService dataSyncService;
	
	
	
//	@Override
//	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//		taskRegistrar.setScheduler(taskExecutor());
//	}
//
//	@Bean
//    public Executor taskExecutor() {
//        return Executors.newScheduledThreadPool(10);
//    }
	
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		//注册爬取定时任务
		taskRegistrar.addTriggerTask(crawlScheduleService,getTrigger(1));  
		//注册数据同步定时任务
		taskRegistrar.addTriggerTask(dataSyncService,getTrigger(2));
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
