package com.troila.tjsmesp.spider.scheduled;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.troila.tjsmesp.spider.config.DataSyncSettings;
import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.service.PolicyService;

@Service
public class DataSyncService implements Runnable{

	
	private final static Logger logger = LoggerFactory.getLogger(DataSyncService.class);
	
	@Autowired
	private PolicyService policyService;	
	@Autowired
	private DataSyncSettings dataSyncSettings;
	
	@Override
	public void run() {
		long start = System.currentTimeMillis();
		logger.info("数据同步任务现在开始，请稍候……");
		syncPolicyDataLastNDay();
		logger.info("本次数据同步任务结束,用时{}ms",(System.currentTimeMillis()-start));
	}
	
	/**
	 * 
	 * @Description 政策数据同步任务，定于每周五进行同步
	 */
	@Scheduled(cron="0 20 6-20/1 ? 1-12 5 ")
	public void syncPolicyDataLastNDay() {
		try {
			long start = System.currentTimeMillis();
			logger.info("{}数据同步任务现在开始，请稍候……，……",new Date());  //数据查重问题
			//同步政策原文
			policyService.syncLatestPolicyData(SpiderModuleEnum.POLICY_NEWEST,dataSyncSettings.getLastDays());
			Thread.sleep(2000);
			//同步政策解读
			policyService.syncLatestPolicyData(SpiderModuleEnum.POLICY_READING,dataSyncSettings.getLastDays());
//			logger.info("{}数据同步任务结束，……",new Date());    //数据查重问题
			logger.info("本次数据同步任务结束,用时{}ms",(System.currentTimeMillis()-start));
		} catch (Exception e) {
			logger.error("数据同步任务出现异常，异常信息如下：",e);
		}		
	}
}
