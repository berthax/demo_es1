package com.troila.tjsmesp.spider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PolicyWebController {
	
	@GetMapping("/source")
	public String getTemplate() {
		return "policy-source2";
	}
	
	@GetMapping("/test")
	public String getTestTemplate() {
		return "test";
	}
	
	@GetMapping("/list")
	public String getListTemplate() {
		return "policy-list2";
	}
	@GetMapping("/detail")
	public String getDetailTemplate() {
		return "policy-detail2";
	}
}
