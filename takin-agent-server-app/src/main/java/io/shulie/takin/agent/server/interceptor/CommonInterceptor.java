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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.shulie.takin.agent.server.utils.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Description 公共拦截器, 添加统一的拦截
 * @Author ocean_wll
 * @Date 2022/3/3 5:14 下午
 */
@Component
public final class CommonInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
        IOException {
        // 404 处理
        if (Objects.equals(response.getStatus(), HttpStatus.NOT_FOUND.value())) {
            Map<String, Object> map = new HashMap<>(4);
            map.put("msg", "调用接口不存在, 请查看配置是否正确!");
            map.put("status", HttpStatus.NOT_FOUND.value());
            this.printResponse(response, JsonUtil.bean2Json(map), HttpStatus.NOT_FOUND.value());
            return false;
        }

        return true;
    }

    /**
     * 打印响应信息
     *
     * @param response     响应
     * @param responseJson 响应信息
     * @param responseCode 响应码
     * @throws IOException io 异常
     */
    void printResponse(HttpServletResponse response, String responseJson, Integer responseCode) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(responseCode);
        PrintWriter printWriter = response.getWriter();
        printWriter.write(responseJson);
        printWriter.flush();
        printWriter.close();
    }

}
