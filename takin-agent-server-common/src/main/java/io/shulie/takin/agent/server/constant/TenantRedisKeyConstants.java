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
package io.shulie.takin.agent.server.constant;

/**
 * @Description
 * @Author ocean_wll
 * @Date 2022/3/4 5:36 下午
 */
public interface TenantRedisKeyConstants {

    /**
     * 租户信息
     */
    String TENANT_INFO_REDIS_KEY = "TAKIN:AGENT:TENANT:INFO:%s";

    /**
     * 租户信息根据appKey存储
     */
    String TENANT_INFO_APPKEY_REDIS_KEY = "TAKIN:AGENT:TENANT:INFO:APPKEY:%s";

    /**
     * 租户信息 是否存在判断
     */
    String TENANT_INFO_EXIST_REDIS_KEY = "TAKIN:AGENT:TENANT:EXIST:INFO:%s";

    /**
     * 租户信息 管理员
     */
    String TENANT_INFO_ADMIN_REDIS_KEY = "TAKIN:AGENT:TENANT:ADMIN:INFO:%s";

    /**
     * 租户列表
     */
    String TENANT_LIST_REDIS_KEY = "TAKIN:AGENT:TENANT:LIST";

    /**
     * 租户配置key
     */
    String TENANT_CONFIG_REDIS_KEY = "takin:agent:tenant:config";

    /**
     * 全部租户配置key
     */
    String TENANT_CONFIG_ALL_REDIS_KEY = "takin:agent:tenant:config:all";

    /**
     * 租户应用配置，只用于老探针
     */
    String TENANT_ALL_APP_REDIS_KEY = "takin:agent:tenant:all:app";

}
