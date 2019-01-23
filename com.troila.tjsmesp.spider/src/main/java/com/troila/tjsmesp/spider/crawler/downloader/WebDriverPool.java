package com.troila.tjsmesp.spider.crawler.downloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.config.SpiderDriverConfig;
import com.troila.tjsmesp.spider.util.OSUtil;

@Component
public class WebDriverPool {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final static int DEFAULT_CAPACITY = 5;

	@Value("${spider.web.pool.max-num}")
	private final int capacity;

	private final static int STAT_RUNNING = 1;

	private final static int STAT_CLODED = 2;
	
	@Autowired
	private SpiderDriverConfig spiderDriverConfig;
	

	//线程安全的自增操作类
	private AtomicInteger stat = new AtomicInteger(STAT_RUNNING);

	private WebDriver mDriver = null;

	protected static Properties sConfig;
	protected static DesiredCapabilities sCaps;
	
	/**
	 * 初始化一个WebDriver实例
	 */
	public void configure(){
		/**
		 * Disable "web-security", 
		 * enable all possible "ssl-protocols"
		 * "ignore-ssl-errors" for PhantomJSDriver
		 * --headless 使浏览器不弹出界面
		 */
		//enable all possible "ssl-protocols" and
		// "ignore-ssl-errors" for PhantomJSDriver
		if(OSUtil.isOswindows()) {
			System.getProperties().setProperty("webdriver.chrome.driver", spiderDriverConfig.getChromeWindowsDriver());			
		}else if(OSUtil.isOslinux()) {
			System.getProperties().setProperty("webdriver.chrome.driver", spiderDriverConfig.getChromeLinuxDriver());	
		}else if(OSUtil.isOsmac()) {
			System.getProperties().setProperty("webdriver.chrome.driver", spiderDriverConfig.getChromeMacDriver());	
		}else {
			logger.error("当前工程不支持的操作系统类型");
			throw new RuntimeException("当前工程不支持的操作系统类型");
		}
		ChromeOptions options = new ChromeOptions().addArguments("--headless","--web-security=false","--ssl-protocol=any");
		mDriver = new ChromeDriver(ChromeDriverService.createDefaultService(), options);	
//		System.out.println("mDrive创建成功……");
	}

	/**
	 * 
	 */
	private boolean isUrl(String urlString) {
		try {
			new URL(urlString);
			return true;
		} catch (MalformedURLException mue) {
			return false;
		}
	}

	/**
	 * 用于存储WebDriver实例
	 */
	private List<WebDriver> webDriverList = Collections.synchronizedList(new ArrayList<WebDriver>());

	/**
	 * store webDrivers available
	 */
	private BlockingDeque<WebDriver> innerQueue = new LinkedBlockingDeque<WebDriver>();

	public WebDriverPool(int capacity) {
		this.capacity = capacity;
	}

	public WebDriverPool() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public WebDriver get() throws InterruptedException {
		checkRunning();
		WebDriver poll = innerQueue.poll();
		//从队列中拿取一个可用的WebDriver实例，
		//poll方法在给定的时间里，从队列中获取值，时间到了直接调用普通的poll方法，为null则直接返回null
		if (poll != null) {
			return poll;
		}
		//如果没有可用的，当前实例容量没有到最大值，则创建一个新的实例返回
		if (webDriverList.size() < capacity) {
			synchronized (webDriverList) {
				if (webDriverList.size() < capacity) {
					//生成一个新的实例
					configure();
					innerQueue.add(mDriver);
					webDriverList.add(mDriver);					
				}
			}
		}
		//阻塞式等待获取值
		return innerQueue.take();
	}

	public void returnToPool(WebDriver webDriver) {
		checkRunning();
		innerQueue.add(webDriver);
		logger.info("Return webDriver" + webDriver);
	}

	protected void checkRunning() {
		if (!stat.compareAndSet(STAT_RUNNING, STAT_RUNNING)) {
			throw new IllegalStateException("Already closed!");
		}
	}

	public void closeAll() {
		boolean b = stat.compareAndSet(STAT_RUNNING, STAT_CLODED);
		if (!b) {
			throw new IllegalStateException("Already closed!");
		}
		for (WebDriver webDriver : webDriverList) {
			logger.info("Quit webDriver" + webDriver);
			webDriver.quit();
			webDriver = null;
		}
	}

}
