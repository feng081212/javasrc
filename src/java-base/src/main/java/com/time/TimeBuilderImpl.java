package com.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeBuilderImpl implements TimeBuilder {

	@Override
	public long timestamp(long timestamp) {
		if(timestamp > 9999999999l){
			timestamp = timestamp / 1000 ;
		}
		return timestamp ;
	}
	
	@Override
	public long timestampMilliSecond(long timestamp) {
		if(timestamp < 9999999999l){
			timestamp = timestamp * 1000 ;
		}
		return timestamp ;
	}
	
	@Override
	public long timestampMilliSecond(Date date){
		return date.getTime() ;
	}
	
	@Override
	public long timestampFristSecondOfDay(long timestamp) {
		return timestamp((timestampMilliSecond(timestamp) + MILLISECONDS_IN_8HOURS) / MILLISECONDS_IN_DAY * MILLISECONDS_IN_DAY - MILLISECONDS_IN_8HOURS)  ;
	}
	
	@Override
	public long timestampLastSecondOfDay(long timestamp) {
		return timestamp(timestampFristSecondOfDay(timestamp) + SECONDS_IN_DAY - 1) ;
	}
	
	@Override
	public int[] dateArray(long timestamp) {
		Calendar calendar = calendar(timestamp);
		int[] dateArray = new int[7];
		dateArray[0] = calendar.get(Calendar.YEAR) ;
		dateArray[1] = calendar.get(Calendar.MONTH) + 1 ;
		dateArray[2] = calendar.get(Calendar.DAY_OF_MONTH) ;
		dateArray[3] = calendar.get(Calendar.HOUR) ;
		dateArray[4] = calendar.get(Calendar.MINUTE) ;
		dateArray[5] = calendar.get(Calendar.SECOND) ;
		dateArray[6] = calendar.get(Calendar.MILLISECOND) ;
		return dateArray;
	}
	
	@Override
	public Calendar calendar(long timestamp) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestampMilliSecond(timestamp));
		return calendar ;
	}
	
	@Override
	public Calendar calendar(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance() ;
		calendar.set(year, month-1, day, 0, 0, 0) ;
		return calendar ;
	}
	
	@Override
	public long currentMilliSecond() {
		return System.currentTimeMillis() ;
	}

	@Override
	public String str(Date date,String format){
		return new SimpleDateFormat(format).format(date) ;
	}
	
	@Override
	public String strDayOfWeek(long timestamp) {
		int dayOfWeek = dayOfWeek(timestamp) ;
		switch (dayOfWeek) {
			case 1:
				return "日" ;
			case 2:
				return "一" ;
			case 3:
				return "二" ;
			case 4:
				return "三" ;
			case 5:
				return "四" ;
			case 6:
				return "五" ;
			case 7:
				return "六" ;
			default:
				return "";
		}
	}
	
	@Override
	public Date date(String dateStr, String format) throws Exception {
		return new SimpleDateFormat(format).parse(dateStr) ;
	}
	
	@Override
	public Date date(int year,int month,int day){
		return calendar(year,month,day).getTime() ;
	}
	
	@Override
	public Date date(long timestamp){
		return new Date(timestampMilliSecond(timestamp)) ;
	}
	
	@Override
	public Date dateFirstDayOfMonth(int year, int month) {
		return date(year, month, 1) ;
	}
	
	@Override
	public Date dateFirstDayOfMonth(long timestamp) {
		Calendar calendar = calendar(timestamp) ;
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime() ;
	}
	
	@Override
	public Date dateLastDayOfMonth(int year, int month) {
		Calendar calendar = calendar(year, month, 1) ;
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime() ;
	}
	
	@Override
	public Date dateLastDayOfMonth(long timestamp) {
		Calendar calendar = calendar(timestamp) ;
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime() ;
	}
	
	@Override
	public Date chinaTimeFromGMT(long gmtTime){
		return new Date(timestampMilliSecond(gmtTime) + MILLISECONDS_IN_8HOURS) ;
	}
	
	@Override
	public int dayOfWeek(long timestamp) {
		return calendar(timestamp).get(Calendar.DAY_OF_WEEK) ;
	}
	
	@Override
	public int dayOfWeekInMonth(long timestamp) {
		int day = day(timestamp) ;
		int dayOfWeek = dayOfWeek(dateFirstDayOfMonth(timestamp).getTime()) ;
		int day2 = day + dayOfWeek - 8 ;
		int week =  1 + day2 / 7 + (day2 % 7 == 0 ? 0 : 1) ;
		return week ;
	}
	
	@Override
	public int year(long timestamp) {
		return dateArray(timestamp)[0];
	}
	
	@Override
	public int month(long timestamp) {
		return dateArray(timestamp)[1];
	}
	
	@Override
	public int day(long timestamp) {
		return dateArray(timestamp)[2];
	}
	
	@Override
	public int daysOfMonth(long timestamp) {
		int[] dateArray = dateArray(timestamp) ;
		return daysOfMonth(dateArray[0],dateArray[1]);
	}
	
	@Override
	public int daysOfMonth(int year, int month) {
		switch (month) {
		case 4:
		case 6:
		case 9:
		case 11:
			return 30 ;
		case 2:
			if(year % 4 == 0){
				return 29 ;
			}
			return 28 ;
		default:
			return 31 ;
	}
	}
	
	@Override
	public int weeksOfMonth(long timestamp) {
		return dayOfWeekInMonth(dateLastDayOfMonth(timestamp).getTime());
	}
	
	public static void main(String[] args) {
		TimeBuilderImpl timeBuilderImpl = new TimeBuilderImpl() ;
//		Date date = new Date() ;
//		System.out.println(date);
//		System.out.println(timeBuilderImpl.dateLastDayOfMonth(2018,4));
//		System.out.println(timeBuilderImpl.date(2018,2,12));
//		System.out.println(timeBuilderImpl.year(new Date().getTime()));
//		System.out.println(timeBuilderImpl.month(new Date().getTime()));
//		System.out.println(timeBuilderImpl.day(new Date().getTime()));
//		System.out.println(timeBuilderImpl.daysOfMonth(timeBuilderImpl.date(2018,3,12).getTime()));
//		for (int i = 1; i <= 31; i++) {
//			System.out.println(i + "-" + timeBuilderImpl.dayOfWeekInMonth(timeBuilderImpl.date(2018,12,i).getTime()));
//		}
//		System.out.println("12-" + timeBuilderImpl.weeksOfMonth(timeBuilderImpl.date(2018,12,31).getTime()));
		for (int i = 1; i <= 12; i++) {
			System.out.println(i + "-" + timeBuilderImpl.daysOfMonth(timeBuilderImpl.date(2020,i,1).getTime()));
		}
	}
}
