package com.nullempty;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class NullEmptyUtil {
	/**
	 * 判断对象是否为空
	 * @param obj
	 * @return
	 */
	public final static boolean isNull(Object obj){
		if(obj == null){
			return true ;
		}
		if(obj instanceof String){
			return obj.toString().trim().length() == 0 || obj.toString().equalsIgnoreCase("null") ;
		}
		return false ;
	}
	
	/**
	 * 判断对象是否不为空
	 * @param obj
	 * @return
	 */
	public final static boolean isNotNull(Object obj){
		return !isNull(obj) ;
	}
	
	/**
	 * 判断对象是否为空集
	 * @param obj
	 * @return
	 */
	public final static boolean isEmpty(Object obj){
		if(isNull(obj)){
			return true ;
		}
		if(obj instanceof Set<?>){
			return ((Set<?>)obj).isEmpty() ;
		}
		if(obj instanceof Map<?, ?>){
			return ((Map<?, ?>)obj).isEmpty() ;
		}
		if(obj instanceof List<?>){
			return ((List<?>)obj).isEmpty() ;
		}
		if(obj instanceof Object[]){
			return ((Object[])obj).length == 0 ;
		}
		return false ;
	} 
	
	/**
	 * 判断对象是否不为空集
	 * @param obj
	 * @return
	 */
	public final static boolean isNotEmpty(Object obj){
		return !isEmpty(obj) ;
	}
	
	/**
	 * 判断Map集合中是否存在键key，并且键key对应的值不为空
	 * @param map Map集合
	 * @param key 键
	 * @return false map为空 || map不存在键key || 键key对应的值为空 <br>true 其他
	 */
	public final static boolean isNullValue(Map<String,Object> map,String key){
		return isEmpty(map) || (!map.containsKey(key)) || isEmpty(map.get(key)) ;
	}
	
	/**
	 * 与isNullValue(Map<String,Object> ,String)相反
	 * @param map Map集合
	 * @param key 键
	 * @return !isNullValue(Map<String,Object> ,String) 
	 * @see NullEmptyUtil#isNullValue(Map, String)
	 */
	public final static boolean isNotNullValue(Map<String,Object> map,String key){
		return !isNullValue(map, key) ;
	}
	
	public final static Object convertNull(Object obj,Object defaultValue){
		if(isNull(obj)){
			return defaultValue ;
		} else {
			return obj ;
		}
	}
}
