package com.troila.tjsmesp.spider.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.troila.tjsmesp.spider.config.DataSyncSettings;
import com.troila.tjsmesp.spider.constant.SmeNewsTypeConst;
import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.model.primary.NewsSpider;
import com.troila.tjsmesp.spider.model.secondary.BmsPlatformPublishInfo;
import com.troila.tjsmesp.spider.model.secondary.SmePolicy;
import com.troila.tjsmesp.spider.repository.informix.BmsPlatformPublishInfoRepositoryInformix;
import com.troila.tjsmesp.spider.repository.mysql.NewsSpiderRepositoryMysql;
import com.troila.tjsmesp.spider.util.TimeUtils;

@Service
public class NewsService {
	
	private static final Logger logger = LoggerFactory.getLogger(NewsService.class);
	
	@Autowired
	private DataSyncSettings dataSyncSettings;
		
	@Autowired
	private BmsPlatformPublishInfoRepositoryInformix bmsPlatformPublishInfoRepositoryInformix;
	
	@Autowired
	private NewsSpiderRepositoryMysql newsSpiderRepositoryMysql;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	private Map<String,NewsSpider> map = new ConcurrentHashMap<>();
	
	/**
	 * mysql数据库的数据实体类与informix数据库实体类之间的转换
	 * @param policySpider
	 * @return
	 */
	private BmsPlatformPublishInfo convertTo(NewsSpider newsSpider) {
		if(newsSpider == null) {
			logger.error("类型转换参数错误：参数newsSpider为null");
			return null;			
		}
		
		BmsPlatformPublishInfo bmsPlatformPublishInfo = new BmsPlatformPublishInfo();
		bmsPlatformPublishInfo.setPublishTitle(newsSpider.getTitle());
		bmsPlatformPublishInfo.setPublishContent(newsSpider.getContent());
		bmsPlatformPublishInfo.setPublishTime(newsSpider.getPublishDate());
		bmsPlatformPublishInfo.setPublishFrom(newsSpider.getSource());
		// 枚举类中要闻焦点国家、部委、天津是5 6 7，informix数据库中是1 2 3，偷懒先这么写了
		if(newsSpider.getSpiderModule() ==  SpiderModuleEnum.POLICY_NEWS_FOCUS_GUOJIA.getIndex()) {
			bmsPlatformPublishInfo.setFocusType(String.valueOf(SmeNewsTypeConst.NEWS_FOCUS_GUOJIA));
			bmsPlatformPublishInfo.setPublishType("1");
		}else if(newsSpider.getSpiderModule() == SpiderModuleEnum.POLICY_NEWS_FOCUS_BUWEI.getIndex()) {
			bmsPlatformPublishInfo.setFocusType(String.valueOf(SmeNewsTypeConst.NEWS_FOCUS_BUWEI));
			bmsPlatformPublishInfo.setPublishType("1");
		}else if(newsSpider.getSpiderModule() ==  SpiderModuleEnum.POLICY_NEWS_FOCUS_TIANJIN.getIndex()) {
			bmsPlatformPublishInfo.setFocusType(String.valueOf(SmeNewsTypeConst.NEWS_FOCUS_TIANJIN));
			bmsPlatformPublishInfo.setPublishType("1");			
		}
				
		if(newsSpider.getSpiderModule() ==  SpiderModuleEnum.POLICY_INDUSTRY_INFO.getIndex()) {
			bmsPlatformPublishInfo.setFocusType("0");
			bmsPlatformPublishInfo.setPublishType(String.valueOf(SmeNewsTypeConst.NEWS_INDUSTRY_INFO));
		}else if(newsSpider.getSpiderModule() == SpiderModuleEnum.POLICY_REGIONAL_DYNAMIC.getIndex()) {
			bmsPlatformPublishInfo.setFocusType("0");
			bmsPlatformPublishInfo.setPublishType(String.valueOf(SmeNewsTypeConst.NEWS_REGIONAL_DYNAMIC));
		}
		// 设置转载时间
		bmsPlatformPublishInfo.setTransTime(new Date());
		// 设置原文链接地址
		bmsPlatformPublishInfo.setOrgLink(newsSpider.getPublishUrl());
		
		// 设置所属区域为01
		bmsPlatformPublishInfo.setAreaType("01");
		// 设置所属平台属于中枢平台
		bmsPlatformPublishInfo.setPlatformType("120000");
		bmsPlatformPublishInfo.setPublisherId(dataSyncSettings.getDefaultPublisherId());
		bmsPlatformPublishInfo.setCreateStamp(new Date());
		bmsPlatformPublishInfo.setUpdateStamp(new Date());
		bmsPlatformPublishInfo.setResponsibilityWindowType("0");
		bmsPlatformPublishInfo.setIsPublished("1");
		bmsPlatformPublishInfo.setIsTop("0");
		bmsPlatformPublishInfo.setIsPublishCenterPlatform("1");
		bmsPlatformPublishInfo.setIsVerify("2");   // 设置为未审核
		bmsPlatformPublishInfo.setDistrictCharacteristicType("0");
		bmsPlatformPublishInfo.setServiceType("100000");  //设置服务类型为信息服务
		bmsPlatformPublishInfo.setPublisherType("1");
//		bmsPlatformPublishInfo.setPublishOrder("1000");
		bmsPlatformPublishInfo.setIsSubmit("1");
		bmsPlatformPublishInfo.setIsOut("0");
		return bmsPlatformPublishInfo;
	}
	
