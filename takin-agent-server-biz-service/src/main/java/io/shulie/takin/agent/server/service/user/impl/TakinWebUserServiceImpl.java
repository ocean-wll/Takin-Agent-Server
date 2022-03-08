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

import io.shulie.takin.agent.server.mapper.TroUserMapper;
import io.shulie.takin.agent.server.model.entity.TroUserEntity;
import io.shulie.takin.agent.server.model.result.TakinUserResult;
import io.shulie.takin.agent.server.service.user.TakinWebUserService;
import io.shulie.takin.utils.json.JsonHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author ocean_wll
 * @Date 2022/3/4 5:14 下午
 */
@Service
public class TakinWebUserServiceImpl implements TakinWebUserService {

    @Autowired
    private TroUserMapper troUserMapper;

    @Autowired
    private RedisTemplate userRedisTemplate;

    @Override
    public TakinUserResult selectByUserAppKey(String userAppkey) {
        String redisKey = "TAKIN:AGENT:USER:" + userAppkey;
        if (userRedisTemplate.hasKey(redisKey)) {
            return JsonHelper.json2Bean(JsonHelper.bean2Json(userRedisTemplate.opsForValue().get(redisKey)),
                TakinUserResult.class);
        }
        List<TroUserEntity> troUserEntities = troUserMapper.getUserByUserAppKey(userAppkey);
        if (CollectionUtils.isEmpty(troUserEntities)) {
            return null;
        }
        TakinUserResult takinUserResult = new TakinUserResult();
        BeanUtils.copyProperties(troUserEntities.get(0), takinUserResult);
        userRedisTemplate.opsForValue().set(redisKey, JsonHelper.bean2Json(takinUserResult), 10, TimeUnit.MINUTES);
        return takinUserResult;
    }

    @Override
    public TakinUserResult selectById(Long id) {
        String redisKey = "TAKIN:AGENT:USER:ID:" + id;
        if (userRedisTemplate.hasKey(redisKey)) {
            return JsonHelper.json2Bean(JsonHelper.bean2Json(userRedisTemplate.opsForValue().get(redisKey)),
                TakinUserResult.class);
        }
        TroUserEntity troUserEntity = troUserMapper.selectById(id);
        if (troUserEntity == null) {
            return null;
        }
        TakinUserResult takinUserResult = new TakinUserResult();
        BeanUtils.copyProperties(troUserEntity, takinUserResult);
        userRedisTemplate.opsForValue().set(redisKey, JsonHelper.bean2Json(takinUserResult), 10, TimeUnit.MINUTES);
        return takinUserResult;
    }
}
