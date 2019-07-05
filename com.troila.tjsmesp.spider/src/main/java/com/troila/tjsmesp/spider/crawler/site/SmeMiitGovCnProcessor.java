package com.troila.tjsmesp.spider.crawler.site;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.troila.tjsmesp.spider.constant.CrawlConst;
import com.troila.tjsmesp.spider.constant.FromSiteEnum;
import com.troila.tjsmesp.spider.crawler.processor.base.PageSettings;
import com.troila.tjsmesp.spider.model.primary.NewsSpider;
import com.troila.tjsmesp.spider.util.MD5Util;
import com.troila.tjsmesp.spider.util.TimeUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Selectors;

/**
 * 
 * @ClassName:  SmeMiitGovCn   
 * @Description:中小企业信息网页面逻辑处理  
 * @author: xgj
 * @date:   2019年6月19日 下午4:01:33   
 *
 */
//@Component
public class SmeMiitGovCnProcessor implements SpiderProcess{
	private static final Logger logger = LoggerFactory.getLogger(SmeMiitGovCnProcessor.class);

	private List<String> pastCrawledUrls;
	@Override
	public void listProcess(Page page, PageSettings pageSettings) {
		if(StringUtils.isEmpty(page.getRawText())) {
			logger.info("文章链接页{}，页面获取结果有问题，跳过该页面",page.getUrl());
			page.setSkip(true);
			return;
		}
		List<String> list =  page.getHtml().xpath("//div[@class='new_title']").links().all();
//		if(null == list || list.size() == 0) {
//			logger.info("当前页面获取结果有问题，获取地址为【{}】,获取到的内容为【{}】",page.getUrl(),page.getRawText());
//		}
		//将当前列表页所有的最新政策文章详情页加入到后续的url地址，有待继续爬取
		List<String> articleList = list.stream().filter(p->p.matches(pageSettings.getArticleUrlRegex())).collect(Collectors.toList());
		if(null != articleList && articleList.size()>0) {
			// 过滤掉以前已经爬取过的记录，不再重复爬取
			pastCrawledUrls = pageSettings.getProcessorService().getCrawledUrls(pageSettings.getModule().getIndex());				
			if(pastCrawledUrls != null  && pastCrawledUrls.size()>0) {
				articleList = articleList.stream().filter(p->!pastCrawledUrls.contains(p)).collect(Collectors.toList());
			}			
			page.addTargetRequests(articleList);			
		}
		
		//获取底部分页栏的所有a标签
		List<Element> urlList = Selectors.xpath("//div[@class='fl_page2']/a").selectElements(page.getHtml().toString());
		if(null != urlList && urlList.size() > 0) {
			List<String> urls = urlList.stream()
					//取出a链接的页码
					.map(e->{return Selectors.xpath("//a/text()").select(e.toString()).replace(".", "");})
					// 筛除掉1和跳转两个值，此两种拼接后是无效的
					.filter(p->!(p.equals("1") || p.equals("跳转")))
					// 将页码拼接成有效的链接地址
					.map(e->pageSettings.getWebSiteListPrefix() + e +".shtml")  
					.collect(Collectors.toList());
			//将其他列表页加入后续地址列表中
			page.addTargetRequests(urls);			
		}
		
		list = null;
		articleList = null;
		pastCrawledUrls = null;		
		urlList = null;
		System.gc();
		
	}

	@Override
	public void detailProcess(Page page, PageSettings pageSettings) {
		if(StringUtils.isEmpty(page.getRawText())) {
			logger.info("文章链接页{}，未找到指定的页面内容，跳过该页面",page.getUrl());
			page.setSkip(true);
			return;
		}
		String title = page.getHtml().xpath("//div[@class='head_1']/a/font/tidyText()").toString();
		String content = page.getHtml().xpath("//div[@class='news_nav']").toString();
		if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
			logger.info("文章链接页{}，未找到指定的页面内容，跳过该页面",page.getUrl());
			page.setSkip(true);
			return;
		}
		//具体字段内容解析
		NewsSpider spider = new NewsSpider();
		spider.setTitle(title);
		spider.setContent(content);
		List<Element> list = Selectors.xpath("//div[@class='head_2']/span/a").selectElements(page.getHtml().toString()); 
		if(list.size()>=1) {
			String dateStr = Selectors.xpath("//a/@title").select(list.get(0).toString());			
			// spider.setPublishDate(TimeUtils.getLongFormatDate(dateStr));  
			// 截取到的时间是2019-06-19 09:06:028格式的
			spider.setPublishDate(TimeUtils.getFormatDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:sss"), dateStr));
		}
		if(list.size()>=2) { // 判断一下，防止个别记录没有该项内容，出现数组下表越界异常
			String sourceStr = Selectors.xpath("//a/@title").select(list.get(1).toString());
			spider.setSource(sourceStr);			
		}
		spider.setPublishUrl(page.getUrl().toString());
		spider.setFromLink(FromSiteEnum.ZHONGXIAOQIYEXINXIWANG.getLink());
		spider.setFromSite(FromSiteEnum.ZHONGXIAOQIYEXINXIWANG.getName());
		spider.setSpiderCode(MD5Util.getMD5(spider.getPublishUrl()));   //根据特定的内容生成MD5，作为该条记录的id
		spider.setSpiderModule(pageSettings.getModule().getIndex());
		page.putField(CrawlConst.CRAWL_ITEM_KEY, spider);			
	}
}
