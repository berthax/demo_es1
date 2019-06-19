package com.troila.tjsmesp.spider.crawler.site;

import us.codecraft.webmagic.Page;
/**
 * 
 * @ClassName:  SpiderProcess   
 * @Description:分别提供对爬取页的具体处理  
 * @author: xgj
 * @date:   2019年6月19日 上午10:21:18   
 *
 */
public interface SpiderProcess {
	/**
	 * 
	 * 对爬取的列表页的处理   
	 * @param page
	 */	
	public void listProcess(Page page);
	/**
	 * 
	 * 对爬取的详情页的处理   
	 * @param page
	 */
	public void detailProcess(Page page);
}
