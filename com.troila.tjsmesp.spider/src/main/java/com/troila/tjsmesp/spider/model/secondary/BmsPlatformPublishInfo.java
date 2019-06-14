package com.troila.tjsmesp.spider.model.secondary;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bms_platform_publish_info")
public class BmsPlatformPublishInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 标题
	 */
    private String publishTitle;					
    /**
     * 栏目类型 1：新闻动态 2：政策法规 3：通知公告  4: 扶持政策（区县窗口）9：其他
     */
    private String publishType;	
    /**
     * 所属区域01：市级（中心）02：区县窗口  03：产业集聚   99：其他
     */
    private String areaType;   
    /**
     * 内容
     */
    private String publishContent;					
    /**
     * 发布时间
     */
    private Date publishTime;	
    /**
     * 发布人id,gov_user的id
     */
    private Integer publisherId;
   /**
    * 摘自哪里，爬取的要闻焦点-》天津模块 来源设置为天津政务网
    */
    private String publishFrom;						
    private String platformType;					//所属平台：120000中枢平台  120101和平窗口平台……
    private String industrialClustersType;        //产业集聚类别
    private String responsibilityWindowType;		//责任窗口类别市中小局，市发改委……
    private String noticeType;						//通知通告的类别（十大服务类别）
    private String isHot;							//是否热点,0:否1:是',
    private String isPublished;					//是否发布,0:否1:是
    private String isTop;							//是否置顶,0:否1:是
    private String clickTimes;						//点击次数
    private String annotation;						//政策(新闻)解读
    private String annotator;						//解读者
    private Date annotateDate;						//解读发表时间
    private String newsPic;						//新闻图片
    private Date updateStamp;						//时间戳
    private Date topTime;                          //置顶时间
    private String districtCharacteristicType;
    private String isPublishCenterPlatform;
    private String isPublishCountyPlatform;
    private String declareType;
    private String isBeginDeclare;
    private Date declareStartTime;
    private Date declareEndTime;
    private Date recommendStartTime;
    private Date recommendEndTime;
    private String serviceType;
    private Date createStamp;
    private String filePath;
    private String publisherType;
    private String isPublishIndustrial;
    /**
     * 代表要闻焦点中的3个模块  1是国家 2是部委 3是天津
     */
    private String focusType;
    private String publishOrder;
    private String isVerify;
    private Date verifyTime;
    private String isMark;
    private String isSubmit;        //是否提交
    private String isOut;            //是否永久失效
    private String isPublishedSon;   //子发布
    private String isTopQx;          //是否区县置顶,0:否1:是
    private String isTopCy;          //是否chanye置顶,0:否1:是
    private Date topTimeQx;
    private Date topTimeCy;
    private Date transTime;
    private String orgLink;
    private String nopassReason;
    
	public Integer getId() {
		return id;
	}
	public String getPublishTitle() {
		return publishTitle;
	}
	public String getPublishType() {
		return publishType;
	}
	public String getAreaType() {
		return areaType;
	}
	public String getPublishContent() {
		return publishContent;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public Integer getPublisherId() {
		return publisherId;
	}
	public String getPublishFrom() {
		return publishFrom;
	}
	public String getPlatformType() {
		return platformType;
	}
	public String getIndustrialClustersType() {
		return industrialClustersType;
	}
	public String getResponsibilityWindowType() {
		return responsibilityWindowType;
	}
	public String getNoticeType() {
		return noticeType;
	}
	public String getIsHot() {
		return isHot;
	}
	public String getIsPublished() {
		return isPublished;
	}
	public String getIsTop() {
		return isTop;
	}
	public String getClickTimes() {
		return clickTimes;
	}
	public String getAnnotation() {
		return annotation;
	}
	public String getAnnotator() {
		return annotator;
	}
	public Date getAnnotateDate() {
		return annotateDate;
	}
	public String getNewsPic() {
		return newsPic;
	}
	public Date getUpdateStamp() {
		return updateStamp;
	}
	public Date getTopTime() {
		return topTime;
	}
	public String getDistrictCharacteristicType() {
		return districtCharacteristicType;
	}
	public String getIsPublishCenterPlatform() {
		return isPublishCenterPlatform;
	}
	public String getIsPublishCountyPlatform() {
		return isPublishCountyPlatform;
	}
	public String getDeclareType() {
		return declareType;
	}
	public String getIsBeginDeclare() {
		return isBeginDeclare;
	}
	public Date getDeclareStartTime() {
		return declareStartTime;
	}
	public Date getDeclareEndTime() {
		return declareEndTime;
	}
	public Date getRecommendStartTime() {
		return recommendStartTime;
	}
	public Date getRecommendEndTime() {
		return recommendEndTime;
	}
	public String getServiceType() {
		return serviceType;
	}
	public Date getCreateStamp() {
		return createStamp;
	}
	public String getFilePath() {
		return filePath;
	}
	public String getPublisherType() {
		return publisherType;
	}
	public String getIsPublishIndustrial() {
		return isPublishIndustrial;
	}
	public String getFocusType() {
		return focusType;
	}
	public String getPublishOrder() {
		return publishOrder;
	}
	public String getIsVerify() {
		return isVerify;
	}
	public Date getVerifyTime() {
		return verifyTime;
	}
	public String getIsMark() {
		return isMark;
	}
	public String getIsSubmit() {
		return isSubmit;
	}
	public String getIsOut() {
		return isOut;
	}
	public String getIsPublishedSon() {
		return isPublishedSon;
	}
	public String getIsTopQx() {
		return isTopQx;
	}
	public String getIsTopCy() {
		return isTopCy;
	}
	public Date getTopTimeQx() {
		return topTimeQx;
	}
	public Date getTopTimeCy() {
		return topTimeCy;
	}
	public Date getTransTime() {
		return transTime;
	}
	public String getOrgLink() {
		return orgLink;
	}
	public String getNopassReason() {
		return nopassReason;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setPublishTitle(String publishTitle) {
		this.publishTitle = publishTitle;
	}
	public void setPublishType(String publishType) {
		this.publishType = publishType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public void setPublishContent(String publishContent) {
		this.publishContent = publishContent;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}
	public void setPublishFrom(String publishFrom) {
		this.publishFrom = publishFrom;
	}
	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}
	public void setIndustrialClustersType(String industrialClustersType) {
		this.industrialClustersType = industrialClustersType;
	}
	public void setResponsibilityWindowType(String responsibilityWindowType) {
		this.responsibilityWindowType = responsibilityWindowType;
	}
	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}
	public void setIsPublished(String isPublished) {
		this.isPublished = isPublished;
	}
	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}
	public void setClickTimes(String clickTimes) {
		this.clickTimes = clickTimes;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public void setAnnotator(String annotator) {
		this.annotator = annotator;
	}
	public void setAnnotateDate(Date annotateDate) {
		this.annotateDate = annotateDate;
	}
	public void setNewsPic(String newsPic) {
		this.newsPic = newsPic;
	}
	public void setUpdateStamp(Date updateStamp) {
		this.updateStamp = updateStamp;
	}
	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}
	public void setDistrictCharacteristicType(String districtCharacteristicType) {
		this.districtCharacteristicType = districtCharacteristicType;
	}
	public void setIsPublishCenterPlatform(String isPublishCenterPlatform) {
		this.isPublishCenterPlatform = isPublishCenterPlatform;
	}
	public void setIsPublishCountyPlatform(String isPublishCountyPlatform) {
		this.isPublishCountyPlatform = isPublishCountyPlatform;
	}
	public void setDeclareType(String declareType) {
		this.declareType = declareType;
	}
	public void setIsBeginDeclare(String isBeginDeclare) {
		this.isBeginDeclare = isBeginDeclare;
	}
	public void setDeclareStartTime(Date declareStartTime) {
		this.declareStartTime = declareStartTime;
	}
	public void setDeclareEndTime(Date declareEndTime) {
		this.declareEndTime = declareEndTime;
	}
	public void setRecommendStartTime(Date recommendStartTime) {
		this.recommendStartTime = recommendStartTime;
	}
	public void setRecommendEndTime(Date recommendEndTime) {
		this.recommendEndTime = recommendEndTime;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public void setCreateStamp(Date createStamp) {
		this.createStamp = createStamp;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public void setPublisherType(String publisherType) {
		this.publisherType = publisherType;
	}
	public void setIsPublishIndustrial(String isPublishIndustrial) {
		this.isPublishIndustrial = isPublishIndustrial;
	}
	public void setFocusType(String focusType) {
		this.focusType = focusType;
	}
	public void setPublishOrder(String publishOrder) {
		this.publishOrder = publishOrder;
	}
	public void setIsVerify(String isVerify) {
		this.isVerify = isVerify;
	}
	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}
	public void setIsMark(String isMark) {
		this.isMark = isMark;
	}
	public void setIsSubmit(String isSubmit) {
		this.isSubmit = isSubmit;
	}
	public void setIsOut(String isOut) {
		this.isOut = isOut;
	}
	public void setIsPublishedSon(String isPublishedSon) {
		this.isPublishedSon = isPublishedSon;
	}
	public void setIsTopQx(String isTopQx) {
		this.isTopQx = isTopQx;
	}
	public void setIsTopCy(String isTopCy) {
		this.isTopCy = isTopCy;
	}
	public void setTopTimeQx(Date topTimeQx) {
		this.topTimeQx = topTimeQx;
	}
	public void setTopTimeCy(Date topTimeCy) {
		this.topTimeCy = topTimeCy;
	}
	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}
	public void setOrgLink(String orgLink) {
		this.orgLink = orgLink;
	}
	public void setNopassReason(String nopassReason) {
		this.nopassReason = nopassReason;
	}
    
    
    
    
}
