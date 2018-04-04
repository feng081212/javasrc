package com.spring.db.jdbc.ds;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import com.db.jdbc.DS;
import com.pool.AbstractPool;
import com.properties.PropertiesPool;

public class CustomDS extends AbstractPool<Thread, Connection> implements DS {

	private String driver = "" ;
	private String url = "" ;
	private String username = "" ;
	private String password = "" ;
	
	@Override
	public void setDriver(String driver) {
		this.driver = driver ;
		try {
			Class.forName(this.driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setURL(String url) {
		this.url = url ;
	}
	
	@Override
	public void setPassword(String password) {
		this.password = password ;
	}
	
	@Override
	public void setUsername(String username) {
		this.username = username ;
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return 1 ;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null ;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return get(Thread.currentThread()) ;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		this.username = username ;
		this.password = password ;
		return getConnection() ;
	}
	
	@Override
	public Connection newInstance(Thread thread) {
		try {
			if(driver == null || driver.trim().length() == 0 || driver.equalsIgnoreCase("null")){
				driver = PropertiesPool.getValue("db", "driver") ;
				try {
					Class.forName(this.driver);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				url = PropertiesPool.getValue("db", "url") ;
				username = PropertiesPool.getValue("db", "username") ;
				password = PropertiesPool.getValue("db", "password") ;
			}
			if(username == null || username.trim().length() == 0 || username.equalsIgnoreCase("null")){
				return DriverManager.getConnection(url) ;
			} else {
				return DriverManager.getConnection(url, username, password) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null ;
	}
}
