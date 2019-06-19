package com.troila.tjsmesp.spider.crawler.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.model.primary.NewsSpider;
import com.troila.tjsmesp.spider.repository.mysql.NewsSpiderRepositoryMysql;

@Component
public class NewsProcessorService {
	
	private static final Logger logger = LoggerFactory.getLogger(NewsProcessorService.class);
	@Autowired
	private NewsSpiderRepositoryMysql newsSpiderRepositoryMysql;
	 
 
	/**
	 * 获取之前已经下载过的文章链接，不再重复下载(以数据库中当前的存储为准)
	 * @return
	 */
	public List<String> getCrawledUrls(int spiderMoudleIndex){
		try {			
			List<NewsSpider> list = newsSpiderRepositoryMysql.findBySpiderModule(spiderMoudleIndex);
			List<String> listUrls = list.stream().map(e->{return ((NewsSpider)e).getPublishUrl();}).collect(Collectors.toList());
			return listUrls;
		} catch (Exception e) {
			logger.error("从本地数据库中获取已经下载过的所有链接出错，出错信息：",e);
			return null;
		}
	}
}
