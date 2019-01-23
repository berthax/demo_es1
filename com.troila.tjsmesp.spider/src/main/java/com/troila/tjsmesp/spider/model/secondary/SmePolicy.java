package com.troila.tjsmesp.spider.model.secondary;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sme_policy")
public class SmePolicy {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  //采用数据库ID自增长的方式来自增主键字段
	private int id;
	
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 政策大类，0政策原文，1政策摘编，2政策解读，若没有特殊指定，都是政策原文
	 */
	private int type = -1;
	/**
	 * 类别  创业创新，资金扶持，金融税收，产业指导，综合政策（同步过来的暂时默认综合政策）
	 */
	private String category = "综合政策"; 
	/**
	 * 政策摘要
	 */
	private String policyAbstract;
	/**
	 * 去除HTML标签的政策内容
	 */
	private String stripedContent;
	/**
	 * 政策内容
	 */
	private String content;
	/**
	 * 发布者id  5d7f07d1d8ca4b66b7da5b8e7f191435
	 */
	private String publisher;
	/**
	 * 发布类型，目前默认是platform
	 */
	private String publishType = "platform";
	/**
	 * 发布时间
	 */
	private long publishDate;
	private Date gmtStart;
	private Date gmtEnd;
	private Date gmtCreate;
	private Date gmtModify;
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 扶持行业
	 */
	private String industry = "ALL";
	/**
	 * 适用范围地区
	 */
	private String area = "全国";
	/**
	 * ???
	 */
	private int parentId = -1;
	/**
	 * 附件情况
	 */
	private String attachments;
	
	private int priority = 0;
	/**
	 * 状态  0待审核，1审核通过，2审核拒绝，.3撤销，99草稿
	 */
	private int status;
	/**
	 * 发文字号
	 */
	private String referenceNumber;
	/**
	 * 政策被拒绝或取消时，存储着原因
	 */
	private String reviewMessage;
	/**
	 * 关键词
	 */
	private String keywords;
	/**
	 * 核批人
	 */
	private String supervisor;
	/**
	 * 联系人
	 */
	private String contact;
	
	private String url;
	
	private String logo;
	
	private int seq;
	/**
	 * 国家级0，天津市1，区县2
	 */
	private int policyLevel = 2;
	/**
	 * 转载网站
	 */
	private String fromSite;
	/**
	 * 转载连接
	 */
	private String fromLink;
	
	private Date gmtForward;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPolicyAbstract() {
		return policyAbstract;
	}

	public void setPolicyAbstract(String policyAbstract) {
		this.policyAbstract = policyAbstract;
	}

	public String getStripedContent() {
		return stripedContent;
	}

	public void setStripedContent(String stripedContent) {
		this.stripedContent = stripedContent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishType() {
		return publishType;
	}

	public void setPublishType(String publishType) {
		this.publishType = publishType;
	}

	public long getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(long publishDate) {
		this.publishDate = publishDate;
	}

	public Date getGmtStart() {
		return gmtStart;
	}

	public void setGmtStart(Date gmtStart) {
		this.gmtStart = gmtStart;
	}

	public Date getGmtEnd() {
		return gmtEnd;
	}

	public void setGmtEnd(Date gmtEnd) {
		this.gmtEnd = gmtEnd;
	}
	
	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModify() {
		return gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getReviewMessage() {
		return reviewMessage;
	}

	public void setReviewMessage(String reviewMessage) {
		this.reviewMessage = reviewMessage;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getPolicyLevel() {
		return policyLevel;
	}

	public void setPolicyLevel(int policyLevel) {
		this.policyLevel = policyLevel;
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

	public Date getGmtForward() {
		return gmtForward;
	}

	public void setGmtForward(Date gmtForward) {
		this.gmtForward = gmtForward;
	}
}
