package com.troila.tjsmesp.spider.crawler.pipeline;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.model.PolicyReading;
import com.troila.tjsmesp.spider.model.PolicySpider;
import com.troila.tjsmesp.spider.repository.informix.PolicySpiderRepositoryInformix;
//import com.troila.tjsmesp.spider.repository.PolicyReadingRepository;
import com.troila.tjsmesp.spider.util.TimeUtils;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
/**
 * 注解形式调用的pipeline
 * @author xuanguojing
 *
 */
@Component
public class MySqlPageModelPipeline implements PageModelPipeline<PolicySpider>{

//	@Autowired
//	private PolicyReadingRepository policyReadingRepository;
	
	@Autowired
	private PolicySpiderRepositoryInformix policySpiderRepositoryInformix;
	
	@Override
	public void process(PolicySpider policySpider, Task task) {
		System.out.println(policySpider.toString());
		//下边处理来源和政策发布时间字段
		String str[] = policySpider.getTempStr().split("：");
		String dateStr = str[2];
		Date date = TimeUtils.getShortFormatDate(dateStr);
		policySpider.setPublishDate(date);
		
		String str1 = str[1];
		String source = str1.substring(0, str1.length()-4).trim();   //分隔结果中第二部分呢，去除掉日期：这三个字符
		policySpider.setSource(source);

		policySpiderRepositoryInformix.save(policySpider);
//		policyReadingRepository.save(policyReading);
	}
	
	public static void main(String[] args) {
		String tempStr =  " 来源：市建委          日期：2018-10-18";
		
		String str[] = tempStr.split("：");
		String dateStr = str[2];
//		Date date = TimeUtils.getFormatDate(TimeUtils.shortDateFormat, dateStr);
		Date date = TimeUtils.getShortFormatDate(dateStr);
		System.out.println(dateStr.toString());
		String str1 = str[1];
/*		int length = "日期：".length();
		System.out.println(length);*/
		
		String source = str[1].substring(0, str[1].length()-4).trim();
		System.out.println(source);
	}

}
