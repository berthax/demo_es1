package com.troila.tjsmesp.spider.crawler.site;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SiteProcessorFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(SiteProcessorFactory.class);
	
	public static SpiderProcess create(Class<? extends SpiderProcess> clazz) {
		SpiderProcess spiderProcess = null;
		try {
//			spiderProcess = (SpiderProcess)clazz.newInstance();
			// 动态依赖用的，可以使用任何一个包路径，而不需要去引入这个类
			spiderProcess = (SpiderProcess)Class.forName(clazz.getName()).newInstance();
		} catch (Exception e) {
			logger.error("工厂创建实例失败，实例类型为:【{}】,异常信息如下:",clazz.getName(),e);
		}
		return spiderProcess;
	}
	
/*	@SuppressWarnings("unchecked")
	public static <T extends SpiderProcess> T create2(Class<T> c) {		
		T t = null;		
		try {
			t = (T) Class.forName(c.getName()).newInstance();
		} catch (Exception e) {
			logger.error("工厂创建实例失败，实例类型为:【{}】,异常信息如下:",c.getName(),e);
		}		
		return t;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T create3(Class<T> c) {		
		T t = null;		
		try {
			t = (T) Class.forName(c.getName()).newInstance();
		} catch (Exception e) {
			logger.error("工厂创建实例失败，实例类型为:【{}】,异常信息如下:",c.getName(),e);
		}		
		return t;
	}
		
	public static void main(String[] args) {
		try {
			TjGovCnProcessor processor = (TjGovCnProcessor) SiteProcessorFactory.create(TjGovCnProcessor.class);
			System.out.println(processor);
			System.out.println("创建完成");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
