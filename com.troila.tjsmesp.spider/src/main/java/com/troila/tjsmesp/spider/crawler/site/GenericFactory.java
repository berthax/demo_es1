package com.troila.tjsmesp.spider.crawler.site;
/**
 * 
 * @ClassName:  GenericFactory   
 * @Description:带泛型的简单工厂，但是不能是静态工厂了 
 * @author: xgj
 * @date:   2019年6月20日 下午5:18:12   
 *   
 * @param <T>
 */
public class GenericFactory<T extends SpiderProcess> {
	
	public T getInstance(Class<T> clazz) {  
        T t = null;  
        try {  
            t = clazz.newInstance();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return t;  
    } 
	
}
