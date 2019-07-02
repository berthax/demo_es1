package com.troila.tjsmesp.spider.service.impl;

import com.troila.tjsmesp.spider.annotation.EntityGenerator;
import com.troila.tjsmesp.spider.config.DataSyncSettings;
import com.troila.tjsmesp.spider.model.primary.NewsSpider;
import com.troila.tjsmesp.spider.model.secondary.BmsPlatformPublishInfo;
@EntityGenerator(value = 9)
public class JinghaiIndustrialClustersNoticeGenerateNewsEntity extends AbstractGenerateNewsEntity{
	
	@Override
	public BmsPlatformPublishInfo generate(NewsSpider newsSpider, DataSyncSettings dataSyncSettings) {
		BmsPlatformPublishInfo entity = super.generate(newsSpider, dataSyncSettings);
		entity.setPublisherId(dataSyncSettings.getAdminJhXunhuanId());
		entity.setAreaType("03");
		// 通知公告
		entity.setPublishType("5");
		entity.setIndustrialClustersType("11");
		entity.setIsPublishIndustrial("1");
//		bmsPlatformPublishInfo.setIsSubmit("0");
		entity.setIsPublishCenterPlatform("0");
		entity.setIsPublishCountyPlatform("1");
		entity.setIsPublishIndustrial("1");
		entity.setIsTopCy("0");
		entity.setIsTopQx("0");
		return entity;
	}
}
