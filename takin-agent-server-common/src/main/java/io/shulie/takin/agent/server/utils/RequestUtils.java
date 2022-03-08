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
package io.shulie.takin.agent.server.utils;

/**
 * @author by: hezhongqi
 * @Package io.shulie.takin.web.common.util
 * @ClassName: RequestUtils
 * @Description: TODO
 * @Date: 2022/1/14 10:23
 */
public class RequestUtils {
    /**
     * special char handle
     * '：用于包裹搜索条件，需转为\'
     * %：用于代替任意数目的任意字符，需转换为\%
     * _：用于代替一个任意字符，需转换为\_
     * \：转义符号，需转换为\\在java中\也是特殊字符因此需要两次转义，而replace正则需要再次转义
     *
     * \要最先处理，防止把转义\再次转一遍
     * eg:abc _'%*sdf\\
     * result:abc\_\'\%\*sdf\\\\
     *
     * author :bamboo
     * url:https://editor.csdn.net/md?not_checkout=1&articleId=111865685
     */
    public static String escapeSqlSpecialChar(String str ){
        return  str.trim().replaceAll("\\s", "").replace("\\", "\\\\\\\\")
            .replace("_", "\\_").replace("\'", "\\'")
            .replace("%", "\\%").replace("*", "\\*");
    }
}
