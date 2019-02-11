package com.troila.tjsmesp.spider.constant;
/**
 * 外部获取的政策数据类别的枚举类
 * @author xuanguojing
 *
 */
public enum Category {
	
	SHELIBIANGEGN("设立变更", 74), 
	ZHUNYINGZHUNBAN("准营准办", 75), 
	ZIZHIRENZHENG("资质认证", 76), 
	NIANJIANNIANSHEN("年检年审", 77),  
	SHUISHOUCAIWU("税收财务",78),	
	RENLIZIYUAN("人力资源",79),
	SHEHUIBAOZHANG("社会保障",80),	
	
	TOUZISHENPI("投资审批",81),	
	RONGZIXINDAI("融资信贷",82),
	DIYAZHIYA("抵押质押",83),
	SHANGWUMAOYI("商务贸易",84),
	ZHAOBIAOPAIMAI("招标拍卖",85),
	HAIGUANKOUAN("海关口岸",86),
	SHEWAIFUWU("涉外服务",87),	
	NONGLINMUYU("农林牧渔",88),
	GUOTUHEGUIHUAJIANSHE("国土和规划建设",89),
	JIAOTONGYUNSHU("交通运输",90),
	
	HUANBAOLVHUA("环保绿化",91),	
	YINGDUIQIHOUBIANHUA("应对气候变化",92),
	SHUIWUQIXIANG("水务气象",93),
	YILIAOWEISHENG("医疗卫生",94),
	KEJICHUANGXIN("科技创新",95),
	WENTIJIAOYU("文体教育",96),
	ZHISHICHANQUAN("知识产权",97),
	MINZUZONGJIAO("民族宗教",98),
	ZHILIANGJISHU("质量技术",99),
	JIANYANJIANYI("检验检疫",100),

	ANQUANSHENGCHAN("安全生产",101),
	GONGANXIAOFANG("公安消防",102),
	SIFAGONGZHENG("司法公证",103),
	GONGYONGSHIYE("公用事业",104),
	FARENZHUXIAO("法人注销",105),
	DANGANWENWU("档案文物",106),
	QITA("其他",107);
		
    // 成员变量  
    private String name;  
    private int index;  
    // 构造方法  
    private Category(String name, int index) {  
        this.name = name;  
        this.index = index;  
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
    // 普通方法  
    public static String getName(int index) {  
        for (Category c : Category.values()) {  
            if (c.getIndex() == index) {  
                return c.name;  
            }  
        }  
        return null;    //说明该枚举类中不存在
    }    
}
