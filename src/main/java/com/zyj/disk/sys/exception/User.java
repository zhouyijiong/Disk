package com.zyj.disk.sys.exception;

import lombok.AllArgsConstructor;

/**
 * @Author: ZYJ
 * @Date: 2022/04/09
 * @Remark: 使用者产生的错误 [6000,6999]
 */
@AllArgsConstructor
public enum User{
    MAPPER_CONFIG_ERROR(6000,"映射配置异常"),
    REQ_PARAM_REQUIRED(6001,"请求参数必须有: "),
    SQL_PARAM_REDUNDANT(6002,"SQL参数多余"),
    SQL_PARAM_NOT_EXIST(6003,"SQL参数不存在"),
    REQ_PARAM_REGEX_ERROR(4004,"请求参数正则验证失败: "),
    REQ_PARAM_LEN_ERROR(4005,"请求参数长度异常: ")
    ;

    public final int code;
    public final String msg;
}