package com.zyj.disk.tool;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Result{
    private final Map<String,Object> result;

    private Result(){
        result = new LinkedHashMap<>();
    }

    public static Result init(){
        return new Result();
    }

    public Map<String,Object> put(String key,Object val){
        result.put(key,val);
        return result;
    }
}