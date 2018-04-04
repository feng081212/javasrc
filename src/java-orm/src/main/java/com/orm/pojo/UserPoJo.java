package com.orm.pojo;

/**
 * 用户表
 * 1、数据库中标的字段名称和属性名称不一致
 * 2、实体名称和表名称不对应
 * @author Administrator
 */
public class UserPoJo {

	private Integer id ;
	private String username ;
	private String password ;
	private Integer age ;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
