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
package io.shulie.takin.agent.server.exception;

import io.shulie.takin.parent.exception.entity.BaseException;
import io.shulie.takin.parent.exception.entity.ExceptionReadable;

/**
 * @Description takin-agent-server 通用异常
 * @Author ocean_wll
 * @Date 2022/3/3 5:53 下午
 */
public class TakinAgentServerException extends BaseException {
    public TakinAgentServerException(ExceptionReadable ex, Object source) {
        super(ex, source);
    }

    public TakinAgentServerException(ExceptionReadable ex, Throwable e) {
        super(ex, e);
    }

    public TakinAgentServerException(ExceptionReadable ex, Object source, Throwable e) {
        super(ex, source, e);
    }
}
