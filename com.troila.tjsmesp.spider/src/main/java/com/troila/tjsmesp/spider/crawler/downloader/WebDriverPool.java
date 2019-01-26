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

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.config.SpiderConfig;
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
		logger.info("当前执行初始化WebDrive实例操作，当前系统是否为linux:{},驱动路径为：{}",OSUtil.isOslinux(),spiderDriverConfig.getLinuxDriver());
		String systemDriverPathName = "";
		if(spiderDriverConfig.getType().equals("chromedriver")) {
			systemDriverPathName = "webdriver.chrome.driver";
				
		}else if(spiderDriverConfig.getType().equals("geckodriver")){
			systemDriverPathName = "webdriver.gecko.driver";
			
		}else {
			logger.error("当前工程仅提供对chromedriver、geckodriver的支持， 当前类型为{}，不支持的浏览器类型",spiderDriverConfig.getType());
			throw new RuntimeException("当前工程仅提供对chromedriver、geckodriver的支持， 当前类型为{}，不支持的浏览器类型\",spiderDriverConfig.getType()");
		}			
		if(OSUtil.isOswindows()) {
//			System.getProperties().setProperty("webdriver.chrome.driver", spiderDriverConfig.getChromeWindowsDriver());		
//			System.getProperties().setProperty("webdriver.gecko.driver", spiderDriverConfig.getWindowsDriver());
			System.getProperties().setProperty(systemDriverPathName, spiderDriverConfig.getWindowsDriver());
		}else if(OSUtil.isOslinux()) {
			logger.info("linux下设置系统变量开始，设置内容为:{}",spiderDriverConfig.getLinuxDriver());
			System.getProperties().setProperty(systemDriverPathName, spiderDriverConfig.getLinuxDriver());	
			logger.info("linux下设置系统变量结束，设置内容为:{}",spiderDriverConfig.getLinuxDriver());
		}else if(OSUtil.isOsmac()) {
			System.getProperties().setProperty(systemDriverPathName, spiderDriverConfig.getMacDriver());	
		}else {
			logger.error("当前工程仅提供对windows、linux以及mac系统的支持， 当前类型为{}，当前系统属于不支持的操作系统类型");
			throw new RuntimeException("当前工程不支持的操作系统类型当前工程仅提供对windows、linux以及mac系统的支持， 当前类型为{}，当前系统属于不支持的操作系统类型");
		}
		logger.info("开始创建WebDrive实例……");
		if(spiderDriverConfig.getType().equals("chromedriver")) {
			ChromeOptions options = new ChromeOptions().addArguments("--headless","--web-security=false","--ssl-protocol=any");
			mDriver = new ChromeDriver(options);						
			logger.info("创建WebDrive实例成功，实例为{},参数为：{}",mDriver,options);
		}else if(spiderDriverConfig.getType().equals("geckodriver")){
			FirefoxOptions options = new FirefoxOptions().addArguments("--headless","--web-security=false","--ssl-protocol=any");
			mDriver = new FirefoxDriver(options);	
			logger.info("创建WebDrive实例成功，实例为{},参数为：{}",mDriver,options);
		}else {
			logger.error("当前工程仅提供对chromedriver、geckodriver的支持， 当前类型为{}，不支持的浏览器类型",spiderDriverConfig.getType());
			throw new RuntimeException("当前工程仅提供对chromedriver、geckodriver的支持， 当前类型为{}，不支持的浏览器类型\",spiderDriverConfig.getType()");
		}	
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
