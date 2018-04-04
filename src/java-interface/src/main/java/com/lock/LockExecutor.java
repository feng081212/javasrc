package com.lock;

public interface LockExecutor {

	/**
	 * 最大锁定时间，单位：毫秒
	 * @param key
	 * @return
	 */
    void execute() ;
    
    /**
	 * 最大等待锁时间，单位：毫秒
	 * @param key
	 * @return
	 */
    long maxWaitLockTime() ;
    
    /**
	 * 重新尝试加锁的时间间隔，单位：毫秒
	 * @param key
	 * @return
	 */
    long intervalTime() ;
}
