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

package com.hmily.tcc.admin.query;

import java.io.Serializable;

import com.hmily.tcc.admin.page.PageParameter;

/**
 * <p>Description: .</p>
 *
 * @author xiaoyu(Myth)
 * @version 1.0
 * @date 2017/10/19 16:46
 * @since JDK 1.8
 */
public class CompensationQuery implements Serializable {

    private static final long serialVersionUID = 3297929795348894462L;

    /**
     * 应用名称
     */
    private String applicationName;

    /**
     * 事务id
     */
    private String transId;

    /**
     * 重试次数
     */
    private Integer retry;


    /**
     * 分页信息
     */
    private PageParameter pageParameter;


	public String getApplicationName() {
		return applicationName;
	}


	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}


	public String getTransId() {
		return transId;
	}


	public void setTransId(String transId) {
		this.transId = transId;
	}


	public Integer getRetry() {
		return retry;
	}


	public void setRetry(Integer retry) {
		this.retry = retry;
	}


	public PageParameter getPageParameter() {
		return pageParameter;
	}


	public void setPageParameter(PageParameter pageParameter) {
		this.pageParameter = pageParameter;
	}


}
