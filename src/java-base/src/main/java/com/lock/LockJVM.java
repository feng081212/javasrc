package com.lock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;

public class LockJVM implements Locker {

	protected final static ConcurrentMap<String,ReentrantLock> LOCKS = new ConcurrentHashMap<String, ReentrantLock>() ;
	
	protected String key = "" ;
	
	public LockJVM(String key) {
		this.key = key ;
	}
	
	@Override
	public boolean lock() {
		try{
			if(getReentrantLock(key).isLocked()){
				return false ;
			}
			getReentrantLock(key).lock();
			return true ;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean unlock() {
		try{
			if(getReentrantLock(key).isLocked()){
				getReentrantLock(key).unlock() ;
			}
			return true ;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	protected ReentrantLock getReentrantLock(String key){
		if(!LOCKS.containsKey(key)){
			LOCKS.put(key, new ReentrantLock()) ;
		}
		return LOCKS.get(key) ;
	}
}
