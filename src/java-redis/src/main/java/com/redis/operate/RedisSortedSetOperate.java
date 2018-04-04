package com.redis.operate;

import java.util.Set;

import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

/**
 *  Redis 有序集合和集合一样也是string类型元素的集合,且不允许重复的成员。<br>
 *  不同的是每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。<br>
 *  有序集合的成员是唯一的,但分数(score)却可以重复。<br>
 *  集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是O(1)。 <br>
 *  集合中最大的成员数为 232 - 1 (4294967295, 每个集合可存储40多亿个成员)
 * @author Administrator
 */
public interface RedisSortedSetOperate {

	/**
	 *  Redis Zadd 命令用于将一个或多个成员元素及其分数值加入到有序集当中。<br>
	 *  如果某个成员已经是有序集的成员，那么更新这个成员的分数值，并通过重新插入这个成员元素，来保证该成员在正确的位置上。<br>
	 *  分数值可以是整数值或双精度浮点数。<br>
	 *  如果有序集合 key 不存在，则创建一个空的有序集并执行 ZADD 操作。<br>
	 *  当 key 存在但不是有序集类型时，返回一个错误。<br>
	 *  注意： 在 Redis 2.4 版本以前， ZADD 每次只能添加一个元素
	 * @param key
	 * @param score
	 * @param value
	 * @return
	 */
	long zadd(String key,double score, String value) ;
	
	/**
	 * Redis Zcard 命令用于计算集合中元素的数量
	 * @param key
	 * @return 当 key 存在且是有序集类型时，返回有序集的基数。 当 key 不存在时，返回 0 
	 */
	long zcard(String key) ;
	
	/**
	 * Redis Zcount 命令用于计算有序集合中指定分数区间的成员数量
	 * 与Zlexcount的区别：zcount根据分数值计算、Zlexcount根据内容计算
	 * @param key
	 * @param min
	 * @param max
	 * @return 分数值在 min 和 max 之间的成员的数量
	 */
	long zcount(String key,long min,long max) ;
	
	/** Redis Zincrby 命令对有序集合中指定成员的分数加上增量 increment<br>
	 * 可以通过传递一个负数值 increment ，让分数减去相应的值，比如 ZINCRBY key -5 member ，就是让 member 的 score 值减去 5 。<br>
	 * 当 key 不存在，或分数不是 key 的成员时， ZINCRBY key increment member 等同于 ZADD key increment member 。<br>
	 * 当 key 不是有序集类型时，返回一个错误。<br>
	 * 分数值可以是整数值或双精度浮点数
	 * @param key
	 * @param score
	 * @param value
	 * @return member 成员的新分数值，以字符串形式表示
	 */
	double zincrby(String key, double score, String value) ;
	
	/**
	 *  Redis Zinterstore 命令计算给定的一个或多个有序集的交集，其中给定 key 的数量必须以 numkeys 参数指定，并将该交集(结果集)储存到 destination<br>
	 *  默认情况下，结果集中某个成员的分数值是所有给定集下该成员分数值之和
	 * @param destination
	 * @param keys
	 * @return 保存到目标结果集的的成员数量
	 * @example 
	 * ZADD mid_test 70 "Li Lei" <br>
	 * (integer) 1 <br>
	 * ZADD mid_test 70 "Han Meimei" <br>
	 * (integer) 1 <br>
	 * ZADD mid_test 99.5 "Tom" <br> 
	 * (integer) 1 <br>
	 * ZADD fin_test 60 "Li Lei" <br>
	 * (integer) 1 <br>
	 * ZADD fin_test 80 "Han Meimei" <br>
	 * (integer) 1 <br>
	 * ZADD fin_test 99.5 "Tom" <br> 
	 * (integer) 1 <br>
	 * ZINTERSTORE sum_point 2 mid_test fin_test <br> 
	 * (integer) 3 <br>
	 * ZRANGE sum_point 0 -1 WITHSCORES <br> 
	 * 1)"Han Meimei" <br>
	 * 2)"130"<br>
	 * 3)"Li Lei" <br>
	 * 4)"150"<br>
	 * 5)"Tom" <br>
	 * 6)"199"<br>
	 */
	long zinterstore(String destination ,String... keys) ;
	
	/**
	 * Redis Zlexcount 命令在计算有序集合中指定字典区间内成员数量 <br> 
	 * 与zcount的区别：zcount根据分数值计算、Zlexcount根据内容计算
	 * @param key
	 * @param min
	 * @param max
	 * @return 指定区间内的成员数量
	 */
	long zlexcount(String key,String min,String max) ;
	
	/**
	 *  Redis Zrange 返回有序集中，指定区间内的成员。<br>
	 *  其中成员的位置按分数值递增(从小到大)来排序。<br>
	 *  具有相同分数值的成员按字典序(lexicographical order)来排列。<br>
	 *  如果你需要成员按值递减(从大到小)来排列，请使用 ZREVRANGE 命令。<br>
	 *  下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。<br>
	 *  你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推
	 * @param key
	 * @param min
	 * @param max
	 * @return 指定区间内，带有分数值(可选)的有序集成员的列表
	 */
	Set<String> zrange(String key,long min,long max) ;
	
	/**
	 * Redis Zrangebylex 通过字典区间返回有序集合的成员
	 * @param key
	 * @param min
	 * @param max
	 * @return 指定区间内的元素列表
	 */
	Set<String> zrangeByLex(String key,String min,String max) ;
	
