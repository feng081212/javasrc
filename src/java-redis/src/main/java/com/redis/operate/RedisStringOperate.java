package com.redis.operate;

import java.util.List;

public interface RedisStringOperate {

	/**
	 * Redis SET命令用于在Redis键中设置一些字符串值
	 * @param key
	 * @param value
	 * @return
	 */
	boolean set(String key,String value) ;
	
	/**
	 * Redis SETEX命令用于在Redis键中的指定超时，设置键的字符串值
	 * @param key
	 * @param seconds （秒）
	 * @param value
	 * @return
	 */
	boolean setex(String key,int seconds,String value) ;
	
	/**
	 * Redis PSETEX命令用于设置键的值，以毫秒为单位指定过期时间
	 * @param key
	 * @param milliseconds （毫秒）
	 * @param value
	 * @return
	 */
	boolean psetex(String key,long milliseconds,String value) ;
	
	/**
	 * Redis SETNX命令用于在Redis键中设置某些字符串值(如果该键在Redis中不存在)<br>
	 * 如果不存在，则SETNX的全形式是SET<br>
	 * 如果存在，则放弃此次行为
	 * @param key
	 * @param value
	 * @return
	 */
	boolean setnx(String key,String value) ;
	
	/**
	 * Redis SETRANGE命令用于覆盖键的值，从指定偏移处开始的一部分字符串
	 * @example set key helloworld <br>
	 * setrange(key,6,kk) -> hellowkkld <br>
	 * setrange(key,6,kkkkkkkkkk) -> hellowkkkkkkkkkk
	 * @param key
	 * @param offset
	 * @param value
	 * @return 返回整数 - 字符串在修改后的长度
	 */
	long setrange(String key, long offset, String value) ;
	
	
	/**
	 * Redis GET命令用于获取存储在指定键中的值。如果键不存在，则返回nil。 如果返回的值不是字符串，则返回错误
	 * @param key
	 * @return
	 */
	String get(String key) ;
	
	/**
	 * 命令用于获取存储在键的字符串值的子字符串，由偏移量的开始和结束(两者都包括)确定<br> 
	 * 可以使用负偏移，以便从字符串的末尾开始计算偏移<br>
	 * 该函数通过将结果范围限制为字符串的实际长度来处理范围外的请求<br>
	 * 简单理解：将key的值取出来，然后截取字符串
	 * @param key
	 * @param startOffset
	 * @param endOffset
	 * @return
	 */
	String getrange(String key,long startOffset, long endOffset) ;
	
	/**
	 * Redis GETSET命令在Redis键中设置指定的字符串值，并返回其旧值<br>
	 * 将key的值设置为value，并将key原先的值返回，如果原先key不存在，返回nil
	 * @param key
	 * @param value
	 * @return
	 */
	String getSet(String key,String value) ;
	
	/**
	 * Redis MGET命令用于获取所有指定键的值。对于不包含字符串值或不存在的每个键，返回特殊值 - nil
	 * @param keys
	 * @return
	 */
	List<String> mget(String...  keys) ;
	
	/**
	 * Redis STRLEN命令用于获取存储在键中的字符串值的长度。当键包含非字符串值时返回错误。
	 * @param key
	 * @return 返回整数 - 在键中的值的字符串长度，或当键不存在时返回0
	 */
	long strlen(String key) ;
	
	/**
	 * Redis INCR命令用于将键的整数值递增1<br>
	 * 如果键不存在，则在执行操作之前将其设置为0<br>
	 * 如果键包含错误类型的值或包含无法表示为整数的字符串，则会返回错误<br>
	 * 此操作限于64位有符号整数
	 * @param key
	 * @return 返回一个整数，增加后键的值
	 */
	long incr(String key) ;
	
	/**
	 * Redis INCRBY命令用于将存储在键上的数字按指定的值增加<br>
	 * 如果键不存在，则在执行操作之前将其设置为0<br>
	 * 如果键包含错误类型的值或包含无法表示为整数的字符串，则会返回错误
	 * @param key
	 * @param integer
	 * @return 返回一个整数，增加后键的值
	 */
	long incrBy(String key,long integer) ;
	
	/**
	 * Redis INCRBYFLOAT命令用于将递增的字符串表示为浮点数，该值存储在键上指定的增量<br>
	 * 如果键不存在，则在执行操作之前将其设置为0<br>
	 * 如果键包含错误类型的值或当前键内容或指定的增量不能解析为浮点数，则返回错误
	 * @param key
	 * @param f
	 * @return 返回一个浮点数，增加后键的值
	 */
	double incrByFloat(String key,double value) ;
	
	/**
	 * Redis DECR命令用于将键的整数值减1<br>
	 * 如果键不存在，则在执行操作之前将其设置为0<br>
	 * 如果键包含错误类型的值或包含无法表示为整数的字符串，则会返回错误<br>
	 * 此操作限于64位有符号整数
	 * @param key
	 * @return 返回一个整数，递减后键的值
	 */
	long decr(String key) ;
	
	/**
	 * Redis DECRBY命令用于将存储在键上的数字按指定的值减少<br>
	 * 如果键不存在，则在执行操作之前将其设置为0<br>
	 * 如果键包含错误类型的值或包含无法表示为整数的字符串，则会返回错误
	 * @param key
	 * @param integer
	 * @return 返回一个整数，递减后键的值
	 */
	long decrBy(String key,long integer) ;
	
	/**
	 * Redis APPEND命令用于在键中添加一些值
	 * @param key
	 * @param value
	 * @return 返回一个整数，在追加操作后的字符串的长度
	 */
	long append(String key,String value) ;
}
