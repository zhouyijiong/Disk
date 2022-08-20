package com.zyj.disk.sys.exception.server;

/**
 * 服务端异常
 */
public interface ServerError {
    ServerException TOKEN_LOST = new ServerException(15000, "身份信息丢失");
    ServerException REQUEST_PARAM_LOOS = new ServerException(5001, "请求参数丢失");
    ServerException SQL_RESULT_FAIL = new ServerException(5002, "SQL返回异常");
    ServerException SQL_BUILD_FAIL = new ServerException(5003, "SQL构造异常");
    ServerException DES_ENCRYPT_FAIL = new ServerException(5004, "DES 加密失败");
}