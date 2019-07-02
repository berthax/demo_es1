package com.troila.tjsmesp.spider.service.impl;

import com.troila.tjsmesp.spider.annotation.EntityGenerator;
import com.troila.tjsmesp.spider.config.DataSyncSettings;
import com.troila.tjsmesp.spider.constant.SmeNewsTypeConst;
import com.troila.tjsmesp.spider.model.primary.NewsSpider;
import com.troila.tjsmesp.spider.model.secondary.BmsPlatformPublishInfo;
@EntityGenerator(value = 5)
public class NewsFocusGuojiaGenerateNewsEntity extends AbstractGenerateNewsEntity{

	@Override
	public BmsPlatformPublishInfo generate(NewsSpider newsSpider,DataSyncSettings dataSyncSettings) {
		BmsPlatformPublishInfo guojiaNews = super.generate(newsSpider,dataSyncSettings);
		guojiaNews.setFocusType(String.valueOf(SmeNewsTypeConst.NEWS_FOCUS_GUOJIA));
		guojiaNews.setPublishType("1");
		// 设置所属区域为01
		guojiaNews.setAreaType("01");
		guojiaNews.setPublisherId(dataSyncSettings.getDefaultPublisherId());
		return guojiaNews;
	}

}
