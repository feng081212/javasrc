package com.db.jdbc;

import javax.sql.DataSource;

public interface DS extends DataSource {

	void setDriver(String driver);
	
	void setURL(String url);
	
	void setUsername(String username) ;
	
	void setPassword(String password) ;
}
