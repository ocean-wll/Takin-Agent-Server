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
package io.shulie.takin.agent.server.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import com.google.common.collect.Maps;
import io.shulie.takin.agent.server.constant.TakinClientAuthConstant;
import io.shulie.takin.agent.server.global.TakinAgentServerContext;
import io.shulie.takin.agent.server.model.entity.User;
import io.shulie.takin.agent.server.model.result.TakinUserResult;
import io.shulie.takin.agent.server.model.result.TenantInfoResult;
import io.shulie.takin.agent.server.service.user.TakinWebUserService;
import io.shulie.takin.agent.server.service.user.TenantInfoService;
import io.shulie.takin.utils.json.JsonHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Description agent拦截器
 * @Author ocean_wll
 * @Date 2022/3/4 4:44 下午
 */
@Component
@Slf4j
public final class AgentInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate userRedisTemplate;

    @Autowired
    private TakinWebUserService takinWebUserService;

    @Autowired
    private TenantInfoService tenantInfoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws IOException {
        TakinAgentServerContext.clear();
        String tenantAppKey = request.getHeader(TakinClientAuthConstant.TENANT_APP_KEY_HEADER_KEY);
        String envCode = request.getHeader(TakinClientAuthConstant.AGENT_HEADER_ENV_CODE);
        String userIdHeader = request.getHeader(TakinClientAuthConstant.AGENT_HEADER_USER_ID);

        if (StringUtils.isBlank(tenantAppKey) || StringUtils.isBlank(envCode)
            || StringUtils.isBlank(userIdHeader)) {
            assembleResponse(response,
                getResponseMap(response, "禁止访问, Agent请求接口需要携带 tenantAppKey, envCode, userId"));
            return false;
        }

        TenantInfoResult tenantInfoResult = tenantInfoService.getTenantInfoByAppKey(tenantAppKey);
        // 租户是否存在
        if (tenantInfoResult == null) {
            assembleResponse(response, getResponseMap(response, "禁止访问, Agent 请求接口, " + tenantAppKey + "对应的租户不存在!"));
            return false;
        }

        Long userId = Long.parseLong(userIdHeader);

        // 赋值上下文
        TakinAgentServerContext.setTenantAppKey(tenantAppKey);
        TakinAgentServerContext.setTenantCode(tenantInfoResult.getTenantCode());
        TakinAgentServerContext.setTenantId(tenantInfoResult.getTenantId());
        TakinAgentServerContext.setEnvCode(envCode);

        // 获取 用户
        User user = JsonHelper.json2Bean(JSON.toJSONString(
                userRedisTemplate.opsForHash().get(transferUserRedisKey(tenantAppKey), userId + "")),
            User.class);

        if (user == null) {
            TakinUserResult takinUserResult = takinWebUserService.selectById(userId);
            if (takinUserResult == null) {
                assembleResponse(response, getResponseMap(response, "禁止访问, Agent 请求接口, " + userId + "对应的用户不存在!"));
                return false;
            }
            user = new User();
            BeanUtils.copyProperties(takinUserResult, user);
            userRedisTemplate.opsForHash().put(transferUserRedisKey(tenantAppKey), userId + "", user);
            userRedisTemplate.expire(transferUserRedisKey(tenantAppKey), 1, TimeUnit.HOURS);
        }
        user.setLoginChannel(1);
        TakinAgentServerContext.setUser(user);
        return true;
    }

    private Map<String, Object> getResponseMap(HttpServletResponse response, String errorMessage) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("msg", errorMessage);
        map.put("status", HttpStatus.FORBIDDEN.value());
        response.setStatus(HttpStatus.FORBIDDEN.value());
        log.error(errorMessage);
        return map;
    }

    public void assembleResponse(HttpServletResponse response, Map<String, Object> map) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter;
        try {
            printWriter = response.getWriter();
            String s = JSON.toJSONString(map);
            printWriter.write(s);
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            log.error("error:", e);
        }
    }

    private String transferUserRedisKey(String userAppKey) {
        return String.format(TakinClientAuthConstant.KEY_USER_MAP_REDIS_KEY, userAppKey);
    }
}
