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
package io.shulie.takin.agent.server.config;

import java.time.LocalDate;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description SwaggerConfig
 * @Author ocean_wll
 * @Date 2022/3/3 4:14 下午
 */
@Configuration
@EnableSwagger2
@Profile({"dev"})
public class SwaggerConfig {

    @Value("${swagger.enable:true}")
    private Boolean swaggerEnable;

    @Bean
    public Docket all() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("agent-server(所有接口)")
            .select().apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
            .paths(PathSelectors.any())
            .build()
            .directModelSubstitute(LocalDate.class, String.class)
            .useDefaultResponseMessages(false)
            .enable(swaggerEnable)
            .apiInfo(this.apiInfo());
    }

    /**
     * api info
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Takin-agent-server API Document")
            .build();
    }
}
