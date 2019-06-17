package com.troila.tjsmesp.spider.model.primary;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sme_news_spider")
public class NewsSpider {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String newsCode;
	
	private String title;
	
	private String content;
	/**
	 * 来源，即具体的发文部门
	 */
	private String source;
	/**
	 * 发文时间
	 */
	private Date publishDate;	
	/**
	 * 发布文章的具体链接地址
	 */
	private String publishUrl;
	/**
	 * 爬取模块记录，1最新政策原文，2政策解读，3产业资讯，4区域动态，5要闻焦点国家"，6要闻焦点部委，7要闻焦点天津
	 */
	private Integer spiderModule;
	
	private String fromSite;
	
	private String fromLink;
	
	private Date gmtCreate = new Date();
	
	private Date gmtModified = new Date();

	public Integer getId() {
		return id;
	}

	public String getNewsCode() {
		return newsCode;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNewsCode(String newsCode) {
		this.newsCode = newsCode;
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
