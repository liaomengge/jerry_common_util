package com.sh.lmg.json.gson;

import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.sh.lmg.json.AbstractJsonUtil;

import java.util.Map;

/**
 * json工具类的gson实现
 */
public class GsonUtil extends AbstractJsonUtil {

    private static final Gson gson = GsonBuilderUtil.create();

    @Override
    public String toJson(Object object) {
        return gson.toJson(object);
    }

    @Override
    public String toJson4DateFormat(Object object) {
        return null;
    }

    @Override
    public String toJson4DateFormat(Object object, Map<String, Object> valueFormat) {
        return null;
    }

    @Override
    public String toJsonIgnoreProperty(Object object, String... ignorePropertyNames) {
        return null;
    }

    @Override
    public String toJsonIncludeProperty(Object object, String... includePropertyNames) {
        return null;
    }

    @Override
    public String toJsonReplaceProperty(Object object, Map<String, Object> replacePropertyMap) {
        return null;
    }

    @Override
    public Object fromJson(String jsonString) {
        return null;
    }

    @Override
    public <T> T fromJson(String jsonString, Class<T> c) {
        return gson.fromJson(jsonString, c);
    }

    @Override
    public <T> T fromJson(String jsonString, TypeReference<T> typeReference) {
        return null;
    }

}
