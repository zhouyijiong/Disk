package com.zyj.disk.sys.tool;

import com.google.gson.Gson;

public final class GsonTool {
    public static final Gson gson = new Gson();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        return gson.fromJson(json, tClass);
    }
}