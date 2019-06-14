package com.troila.tjsmesp.spider.crawler.processor;

import java.text.SimpleDateFormat;
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
 * 天津政务网-》新闻-》各区动态相关政策的爬取
 * 网址http://www.tj.gov.cn/xw/qx1/
 * 此部分对应中小企网站的天津模块的内容
 * @author xuanguojing
 *
 */
@Component("policyNewsDistrictPageProcessor")
public class PolicyNewsDistrictPageProcessor implements PageProcessor{
	
	/**
     * 各区动态详情页的正则表达式
     */
    private static final String ARTICLE_URL = "http://www.tj.gov.cn/xw/qx1/(\\d+)/(\\w+)\\.html";
    		
    /**
     * 各区动态列表页的正则表达式
     */    
    private static final String LIST_URL = "http://www\\.tj\\.gov\\.cn/xw/qx1/index(_\\d+)*\\.html";
		
	@Override
	public void process(Page page) {
		if(page.getUrl().regex(LIST_URL).match()) {
			List<String> list =  page.getHtml().xpath("//div[@class='left leftlist']").links().all();
//			System.out.println(list);
			
			for(String str : list)
				System.out.println(str);
			//将当前列表页所有的最新政策文章详情页加入到后续的url地址，有待继续爬取
			List<String> articleList = list.stream().filter(p->p.matches(ARTICLE_URL)).collect(Collectors.toList());
			page.addTargetRequests(articleList);
			//将下一页的列表页链接加入到后续的url地址，有待继续爬取
			List<String> urlList = list.stream().filter(p->p.matches(LIST_URL)).collect(Collectors.toList());
			page.addTargetRequests(urlList);  			
		}else if(page.getUrl().regex(ARTICLE_URL).match()){
			System.out.println("当前下载的页面url为："+page.getUrl());
			PolicySpider spider = new PolicySpider();
			String selectedDiv = page.getHtml().xpath("//div[@class='left leftlist']").toString();
			spider.setTitle(Selectors.xpath("//div[@class='title']/text()").select(selectedDiv));
			List<Element> list1 = Selectors.xpath("//div[@class='time xwlc pd']/span").selectElements(selectedDiv);
			String sourceStr =  Selectors.xpath("//span/text()").select(list1.get(0).toString()).split("来源：")[1];
			spider.setSource(sourceStr);
			String dateStr = Selectors.xpath("//span/text()").select(list1.get(1).toString()).split("发布时间：")[1];
			spider.setPublishDate(TimeUtils.getFormatDate(new SimpleDateFormat("yyyy-MM-dd HH:mm"), dateStr));
			String content = Selectors.xpath("//div[@class='concon']/tidyText()").select(selectedDiv);
			spider.setContent(content);
			spider.setStripContent(content);
			spider.setPublishUrl(page.getUrl().toString());
			spider.setFromLink("http://www.tj.gov.cn");
			spider.setFromSite("天津政务网");
			spider.setForwardTime(new Date());	
			spider.setId(MD5Util.getMD5(spider.getPublishUrl()));   //根据特定的内容生成MD5，作为该条记录的id
			spider.setSpiderModule(SpiderModuleEnum.POLICY_NEWS_DISTRICT.getIndex());
			page.putField("policy", spider);
		}else {
			return;
		}
		
	}

	@Override
	public Site getSite() {
		return Site.me().setRetryTimes(3).setSleepTime(1000).setDomain("http://www.tj.gov.cn");
	}

	public static void main(String[] args) {
		String url1 = "http://www.tj.gov.cn/xw/qx1/201812/t20181207_3646000.html";
		String url2 = "http://www.tj.gov.cn/xw/qx1/201812/t20181207_3645997.html";
		String url3 = "http://www.tj.gov.cn/xw/qx1/201812/t20181207_3646011.html";
		
		System.out.println(url1.matches(ARTICLE_URL));
		System.out.println(url2.matches(ARTICLE_URL));
		System.out.println(url3.matches(ARTICLE_URL));
	}
}
