package com.zyj.disk.sys.exception;

import lombok.AllArgsConstructor;

/**
 * 客户端异常
 */
@AllArgsConstructor
public enum Client {
    VERIFY_ERROR(new GlobalException("验证失败")),
    USER_EXIST(new GlobalException("用户已存在")),
    ACCOUNT_OR_PASSWORD_ERROR(new GlobalException("账号或密码不正确"));

    public final GlobalException exception;
}