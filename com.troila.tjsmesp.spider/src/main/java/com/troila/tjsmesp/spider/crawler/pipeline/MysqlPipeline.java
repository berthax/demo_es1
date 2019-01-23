package com.troila.tjsmesp.spider.crawler.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.model.primary.PolicySpider;
import com.troila.tjsmesp.spider.repository.mysql.PolicySpiderRepositoryMysql;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 持久化到mysql数据库
 * @author xuanguojing
 *
 */
@Component
public class MysqlPipeline implements Pipeline{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PolicySpiderRepositoryMysql PolicySpiderRepositoryMysql;
	
	@Override
	public void process(ResultItems resultItems, Task task) {
 		 try {
 			 PolicySpider policySpider = (PolicySpider)resultItems.get("policy");
 			 if(policySpider == null) {
 				 //如果是列表页，没有此项内容
 				 return;
 			 }
 			 PolicySpiderRepositoryMysql.save(policySpider);
		} catch (Exception e) {
			logger.error("持久化到mysql数据库发生异常，异常信息如下：",e);
		}
	}

}
