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
package io.shulie.takin.agent.server.service.user.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import io.shulie.takin.agent.server.mapper.TenantEnvRefMapper;
import io.shulie.takin.agent.server.model.entity.TenantEnvRefEntity;
import io.shulie.takin.agent.server.model.result.TenantEnvRefListResult;
import io.shulie.takin.agent.server.model.result.TenantInfoResult;
import io.shulie.takin.agent.server.model.result.TenantInfoResult.TenantEnv;
import io.shulie.takin.agent.server.service.user.TenantEnvRefService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author ocean_wll
 * @Date 2022/3/4 5:49 下午
 */
@Service
public class TenantEnvRefServiceImpl implements TenantEnvRefService {

    @Autowired
    private TenantEnvRefMapper tenantEnvRefMapper;

    @Override
    public List<TenantEnv> getEnv(Long tenantId) {

        QueryWrapper<TenantEnvRefEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tenant_id", tenantId);
        queryWrapper.eq("is_deleted", 0);

        List<TenantEnvRefListResult> results = getTenantEnvRefListResults(queryWrapper);
        return results.stream().map(result -> {
            TenantInfoResult.TenantEnv env = new TenantEnv();
            env.setEnvCode(result.getEnvCode());
            env.setEnvName(result.getEnvName());
            env.setDesc(result.getDesc());
            env.setIsDefault(result.getIsDefault());
            return env;
        }).collect(Collectors.toList());
    }

    private List<TenantEnvRefListResult> getTenantEnvRefListResults(QueryWrapper<TenantEnvRefEntity> queryWrapper) {
        List<TenantEnvRefEntity> entities = tenantEnvRefMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(entities)) {
            return Lists.newArrayList();
        }
        return entities.stream().map(entity -> {
            TenantEnvRefListResult result = new TenantEnvRefListResult();
            BeanUtils.copyProperties(entity, result);
            return result;
        }).collect(Collectors.toList());
    }
}
