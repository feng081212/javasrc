package com.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolBuilder {
	
	/**
	 * @param maxTotal 			最多可分配多少个Jedis实例
	 * @param maxIdle			最多可分配多少个状态为idle的Jedis实例
	 * @param maxWaitMillis		最大的等待时间
	 * @param testOnBorrow		获取一个Jedis实例时，是否提前进行alidate操作；如果为true，则得到的Jedis实例均是可用的
	 * @param testOnReturn		在return给pool时，是否提前进行validate操作
	 * @param ip				Redis的IP地址
	 * @param port				Redis的端口号
	 * @param timeout			超时时间
	 * @return
	 */
	public final static synchronized JedisPool getJedisPool(int maxTotal,int maxIdle,long maxWaitMillis,boolean testOnBorrow,boolean testOnReturn,String ip,int port,int timeout){
		JedisPoolConfig config = new JedisPoolConfig();
    	config.setMaxTotal(maxTotal);
    	config.setMaxIdle(maxIdle);
    	config.setMaxWaitMillis(maxWaitMillis);
    	config.setTestOnBorrow(testOnBorrow);
    	config.setTestOnReturn(testOnReturn);
		return getJedisPool(config,ip,port,timeout) ;
	}
	
	/**
	 * @param maxTotal 			最多可分配多少个Jedis实例
	 * @param maxIdle			最多可分配多少个状态为idle的Jedis实例
	 * @param maxWaitMillis		最大的等待时间
	 * @param testOnBorrow		获取一个Jedis实例时，是否提前进行alidate操作；如果为true，则得到的Jedis实例均是可用的
	 * @param testOnReturn		在return给pool时，是否提前进行validate操作
	 * @param ip				Redis的IP地址
	 * @param port				Redis的端口号
	 * @param timeout			超时时间
	 * @param password			密码
	 * @return
	 */
	public final static synchronized JedisPool getJedisPool(int maxTotal,int maxIdle,long maxWaitMillis,boolean testOnBorrow,boolean testOnReturn,String ip,int port,int timeout,String password){
		JedisPoolConfig config = new JedisPoolConfig();
    	config.setMaxTotal(maxTotal);
    	config.setMaxIdle(maxIdle);
    	config.setMaxWaitMillis(maxWaitMillis);
    	config.setTestOnBorrow(testOnBorrow);
    	config.setTestOnReturn(testOnReturn);
		return getJedisPool(config,ip,port,timeout,password) ;
	}
	
	/**
	 * @param config 			JedisPoolConfig实例，用于设置JedisPool的参数
	 * @param ip				Redis的IP地址
	 * @param port				Redis的端口号
	 * @param timeout			超时时间
	 * @return
	 */
	public final static synchronized JedisPool getJedisPool(JedisPoolConfig config,String ip,int port,int timeout){
		return new JedisPool(config,ip,port,timeout) ;
	}
	
	/**
	 * @param config 			JedisPoolConfig实例，用于设置JedisPool的参数
	 * @param ip				Redis的IP地址
	 * @param port				Redis的端口号
	 * @param timeout			超时时间
	 * @param password			密码
	 * @return
	 */
	public final static synchronized JedisPool getJedisPool(JedisPoolConfig config,String ip,int port,int timeout,String password){
		return new JedisPool(config,ip,port,timeout,password) ;
	}
}
