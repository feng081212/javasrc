package com.common.util;

import redis.clients.jedis.Jedis;

public class RedisUtil {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.2.16",6379);
		jedis.auth("123456") ;
		jedis.set("redis", "myredis");  
        System.out.println(jedis.get("redis"));
	    System.out.println("Stored string in redis: "+ jedis.get("name"));
	}
}
