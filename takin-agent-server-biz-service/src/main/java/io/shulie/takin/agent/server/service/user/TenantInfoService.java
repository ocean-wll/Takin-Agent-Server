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
package io.shulie.takin.agent.server.service.user;

import io.shulie.takin.agent.server.model.result.TenantInfoResult;

/**
 * @Description
 * @Author ocean_wll
 * @Date 2022/3/4 5:32 下午
 */
public interface TenantInfoService {

    /**
     * 根据 id 获取租户信息
     *
     * @param tenantId 租户主键
     * @return 租户信息
     */
    TenantInfoResult getTenantInfo(Long tenantId);

    /**
     * 根据tenantAppKey获取租户信息
     *
     * @param tenantAppKey tenantAppKey
     * @return 租户信息
     */
    TenantInfoResult getTenantInfoByAppKey(String tenantAppKey);
}
