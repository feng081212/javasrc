package com.enums;

public enum ResultEnum {
	
	/**	初始值【0】（默认） */
	DEFAULT(0,"初始值") ,
	/**	成功【1】 */
	SUCCESS(1,"成功") ,
	/**	失败【2】 */
	FAILURE(2,"失败"),
	/**	冻结【3】 */
	FREEZE(3,"冻结"),
	/**	处理中【4】 */
	HANDLING(4,"处理中"),
	/**	取消【5】 */
	CANCLE(5,"取消");
	
	public int CODE = 0 ;
	public String NAME = "初始值" ;
	
	ResultEnum(int code,String name){
		CODE = code ;
		NAME = name ;
	}
	
	public final static ResultEnum getResultEnum(int code){
		for (ResultEnum resultEnum : ResultEnum.values()) {
			if(resultEnum.CODE == code){
				return resultEnum ;
			}
		}
		return null ;
	}
}
