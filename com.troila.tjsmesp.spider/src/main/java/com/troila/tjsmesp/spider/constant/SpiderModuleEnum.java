package com.troila.tjsmesp.spider.constant;

/**
 * 爬取信息对应模块枚举类
 * @author xuanguojing
 *
 */
public enum SpiderModuleEnum {
	
	//对应政策一点通的最新政策
	POLICY_NEWEST("最新政策", 0,"http://zcydt.fzgg.tj.gov.cn","spiderNewest"), 
	//对应政策一点通的政策解读
	POLICY_READING("政策解读",2,"http://zcydt.fzgg.tj.gov.cn","spiderReading"),
	//中小企业信息网-》产业频道-》行业热点
	POLICY_INDUSTRY_FOCUS("行业热点",3,"http://www.sme.gov.cn","spiderIndustryFocus"),
	//中小企业信息网-》政务频道-》地方政府
	POLICY_LOCAL_GOV("地方政府",4,"http://www.sme.gov.cn","spiderLocalGov"),
	//中小企业信息网-》政务频道-》部委动态
	POLICY_MINISTRIES_DYNAMIC("部委动态",5,"http://www.sme.gov.cn","spiderMinistriesDynamic"),
	//中小企业信息网-》新闻资讯-》焦点新闻
	POLICY_NEWS_FOCUS("焦点新闻",6,"http://www.sme.gov.cn","spiderNewestFocus"),
	//天津政务网-》新闻-》各区动态
	POLICY_NEWS_DISTRICT("新闻各区",7,"http://www.tj.gov.cn","spiderNewsDistrict");
	
	//当前爬取的信息的类型
	private String name;  
    private int index;  
    private String siteUrl;
    private String key;
    

    private SpiderModuleEnum(String name, int index) {  
        this.name = name;  
        this.index = index;  
    }
    
    private SpiderModuleEnum(String name, int index, String siteUrl) {
		this(name,index);
		this.siteUrl = siteUrl;
	}
    
    private SpiderModuleEnum(String name, int index, String siteUrl,String key) {
		this(name,index,siteUrl);
		this.key = key;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public int getIndex() {  
        return index;  
    }  
    public void setIndex(int index) {  
        this.index = index;  
    }

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	//通过索引获取key值  
    public static String getKey(int index) {  
        for (SpiderModuleEnum s : SpiderModuleEnum.values()) {  
            if (s.getIndex() == index) {  
                return s.key;  
            }  
        }  
        return null;    //说明该枚举类中不存在
    }  
}
