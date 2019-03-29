package com.troila.tjsmesp.spider.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.model.primary.PolicySpider;
import com.troila.tjsmesp.spider.model.secondary.SmePolicy;
import com.troila.tjsmesp.spider.repository.mysql.PolicySpiderRepositoryMysql;
import com.troila.tjsmesp.spider.service.PolicyService;

@RestController
public class PolicyDataSyncController {
	private static final Logger logger = LoggerFactory.getLogger(PolicyDataSyncController.class);
	
	@Autowired
	private PolicyService policyService;
	
	@Autowired
	private PolicySpiderRepositoryMysql policySpiderRepositoryMysql;
		
	@GetMapping("/policy/sync")
	public List<SmePolicy> dataLastTenDaySync(@RequestParam(value="lastDays",defaultValue="7",required=false) int lastDays){
		List<SmePolicy> list = new ArrayList<>();
		try {
			logger.info("{}开始执行数据同步任务，……",new Date());
			//同步政策原文
			list = policyService.syncLatestPolicyData(SpiderModuleEnum.POLICY_NEWEST,lastDays);
//			Thread.sleep(10);
			//同步政策解读
			List<SmePolicy> list2 = policyService.syncLatestPolicyData(SpiderModuleEnum.POLICY_READING,lastDays);
			list.addAll(list2);
			logger.info("{}数据同步任务结束，……",new Date());  
		} catch (Exception e) {
			logger.error("数据同步任务出现异常，异常信息如下：",e);
		}			
		return list;
	}
	
	@GetMapping("/policy/parent")
	public SmePolicy  getParentPolicy() {
		PolicySpider policySpider = policySpiderRepositoryMysql.findByPublishUrl("http://zcydt.fzgg.tj.gov.cn/zcbjd/gjzcjd/201903/t20190318_54710.shtml");
		SmePolicy smePolicy = policyService.convertTo(policySpider);
		return smePolicy;
	}
}
