package com.sh.lmg.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;


public class GsonBuilderUtil {

    public static Gson create(Class... clazzs) {
        if (clazzs.length == 0) {
            return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        }
        Class clazz = clazzs[0];
        Gson gson = null;
        if (clazz.getClass() == Long.class.getClass()) {
            GsonBuilder gb = new GsonBuilder();
            gb.registerTypeAdapter(java.util.Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
            gb.registerTypeAdapter(java.util.Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
            gson = gb.create();
        }
        return gson;
    }

}
