package com.troila.tjsmesp.spider.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * java获取当前时间前一周、前一月、前一年的时间 
 * @author xuanguojing
 *
 */
public class TimeUtils {
	
	/**
	 * 注意SimpleDateFormat是线程不安全的，使用的时候要注意
	 */
	//短时间格式
	public volatile static SimpleDateFormat shortDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	//长时间格式
	public static SimpleDateFormat longDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
	/**
	 * 获取前一周的时间
	 * @return
	 */
	public static Date getLastWeek() {
		Calendar c = Calendar.getInstance(); 
		c.setTime(new Date());
	    c.add(Calendar.DATE, - 7); 
	    Date lastWeek = c.getTime();
//	    System.out.println("过去七天："+day);
	    return lastWeek;
	}
	
	/**
	 * 获取过去n天的时间
	 * @param n
	 * @return
	 */
	public static Date getLastNDay(int n) {
		Calendar c = Calendar.getInstance(); 
		c.setTime(new Date());
	    c.add(Calendar.DATE, - n); 
	    Date lastNDay = c.getTime();
	    return lastNDay;
	}
	
	/**
	 * 获取前一个月的时间
	 * @return
	 */
    public static Date getLastMonth() {
        Calendar c = Calendar.getInstance();
        //过去一月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date lastMonth = c.getTime();
        String mon = longDateFormat.format(lastMonth);
//        System.out.println("过去一个月："+mon);
        return lastMonth;
    }
    
    /**
     * 获取前一年的时间
     * @return
     */
    public static Date getLastYear() {
    	Calendar c = Calendar.getInstance();
    	c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Date lastYear = c.getTime();
        String year = longDateFormat.format(lastYear);
//        System.out.println("过去一年："+year);
        return lastYear;
    }
    
    /**
     * 返回指定的短格式时间"yyyy-MM-dd"
     * @param formatter
     * @param date
     * @return
     */
    public static Date getShortFormatDate(Date date) {
    	if(date == null) {
    		return null; 		
    	}
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(date);
	    Date date_res = null;
		try {
			date_res = formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return date_res;
    }
    
    /**
     * 返回指定格式的短格式时间
     * @param formatter
     * @param date
     * @return
     */
    public static Date getShortFormatDate(String dateStr) {
    	if(dateStr == null || "".equals(dateStr)) {
    		return null; 		
    	}
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    Date date_res = null;
		try {
			date_res = formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return date_res;
    }
    
    /**
     * 返回指定的长格式时间"yyyy-MM-dd HH:mm:ss"
     * @param formatter
     * @param date
     * @return
     */
    public static Date getLongFormatDate(Date date) {
    	if(date == null) {
    		return null; 		
    	}
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(date);
	    Date date_res = null;
		try {
			date_res = formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return date_res;
    }
    
    /**
     * 返回指定的长格式时间"yyyy-MM-dd HH:mm:ss"
     * @param formatter
     * @param date
     * @return
     */
    public static Date getLongFormatDate(String dateStr) {
    	if(dateStr == null || "".equals(dateStr)) {
    		return null; 		
    	}
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date_res = null;
		try {
			date_res = formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return date_res;
    }
    
    /**
     * 返回用户指定格式的时间
     * @param formatter
     * @param date
     * @return
     */
    public static Date getFormatDate(SimpleDateFormat formatter,Date date) {
    	if(date == null) {
    		return null; 		
    	}
	    String dateString = formatter.format(date);
	    Date date_res = null;
		try {
			date_res = formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return date_res;
    }
    
    
    /**
     * 返回用户指定格式的时间
     * @param formatter
     * @param date
     * @return
     */
    public static Date getFormatDate(SimpleDateFormat formatter,String dateStr) {
    	if(dateStr == null) {
    		return null; 		
    	}

	    Date date_res = null;
		try {
			date_res = formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return date_res;
    }
    
    public static void main(String[] args) {
/*		getLastWeek();
		
		getLastMonth();
		
		getLastYear();
		
		Date date = getFormatDate(longDateFormat, new Date());
		System.out.println(date);	
		
		String msg = "全国人大常委会,国务院,国家能源局,自然资源部,中央网信办,民政部,国务院办公厅,工信部";
		System.out.println(msg.length());*/
		
//		System.out.println("金融税收".hashCode());  //1146410436
//		System.out.println("财税金融".hashCode());  //1107568136
//		System.out.println("不限".hashCode());     //657891
//		System.out.println("综合政策".hashCode());  //989953923
//		System.out.println("创业创新".hashCode());  //646068884  已有
//		System.out.println("人才引进".hashCode());  //625382777
//		System.out.println("知识产权".hashCode());  //949412285
//		System.out.println("招商引资".hashCode());  //775690202
//		System.out.println("资金扶持".hashCode());  //1114041624 已有
//		System.out.println("产业指导".hashCode());  //619865448    已有
		
    	
    	Date date = getLastNDay(36);
    	System.out.println(date);
	}
    
}
