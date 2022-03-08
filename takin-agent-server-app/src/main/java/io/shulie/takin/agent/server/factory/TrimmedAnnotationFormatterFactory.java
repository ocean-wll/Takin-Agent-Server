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
package io.shulie.takin.agent.server.factory;

import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import io.shulie.takin.agent.server.annocation.Trimmed;
import io.shulie.takin.agent.server.annocation.Trimmed.TrimmerType;
import io.shulie.takin.agent.server.utils.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.stereotype.Component;

/**
 * @author 无涯
 * @date 2021/5/18 11:19 上午
 * description: 清除字符串前后的空格自定义注解格式工厂
 */
@Component
public final class TrimmedAnnotationFormatterFactory implements AnnotationFormatterFactory<Trimmed> {

    private static final Map<TrimmerType, TrimmerFormatter> TRIMMER_FORMATTER_MAP;

    static {
        TrimmerType[] values = Trimmed.TrimmerType.values();
        Map<TrimmerType, TrimmerFormatter> map = new HashMap<>(values.length);
        for (TrimmerType type : values) {
            map.put(type, new TrimmerFormatter(type));
        }
        TRIMMER_FORMATTER_MAP = Collections.unmodifiableMap(map);
    }

    @Override
    public Set<Class<?>> getFieldTypes() {
        Set<Class<?>> fieldTypes = new HashSet<Class<?>>(1, 1);
        fieldTypes.add(String.class);
        return fieldTypes;
    }

    @Override
    public Parser<?> getParser(Trimmed annotation, Class<?> fieldType) {
        return TRIMMER_FORMATTER_MAP.get(annotation.value());
    }

    @Override
    public Printer<?> getPrinter(Trimmed annotation, Class<?> fieldType) {
        return TRIMMER_FORMATTER_MAP.get(annotation.value());
    }

    private static class TrimmerFormatter implements Formatter<String> {

        private static final Pattern PATTERN_WHITESPACES = Pattern.compile("\\s+");
        private static final Pattern PATTERN_WHITESPACES_WITH_LINE_BREAK = Pattern.compile("\\s*\\n\\s*");
        private static final Pattern PATTERN_WHITESPACES_EXCEPT_LINE_BREAK = Pattern.compile("[\\s&&[^\\n]]+");

        private final TrimmerType type;

        public TrimmerFormatter(TrimmerType type) {
            if (type == null) {
                throw new NullPointerException();
            }
            this.type = type;
        }

        @Override
        public String print(String object, Locale locale) {
            return object;
        }

        @Override
        public String parse(String text, Locale locale) throws ParseException {
            if (StringUtils.isBlank(text)) {
                return "";
            }
            text = text.trim();
            // 非法字符转义
            text = RequestUtils.escapeSqlSpecialChar(text);
            switch (type) {
                case ALL_WHITESPACES:
                    return PATTERN_WHITESPACES.matcher(text).replaceAll(" ");
                case EXCEPT_LINE_BREAK:
                    return PATTERN_WHITESPACES_EXCEPT_LINE_BREAK
                        .matcher(PATTERN_WHITESPACES_WITH_LINE_BREAK.matcher(text).replaceAll("\n")).replaceAll(" ");
                case SIMPLE:
                    return text;
                default:
                    // not possible
                    throw new AssertionError();
            }
        }

    }
}

