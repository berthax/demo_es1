package com.troila.tjsmesp.spider.crawler.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.constant.CrawlConst;
import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.model.primary.NewsSpider;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class BaseRedisPipeline implements Pipeline{
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
		
	@Override
	public void process(ResultItems resultItems, Task task) {
		
		 NewsSpider baseSpider = (NewsSpider)resultItems.get(CrawlConst.CRAWL_ITEM_KEY);
		 if(baseSpider == null) {
			 //如果是列表页，没有此项内容
			 return;
		 }
		 //根据不同的爬取类型，存储到对应的列表中去
		 String key = SpiderModuleEnum.getKey(baseSpider.getSpiderModule());
		 redisTemplate.opsForList().rightPush(key, baseSpider);		
	}
}
