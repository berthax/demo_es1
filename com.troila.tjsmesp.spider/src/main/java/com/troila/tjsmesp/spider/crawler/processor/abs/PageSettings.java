package com.troila.tjsmesp.spider.crawler.processor.abs;

import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.crawler.site.SpiderProcess;

public class PageSettings {
	private SpiderModuleEnum module;
	private String domain;
	private String listUrlRegex;
	private String ArticleUrlRegex;
	private SpiderProcess spiderProcess;
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getListUrlRegex() {
		return listUrlRegex;
	}
	public void setListUrlRegex(String listUrlRegex) {
		this.listUrlRegex = listUrlRegex;
	}
	public String getArticleUrlRegex() {
		return ArticleUrlRegex;
	}
	public void setArticleUrlRegex(String articleUrlRegex) {
		ArticleUrlRegex = articleUrlRegex;
	}
	public SpiderModuleEnum getModule() {
		return module;
	}
	public void setModule(SpiderModuleEnum module) {
		this.module = module;
	}
	public SpiderProcess getSpiderProcess() {
		return spiderProcess;
	}
	public void setSpiderProcess(SpiderProcess spiderProcess) {
		this.spiderProcess = spiderProcess;
	}
}
