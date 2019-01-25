package com.troila.tjsmesp.spider.constant;

public enum PolicyLevelEnum {
	
	//-------国家级--------
	ZHONGGONGZHONGYANG("中共中央",0),
	QUANGUORENDACHAGNWEIHUI("全国人大常委会",0),
	GUOWUYUANBANGONGTING("国务院办公厅",0),
	GUOWUYUAN("国务院",0,"国务院"),
	GUOJIANEGNYUANJU("国家能源局",0),
	GUOTUZIYUANJU("自然资源部",0,"国土资源局","国土资源部"),
	ZONGYANGWANGXINBAN("中央网信办",0),
	MINZHENGBU("民政部",0),
	GONGXINBU("工信部",0),
	FAGAIWEI("发改委",0,"国家发展改革委","国家发改委"),  //国家发改委
	KEJIBU("科技部",0),
	CAIZHENGBU("财政部",0),
	RENLISHEBAOBU("人力社保部",0),
	ZHUJIANBU("住建部",0,"住房和城乡建设部","住房城乡建设部办公厅"),
	SHANGWUBU("商务部",0),
	ZHONGGUORENMINYINHANG("中国人民银行",0),
	HAIGUANZONGSHU("海关总署",0),
	SHUIWUZONGJU("税务总局",0,"国家税务总局"),
	GONGSHANGZONGJU("工商总局",0),
	ZHIJIANZONGJU("质检总局",0),
	ANJIANJU("安监局",0,"国家安全生产监督管理总局"),
	SHIPIANYAOJIANJU("食品药监局",0,"国家食品药品监督管理总局"),
	TONGJIJU("统计局",0),
	ZHISHICHANQUANJU("知识产权局",0),
	FAZHANYANJIUZHONGXIN("发展研究中心",0),
	ZHONGGUOYINJIANHUI("中国银监会",0),
	ZHONGGUOZHENGJIANHUI("中国证监会",0,"证监会"),
	GUOJIARENZHENGRENKEJIANDUGUANLIWEIYUANHUIBANGONGSHI("国家认证认可监督管理委员会办公室",0),
	/**********国家级新增************/
	GUOJIALVYOUJU("国家旅游局",0),
	JIAOTONGYUNSHUBU("交通运输部",0,"交通运输委"),
	SHUILIBU("水利部",0),
	GUOJIALIANGSHIJU("国家粮食局",0),
	NONGYEBU("农业部",0),
	ZHUFANGCHENGXIANGJIANSHEBUBANGONGTING("住房城乡建设部办公厅",0,"住房城乡建设部"),
	WENGUANGJU("文广局",0),
	GONGANBU("公安部",0),
	WENHUABU("文化部",0),
	HUANJINGBAOHUBU("环境保护部",0),
	GONGYEHEXINXIHUABU("工业和信息化部",0),
	SHENJISHU("审计署",0),
	SIFABU("司法部",0),
		
	//--------天津市--------	
	SHIFAZHANGAIGEWEI("市发展改革委",1),
	SHIJIAOWEI("市教委",1),
	SHIKEJIJU("市科技局",1),
	SHIGONGYEHEXINXIHUAJU("市工业和信息化局",1,"市工业和信息化委"),
	SHIMINZUZONGJIAOWEI("市民族宗教委",1),
	SHIGONGANJU("市公安局",1),
	SHIMINZHENGJU("市民政局",1),
	SHISIFAJU("市司法局",1),
	SHICAIZHENGJU("市财政局",1,"市财政局"),
	SHIRENLISHEBAOJU("市人社局",1,"市人力社保局"),
	SHIGUIHUAHEZIRANZIYUANJU("市规划和自然资源局",1,"市规划局","市自然资源局"),
	SHISHENGTAIHUANJINGJU("市生态环境局",1),
	SHIZHUFANGCHENGXIANGJIANSHEWEI("市住房城乡建设委",1),
	SHICHENGSHIGUANLIWEI("市城市管理委",1),
	SHIJIAOTONGYUNSHUWEI("市交通运输委",1,"市交通运输委"),
	SHISHUIWUJU("市水务局",1),
	SHINONGYENONGCUNWEI("市农业农村委",1,"市农委"),
	SHISHANGWUBUWEI("市商务局",1,"市商务委"),
	SHIWENHUAHELVYOUJU("市文化和旅游局",1,"市旅游局","市文化局"),
	SHIWEISHENGJIANKANGWEI("市卫生健康委",1,"市卫生计生委"),
	SHITUIYIJUNRENJU("市退役军人局",1),
	SHIYINGJIJU("市应急局",1),
	SHISHENJIJU("市审计局",1),
	SHIWAIBAN("市外办",1),
	SHISHICHANGJIANDUWEI("市市场监管委",1,"市市场监督委"),
	SHIGUOZIWEI("市国资委",1),
	SHITIYUJU("市体育局",1),
	SHITONGJIJU("市统计局",1),
	SHIJIANRONGJU("市金融局",1),
	SHIXINFANGBAN("市信访办",1),
	SHIRENFANGBAN("市人防办",1),
	SHIHEZUOJIAOLIUBAN("市合作交流办",1),
	SHIZHENGWUFUWUBAN("市政务服务办",1),
	SHILIANGSHIHEWUZIJU("市粮食和物资局",1,"市粮食局","市物资局"),
	SHIZHISHICHANQUANJU("市知识产权局",1),
	
