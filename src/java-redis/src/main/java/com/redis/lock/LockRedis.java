package com.redis.lock;

import com.redis.impl.DataRedisPool;

import redis.clients.jedis.Jedis;

public class LockRedis extends AbstractLockRedis {
	
	public LockRedis(String key) {
		super(key);
	}

	@Override
	public Jedis getJedis() {
		return DataRedisPool.getDataRedis().getJedis() ;
	}

	@Override
	public long maxLockTime() {
		return 120 * 1000 ;
	}
}
