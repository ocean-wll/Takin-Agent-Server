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
 * @Date 2022/3/4 5:50 下午
 */
@Data
@TableName(value = "t_tenant_env_ref")
@ToString(callSuper = true)
public class TenantEnvRefEntity implements Serializable {
    private static final long serialVersionUID = 821700204923187081L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 删除
     * 1 删除, 0 未删除
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 环境代码，测试环境：test,生产环境：prod
     */
    private String envCode;

    /**
     * 环境名称
     */
    private String envName;

    /**
     * 描述
     */
    @TableField("`desc`")
    private String desc;

    /**
     * 是否默认
     */
    private Boolean isDefault;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtUpdate;

}
