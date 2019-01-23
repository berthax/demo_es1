package com.troila.tjsmesp.spider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.model.primary.PolicySpider;
import com.troila.tjsmesp.spider.model.secondary.SmePolicy;
import com.troila.tjsmesp.spider.repository.informix.SmePolicyRespositoryInformix;
import com.troila.tjsmesp.spider.repository.mysql.PolicySpiderRepositoryMysql;
import com.troila.tjsmesp.spider.service.PolicyService;

@RestController
public class PolicyDataSyncController {
	
	@Autowired
	private SmePolicyRespositoryInformix  smePolicyRespositoryInformix;
	@Autowired
	private PolicySpiderRepositoryMysql policySpiderRepositoryMysql;
	
	@Autowired
	private PolicyService policyService;
	
	@GetMapping("/policy/sync")
	public List<SmePolicy> dataSync(){
//		return policyService.dataSync(SpiderModuleEnum.POLICY_NEWEST);
		return policyService.dataSync_test(SpiderModuleEnum.POLICY_NEWEST);
	}
	
	
	
}
