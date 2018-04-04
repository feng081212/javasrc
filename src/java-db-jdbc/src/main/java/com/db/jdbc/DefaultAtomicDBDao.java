package com.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.log.LogFactory;
import com.spi.SpiFactory;

public class DefaultAtomicDBDao implements AtomicDBDao<Map<String,Object>> {

	@Override
	public int update(String sql) {
		Statement statement = null ;
		try{
			Connection connection = getDataSource().getConnection() ;
			statement = connection.createStatement();
			return statement.executeUpdate(sql) ;
		}catch(Exception e){
			LogFactory.info(e);
		} finally{
			close(statement);
		}
		return 0 ;
	}

	@Override
	public int update(String sql, Object... values) {
		PreparedStatement statement = null ;
		try{
			Connection connection = getDataSource().getConnection() ;
			statement = connection.prepareStatement(sql) ;
			if(values != null && values.length > 0){
				for (int i = 0 ; i < values.length ; i++) {
					statement.setObject(i+1, values[i]);
				}
			}
			return statement.executeUpdate();
		}catch(Exception e){
			LogFactory.info(e);
		} finally{
			close(statement);
		}
		return 0 ;
	}

	@Override
	public int[] updateBatch(String... sqls) {
		if(sqls == null || sqls.length == 0){
			return null ;
		}
		Statement statement = null ;
		try{
			Connection connection = getDataSource().getConnection() ;
			statement = connection.createStatement();
			for(String sql : sqls){
				statement.addBatch(sql);
			}
			return statement.executeBatch() ;
		}catch(Exception e){
			LogFactory.info(e);
		} finally{
			close(statement);
		}
		return null ;
	}

	@Override
	public int[] updateBatch(String sql, List<Object[]> data) {
		PreparedStatement statement = null ;
		try{
			Connection connection = getDataSource().getConnection() ;
			statement = connection.prepareStatement(sql) ;
			for(Object[] values : data){
				for (int i = 0 ; i < values.length ; i++) {
					statement.setObject(i+1, values[i]);
				}
				statement.addBatch();
			}
			return statement.executeBatch() ;
		}catch(Exception e){
			LogFactory.info(e);
		} finally{
			close(statement);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> query(String sql) {
		Statement statement = null ;
		ResultSet resultset = null ;
		try{
			Connection connection = getDataSource().getConnection() ;
			statement = connection.createStatement();
			resultset = statement.executeQuery(sql);
			return getList(resultset);
		}catch(Exception e){
			return null ;
		}finally{
			close(resultset);
			close(statement);
		}
	}

	@Override
	public List<Map<String, Object>> query(String sql, Object... values) {
		PreparedStatement statement = null ;
		ResultSet resultset = null ;
		try{
			Connection connection = getDataSource().getConnection() ;
			statement = connection.prepareStatement(sql) ;
			for (int i = 0 ; i < values.length ; i++) {
				statement.setObject(i+1, values[i]);
			}
			LogFactory.info(sql);
			resultset = statement.executeQuery();
			return getList(resultset);
		}catch(Exception e){
			e.printStackTrace();
			LogFactory.error(e);
			return null ;
		}finally{
			close(resultset);
			close(statement);
		}
	}
	
	@Override
	public long insertOfReturnId(String sql) {
		Statement statement = null ;
		ResultSet resultSet = null ;
		try{
			Connection connection = getDataSource().getConnection() ;
			statement = connection.createStatement();
			statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS) ;
			resultSet = statement.getGeneratedKeys() ;
			if(resultSet.next()){
				return resultSet.getLong(1) ;
			}
		}catch(Exception e){
			LogFactory.info(e);
		} finally{
			close(resultSet);
			close(statement);
		}
		return 0 ;
	}

	@Override
	public long insertOfReturnId(String sql, Object... values) {
		PreparedStatement statement = null ;
		ResultSet resultSet = null ;
		try{
			Connection connection = getDataSource().getConnection() ;
			statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS) ;
			for (int i = 0 ; i < values.length ; i++) {
				statement.setObject(i+1, values[i]);
			}
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys() ;
			if(resultSet.next()){
				return resultSet.getLong(1) ;
			}
		}catch(Exception e){
			LogFactory.info(e);
		} finally{
			close(resultSet);
			close(statement);
		}
		return 0 ;
	}
	
	public List<Map<String,Object>> getList(ResultSet resultset){
		try{
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			ResultSetMetaData rsmd = (ResultSetMetaData) resultset.getMetaData() ;
			while(resultset.next()){
				Map<String,Object> map = new HashMap<String,Object>();
				for(int i = 0 ;i < rsmd.getColumnCount() ; i ++ ){
					String key = rsmd.getColumnLabel(i+1) ;
					Object value = resultset.getObject(i+1) ;
					map.put(key, value) ;
				}
				list.add(map) ;
			}
			return list ;
		}catch(Exception e){
			e.printStackTrace();
			return null ;
		}
	}
	
	public void close(AutoCloseable autoCloseable){
		try{
			if(autoCloseable != null){
				autoCloseable.close();
			}
		}catch(Exception e){
			autoCloseable = null ;
		}
	}
	
	private DataSource dataSource = null ;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource ;
	}

	@Override
	public DataSource getDataSource() {
		if(this.dataSource == null){
			this.dataSource = SpiFactory.get(DataSource.class) ;
		}
		return this.dataSource ;
	}
}
