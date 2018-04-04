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
package com.hmily.tcc.common.config;



/**
 * @author xiaoyu
 */
public class TccMongoConfig {

    /**
     * mongo数据库设置
     */
    private String mongoDbName;

    /**
     * mongo数据库URL
     */
    private String mongoDbUrl;
    /**
     * mongo数据库用户名
     */
    private String mongoUserName;

    /**
     * mongo数据库密码
     */
    private String mongoUserPwd;

	public String getMongoDbName() {
		return mongoDbName;
	}

	public void setMongoDbName(String mongoDbName) {
		this.mongoDbName = mongoDbName;
	}

	public String getMongoDbUrl() {
		return mongoDbUrl;
	}

	public void setMongoDbUrl(String mongoDbUrl) {
		this.mongoDbUrl = mongoDbUrl;
	}

	public String getMongoUserName() {
		return mongoUserName;
	}

	public void setMongoUserName(String mongoUserName) {
		this.mongoUserName = mongoUserName;
	}

	public String getMongoUserPwd() {
		return mongoUserPwd;
	}

	public void setMongoUserPwd(String mongoUserPwd) {
		this.mongoUserPwd = mongoUserPwd;
	}

}
