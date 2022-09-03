package com.zyj.disk.sys.tool;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.zyj.disk.sys.entity.Record;
import java.util.Map;

public final class GsonTool {
    public static final Gson GSON = new Gson();
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static final Record RECORD = new Record(GsonTool.class);

    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        return GSON.fromJson(json, tClass);
    }

    @SuppressWarnings("unchecked")
    public static<K,V> Map<K,V> toMap(String json) {
        try {
            return OBJECT_MAPPER.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            RECORD.error(e);
            return null;
        }
    }
}