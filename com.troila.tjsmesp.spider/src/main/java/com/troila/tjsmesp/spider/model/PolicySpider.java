package com.troila.tjsmesp.spider.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="sme_policy_spider")
public class PolicySpider {
	
	@Id
	private String id;	
	/**
	 * 政策的标题
	 */
	private String title;	
	/**
	 * 政策的内容
	 */
//	@Lob  
	@Basic(fetch=FetchType.EAGER)    //即使获取，默认的存取策略，与其对应的是FetchType.LAZY懒加载
	@Column(name="content") 
	private String content;
	/**
	 * 除去各种Html标签的内容
	 */
	private String stripContent;
	
	/**
	 * 政策来源
	 */
	private String source;
	
	/**
	 * 发布时间
	 */
	private Date publishDate;
	
	/**
	 * 发布单位
	 */
	private String publishUnit;
	/**
	 * 发布文章的链接
	 */
	private String publishUrl;
	
	/**
	 * 转载网站
	 */
	private String fromSite;
	/**
	 * 转载地址
	 */
	private String fromLink;
	/**
	 * 转载时间
	 */
	private Date forwardTime;
	/**
	 * 数据库记录的创建时间
	 */
	private Date createTime = new Date();
	/**
	 * 数据库记录的更新时间
	 */
	private Date updateTime = new Date();
	
	/**
	 * 发文字号
	 */
	private String publishNo;
	/**
	 * 发文种类
	 */
	private String category;
	/**
	 * 附件情况
	 */
	private String attachment;
	/**
	 * 爬取模块记录，1最新政策原文，2政策解读，3行业热点，4地方政府，5部位动态，6焦点新闻，7新闻各区
	 */
	private Integer spiderModule;
	
	/**
	 * 如果为政策原文的，且有对应的解读文章的，此处填写政策解读的title
	 */
	private String articleReading;
	
	/**
	 * 政策的级别
	 */
	private Integer policyLevel;
	/**
	 * 父类id 政策原文为-1，解读为对应的原文的id
	 */
	private String parentId;
	
	@Transient
	private String tempStr;
	
	public String getTempStr() {
		return tempStr;
	}
	public void setTempStr(String tempStr) {
		this.tempStr = tempStr;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStripContent() {
		return stripContent;
	}
	public void setStripContent(String stripContent) {
		this.stripContent = stripContent;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public String getPublishUnit() {
		return publishUnit;
	}
	public void setPublishUnit(String publishUnit) {
		this.publishUnit = publishUnit;
	}
	public String getPublishUrl() {
		return publishUrl;
	}
	public void setPublishUrl(String publishUrl) {
		this.publishUrl = publishUrl;
	}
	public String getFromSite() {
		return fromSite;
	}
	public void setFromSite(String fromSite) {
		this.fromSite = fromSite;
	}
	public String getFromLink() {
		return fromLink;
	}
	public void setFromLink(String fromLink) {
		this.fromLink = fromLink;
	}
	public Date getForwardTime() {
		return forwardTime;
	}
	public void setForwardTime(Date forwardTime) {
		this.forwardTime = forwardTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getPublishNo() {
		return publishNo;
	}
	public void setPublishNo(String publishNo) {
		this.publishNo = publishNo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public Integer getSpiderModule() {
		return spiderModule;
	}
	public void setSpiderModule(Integer spiderModule) {
		this.spiderModule = spiderModule;
	}
	public String getArticleReading() {
		return articleReading;
	}
	public void setArticleReading(String articleReading) {
		this.articleReading = articleReading;
	}
	public Integer getPolicyLevel() {
		return policyLevel;
	}
	public void setPolicyLevel(Integer policyLevel) {
		this.policyLevel = policyLevel;
	}	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 选取几个能唯一确定一条政策记录的字段，用于生成MD5字符串作为该记录的Id存储
	 */
	@Override
	public String toString() {
		return "PolicySpider [title=" + title + ", content=" + content + ", source=" + source + ", publishDate="
				+ publishDate + ", publishUnit=" + publishUnit + ", publishUrl=" + publishUrl + ", publishNo="
				+ publishNo + "]";
	}
}
