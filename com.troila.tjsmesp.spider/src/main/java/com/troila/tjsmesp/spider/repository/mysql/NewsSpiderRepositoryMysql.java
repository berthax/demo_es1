package com.troila.tjsmesp.spider.repository.mysql;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.troila.tjsmesp.spider.model.primary.NewsSpider;

public interface NewsSpiderRepositoryMysql extends JpaRepository<NewsSpider, Integer>{
	
	public NewsSpider findByNewsCode(String newsCode);
	
	public List<NewsSpider> findBySpiderModule(int spiderModule);
		
	public NewsSpider findByPublishUrl(String publishUrl);
		
	public List<NewsSpider> findByPublishDateGreaterThanEqualAndSpiderModule(Date lastNDay, int spiderModule);
	
	
	public List<NewsSpider> findByTitleContainsAndSpiderModule(String str, int spiderModule);
}
