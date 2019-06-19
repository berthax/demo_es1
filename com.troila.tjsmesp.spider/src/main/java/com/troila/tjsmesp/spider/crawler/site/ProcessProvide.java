package com.troila.tjsmesp.spider.crawler.site;

import us.codecraft.webmagic.Page;

public interface ProcessProvide {
	
//	private SpiderModuleEnum spiderModuleEnum = null;
	
	
	
	public void settingPageDetail(Page page, PageDetail pageDetail);
	
	public void settingPageList(Page page, PageList pageList);
		
}
