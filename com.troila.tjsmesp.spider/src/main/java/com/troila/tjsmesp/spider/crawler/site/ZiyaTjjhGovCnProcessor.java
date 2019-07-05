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
 * @ClassName:  ZiyaTjjhGovCnProcessor   
 * @Description:子牙循环经济网
 * @author: xgj
 * @date:   2019年6月25日 下午1:49:08   
 *
 */
public class ZiyaTjjhGovCnProcessor implements SpiderProcess{
	
	private static final Logger logger = LoggerFactory.getLogger(ZiyaTjjhGovCnProcessor.class);
	
	private List<String> pastCrawledUrls;
		
	@Override
	public void listProcess(Page page, PageSettings pageSettings) {
		List<String> list =  page.getHtml().xpath("//div[@class='span8 main']").links().all();
		List<String> articleList = list.stream().filter(p->p.matches(pageSettings.getArticleUrlRegex())).collect(Collectors.toList());
		// 判断一下，避免NPE异常
		if(null != articleList && articleList.size() > 0) {
			//	查看已经爬取过的链接记录
			pastCrawledUrls = pageSettings.getProcessorService().getCrawledUrls(pageSettings.getModule().getIndex());
			if(pastCrawledUrls != null  && pastCrawledUrls.size()>0) {
				articleList = articleList.stream().filter(p->!pastCrawledUrls.contains(p)).collect(Collectors.toList());
			}			
			page.addTargetRequests(articleList);			
		}
		
		//	将下一页的列表页链接加入到后续的url地址，有待继续爬取
		List<String> urlList = list.stream().filter(p->p.matches(pageSettings.getListUrlRegex())).collect(Collectors.toList());
		// 判断一下，避免NPE异常
		if(null != urlList && urlList.size() > 0) {
			page.addTargetRequests(urlList); 			
		}
		
		list = null;
		articleList = null;
		pastCrawledUrls = null;		
		urlList = null;
		System.gc();
		
	}

	@Override
	public void detailProcess(Page page, PageSettings pageSettings) {
		NewsSpider spider = new NewsSpider();
		if(StringUtils.isEmpty(page.getRawText())){
			logger.info("文章链接页{}，未找到指定的页面内容，跳过该页面",page.getUrl());
			page.setSkip(true);
			return;
		}
		String title = page.getHtml().xpath("//div[@class='infos detail']/div[@class='title']/h4/tidyText()").toString();
		String content = page.getHtml().xpath("//div[@class='infos detail']/div[@class='content']").toString();
	
		if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
			logger.info("文章链接页{}，未找到指定的页面内容，跳过该页面",page.getUrl());
			page.setSkip(true);
			return;
		}
		
		//具体页面内容获取，具体字段拆分待完成
		spider.setTitle(title);
			
		// 处理时间和来源字段
		String dateStr = page.getHtml().xpath("//div[@class='title']/p[@class='time']/tidyText()").toString();			
		String dateStrTemp = dateStr.replaceFirst("发表于:", "").trim();
		// 设置时间
		if(!StringUtils.isEmpty(dateStrTemp)) {
			spider.setPublishDate(TimeUtils.getFormatDate(new SimpleDateFormat("yyyy年MM月dd日"), dateStrTemp));
		}
		
		spider.setPublishUrl(page.getUrl().toString());
		spider.setFromLink(FromSiteEnum.ZHONGGUOZIYAXUNHUANJINGJIWANG.getLink());
		spider.setFromSite(FromSiteEnum.ZHONGGUOZIYAXUNHUANJINGJIWANG.getName());
		spider.setSource(FromSiteEnum.ZHONGGUOZIYAXUNHUANJINGJIWANG.getName());
		
		spider.setSpiderCode(MD5Util.getMD5(spider.getPublishUrl()));   //根据特定的内容生成MD5，作为该条记录的id
		spider.setSpiderModule(pageSettings.getModule().getIndex());
		
		//获取内容中所有的链接
		List<String> urls =  page.getHtml().xpath("//div[@class='infos detail']/div[@class='content']").links().all();
		
		List<String> attachmentList = urls.stream().filter(p->p.matches(pageSettings.getAttachmentsUrlAdjustRegex())).collect(Collectors.toList());
		if(attachmentList !=null && attachmentList.size()>0) {	
			content = adjustRelativeAttachUrls(content, attachmentList,pageSettings);
		}
		
		//将一些图片链接也需要矫正(此处的list得到的是img标签)
		List<Element> imageUrlList = Selectors.xpath("//img/@src").selectElements(content);
		if(imageUrlList != null && imageUrlList.size() > 0) {
			List<String> imageSrcList = imageUrlList.stream()
					.map(e->{return Selectors.xpath("img/@src").select(e);})
					.collect(Collectors.toList());
			content = adjustRelativeImageUrls(content,imageSrcList,pageSettings);				
		}			
		spider.setContent(content);
		page.putField(CrawlConst.CRAWL_ITEM_KEY, spider);
		
	}

	
	/**
	 * 获取到的政策文章中的附件链接下载地址处理，全部替换成完整地址
	 * @param content
	 * @param attachmentList
	 * @param baseUrl 网站的基地址    拼接上相对链接地址=》绝对链接地址
	 * 内容中获取到的/attachments/806/download?locale=cn
	 * 要替换成的完整的http://ziya.tjjh.gov.cn/attachments/806/download?locale=cn
	 * 
	 * 内容中获取到的/system/attached_images/images/846/001_original.jpg?1560827790
	 * 要替换成完整的http://ziya.tjjh.gov.cn/system/attached_images/images/846/001_original.jpg?1560827790
	 */
	private String adjustRelativeAttachUrls(String content,List<String> urlList,PageSettings pageSettings) {
		if(content==null || "".equals(content) || urlList == null) {
			return "";
		}
		String returnStr = content;	
		try {
			//处理将content中的相对链接下载地址换成绝对地址
			for(String urlTemp : urlList) {
				if(urlTemp.matches(pageSettings.getAttachmentsUrlAdjustRegex())) {
					String[] attachIn =  urlTemp.split(pageSettings.getWebSiteListPrefix());
					String oldReplaceStr = attachIn[attachIn.length-1];
					// 处理问号的正则匹配，否则无法替换，因为正则不认识
					oldReplaceStr = oldReplaceStr.replaceFirst("\\?", "\\\\?"); 
					returnStr = returnStr.replaceAll(oldReplaceStr, urlTemp);
				}				
			}			
		}catch(Exception e) {
//			logger.error("替换附件下载链接地址时出错：信息为:",e);
		}
		return returnStr;
	}
	
	private String adjustRelativeImageUrls(String content,List<String> imageSrcList,PageSettings pageSettings) {
		if(content==null || "".equals(content) || imageSrcList == null || imageSrcList.size() == 0) {
			return "";
		}
		//完整地址类似如下的 http://zcydt.fzgg.tj.gov.cn/zcbjd/sjbmjd/ssww_199/201804/W020180402379608413246.jpg
		String returnStr = content;	
		for(String imageUrl : imageSrcList) {
			if(imageUrl.matches(pageSettings.getImageUrlAdjustRegex())) {				
				String newReplaceImageUrl = imageUrl;
				String newReplaceImageUrlAfter = newReplaceImageUrl.replaceFirst("/", pageSettings.getWebSiteListPrefix()+"/");	
				imageUrl = imageUrl.replaceFirst("\\?", "\\\\?"); 
				returnStr = returnStr.replaceAll(imageUrl, newReplaceImageUrlAfter);
			}
		}
		return returnStr;
	}

}
