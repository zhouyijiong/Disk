package com.zyj.disk.sys.entity;

import com.zyj.disk.sys.exception.GlobalException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 请求返回模型
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class Response<T> {
    private T data;
    private int code;
    private String message;

    public static <T> Response<T> success(T data) {
        return new Response<T>().data(data);
    }

    public static <T> Response<T> error(GlobalException e) {
        return new Response<T>().message(e);
    }

    public Response<T> data(T data) {
        this.data = data;
        return this;
    }

    public Response<T> message(GlobalException e) {
        this.code = e.getCode();
        this.message = e.getMessage();
        return this;
    }
}