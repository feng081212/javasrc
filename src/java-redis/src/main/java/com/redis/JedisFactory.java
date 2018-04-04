package com.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public interface JedisFactory {

	public static final int GET_JEDIS_MAX_TURN = 3 ;
	public static final long JEDIS_TIMEOUT = 60 * 1000 ;
	public static final int JEDIS_MAX_INSTANCE = 100 ;
	
	/**
	 * 获取JedisPool连接池
	 * @return
	 */
	JedisPool getJedisPool() ;
	
	/**
	 * 销毁JedisPool连接池
	 */
	void destroyJedisPool() ;
	
	/**
	 * 从JedisPool连接池中获取Jedis实例
	 * @return
	 */
	Jedis getJedis() ;
	
	/**
	 * 关闭Jedis实例
	 * @return
	 */
	void closeJedis(Jedis jedis) ;
	
//	/**
//	 * Jedis 如果超出时间未释放，系统自动释放
//	 * @return Jedis最大有效时间（毫秒）
//	 */
//	long getJedisTimeOut() ;
//	
//	/**
//	 * 释放超出Jedis最大有效时间的实例
//	 * @see JedisFactory#getJedisTimeOut() 最大有效时间
//	 */
//	void cleanTimeOutJedis() ;
//	
//	/**
//	 * Jedis 最大实例数
//	 * @return
//	 */
//	int maxJedisInstance() ;
}
