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
package io.shulie.takin.agent.server.global;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.alibaba.ttl.TransmittableThreadLocal;

import io.shulie.takin.agent.server.model.entity.User;

/**
 * @Description 全局上下文
 * @Author ocean_wll
 * @Date 2022/3/4 4:58 下午
 */
public class TakinAgentServerContext {
    /**
     * 线程来源
     */
    public static TransmittableThreadLocal<Integer> sourceThreadLocal = new TransmittableThreadLocal<>();

    /**
     * 通过 http 登录的当前用户
     */
    public static TransmittableThreadLocal<User> userThreadLocal = new TransmittableThreadLocal<>();

    /**
     * 当前登录用户的租户 id
     */
    public static TransmittableThreadLocal<Long> tenantIdThreadLocal = new TransmittableThreadLocal<>();
    /**
     * 当前登录用户的租户 code
     */
    public static TransmittableThreadLocal<String> tenantCodeThreadLocal = new TransmittableThreadLocal<>();

    /**
     * 当前登录用户的环境 env_code
     */
    public static TransmittableThreadLocal<String> envCodeThreadLocal = new TransmittableThreadLocal<>();

    /**
     * 当前登录用户的租户key
     */
    public static TransmittableThreadLocal<String> tenantAppKeyThreadLocal = new TransmittableThreadLocal<>();

    public static TransmittableThreadLocal<List<Long>> queryAllowUserIdListThreadLocal = new TransmittableThreadLocal<>();

    public static TransmittableThreadLocal<List<Long>> updateAllowUserIdListThreadLocal = new TransmittableThreadLocal<>();

    public static TransmittableThreadLocal<List<Long>> deleteAllowUserIdListThreadLocal = new TransmittableThreadLocal<>();

    public static TransmittableThreadLocal<List<Long>> enableDisableAllowUserIdListThreadLocal = new TransmittableThreadLocal<>();

    public static TransmittableThreadLocal<List<Long>> startStopAllowUserIdListThreadLocal = new TransmittableThreadLocal<>();

    public static TransmittableThreadLocal<List<Long>> downloadAllowUserIdListThreadLocal = new TransmittableThreadLocal<>();

    private TakinAgentServerContext() { /* no instance */ }

    public static User getUser() {
        return userThreadLocal.get();
    }

    public static void setUser(User user) {
        userThreadLocal.remove();
        userThreadLocal.set(user);
    }

    /**
     * 设置来源
     * @param source
     */
    public static void setSource(Integer source) {
        sourceThreadLocal.remove();
        sourceThreadLocal.set(source);
    }

    public static Integer getSource() {
        return sourceThreadLocal.get();
    }

    /**
     * 获取租户id
     *
     * @return 租户id
     */
    public static Long getTenantId() {
        Long tenantId = tenantIdThreadLocal.get();
        if (tenantId != null) {
            return tenantId;
        }
        return null;
    }

    /**
     * 设置租户id
     *
     * @return 租户id
     */
    public static void setTenantId(Long tenantId) {
        tenantIdThreadLocal.remove();
        tenantIdThreadLocal.set(tenantId);
    }
    /**
     * 获取租户id
     *
     * @return 租户id
     */
    public static String getTenantCode() {
        String tenantCode = tenantCodeThreadLocal.get();
        if (tenantCode != null) {
            return tenantCode;
        }
        return null;
    }

    /**
     * 设置租户id
     *
     * @return 租户id
     */
    public static void setTenantCode(String tenantCode) {
        tenantCodeThreadLocal.remove();
        tenantCodeThreadLocal.set(tenantCode);
    }


    /**
     * 获取环境
     *
     * @return 环境
     */
    public static String getEnvCode() {
        String envCode = envCodeThreadLocal.get();
        if (envCode != null) {
            return envCode;
        }
        return null;
    }

    /**
     * 设置环境
     */
    public static void setEnvCode(String envCode) {
        envCodeThreadLocal.remove();
        envCodeThreadLocal.set(envCode);
    }

    public static void clear() {
        userThreadLocal.remove();
        tenantAppKeyThreadLocal.remove();
        envCodeThreadLocal.remove();
        tenantAppKeyThreadLocal.remove();
        sourceThreadLocal.remove();
    }

