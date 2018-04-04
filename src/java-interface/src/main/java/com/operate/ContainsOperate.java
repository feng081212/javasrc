package com.operate;

/**
 * 判断是否包含操作
 * @author Administrator
 *
 * @param <K> 键
 */
public interface ContainsOperate<K> {

	/**
	 * 判断是否包含键
	 * @param k 键
	 * @return true 包含 <br> false 不包含
	 */
	boolean contains(K k) ;
}
