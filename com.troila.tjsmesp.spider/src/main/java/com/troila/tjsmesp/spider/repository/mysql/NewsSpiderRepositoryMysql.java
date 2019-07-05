package com.troila.tjsmesp.spider.repository.mysql;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.troila.tjsmesp.spider.model.primary.NewsSpider;

public interface NewsSpiderRepositoryMysql extends JpaRepository<NewsSpider, Integer>{
	
	public NewsSpider findBySpiderCode(String spiderCode);
	
	public List<NewsSpider> findBySpiderModule(int spiderModule);
		
	public NewsSpider findByPublishUrl(String publishUrl);
		
	public List<NewsSpider> findByPublishDateGreaterThanEqualAndSpiderModule(Date lastNDay, int spiderModule);
		
	public List<NewsSpider> findByTitleContainsAndSpiderModule(String str, int spiderModule);
	
	@Query(value="select publish_url from sme_news_spider where spider_module = :spiderModule",nativeQuery = true)
	public List<String> findCrawledUrlsBySpiderModule(int spiderModule);
	
	@Query(value="select * from sme_news_spider "
			+ "where spider_module = :spiderModule and publish_date >= :lastNDay "
			+ "order by publish_date desc "
			+ "limit :number",nativeQuery =  true)
	public List<NewsSpider> findByPublishDateGreaterThanEqualAndSpiderModuleOrderByPublishDateDesc(Date lastNDay, int spiderModule, int number);
}
