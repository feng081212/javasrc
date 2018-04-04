package com.redis.pool;

import java.util.List;
import java.util.Set;

import com.pool.Pool;
import com.redis.AbstractRedis;

/**
 * Hash（哈希表）Redis缓存
 * @author Administrator
 *
 * @param <F> 域
 * @param <V> 值
 */
public abstract class AbstractRedisHashPool<F,V> implements Pool<F, V> {
	/** REDIS资源 */
	protected abstract AbstractRedis getRedis() ;
	/** REDIS KEY值 */
	protected abstract String key() ;
	/** 将FIELD值转换成字符串 */
	protected abstract String stringField(F k) ;
	/** 将FIELD值的字符串形式还原成对象 */
	protected abstract F restoreField(String str) ;
	/** 将VALUE值转换成字符串 */
	protected abstract String stringValue(V v) ;
	/** 将VALUE值的字符串形式还原成对象 */
	protected abstract V restoreValue(String str) ;
	/** 初始化 */
	protected abstract void init() ;
	
	/** KEY下的所有FIELD值 */
	public Set<String> keys() {
		if(!isActive()){
			init() ;
		}
		return getRedis().hkeys(key()) ;
	}
	
	/** 所有VALUE值 */
	public List<String> values(){
		if(!isActive()){
			init() ;
		}
		return getRedis().hvals(key()) ;
	}
	
	/** 是否存在KEY */
	public boolean isActive(){
		return getRedis().exists(key()) ;
	}
	
	/** 刷新KEY的过期时间，过期时间=当前时间+最大生命周期  */
	private void expire(){
		getRedis().pexpireAt(key(), System.currentTimeMillis() + maxLifeTime()) ;
	}
	
	@Override
	public int add(F f, V v) {
		if(f == null || v == null){
			return 0 ;
		}
		expire() ;
		boolean boo = getRedis().hsetnx(key(),stringField(f), stringValue(v)) ;
		return boo ? 1 : 0 ;
	}
	
	@Override
	public V remove(F f) {
		if(f != null){
			getRedis().hdel(key(),stringField(f));
		}
		return null ;
	}
	
	@Override
	public int notify(F f, V v) {
		if(f == null || v == null){
			return 0;
		}
		expire() ;
		boolean boo = getRedis().hset(key(),stringField(f), stringValue(v)) ;
		return boo ? 1 : 0 ;
	}
	
	@Override
	public V get(F f) {
		if(f == null){
			return null ;
		}
		if(!isActive()){
			init() ;
		}
		try{
			if(!contains(f)){
				V v = newInstance(f) ;
				if(v != null){
					add(f,v);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		String value = getRedis().hget(key(),stringField(f)) ;
		if(value == null || value.trim().length() == 0 || value.equals("nil")){
			return null ;
		}
		return restoreValue(value);
	}
	
	@Override
	public boolean contains(F f) {
		return getRedis().hexists(key(),stringField(f));
	}
	
	@Override
	public synchronized int clear() {
		boolean boo = getRedis().del(key()) ;
		return boo ? 1 : 0 ;
	}
	
	@Override
	public int amount() {
		return getRedis().hlen(key()).intValue() ;
	}
	
	@Override
	public int maxAmount() {
		return DEFAULT_MAX_OBJECT_AMOUNT ;
	}
	
	@Override
	public int maxLifeTime() {
		return DEFAULT_OBJECT_MAX_LIFE_TIME ;
	}
	
	@Override
	public synchronized void clearRubbish() {
		
	}
}
