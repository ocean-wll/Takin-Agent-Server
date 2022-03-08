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
package io.shulie.takin.agent.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Description 启动类
 * @Author ocean_wll
 * @Date 2022/3/3 3:53 下午
 */
@SpringBootApplication(scanBasePackages = {"io.shulie.takin"})
@MapperScan("io.shulie.takin.agent.server.mapper")
@EnableWebMvc
public class TakinAgentServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TakinAgentServerApplication.class, args);
    }

}
