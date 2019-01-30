package com.troila.tjsmesp.spider.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.troila.tjsmesp.spider.config.SpiderDriverConfig;
import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.model.primary.PolicySpider;
import com.troila.tjsmesp.spider.model.secondary.SmePolicy;
import com.troila.tjsmesp.spider.repository.informix.SmePolicyRespositoryInformix;
import com.troila.tjsmesp.spider.repository.mysql.PolicySpiderRepositoryMysql;
import com.troila.tjsmesp.spider.service.PolicyService;
import com.troila.tjsmesp.spider.util.TimeUtils;

@RestController
public class PolicyController {

	@Autowired
	private PolicySpiderRepositoryMysql policySpiderRepositoryMysql;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private PolicyService policyService;
	@Autowired
	private SpiderDriverConfig spiderDriverConfig;
	@Autowired
	private SmePolicyRespositoryInformix smePolicyRespositoryInformix;
	@GetMapping("/mysql/getList")
	public List<PolicySpider> getListStripContentLikeFromPrimaryDs(@RequestParam String queryStr){
//		return policySpiderRepositoryMysql.findByStripContentContains(queryStr);
		return policySpiderRepositoryMysql.findAll();
	}
	
	@GetMapping("/mysql/getOne")
	public PolicySpider getOnePolicyByIdFromMysql(@RequestParam String id) {		
		return policySpiderRepositoryMysql.findById(id);
	}
	@GetMapping("/redis/getList")
	public List<PolicySpider> getListFromRedis(){
		long size = redisTemplate.opsForList().size(SpiderModuleEnum.POLICY_NEWEST.getKey());
		List<Object> list = redisTemplate.opsForList().range(SpiderModuleEnum.POLICY_NEWEST.getKey(), 0L, size-1);
		List<PolicySpider> result = list.stream().map(e->{return (PolicySpider)e;}).collect(Collectors.toList());
		return result;
	}
	
	@GetMapping("/redis/save")
	public List<PolicySpider> saveListFromRedis(){
//		long size = redisTemplate.opsForList().size(SpiderModuleEnum.POLICY_NEWEST.getKey());
//		List<Object> list = redisTemplate.opsForList().range(SpiderModuleEnum.POLICY_NEWEST.getKey(), 0L, size-1);
//		List<PolicySpider> result = list.stream().map(e->{return (PolicySpider)e;}).collect(Collectors.toList());
//		policyService.dataSync(SpiderModuleEnum.POLICY_NEWEST);
		policyService.dataUpdate(SpiderModuleEnum.POLICY_READING); 
		return new ArrayList<PolicySpider>();
	}
	@GetMapping("/mysql/lastweekdata")
	public List<PolicySpider> getLastWeekData(){
		return policySpiderRepositoryMysql.findByPublishDateGreaterThanEqualAndSpiderModule(TimeUtils.getLastWeek(), 0);
	}
	@GetMapping("/getSpiderDriverConf")
	public String getSpiderDriverConf() {
		return spiderDriverConfig.toString();
	}
	
	@GetMapping("/informix/getOne")
	public SmePolicy getOneInformixData(@RequestParam int id){
		return smePolicyRespositoryInformix.findById(id).get();
	}
}
