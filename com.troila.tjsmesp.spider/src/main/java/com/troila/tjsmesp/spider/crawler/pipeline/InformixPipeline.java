package com.troila.tjsmesp.spider.crawler.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.model.PolicySpider;
import com.troila.tjsmesp.spider.repository.informix.PolicySpiderRepositoryInformix;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
/**
 * 持久化到Informix数据库
 * @author xuanguojing
 *
 */
@Component
public class InformixPipeline implements Pipeline{

	@Autowired
	private PolicySpiderRepositoryInformix policySpiderRepositoryInformix;
	
	@Override
	public void process(ResultItems resultItems, Task task) {
 		 
		 PolicySpider policySpider = (PolicySpider)resultItems.get("policy");
		 if(policySpider == null) {
			 //如果是列表页，没有此项内容
			 return;
		 }

		 //筛选出内容中包含企业的政策来
//		 if(ContentFilter.filterWords(policySpider.getContent(),"企业")) {
//			 return;
//		 }
		 //对原有记录进行更新，对新纪录进行保存
		 policySpiderRepositoryInformix.save(policySpider);
	}

}
