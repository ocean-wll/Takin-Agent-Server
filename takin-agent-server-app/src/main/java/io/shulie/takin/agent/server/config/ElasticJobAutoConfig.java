/*
 * Copyright 2021 Shulie Technology, Co.Ltd
 * Email: shulie@shulie.io
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.shulie.takin.agent.server.config;

import com.dangdang.ddframe.job.event.JobEventConfiguration;
import io.shulie.takin.job.ElasticJobProperties;
import io.shulie.takin.job.ElasticRegCenterConfig;
import io.shulie.takin.job.config.ElasticJobConfig;
import io.shulie.takin.job.config.zk.ZkClientConfig;
import io.shulie.takin.job.factory.SpringJobSchedulerFactory;
import io.shulie.takin.job.parser.JobConfParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description elasticJob配置
 * @Author ocean_wll
 * @Date 2022/3/4 2:43 下午
 */
@Configuration
@EnableConfigurationProperties(ElasticJobProperties.class)
public class ElasticJobAutoConfig {

    @Value("${takin.agent.server.job.namespace:dev}")
    private String jobNamespace;

    @Value("${takin.agent.server.config.zk.addr}")
    private String zkAddress;

    @Bean
    @ConditionalOnMissingBean(JobEventConfiguration.class)
    public SpringJobSchedulerFactory springJobSchedulerFactory(ElasticJobProperties elasticJobProperties) {
        ElasticJobConfig elasticJobConfig = new ElasticJobConfig();
        ZkClientConfig zkClientConfig = new ZkClientConfig();
        zkClientConfig.setZkServers(zkAddress);
        zkClientConfig.setNamespace("takin-agent-server-job-" + jobNamespace);
        elasticJobConfig.setZkClientConfig(zkClientConfig);
        ElasticRegCenterConfig elasticRegCenterConfig = new ElasticRegCenterConfig(elasticJobConfig);
        return new SpringJobSchedulerFactory(elasticJobProperties, elasticRegCenterConfig.regCenter());
    }

    @Bean
    public JobConfParser jobConfParser(SpringJobSchedulerFactory springJobSchedulerFactory) {
        return new JobConfParser(springJobSchedulerFactory);
    }
}
