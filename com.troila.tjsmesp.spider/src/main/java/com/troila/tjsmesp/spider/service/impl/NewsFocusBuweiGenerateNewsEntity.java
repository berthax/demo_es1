package com.troila.tjsmesp.spider.service.impl;

import com.troila.tjsmesp.spider.annotation.EntityGenerator;
import com.troila.tjsmesp.spider.config.DataSyncSettings;
import com.troila.tjsmesp.spider.constant.SmeNewsTypeConst;
import com.troila.tjsmesp.spider.model.primary.NewsSpider;
import com.troila.tjsmesp.spider.model.secondary.BmsPlatformPublishInfo;
@EntityGenerator(value = 6)
public class NewsFocusBuweiGenerateNewsEntity extends AbstractGenerateNewsEntity{

	@Override
	public BmsPlatformPublishInfo generate(NewsSpider newsSpider, DataSyncSettings dataSyncSettings) {
		BmsPlatformPublishInfo buweiNews = super.generate(newsSpider,dataSyncSettings);
		buweiNews.setFocusType(String.valueOf(SmeNewsTypeConst.NEWS_FOCUS_BUWEI));
		buweiNews.setPublishType("1");
		// 设置所属区域为01
		buweiNews.setAreaType("01");
		buweiNews.setPublisherId(dataSyncSettings.getDefaultPublisherId());
		return buweiNews;
	}
	
	
}
