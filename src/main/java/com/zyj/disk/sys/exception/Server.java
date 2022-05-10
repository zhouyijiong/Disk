package com.zyj.disk.sys.exception;

import lombok.AllArgsConstructor;

/**
 * @Author: ZYJ
 * @Date: 2022/04/09
 * @Remark: 服务端产生的异常 [5000,5999]
 */
@AllArgsConstructor
public enum Server{
    REQUEST_PARAM_LOOS(new GlobalException("请求参数丢失")),
    SQL_RESULT_ERROR(new GlobalException("SQL返回异常"));

    public final GlobalException exception;
}