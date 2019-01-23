package com.troila.tjsmesp.spider.model.primary;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
/**
 * 政策解读实体类
 * @author xuanguojing
 *
 */
//@Entity
//@Table(name="policy_reading")
//@TargetUrl("http://zcydt.fzgg.tj.gov.cn/zcbjd/sjbmjd/skw_201/201811/t20181115_51122.shtml")
//@TargetUrl("http://zcydt.fzgg.tj.gov.cn/zcbjd/(\\w+)/(\\w+)/(\\d+)/(\\w+).shtml")
//@HelpUrl("http://zcydt.fzgg.tj.gov.cn/zcbjd/index.shtml")
//@HelpUrl("http://zcydt.fzgg.tj.gov.cn/zcbjd/index*.shtml")
//@HelpUrl("http://zcydt.fzgg.tj.gov.cn/zcbjd/index_1.shtml")
@Component
public class PolicyReading {
	
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id = UUID.randomUUID().toString();
	
	/**
	 * 解读政策的标题
	 */
	@ExtractBy(value = "//div[@class='jiedu_nr_2']/text()")
	private String title;
	
	/**
	 * 解读政策的内容
	 */
	@ExtractBy(value = "//div[@class='jiedu_nr_7']/tidyText()")
	private String content;
	
//	@ExtractBy(value="//div[@class='jiedu_nr_5']/tidyText()")
	private String source;
	
	@Transient
	@ExtractBy(value="//div[@class='jiedu_nr_5']/tidyText()")
	private String tempStr;
	
	@Transient
	@ExtractBy(value="//div[@class='page']/a[text='尾页']/href")
	private String lastPageUrls;
	
	private Date publishDate;
	
	private Date createTime = new Date();

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "PolicyReading [title=" + title + ", content=" + content + ", source=" + source + ", publishDate="
				+ publishDate + "]";
	}
}
