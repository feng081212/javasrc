package com.time;

import java.util.Calendar;
import java.util.Date;

/**
 * timestamp : 10位时间戳（精确到秒）
 * @author Administrator
 */
public interface TimeBuilder {

	/** 年月日时分秒（e.g. 2017-01-31 14:00:59 2017年1月31号14点00分59秒） */
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	/** 年月日时分秒（e.g. 2017-01-31 14:00 2017年1月31号14点00分） */
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	/** 年月日时分秒（e.g. 20170131140059 2017年1月31号14点00分59秒） */
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	/** 年月日（e.g. 2017-01-31：2017年1月31号） */
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	/** 年月日（e.g. 20170131：2017年1月31号） */
	public static final String YYYYMMDD = "yyyyMMdd";
	/** 时分秒（e.g. 14:00:59：14点00分59秒） */
	public static final String HH_MM_SS = "HH:mm:ss";
	/** 时分秒（e.g. 140059：14点00分59秒） */
	public static final String HHMMSS = "HHmmss";
	/** 时分（e.g. 14:00：14点00分） */
	public static final String HH_MM = "HH:mm";
	/** 年（e.g. 2017） */
	public static final String yyyy = "yyyy" ;
	/** 月（e.g. 01~12） */
	public static final String MM = "MM" ;
	/** 日（e.g. 01~31） */
	public static final String dd = "dd" ;
	/** 时（e.g. 00~23） */
	public static final String HH = "HH" ;
	/** 分（e.g. 00~59） */
	public static final String mm = "mm" ;
	/** 秒（e.g. 00~59） */
	public static final String ss = "ss" ;
	
	public static final long SECONDS_IN_MINUTE = 60L ;
	public static final long SECONDS_IN_HOUR = 60 * SECONDS_IN_MINUTE ;
	public static final long SECONDS_IN_8HOURS = 8 * SECONDS_IN_HOUR ;
	public static final long SECONDS_IN_DAY = 24 * SECONDS_IN_HOUR ;
	
	public static final long MILLISECONDS_IN_SECOND = 1000L ;
	public static final long MILLISECONDS_IN_MINUTE = 60 * MILLISECONDS_IN_SECOND ;
	public static final long MILLISECONDS_IN_HOUR = 60 * MILLISECONDS_IN_MINUTE ;
	public static final long MILLISECONDS_IN_8HOURS = 8 * MILLISECONDS_IN_HOUR ;
	public static final long MILLISECONDS_IN_DAY = 24 * MILLISECONDS_IN_HOUR ;
	
	/**
	 * 时间戳格式化成秒
	 * @param timestamp 时间戳（毫秒、秒）
	 * @return
	 */
	long timestamp(long timestamp) ;
	
	/**
	 * 时间戳格式化成毫秒
	 * @param timestamp 时间戳（毫秒、秒）
	 * @return
	 */
	long timestampMilliSecond(long timestamp) ;
	
	/**
	 * 时间戳（毫秒）
	 * @param date 日期
	 * @return
	 */
	long timestampMilliSecond(Date date);
	
	/**
	 * 得到某个时间戳所在的日期（天）的第一秒
	 * @param timestamp 某个时间戳
	 * @return 返回10位时间戳
	 */
	long timestampFristSecondOfDay(long timestamp);
	
	
	/**
	 * 得到某个时间戳所在的日期（天）的最后一秒
	 * @param timestamp 某个时间戳
	 * @return 返回10位时间戳
	 */
	long timestampLastSecondOfDay(long timestamp);
	
	/**
	 * 得到某个时间戳所在的日期（天）的最后一秒
	 * @param timestamp 某个时间戳
	 * @return 返回10位时间戳
	 */
	int[] dateArray(long timestamp);
	
	/**
	 * 获取Calendar
	 * @param timestamp 时间戳（毫秒、秒）
	 * @return
	 */
	Calendar calendar(long timestamp);
	
	/**
	 * 获取Calendar
	 * @param year 年
	 * @param month 月（1~12）
	 * @param day 日
	 * @return
	 */
	Calendar calendar(int year,int month,int day);
	
	
	/**
	 * 返回当前时间的时间戳（毫秒）
	 * @return
	 */
	long currentMilliSecond() ;
	
	/**
	 * 返回格式化后的字符串
	 * @param date 日期
	 * @param format 日期格式
	 * @return
	 */
	String str(Date date,String format) ;
	
	/**
	 * 获得星期几
	 * @param timestamp
	 * @return
	 */
	String strDayOfWeek(long timestamp);
	
	/**
	 * 时间戳转换成日期
	 * @param date 日期
	 * @param format 日期格式
	 * @return
	 * @throws Exception 
	 */
	Date date(String dateStr,String format) throws Exception ;
	
	/**
	 * 时间戳转换成日期
	 * @param year 年
	 * @param month 月（1~12）
	 * @param day 日
	 * @return
	 */
	Date date(int year,int month,int day);
	
	/**
	 * 时间戳转换成日期
	 * @param timestamp 时间戳
	 * @return
	 */
	Date date(long timestamp) ;
	
	/**
	 * 得到指定月份的第一天
	 * @param year
	 * @param month 月（1~12）
	 * @return
	 */
	Date dateFirstDayOfMonth(int year,int month);
	
	/**
	 * 得到指定月份的第一天
	 * @param timestamp
	 * @return
	 */
	Date dateFirstDayOfMonth(long timestamp);
	
	/**
	 * 得到指定月份的最后一天
	 * @param year 年
	 * @param month 月（1~12）
	 * @return
	 */
	Date dateLastDayOfMonth(int year,int month);
	
	/**
	 * 得到指定月份的最后一天
	 * @param timestamp
	 * @return
	 */
	Date dateLastDayOfMonth(long timestamp);
	
	/**
	 * 北京时间（GMT时间转北京时间）
	 * @param gmtTime
	 * @return
	 */
	Date chinaTimeFromGMT(long gmtTime) ;
	
	/**
	 * 获得星期几
	 * @param timestamp
	 * @return
	 */
	int dayOfWeek(long timestamp) ;
	/**
	 * 获得当月第几周
	 * @param timestamp
	 * @return
	 */
	int dayOfWeekInMonth(long timestamp) ;
	
	/**
	 * 年
	 * @param timestamp
	 * @return
	 */
	int year(long timestamp);
	
	/**
	 * 月（1~12）
	 * @param timestamp
	 * @return
	 */
	int month(long timestamp);
	
	/**
	 * 日
	 * @param timestamp
	 * @return
	 */
	int day(long timestamp);
	
	/**
	 * 当前月总天数
	 * @param timestamp
	 * @return
	 */
	int daysOfMonth(long timestamp);
	
	/**
	 * 当前月总天数
	 * @param year 年
	 * @param month 月（1~12）
	 * @return
	 */
	int daysOfMonth(int year,int month);
	
	/**
	 * 当前月总周数
	 * @param timestamp
	 * @return
	 */
	int weeksOfMonth(long timestamp);
}