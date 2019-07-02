package com.troila.tjsmesp.spider.service;

import com.troila.tjsmesp.spider.config.DataSyncSettings;
import com.troila.tjsmesp.spider.model.primary.NewsSpider;
import com.troila.tjsmesp.spider.model.secondary.BmsPlatformPublishInfo;

public interface IGenerateNewsEntity {	
	public BmsPlatformPublishInfo generate(NewsSpider newsSpider,DataSyncSettings dataSyncSettings);
}
