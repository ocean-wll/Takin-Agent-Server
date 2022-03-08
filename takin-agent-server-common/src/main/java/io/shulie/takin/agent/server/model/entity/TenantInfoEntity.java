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

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @Description
 * @Author ocean_wll
 * @Date 2022/3/4 5:44 下午
 */
@Data
@TableName(value = "t_tenant_info")
@ToString(callSuper = true)
public class TenantInfoEntity implements Serializable {
    private static final long serialVersionUID = 884223799720720665L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 租户key唯一,同时也是userappkey
     */
    @TableField("`key`")
    private String key;

    /**
     * 租户名称
     */
    private String name;

    /**
     * 状态 0: 停用 1:正常 2：试用 3：欠费
     */
    private Integer status;

    /**
     * 中文名称
     */
    private String nick;

    /**
     * 代码
     */
    private String code;


    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtUpdate;

    /**
     * 删除
     * 1 删除, 0 未删除
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 创建者
     */
    private Long createBy;

}