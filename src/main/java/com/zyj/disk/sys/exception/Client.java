package com.zyj.disk.sys.exception;

import lombok.AllArgsConstructor;

/**
 * @Author: ZYJ
 * @Date: 2022/04/09
 * @Remark: 客户端产生的异常 [4000,4999]
 */
@AllArgsConstructor
public enum Client{
    VERIFY_ERROR(4001,"验证失败"),
    USER_EXIST(4002,"用户已存在"),
    USER_NOT_EXIST(4003,"用户不存在")
    ;

    public final int code;
    public final String msg;
}