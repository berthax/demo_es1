package com.troila.tjsmesp.spider.crawler.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.crawler.processor.base.AbstractPolicyPageProcessor;
import com.troila.tjsmesp.spider.crawler.processor.base.PageSettings;
import com.troila.tjsmesp.spider.crawler.service.NewsProcessorService;
import com.troila.tjsmesp.spider.crawler.site.SiteProcessorFactory;
import com.troila.tjsmesp.spider.crawler.site.ZiyaTjjhGovCnProcessor;
@Component
public class JinghaiIndustrialClustersNewsPageProcessor extends AbstractPolicyPageProcessor{

	/**
     * 子牙循环经济网，园区新闻详情正则
     */
    private static final String ARTICLE_URL = "http://ziya.tjjh.gov.cn/zhengwu/yuanquxinwen/(\\d+)(-(\\w+))*";
    
    /**
     *子牙循环经济网，园区新闻列表正则
     */    
    private static final String LIST_URL = "http://ziya.tjjh.gov.cn/zhengwu/yuanquxinwen\\?page=(\\d+)";
	
    /**
     * 子牙循环经济网网站基地址
     */
    private static final String CHANNELID = "http://ziya.tjjh.gov.cn";
    /**
     * 园区新闻需要矫正的附件链接地址正则
     */
    private static final String ATTACHMENTS_URL_ADJUST_REX = "http://ziya.tjjh.gov.cn/attachments/(\\d+)/download\\?locale=cn";

    /**
     * 园区新闻需要矫正的图片链接地址正则
     */
    private static final String IMAGE_URL_ADJUST_REX = "/system/attached_images/images/(\\d+)/([\\w,%,-])+_original.jpg\\?(\\d+)";
    
    @Autowired
    private NewsProcessorService newsProcessorService;
	
	
	
/*	public static void main(String[] args) {
	    //http://ziya.tjjh.gov.cn/system/attached_images/images/846/001_original.jpg?1560827790
//		http://ziya.tjjh.gov.cn/zhengwu/yuanquxinwen/3795-tian-jin-zi-ya-jing-ji-ji-zhu-kai-fa-qu-guan-che
		String str1 = "http://ziya.tjjh.gov.cn/zhengwu/yuanquxinwen/3795-tian-jin-zi-ya-jing-ji-ji-zhu-kai-fa-qu-guan-che";		
		String str3 = "http://ziya.tjjh.gov.cn/zhengwu/yuanquxinwen/3766-tian-jin-zi-ya-jing-ji-ji-zhu-kai-fa-qu-zhao-kai";
		String str2 = "http://ziya.tjjh.gov.cn/zhengwu/yuanquxinwen?page=1";
		System.out.println(str3.matches(ARTICLE_URL));
		System.out.println(str2.matches(LIST_URL));
	}*/



	@Override
	protected void configure(PageSettings pageSettings) {
		pageSettings.setSpiderProcess(SiteProcessorFactory.create(ZiyaTjjhGovCnProcessor.class))
			.setArticleUrlRegex(ARTICLE_URL)
			.setListUrlRegex(LIST_URL)
			.setProcessorService(newsProcessorService)
			.setAttachmentsUrlAdjustRegex(ATTACHMENTS_URL_ADJUST_REX)
			.setImageUrlAdjustRegex(IMAGE_URL_ADJUST_REX)
			.setModule(SpiderModuleEnum.JINGHAI_INDUSTRIAL_CLUSTERS_NEWS)
			.setWebSiteListPrefix(CHANNELID)
			.setDomain("http://ziya.tjjh.gov.cn");	
	}
}