	TIANJINSHIRENMINZHENGFU("天津市人民政府",1),
	ZHONGGONGTIANJINSHIWEI("中共天津市委",1),
	SHIWEIXUANCHUANBU("市委宣传部",1),
	SHIWEITONGZHANBU("市委统战部",1),	
	SHIKEWEI("市科委",1),
	SHIJIANWEI("市建委",1),
	SHIGUOTUFANGGUANJU("市国土房管局",1),	
	SHIZHONGXIAOQIYEJU("市中小企业局",1),
	SHIGUOSHUIJU("市国税局",1),
	SHIGONGSHANGLIAN("市工商联",1),
	SHIKEXIE("市科协",1),
	SHIZHENGFUBANGONGTING("市政府办公厅",1),
	SHIQILIAN("市企联",1),
	//新增内容----开始------	
	SHICHUBANJU("市出版局",1),	
	SHIANQUANJIANGUANJU("市安全监管局",1),	
	SHIZHUFANGGONGJIJINGUANLIZHONGXIN("市住房公积金管理中心",1),	
	SHISHIRONGYUANLINWEI("市市容园林委",1),
	SHISHENPIBAN("市审批办",1),
	SHIWENHUAGUAGBOYINGSHIJU("市文化广播影视局",1),	
	SHIKOUANBAN("市口岸办",1),	
	SHIHAIYANGJU("市海洋局",1),
	SHIHUANBAOJU("市环保局",1),
		
	//新增内容----结束------
	//--------区级--------
	HEPINGQU("和平区",2),
	HEDONGQU("河东区",2),
	HEXIQU("河西区",2),
	NANKAIQU("南开区",2),
	HEBEIQU("河北区",2),
	HONGQIAOQU("红桥区",2),
	DONGLIQU("东丽区",2),
	XIQINGQU("西青区",2),
	JINNANQU("津南区",2),
	BEICHENQU("北辰区",2),
	WUQINGQU("武清区",2),
	BAODIQU("宝坻区",2),
	BINHAIXINQU("滨海新区",2),
	NINGHEQU("宁河区",2),
	JINGHAIQU("静海区",2),
	JINZHOUQU("蓟州区",2);
	
	// 成员变量  
	/**
	 * 在中小企项目中的表达方式
	 */
    private String name;
    /**
            * 政策级别 0国家级，1市级，2区级
     */
    private int level;    
    /**
              * 别名
     */
    private String alias;
    /**
           *  别名2
     */
    private String alias2;
    // 构造方法  
    private PolicyLevelEnum(String name, int level) {  
        this.name = name;  
        this.level = level; 
        this.alias = null;
    }
    private PolicyLevelEnum(String name, int level,String alias) {  
        this.name = name;  
        this.level = level; 
        this.alias = alias;
    }
    private PolicyLevelEnum(String name, int level,String alias,String alias2) {  
        this(name,level,alias);
        this.alias2 = alias2;
    }
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public int getLevel() {  
        return level;  
    }  
    public void setLevel(int level) {  
        this.level = level;  
    }
    // 普通方法  
    public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	/**
	 * 根据发文部门名称获取政策等级
	 * @param name
	 * @return
	 */
	public static int getLevelByName(String name) {
		if(name==null || "".equals(name)) {
    		return -1;
    	} 
        //先查看是否有真正对应的发文部门名称
    	for (PolicyLevelEnum p : PolicyLevelEnum.values()) {  
            if (p.name.equals(name) || (p.alias!=null && p.alias.equals(name) || (p.alias2!=null && p.alias2.equals(name)))) {  
                return p.level;  
            }  
        } 
    	//说明该发文部门不存在
        return -1;    
    }    
    
