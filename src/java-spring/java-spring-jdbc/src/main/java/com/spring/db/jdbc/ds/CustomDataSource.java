package com.spring.db.jdbc.ds;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class CustomDataSource extends AbstractRoutingDataSource {  
	
	public Object key = null ;
	
	@Resource
	public void setKey(Object key){
		this.key = key ;
	}
	
    @Override  
    protected Object determineCurrentLookupKey() {  
        return this.key ; 
    }
    
    public Object determineCurrentLookupKeyForLog(){
    	return determineCurrentLookupKey() ;
    }
    
    public DataSource getComboPooledDataSource(){
    	return determineTargetDataSource() ;
    }
}  