package com.troila.tjsmesp.spider.iface;

public interface DistributedLock {
	boolean lock();
	
	void unlock();
}
