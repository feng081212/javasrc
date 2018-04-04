package com.operate;

/**
 * 修改一个元素，如果不存在key，则新增，如果存在，则修改
 * @author Administrator
 *
 * @param <K> 键
 * @param <V> 值
 */
public interface NotifyOperate<K,V> {
	
	/**
	 * 修改一个元素，如果不存在key，则新增，如果存在，则修改
	 * @author Administrator
	 *
	 * @param <K> 键
	 * @param <V> 值
	 */
	int notify(K k,V v) ;
}
