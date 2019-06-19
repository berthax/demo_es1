package com.troila.tjsmesp.spider.crawler.site;

import us.codecraft.webmagic.Page;

@FunctionalInterface
public interface PageDetail {
	void process(Page page);
}
