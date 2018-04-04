package com.redis.operate;

import java.util.List;

public interface RedisListOperate {

	/**
	 * Redis Lpop 命令用于移除并返回列表的第一个元素
	 * @param key 
	 * @return 列表的第一个元素。 当列表 key 不存在时，返回 nil 
	 */
	String lpop(String key) ;
	
	/**
	 * Redis Blpop 命令移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
	 * @param key
	 * @param timeout 超时时间
	 * @return 如果列表为空，返回一个 nil和等待时长<br>
	 * 否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值
	 */
	List<String> blpop(String key,int timeout) ;
	
	/**
	 * Redis Rpop 命令用于移除并返回列表的最后一个元素
	 * @param key
	 * @return 列表的最后一个元素。 当列表不存在时，返回 nil
	 */
	String rpop(String key) ;
	
	
	/**
	 * Redis Brpop 命令移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
	 * @param key
	 * @param timeout
	 * @return 假如在指定时间内没有任何元素被弹出，则返回一个 nil和等待时长<br>
	 * 反之，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值
	 */
	List<String> brpop(String key,int timeout) ;
	
	/**
	 * Redis Rpoplpush 命令用于移除列表的最后一个元素，并将该元素添加到另一个列表并返回
	 * @param oldkey
	 * @param newkey
	 * @return 被弹出的元素
	 */
	String rpoplpush(String oldkey,String newkey) ;
	
	/**
	 * Redis Brpoplpush 命令从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它<br>
	 * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
	 * @param oldkey
	 * @param newkey
	 * @param timeout
	 * @return 假如在指定时间内没有任何元素被弹出，则返回一个 nil 和等待时长<br>
	 * 反之，返回一个含有两个元素的列表，第一个元素是被弹出元素的值，第二个元素是等待时长
	 */
	String brpoplpush(String oldkey,String newkey,int timeout) ;
	
	/**
	 * Redis Lindex 命令用于通过索引获取列表中的元素<br>
	 * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推
	 * @param key
	 * @param index
	 * @return 列表中下标为指定索引值的元素。 如果指定索引值不在列表的区间范围内，返回 nil
	 */
	String lindex(String key,long index) ;
	
	/**
	 * Redis Linsert 命令用于在列表的元素前插入元素<br>
	 * 当指定元素不存在于列表中时，不执行任何操作<br>
	 * 当列表不存在时，被视为空列表，不执行任何操作<br>
	 * 如果 key 不是列表类型，返回一个错误
	 * @param key
	 * @param signKey
	 * @param value
	 * @return 如果命令执行成功，返回插入操作完成之后，列表的长度<br>
	 * 如果没有找到指定元素 ，返回 -1 。 如果 key 不存在或为空列表，返回 0
	 */
	long linsertBefore(String key, String signKey,String value) ;
	
	/**
	 * Redis Linsert 命令用于在列表的元素后插入元素<br>
	 * 当指定元素不存在于列表中时，不执行任何操作<br>
	 * 当列表不存在时，被视为空列表，不执行任何操作<br>
	 * 如果 key 不是列表类型，返回一个错误
	 * @param key
	 * @param signKey
	 * @param value
	 * @return 如果命令执行成功，返回插入操作完成之后，列表的长度<br>
	 * 如果没有找到指定元素 ，返回 -1 。 如果 key 不存在或为空列表，返回 0
	 */
	long linsertAfter(String key, String signKey,String value) ;
	
	/**
	 * Redis Llen 命令用于返回列表的长度<br>
	 * 如果列表 key 不存在，则 key 被解释为一个空列表，返回 0 <br>
	 *  如果 key 不是列表类型，返回一个错误
	 * @param key
	 * @return 列表的长度
	 */
	long llen(String key) ;
	
	/**
	 *  Redis Lpush 命令将一个或多个值插入到列表头部<br>
	 *  如果 key不存在，一个空列表会被创建并执行 LPUSH 操作<br>
	 *  当 key 存在但不是列表类型时，返回一个错误<br>
	 *  注意：在Redis 2.4版本以前的 LPUSH 命令，都只接受单个 value 值。
	 * @param key
	 * @param values
	 * @return
	 */
	long lpush(String key,String... values ) ;
	
	/**
	 *  Redis Rpush 命令用于将一个或多个值插入到列表的尾部(最右边)。<br>
	 *  如果列表不存在，一个空列表会被创建并执行 RPUSH 操作。 当列表存在但不是列表类型时，返回一个错误。<br>
	 *  注意：在 Redis 2.4 版本以前的 RPUSH 命令，都只接受单个 value 值。
	 * @param key
	 * @param values
	 * @return 执行 RPUSH 操作后，列表的长度
	 */
	long rpush(String key,String... values) ;
	
	/**
	 * Redis Lpushx 将一个或多个值插入到已存在的列表头部，列表不存在时操作无效
	 * @param key
	 * @param values
	 * @return LPUSHX 命令执行之后，列表的长度
	 */
	long lpushx(String key,String... values ) ;
	
	/**
	 * Redis Rpushx 命令用于将一个或多个值插入到已存在的列表尾部(最右边)。如果列表不存在，操作无效
	 * @param key
	 * @param values
	 * @return 执行 Rpushx 操作后，列表的长度
	 */
	long rpushx(String key,String... values) ;
	
	/**
	 * Redis Lrange 返回列表中指定区间内的元素，区间以偏移量 START 和 END 指定<br>
	 * 其中 0 表示列表的第一个元素， 1 表示列表的第二个元素，以此类推<br>
	 * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	List<String> lrange(String key,long start,long end) ;
	
	/**
	 *  Redis Lrem 根据参数 COUNT 的值，移除列表中与参数 VALUE 相等的元素。<br>
	 *  COUNT 的值可以是以下几种：<br>
	 *  count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT 。<br>
	 *  count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。<br>
	 *  count = 0 : 移除表中所有与 VALUE 相等的值
	 * @param key
	 * @param count
	 * @param value
	 * @return 被移除元素的数量。 列表不存在时返回 0 
	 */
	long lrem(String key,long count,String value) ;
	
	/**
	 *  Redis Lset 通过索引来设置元素的值。<br>
	 *  当索引参数超出范围，或对一个空列表进行 LSET 时，返回一个错误。<br>
	 *  关于列表下标的更多信息，请参考 LINDEX 命令
	 * @param key
	 * @param index
	 * @param value
	 * @return 操作成功返回 true ，否则返回false
	 */
	boolean lset(String key,long index,String value) ;
	
	/**
	 *  Redis Ltrim 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。<br>
	 *  下标 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推<br>
	 *  你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	boolean ltrim(String key,long start,long end) ;
	
	
}
