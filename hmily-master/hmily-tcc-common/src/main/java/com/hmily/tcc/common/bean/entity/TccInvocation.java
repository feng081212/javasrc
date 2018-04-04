/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hmily.tcc.common.bean.entity;

import java.io.Serializable;


/**
 * @author xiaoyu
 */
public class TccInvocation implements Serializable {

    private static final long serialVersionUID = -5108578223428529356L;

    private Class targetClass;

    private String methodName;

    private Class[] parameterTypes;

    private Object[] args;

    public TccInvocation(){

    }


	public TccInvocation(Class clazz, String confirmMethodName, Class[] parameterTypes, Object[] args) {
		this.targetClass = clazz ;
		this.methodName = confirmMethodName ;
		this.parameterTypes = parameterTypes ;
		this.args = args ;
	}


	public Class getTargetClass() {
		return targetClass;
	}


	public void setTargetClass(Class targetClass) {
		this.targetClass = targetClass;
	}


	public String getMethodName() {
		return methodName;
	}


	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}


	public Class[] getParameterTypes() {
		return parameterTypes;
	}


	public void setParameterTypes(Class[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}


	public Object[] getArgs() {
		return args;
	}


	public void setArgs(Object[] args) {
		this.args = args;
	}
}
