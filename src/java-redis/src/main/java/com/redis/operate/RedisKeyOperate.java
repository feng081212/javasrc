package com.redis.operate;

import java.util.Set;

/**
 * REDIS关于键的操作
 * @author Administrator
 *
 */
public interface RedisKeyOperate {

	/**
	 * Redis DEL命令用于删除Redis中现有/存在的键
	 * @param key
	 * @return 删除成功返回true，删除失败返回false
	 */
	boolean del(String key) ;
	
	/**
	 * Redis DEL命令用于删除Redis中现有/存在的键
	 * @param key
	 * @return 被删除的key的数量
	 */
	long del(String... key) ;
	
	/**
	 * Redis exists命令用于检查键是否存在于Redis中
	 * @param key
	 * @return
	 */
	boolean exists(String key) ;
	
	/**
	 * Redis Expire命令用于设置键的到期时间。在到指定的到期时间后，在Redis中这个键将失效，不能再使用
	 * @example expireKey(key,60) 60秒之后key过期
	 * @param key 键 
	 * @param seconds 过期时间（秒）
	 * @return 如果成功地为该键设置了超时时间，返回 true<br>
	 * 如果键不存在或无法设置超时时间，返回 false
	 */
	boolean expire(String key,int seconds) ;
	
	/**
	 * Redis Expireat命令用于以Unix时间戳格式设置键的到期时间。 在到期时间后，键将在Redis中失效不可用
	 * @example expireKey(key,1493840000) 在时间到达1493840000之后key过期
	 * @param key
	 * @param unixTime 过期时间（秒）
	 * @return 如果成功地为该键设置了超时时间，返回 true<br>
	 * 如果键不存在或无法设置超时时间，返回 false
	 */
	boolean expireAt(String key,long unixTime) ;
	
	/**
	 * Redis Expire命令用于设置键的到期时间。在到指定的到期时间后，在Redis中这个键将失效，不能再使用
	 * @example expireKey(key,6000) 6000毫秒之后key过期
	 * @param key 键 
	 * @param milliseconds 过期时间（毫秒）
	 * @return 如果成功地为该键设置了超时时间，返回 true<br>
	 * 如果键不存在或无法设置超时时间，返回 false
	 */
	boolean pexpire(String key,long milliseconds) ;
	
	/**
	 * Redis Expireat命令用于以Unix时间戳格式设置键的到期时间。 在到期时间后，键将在Redis中失效不可用
	 * @example expireKey(key,1493840000000) 在时间到达1493840000000之后key过期
	 * @param key
	 * @param millisecondsTimestamp 过期时间（毫秒）
	 * @return 如果成功地为该键设置了超时时间，返回 true<br>
	 * 如果键不存在或无法设置超时时间，返回 false
	 */
	boolean pexpireAt(String key,long millisecondsTimestamp) ;
	
	/**
	 * Redis KEYS命令用于搜索具有匹配模式的键，* 能获取Redis中所有可用键的列表
	 * @param key
	 */
	Set<String> keys(String key) ;
	
	/**
	 * Redis PERSIST命令用于删除键所指定的过期时间
	 * @param key
	 * @return
	 */
	boolean persist(String key) ;
	
	/**
	 * Redis TTL命令用于获取键到过期的剩余时间(以秒为单位)
	 * @param key
	 * @return TTL的时间值(以秒为单位)<br>
	 * 当 key 不存在时，返回 -2 <br>
	 * 当 key 存在但没有设置剩余生存时间时，返回 -1
	 */
	long ttl(String key) ;
	
	/**
	 * Redis PTTL命令用于获取键到过期的剩余时间(以毫秒为单位)
	 * @param key
	 * @return TTL的时间值(以毫秒为单位)<br>如果键的没有过期时间返回0<br>如果键不存在返回-1
	 */
	long pttl(String key) ;
	
	/**
	 * Redis RANDOMKEY命令用来获取Redis数据库的随机键
	 * @return
	 */
	String randomKey() ;
	
	/**
	 * Redis RENAME命令用于更改键的名称<br>
	 * 如果旧键的名称和新键的名称相等，或者密钥不存在，则返回错误。 如果新键的名称已存在，则它将覆盖现有键的名称
	 * @param oldKey
	 * @param newKey
	 * @return
	 */
	boolean rename(String oldKey,String newKey) ;
	
	/**
	 * Redis RENAMENX命令用于更改键的名称(如果新键不存在)。
	 * @param oldKey
	 * @param newKey
	 * @return 如果键被重命名为新键，则返回true<br>如果键已经存在，则返回false
	 */
	boolean renamenx(String oldKey,String newKey) ;
	
	/**
	 * Redis TYPE命令用于获取存储在键中的值的数据类型
	 * @param key
	 * @return 返回一个字符串，它是存储在键中的值的数据类型或none
	 */
	String type(String key) ;
}
