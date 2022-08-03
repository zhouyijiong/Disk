package com.zyj.disk.sys.exception.server;

import lombok.AllArgsConstructor;

/**
 * 服务端异常
 */
@AllArgsConstructor
public enum Server {
    REQUEST_PARAM_LOOS(new ServerException("请求参数丢失")),
    SQL_RESULT_ERROR(new ServerException("SQL返回异常")),
    SQL_BUILD_FAIL(new ServerException("SQL构造异常")),
    SERIALIZE_FAIL(new ServerException("序列化失败")),
    DESERIALIZE_FAIL(new ServerException("反序列化失败")),
    DES_ENCRYPT_FAIL(new ServerException("DES 加密失败")),
    DES_DECRYPT_FAIL(new ServerException("DES 解密失败")),
    GENERATE_TOKEN_ERROR(new ServerException("token 生成失败"));

    public final ServerException e;
}