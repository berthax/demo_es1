package com.troila.tjsmesp.spider.crawler;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@TargetUrl("http://zcydt.fzgg.tj.gov.cn/zcbjd/sjbmjd/skw_201/201811/t20181115_51122.shtml")
@HelpUrl("http://zcydt.fzgg.tj.gov.cn/zcbjd/index.shtml/")
public class MyTest1 {
	
	@ExtractBy("//div[@class='jiedu_nr_2']/tidyText()")
	private String title;
	
	@ExtractBy("//div[@class='jiedu_nr_7']/tidyText()")
	private String content;
	
	 public static void main(String[] args) {
//	        OOSpider.create(Site.me().setSleepTime(1000)
//	                , new ConsolePageModelPipeline(), MyTest1.class)
//	                .addUrl("http://zcydt.fzgg.tj.gov.cn/zcbjd/index.shtml").thread(5).run();
		 
//		  String urlTemplate = "http://baike.baidu.com/search/word?word=%s&pic=1&sug=1&enc=utf8";
//		  System.out.println(String.format(urlTemplate, "水力发电"));
	        
	        System.out.println( System.getProperty("os.name") );
	        System.out.println( System.getProperty("os.version") );
	        System.out.println( System.getProperty("os.arch") );

	    }
}
