package com.pool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unchecked")
public class PoolHolder {

	private final static Map<Class<?>,Pool<?,?>> POOL = new ConcurrentHashMap<Class<?>,Pool<?,?>>() ;
	
	public final static <T extends Pool<K,V>,K,V> T get(Class<T> SELF_CLASS) {
		if(!POOL.containsKey(SELF_CLASS) || POOL.get(SELF_CLASS) == null){
			synchronized (SELF_CLASS) {
				try {
					if(!POOL.containsKey(SELF_CLASS) || POOL.get(SELF_CLASS) == null){
						POOL.put(SELF_CLASS, SELF_CLASS.newInstance()) ;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return (T) POOL.get(SELF_CLASS) ;
	}
	
	public final static <T extends Pool<K,V>,K,V> void clear(Class<T> SELF_CLASS) {
		if(POOL.containsKey(SELF_CLASS)){
			POOL.get(SELF_CLASS).clear();
			POOL.remove(SELF_CLASS) ;
		} else {
			try {
				Pool<K,V> pool = SELF_CLASS.newInstance() ;
				pool.clear();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
