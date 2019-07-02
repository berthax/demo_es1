package com.troila.tjsmesp.spider.service.impl;

import com.troila.tjsmesp.spider.annotation.EntityGenerator;
import com.troila.tjsmesp.spider.config.DataSyncSettings;
import com.troila.tjsmesp.spider.constant.SmeNewsTypeConst;
import com.troila.tjsmesp.spider.model.primary.NewsSpider;
import com.troila.tjsmesp.spider.model.secondary.BmsPlatformPublishInfo;
@EntityGenerator(value = 7)
public class NewsFocusTianjinGenerateNewsEntity extends AbstractGenerateNewsEntity{

	@Override
	public BmsPlatformPublishInfo generate(NewsSpider newsSpider, DataSyncSettings dataSyncSettings) {
		BmsPlatformPublishInfo entity = super.generate(newsSpider, dataSyncSettings);
		entity.setFocusType(String.valueOf(SmeNewsTypeConst.NEWS_FOCUS_TIANJIN));
		entity.setPublishType("1");	
		// 设置所属区域为01
		entity.setAreaType("01");
		entity.setPublisherId(dataSyncSettings.getDefaultPublisherId());		
		return entity;
	}
	
	
}
