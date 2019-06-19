package com.troila.tjsmesp.spider.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.troila.tjsmesp.spider.config.DataSyncSettings;
import com.troila.tjsmesp.spider.constant.PolicyStatusConst;
import com.troila.tjsmesp.spider.constant.SpiderModuleEnum;
import com.troila.tjsmesp.spider.model.primary.PolicySpider;
import com.troila.tjsmesp.spider.model.secondary.SmePolicy;
import com.troila.tjsmesp.spider.repository.informix.SmePolicyRespositoryInformix;
import com.troila.tjsmesp.spider.repository.mysql.PolicySpiderRepositoryMysql;
import com.troila.tjsmesp.spider.util.MD5Util;
import com.troila.tjsmesp.spider.util.ReduceHtml2Text;
import com.troila.tjsmesp.spider.util.TimeUtils;
@Service
public class PolicyService {
	private static final Logger logger = LoggerFactory.getLogger(PolicyService.class);
	
	@Autowired
	private DataSyncSettings dataSyncSettings;
		
	@Autowired
	private SmePolicyRespositoryInformix smePolicyRespositoryInformix;
	
	@Autowired
	private PolicySpiderRepositoryMysql policySpiderRepositoryMysql;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	private Map<String,PolicySpider> map = new ConcurrentHashMap<>();
	
	/**
	 * mysql数据库的数据实体类与informix数据库实体类之间的转换
	 * @param policySpider
	 * @return
	 */
	public SmePolicy convertTo(PolicySpider policySpider) {
		if(policySpider == null) {
			logger.error("类型转换参数错误：参数policySpider为null");
			return null;			
		}
		SmePolicy smePolicy = new SmePolicy();
		//待确定字段内容
		if(policySpider.getSpiderModule()==SpiderModuleEnum.POLICY_NEWEST.getIndex()) {
			//如果是政策原文,父类Id默认-1
			smePolicy.setParentId(-1);	
			smePolicy.setPolicyLevel(policySpider.getPolicyLevel());   //有待区分对待
			smePolicy.setSource(policySpider.getPublishUnit());
			smePolicy.setReferenceNumber(policySpider.getPublishNo());
		}else {
			//如果是政策解读，需要找到该解读的文章的父类，即政策原文
			SmePolicy parentPolicy = getParentPolicyForReadingActicleInformix(policySpider.getPublishUrl());			
			if(parentPolicy != null) {
				//设置解读父类id
				smePolicy.setParentId(parentPolicy.getId());  
				//设置解读的政策级别
				smePolicy.setPolicyLevel(policySpider.getPolicyLevel()==null?parentPolicy.getPolicyLevel():policySpider.getPolicyLevel());
				smePolicy.setSource((policySpider.getPolicyLevel()!=null && policySpider.getPolicyLevel()==0)?parentPolicy.getSource():policySpider.getPublishUnit());  //设置发文部门
				smePolicy.setReferenceNumber(parentPolicy.getReferenceNumber());				
			}else {
				logger.warn("类型转换时出现错误，政策解读记录{}:{}找不到对应的原文记录",policySpider.getTitle(),policySpider.getPublishUrl());
				smePolicy.setParentId(-1);  //此种情况为没有对应原文的解读文章
				smePolicy.setSource(policySpider.getPublishUnit());
				smePolicy.setPolicyLevel(policySpider.getPolicyLevel());
			}
		}
		smePolicy.setTitle(policySpider.getTitle());
		smePolicy.setType(policySpider.getSpiderModule());
		smePolicy.setStripedContent(ReduceHtml2Text.removeHtmlTag(policySpider.getContent()));
		String content = "<div class='articleList'>"+
							"<h3 class='articleTit'>正文</h3><div class='articleTxt'>"+policySpider.getContent()+"</div>"+
					 	    "<h3 class='articleTit'>联系人及联系方式</h3><div class='articleTxt'></div>"+
					 	  "</div>";
		smePolicy.setContent(content);
		smePolicy.setPublishDate(policySpider.getPublishDate().getTime());
		
		smePolicy.setStatus(PolicyStatusConst.Pending);
		
		smePolicy.setUrl(policySpider.getPublishUrl());
		
		//有待进一步确定
		smePolicy.setGmtStart(policySpider.getPublishDate());
		smePolicy.setGmtEnd(new Date());   
		smePolicy.setGmtCreate(new Date());
		smePolicy.setGmtModify(new Date());
		smePolicy.setFromSite(policySpider.getFromSite());
//		smePolicy.setFromLink(policySpider.getFromLink());
		//修改内容，转载链接改为原文链接了
		smePolicy.setFromLink(policySpider.getPublishUrl());
		smePolicy.setGmtForward(new Date());
				
		smePolicy.setIndustry("ALL");
		smePolicy.setArea("全国");
		smePolicy.setPriority(0);
		smePolicy.setCategory("不限");
		smePolicy.setPublishType("platform");
		smePolicy.setPublisher(dataSyncSettings.getDefaultPublisher());
		//如果有附件下载链接，设置附件
		smePolicy.setAttachments(policySpider.getAttachment());   //文章链接问题已经搞定，同步过去之后可以直接下载附件
		return smePolicy;
	}
	
