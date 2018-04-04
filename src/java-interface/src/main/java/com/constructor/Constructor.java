package com.constructor;


/**
 * 构造器
 * @author Administrator
 * @param <K> 构造实例的参数
 * @param <T> 实例
 */
public interface Constructor<K,T> {

	T newInstance(K k) ;
}
