package com.troila.tjsmesp.spider.service.impl;

import com.troila.tjsmesp.spider.annotation.EntityGenerator;
import com.troila.tjsmesp.spider.config.DataSyncSettings;
import com.troila.tjsmesp.spider.model.primary.NewsSpider;
import com.troila.tjsmesp.spider.model.secondary.BmsPlatformPublishInfo;
@EntityGenerator(value = 8)
public class JinghaiIndustrialClustersNewsGenerateNewsEntity extends AbstractGenerateNewsEntity{

	@Override
	public BmsPlatformPublishInfo generate(NewsSpider newsSpider, DataSyncSettings dataSyncSettings) {
		BmsPlatformPublishInfo entity = super.generate(newsSpider, dataSyncSettings);
		entity.setPublisherId(dataSyncSettings.getAdminJhXunhuanId());
		entity.setAreaType("03");
		// 要闻焦点
		entity.setPublishType("3");
		entity.setIndustrialClustersType("11");
		entity.setIsPublishCenterPlatform("0");
		entity.setIsPublishCountyPlatform("1");
		entity.setIsPublishIndustrial("1");
		entity.setIsTopCy("0");
		entity.setIsTopQx("0");
		return entity;
	}
	
	
}
