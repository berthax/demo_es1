package com.troila.tjsmesp.spider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.troila.tjsmesp.spider.crawler.pipeline.MysqlPipeline;
import com.troila.tjsmesp.spider.crawler.processor.PolicyReadingPageProcessor;

import us.codecraft.webmagic.Spider;

@SpringBootApplication
public class SpiderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpiderApplication.class, args);
	}
	
}
