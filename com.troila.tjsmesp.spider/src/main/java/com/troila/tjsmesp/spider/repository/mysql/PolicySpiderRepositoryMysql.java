package com.troila.tjsmesp.spider.repository.mysql;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.troila.tjsmesp.spider.model.primary.PolicySpider;

public interface PolicySpiderRepositoryMysql extends JpaRepository<PolicySpider, Integer>{
	
	public PolicySpider findById(String id);
		
	public List<PolicySpider> findBySpiderModule(int spiderModule);
	
	public List<PolicySpider> findByStripContentContains(String str);
	
	public PolicySpider findByPublishUrl(String publishUrl);
	
	public List<PolicySpider> findByArticleReadingContaining(String articleReading);
	
	public List<PolicySpider> findByPublishUnitContaining(String publishUntLike);
	
	public List<PolicySpider> findByPublishDateGreaterThanEqualAndSpiderModule(Date lastNDay, int spiderModule);
	
	/**
	 *  主要用于查找 没有对应原文的解读文章，进行可能的矫正
	 * @param lastNDay
	 * @param spiderModule
	 * @return
	 */
	public List<PolicySpider> findByParentIdIsNullAndPublishDateGreaterThanEqualAndSpiderModule(Date lastNDay, int spiderModule);
	
	
	public List<PolicySpider> findByTitleContainsAndSpiderModule(String str, int spiderModule);
	
	@Query(value="select publish_url from sme_policy_spider where spider_module = :spiderModule",nativeQuery=true)
	public List<String> findCrawledUrlsBySpiderModule(int spiderModule);
}
