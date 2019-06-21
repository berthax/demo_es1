package com.troila.tjsmesp.spider.crawler.site;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.troila.tjsmesp.spider.constant.CrawlConst;
import com.troila.tjsmesp.spider.constant.FromSiteEnum;
import com.troila.tjsmesp.spider.crawler.processor.base.PageSettings;
import com.troila.tjsmesp.spider.model.primary.NewsSpider;
import com.troila.tjsmesp.spider.util.MD5Util;
import com.troila.tjsmesp.spider.util.TimeUtils;

import us.codecraft.webmagic.Page;
/**
 * 
 * @ClassName:  SouSouGovCn   
 * @Description:中国政府网的页面爬取逻辑  区域资讯暂时用到此网站
 * @author: xgj
 * @date:   2019年6月20日 下午3:09:53   
 *
 */
public class SouSouGovCn implements SpiderProcess{

	@Override
	public void listProcess(Page page, PageSettings pageSettings) {
		List<String> list =  page.getHtml().xpath("//div[@class='news_box']").links().all();
		//将当前列表页所有的最新政策文章详情页加入到后续的url地址，有待继续爬取
		List<String> articleList = list.stream().filter(p->p.matches(pageSettings.getArticleUrlRegex())).collect(Collectors.toList());
		
		//查看已经爬取过的链接记录
		List<String> pastCrawledUrls = pageSettings.getProcessorService().getCrawledUrls(pageSettings.getModule().getIndex());
		if(pastCrawledUrls != null  && pastCrawledUrls.size()>0) {
			articleList = articleList.stream().filter(p->!pastCrawledUrls.contains(p)).collect(Collectors.toList());
		}			
		page.addTargetRequests(articleList);
		//将其他的最新政策列表页加入到后续的url地址，有待继续爬取,该网站列表页的连接是用js函数去做的，不能直接抓取

		//将下一页的列表页链接加入到后续的url地址，有待继续爬取
		List<String> urlList = list.stream().filter(p->p.matches(pageSettings.getListUrlRegex())).collect(Collectors.toList());
		page.addTargetRequests(urlList); 			
	}

	@Override
	public void detailProcess(Page page, PageSettings pageSettings) {
		//具体页面内容获取，具体字段拆分待完成
		NewsSpider spider = new NewsSpider();
		String title = page.getHtml().xpath("//div[@class='article oneColumn pub_border']/h1/tidyText()").toString();
		spider.setTitle(title);
		
		// 处理时间和来源字段
		String page_date = page.getHtml().xpath("//div[@class='pages-date']/tidyText()").toString();			
		String pages_print = page.getHtml().xpath("//div[@class='pages_print']/tidyText()").toString();			
		String dateSourceStr = page_date.replaceFirst(pages_print, "");	
		String source = page.getHtml().xpath("//div[@class='pages-date']/span/tidyText()").toString();
		String dateStr = dateSourceStr.replaceFirst(source, "").trim();			
		// 设置来源
		spider.setSource(source.replaceAll("来源：", "").trim());
		// 设置时间
		if(!StringUtils.isEmpty(dateStr)) {
			spider.setPublishDate(TimeUtils.getFormatDate(new SimpleDateFormat("yyyy-MM-dd HH:mm"), dateStr));
		}
					
//		String content = page.getHtml().xpath("//div[@class='pages_content']").toString();
		spider.setContent(page.getHtml().xpath("//div[@class='pages_content']").toString());
		
		spider.setPublishUrl(page.getUrl().toString());
		spider.setFromLink(FromSiteEnum.ZHONGGUOZHENGFUWANG.getLink());
		spider.setFromSite(FromSiteEnum.ZHONGGUOZHENGFUWANG.getName());
		
		spider.setSpiderCode(MD5Util.getMD5(spider.getPublishUrl()));   //根据特定的内容生成MD5，作为该条记录的id
		spider.setSpiderModule(pageSettings.getModule().getIndex());
		page.putField(CrawlConst.CRAWL_ITEM_KEY, spider);
		
	}

}
