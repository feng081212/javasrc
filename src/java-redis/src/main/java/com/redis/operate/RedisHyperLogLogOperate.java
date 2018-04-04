package com.redis.operate;

/**
 * Redis 在 2.8.9 版本添加了 HyperLogLog 结构。<br>
 * Redis HyperLogLog 是用来做基数统计的算法，HyperLogLog 的优点是，在输入元素的数量或者体积非常非常大时，计算基数所需的空间总是固定 的、并且是很小的。<br>
 * 在 Redis 里面，每个 HyperLogLog 键只需要花费 12 KB 内存，就可以计算接近 2^64 个不同元素的基 数。这和计算基数时，元素越多耗费内存就越多的集合形成鲜明对比。<br>
 * 但是，因为 HyperLogLog 只会根据输入元素来计算基数，而不会储存输入元素本身，所以 HyperLogLog 不能像集合那样，返回输入的各个元素<br>
 * @author Administrator
 *
 */
public interface RedisHyperLogLogOperate {

	/**
	 * Redis Pfadd 命令将所有元素参数添加到 HyperLogLog 数据结构中
	 * @param key
	 * @param values
	 * @return 整型，如果至少有个元素被添加返回 true， 否则返回 false
	 */
	boolean pfadd(String key,String... values) ;
	
	/**
	 * Redis Pfcount 命令返回给定 HyperLogLog 的基数估算值
	 * @param key
	 * @return 整数，返回给定 HyperLogLog 的基数值，如果多个 HyperLogLog 则返回基数估值之和
	 */
	long pfcount(String key) ;
	
	/**
	 * Redis Pgmerge 命令将多个 HyperLogLog 合并为一个 HyperLogLog ，合并后的 HyperLogLog 的基数估算值是通过对所有 给定 HyperLogLog 进行并集计算得出的
	 * @param destkey
	 * @param sourcekey
	 * @return 
	 */
	boolean pfmerge(String destkey,String... sourcekey) ;
}
