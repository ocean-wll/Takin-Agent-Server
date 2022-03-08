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
package io.shulie.takin.agent.server.common;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description 统一返回结果对象
 * @Author ocean_wll
 * @Date 2022/3/3 4:49 下午
 */
public class Response<T> {

    public static final boolean DEFAULT_SUCCESS = true;

    /**
     * 错误信息实体
     */
    private ErrorInfo error;

    /**
     * 返回数据，如果请求失败，则为空
     */
    @ApiModelProperty(name = "data", value = "返回的具体数据")
    private T data;

    /**
     * 成功标记
     */
    @ApiModelProperty(name = "success", value = "是否成功")
    private Boolean success;

    private Boolean unsuccess;

    public Response(T data) {
        this(null, data, DEFAULT_SUCCESS);
    }

    public Response(ErrorInfo error, boolean success) {
        this(error, null, success);
    }

    public Response(ErrorInfo error, T data, boolean success) {
        this.error = error;
        this.data = data;
        this.success = success;
    }

    public Response(ErrorInfo error, T data, boolean success, int code) {
        this.error = error;
        this.data = data;
        this.success = success;
    }

    /**
     * 返回成功,无内容
     *
     * @return -
     */
    public static <T> Response<T> success() {
        return new Response<>(null);
    }

    /**
     * 返回成功
     *
     * @return -
     */
    public static <T> Response<T> success(T data) {
        if (data instanceof List) {
            return (Response<T>)successList((List)data);
        }
        return new Response<>(data);
    }

    public static Response<List> successList(List data) {
        return new Response<>(data);
    }

    /**
     * 返回失败，使用传入的错误码
     */
    public static Response fail(String code, String msgTemplate, Object... args) {
        ErrorInfo errorInfo = ErrorInfo.build(code, msgTemplate, args);
        return new Response<>(errorInfo, false);
    }

    /**
     * 返回失败，使用传入的错误码
     */
    public static Response fail(String msgTemplate, Object... args) {
        ErrorInfo errorInfo = ErrorInfo.build("500", msgTemplate, args);
        return new Response<>(errorInfo, false);
    }

    public ErrorInfo getError() {
        return error;
    }

    public void setError(ErrorInfo error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
