package com.db.jdbc;

import java.util.List;
import java.util.Map;

public interface DBDao<T> extends AtomicDBDao<T> {

	/**
	 * 执行更新语句（只执行update语句）
	 * @param tableName 	表名
	 * @param data			更新内容
	 * @param where			查询条件
	 * @return
	 */
	int update(String tableName, Map<String, Object> data, Map<String, Object> where) ;
	
	/**
	 * 查询（只执行select语句）
	 * @param tableName 	表名
	 * @param where 		where语句中字段名和字段名对应的值
	 * @return
	 */
	List<T> query(String tableName,Map<String,Object> where) ;
	
	/**
	 * 查询（只执行select语句）
	 * @param tableName 	表名
	 * @param columnKeys 	select语句中字段名（要查询的字段名）
	 * @param where 		where语句中字段名和字段名对应的值
	 * @return
	 */
	List<T> query(String tableName,String[] columnKeys,Map<String,Object> where) ;
	
	
	/**
	 * 插入一条记录（只执行insert语句）
	 * @param tableName 表名
	 * @param data		要插入的记录的字段名和值
	 */
	int insert(String tableName, Map<String, Object> data);
	
	/**
	 * 插入一条记录，并且返回主键ID（只执行insert语句）
	 * @param tableName 表名
	 * @param data		要插入的记录的字段名和值
	 */
	long insertOfReturnId(String tableName, Map<String, Object> data);
}
