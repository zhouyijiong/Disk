package com.zyj.disk.sys.exception;

import lombok.AllArgsConstructor;

/**
 * @Author: ZYJ
 * @Date: 2022/04/09
 * @Remark: 客户端产生的异常 [4000,4999]
 */
@AllArgsConstructor
public enum Client{
    VERIFY_ERROR(new GlobalException("验证失败")),
    USER_EXIST(new GlobalException("用户已存在")),
    ACCOUNT_OR_PASSWORD_ERROR(new GlobalException("账号或密码不正确"));

    public final GlobalException exception;
}