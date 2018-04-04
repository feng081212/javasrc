package com.redis.lock;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

import com.lock.Locker;
import com.log.LogFactory;
import com.random.UuId;

public abstract class AbstractLockRedis implements Locker {

	public abstract Jedis getJedis() ;
	
	/**
	 * 最大锁定时间，单位：毫秒
	 * @param key
	 * @return
	 */
	public abstract long maxLockTime() ;
	
	protected String value = "" ;
	
	protected String key = "" ;
	
	public AbstractLockRedis(String key) {
		value = UuId.getUUID() ;
	}

	@Override
	public boolean lock() {
		Jedis jedis = null;
        try {
            jedis = getJedis() ;
            String luaScript = "local r = tonumber(redis.call('SETNX', KEYS[1],ARGV[1])) if r == 1 then redis.call('PEXPIRE',KEYS[1],ARGV[2]) end return r" ;
            List<String> keys = new ArrayList<String>();
            keys.add(key);
            List<String> args = new ArrayList<String>();
            args.add(value);
            args.add(maxLockTime()+"");
            return Long.valueOf(jedis.eval(luaScript, keys, args) + "") == 1;
        } catch (JedisException e) {
        	LogFactory.error(e.getMessage());
        } finally {
        	closeJedis(jedis) ;
        }
		return false;
	}

	@Override
	public boolean unlock() {
		Jedis jedis = null;
        try {
            jedis = getJedis() ;
            String luaScript="local v = redis.call('GET', KEYS[1]) local r = 0 if v == ARGV[1] then r = redis.call('DEL',KEYS[1]) end return r" ;
            List<String> keys = new ArrayList<String>();
            keys.add(key);
            List<String> args = new ArrayList<String>();
            args.add(value);
            jedis.eval(luaScript, keys, args);
            return true ;
        } catch (JedisException e) {
        	LogFactory.error(e.getMessage());
        } finally {
        	closeJedis(jedis);
        }
		return false;
	}

	protected void closeJedis(Jedis jedis) {
		if(jedis != null){
			jedis.close();
		}
	}
}
