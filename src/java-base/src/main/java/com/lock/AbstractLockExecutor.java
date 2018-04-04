package com.lock;

import com.event.CallBack;

public abstract class AbstractLockExecutor implements LockExecutor {

	protected Locker lock = null ;
	protected CallBack callback = null ;
	
	public AbstractLockExecutor(Locker lock,CallBack callback){
		this.lock = lock ;
		this.callback = callback ;
	}
	
	@Override
    public void execute(){
		long curTime = System.currentTimeMillis() ;
		long timeout = maxWaitLockTime() ;
		boolean lockFlag = false ;
		try{
			while(curTime + timeout > System.currentTimeMillis()){
				if(lock.lock()){
					lockFlag = true ;
					break ;
				}
				Thread.sleep(intervalTime());
			}
			if(lockFlag){
				callback.onSuccess();
			} else {
				callback.onTimeOut();
			}
		}catch(Exception e){
			callback.onException(e);
		} finally {
			if(lockFlag){
				lock.unlock() ;
			}
		}
    }
}
