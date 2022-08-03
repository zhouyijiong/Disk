package com.zyj.disk.sys.exception.client;

import lombok.AllArgsConstructor;

/**
 * 客户端异常
 */
@AllArgsConstructor
public enum Client {
    USER_EXIST(new ClientException("用户已存在")),
    IDENTITY_VERIFY_FAIL(new ClientException("身份验证失败")),
    ACCOUNT_VERIFY_FAIL(new ClientException("账号或密码不正确"));

    public final ClientException e;
}