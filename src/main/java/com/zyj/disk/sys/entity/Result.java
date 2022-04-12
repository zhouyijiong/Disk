package com.zyj.disk.sys.entity;

import com.zyj.disk.sys.exception.GlobalException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZYJ
 * @Date: 2022/04/12
 * @Remark: 修改成轻量级返回,每次创建 Map 代价太大
 */
public final class Result{
    private final Map<String,Object> result;

    private Result(){
        result = new HashMap<>();
    }

    public static Result init(){
        return new Result();
    }

    public Result put(String key,Object val){
        result.put(key,val);
        return this;
    }

    public Map<String,Object> result(){
        return result;
    }

    public Map<String,Object> result(GlobalException e){
        result.put("code",e.getCode());
        result.put("msg",e.getMessage());
        return result;
    }
}