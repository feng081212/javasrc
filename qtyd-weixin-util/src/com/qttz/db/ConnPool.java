package com.qttz.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnPool {
	
	public static Connection[] conns = null ;
	public static int[] status = null ;
	public static int maxSize = 6 ;
	public static String driver = "com.mysql.jdbc.Driver";
//	public static String url = "jdbc:mysql://192.168.2.1:3309/qttz01?autoReconnect=true&useUnicode=true&characterEncoding=utf-8";
//	public static String username = "qttz";
//	public static String password = "test";
	
	public static String url = "jdbc:mysql://localhost:3306/localdb?autoReconnect=true&useUnicode=true&characterEncoding=utf-8";
	public static String username = "root";
	public static String password = "mysql";
	static{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		conns = new Connection[maxSize];
		status = new int[maxSize];
		for(int i=0;i<maxSize;i++){
			conns[i] = getConnection();
			status[i] = 1 ;
		}
	}
	
	/**
	 * 获取 mysql Connection
	 */
	public static Connection getConnection(){
		try{
			try {
				return DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null ;
	}
	
	/**
	 * 从 Connection连接池中获取Connection
	 */
	public static Connection getConn(){
		try{
			Connection conn = null ;
			for(int i = 0 ;i<status.length;i++){
				if(status[i]==1){
					if(conns[i]==null) conns[i] = getConnection();
					conn = conns[i];
					status[i] = 0 ;
					break;
				}
			}
			if(conn==null) conn = getConnection();
			return conn ;
		}catch(Exception e){
			e.printStackTrace();
			return getConnection() ;
		}
	}
	
	/**
	 * 释放Connection 到连接池中
	 */
	public static boolean freeConn(Connection conn){
		try {
			for(int i=0;i<conns.length;i++){
				if(conns[i]==conn){
					status[i] = 1 ;
					return true ;
				}
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false ;
		}
		return true ;
	}
	
	public static void main(String[] args) {
		Connection conn = ConnPool.getConn();
		System.out.println(conn);
		Connection conn2 = ConnPool.getConn();
		System.out.println(conn2);
		System.out.println(ConnPool.getConn());
		System.out.println(ConnPool.getConn());
		System.out.println(ConnPool.getConn());
		System.out.println(ConnPool.getConn());
		
		ConnPool.freeConn(conn);
		System.out.println(ConnPool.getConn());
		
		System.out.println(ConnPool.getConn());
		
		Connection conn3 = ConnPool.getConn();
		System.out.println(conn3);
		ConnPool.freeConn(conn3);
		System.out.println(ConnPool.getConn());
		
		ConnPool.freeConn(conn2);
		System.out.println(ConnPool.getConn());
	}
}
