package com.redis.impl;

import com.redis.JedisPoolBuilder;

import redis.clients.jedis.JedisPool;

public class JedisPoolHolder {

	private static JedisPool DATA_CENTER_JEDIS_POOL = null ;
	
	public static JedisPool getDataCenterJedisPool() {
		if(DATA_CENTER_JEDIS_POOL == null){
			synchronized (JedisPoolHolder.class) {
				if(DATA_CENTER_JEDIS_POOL == null){
					initDataCenterJedisPool();
				}
			}
		}
		return DATA_CENTER_JEDIS_POOL;
	}
	
	public static void destroyDataCenterJedisPool() {
		if(DATA_CENTER_JEDIS_POOL != null){
			DATA_CENTER_JEDIS_POOL.destroy();
			DATA_CENTER_JEDIS_POOL = null ;
		}
	}
	
	private static void initDataCenterJedisPool() {
//		String ip = DBCode.getRedis("redis.ip");
//		int port = Integer.valueOf(DBCode.getRedis("redis.port"));
//		String password = DBCode.getRedis("redis.auth");
//		int maxTotal = Integer.valueOf(DBCode.getRedis("jedis.pool.maxActive"));
//		int maxIdle = Integer.valueOf(DBCode.getRedis("jedis.pool.maxIdle"));
//		long maxWaitMillis = Long.valueOf(DBCode.getRedis("jedis.pool.maxWait"));
//		boolean testOnBorrow = Boolean.valueOf(DBCode.getRedis("jedis.pool.testOnBorrow"));
//		boolean testOnReturn = Boolean.valueOf(DBCode.getRedis("jedis.pool.testOnReturn"));
//		int timeout = Integer.valueOf(DBCode.getRedis("timeout"));
//		if(password == null || password.trim().length() == 0 || password.trim().equalsIgnoreCase("null")){
//			DATA_CENTER_JEDIS_POOL = JedisPoolFactory.getJedisPool(maxTotal, maxIdle, maxWaitMillis, testOnBorrow, testOnReturn, ip, port, timeout) ;
//		} else {
//			DATA_CENTER_JEDIS_POOL = JedisPoolFactory.getJedisPool(maxTotal, maxIdle, maxWaitMillis, testOnBorrow, testOnReturn, ip, port, timeout, password) ;
//		}
		DATA_CENTER_JEDIS_POOL = JedisPoolBuilder.getJedisPool(10, 10, 1000, true, true, "localhost", 6379, 3000) ;
	}
	
	
}
