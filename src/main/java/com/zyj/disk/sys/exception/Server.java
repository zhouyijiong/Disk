package com.zyj.disk.sys.exception;

import lombok.AllArgsConstructor;

/** service caused : [5000,5999] */
@AllArgsConstructor
public enum Server{
    REQUEST_PARAM_LOOS(5000,"请求参数丢失"),
    SQL_RESULT_ERROR(5001,"SQL返回异常")
    ;

    public final int code;
    public final String msg;
}