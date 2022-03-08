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
package io.shulie.takin.agent.server.controller;

import java.util.List;

import io.shulie.takin.agent.server.enums.ErrorEnum;
import io.shulie.takin.agent.server.exception.TakinAgentServerException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 健康检查接口
 * @Author ocean_wll
 * @Date 2022/3/3 4:24 下午
 */
@RestController
@RequestMapping("/health")
@Api(tags = "健康检查控制器")
@Slf4j
public class HealthController {

    @GetMapping("/check")
    @ApiOperation("健康检查")
    public String health() {
        return "ok";
    }
}
