package com.time;

public class TimeUtil extends TimeBuilderImpl {
	
	public final static TimeUtil getInstance() {
		return new TimeUtil() ;
	}
	
	/**
	 * 返回时间戳对应的年月日（默认格式：2017-01-31）
	 * @param timestamp
	 * @return
	 */
	public String strDay(long timestamp){
		return str(date(timestamp), TimeBuilder.YYYY_MM_DD) ;
	}
	/**
	 * 返回时间戳对应的时间（默认格式：14:00:59）
	 * @param timestamp
	 * @return
	 */
	public String strTime(long timestamp){
		return str(date(timestamp), TimeBuilder.HH_MM_SS) ;
	}
	/**
	 * 返回时间戳对应的日期（默认格式：2017-01-31 14:00:59）
	 * @param timestamp
	 * @return
	 */
	public String strDate(long timestamp){
		return str(date(timestamp), TimeBuilder.YYYY_MM_DD_HH_MM_SS) ;
	}
	
	/**
	 * 返回时间戳对应的日期
	 * @param timestamp
	 * @param format 日期格式
	 * @return
	 */
	public String str(long timestamp,String format){
		return str(date(timestamp), format) ;
	}
}
