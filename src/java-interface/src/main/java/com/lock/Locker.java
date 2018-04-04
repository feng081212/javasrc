package com.lock;

public interface Locker {

	/**
	 * 尝试加锁
	 * @return
	 */
    boolean lock() ;

    /**
	 * 尝试解锁
	 * @param key
	 * @return
	 */
    boolean unlock() ;
    
}
