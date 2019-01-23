package com.troila.tjsmesp.spider.constant;

/**
 * 所有的错误信息的统计
 * @author xuanguojing
 *
 */
public enum ErrorInfo {
	NOTFOUND("查询未找到", 4), 
	QUERYERROR("查询过程出错",5),
	BADREQUEST("错误的请求", 6), 
	SQLERROR("操作数据库失败", 7);
	
    private String name;  
    private int index;  

    private ErrorInfo(String name, int index) {  
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

    public static String getName(int index) {  
        for (ErrorInfo error : ErrorInfo.values()) {  
            if (error.getIndex() == index) {  
                return error.name;  
            }  
        }  
        return null;    //说明该枚举类中不存在
    }    
}
