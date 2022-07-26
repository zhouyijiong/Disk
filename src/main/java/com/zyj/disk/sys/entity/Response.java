package com.zyj.disk.sys.entity;

import com.zyj.disk.sys.exception.GlobalException;
import com.zyj.disk.sys.tool.structure.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;

/** 请求返回模型 */
@Getter
@AllArgsConstructor
public final class Response<T>{
    private String message;
    private T data;

    private Response(){}

    public static Response<String> success(Pair<?,?> pair){
        Response<String> response = new Response<>();
        return pair == null ? response : response.data(pair.toJSONString());
    }

    public static<T> Response<T> error(GlobalException e){
        return new Response<T>().message(e.getMessage());
    }

    public Response<T> data(T data){
        this.data = data;
        return this;
    }

    public Response<T> message(String message){
        this.message = message;
        return this;
    }
}