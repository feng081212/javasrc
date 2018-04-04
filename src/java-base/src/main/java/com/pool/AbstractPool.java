package com.pool;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public abstract class AbstractPool<K,V> implements Pool<K, V> {
	
	private final Map<K,ObjectTrust<V>> CONTAINER = new ConcurrentHashMap<K,ObjectTrust<V>>() ;
	
	@Override
	public int add(K k, V v) {
		if(k == null || v == null){
			return 0 ;
		}
		if(contains(k)){
			return 0 ;
		}
		return notify(k, v) ;
	}
	
	@Override
	public V remove(K k) {
		if(contains(k)){
			if(CONTAINER.get(k) != null && CONTAINER.get(k).getObject() instanceof AutoCloseable){
				try {
					((AutoCloseable)CONTAINER.get(k).getObject()).close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return CONTAINER.remove(k).getObject() ;
		}
		return null ;
	}
	
	@Override
	public int notify(K k, V v) {
		if(k == null || v == null){
			return 0 ;
		}
		if(CONTAINER.size() >= maxAmount()){
			clearRubbish() ;
		}
		if(CONTAINER.size() >= maxAmount()){
			delFristItem() ;
		}
		if(CONTAINER.size() >= maxAmount()){
			return 0 ;
		}
		remove(k);
		CONTAINER.put(k, new ObjectTrust<V>(v,maxLifeTime())) ;
		return 1 ;
	}
	
	@Override
	public V get(K k) {
		if(k == null)
			return null ;
		try{
			if(!contains(k)){
				add(k,newInstance(k));
			} else {
				if(CONTAINER.get(k) == null || !CONTAINER.get(k).isValid()){
					notify(k, newInstance(k)) ;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return CONTAINER.get(k).getObject() ;
	}
	
	@Override
	public boolean contains(K k) {
		return CONTAINER.containsKey(k) ;
	}
	
	@Override
	public int clear() {
		int count = CONTAINER.size() ;
		CONTAINER.clear();
		return count ;
	}
	
	@Override
	public int amount() {
		return CONTAINER.size() ;
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
	public void clearRubbish() {
		Set<K> keySet = CONTAINER.keySet() ;
		for(K k : keySet){
			if(CONTAINER.get(k) == null || !CONTAINER.get(k).isValid()){
				remove(k) ;
			}
		}
	}
	
	public void delFristItem(){
		K first = null ;
		Set<K> keySet = CONTAINER.keySet() ;
		for(K k : keySet){
			if(first != null && CONTAINER.get(first) == null){
				remove(first) ;
				return ;
			}
			if(first == null || CONTAINER.get(first).getTrustBeginTime() > CONTAINER.get(k).getTrustBeginTime()){
				first = k ;
			}
		}
		remove(first) ;
	}
}
