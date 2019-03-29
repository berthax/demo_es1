package com.troila.tjsmesp.spider.constant;
/**
 * 爬取过程中用到的政策常量
 * @author xuanguojing
 *
 */
public class UrlRegexConst {
			
	/**
	 * 附件链接正则
	 */
    public static final String ATTACHMENT_URL_REX = "(http://hd.chinatax.gov.cn/guoshui/action/ShowAppend.do\\?id=\\d+)|"
    		+ "(http://zcydt.fzgg.tj.gov.cn/(zcb|zcbjd)/(\\w+)/((\\w+)/)*(\\d+)/(\\w+)\\.(doc|docx|xlsx|xls|pdf|txt|wmv|png|jpg))";	
    
    	
    /**
     * 附件链接地址需要矫正为完整链接的正则（获取到的文章内容中为相对链接地址，要校正为完整链接地址）
     */
    public static final String ATTACHMENT_URL_ADJUST_REX = "http://zcydt.fzgg.tj.gov.cn/(zcb|zcbjd)/(\\w+)/((\\w+)/)*(\\d+)/(\\w+)\\.(doc|docx|xlsx|xls|pdf|txt|wmv|png|jpg)";
    /**
     * 政策解读文章详情页的链接地址正则
     */
    public static final String POLICY_READING_ARTICLE_URL_REX = "http://zcydt\\.fzgg\\.tj\\.gov\\.cn/zcbjd/(\\w+)/((\\w+)/)*(\\d+)/(\\w+)\\.shtml";
    
    /**
     * 属于文章自己页面的图片，需要矫正为完整地址的正则（是相对链接地址的，需要矫正为成完整地址）
     */
    public static final String IMAGE_URL_ADJUST_REX = "(\\./)(\\w+)\\.(png|jpg|jpeg|jpe|gif|bmp|dib|jfif|tif|tiff|heic)";

}
