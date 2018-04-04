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
public class Participant implements Serializable {

    private static final long serialVersionUID = -2590970715288987627L;


    private String transId;

    private TccInvocation confirmTccInvocation;

    private TccInvocation cancelTccInvocation;

    public Participant(){

    }

	public Participant(String transId, TccInvocation confirmInvocation, TccInvocation cancelInvocation) {
		this.transId = transId ;
		this.confirmTccInvocation = confirmInvocation ;
		this.cancelTccInvocation = cancelInvocation ;
	}
	

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public TccInvocation getConfirmTccInvocation() {
		return confirmTccInvocation;
	}

	public void setConfirmTccInvocation(TccInvocation confirmTccInvocation) {
		this.confirmTccInvocation = confirmTccInvocation;
	}

	public TccInvocation getCancelTccInvocation() {
		return cancelTccInvocation;
	}

	public void setCancelTccInvocation(TccInvocation cancelTccInvocation) {
		this.cancelTccInvocation = cancelTccInvocation;
	}
}
