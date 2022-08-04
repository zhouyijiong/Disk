package com.zyj.disk.sys.exception.client;

/**
 * 客户端异常
 */
public interface ClientError {
    ClientException USER_EXIST = new ClientException("用户已存在");
    ClientException IDENTITY_VERIFY_FAIL = new ClientException("身份验证失败");
    ClientException ACCOUNT_VERIFY_FAIL = new ClientException("账号或密码不正确");
}