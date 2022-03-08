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
 * @Description 常量类
 * @Author ocean_wll
 * @Date 2022/3/4 5:03 下午
 */
public interface TakinClientAuthConstant {
    Boolean isExpire = Boolean.FALSE;

    /**
     * 万能验证码
     */
    String TOKEN = "OfOqAty85zpyuSNc";

    /**
     * 登录token
     */
    String HEADER_X_TOKEN = "x-token";

    String HEADER_X_USER_TOKEN = "x-user-type";
    String HEADER_X_EXPIRE = "x-expire";

    /**
     * userAppKey agent传递 老版本
     */
    String APP_KEY_HEADER_KEY = "userAppKey";

    /**
     * tenantAppKey agent传递 新版本
     */
    String TENANT_APP_KEY_HEADER_KEY = "tenantAppKey";

    /**
     * 租户code
     */
    String HEADER_TENANT_CODE = "tenant-code";

    /**
     * 环境code
     */
    String HEADER_ENV_CODE = "env-code";

    /**
     * agent的环境code
     */
    String AGENT_HEADER_ENV_CODE = "envCode";

    /**
     * 用户id
     */
    String HEADER_USER_ID = "user-id";

    /**
     * agent用户id
     */
    String AGENT_HEADER_USER_ID = "userId";

    /**
     * 用户key
     */
    String KEY_USER_MAP_REDIS_KEY = "takin:agent:key2user:%s";

    /**
     * 大盘
     */
    String DASHBOARD_MENU = "dashboard";

}
