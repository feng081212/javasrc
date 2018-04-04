package com.qttz.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.RowSetDynaClass;

public class DB {
	
	public RowSetDynaClass query_RowSetDC(String sql){
		Connection conn = null ;
		Statement statement = null ;
		ResultSet resultset = null ;
		try{
			conn = ConnPool.getConn();
			statement = conn.createStatement();
			resultset = statement.executeQuery(sql);
			return new RowSetDynaClass(resultset);
		}catch(Exception e){
			
			e.printStackTrace();
			return null ;
		}finally{
			try{
				resultset.close();
				statement.close();
				ConnPool.freeConn(conn);
			}catch(Exception e){
				e.printStackTrace();
				resultset = null ;
				statement = null ;
				conn = null ;
			}
		}
	}
	
	public DynaBean queryOneRowSet(String sql){
		try{
			RowSetDynaClass rsdc = query_RowSetDC(sql);
			List list = rsdc.getRows();
			if(list.size()==0)
				return null;
			else
				return (DynaBean) list.get(0);
		}catch(Exception e){
			e.printStackTrace();
			return null ;
		}
	}
	
	public String queryOneRowSet(String sql,String colname){
		try{
			DynaBean dynaBean = queryOneRowSet(sql);
			if(dynaBean==null)
				return "";
			else
				return dynaBean.get(colname.toLowerCase()).toString();
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	public boolean update(String sql,String[] value){
		Connection conn = null ;
		PreparedStatement statement = null ;
		try{
			if(value==null||value.length==0)
				return false ;
			conn = ConnPool.getConn();
			statement = conn.prepareStatement(sql);
			//System.out.println(sql);
			for(int i=1;i<=value.length;i++){
				statement.setString(i, value[i-1]);
				//System.out.println("Param"+i+":"+value[i-1]);
			}
			int result = statement.executeUpdate();
			if(result<0)
				return false ;
			return true;
		}catch(Exception e){
			System.out.println(value[0]);
			e.printStackTrace();
			return false ;
		}finally{
			try{
				statement.close();
				ConnPool.freeConn(conn);
			}catch(Exception e){
				statement = null ;
				conn = null ;
			}
		}
	}
	
	public int update(String sql){
		if(sql==null||sql.equals(""))
			return -1 ;
		Connection conn = null ;
		Statement statement = null ;
		int resultset = -1 ;
		try{
			conn = ConnPool.getConn();
			statement = conn.createStatement();
			//System.out.println(sql);
			resultset = statement.executeUpdate(sql);
			return resultset;
		}catch(Exception e){
			e.printStackTrace();
			return -1 ;
		}finally{
			try{
				statement.close();
				ConnPool.freeConn(conn);
			}catch(Exception e){
				e.printStackTrace();
				statement = null ;
				conn = null ;
			}
		}
	}
}
