package com.troila.tjsmesp.spider.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.troila.tjsmesp.spider.config.DataSyncSettings;
import com.troila.tjsmesp.spider.constant.SmeNewsTypeConst;
import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.model.primary.NewsSpider;
import com.troila.tjsmesp.spider.model.secondary.BmsPlatformPublishInfo;
import com.troila.tjsmesp.spider.repository.informix.BmsPlatformPublishInfoRepositoryInformix;
import com.troila.tjsmesp.spider.repository.mysql.NewsSpiderRepositoryMysql;
import com.troila.tjsmesp.spider.util.TimeUtils;

@Service
public class NewsService {
	
	private static final Logger logger = LoggerFactory.getLogger(NewsService.class);
	
	@Autowired
	private DataSyncSettings dataSyncSettings;
		
	@Autowired
	private BmsPlatformPublishInfoRepositoryInformix bmsPlatformPublishInfoRepositoryInformix;
	
	@Autowired
	private NewsSpiderRepositoryMysql newsSpiderRepositoryMysql;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	/**
	 * mysql数据库的数据实体类与informix数据库实体类之间的转换
	 * @param policySpider
	 * @return
	 */
	private BmsPlatformPublishInfo convertTo(NewsSpider newsSpider) {
		if(newsSpider == null) {
			logger.error("类型转换参数错误：参数newsSpider为null");
			return null;			
		}
		
		BmsPlatformPublishInfo bmsPlatformPublishInfo = new BmsPlatformPublishInfo();
		bmsPlatformPublishInfo.setPublishTitle(newsSpider.getTitle());
		bmsPlatformPublishInfo.setPublishContent(newsSpider.getContent());
		bmsPlatformPublishInfo.setPublishTime(newsSpider.getPublishDate());
		
		// 枚举类中要闻焦点国家、部委、天津是5 6 7，informix数据库中是1 2 3，偷懒先这么写了
		if(newsSpider.getSpiderModule() ==  SpiderModuleEnum.POLICY_NEWS_FOCUS_GUOJIA.getIndex()) {
			bmsPlatformPublishInfo.setFocusType(String.valueOf(SmeNewsTypeConst.NEWS_FOCUS_GUOJIA));
			bmsPlatformPublishInfo.setPublishType("1");
		}else if(newsSpider.getSpiderModule() == SpiderModuleEnum.POLICY_NEWS_FOCUS_BUWEI.getIndex()) {
			bmsPlatformPublishInfo.setFocusType(String.valueOf(SmeNewsTypeConst.NEWS_FOCUS_BUWEI));
			bmsPlatformPublishInfo.setPublishType("1");
		}else if(newsSpider.getSpiderModule() ==  SpiderModuleEnum.POLICY_NEWS_FOCUS_TIANJIN.getIndex()) {
			bmsPlatformPublishInfo.setFocusType(String.valueOf(SmeNewsTypeConst.NEWS_FOCUS_TIANJIN));
			bmsPlatformPublishInfo.setPublishType("1");
			bmsPlatformPublishInfo.setPublishFrom(newsSpider.getSource());
		}
				
		if(newsSpider.getSpiderModule() ==  SpiderModuleEnum.POLICY_INDUSTRY_INFO.getIndex()) {
			bmsPlatformPublishInfo.setPublishType(String.valueOf(SmeNewsTypeConst.NEWS_INDUSTRY_INFO));
		}else if(newsSpider.getSpiderModule() == SpiderModuleEnum.POLICY_REGIONAL_DYNAMIC.getIndex()) {
			bmsPlatformPublishInfo.setPublishType(String.valueOf(SmeNewsTypeConst.NEWS_REGIONAL_DYNAMIC));
		}
		// 设置转载时间
		bmsPlatformPublishInfo.setTransTime(new Date());
		// 设置原文链接地址
		bmsPlatformPublishInfo.setOrgLink(newsSpider.getPublishUrl());
		
		// 设置所属区域为01
		bmsPlatformPublishInfo.setAreaType("01");
		// 设置所属平台属于中枢平台
		bmsPlatformPublishInfo.setPlatformType("120000");
		bmsPlatformPublishInfo.setPublisherId(dataSyncSettings.getDefaultPublisherId());
		bmsPlatformPublishInfo.setCreateStamp(new Date());
		bmsPlatformPublishInfo.setUpdateStamp(new Date());
		bmsPlatformPublishInfo.setResponsibilityWindowType("0");
		bmsPlatformPublishInfo.setIsPublished("1");
		bmsPlatformPublishInfo.setIsTop("0");
		bmsPlatformPublishInfo.setIsPublishCenterPlatform("1");
		bmsPlatformPublishInfo.setIsVerify("2");
		bmsPlatformPublishInfo.setDistrictCharacteristicType("0");
		bmsPlatformPublishInfo.setServiceType("100000");  //设置服务类型为信息服务
		bmsPlatformPublishInfo.setPublisherType("1");
//		bmsPlatformPublishInfo.setPublishOrder("1000");
		bmsPlatformPublishInfo.setIsSubmit("1");
		bmsPlatformPublishInfo.setIsOut("0");
		return bmsPlatformPublishInfo;
	}
	
	/**
	 * 
	 * @Description 同步最近N天的数据
	 * @param spiderMoudleEnum
	 * @param lastDays
	 * @return
	 */
	public List<BmsPlatformPublishInfo> newsDataSync(SpiderModuleEnum spiderMoudleEnum, int lastDays){
		Date lastNDays = TimeUtils.getLastNDay(lastDays);
		System.out.println(lastNDays);
		//获取最近N天的数据
		List<NewsSpider> list = newsSpiderRepositoryMysql.findByPublishDateGreaterThanEqualAndSpiderModule(TimeUtils.getLastNDay(lastDays), spiderMoudleEnum.getIndex());
		if(list == null) {
			logger.error("数据库中没有相关记录，本次同步完成，本次同步模块：{},增加条目数为：0条",spiderMoudleEnum.getName());
			return null;
		}
		List<BmsPlatformPublishInfo> listToSync = list.stream().map(e->{
			return convertTo(e);
		}).collect(Collectors.toList());
				
		return bmsPlatformPublishInfoRepositoryInformix.saveAll(listToSync);
	}
}
