package com.troila.tjsmesp.spider.crawler.processor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.constant.CrawlConst;
import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.crawler.processor.base.AbstractPolicyPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.base.PageSettings;
import com.troila.tjsmesp.spider.crawler.service.NewsProcessorService;
import com.troila.tjsmesp.spider.crawler.site.SiteProcessorFactory;
import com.troila.tjsmesp.spider.crawler.site.SmeMiitGovCnProcessor;
import com.troila.tjsmesp.spider.model.primary.PolicySpider;
import com.troila.tjsmesp.spider.util.MD5Util;
import com.troila.tjsmesp.spider.util.TimeUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectors;

/**
 * 中小企业信息网-》政务频道-》地方政府的相关政策爬取
 * 网址：http://www.sme.gov.cn/cms/news/100000/0000000225/0000000225.shtml
 * 此部分对应中小企网站区域动态模块的内容
 * @author xuanguojing
 *
 */
@Component("policyNewsRegionalDynamicPageProcessor")
public class PolicyNewsRegionalDynamicPageProcessor extends AbstractPolicyPageProcessor{

	 /**
     * 区域动态详情页的正则表达式
     */
    private static final String ARTICLE_URL = "http://sme.miit.gov.cn/cms/news/100000/0000000239/(\\d+)/(\\d+)/(\\d+)/(\\w+)\\.shtml";
    /**
     * 区域动态列表页的正则表达式
     */    
    private static final String LIST_URL = "http://sme.miit.gov.cn/cms/news/100000/0000000239/0000000239(_\\d+)*\\.shtml";

    private static final String channelid = "http://sme.miit.gov.cn/cms/news/100000/0000000239/0000000239_";
    
    @Autowired
    private NewsProcessorService newsProcessorService;
    
    @Override
	protected void configure(PageSettings pageSettings) {
    	pageSettings.setArticleUrlRegex(ARTICLE_URL)
			.setListUrlRegex(LIST_URL)
			.setDomain("http://sme.miit.gov.cn")
			.setWebSiteListPrefix(channelid)
			.setSpiderProcess(SiteProcessorFactory.create(SmeMiitGovCnProcessor.class))
			.setProcessorService(newsProcessorService)
			.setModule(SpiderModuleEnum.POLICY_REGIONAL_DYNAMIC);		
	}
	
}