	/**
	 * 如果发文部门是多个的时候，政策级别以级别最高的为准
	 * @param names
	 * @return
	 */
	public static int getLevelByMultiNames(String multiNames) {
		if(multiNames == null || "".equals(multiNames)) {
			return -1;
		}
		String[] names = multiNames.split(",");
		if(names == null || names.length==0) {
			return -1;			
		}
		if(names.length == 1) {
			return getLevelByName(names[0].trim());			
		}
		int minLevel = getLevelByName(names[0].trim()); 
		if(minLevel == 0) {
			return 0;				
		}
		for(String str : names) {
			int level = getLevelByName(str.trim());
			if(level != -1 && level < minLevel) {
				minLevel = level;
			}
			if(minLevel == 0) {
				return 0;				
			}			
		}
		return minLevel;
	}
	
    /**
     * 返回国家级的发文部门
     * @param name
     * @return
     */
    public static String getInfoByName(String name) {
    	if(name==null || "".equals(name)) {
    		return null;
    	} 
    	for (PolicyLevelEnum p : PolicyLevelEnum.values()) { 
//            if((name.contains(p.name) || p.name.contains(name)) && p.level == 0) {
//            	return p.name;
//            }
            if(p.name.equals(name) || (p.alias!=null && p.alias.equals(name)) || (p.alias2!=null && p.alias2.equals(name))) {
            	return p.name;
            }
        }  
        return null;    //说明该枚举类中不存在
    }
    
    /**
     * 返回国家级的发文部门
     * @param name
     * @return
     */
    public static String getInfoByNameLike(String name) {
    	if(name==null || "".equals(name)) {
    		return null;
    	} 
    	for (PolicyLevelEnum p : PolicyLevelEnum.values()) { 
            if((name.contains(p.name) || p.name.contains(name) 
            		|| (p.alias!=null && p.alias.contains(name))
            		|| (p.alias!=null && name.contains(p.alias))
            		|| (p.alias2!=null && p.alias2.contains(name))
            		|| (p.alias2!=null && name.contains(p.alias2))
            		) && p.level == 0) {
            	return p.name;
            }
        }  
        return null;    //说明该枚举类中不存在
    }
    
    /**
     *查看发文部门是否存在
     * @param name
     * @return
     */
    public static boolean isNameExists(String name) {
    	if(name==null || "".equals(name)) {
    		return false;
    	}    	
    	for (PolicyLevelEnum p : PolicyLevelEnum.values()) {  
            if(p.name.contains(name) || name.contains(p.name)
            		|| (p.alias!=null && p.alias.contains(name)) 
            		|| (p.alias!=null && name.contains(p.alias))
            		|| (p.alias2!=null && p.alias2.contains(name))
            		|| (p.alias2!=null && name.contains(p.alias2))) {
            	return true;
            }
        }  
        return false;    //说明该枚举类中不存在类似的发文部门
    }
//	var units = new Object();
//	units['国家']=new Array('外交部', '国防部', '发展改革委', '教育部', '科学技术部', '工业和信息化部', '民委', '公安部', '民政部', '司法部', '财政部',
//			'人力资源社会保障部','自然资源部','生态环境部','住房城乡建设部','交通运输部','水利部','农业农村部','商务部','文化和旅游部','卫生健康委','退役军人事务部',
//			'应急管理部','人民银行','审计署','国资委','海关总署','税务总局','市场监督管理总局','国家广播电视总局','体育总局','统计局','国际发展合作署','林业局',
//			'知识产权局','宗教局','参事室','国管局','版权局','侨办','港澳办','国研室','台办','新闻办','新华社','中科院','社科院','工程院','发展研究中心',
//			'行政学院','地震局','气象局','银保监会','证监会','社保基金会','自然科学基金会','信访局','粮食局','能源局','国防科工局','烟草局','外专局','公务员局',
//			'海洋局','测绘地信局','铁路局','民航局','邮政局','文物局','中医药局','外汇局','档案局','密码局','扶贫办','南水北调办');
//	units['市级']=new Array('发展改革委', '教委', '科技局', '工业和信息化局', '民族宗教委', '公安局', '民政局', '司法局', '财政局', '人社局', '规划和自然资源局',
//			'生态环境局','住房城乡建设委','城市管理委','交通运输委','水务局','农业农村委','商务局','市文化和旅游局','卫生健康委','市退役军人局','应急局','审计局','外办',
//			'市场监管委','国资委','体育局','统计局','金融局','信访办','人防办','合作交流办','政务服务办','粮食和物资局','知识产权局');
//	units['区级']=new Array('滨海新区', '和平区', '河北区', '河西区', '河东区', '南开区', '红桥区', '东丽区', '西青区', '津南区', '北辰区','武清区','宝坻区',
//			'静海区','宁河区','蓟州区');
}
