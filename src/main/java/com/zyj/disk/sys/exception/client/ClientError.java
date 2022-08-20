package com.zyj.disk.sys.exception.client;

/**
 * 客户端异常
 */
public interface ClientError {
    ClientException USER_EXIST = new ClientException(4000, "用户已存在");
    ClientException INFO_TAMPER = new ClientException(14001, "信息篡改");
    ClientException TOKEN_EXPIRED = new ClientException(14002, "身份信息过期");
    ClientException TOKEN_NOT_EXISTS = new ClientException(14003, "身份信息不存在");
    ClientException IDENTITY_VERIFY_FAIL = new ClientException(14004, "身份验证失败");
    ClientException ACCOUNT_VERIFY_FAIL = new ClientException(4005, "账号或密码不正确");
}