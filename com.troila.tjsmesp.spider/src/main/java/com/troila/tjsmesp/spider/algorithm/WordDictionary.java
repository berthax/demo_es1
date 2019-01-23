package com.troila.tjsmesp.spider.algorithm;

public interface WordDictionary {
	public String selectAndReplace(String uri,String key,String value);
	
	public int getOrder();
}
