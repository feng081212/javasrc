package com.operate;

/**
 * 增加一个元素操作，如果存在则忽然此操作
 * @author Administrator
 * @param <K> 键
 * @param <V> 值
 */
public interface AddOperate<K,V> {

	int add(K k,V v) ;
}
