package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Policy;
import com.example.demo.repository.PolicyRepository;

@RestController
@RequestMapping("/es")
public class PolicyESController {
	
	@Autowired
	private PolicyRepository policyRepository;
	
	@RequestMapping("/add")
	public String add(){		
		Policy policy = new Policy();		
		policy.setId("3");
		policy.setTitle("产业政策3_百度百科");
		policy.setContent("产业政策是国家制定的，引导国家产业发展方向、引导推动产业结构升级、协调国家产业结构、使国民经济健康可持续发展的政策。产业政策主要通过制定国民经济计划（包括指令性计划和指导性计划）、产业结构调整计划、产业扶持计划、财政投融资、货币手段、项目审批来实现。");
		policy.setSource("百度百科");	
		policy.setUrl("http://www.baidu.com/link?url=mVo9HR-oFUCFnhhkXWMqUGRnaAbUiDKDOqn6ogOMhiVYbxO9Hdh0kKWURUiLXxVp");
		policyRepository.save(policy);
		System.err.println("add a obj");		
		return "success";
	}
	
	@RequestMapping("/query")
	public String getPolicyRecord(){
		Optional<Policy> policy = policyRepository.findById("3");
		System.out.println(policy.toString());
		return policy.toString();
	}
}
