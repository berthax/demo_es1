package com.troila.tjsmesp.spider.crawler;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSelenium {
	
	@Test
    public void testSelenium() {
        System.getProperties().setProperty("webdriver.chrome.driver", "D://chromedriver/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
//        webDriver.get("http://huaban.com/");
//        webDriver.get("http://zcydt.fzgg.tj.gov.cn/zcbjd/index.shtml");
        
//        js = """var xmlhttp=new XMLHttpRequest();
//                xmlhttp.open("GET","http://127.0.0.1/get.php",false);
//                xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
//                xmlhttp.setRequestHeader("User-Agent","Mozilla/5.0");
//                xmlhttp.setRequestHeader("Cookie","");
//                xmlhttp.send("test=1");
//                return xmlhttp.responseText;
//        	    """ 
//        brower.implicitly_wait(30)
//        #time.sleep(30)
//        resp = brower.execute_script(js)
        
//        WebElement webElement = webDriver.findElement(By.xpath("/html"));
//        System.out.println(webElement.getAttribute("outerHTML"));
//        webDriver.close();
        

        webDriver.get("http://202.106.120.178/cms/show.action");
        JavascriptExecutor jsExec = (JavascriptExecutor)webDriver;
		String functionBody = "return arguments[1]+','+arguments[2]";
		String returnRes = (String)jsExec.executeScript(functionBody, 1, "hello", "selenium");
		System.out.println(returnRes);
		
		
//		jquery = open("jquery.min.js", "r").read();
//
//				driver = webdriver.Firefox(firefox_options=fireFoxOptions)
//				driver.execute_script(jquery)

		String	ajax_query_template = "$.ajax('%s', {"
				            +"type: %s,"
				            +"data: %s," 
				            +"headers: { 'User-Agent': 'Chrome/71.0.3578.98' },"
				            +"crossDomain: true,"
				            +"xhrFields: {"
				            +" withCredentials: true"
				            +"},"
				            +"success: function(){}"
				            +"});";
//				            ''' % (url, request_type, data)
		String	ajax_query	= String.format(ajax_query_template, "http://202.106.120.178/cms/show.action","post","data");
		System.out.println(ajax_query);
//		ajax_query = ajax_query.replace(" ", "").replace("\n", "");
//		String	resp = (String)jsExec.execute_script("return " + ajax_query);

    }
}
