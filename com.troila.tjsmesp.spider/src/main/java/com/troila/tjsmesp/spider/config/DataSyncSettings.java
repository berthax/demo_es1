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
	 * 初次同步数据时，同步数据的最大条目数
	 */
	private int firstMaxNumber = 5;
	/**
	 * 同步多少天之内的数据
	 */
	private int lastDays = 7;
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
}