	/**
	 * 本地数据更新
	 * @return
	 */
	public List<PolicySpider> dataUpdate(SpiderModuleEnum spiderMoudleEnum){
		try {	
			logger.info("{}开始执行数据更新过程，更新模块为{},请稍候……",TimeUtils.getLongFormatDate(new Date()),spiderMoudleEnum.getName());
			//先获取mysql数据库中的所有该类型的数据
			List<PolicySpider> mysqlList = policySpiderRepositoryMysql.findBySpiderModule(spiderMoudleEnum.getIndex());
			//获取redis中爬取记录的总数
			long size = redisTemplate.opsForList().size(spiderMoudleEnum.getKey());
			if(size == 0) {
				logger.info("当前Reids中未查询到任何爬取的数据，本次爬取没有更新的内容");
				return new ArrayList<>();
			}
			//从redis中获取本次爬取的所有记录
			List<Object> redisListObj = redisTemplate.opsForList().range(spiderMoudleEnum.getKey(), 0L, size-1);
			//进行类型转换
			List<PolicySpider> redisList = redisListObj.stream().map(e->{return (PolicySpider)e;}).collect(Collectors.toList());
			map = mysqlList.stream().collect(Collectors.toMap(PolicySpider::getId, e->e));		
				
			List<PolicySpider> updateList = new ArrayList<PolicySpider>();
			//redis中本次爬取的所有记录中筛选出原来数据库中没有的记录，即为距离上次爬取的这段时间的更新的记录
			updateList = redisList.stream()
								.filter(p->!map.containsKey(MD5Util.getMD5(p.getPublishUrl())))
								.collect(Collectors.toList());
			//如果是政策解读，需要对发文部门，发文字号，政策级别进行设置（此处为政策解读在本地库的存储情况，与中小企业项目无关）
			if(spiderMoudleEnum == SpiderModuleEnum.POLICY_READING) {  
				updateList = updateList.stream()
										.map(e->{
											PolicySpider parentPolicy = getParentPolicyForReadingActicleMysql(e.getPublishUrl());
											if(parentPolicy != null) {
												e.setParentId(parentPolicy.getId());
												e.setPublishNo(parentPolicy.getPublishNo());
												if(e.getPublishUnit().equals("国家政策解读")) {
													e.setPublishUnit(parentPolicy.getPublishUnit());
													e.setPolicyLevel(0);  //因为是国家政策的解读，级别肯定是0
												}
											}
											return e;})
										.collect(Collectors.toList());
			}
			//将本次新增的内容保存进本地数据库
			if(updateList.size()>0) {				
				policySpiderRepositoryMysql.saveAll(updateList);	
				logger.info("Mysql本地库本次更新模块：{}完成,增加条目数为：{} 条",spiderMoudleEnum.getName(),updateList.size());
			}
			return updateList;
		}catch (Exception e) {
			logger.error("本次更新模块：{} 出现异常,信息如下：",spiderMoudleEnum.getName(),e);
			return null;
		}finally {
			map.clear();
		}
	}
	
	/**
	 * 数据同步，同步最近n天的政策数据
	 * @return
	 */
	public List<SmePolicy> syncLatestPolicyData(SpiderModuleEnum spiderMoudleEnum,int n){
		//获取最近N天的数据
		List<PolicySpider> list = policySpiderRepositoryMysql.findByPublishDateGreaterThanEqualAndSpiderModule(TimeUtils.getLastNDay(n), spiderMoudleEnum.getIndex());
		if(list == null) {
			logger.error("数据库中没有相关记录，本次同步完成，本次同步模块：{},增加条目数为：0条",spiderMoudleEnum.getName());
			return null;
		}
		
		//将本次新增的内容同步到中小企数据库中
		List<SmePolicy> resultList = list.stream()
				.filter(p->{
					if(convertTo(p)==null) {
						return false;						
					}
					List<SmePolicy> listInner = smePolicyRespositoryInformix.findByFromLink(p.getPublishUrl());
					if(listInner == null || listInner.size()==0) {
						return true;
					}else {
						logger.info("本次同步有重复数据，标题为：{}，链接为：{}的政策已经筛除掉",p.getTitle(),p.getPublishUrl());
						return false;											
					}
				})//过滤掉转换完后为null的记录，此种情况基本不存在
				.map(e->{return convertTo(e);})    
				.collect(Collectors.toList());	
		
		List<SmePolicy> resultListSave = smePolicyRespositoryInformix.saveAll(resultList);
		logger.info("Informix库本次同步数据完成，本次同步模块：{},增加条目数为：{} 条",spiderMoudleEnum.getName(),resultListSave.size());
		return resultListSave;	
	}
	

	
	
