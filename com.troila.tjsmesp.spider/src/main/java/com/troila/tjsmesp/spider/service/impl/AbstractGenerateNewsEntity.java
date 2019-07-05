package com.troila.tjsmesp.spider.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.troila.tjsmesp.spider.config.DataSyncSettings;
import com.troila.tjsmesp.spider.model.primary.NewsSpider;
import com.troila.tjsmesp.spider.model.secondary.BmsPlatformPublishInfo;
import com.troila.tjsmesp.spider.service.IGenerateNewsEntity;

public abstract class AbstractGenerateNewsEntity implements IGenerateNewsEntity{
	private static final Logger logger = LoggerFactory.getLogger(AbstractGenerateNewsEntity.class);
	
	@Override
	public BmsPlatformPublishInfo generate(NewsSpider newsSpider,DataSyncSettings dataSyncSettings) {
		// 通用方法进行最基本通用的参数赋值
		if(newsSpider == null) {
			logger.error("类型转换参数错误：参数newsSpider为null");
			return null;			
		}
		
		BmsPlatformPublishInfo bmsPlatformPublishInfo = new BmsPlatformPublishInfo();
		bmsPlatformPublishInfo.setPublishTitle(newsSpider.getTitle());
		bmsPlatformPublishInfo.setPublishContent(newsSpider.getContent());
		bmsPlatformPublishInfo.setPublishTime(newsSpider.getPublishDate());
		bmsPlatformPublishInfo.setPublishFrom(newsSpider.getSource());
		// 设置所属平台属于中枢平台
		bmsPlatformPublishInfo.setPlatformType("120000");
		bmsPlatformPublishInfo.setCreateStamp(new Date());
		bmsPlatformPublishInfo.setUpdateStamp(new Date());
		bmsPlatformPublishInfo.setResponsibilityWindowType("0");
		bmsPlatformPublishInfo.setIsPublished("0");
		bmsPlatformPublishInfo.setIsTop("0");
		bmsPlatformPublishInfo.setIsPublishCenterPlatform("1");
		bmsPlatformPublishInfo.setIsVerify("2");   // 设置为未审核
		bmsPlatformPublishInfo.setDistrictCharacteristicType("0");
		bmsPlatformPublishInfo.setServiceType("100000");  //设置服务类型为信息服务
		bmsPlatformPublishInfo.setPublisherType("1");
		bmsPlatformPublishInfo.setIsSubmit("1");
		bmsPlatformPublishInfo.setIsOut("0");
		bmsPlatformPublishInfo.setTransTime(new Date()); // 设置原文链接地址
		bmsPlatformPublishInfo.setOrgLink(newsSpider.getPublishUrl());
		return bmsPlatformPublishInfo;
	}
	
	
}
