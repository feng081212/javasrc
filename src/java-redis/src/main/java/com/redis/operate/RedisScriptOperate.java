package com.redis.operate;

import java.util.List;

/**
 * REDIS关于脚本的操作
 * @author Administrator
 *
 */
public interface RedisScriptOperate {

	Object eval(String script, List<String> keys, List<String> args) ;
}
