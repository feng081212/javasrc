package com.pool;


/**
 * 对象托管
 * @author Administrator
 */
public class ObjectTrust<T>{
	
	/**	对象托管时间为无穷大，即不会失效 */
	public static final long DEFAULT_TRUST_TIME_WITHOUT_TIMEOUT = 0 ;
	
	/**	对象默认托管时间（60 * 60 * 1000毫秒）*/
	public static final long DEFAULT_TRUST_TIME_MILLISECOND = 60 * 60 * 1000 ;
	/**	对象托管时间间隔（单位：毫秒） */
	private long trustTime = 0 ;
	/**	对象开始托管时间（单位：毫秒）*/
	private long trustBeginTime = 0 ;
	/**	对象结束托管时间（单位：毫秒）*/
	private long trustEndTime = 0 ;
	/**	对象 */
	private T object = null ;
	
	/**
	 * @param object 托管对象
	 * @param trustTime （单位：毫秒）
	 */
	public ObjectTrust(T object,long trustTime){
		this.object = object ;
		this.trustBeginTime = System.currentTimeMillis() ;
		this.trustTime = trustTime ;
		this.trustEndTime = this.trustBeginTime + this.trustTime ;
	}
	
	/**
	 * @param object 托管对象
	 * @see ObjectTrust#DEFAULT_TRUST_TIME_MILLISECOND 托管时间 
	 */
	public ObjectTrust(T object){
		this(object,DEFAULT_TRUST_TIME_MILLISECOND) ;
	}

	public long getTrustTime() {
		return trustTime;
	}

	public long getTrustBeginTime() {
		return trustBeginTime;
	}

	public long getTrustEndTime() {
		return trustEndTime;
	}

	public T getObject() {
		return object ;
	}
	
	public boolean isValid(){
		if(this.trustTime == DEFAULT_TRUST_TIME_WITHOUT_TIMEOUT)
			return true ;
		if(System.currentTimeMillis() > this.trustEndTime)
			return false ;
		return true ;
	}
	
	@Override
	public int hashCode() {
		return getObject().hashCode() ;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(super.equals(obj) || getObject().equals(obj))
			return true ;
		if(obj instanceof ObjectTrust){
			return ((ObjectTrust<?>)obj).getObject().equals(getObject()) ;
		}
		return false ;
	}
}