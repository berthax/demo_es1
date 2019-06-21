package com.troila.tjsmesp.spider.crawler.processor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.constant.CrawlConst;
import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
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
@Component("policyNewsRegionalDynamicPageProcessor_old")
public class PolicyNewsRegionalDynamicPageProcessor_old implements PageProcessor{

	 /**
     * 区域动态详情页的正则表达式
     */
    private static final String ARTICLE_URL = "http://www.sme.gov.cn/cms/news/100000/0000000225/(\\d+)/(\\d+)/(\\d+)/(\\w+)\\.shtml";
    /**
     * 区域动态列表页的正则表达式
     */    
    private static final String LIST_URL = "http://www\\.sme\\.gov\\.cn/cms/news/100000/0000000225/0000000225(_\\d+)*\\.shtml";
	
//    private boolean isFlag = false;  //是否需要停止爬取  默认最近三天的政策记录
	
	@Override
	public void process(Page page) {
		if(page.getUrl().regex(LIST_URL).match()) {
			List<String> list =  page.getHtml().xpath("//div[@class='new_title']").links().all();
			//将当前列表页所有的最新政策文章详情页加入到后续的url地址，有待继续爬取
			List<String> articleList = list.stream().filter(p->p.matches(ARTICLE_URL)).collect(Collectors.toList());
			page.addTargetRequests(articleList);
			//将其他的最新政策列表页加入到后续的url地址，有待继续爬取,该网站列表页的连接是用js函数去做的，不能直接抓取
//			List<String> urlList = list.stream().filter(p->p.matches(LIST_URL)).collect(Collectors.toList());
//			page.addTargetRequests(urlList);
//			String selectPage = page.getHtml().xpath("//div[@class='fl_page2']/a[@class='selected']/text()").toString();
			//获取底部分页栏的所有a标签
			List<Element> urlList = Selectors.xpath("//div[@class='fl_page2']/a").selectElements(page.getHtml().toString());
			//获取总页码数，倒数第2个a标签为最后一页的页码
			String totalStr = Selectors.xpath("//a/text()").select(urlList.get(urlList.size()-2).toString()).replace(".", "");  
			//获取当前选择的页码数
			String currentPage = page.getHtml().xpath("//div[@class='fl_page2']/a[@class='selected']/text()").toString();
			if(Integer.parseInt(currentPage)<Integer.parseInt(totalStr)) {
				//将当前页的下一个列表页加入到后续爬取的url地址中
				page.addTargetRequest("http://www.sme.gov.cn/cms/news/100000/0000000225/0000000225_"+(Integer.parseInt(currentPage)+1)+".shtml");
			}
		}else {
			//System.out.println("当前下载的页面url为："+page.getUrl());
			//具体页面内容获取，具体字段拆分待完成
			PolicySpider spider = new PolicySpider();
//			spider.setTitle(page.getHtml().xpath("//div[@class='head_1']/a/@title").toString());  //此种选法有时候不准确，会截断一部分文字
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
			spider.setSpiderModule(SpiderModuleEnum.POLICY_REGIONAL_DYNAMIC.getIndex());
			page.putField(CrawlConst.CRAWL_ITEM_KEY, spider);
		}		
	}

	@Override
	public Site getSite() {		
		return Site.me().setRetryTimes(3).setSleepTime(1000).setDomain("http://www.sme.gov.cn");
	}
	
	public static void main(String[] args) {
		String listStr1 = "http://www.sme.gov.cn/cms/news/100000/0000000225/0000000225.shtml";
		System.out.println(listStr1.matches(LIST_URL));
		String listStr2 = "http://www.sme.gov.cn/cms/news/100000/0000000225/0000000225_2.shtml";
		System.out.println(listStr2.matches(LIST_URL));
		
		String articleStr1="http://www.sme.gov.cn/cms/news/100000/0000000225/2018/12/12/94c061b485224e4991f9bd78b7b118b1.shtml";
		String articelStr2="http://www.sme.gov.cn/cms/news/100000/0000000225/2018/11/26/15177098b9f74ce8af64ef4290416fdb.shtml";
		System.out.println(articleStr1.matches(ARTICLE_URL));
		System.out.println(articelStr2.matches(ARTICLE_URL));
	}

}
