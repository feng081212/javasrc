package com.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.redis.operate.RedisHashesOperate;
import com.redis.operate.RedisHyperLogLogOperate;
import com.redis.operate.RedisKeyOperate;
import com.redis.operate.RedisListOperate;
import com.redis.operate.RedisScriptOperate;
import com.redis.operate.RedisSetOperate;
import com.redis.operate.RedisSortedSetOperate;
import com.redis.operate.RedisStringOperate;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

public abstract class AbstractRedis implements JedisFactory , RedisKeyOperate , RedisStringOperate , RedisScriptOperate ,
	RedisHashesOperate , RedisListOperate , RedisSetOperate , RedisSortedSetOperate , RedisHyperLogLogOperate , AutoCloseable {
	
	@Override
	public Jedis getJedis() {
		Jedis jedis = null ;
		try{
			jedis = getJedisPool().getResource() ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jedis ;
	}
	
	@Override
	public void closeJedis(Jedis jedis){
		if(jedis != null){
			jedis.close();
			jedis = null ;
		}
	}
	
	@Override
	public void close() throws Exception {
		
	}
	
	@Override
	public boolean del(String key){
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.del(key) == 1 ;
		} finally{
			closeJedis(jedis);
		}
	}
	
	@Override
	public long del(String... key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.del(key) ;
		}finally{
			closeJedis(jedis);
		}
	}
	
	@Override
	public boolean exists(String key){
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.exists(key) ;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public boolean expire(String key, int seconds) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.expire(key, seconds) == 1 ;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public boolean expireAt(String key, long unixTime) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.expireAt(key, unixTime) == 1;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public boolean pexpire(String key, long milliseconds) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.pexpire(key, milliseconds) == 1;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public boolean pexpireAt(String key, long millisecondsTimestamp) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.pexpireAt(key, millisecondsTimestamp) == 1;
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public Set<String> keys(String key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.keys(key) ;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public boolean persist(String key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.persist(key) == 1 ;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long ttl(String key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.ttl(key) ;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long pttl(String key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.pttl(key);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public String randomKey() {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.randomKey() ;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public boolean rename(String oldKey, String newKey) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.rename(oldKey, newKey).equalsIgnoreCase("ok") ;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public boolean renamenx(String oldKey, String newKey) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.renamenx(oldKey, newKey) == 1;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public String type(String key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.type(key) ;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public boolean set(String key, String value) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.set(key, value).equalsIgnoreCase("ok");
		}finally{
			closeJedis(jedis);
		}
	}
	
	@Override
	public boolean setex(String key, int seconds, String value) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.setex(key, seconds, value).equalsIgnoreCase("ok");
		}finally{
			closeJedis(jedis);
		}
	}
	
	@Override
	public boolean psetex(String key, long milliseconds, String value) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.psetex(key, milliseconds, value).equalsIgnoreCase("ok");
		}finally{
			closeJedis(jedis);
		}
	}
	
	@Override
	public boolean setnx(String key, String value) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.setnx(key, value) == 1 ;
		}finally{
			closeJedis(jedis);
		}
	}
	
	@Override
	public long setrange(String key, long offset, String value) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.setrange(key, offset, value);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public String get(String key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.get(key) ;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public String getrange(String key, long startOffset, long endOffset) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.getrange(key, startOffset, endOffset) ;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public String getSet(String key, String value) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.getSet(key, value) ;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public List<String> mget(String... keys) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.mget(keys) ;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long strlen(String key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.strlen(key) ;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long incr(String key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.incr(key) ;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long incrBy(String key, long integer) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.incrBy(key, integer);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public double incrByFloat(String key, double value) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.incrByFloat(key, value);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long decr(String key) {
		
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.decr(key);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long decrBy(String key, long integer) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.decrBy(key, integer);
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public long append(String key, String value) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.append(key, value);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public boolean hset(String key, String hasheskey, String hashesvalue) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.hset(key, hasheskey, hashesvalue) == 1 ;
		}finally{
			closeJedis(jedis);
		}
		
	}
	

	@Override
	public boolean hsetnx(String key, String hasheskey, String hashesvalue) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.hsetnx(key, hasheskey, hashesvalue) == 1;
		}finally{
			closeJedis(jedis);
		}
	}
	
	@Override
	public boolean hmset(String key, Map<String, String> values) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.hmset(key, values).equalsIgnoreCase("ok");
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public boolean hdel(String key, String hasheskey) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.hdel(key, hasheskey) == 1;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public boolean hexists(String key, String hasheskey) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.hexists(key, hasheskey);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public String hget(String key, String hasheskey) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.hget(key, hasheskey);
		}finally{
			closeJedis(jedis);
		}
		
	}
	
	@Override
	public List<String> hmget(String key, String... hasheskey) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.hmget(key, hasheskey);
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.hgetAll(key);
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public Long hincrBy(String key, String hasheskey,long value) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.hincrBy(key, hasheskey, value);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public Double hincrByFloat(String key, String hasheskey, double value) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.hincrByFloat(key, hasheskey, value);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public Set<String> hkeys(String key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.hkeys(key);
		}finally{
			closeJedis(jedis);
		}
	}
	
	@Override
	public List<String> hvals(String key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.hvals(key);
		}finally{
			closeJedis(jedis);
		}
	}


	@Override
	public Long hlen(String key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.hlen(key);
		}finally{
			closeJedis(jedis);
		}
	}
	
	@Override
	public String lpop(String key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.lpop(key) ;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public List<String> blpop(String key,int timeout) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.blpop(timeout, key);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public String rpop(String key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.rpop(key);
		}finally{
			closeJedis(jedis);
		}
	}
	
	@Override
	public List<String> brpop(String key, int timeout) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.brpop(timeout, key);
		}finally{
			closeJedis(jedis);
		}
	}
	
	@Override
	public String rpoplpush(String oldkey, String newkey) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.rpoplpush(oldkey, newkey);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public String brpoplpush(String oldkey, String newkey, int timeout) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.brpoplpush(oldkey, newkey, timeout);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public String lindex(String key, long index) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.lindex(key, index);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long linsertBefore(String key, String signKey,String value) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.linsert(key, LIST_POSITION.BEFORE, signKey, value);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long linsertAfter(String key, String signKey,String value) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.linsert(key, LIST_POSITION.AFTER, signKey, value);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long llen(String key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.llen(key);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long lpush(String key, String... values) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.lpush(key, values);
		}finally{
			closeJedis(jedis);
		}
	}
	
	@Override
	public long rpush(String key, String... values) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.rpush(key, values);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long lpushx(String key, String... values) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.lpushx(key, values);
		}finally{
			closeJedis(jedis);
		}
	}
	
	@Override
	public long rpushx(String key, String... values) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.rpushx(key, values);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public List<String> lrange(String key, long start, long end) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.lrange(key, start, end);
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public long lrem(String key, long count,String value) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.lrem(key, count, value);
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public boolean lset(String key, long index, String value) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.lset(key, index, value).equalsIgnoreCase("ok");
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public boolean ltrim(String key, long start, long end) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.ltrim(key, start, end).equalsIgnoreCase("ok");
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public long sadd(String key, String... values) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.sadd(key, values);
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public long scard(String key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.scard(key) ;
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public Set<String> sdiff(String... keys) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.sdiff(keys);
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public long sdiffstore(String key, String... keys) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.sdiffstore(key, keys);
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public Set<String> sinter(String... keys) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.sinter(keys) ;
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public long sinterstore(String key, String... keys) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.sinterstore(key, keys);
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public boolean sismember(String key, String value) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.sismember(key, value);
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public Set<String> smembers(String key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.smembers(key) ;
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public boolean smove(String oldkey, String newkey,String value) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.smove(oldkey, newkey, value) == 1;
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public String spop(String key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.spop(key) ;
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public List<String> srandmember(String key, int count) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.srandmember(key, count);
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public long srem(String key, String... value) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.srem(key, value);
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public Set<String> sunion(String... keys) {
		
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.sunion(keys);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long sunionstore(String destination, String... keys) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.sunionstore(destination, keys);
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public ScanResult<String> sscan(String key,String cursor,ScanParams scanParams) {
		
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.sscan(key, cursor, scanParams);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long zadd(String key,double score, String value) {
		
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.zadd(key, score,value);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long zcard(String key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.zcard(key);
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public long zcount(String key, long min, long max) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.zcount(key, min, max);
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public double zincrby(String key, double score, String value) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.zincrby(key, score, value) ;
		}finally{
			closeJedis(jedis);
		}
		
	}

	@Override
	public long zinterstore(String destination, String... keys) {
		
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.zinterstore(destination, keys) ;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long zlexcount(String key, String min, String max) {
		
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.zlexcount(key, min, max);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public Set<String> zrange(String key, long min, long max) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.zrange(key, min, max) ;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public Set<String> zrangeByLex(String key, String min, String max) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.zrangeByLex(key, min, max);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.zrangeByScore(key, min, max);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long zrank(String key, String value) {
		
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.zrank(key, value);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long zrem(String key, String... values) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.zrem(key, values);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long zremrangeByLex(String key, String min, String max) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.zremrangeByLex(key, min, max);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long zremrangeByRank(String key, long min, long max) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.zremrangeByRank(key, min, max);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long zremrangeByScore(String key, double min, double max) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.zremrangeByScore(key, min, max);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public Set<String> zrevrange(String key, long min, long max) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.zrevrange(key, min, max);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public Set<String> zrevrangeByScore(String key, String min, String max) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.zrevrangeByScore(key, max, min);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long zrevrank(String key, String value) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.zrevrank(key, value);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public double zscore(String key, String value) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.zscore(key, value);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long zunionstore(String destination, String... keys) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.zunionstore(destination, keys);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public ScanResult<Tuple> zscan(String key, String cursor) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.zscan(key, cursor) ;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public boolean pfadd(String key, String... values) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.pfadd(key, values) == 1;
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public long pfcount(String key) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.pfcount(key);
		}finally{
			closeJedis(jedis);
		}
	}

	@Override
	public boolean pfmerge(String destkey, String... sourcekey) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.pfmerge(destkey, sourcekey).equalsIgnoreCase("ok");
		}finally{
			closeJedis(jedis);
		}
	}
	
	@Override
	public Object eval(String script, List<String> keys, List<String> args) {
		Jedis jedis = null ;
		try{
			jedis = getJedis() ;
			return jedis.eval(script, keys, args);
		}finally{
			closeJedis(jedis);
		}
	}
}
