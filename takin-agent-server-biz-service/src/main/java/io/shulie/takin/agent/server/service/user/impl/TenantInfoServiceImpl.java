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
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.shulie.takin.agent.server.constant.TenantRedisKeyConstants;
import io.shulie.takin.agent.server.mapper.TenantInfoMapper;
import io.shulie.takin.agent.server.model.entity.TenantInfoEntity;
import io.shulie.takin.agent.server.model.result.TenantInfoResult;
import io.shulie.takin.agent.server.model.result.TenantInfoResult.TenantEnv;
import io.shulie.takin.agent.server.service.user.TenantEnvRefService;
import io.shulie.takin.agent.server.service.user.TenantInfoService;
import io.shulie.takin.utils.json.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author ocean_wll
 * @Date 2022/3/4 5:35 下午
 */
@Service
public class TenantInfoServiceImpl implements TenantInfoService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TenantEnvRefService tenantEnvRefService;

    @Autowired
    private TenantInfoMapper tenantInfoMapper;

    @Override
    public TenantInfoResult getTenantInfo(Long tenantId) {
        String redisKey = String.format(TenantRedisKeyConstants.TENANT_INFO_REDIS_KEY, tenantId);
        if (redisTemplate.hasKey(redisKey)) {
            Object o = redisTemplate.opsForValue().get(redisKey);
            if (o != null) {
                return JsonHelper.json2Bean(JsonHelper.bean2Json(o), TenantInfoResult.class);
            }
        }

        TenantInfoEntity tenantInfoEntity = tenantInfoMapper.selectById(tenantId);
        if (tenantInfoEntity == null) {
            return null;
        }

        List<TenantEnv> envs = tenantEnvRefService.getEnv(tenantId);
        TenantInfoResult tenantInfoExt = this.getTenantInfoExt(tenantInfoEntity, envs);
        redisTemplate.opsForValue().set(redisKey, JsonHelper.bean2Json(tenantInfoExt), 10, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(
            String.format(TenantRedisKeyConstants.TENANT_INFO_APPKEY_REDIS_KEY, tenantInfoEntity.getKey()),
            JsonHelper.bean2Json(tenantInfoExt), 10, TimeUnit.MINUTES);
        return tenantInfoExt;
    }

    @Override
    public TenantInfoResult getTenantInfoByAppKey(String tenantAppKey) {
        String redisKey = String.format(TenantRedisKeyConstants.TENANT_INFO_APPKEY_REDIS_KEY, tenantAppKey);
        if (redisTemplate.hasKey(redisKey)) {
            Object o = redisTemplate.opsForValue().get(redisKey);
            if (o != null) {
                return JsonHelper.json2Bean(JsonHelper.bean2Json(o), TenantInfoResult.class);
            }
        }

        QueryWrapper<TenantInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("`key`", tenantAppKey);
        TenantInfoEntity tenantInfoEntity = tenantInfoMapper.selectOne(queryWrapper);
        if (tenantInfoEntity == null) {
            return null;
        }
        List<TenantEnv> envs = tenantEnvRefService.getEnv(tenantInfoEntity.getId());
        TenantInfoResult tenantInfoExt = this.getTenantInfoExt(tenantInfoEntity, envs);
        redisTemplate.opsForValue().set(redisKey, JsonHelper.bean2Json(tenantInfoExt), 10, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(
            String.format(TenantRedisKeyConstants.TENANT_INFO_REDIS_KEY, tenantInfoEntity.getId()),
            JsonHelper.bean2Json(tenantInfoExt), 10, TimeUnit.MINUTES);

        return tenantInfoExt;
    }

    private TenantInfoResult getTenantInfoExt(TenantInfoEntity tenantInfoEntity, List<TenantEnv> envs) {
        TenantInfoResult ext = new TenantInfoResult();
        ext.setTenantId(tenantInfoEntity.getId());
        ext.setTenantName(tenantInfoEntity.getName());
        ext.setTenantNick(tenantInfoEntity.getNick());
        ext.setTenantCode(tenantInfoEntity.getCode());
        ext.setTenantAppKey(tenantInfoEntity.getKey());
        ext.setEnvs(envs);
        return ext;
    }
}
