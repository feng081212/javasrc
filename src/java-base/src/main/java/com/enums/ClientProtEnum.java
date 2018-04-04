package com.enums;

public enum ClientProtEnum {
	
	UNKOWN(0,"UNKOWN"),
	PC(1,"PC"),
	WAP(2,"WAP"),
	ANDROID(3,"ANDROID"),
	IOS(4,"IOS") ;
	
	public int CODE = 0 ;
	public String NAME = "UNKOWN" ;
	
	ClientProtEnum(int code,String name){
		CODE = code ;
		NAME = name ;
	}
	
	public final static ClientProtEnum getClientProtEnum(int code){
		for (ClientProtEnum clientProtEnum : ClientProtEnum.values()) {
			if(clientProtEnum.CODE == code){
				return clientProtEnum ;
			}
		}
		return UNKOWN ;
	}
	
	public final static ClientProtEnum getClientProtEnum(String name){
		for (ClientProtEnum clientProtEnum : ClientProtEnum.values()) {
			if(clientProtEnum.NAME.equalsIgnoreCase(name)){
				return clientProtEnum ;
			}
		}
		return UNKOWN ;
	}
}
