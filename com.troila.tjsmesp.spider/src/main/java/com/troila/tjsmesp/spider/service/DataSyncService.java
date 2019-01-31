package com.troila.tjsmesp.spider.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;

@Service
public class DataSyncService implements Runnable{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PolicyService policyService;	
	
	@Override
	public void run() {
		logger.info("改版后执行数据同步任务……");
		syncPolicyDataLastNDay();
	}
	
	public void syncPolicyDataLastNDay() {
		try {
			logger.info("{}开始执行数据同步任务，……",new Date());  //数据查重问题
			//同步政策原文
			policyService.syncLatestPolicyData(SpiderModuleEnum.POLICY_NEWEST,10);
			//同步政策解读
			policyService.syncLatestPolicyData(SpiderModuleEnum.POLICY_READING,10);
			logger.info("{}数据同步任务结束，……",new Date());    //数据查重问题
		} catch (Exception e) {
			logger.error("数据同步任务出现异常，异常信息如下：",e);
		}		
	}
}
