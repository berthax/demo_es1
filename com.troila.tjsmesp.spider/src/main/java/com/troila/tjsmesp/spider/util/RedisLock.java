package com.troila.tjsmesp.spider.util;

import org.springframework.data.redis.core.StringRedisTemplate;

import com.troila.tjsmesp.spider.iface.DistributedLock;

public class RedisLock implements DistributedLock {

	private StringRedisTemplate redisTemplate;
	
	private long expireTime = 60 *1000; //默认超时时间

	private static final String LOCK_SUFFIX = "_reids_lock";
	
	private String lockKey;
	
    private volatile boolean locked = false;
	

	public RedisLock(StringRedisTemplate redisTemplate, String lockKey) {
		super();
		this.redisTemplate = redisTemplate;
		this.lockKey = lockKey+LOCK_SUFFIX;
	}

	private boolean setNx(String key,String value) {
		return this.redisTemplate.opsForValue().setIfAbsent(key, value);
	}
	
	private String getSet(String key,String value) {
		return redisTemplate.opsForValue().getAndSet(key, value);
	}
	
	public boolean isLocked() {
		return locked;
	}

	@Override
	public synchronized boolean lock() {
		//过期时间，避免因为程序崩溃导致永久性死锁
		long expires = System.currentTimeMillis() + expireTime;
		String expiresStr = String.valueOf(expires);
		locked = this.setNx(lockKey, expiresStr);
		if(locked) {
			return true;
		}
		//如果已经存在值了，判断是否过期
		String currentValue = this.redisTemplate.opsForValue().get(lockKey);
		if(currentValue != null && Long.parseLong(currentValue) < System.currentTimeMillis()) {
			String oldValue = this.getSet(lockKey, expiresStr);
			//比较锁的时间，不一致代表其他的服务已经获取了锁
			if(oldValue!=null && oldValue.equals(currentValue)) {
				locked = true;
				return true;
			}
		}
		return false;
	}

	@Override
	public synchronized void unlock() {
		if(locked) {
			this.redisTemplate.delete(lockKey);
			locked = false;
		}
	}
	
}
