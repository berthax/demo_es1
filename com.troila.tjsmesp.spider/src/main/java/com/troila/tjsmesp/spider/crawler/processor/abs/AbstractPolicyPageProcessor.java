package com.troila.tjsmesp.spider.crawler.processor.abs;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public abstract class AbstractPolicyPageProcessor implements PageProcessor{

	private PageSettings pageSettings;
	
	@Override
	public void process(Page page) {
		if(pageSettings == null) {
			pageSettings = new PageSettings();
			configure(pageSettings);
		}
		if(page.getUrl().regex(pageSettings.getListUrlRegex()).match()) {
			pageSettings.getSpiderProcess().listProcess(page,pageSettings);
		}else if(page.getUrl().regex(pageSettings.getArticleUrlRegex()).match()){
			pageSettings.getSpiderProcess().detailProcess(page,pageSettings);
		}else {
			return;
		}		
	}

	@Override
	public Site getSite() {
		if(pageSettings == null) {
			pageSettings = new PageSettings();
			configure(pageSettings);
		}
		return Site.me().setRetryTimes(3).setSleepTime(1000).setDomain(pageSettings.getDomain());
	}
	
	protected abstract void configure(PageSettings pageSettings);
	
}
