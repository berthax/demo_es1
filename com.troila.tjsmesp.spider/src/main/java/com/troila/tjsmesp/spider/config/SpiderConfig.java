package com.troila.tjsmesp.spider.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class SpiderConfig {
	
	//当同步到informix数据库时，默认的政策发布者
	@Value("${data.sync.default.publisher}")
	private String defaultPublisher;
	
	//初次同步数据时，同步数据的最大条目数
	@Value("${data.sync.first.max.number}")
	private int syncFirstMaxNumber;
	
	//进行爬取时，启动的线程个数
	@Value("${spider.thread.number}")
	private int spiderThreadNumber;
	
	//天津政策一点通最新政策爬取的开始地址
	@Value("${spider.policy.newest.start-url}")
	private String policyNewestStartUrl = "http://zcydt.fzgg.tj.gov.cn/gllm/zxzc/index.shtml";
	
	//天津政策一点通政策解读爬取的开始地址
	@Value("${spider.policy.reading.start-url}")
	private String policyReadingStartUrl;

	public String getDefaultPublisher() {
		return defaultPublisher;
	}

	public void setDefaultPublisher(String defaultPublisher) {
		this.defaultPublisher = defaultPublisher;
	}

	public int getSyncFirstMaxNumber() {
		return syncFirstMaxNumber;
	}

	public void setSyncFirstMaxNumber(int syncFirstMaxNumber) {
		this.syncFirstMaxNumber = syncFirstMaxNumber;
	}

	public int getSpiderThreadNumber() {
		return spiderThreadNumber;
	}

	public void setSpiderThreadNumber(int spiderThreadNumber) {
		this.spiderThreadNumber = spiderThreadNumber;
	}

	public String getPolicyNewestStartUrl() {
		return policyNewestStartUrl;
	}

	public void setPolicyNewestStartUrl(String policyNewestStartUrl) {
		this.policyNewestStartUrl = policyNewestStartUrl;
	}

	public String getPolicyReadingStartUrl() {
		return policyReadingStartUrl;
	}

	public void setPolicyReadingStartUrl(String policyReadingStartUrl) {
		this.policyReadingStartUrl = policyReadingStartUrl;
	}
}
