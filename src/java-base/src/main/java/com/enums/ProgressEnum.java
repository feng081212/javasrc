package com.enums;

public enum ProgressEnum {
	
	/**	初始值（默认） */
	DEFAULT(0,"初始值") ,
	/**	就绪 */
	READY(1,"就绪") ,
	/**	进行中 */
	IN_PROGRESS(2,"进行中") ,
	/**	暂停 */
	PAUSE(3,"暂停"),
	/**	完成-成功结束 */
	FINISH(4,"完成（成功结束）") ,
	/**	完成-受干扰结束 */
	FINISH_INTERRUPT(5,"完成（受干扰结束）"),
	/**	完成-失败结束 */
	FINISH_FAILURE(6,"完成（失败结束）") ;
	
	public int CODE = 0 ;
	public String NAME = "初始值" ;
	
	ProgressEnum(int code,String name){
		CODE = code ;
		NAME = name ;
	}
	
	public final static ProgressEnum getProgressEnum(int code){
		for (ProgressEnum progressEnum : ProgressEnum.values()) {
			if(progressEnum.CODE == code){
				return progressEnum ;
			}
		}
		return null ;
	}
}
