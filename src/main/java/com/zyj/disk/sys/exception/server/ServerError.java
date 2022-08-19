package com.zyj.disk.sys.exception.server;

/**
 * 服务端异常
 */
public interface ServerError {
    ServerException REQUEST_PARAM_LOOS = new ServerException("请求参数丢失");
    ServerException SQL_RESULT_FAIL = new ServerException("SQL返回异常");
    ServerException SQL_BUILD_FAIL = new ServerException("SQL构造异常");
    ServerException DES_ENCRYPT_FAIL = new ServerException("DES 加密失败");
    ServerException TOKEN_LOST = new ServerException("身份信息丢失");
}