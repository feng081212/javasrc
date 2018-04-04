package com.redis.impl;


public class RedisHolder {
	
	public static DataRedis getDataRedis() {
		return DataRedisPool.getDataRedis() ;
	}
	
}
