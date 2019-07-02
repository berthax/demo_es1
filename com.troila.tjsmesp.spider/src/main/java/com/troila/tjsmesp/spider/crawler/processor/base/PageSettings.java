package com.troila.tjsmesp.spider.crawler.processor.base;

import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.crawler.service.ProcessorService;
import com.troila.tjsmesp.spider.crawler.site.SpiderProcess;

/**
 * 
 * @ClassName:  PageSettings   
 * @Description:页面处理过程中使用到的各种参数  
 * @author: xgj
 * @date:   2019年6月20日 上午11:34:22   
 *
 */
public class PageSettings {
	/**
	 * 爬取的模块
	 */
	private SpiderModuleEnum module;
	/**
	 * 爬取网站的域名
	 */
	private String domain;
	/**
	 * 要爬取的模块的列表页链接正则表达式
	 */
	private String listUrlRegex;
	/**
	 * 要爬取的模块的文章详情页链接正则表达式
	 */
	private String articleUrlRegex;
	/**
	 * 矫正获取的页面内容中的附件链接地址正则表达式
	 */
	private String attachmentsUrlAdjustRegex;
	/**
	 * 矫正获取的页面内容中的图片链接地址正则表达式
	 */
	private String imageUrlAdjustRegex;
	/**
	 * 某些网站可能用到列表页链接的前缀，有些网站无法直接获取下一页的链接，需要拼接时，会用到，
	 * 如果能直接获取到其他列表页的链接，此字段没什么用
	 */
	private String webSiteListPrefix;
	/**
	 * 用于爬取过程中一些其他的处理，比如去重，查数据库等
	 */
	private ProcessorService processorService;
	/**
	 * 对爬取页面的具体处理，策略模式
	 */
	private SpiderProcess spiderProcess;
	
	public String getDomain() {
		return domain;
	}
	public PageSettings setDomain(String domain) {
		this.domain = domain;
		return this;
	}
	public String getListUrlRegex() {
		return listUrlRegex;
	}
	public PageSettings setListUrlRegex(String listUrlRegex) {
		this.listUrlRegex = listUrlRegex;
		return this;
	}
	public String getArticleUrlRegex() {
		return articleUrlRegex;
	}
	public PageSettings setArticleUrlRegex(String articleUrlRegex) {
		this.articleUrlRegex = articleUrlRegex;
		return this;
	}
	public SpiderModuleEnum getModule() {
		return module;
	}
	public PageSettings setModule(SpiderModuleEnum module) {
		this.module = module;
		return this;
	}
	public SpiderProcess getSpiderProcess() {
		return spiderProcess;
	}
	public PageSettings setSpiderProcess(SpiderProcess spiderProcess) {
		this.spiderProcess = spiderProcess;
		return this;
	}	
	public ProcessorService getProcessorService() {
		return processorService;
	}
	public PageSettings setProcessorService(ProcessorService processorService) {
		this.processorService = processorService;
		return this;
	}
	public String getWebSiteListPrefix() {
		return webSiteListPrefix;
	}
	public PageSettings setWebSiteListPrefix(String webSiteListPrefix) {
		this.webSiteListPrefix = webSiteListPrefix;
		return this;
	}
	public String getAttachmentsUrlAdjustRegex() {
		return attachmentsUrlAdjustRegex;
	}
	public String getImageUrlAdjustRegex() {
		return imageUrlAdjustRegex;
	}
	public PageSettings setAttachmentsUrlAdjustRegex(String attachmentsUrlAdjustRegex) {
		this.attachmentsUrlAdjustRegex = attachmentsUrlAdjustRegex;
		return this;
	}
	public PageSettings setImageUrlAdjustRegex(String imageUrlAdjustRegex) {
		this.imageUrlAdjustRegex = imageUrlAdjustRegex;
		return this;
	}	
}
