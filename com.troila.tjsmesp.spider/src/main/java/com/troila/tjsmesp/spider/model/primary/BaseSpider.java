package com.troila.tjsmesp.spider.model.primary;

import java.util.Date;

import javax.persistence.MappedSuperclass;
/**
 * 	
 * @ClassName:  BaseSpider   
 * @Description:表明这是父类，可以将属性映射到子类中使用JPA生成表
 * @author: xgj
 * @date:   2019年6月18日 下午2:56:04   
 *
 */
@MappedSuperclass 
public class BaseSpider {
	
	protected String spiderCode;
	
	protected String title;
	
	protected String content;
	/**
	 * 来源，即具体的发文部门
	 */
	protected String source;
	/**
	 * 发文时间
	 */
	protected Date publishDate;	
	/**
	 * 发布文章的具体链接地址
	 */
	protected String publishUrl;
	/**
	 * 爬取模块记录，1最新政策原文，2政策解读，3产业资讯，4区域动态，5要闻焦点国家"，6要闻焦点部委，7要闻焦点天津
	 */
	protected Integer spiderModule;
	
	protected String fromSite;
	
	protected String fromLink;
	
	protected Date gmtCreate = new Date();
	
	protected Date gmtModified = new Date();

	public String getSpiderCode() {
		return spiderCode;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getSource() {
		return source;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public String getPublishUrl() {
		return publishUrl;
	}

	public Integer getSpiderModule() {
		return spiderModule;
	}

	public String getFromSite() {
		return fromSite;
	}

	public String getFromLink() {
		return fromLink;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setSpiderCode(String spiderCode) {
		this.spiderCode = spiderCode;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public void setPublishUrl(String publishUrl) {
		this.publishUrl = publishUrl;
	}

	public void setSpiderModule(Integer spiderModule) {
		this.spiderModule = spiderModule;
	}

	public void setFromSite(String fromSite) {
		this.fromSite = fromSite;
	}

	public void setFromLink(String fromLink) {
		this.fromLink = fromLink;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	
	
}
