package com.troila.tjsmesp.spider.constant;

public abstract class SpiderStartUrlConst {
	
	/**
	 * 天津政策一点通最新政策爬取的开始地址
	 */
	public static final String NEWEST_START_URL = "http://zcydt.fzgg.tj.gov.cn/gllm/zxzc/index.shtml";
	/**
	 * 天津政策一点通政策解读爬取的开始地址
	 */
	public static final String	READING_START_URL = "http://zcydt.fzgg.tj.gov.cn/zcbjd/index.shtml";
	/**
	 * 区域动态模块的开始地址
	 */
//	public static final String NEWS_REGION_DYNAMIC_START_URL = "http://sousuo.gov.cn/column/30902/0.htm";
	public static final String NEWS_REGION_DYNAMIC_START_URL = "http://sme.miit.gov.cn/cms/news/100000/0000000239/0000000239.shtml";
	/**
	 * 要闻焦点-》国家模块的开始地址		
	 */
	public static final String NEWS_FOCUS_GUOJIA_START_URL = "http://sme.miit.gov.cn/cms/news/100000/0000000033/0000000033.shtml";
	/**
	 * 要闻焦点-》部委模块的开始地址
	 */
	public static final String NEWS_FOCUS_BUWEI_START_URL = "http://sme.miit.gov.cn/cms/news/100000/0000000224/0000000224.shtml";
	/**
	 * 要闻焦点-》天津模块爬取的开始地址（对应天津政务网）
	 */
	public static final String NEWS_FOCUS_TIANJIN_START_URL = "http://www.tj.gov.cn/xw/qx1/index.html";
	/**
	 * 产业资讯模块的开始地址
	 */
	public static final String NEWS_INDUSTRY_INFO_START_URL = "http://sme.miit.gov.cn/cms/news/100000/0000000071/0000000071.shtml";
	/**
	 * 静海产业集聚，子牙循环经济网，园区新闻
	 */
	public static final String 	JINGHAI_INDUSTRIAL_CLUSTERS_NEWS = "http://ziya.tjjh.gov.cn/zhengwu/yuanquxinwen?page=1";	
	/**
	 * 静海产业集聚，子牙循环经济网，园区公告
	 */
	public static final String 	JINGHAI_INDUSTRIAL_CLUSTERS_NOTICE = "http://ziya.tjjh.gov.cn/zhengwu/yuanqugonggao?page=1";
}
