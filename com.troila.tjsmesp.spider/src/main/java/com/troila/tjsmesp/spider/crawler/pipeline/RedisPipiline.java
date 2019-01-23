package com.troila.tjsmesp.spider.crawler.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.model.PolicySpider;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class RedisPipiline implements Pipeline{

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	private volatile int count = 0;
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		
		 PolicySpider policySpider = (PolicySpider)resultItems.get("policy");
		 if(policySpider == null) {
			 //如果是列表页，没有此项内容
			 return;
		 }
		 //根据不同的爬取类型，存储到对应的列表中去
		 String key = SpiderModuleEnum.getKey(policySpider.getSpiderModule());
		 redisTemplate.opsForList().rightPush(key, policySpider);		
	}
}
