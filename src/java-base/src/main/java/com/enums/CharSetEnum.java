package com.enums;

public enum CharSetEnum {
	
	UTF_8("UTF-8") ,
	GBK("GBK"),
	ISO_8859_1("ISO-8859-1") ;
	
	public String VALUE = "" ;
	
	CharSetEnum(String name) {
		VALUE = name ;
	}
	
	public final static CharSetEnum getCharSetEnum(String value){
		for (CharSetEnum charsetEnum : CharSetEnum.values()) {
			if(charsetEnum.VALUE.equalsIgnoreCase(value)){
				return charsetEnum ;
			}
		}
		return null ;
	}
}
