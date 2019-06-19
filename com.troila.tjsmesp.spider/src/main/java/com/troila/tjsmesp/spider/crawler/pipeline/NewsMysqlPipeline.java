package com.troila.tjsmesp.spider.crawler.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.constant.CrawlConst;
import com.troila.tjsmesp.spider.model.primary.NewsSpider;
import com.troila.tjsmesp.spider.repository.mysql.NewsSpiderRepositoryMysql;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
/**
 * 
 * @ClassName:  NewsMySqlPipeline   
 * @Description:有关新闻类的处理
 * @author: xgj
 * @date:   2019年6月17日 上午11:27:28   
 *
 */
@Component
public class NewsMysqlPipeline implements Pipeline{
	
	private static final Logger logger = LoggerFactory.getLogger(NewsMysqlPipeline.class);
	@Autowired
	private NewsSpiderRepositoryMysql newsSpiderRepositoryMysql;
	
	@Override
	public void process(ResultItems resultItems, Task task) {
 		 try {
 			 NewsSpider newsSpider = (NewsSpider)resultItems.get(CrawlConst.CRAWL_ITEM_KEY);
 			 if(newsSpider == null) {
 				 //如果是列表页，没有此项内容
 				 return;
 			 }
 			newsSpiderRepositoryMysql.save(newsSpider);
		} catch (Exception e) {
			logger.error("持久化到mysql数据库发生异常，当前参数为resultItems:{},NewsSpider:{},异常信息如下：",e);
		}
	}
}
