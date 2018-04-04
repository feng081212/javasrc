package com.io;

public class AutoCloseableFactory {

	/**
	 * 关闭AutoCloseable实例
	 */
	public final static void close(AutoCloseable autoCloseable){
		try{
			if(autoCloseable != null){
				autoCloseable.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			autoCloseable = null ;
		}
	}
	
	
}
