package com.zyj.disk.sys.result;

import com.zyj.disk.sys.exception.GlobalException;
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
    private int code;
    private String message;
    private T data;

    private Response(int code,String message){
        this.code = code;
        this.message = message;
    }

    private Response(T data){
        this.data = data;
    }

    public static<T> Response<T> success(T data){
        return new Response<>(data);
    }

    public static<T> Response<T> error(GlobalException e){
        return new Response<>(e.getCode(),e.getMessage());
    }
}