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

package com.hmily.tcc.common.bean.adapter;

import java.util.Date;

import com.hmily.tcc.common.enums.TccActionEnum;
import com.hmily.tcc.common.enums.TccRoleEnum;

/**
 * <p>Description: .</p>
 *
 * @author xiaoyu(Myth)
 * @version 1.0
 * @date 2017/10/30 10:39
 * @since JDK 1.8
 */
public class CoordinatorRepositoryAdapter {


    /**
     * 事务id
     */
    private String transId;

    /**
     * 事务状态 {@linkplain TccActionEnum}
     */
    private int status;

    /**
     * 事务类型 {@linkplain TccRoleEnum}
     */
    private int role;

    /**
     * 重试次数
     */
    private volatile int retriedCount = 0;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date lastTime;

    /**
     * 版本号 乐观锁控制
     */
    private Integer version = 1;

    /**
     * 模式
     */
    private Integer pattern;

    /**
     * 序列化后的二进制信息
     */
    private byte[] contents;



    /**
     * 调用接口名称
     */
    private String targetClass;


    /**
     * 调用方法名称
     */
    private String targetMethod;

    /**
     * confirm方法
     */
    private String confirmMethod;


    /**
     * cancel方法
     */
    private String cancelMethod;


	public String getTransId() {
		return transId;
	}


	public void setTransId(String transId) {
		this.transId = transId;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getRole() {
		return role;
	}


	public void setRole(int role) {
		this.role = role;
	}


	public int getRetriedCount() {
		return retriedCount;
	}


	public void setRetriedCount(int retriedCount) {
		this.retriedCount = retriedCount;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Date getLastTime() {
		return lastTime;
	}


	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public Integer getPattern() {
		return pattern;
	}


	public void setPattern(Integer pattern) {
		this.pattern = pattern;
	}


	public byte[] getContents() {
		return contents;
	}


	public void setContents(byte[] contents) {
		this.contents = contents;
	}


	public String getTargetClass() {
		return targetClass;
	}


	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass;
	}


	public String getTargetMethod() {
		return targetMethod;
	}


	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}


	public String getConfirmMethod() {
		return confirmMethod;
	}


	public void setConfirmMethod(String confirmMethod) {
		this.confirmMethod = confirmMethod;
	}


	public String getCancelMethod() {
		return cancelMethod;
	}


	public void setCancelMethod(String cancelMethod) {
		this.cancelMethod = cancelMethod;
	}


}
