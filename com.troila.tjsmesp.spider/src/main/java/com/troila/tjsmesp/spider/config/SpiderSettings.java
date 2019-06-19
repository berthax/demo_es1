package com.troila.tjsmesp.spider.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 爬虫的相关配置
 */
@Component
@ConfigurationProperties(prefix="spider.policy")
public class SpiderSettings {
		
	/**
	 * 进行爬取时，驱动池中的驱动的最大数目	
	 */
	private int webPoolMaxNum = 3;
	/**
	 * 进行爬取时，启动的线程个数
	 */
	private int threadNumber = 2;
	/**
	 * 天津政策一点通最新政策爬取的开始地址
	 */
	//private String newestStartUrl = "http://zcydt.fzgg.tj.gov.cn/gllm/zxzc/index.shtml";
	/**
	 * 天津政策一点通政策解读爬取的开始地址
	 */
	//private String readingStartUrl = "http://zcydt.fzgg.tj.gov.cn/zcbjd/index.shtml";
	/**
	 * 要闻焦点-》天津模块爬取的开始地址（对应天津政务网）
	 */
//	private String newsFocusTianjinStartUrl = "http://www.tj.gov.cn/xw/qx1/index.html";
//	
//	private String newsRegionalDynamicStartUrl = "http://sousuo.gov.cn/column/30902/0.htm";
//	
//	private String newsFocusBuweiStartUrl = "http://sme.miit.gov.cn/cms/news/100000/0000000224/0000000224.shtml";
//	
//	private String newsFocusGuojiaStartUrl = "http://sme.miit.gov.cn/cms/news/100000/0000000033/0000000033.shtml";
//	
//	private String newsIndustryInfoStartUrl = "http://sme.miit.gov.cn/cms/news/100000/0000000071/0000000071.shtml";
	
	public SpiderSettings() {
		System.out.println("正在初始化该实例……");
	}
	public int getWebPoolMaxNum() {
		return webPoolMaxNum;
	}
	public void setWebPoolMaxNum(int webPoolMaxNum) {
		this.webPoolMaxNum = webPoolMaxNum;
	}
	public int getThreadNumber() {
		return threadNumber;
	}
	public void setThreadNumber(int threadNumber) {
		this.threadNumber = threadNumber;
	}
/*	public String getNewestStartUrl() {
		return newestStartUrl;
	}
	public void setNewestStartUrl(String newestStartUrl) {
		this.newestStartUrl = newestStartUrl;
	}
	public String getReadingStartUrl() {
		return readingStartUrl;
	}
	public void setReadingStartUrl(String readingStartUrl) {
		this.readingStartUrl = readingStartUrl;
	}
	public String getNewsFocusTianjinStartUrl() {
		return newsFocusTianjinStartUrl;
	}
	public void setNewsFocusTianjinStartUrl(String newsFocusTianjinStartUrl) {
		this.newsFocusTianjinStartUrl = newsFocusTianjinStartUrl;
	}
	public String getNewsRegionalDynamicStartUrl() {
		return newsRegionalDynamicStartUrl;
	}
	public void setNewsRegionalDynamicStartUrl(String newsRegionalDynamicStartUrl) {
		this.newsRegionalDynamicStartUrl = newsRegionalDynamicStartUrl;
	}
	public String getNewsFocusBuweiStartUrl() {
		return newsFocusBuweiStartUrl;
	}
	public String getNewsFocusGuojiaStartUrl() {
		return newsFocusGuojiaStartUrl;
	}
	public String getNewsIndustryInfoStartUrl() {
		return newsIndustryInfoStartUrl;
	}
	public void setNewsFocusBuweiStartUrl(String newsFocusBuweiStartUrl) {
		this.newsFocusBuweiStartUrl = newsFocusBuweiStartUrl;
	}
	public void setNewsFocusGuojiaStartUrl(String newsFocusGuojiaStartUrl) {
		this.newsFocusGuojiaStartUrl = newsFocusGuojiaStartUrl;
	}
	public void setNewsIndustryInfoStartUrl(String newsIndustryInfoStartUrl) {
		this.newsIndustryInfoStartUrl = newsIndustryInfoStartUrl;
	}*/
}
