//package com.troila.tjsmesp.spider.crawler;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.web.client.RestTemplate;
////@Component
////@Order(2)
//public class HttpClientTest implements CommandLineRunner{
//
//	@Autowired
//	private RestTemplate restTemplate;
//	
//	@Override
//	public void run(String... args) throws Exception {
//		
//		String uri = "http://sme.miit.gov.cn/cms/news/100000/0000000239/0000000239.shtml";
//		List<String> urls = new ArrayList<>();
//		HttpHeaders headers = new HttpHeaders(); 
////		headers.setContentType(MediaType.APPLICATION_JSON_UTF8); 
//		headers.setContentType(MediaType.TEXT_HTML);
//		headers.set("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
//		HttpEntity<String> entity = new HttpEntity<String>(headers); 
//		for(int i=1;i<30;i++) {
//			urls.add("http://sme.miit.gov.cn/cms/news/100000/0000000239/0000000239"+i+".shtml");
//			String url = "http://sme.miit.gov.cn/cms/news/100000/0000000239/0000000239_"+i+".shtml";
//			String res = restTemplate.exchange(url, HttpMethod.GET, entity,String.class).getBody(); 
//			System.out.println("【"+url+"】");
//			System.out.println(res);
//			Thread.sleep(10);
//		}
//	}
//
//}
