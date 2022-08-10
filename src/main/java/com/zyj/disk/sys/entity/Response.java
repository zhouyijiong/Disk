package com.zyj.disk.sys.entity;

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
    private String message;
    private T data;

    public static<T> Response<T> success(T data) {
        return new Response<T>().data(data);
    }

    public static <T> Response<T> error(RuntimeException e) {
        return new Response<T>().message(e.getMessage());
    }

    public Response<T> data(T data) {
        this.data = data;
        return this;
    }

    public Response<T> message(String message) {
        this.message = message;
        return this;
    }
}