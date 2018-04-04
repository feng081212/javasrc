package com.pool;

import com.constructor.Constructor;
import com.operate.AddOperate;
import com.operate.ClearOperate;
import com.operate.ContainsOperate;
import com.operate.GetOperate;
import com.operate.NotifyOperate;
import com.operate.RemoveOperate;

/**
 * 池基本操作
 * @author Administrator
 * @param <K> 键
 * @param <V> 值
 */
public interface Pool<K,V> extends Constructor<K,V>,AddOperate<K, V>, NotifyOperate<K, V>, 
	GetOperate<K, V>,RemoveOperate<K, V>,ContainsOperate<K>,ClearOperate {

	/**	默认池中存放的最大数量 */
	public static final int DEFAULT_MAX_OBJECT_AMOUNT = 100 ;
	
	/**	默认池中存放的对象最大寿命（毫秒） */
	public static final int DEFAULT_OBJECT_MAX_LIFE_TIME = 60 * 60 * 1000 ;
	
	/**
	 * 池中的数量
	 * @return
	 */
	int amount() ;
	
	/**
	 * 池中运行存放的最大数量
	 * @return
	 */
	int maxAmount() ;
	
	/**
	 * 池中对象最大生命周期（单位：毫秒）
	 * @return
	 */
	int maxLifeTime() ;
	
	/**
	 * 清理池中无用数据
	 * @return
	 */
	void clearRubbish();
	
}
