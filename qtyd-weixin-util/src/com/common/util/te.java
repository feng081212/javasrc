package com.common.util;


public class te {

	public static void main(String[] args) {
		test() ;
	}
	
	public static void test() { 
		int index = 0 ;
		String[] mobile = new String[]{"1","2","3","4","5","6","7","8","9","10"} ;
		int LIMIT = 3 ;
		while(index < mobile.length){
			int length = 0 ;
			if(LIMIT > mobile.length - index )
				length = mobile.length - index ;
			else
				length = LIMIT ;
			String[] MOBILE = new String[length] ;
			System.arraycopy(mobile, index, MOBILE, 0, MOBILE.length);
			index = index + MOBILE.length ;
		}
	} 
}
