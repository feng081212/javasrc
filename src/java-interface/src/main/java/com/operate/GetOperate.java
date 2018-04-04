package com.operate;

/**
 * 获取一个元素
 * @author Administrator
 *
 * @param <K> 键
 * @param <V> 值
 */
public interface GetOperate<K,V> {

	V get(K k) ;
}
