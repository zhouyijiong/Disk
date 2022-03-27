package com.zyj.disk.sys.exception;

import lombok.AllArgsConstructor;

/** user caused : [6000,6999] */
@AllArgsConstructor
public enum User{
    MAPPER_CONFIG_ERROR(6000,"映射配置异常"),
    REQ_PARAM_REQUIRED(6001,"请求参数必须有: "),
    SQL_PARAM_REDUNDANT(6002,"SQL参数多余"),
    SQL_PARAM_NOT_EXIST(6003,"SQL参数不存在")
    ;

    public final int code;
    public final String msg;
}