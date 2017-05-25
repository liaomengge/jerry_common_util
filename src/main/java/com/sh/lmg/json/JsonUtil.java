package com.sh.lmg.json;

import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * json工具类接口
 */
public interface JsonUtil {

    String toJson(Object object);

    String toJson4DateFormat(Object object);

    String toJson4DateFormat(Object object, Map<String, Object> valueFormat);

    String toJsonIgnoreProperty(Object object, String... ignorePropertyNames);

    String toJsonIncludeProperty(Object object, String... includePropertyNames);

    String toJsonReplaceProperty(Object object, Map<String, Object> replacePropertyMap);

    Object fromJson(String jsonString);

    <T> T fromJson(String jsonString, Class<T> c);

    <T> T fromJson(String jsonString, TypeReference<T> typeReference);

    String format(String jsonString);

}