	/**
	 *  Redis Zrangebyscore 返回有序集合中指定分数区间的成员列表。有序集成员按分数值递增(从小到大)次序排列。<br>
	 *  具有相同分数值的成员按字典序来排列(该属性是有序集提供的，不需要额外的计算)。<br>
	 *  默认情况下，区间的取值使用闭区间 (小于等于或大于等于)，你也可以通过给参数前增加 ( 符号来使用可选的开区间 (小于或大于)<br>
	 * @param key
	 * @param min
	 * @param max
	 * @return 指定区间内，带有分数值(可选)的有序集成员的列表
	 */
	Set<String> zrangeByScore(String key , double min , double max) ;
	
	/**
	 * Redis Zrank 返回有序集中指定成员的排名。其中有序集成员按分数值递增(从小到大)顺序排列
	 * @param key
	 * @param value
	 * @return 如果成员是有序集 key 的成员，返回 member 的排名。 如果成员不是有序集 key 的成员，返回 nil 
	 */
	long zrank(String key,String value) ;
	
	/**
	 *  Redis Zrem 命令用于移除有序集中的一个或多个成员，不存在的成员将被忽略。<br>
	 *  当 key 存在但不是有序集类型时，返回一个错误。<br>
	 *  注意： 在 Redis 2.4 版本以前， ZREM 每次只能删除一个元素
	 * @param key
	 * @param values
	 * @return 被成功移除的成员的数量，不包括被忽略的成员
	 */
	long zrem(String key,String... values) ;
	
	/**
	 * Redis Zremrangebylex 命令用于移除有序集合中给定的字典区间的所有成员
	 * @param key
	 * @param min
	 * @param max
	 * @return 被成功移除的成员的数量，不包括被忽略的成员
	 */
	long zremrangeByLex(String key,String min,String max) ;
	
	/**
	 * Redis Zremrangebyrank 命令用于移除有序集中，指定排名(rank)区间内的所有成员
	 * @param key
	 * @param min
	 * @param max
	 * @return 被移除成员的数量
	 */
	long zremrangeByRank(String key,long min,long max) ;
	
	/**
	 * Redis Zremrangebyscore 命令用于移除有序集中，指定分数（score）区间内的所有成员
	 * @param key
	 * @param min
	 * @param max
	 * @return 被移除成员的数量
	 */
	long zremrangeByScore(String key, double min, double max) ;
	
	/**
	 *  Redis Zrevrange 命令返回有序集中，指定区间内的成员。<br>
	 *  其中成员的位置按分数值递减(从大到小)来排列。<br>
	 *  具有相同分数值的成员按字典序的逆序(reverse lexicographical order)排列。<br>
	 *  除了成员按分数值递减的次序排列这一点外， ZREVRANGE 命令的其他方面和 ZRANGE 命令一样<br>
	 * @param key
	 * @param min
	 * @param max
	 * @return 指定区间内，带有分数值(可选)的有序集成员的列表
	 */
	Set<String> zrevrange(String key, long min, long max) ;
	
	/**
	 *  Redis Zrevrangebyscore 返回有序集中指定分数区间内的所有的成员。有序集成员按分数值递减(从大到小)的次序排列。<br>
	 *  具有相同分数值的成员按字典序的逆序(reverse lexicographical order )排列。<br>
	 *  除了成员按分数值递减的次序排列这一点外， ZREVRANGEBYSCORE 命令的其他方面和 ZRANGEBYSCORE 命令一样<br>
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	Set<String> zrevrangeByScore(String key, String min, String max) ;
	
	/**
	 *  Redis Zrevrank 命令返回有序集中成员的排名。其中有序集成员按分数值递减(从大到小)排序。<br>
	 *  排名以 0 为底，也就是说， 分数值最大的成员排名为 0 。<br>
	 *  使用 ZRANK 命令可以获得成员按分数值递增(从小到大)排列的排名<br>
	 * @param key
	 * @param value
	 * @return 如果成员是有序集 key 的成员，返回成员的排名。 如果成员不是有序集 key 的成员，返回 nil 
	 */
	long zrevrank(String key,String value) ;
	
	/**
	 * Redis Zscore 命令返回有序集中，成员的分数值。 如果成员元素不是有序集 key 的成员，或 key 不存在，返回 nil 
	 * @param key
	 * @param value
	 * @return 成员的分数值，以字符串形式表示
	 */
	double zscore(String key,String value) ;
	
	/**
	 *  Redis Zunionstore 命令计算给定的一个或多个有序集的并集，其中给定 key 的数量必须以 numkeys 参数指定，并将该并集(结果集)储存到 destination 。<br>
	 *  默认情况下，结果集中某个成员的分数值是所有给定集下该成员分数值之和 
	 * @param destination
	 * @param keys
	 * @return 保存到 destination 的结果集的成员数量
	 */
	long zunionstore(String destination ,String... keys) ;
	
	/**
	 * Redis Zscan 命令用于迭代有序集合中的元素（包括元素成员和元素分值）
	 * @param key
	 * @param cursor
	 * @return 返回的每个元素都是一个有序集合元素，一个有序集合元素由一个成员（member）和一个分值（score）组成
	 */
	ScanResult<Tuple> zscan(String key, String cursor) ;
}
