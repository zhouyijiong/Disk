package com.zyj.disk.sys.exception;

import lombok.AllArgsConstructor;

/** 服务端异常 */
@AllArgsConstructor
public enum Server{
    REQUEST_PARAM_LOOS(new GlobalException("请求参数丢失")),
    SQL_RESULT_ERROR(new GlobalException("SQL返回异常"));

    public final GlobalException exception;
}