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

import java.util.List;

import io.shulie.takin.agent.server.model.result.TenantInfoResult.TenantEnv;

/**
 * @Description
 * @Author ocean_wll
 * @Date 2022/3/4 5:49 下午
 */
public interface TenantEnvRefService {

    /**
     * 根据租户获取环境
     * @param tenantId
     * @return
     */
    List<TenantEnv> getEnv(Long tenantId);
}
