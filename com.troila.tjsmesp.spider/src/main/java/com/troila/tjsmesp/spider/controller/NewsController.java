package com.troila.tjsmesp.spider.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.model.secondary.BmsPlatformPublishInfo;
import com.troila.tjsmesp.spider.service.NewsService;
import com.troila.tjsmesp.spider.util.TimeUtils;

@RestController
public class NewsController {
	
	@Autowired
	private NewsService newsService; 
	
	@GetMapping("/news/getLastNDay")
	public Date  getLastNDay(@RequestParam int lastNDays) {
		Date lastDay = TimeUtils.getLastNDay(lastNDays);
		System.out.println(lastDay);
		return lastDay;
	}
	
	@GetMapping("/news/dataSync")
	public List<BmsPlatformPublishInfo> newsDataSync(@RequestParam int lastNDays){
		return newsService.newsDataSync(SpiderModuleEnum.POLICY_NEWS_FOCUS_TIANJIN, lastNDays);
	}

}
