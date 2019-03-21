package com.troila.tjsmesp.spider.crawler;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
/**
 * http://202.106.120.178/cms/show.action 
 * 对应 政策原文 --》政策搜索  
 * 搜索相应关键词进入采集页面
 * @author xuanguojing
 *
 */
public class TestJsExecutor {
	
	public static void main(String[] args) {
		System.getProperties().setProperty("webdriver.chrome.driver", "driver/chromedriver/windows/chromedriver.exe");	
		ChromeOptions options = new ChromeOptions().addArguments("--headless","--web-security=false","--ssl-protocol=any");
		WebDriver driver = new ChromeDriver(options);
		JavascriptExecutor jsExec = (JavascriptExecutor)driver;
		String functionBody = "return arguments[1]+','+arguments[2]";
		String returnRes = (String)jsExec.executeScript(functionBody, 1, "hello", "selenium");
		System.out.println(returnRes);
				
		//示例二    使用executeAsyncScript方法，在js代码中获取方法传入的参数数组，并通过调用callback方法返回函数体计算结果
		//代码中传入3个参数，js语句中索引第二，三各参数。
		
		//超时时间是为callback方法调用而设置的，超时时间内没有调用callback方法，默认会再等待设定的超时间，在此没有返回则抛出异常。
	    driver.manage().timeouts().setScriptTimeout(2, TimeUnit.SECONDS);
	        
		
		String script = "var res = arguments[0] + ',' + arguments[1]; "
				+ "var callback = arguments[2];"
				+ "callback()";
//		String returnVal = (String)driver.executeAsyncScript(script, "hello" , "selenium");
		String returnVal = (String)jsExec.executeAsyncScript(script, "hello" , "selenium");
		System.out.println(">>>" + returnVal);
		
		driver.close();
	}
}