    public static void clearAuth() {
        queryAllowUserIdListThreadLocal.remove();
        updateAllowUserIdListThreadLocal.remove();
        deleteAllowUserIdListThreadLocal.remove();
        enableDisableAllowUserIdListThreadLocal.remove();
        startStopAllowUserIdListThreadLocal.remove();
        downloadAllowUserIdListThreadLocal.remove();
    }

    public static void setEmptyAuth() {
        clearAuth();
        queryAllowUserIdListThreadLocal.set(Collections.singletonList(-1L));
        updateAllowUserIdListThreadLocal.set(Collections.singletonList(-1L));
        deleteAllowUserIdListThreadLocal.set(Collections.singletonList(-1L));
        enableDisableAllowUserIdListThreadLocal.set(Collections.singletonList(-1L));
        startStopAllowUserIdListThreadLocal.set(Collections.singletonList(-1L));
        downloadAllowUserIdListThreadLocal.set(Collections.singletonList(-1L));
    }

    public static String getTenantAppKey() {
        return tenantAppKeyThreadLocal.get();
    }

    /**
     * 只有agent 访问才有 userAppKey
     * @param tenantAppKey
     */
    public static void setTenantAppKey(String tenantAppKey) {
        tenantAppKeyThreadLocal.remove();
        tenantAppKeyThreadLocal.set(tenantAppKey);
    }

    public static List<Long> getQueryAllowUserIdList() {
        List<Long> result = queryAllowUserIdListThreadLocal.get();
        return result == null ? new ArrayList<>(0) : result;
    }

    public static void setQueryAllowUserIdList(List<Long> userIdList) {
        queryAllowUserIdListThreadLocal.remove();
        queryAllowUserIdListThreadLocal.set(userIdList);
    }

    public static List<Long> getUpdateAllowUserIdList() {
        List<Long> result = updateAllowUserIdListThreadLocal.get();
        return result == null ? new ArrayList<>(0) : result;
    }

    public static void setUpdateAllowUserIdList(List<Long> updateAllowUserIdList) {
        updateAllowUserIdListThreadLocal.remove();
        updateAllowUserIdListThreadLocal.set(updateAllowUserIdList);
    }

    public static List<Long> getDeleteAllowUserIdList() {
        List<Long> result = deleteAllowUserIdListThreadLocal.get();
        return result == null ? new ArrayList<>(0) : result;
    }

    public static void setDeleteAllowUserIdList(List<Long> deleteAllowUserIdList) {
        deleteAllowUserIdListThreadLocal.remove();
        TakinAgentServerContext.deleteAllowUserIdListThreadLocal.set(deleteAllowUserIdList);
    }

    public static List<Long> getEnableDisableAllowUserIdList() {
        List<Long> result = enableDisableAllowUserIdListThreadLocal.get();
        return result == null ? new ArrayList<>(0) : result;
    }

    public static void setEnableDisableAllowUserIdList(List<Long> enableDisableAllowUserIdList) {
        enableDisableAllowUserIdListThreadLocal.remove();
        enableDisableAllowUserIdListThreadLocal.set(enableDisableAllowUserIdList);
    }

    public static List<Long> getStartStopAllowUserIdList() {
        List<Long> result = startStopAllowUserIdListThreadLocal.get();
        return result == null ? new ArrayList<>(0) : result;
    }

    public static void setStartStopAllowUserIdList(List<Long> startStopAllowUserIdList) {
        startStopAllowUserIdListThreadLocal.remove();
        startStopAllowUserIdListThreadLocal.set(startStopAllowUserIdList);
    }

    public static List<Long> getDownloadAllowUserIdList() {
        List<Long> result = downloadAllowUserIdListThreadLocal.get();
        return result == null ? new ArrayList<>(0) : result;
    }

    public static void setDownloadAllowUserIdList(List<Long> downloadAllowUserIdList) {
        downloadAllowUserIdListThreadLocal.remove();
        downloadAllowUserIdListThreadLocal.set(downloadAllowUserIdList);
    }
}
