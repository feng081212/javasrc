package com.redis.impl;

import com.pool.AbstractPool;

public class DataRedisPool extends AbstractPool<Thread, DataRedis> {

	public static DataRedisPool dataRedisPool = null ;
	
	@Override
	public DataRedis newInstance(Thread k) {
		return new DataRedis() ;
	}

	public final static DataRedis getDataRedis(){
		if(dataRedisPool == null)
			dataRedisPool = new DataRedisPool() ;
		return dataRedisPool.get(Thread.currentThread()) ;
	}

	@Override
	public int maxAmount() {
		try{
			return 100 ;
		}catch(Exception e){
			return super.maxAmount() ;
		}
	}
	
	@Override
	public int maxLifeTime() {
		return 1 * 60 * 1000 ;
	}
}
