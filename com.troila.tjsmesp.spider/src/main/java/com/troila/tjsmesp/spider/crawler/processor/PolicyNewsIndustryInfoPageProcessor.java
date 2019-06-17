package com.troila.tjsmesp.spider.crawler.processor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.model.primary.PolicySpider;
import com.troila.tjsmesp.spider.util.MD5Util;
import com.troila.tjsmesp.spider.util.TimeUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectors;

/**
 * 中小企业信息网-》产业频道-》行业热点相关政策的爬取
 * 网址http://www.sme.gov.cn/cms/news/100000/0000000071/0000000071.shtml
 * 此部分对应中小企网站的产业资讯模块的内容
 * @author xuanguojing
 *
 */
@Component("policyIndustryFocusPageProcessor")
public class PolicyNewsIndustryInfoPageProcessor implements PageProcessor {

	 /**
     * 行业热点详情页的正则表达式
     */
    private static final String ARTICLE_URL = "http://www.sme.gov.cn/cms/news/100000/0000000071/(\\d+)/(\\d+)/(\\d+)/(\\w+)\\.shtml";
    
    /**
     * 行业热点列表页的正则表达式
     */    
    private static final String LIST_URL = "http://www\\.sme\\.gov\\.cn/cms/news/100000/0000000071/0000000071(_\\d+)*\\.shtml";
    
	
	@Override
	public void process(Page page) {
		if(page.getUrl().regex(LIST_URL).match()) {
			List<String> list =  page.getHtml().xpath("//div[@class='new_title']").links().all();
			System.out.println(list);
			//将当前列表页所有的最新政策文章详情页加入到后续的url地址，有待继续爬取
			List<String> articleList = list.stream().filter(p->p.matches(ARTICLE_URL)).collect(Collectors.toList());
			page.addTargetRequests(articleList);
						
			//获取底部分页栏的所有a标签
			List<Element> urlList = Selectors.xpath("//div[@class='fl_page2']/a").selectElements(page.getHtml().toString());
			//获取总页码数，倒数第2个a标签为最后一页的页码
			String totalStr = Selectors.xpath("//a/text()").select(urlList.get(urlList.size()-2).toString()).replace(".", "");  
			//获取当前选择的页码数
			String currentPage = page.getHtml().xpath("//div[@class='fl_page2']/a[@class='selected']/text()").toString();
			if(Integer.parseInt(currentPage)<Integer.parseInt(totalStr)) {
				//将当前页的下一个列表页加入到后续爬取的url地址中
				page.addTargetRequest("http://www.sme.gov.cn/cms/news/100000/0000000071/0000000071_"+(Integer.parseInt(currentPage)+1)+".shtml");
			}
		}else {
			System.out.println("当前下载的页面url为："+page.getUrl());
			//具体页面内容获取，具体字段拆分待完成
			PolicySpider spider = new PolicySpider();
//			spider.setTitle(page.getHtml().xpath("//div[@class='head_1']/a/@title").toString());
			spider.setTitle(page.getHtml().xpath("//div[@class='head_1']/a/font/tidyText()").toString());
			List<Element> list = Selectors.xpath("//div[@class='head_2']/span/a").selectElements(page.getHtml().toString()); 
			String dateStr = Selectors.xpath("//a/@title").select(list.get(0).toString());
			spider.setPublishDate(TimeUtils.getLongFormatDate(dateStr));
			String sourceStr = Selectors.xpath("//a/@title").select(list.get(1).toString());
			spider.setSource(sourceStr);
			spider.setContent(page.getHtml().xpath("//div[@class='news_nav']/tidyText()").toString());
			spider.setStripContent(page.getHtml().xpath("//div[@class='news_nav']/tidyText()").toString());
			spider.setPublishUrl(page.getUrl().toString());
			spider.setFromLink("http://www.sme.gov.cn");
			spider.setFromSite("中小企业信息网");
			spider.setForwardTime(new Date());	
			spider.setId(MD5Util.getMD5(spider.toString()));   //根据特定的内容生成MD5，作为该条记录的id
			spider.setSpiderModule(SpiderModuleEnum.POLICY_INDUSTRY_INFO.getIndex());
			page.putField("policy", spider);
		}
		
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return Site.me().setRetryTimes(3).setSleepTime(1000).setDomain("http://www.sme.gov.cn");
	}

}
