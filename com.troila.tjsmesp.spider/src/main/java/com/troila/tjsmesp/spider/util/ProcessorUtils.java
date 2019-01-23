package com.troila.tjsmesp.spider.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于各种爬虫过程中字段的处理等
 * @author xuanguojing
 *
 */
public class ProcessorUtils {
	private static final Logger logger = LoggerFactory.getLogger(ProcessorUtils.class);
	
	private static final String ATTACHMENT_URL_REX = "http://zcydt.fzgg.tj.gov.cn/zcb/(\\w+)/((\\w+)/)*(\\d+)/(\\w+)\\.(doc|docx|xlsx|xls|pdf|txt)";
	
	private static final String ARTICLE_READING_URL_REX = "http://zcydt\\.fzgg\\.tj\\.gov\\.cn/zcbjd/(\\w+)/((\\w+)/)*(\\d+)/(\\w+)\\.shtml";
	/**
	 * 获取到的政策文章中的链接下载地址处理，全部替换成完整地址
	 * @param content
	 * @param attachmentList
	 */
	public static String replaceAttachmentsUrlForContent(String content,List<String> attachmentList) {
		if(content==null || "".equals(content) || attachmentList== null) {
			return "";
		}
		String returnStr = content;	
		try {
			//处理将content中的相对链接下载地址换成绝对地址
			for(String attachment : attachmentList) {
				if(attachment.matches(ATTACHMENT_URL_REX)) {
					String[] attachIn =  attachment.split("/");
					String oldReplaceStr = attachIn[attachIn.length-1];
					returnStr = returnStr.replaceAll("\\./"+oldReplaceStr, attachment);
				}
			}			
		}catch(Exception e) {
			logger.error("替换附件下载链接地址时出错：信心为:",e);
		}
		return returnStr;
	}
	
	/**
	 * 获取到的政策文章中对应的政策解读链接，全部替换成完整地址
	 * @param content
	 * @param attachmentList
	 */
	public static String replaceArticelReadingUrlForContent(String content,List<String> articelReadingList) {
		if(content==null || "".equals(content) || articelReadingList == null) {
			return "";
		}
		String returnStr = content;	
		String replaceRex = "(\\.\\./)*((\\w+)/)*";
		//<a href="../../../../zcbjd/sjbmjd/ssww_199/201807/t20180726_49716.shtml" target="_blank">市商务委 市财政局关于印发天津市2018年度市外经贸发展资金鼓励企业开展技术研发和创新项目申报指南政策解读</a>
		//处理将content中的相对链接下载地址换成绝对地址
		for(String articleReading : articelReadingList) {
			if(articleReading.matches(ARTICLE_READING_URL_REX)) {
				String[] readingIn =  articleReading.split("/");
				String oldReplaceStr = readingIn[readingIn.length-1];
				returnStr = returnStr.replaceAll(replaceRex+oldReplaceStr, articleReading);
			}
		}
		return returnStr;
	}
}
