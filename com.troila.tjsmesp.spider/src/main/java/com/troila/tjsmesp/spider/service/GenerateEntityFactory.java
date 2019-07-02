package com.troila.tjsmesp.spider.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.troila.tjsmesp.spider.annotation.EntityGenerator;
import com.troila.tjsmesp.spider.config.DataSyncSettings;
import com.troila.tjsmesp.spider.model.primary.NewsSpider;
import com.troila.tjsmesp.spider.model.secondary.BmsPlatformPublishInfo;

@Component
public class GenerateEntityFactory implements ApplicationListener<ContextRefreshedEvent>{
	private static final Logger logger = LoggerFactory.getLogger(GenerateEntityFactory.class);
	
	private static Map<Integer,IGenerateNewsEntity> generateMap = new HashMap<>();
    
    private GenerateEntityFactory() {}
    
    @Autowired
    private DataSyncSettings dataSyncSettings;
   
  //获取
    public BmsPlatformPublishInfo createEntity(NewsSpider newsSpider) {
    	// 根据模块获取对应的生成实体实现类
    	IGenerateNewsEntity result = generateMap.get(newsSpider.getSpiderModule());
        if(null == result) {
        	logger.error("工厂中没有对应的生产实体实现类，生成实体类错误，对应的参数为:{}",newsSpider);
        	return null;
        }
    	return result.generate(newsSpider, dataSyncSettings);
    }
    
//    @PostConstruct
//    private void init() {
////    	generateMap.put(SpiderModuleEnum.POLICY_NEWS_FOCUS_GUOJIA.getIndex(), new NewsFocusGuojiaGenerateNewsEntity());
//    	generateMap.put(SpiderModuleEnum.POLICY_NEWS_FOCUS_GUOJIA.getIndex(), newsFocusGuojiaGenerateNewsEntity);
//    	generateMap.put(SpiderModuleEnum.POLICY_NEWS_FOCUS_BUWEI.getIndex(),new NewsFocusBuweiGenerateNewsEntity());
//    	generateMap.put(SpiderModuleEnum.POLICY_NEWS_FOCUS_TIANJIN.getIndex(), new NewsFocusTianjinGenerateNewsEntity());
//    	generateMap.put(SpiderModuleEnum.POLICY_REGIONAL_DYNAMIC.getIndex(), new NewsRegionalDynamicGenerateNewsEntity());
//    	generateMap.put(SpiderModuleEnum.POLICY_INDUSTRY_INFO.getIndex(),new NewsIndustryInfoGenerateNewsEntity());
//    	generateMap.put(SpiderModuleEnum.JINGHAI_INDUSTRIAL_CLUSTERS_NEWS.getIndex(), new JinghaiIndustrialClustersNewsGenerateNewsEntity());
//    	generateMap.put(SpiderModuleEnum.JINGHAI_INDUSTRIAL_CLUSTERS_NOTICE.getIndex(),new JinghaiIndustrialClustersNoticeGenerateNewsEntity());
//    }
    
    /**
     * 实体转换类加自定义注解后，注册到工厂类中
     */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// 根容器为Spring容器
		if(event.getApplicationContext().getParent()==null){
            Map<String,Object> beans = event.getApplicationContext().getBeansWithAnnotation(EntityGenerator.class);
            for(Object bean : beans.values()){
            	EntityGenerator entityGenerator = bean.getClass().getAnnotation(EntityGenerator.class);
//                System.out.println(bean.getClass().getName()+"===" + entityGenerator.value());
                int value = entityGenerator.value();
                Class<? extends Object> clazz = bean.getClass();
                try {
					IGenerateNewsEntity result = (IGenerateNewsEntity)clazz.newInstance();
					generateMap.put(value, result);
				} catch (InstantiationException e) {
					logger.error("注册实体转换类异常，信息如下：value:{},Class:{}",value,clazz,e);
				} catch (IllegalAccessException e) {
					logger.error("注册实体转换类异常，信息如下：value:{},Class:{}",value,clazz,e);
				}
            }            
           logger.error("=====onApplicationEvent====="+event.getSource().getClass().getName());
        }	
	}
}