	/**
	 * 同步最近一周的数据
	 * @param spiderMoudleEnum
	 * @return
	 */
	public List<SmePolicy> dataSync_test(SpiderModuleEnum spiderMoudleEnum){
		List<PolicySpider> mysqlList = policySpiderRepositoryMysql.findByPublishDateGreaterThanEqualAndSpiderModule(TimeUtils.getLastWeek(), 0);
		List<SmePolicy> resultList = mysqlList.stream().map(e->{return convertTo(e);}).collect(Collectors.toList());
		return smePolicyRespositoryInformix.saveAll(resultList);	
	}
	
	/**
	 * 为爬取的解读文章找到政策原文文章，用于同步政策解读的数据时候使用
	 * @param publicshUrl
	 * @return
	 */
	public SmePolicy getParentPolicyForReadingActicleInformix(String publishUrl) {
		//其对应的父类文章
		List<PolicySpider>  list = policySpiderRepositoryMysql.findByArticleReadingContaining(publishUrl);
		if(list == null || list.size()==0) {
			logger.info("查找文章链接为：{} 的政策原文出错,Mysql库中没有相关记录",publishUrl);
			return null;			
		}
		SmePolicy parentPolicyInformix = null;
		List<SmePolicy> listParentInformix = null;
		//此处对于一点通网站，有一篇解读对应多个原文的情况
		for(PolicySpider parentPolicy : list) {
			listParentInformix = smePolicyRespositoryInformix.findByFromLink(parentPolicy.getPublishUrl());
			if(listParentInformix != null && listParentInformix.size()>0) {
				parentPolicyInformix = listParentInformix.get(0);
				break; 				
			}
		}
		if(parentPolicyInformix == null) {
			logger.warn("查找文章链接为：{} 的政策原文出错,Informix库中查询结果为空",publishUrl);
			return null;
		}
		return parentPolicyInformix;
	}
	
	
	/**
	 * 为爬取的解读文章找到政策原文文章Mysql
	 * @param publicshUrl
	 * @return
	 */
	//http://zcydt.fzgg.tj.gov.cn/zcbjd/sjbmjd/sjrj_237/201703/t20170321_19918.shtml
	public PolicySpider getParentPolicyForReadingActicleMysql(String publishUrl) {
		List<PolicySpider>  list = policySpiderRepositoryMysql.findByArticleReadingContaining(publishUrl);
		if(list == null || list.size() <= 0) {
			logger.warn("查找文章链接为：{} 的父类文章,Mysql库中查询结果为空",publishUrl);	
			return null;
		}
		if(list.size() > 1) {
			logger.info("查找文章链接为：{} 的父类文章完成，父类文章个数为{}",publishUrl,list.size());	
		}
		PolicySpider parentPolicy = list.get(0);
		logger.info("查找文章链接为：{} 的父类文章完成，父类文章个数为{},文章链接为：{}",publishUrl,list.size(),parentPolicy.getPublishUrl());	
		return parentPolicy;
	}

	/**
	 *   为爬取的解读文章没有对应原文的，进行一些矫正工作
	 * @return
	 */
	public List<PolicySpider> adjustForReadingRelated(int lastDays){
		Date lastNDay = TimeUtils.getLastNDay(lastDays);
		List<PolicySpider> mysqlList = policySpiderRepositoryMysql.findByParentIdIsNullAndPublishDateGreaterThanEqualAndSpiderModule(lastNDay, SpiderModuleEnum.POLICY_READING.getIndex());
		return mysqlList;
	}
	
/*	public static final String TITLE_REGEX = "(\\w+)(\\u4E00-\\u9FA5)(\\w+)(\\u4E00-\\u9FA5)(\\w+)";
	
	public PolicySpider adjustForReadingRelatedDetail(PolicySpider policySpider) {
		if(null == policySpider) {
			return null;
		}
		String title = policySpider.getTitle();
//		if(title.contains("关于") || title.contains("解读") || title.contains("《"))
		return null;
	}*/

}
