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
package io.shulie.takin.agent.server.enums;

import io.shulie.takin.parent.exception.entity.ExceptionReadable;

/**
 * @Description
 * @Author ocean_wll
 * @Date 2022/3/4 9:47 上午
 */
public enum ErrorEnum implements ExceptionReadable {

    BIZ_EXCEPTION(1000000000, "业务异常"),
    ;

    /**
     * >>>>>>> chaos_0.0.2
     * 错误码
     */
    private int errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 构造方法
     *
     * @param errorCode    错误码
     * @param errorMessage 错误信息
     * @author shulie
     * @version 1.0
     */
    private ErrorEnum(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * @return the errorCode
     * @author shulie
     * @version 1.0
     */
    @Override
    public String getErrorCode() {
        return String.valueOf(errorCode);
    }

    @Override
    public String getDefaultValue() {
        return null;
    }

    /**
     * @param errorCode the errorCode to set
     * @author shulie
     * @version 1.0
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMessage
     * @author shulie
     * @version 1.0
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     * @author shulie
     * @version 1.0
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
