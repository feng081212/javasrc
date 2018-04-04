package com.enums;

public enum BooleanEnum {
	
	/**	无效、否定 */
	FALSE(0,"失效") ,
	/**	有效、肯定 */
	TRUE(1,"有效") ,
	/**	未知、冻结 */
	UNKNOW(2,"未知") ;
	
	public int CODE = 2 ;
	public String NAME = "未知" ;
	
	BooleanEnum(int code,String name){
		CODE = code ;
		NAME = name ;
	}
	
	public final static BooleanEnum getBooleanEnum(int code){
		for (BooleanEnum booleanEnum : BooleanEnum.values()) {
			if(booleanEnum.CODE == code){
				return booleanEnum ;
			}
		}
		return UNKNOW ;
	}
}
