package com.troila.tjsmesp.spider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.troila.tjsmesp.spider.config.SpiderDriverConfig;
import com.troila.tjsmesp.spider.model.primary.PolicyReading;
import com.troila.tjsmesp.spider.model.primary.PolicySpider;
//import com.troila.tjsmesp.spider.repository.PolicyReadingRepository;
import com.troila.tjsmesp.spider.service.PolicyService;

@RestController
public class PolicyReadingController {

//	@Autowired
//	private PolicyReadingRepository policyReadingRepository;
	@Autowired
	private SpiderDriverConfig spiderDriverConfig;
	@Autowired
	private PolicyService policyService;
	
	@GetMapping("/save")
	public boolean savePolicy() {
		PolicyReading spider = new PolicyReading();
		spider.setTitle("测试titel1");
		spider.setContent("测试内容1");
//		policyReadingRepository.save(spider);
		return true;
	}
	@GetMapping("/getConf")
	public String getConfig() {
		return spiderDriverConfig.getWindowsDriver();
	}
	
	@GetMapping("/getParent")
	public PolicySpider getParentPolicy() {
		String articleReading = "http://zcydt.fzgg.tj.gov.cn/zcbjd/sjbmjd/srlsbj_209/201705/t20170505_20709.shtml";
		return policyService.getParentIdForReadingActicle2(articleReading);
	}
}
