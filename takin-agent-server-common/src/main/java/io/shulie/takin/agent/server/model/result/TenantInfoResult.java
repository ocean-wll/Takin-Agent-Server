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
package io.shulie.takin.agent.server.model.result;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author ocean_wll
 * @Date 2022/3/4 5:33 下午
 */
@Data
public class TenantInfoResult {

    /**
     * 租户id
     */
    private Long tenantId;
    /**
     * 租户名
     */
    private String tenantName;

    /**
     * 租户名
     */
    private String tenantNick;
    /**
     * 租户代码
     */
    private String tenantCode;
    /**
     * 租户key
     */
    private String tenantAppKey;
    /**
     * 租户环境
     */
    private List<TenantEnv> envs;

    /**
     * 创建者
     */
    private Long createBy;

    @Data
    @NoArgsConstructor
    public static class TenantEnv {
        /**
         * 环境代码
         */
        private String envCode;
        /**
         * 环境名
         */
        private String envName;

        /**
         * 描述
         */
        private String desc;

        /**
         * 是否默认
         */
        private Boolean isDefault;
    }
}
