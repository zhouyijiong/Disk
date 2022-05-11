package com.zyj.disk.sys.entity;

import com.zyj.disk.sys.exception.GlobalException;
import com.zyj.disk.sys.tool.structure.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: ZYJ
 * @Date: 2022/4/27 16:20
 * @Remark: 请求返回模型
 */
@Getter
@AllArgsConstructor
public final class Response<T>{
    private String message;
    private T data;

    private Response(String message){
        this.message = message;
    }

    private Response(T data){
        this.data = data;
    }

    public static Response<String> success(Pair<?,?> pair){
        return new Response<>(pair.toJSONString());
    }

    public static<T> Response<T> error(GlobalException e){
        return new Response<>(e.getMessage());
    }
}