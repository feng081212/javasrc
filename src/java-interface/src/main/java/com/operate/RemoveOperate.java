package com.operate;

/**
 * 删除一个元素，并将删除的元素返回
 * @author Administrator
 *
 * @param <K> 键
 * @param <K> 值
 */
public interface RemoveOperate<K,V> {

	V remove(K k) ;
}
