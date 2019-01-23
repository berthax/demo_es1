package com.troila.tjsmesp.spider.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	/**
	 * 去除字符串首尾的空白
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str){
		  Pattern pt=Pattern.compile("^\\s*|\\s*$");
		  Matcher mt=pt.matcher(str);
		  str=mt.replaceAll("");
		  //12288为全角空格，一般trim（）方法无法去除
		  str = str.replace((char)12288, ' ');  
		  return str.trim();
		 }
}
