package com.troila.tjsmesp.spider.crawler.processor.base;

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
	
	/**
	 * 注： 当子类进行实例化的时候，一定会调用父类的无参构造，
	 * 那么就new了一个新对象，并调用了configure方法进行相关属性的设置
	 * （configure是抽象方法，一定会在子类中重写的）
	 * 
	 * 因为参数中有依赖注入的内容，此部分不能放在无参构造里，否则注入有问题
	 */

/*	protected AbstractPolicyPageProcessor() {
		if(pageSettings == null) {
			pageSettings = new PageSettings();
			configure(pageSettings);
		}
	}*/

	protected abstract void configure(PageSettings pageSettings);
	
}
