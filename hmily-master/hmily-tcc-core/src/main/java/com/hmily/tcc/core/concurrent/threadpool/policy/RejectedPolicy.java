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
package com.hmily.tcc.core.concurrent.threadpool.policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @author xiaoyu
 */
public class RejectedPolicy implements RejectedExecutionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(RejectedPolicy.class);

    private final String threadName;

    public RejectedPolicy() {
        this(null);
    }

    public RejectedPolicy(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
        if (threadName != null) {
            LOG.error("tccTransaction Thread pool [{}] is exhausted, executor={}", threadName, executor.toString());
        }

        if (runnable instanceof RejectedRunnable) {
            ((RejectedRunnable) runnable).rejected();
        } else {
            if (!executor.isShutdown()) {
                BlockingQueue<Runnable> queue = executor.getQueue();
                int discardSize = queue.size() >> 1;
                for (int i = 0; i < discardSize; i++) {
                    queue.poll();
                }

                try {
                    queue.put(runnable);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