	public List<NewsSpider> dataUpdate(SpiderModuleEnum spiderMoudleEnum){
		try {
					
			logger.info("【{}】开始执行数据更新过程，更新模块为【{}】,请稍候……",TimeUtils.getLongFormatDate(new Date()),spiderMoudleEnum.getName());
			//先获取mysql数据库中的所有该类型的数据
			List<NewsSpider> mysqlList = newsSpiderRepositoryMysql.findBySpiderModule(spiderMoudleEnum.getIndex());
			// 用java8进行转换用到Map时，有时会遇到重复key的问题，第三个参数是进行处理的
			map = mysqlList.stream().collect(Collectors.toMap(NewsSpider::getPublishUrl, e->e,(key1 , key2)-> key2));		
			
			//获取redis中爬取记录的总数
			long size = redisTemplate.opsForList().size(spiderMoudleEnum.getKey());
			if(size == 0) {
				logger.info("模块【{}】，当前Reids中未查询到任何爬取的数据，本次爬取没有更新的内容",spiderMoudleEnum.getName());
				return new ArrayList<>();
			}
			//从redis中获取本次爬取的所有记录
			List<Object> redisListObj = redisTemplate.opsForList().range(spiderMoudleEnum.getKey(), 0L, size-1);
			//进行类型转换
			List<NewsSpider> redisList = redisListObj.stream().map(e->{return (NewsSpider)e;}).collect(Collectors.toList());
			List<NewsSpider> updateList = new ArrayList<NewsSpider>();
			//redis中本次爬取的所有记录中筛选出原来数据库中没有的记录，即为距离上次爬取的这段时间的更新的记录
			updateList = redisList.stream()
								.filter(p->!map.containsKey(p.getPublishUrl()))
								.collect(Collectors.toList());
			//将本次新增的内容保存进本地数据库
			if(updateList.size()>0) {				
				newsSpiderRepositoryMysql.saveAll(updateList);	
				logger.info("Mysql本地库本次更新模块：【{}】完成,增加条目数为：【{}】 条",spiderMoudleEnum.getName(),updateList.size());
			}
			return updateList;	
		} catch (Exception e2) {
			e2.printStackTrace();
			return null;
		}
	}
		
	/**
	 * 
	 * @Description 同步最近N天的数据
	 * @param spiderMoudleEnum
	 * @param lastDays
	 * @return
	 */
	@Transactional
	public List<BmsPlatformPublishInfo> newsDataSync(SpiderModuleEnum spiderMoudleEnum, int lastDays){
		// 获取最近N天的数据
		List<NewsSpider> list = newsSpiderRepositoryMysql.findByPublishDateGreaterThanEqualAndSpiderModule(TimeUtils.getLastNDay(lastDays), spiderMoudleEnum.getIndex());
		if(list == null) {
			logger.error("数据库中没有相关记录，本次同步完成，本次同步模块：【{}】,增加条目数为：0条",spiderMoudleEnum.getName());
			return null;
		}
		
		// 完成实体转换，并过滤掉已经通不过的记录，避免重复同步的问题
		List<BmsPlatformPublishInfo> listToSync = list.stream()
				.filter(p->{
					if(convertTo(p)==null) {
						return false;						
					}
					List<BmsPlatformPublishInfo> listInner = bmsPlatformPublishInfoRepositoryInformix.findByOrgLink(p.getPublishUrl());
					if(listInner == null || listInner.size()==0) {
						return true;
					}else {
						logger.info("本次同步有重复数据，标题为：{}，链接为：{}的信息已经筛除掉",p.getTitle(),p.getPublishUrl());
						return false;											
					}
				})
				.map(e->{return convertTo(e);})
				.collect(Collectors.toList());
		List<BmsPlatformPublishInfo> resultListSave = bmsPlatformPublishInfoRepositoryInformix.saveAll(listToSync);
		logger.info("Informix库本次同步数据完成，本次同步模块：【{}】,增加条目数为：【{}】 条",spiderMoudleEnum.getName(),resultListSave.size());		
		return resultListSave;
	}
	
/*	public static void main(String[] args) {
		Map<String, List<String>> map = list.stream().collect(Collectors.toMap(Student :: getClassName, 
				    // 此时的value 为集合，方便重复时操作
				    s ->  {
					List<String> studentNameList = new ArrayList<>();
					studentNameList.add(s.getStudentName());
					return studentNameList;
				    }, 
				    // 重复时将现在的值全部加入到之前的值内
				    (List<String> value1, List<String> value2) -> {
					value1.addAll(value2);
					return value1;
				    }
				));
	}*/
}
