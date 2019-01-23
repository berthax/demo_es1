package com.troila.tjsmesp.spider.model.primary;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


//@Entity
//@Table(name="policy_inner")
@Component
public class Policy implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 6464890570918783247L;

	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)  //由于数据库主键是自己用MD5生成的，所以不需要设置自增的生成策略了，某则不匹配，一直提示字段id没有默认值
	protected String id;
	
	/**
	 * 文章标题
	 */
	protected String title;
	/**
	 * 文章内容
	 */
	protected String content;
	
	/**
	 * 发文单位
	 */
	protected String publishUnit;
	
	/**
	 * 发文字号
	 */
	protected String publishNo;
	
	/**
	 * 发文日期
	 */
	protected Date publishDate;
	
	/**
	 * 文章链接
	 */
	protected String publishUrl;
		
	/**
	 * 来源
	 */
	protected String source = "天津政策一点通";
	/**
	 * 所属分类
	 */
	protected String category;
	/**
	 * 是否已读
	 */
	protected Integer readFlag = 0;
	/**
	 * 创建时间
	 */
	protected Date createTime = new Date();
	/**
	 * 更新时间
	 */
	protected Date updateTime = new Date();
	/**
	 * 政策级别 0国家级 1市级 2区级
	 */
	protected int level = 0;
	/**
	 * 父类id
	 */
	protected String parentId;
	/**
	 * 逗号分隔的关键字，以后备用
	 */
	protected String keywords;
	
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
	public String getPublishUnit() {
		return publishUnit;
	}
	public void setPublishUnit(String publishUnit) {
		this.publishUnit = publishUnit;
	}	
	public Date getPublishDate() {
		return publishDate;
	}
	public String getPublishNo() {
		return publishNo;
	}
	public void setPublishNo(String publishNo) {
		this.publishNo = publishNo;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public String getPublishUrl() {
		return publishUrl;
	}
	public void setPublishUrl(String publishUrl) {
		this.publishUrl = publishUrl;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getReadFlag() {
		return readFlag;
	}
	public void setReadFlag(Integer readFlag) {
		this.readFlag = readFlag;
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
}
