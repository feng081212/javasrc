package com.orm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // 指定标注的位置
@Retention(RetentionPolicy.RUNTIME) // 指定作用域
public @interface Column {
	String value() ;
}
