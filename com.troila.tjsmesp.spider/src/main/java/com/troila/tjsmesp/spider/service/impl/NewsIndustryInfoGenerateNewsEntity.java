package com.troila.tjsmesp.spider.service.impl;

import com.troila.tjsmesp.spider.annotation.EntityGenerator;
import com.troila.tjsmesp.spider.config.DataSyncSettings;
import com.troila.tjsmesp.spider.constant.SmeNewsTypeConst;
import com.troila.tjsmesp.spider.model.primary.NewsSpider;
import com.troila.tjsmesp.spider.model.secondary.BmsPlatformPublishInfo;
@EntityGenerator(value = 3)
public class NewsIndustryInfoGenerateNewsEntity extends AbstractGenerateNewsEntity{

	@Override
	public BmsPlatformPublishInfo generate(NewsSpider newsSpider, DataSyncSettings dataSyncSettings) {
		BmsPlatformPublishInfo entity = super.generate(newsSpider, dataSyncSettings);
		entity.setFocusType("0");
		entity.setPublishType(String.valueOf(SmeNewsTypeConst.NEWS_INDUSTRY_INFO));
		// 设置所属区域为01
		entity.setAreaType("01");
		entity.setPublisherId(dataSyncSettings.getDefaultPublisherId());		
		return entity;
	}
	
	
}	
