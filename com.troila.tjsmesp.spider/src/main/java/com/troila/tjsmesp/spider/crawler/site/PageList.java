package com.troila.tjsmesp.spider.crawler.site;

import us.codecraft.webmagic.Page;

@FunctionalInterface
public interface PageList {	
	void process(Page page);
}
