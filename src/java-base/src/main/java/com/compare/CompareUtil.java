package com.compare;

@SuppressWarnings("unchecked")
public class CompareUtil {

	public static final <T> boolean equal(T value , T compared){
		try{
			if(value == compared){
				return true ;
			}
			if(value == null || compared == null){
				return false ;
			}
			if(value instanceof Number || compared instanceof Number){
				if(value.toString().trim().length() == 0 || compared.toString().trim().length() == 0){
					return false ;
				}
				return Double.valueOf(String.valueOf(value)).compareTo(Double.valueOf(String.valueOf(compared))) == 0 ;
			} else if(value instanceof Comparable) {
				return ((Comparable<T>)value).compareTo(compared) == 0 ;
			} else {
				return value.equals(compared) ;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false ;
	}
	
	
	public static final <T> boolean more(T moreone , T lessone){
		try{
			if(moreone == null || lessone == null){
				return false ;
			}
			if(moreone instanceof Number || lessone instanceof Number){
				return Double.valueOf(String.valueOf(moreone)).compareTo(Double.valueOf(String.valueOf(lessone))) == 1 ;
			} else if(moreone instanceof Comparable) {
				return ((Comparable<T>)moreone).compareTo(lessone) == 1 ;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false ;
	}
	
	public static final <T> boolean less(T lessone , T moreone){
		try{
			if(moreone == null || lessone == null){
				return false ;
			}
			if(moreone instanceof Number || lessone instanceof Number){
				return Double.valueOf(String.valueOf(lessone)).compareTo(Double.valueOf(String.valueOf(moreone))) == -1 ;
			} else if(lessone instanceof Comparable) {
				return ((Comparable<T>)lessone).compareTo(moreone) == -1 ;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false ;
	}
}
