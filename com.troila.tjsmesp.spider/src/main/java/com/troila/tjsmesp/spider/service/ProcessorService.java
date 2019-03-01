package com.troila.tjsmesp.spider.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.constant.UrlRegexConst;
import com.troila.tjsmesp.spider.model.primary.PolicySpider;
import com.troila.tjsmesp.spider.repository.mysql.PolicySpiderRepositoryMysql;
/**
 * 用于各种爬虫过程中字段的处理等
 * @author xuanguojing
 *
 */
@Component("processorService")
public class ProcessorService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private PolicySpiderRepositoryMysql policySpiderRepositoryMysql;
	 
 
	/**
	 * 获取之前已经下载过的文章链接，不再重复下载(以数据库中当前的存储为准)
	 * @return
	 */
	public List<String> getCrawledUrls(SpiderModuleEnum spiderMoudleEnum){
		try {			
			List<PolicySpider> list = policySpiderRepositoryMysql.findBySpiderModule(spiderMoudleEnum.getIndex());
			List<String> listUrls = list.stream().map(e->{return ((PolicySpider)e).getPublishUrl();}).collect(Collectors.toList());
			return listUrls;
		} catch (Exception e) {
			logger.error("从本地数据库中获取已经下载过的所有链接出错，出错信息：",e);
			return null;
		}
	}
	
	/**
	 * 获取到的政策文章中的链接下载地址处理，全部替换成完整地址
	 * @param content
	 * @param attachmentList
	 */
	public String replaceAttachmentsUrlForContent(String content,List<String> attachmentList) {
		if(content==null || "".equals(content) || attachmentList== null) {
			return "";
		}
		String returnStr = content;	
		try {
			//处理将content中的相对链接下载地址换成绝对地址
			for(String attachment : attachmentList) {
				if(attachment.matches(UrlRegexConst.ATTACHMENT_URL_ADJUST_REX)) {
					String[] attachIn =  attachment.split("/");
					String oldReplaceStr = attachIn[attachIn.length-1];
					returnStr = returnStr.replaceAll("\\./"+oldReplaceStr, attachment);
				}
			}			
		}catch(Exception e) {
			logger.error("替换附件下载链接地址时出错：信息为:",e);
		}
		return returnStr;
	}
	
	/**
	 * 获取到的政策文章中对应的政策解读链接，全部替换成完整地址
	 * @param content
	 * @param attachmentList
	 */
	public String replaceArticelReadingUrlForContent(String content,List<String> articelReadingList) {
		if(content==null || "".equals(content) || articelReadingList == null) {
			return "";
		}
		String returnStr = content;	
		String replaceRex = "(\\.\\./)*((\\w+)/)*";
		//<a href="../../../../zcbjd/sjbmjd/ssww_199/201807/t20180726_49716.shtml" target="_blank">市商务委 市财政局关于印发天津市2018年度市外经贸发展资金鼓励企业开展技术研发和创新项目申报指南政策解读</a>
		//处理将content中的相对链接下载地址换成绝对地址
		for(String articleReading : articelReadingList) {
			if(articleReading.matches(UrlRegexConst.POLICY_READING_ARTICLE_URL_REX)) {
				String[] readingIn =  articleReading.split("/");
				String oldReplaceStr = readingIn[readingIn.length-1];
				returnStr = returnStr.replaceAll(replaceRex+oldReplaceStr, articleReading);
			}
		}
		return returnStr;
	}
}
