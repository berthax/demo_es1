package com.troila.tjsmesp.spider.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.model.PolicySpider;
import com.troila.tjsmesp.spider.repository.informix.PolicySpiderRepositoryInformix;
import com.troila.tjsmesp.spider.repository.mysql.PolicySpiderRepositoryMysql;

@RestController
public class PolicyController {

	@Autowired
	private PolicySpiderRepositoryInformix policySpiderRepositoryInformix;
	@Autowired
	private PolicySpiderRepositoryMysql policySpiderRepositoryMysql;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@GetMapping("/informix/getOne")
	public PolicySpider getOnePolicyById(@RequestParam String id) {		
		return policySpiderRepositoryInformix.findById(id).get();
	}
	@GetMapping("/informix/getList")
	public List<PolicySpider> getListStripContentLike(@RequestParam String queryStr){
		return policySpiderRepositoryInformix.findByStripContentContains(queryStr);
	}

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
	
	
}
