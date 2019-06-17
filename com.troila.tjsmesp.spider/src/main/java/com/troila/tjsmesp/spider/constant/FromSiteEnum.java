package com.troila.tjsmesp.spider.constant;

public enum FromSiteEnum {
	/**
	 * 天津政策一点通
	 */
	TIANJINZHEGNCEYIDIANTONG("政策一点通","http://zcydt.fzgg.tj.gov.cn"),
	/**
	 * 天津政务网
	 */
	TIANJINZHENGWUWANG("天津政务网","http://www.tj.gov.cn"),
	/**
	 * 中小企业信息网
	 */
	ZHONGXIAOQIYEXINXIWANG("中小企业信息网","http://sme.miit.gov.cn");
	
	/**
	 * 转载网站的名称
	 */
	private String name;
	
	/**
	 * 转载网站的链接地址
	 */
	private String link;

	private FromSiteEnum(String name, String link) {
		this.name = name;
		this.link = link;
	}

	public String getName() {
		return name;
	}

	public String getLink() {
		return link;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	/**
	 * 	通过网站名称获取链接地址  
	 * @param name
	 * @return
	 */
    public static String getKey(String name) {  
        for (FromSiteEnum s : FromSiteEnum.values()) {  
            if (s.getName().equals(name)) {  
                return s.link;  
            }  
        }  
        return null;    //说明该枚举类中不存在
    }  
}
