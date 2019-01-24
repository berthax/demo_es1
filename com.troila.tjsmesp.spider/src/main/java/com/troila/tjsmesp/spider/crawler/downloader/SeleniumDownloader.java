package com.troila.tjsmesp.spider.crawler.downloader;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.PlainText;

@Component
public class SeleniumDownloader implements Downloader, Closeable{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private int sleepTime = 0;
	
//	private volatile WebDriverPool webDriverPool;
	
	private int poolSize = 5;
	
	@Autowired
	private volatile WebDriverPool webDriverPool;
	
	@Override
	public Page download(Request request, Task task) {
		checkInit();
		WebDriver webDriver = null;
		try {
			webDriver = webDriverPool.get();
		} catch (Exception e) {
			logger.warn("interrupted", e);
			return null;
		}
		 
		logger.info("downloading page " + request.getUrl());
		Page page = Page.fail();
		try {
			webDriver.get(request.getUrl());
			Thread.sleep(sleepTime);
//            logger.info("downloading page success {}", request.getUrl());
            WebDriver.Options manage = webDriver.manage();
    		Site site = task.getSite();
    		if (site.getCookies() != null) {
    			for (Map.Entry<String, String> cookieEntry : site.getCookies().entrySet()) {
    				Cookie cookie = new Cookie(cookieEntry.getKey(),cookieEntry.getValue());
    				manage.addCookie(cookie);
    			}
    		}

    		WebElement webElement = webDriver.findElement(By.xpath("/html"));
    		String content = webElement.getAttribute("outerHTML");
    		page.setRawText(content);
//    		page.setHtml(new Html(content, request.getUrl()));
    		page.setUrl(new PlainText(request.getUrl()));
    		page.setDownloadSuccess(true);
    		page.setRequest(request);
    		return page;
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("download page {} error", request.getUrl(), e);
            return page;
		}finally {
			if(webDriver != null) {
				//将WebDriver实例归还到线程池中
				webDriverPool.returnToPool(webDriver);
//    		webDriver.close();
//    		webDriver.quit();				
			}
        }		
	}
	
	@Override
	public void close() throws IOException {
//		webDriverPool.closeAll();		
	}
	
	private void checkInit() {
		if (webDriverPool == null) {
			synchronized (this) {
				webDriverPool = new WebDriverPool(poolSize);
			}
		}
//		System.getProperties().setProperty("webdriver.chrome.driver", "D://chromedriver/chromedriver.exe");
	}
	
	@Override
	public void setThread(int threadNum) {
		// TODO Auto-generated method stub
		this.poolSize = threadNum;
	}
	
	/**
	 * 新建
	 *
	 * @param chromeDriverPath chromeDriverPath
	 */
	public SeleniumDownloader(String chromeDriverPath) {
		System.getProperties().setProperty("webdriver.chrome.driver",
				chromeDriverPath);
	}

	/**
	 * Constructor without any filed. Construct PhantomJS browser
	 * 
	 * @author bob.li.0718@gmail.com
	 */
	public SeleniumDownloader() {
		// System.setProperty("phantomjs.binary.path",
		// "/Users/Bingo/Downloads/phantomjs-1.9.7-macosx/bin/phantomjs");
	}
	/**
	 * set sleep time to wait until load success
	 *
	 * @param sleepTime sleepTime
	 * @return this
	 */
	public SeleniumDownloader setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
		return this;
	}


}
