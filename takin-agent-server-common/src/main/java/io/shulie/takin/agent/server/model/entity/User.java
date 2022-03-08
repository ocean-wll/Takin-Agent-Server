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
package io.shulie.takin.agent.server.model.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Description 用户对象
 * @Author ocean_wll
 * @Date 2022/3/4 5:00 下午
 */
@Data
public class User {

    private Long id;

    private Long tenantId;

    private String name;

    private String nick;

    private String key;

    private String salt;

    private String password;

    private Integer status;

    /**
     * 用户类型 0:系统管理员 1:其他
     */
    private Integer userType;

    private Integer model;

    /**
     * 角色 0:管理员，1:体验用户 2:正式用户（作废）
     */
    private Integer role;

    private Integer isDelete;

    private Date gmtCreate;

    private Date gmtUpdate;

    private String version;

    @JsonProperty("xToken")
    private String xToken;

    private List<String> permissionUrl;

    private Map<String, Boolean> permissionMenu;

    private Map<String, Boolean> permissionAction;

    private Map<String, List<Integer>> permissionData;
    /**
     * 登录渠道
     * 0-console 前端页面
     * 1-agent agent
     */
    private Integer loginChannel;

    /**
     * 环境code
     */
    private String envCode;

    /**
     * 租户code
     */
    private String tenantCode;

    /**
     * 租户key
     */
    private String tenantAppKey;

    /**
     * 登录ip
     */
    private String loginIp;

    /**
     * 是否有超级管理员的权限(创建租户)
     */
    private Integer isSuper;
}
