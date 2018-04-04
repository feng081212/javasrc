package com.redis.operate;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

/**
 * Redis的Set是string类型的无序集合。集合成员是唯一的，这就意味着集合中不能出现重复的数据。<br>
 * Redis 中 集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是O(1)。<br>
 * 集合中最大的成员数为 232 - 1 (4294967295, 每个集合可存储40多亿个成员)<br>
 * @author Administrator
 */
public interface RedisSetOperate {

	/**
	 *  Redis Sadd 命令将一个或多个成员元素加入到集合中，已经存在于集合的成员元素将被忽略。<br>
	 *  假如集合 key 不存在，则创建一个只包含添加的元素作成员的集合。<br>
	 *  当集合 key 不是集合类型时，返回一个错误。<br>
	 *  注意：在Redis2.4版本以前， SADD 只接受单个成员值<br>
	 * @param key
	 * @param values
	 * @return
	 */
	long sadd(String key,String... values) ;
	
	/**
	 * Redis Scard 命令返回集合中元素的数量
	 * @param key
	 * @return 集合的数量。 当集合 key 不存在时，返回 0 
	 */
	long scard(String key) ;
	
	/**
	 * Redis Sdiff 命令返回给定集合之间的差集。不存在的集合 key 将视为空集
	 * @param key
	 * @return 包含差集成员的列表
	 */
	Set<String> sdiff(String... key) ;
	
	/**
	 * Redis Sdiffstore 命令将给定集合之间的差集存储在指定的集合中。如果指定的集合 key 已存在，则会被覆盖
	 * @param key
	 * @param key1
	 * @return 结果集中的元素数量
	 */
	long sdiffstore(String key,String... keys) ;
	
	/**
	 * Redis Sinter 命令返回给定所有给定集合的交集<br>
	 * 不存在的集合 key 被视为空集<br>
	 * 当给定集合当中有一个空集时，结果也为空集(根据集合运算定律)
	 * @param keys
	 * @return
	 */
	Set<String> sinter(String... keys) ;
	
	/**
	 * Redis Sinterstore 命令将给定集合之间的交集存储在指定的集合中。如果指定的集合已经存在，则将其覆盖
	 * @param key
	 * @param keys
	 * @return 交集成员的列表
	 */
	long sinterstore(String key,String... keys) ;
	
	/**
	 * Redis Sismember 命令判断成员元素是否是集合的成员
	 * @param key
	 * @param value
	 * @return 如果成员元素是集合的成员，返回 true 。 如果成员元素不是集合的成员，或 key 不存在，返回false
	 */
	boolean sismember(String key,String value) ;
	
	/**
	 * Redis Smembers 命令返回集合中的所有的成员。 不存在的集合 key 被视为空集合
	 * @param key
	 * @return 集合中的所有成员
	 */
	Set<String> smembers(String key) ;
	
	/**
	 *  Redis Smove 命令将指定成员 member 元素从 source 集合移动到 destination 集合。<br>
	 *  SMOVE 是原子性操作。<br>
	 *  如果 source 集合不存在或不包含指定的 member 元素，则 SMOVE 命令不执行任何操作，仅返回 0 <br>
	 *  否则， member 元素从 source 集合中被移除，并添加到 destination 集合中去。<br>
	 *  当 destination 集合已经包含 member 元素时， SMOVE 命令只是简单地将 source 集合中的 member 元素删除。
	 *  当 source 或 destination 不是集合类型时，返回一个错误
	 * @param oldkey
	 * @param newkey
	 * @param value
	 * @return 如果成员元素被成功移除，返回 true <br>
	 * 如果成员元素不是 oldkey 集合的成员，并且没有任何操作对 newkey 集合执行，那么返回 false
	 */
	boolean smove(String oldkey,String newkey,String value) ;
	
	/**
	 * Redis Spop 命令用于移除并返回集合中的一个随机元素
	 * @param key
	 * @return 被移除的随机元素。 当集合不存在或是空集时，返回 nil
	 */
	String spop(String key) ;
	
	/**
	 *  Redis Srandmember 命令用于返回集合中的一个随机元素。<br>
	 *  从 Redis 2.6 版本开始， Srandmember 命令接受可选的 count 参数：<br>
	 *  如果 count 为正数，且小于集合基数，那么命令返回一个包含 count 个元素的数组，数组中的元素各不相同。<br>
	 *  如果 count 大于等于集合基数，那么返回整个集合。<br>
	 *  如果 count 为负数，那么命令返回一个数组，数组中的元素可能会重复出现多次，而数组的长度为 count 的绝对值。<br>
	 *  该操作和 SPOP 相似，但 SPOP 将随机元素从集合中移除并返回，而 Srandmember 则仅仅返回随机元素，而不对集合进行任何改动
	 * @param key
	 * @param count
	 * @return 只提供集合 key 参数时，返回一个元素；如果集合为空，返回 nil<br>
	 * 如果提供了 count 参数，那么返回一个数组；如果集合为空，返回空数组
	 */
	List<String> srandmember(String key,int count) ;
	
	/**
	 *  Redis Srem 命令用于移除集合中的一个或多个成员元素，不存在的成员元素会被忽略。<br>
	 *  当 key 不是集合类型，返回一个错误。<br>
	 *  在 Redis 2.4 版本以前， SREM 只接受单个成员值<br>
	 * @param key
	 * @param value
	 * @return 被成功移除的元素的数量，不包括被忽略的元素
	 */
	long srem(String key,String... value) ;
	
	/**
	 * Redis Sunion 命令返回给定集合的并集。不存在的集合 key 被视为空集
	 * @param keys
	 * @return 并集成员的列表
	 */
	Set<String> sunion(String... keys) ;
	
	/**
	 * Redis Sunionstore 命令将给定集合的并集存储在指定的集合 destination 中<br>
	 * 如果 destination 已经存在，则将其覆盖
	 * @param destination
	 * @param keys
	 * @return 结果集中的元素数量
	 */
	long sunionstore(String destination,String... keys) ;
	
	/**
	 * Redis Sscan 命令用于迭代集合键中的元素
	 * @param key
	 * @param cursor
	 * @param scanParams
	 * @return 数组列表
	 */
	ScanResult<String> sscan(String key,String cursor,ScanParams scanParams) ;
}
