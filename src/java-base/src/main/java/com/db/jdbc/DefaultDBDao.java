package com.db.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.spi.SpiFactory;

public class DefaultDBDao implements DBDao<Map<String,Object>> {

	protected AtomicDBDao<Map<String,Object>> atomicDBDao = null ;
	
	@SuppressWarnings("unchecked")
	public AtomicDBDao<Map<String, Object>> getAtomicDBDao() {
		if(atomicDBDao == null){
			atomicDBDao = SpiFactory.get(AtomicDBDao.class) ;
		}
		return atomicDBDao;
	}

	public void setAtomicDBDao(AtomicDBDao<Map<String, Object>> atomicDBDao) {
		this.atomicDBDao = atomicDBDao;
	}

	@Override
	public int update(String tableName, Map<String, Object> data, Map<String, Object> where) {
		StringBuffer sql = new StringBuffer();
		sql.append("update " + tableName + " set ");
		for (String key : data.keySet()) {
			sql.append(key + " = ? , ");
		}
		sql.delete(sql.length() - 2, sql.length()) ;
		sql.append("where ");
		for (String key : where.keySet()) {
			sql.append(key + " = ? , ");
		}
		sql.delete(sql.length() - 2, sql.length()) ;
		List<Object> values = new ArrayList<Object>() ;
		for (String key : data.keySet()) {
			values.add(data.get(key)) ;
		}
		for (String key : where.keySet()) {
			values.add(where.get(key)) ;
		}
		return update(sql.toString(), values.toArray());
	}

	@Override
	public List<Map<String, Object>> query(String tableName, Map<String, Object> where) {
		return query(tableName,new String[]{"*"},where) ;
	}

	@Override
	public List<Map<String, Object>> query(String tableName, String[] columnKeys, Map<String, Object> where) {
		StringBuffer sql = new StringBuffer("select ") ;
		for (String key : columnKeys) {
			sql.append(key + " , ");
		}
		sql.append("from " + tableName + " where ");
		List<Object> values = new ArrayList<Object>();
		for (String key : where.keySet()) {
			sql.append(key + " = ? , ");
			values.add(where.get(key)) ;
		}
		sql.delete(sql.length() - 2, sql.length()) ;
		return query(sql.toString(),values.toArray()) ;
	}

	@Override
	public int insert(String tableName, Map<String, Object> data) {
		StringBuffer sql = new StringBuffer("insert into ") ;
		sql.append(tableName).append("(") ;
		for (String key : data.keySet()) {
			sql.append(key + ",");
		}
		sql.delete(sql.length() - 1, sql.length()) ;
		sql.append(")values(") ;
		List<Object> values = new ArrayList<Object>();
		for (String key : data.keySet()) {
			sql.append("?,");
			values.add(data.get(key)) ;
		}
		sql.delete(sql.length() - 1, sql.length()) ;
		sql.append(")") ;
		return update(sql.toString(),values.toArray()) ;
	}

	@Override
	public long insertOfReturnId(String tableName, Map<String, Object> data) {
		StringBuffer sql = new StringBuffer("insert into ") ;
		sql.append(tableName).append("(") ;
		for (String key : data.keySet()) {
			sql.append(key + ",");
		}
		sql.delete(sql.length() - 1, sql.length()) ;
		sql.append(")values(") ;
		List<Object> values = new ArrayList<Object>();
		for (String key : data.keySet()) {
			sql.append("?,");
			values.add(data.get(key)) ;
		}
		sql.delete(sql.length() - 1, sql.length()) ;
		sql.append(")") ;
		return insertOfReturnId(sql.toString(), values.toArray()) ;
	}

	@Override
	public int update(String sql) {
		return getAtomicDBDao().update(sql) ;
	}

	@Override
	public int update(String sql, Object... values) {
		return getAtomicDBDao().update(sql, values);
	}

	@Override
	public int[] updateBatch(String... sql) {
		return getAtomicDBDao().updateBatch(sql);
	}

	@Override
	public int[] updateBatch(String sql, List<Object[]> data) {
		return getAtomicDBDao().updateBatch(sql, data);
	}

	@Override
	public List<Map<String, Object>> query(String sql) {
		return getAtomicDBDao().query(sql);
	}

	@Override
	public List<Map<String, Object>> query(String sql, Object... values) {
		return getAtomicDBDao().query(sql, values);
	}

	@Override
	public long insertOfReturnId(String sql) {
		return getAtomicDBDao().insertOfReturnId(sql);
	}

	@Override
	public long insertOfReturnId(String sql, Object... values) {
		return getAtomicDBDao().insertOfReturnId(sql, values);
	}

	@Override
	public DataSource getDataSource() {
		return getAtomicDBDao().getDataSource() ;
	}
	
	@Override
	public void setDataSource(DataSource dataSource) {
		getAtomicDBDao().setDataSource(dataSource);
	}
}
