package com.lock;

import com.event.CallBack;

public class DefaultLockExecutor extends AbstractLockExecutor {

	public DefaultLockExecutor(String key,CallBack callback){
		super(new LockJVM(key), callback) ;
	}
	
	public DefaultLockExecutor(Locker locker,CallBack callback) {
		super(locker, callback) ;
	}

	@Override
	public long maxWaitLockTime() {
		return 60 * 1000 ;
	}

	@Override
	public long intervalTime() {
		return 100 ;
	}
}
