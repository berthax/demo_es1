package com.troila.tjsmesp.spider.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 数据同步的相关配置
 * @author xuanguojing
 *
 */
@Component
@ConfigurationProperties(prefix="data.sync")
public class DataSyncSettings {
		
	/**
	 * 当同步到informix数据库时，默认的政策发布者
	 */
	private String defaultPublisher = "5d7f07d1d8ca4b66b7da5b8e7f191435";
	/**
	 * 同步新闻类信息时，使用的默认发布者的id，默认是使用admin_xinxi的用户发布
	 */
	private Integer defaultPublisherId = 140;
	/**
	 * 同步时，静海窗口平台用户id
	 */
	private Integer adminJhXunhuanId = 324;
	/**
	 * 初次同步数据时，同步数据的最大条目数
	 */
	private int firstMaxNumber = 5;
	/**
	 * 同步多少天之内的数据
	 */
	private int lastDays = 7;
	/**
	 * 新闻部分同步多少天之内的数据
	 */
	private int newsLastDays = 7;
	
	public String getDefaultPublisher() {
		return defaultPublisher;
	}
	public void setDefaultPublisher(String defaultPublisher) {
		this.defaultPublisher = defaultPublisher;
	}
	public int getFirstMaxNumber() {
		return firstMaxNumber;
	}
	public void setFirstMaxNumber(int firstMaxNumber) {
		this.firstMaxNumber = firstMaxNumber;
	}
	public int getLastDays() {
		return lastDays;
	}
	public void setLastDays(int lastDays) {
		this.lastDays = lastDays;
	}
	public Integer getDefaultPublisherId() {
		return defaultPublisherId;
	}
	public void setDefaultPublisherId(Integer defaultPublisherId) {
		this.defaultPublisherId = defaultPublisherId;
	}
	public int getNewsLastDays() {
		return newsLastDays;
	}
	public void setNewsLastDays(int newsLastDays) {
		this.newsLastDays = newsLastDays;
	}
	public Integer getAdminJhXunhuanId() {
		return adminJhXunhuanId;
	}
	public void setAdminJhXunhuanId(Integer adminJhXunhuanId) {
		this.adminJhXunhuanId = adminJhXunhuanId;
	}	
}
