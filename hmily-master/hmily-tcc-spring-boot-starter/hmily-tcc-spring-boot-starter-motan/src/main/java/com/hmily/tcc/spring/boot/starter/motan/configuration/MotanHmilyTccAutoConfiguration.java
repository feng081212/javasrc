/*
 *
 *  * Licensed to the Apache Software Foundation (ASF) under one or more
 *  * contributor license agreements.  See the NOTICE file distributed with
 *  * this work for additional information regarding copyright ownership.
 *  * The ASF licenses this file to You under the Apache License, Version 2.0
 *  * (the "License"); you may not use this file except in compliance with
 *  * the License.  You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  
 */

package com.hmily.tcc.spring.boot.starter.motan.configuration;

import com.hmily.tcc.core.bootstrap.TccTransactionBootstrap;
import com.hmily.tcc.core.service.TccInitService;
import com.hmily.tcc.spring.boot.starter.motan.config.TccConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <p>Description: .</p>
 *
 * @author xiaoyu(Myth)
 * @version 1.0
 * @date 2018/3/7 14:27
 * @since JDK 1.8
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableConfigurationProperties
@ComponentScan(basePackages = {"com.hmily.tcc"})
public class MotanHmilyTccAutoConfiguration {


    private final TccConfigProperties tccConfigProperties;

    @Autowired
    public MotanHmilyTccAutoConfiguration(TccConfigProperties tccConfigProperties) {
        this.tccConfigProperties = tccConfigProperties;
    }

    @Bean
    public TccTransactionBootstrap tccTransactionBootstrap(TccInitService tccInitService) {
        final TccTransactionBootstrap tccTransactionBootstrap = new TccTransactionBootstrap(tccInitService);
        tccTransactionBootstrap.setBufferSize(tccConfigProperties.getBufferSize());
        tccTransactionBootstrap.setRetryMax(tccConfigProperties.getRetryMax());
        tccTransactionBootstrap.setRecoverDelayTime(tccConfigProperties.getRecoverDelayTime());
        tccTransactionBootstrap.setRepositorySuffix(tccConfigProperties.getRepositorySuffix());
        tccTransactionBootstrap.setRepositorySupport(tccConfigProperties.getRepositorySupport());
        tccTransactionBootstrap.setScheduledDelay(tccConfigProperties.getScheduledDelay());
        tccTransactionBootstrap.setScheduledThreadMax(tccConfigProperties.getScheduledThreadMax());
        tccTransactionBootstrap.setSerializer(tccConfigProperties.getSerializer());
        tccTransactionBootstrap.setTccFileConfig(tccConfigProperties.getTccFileConfig());
        tccTransactionBootstrap.setTccDbConfig(tccConfigProperties.getTccDbConfig());
        tccTransactionBootstrap.setTccRedisConfig(tccConfigProperties.getTccRedisConfig());
        tccTransactionBootstrap.setTccZookeeperConfig(tccConfigProperties.getTccZookeeperConfig());
        tccTransactionBootstrap.setTccMongoConfig(tccConfigProperties.getTccMongoConfig());
        return tccTransactionBootstrap;
    }
}
