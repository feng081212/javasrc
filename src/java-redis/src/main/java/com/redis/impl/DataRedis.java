package com.redis.impl;

import redis.clients.jedis.JedisPool;

import com.redis.AbstractRedis;

public class DataRedis extends AbstractRedis {

	@Override
	public JedisPool getJedisPool() {
		return JedisPoolHolder.getDataCenterJedisPool() ;
	}

	@Override
	public void destroyJedisPool() {
		JedisPoolHolder.destroyDataCenterJedisPool() ;
	}
}
