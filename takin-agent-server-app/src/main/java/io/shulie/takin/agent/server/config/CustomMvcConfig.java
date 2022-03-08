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

import io.shulie.takin.agent.server.factory.TrimmedAnnotationFormatterFactory;
import io.shulie.takin.agent.server.interceptor.AgentInterceptor;
import io.shulie.takin.agent.server.interceptor.CommonInterceptor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description web mvc config
 * @Author ocean_wll
 * @Date 2022/3/3 5:11 下午
 */
@Configuration
public class CustomMvcConfig implements WebMvcConfigurer {

    @Autowired
    private CommonInterceptor commonInterceptor;

    @Autowired
    private AgentInterceptor agentInterceptor;

    @Autowired
    private TrimmedAnnotationFormatterFactory trimmedAnnotationFormatterFactory;

    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(trimmedAnnotationFormatterFactory);
        WebMvcConfigurer.super.addFormatters(registry);
    }

    /**
     * 跨域支持
     */
    private CorsConfiguration corsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600 * 24L);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig());
        return new CorsFilter(source);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor).addPathPatterns("/**");
        registry.addInterceptor(agentInterceptor).addPathPatterns("/**");
    }
}
