package com.zyj.disk.sys.exception.develop;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Develop {
    PARAM_REQUIRED(new DevelopException("请求参数必须有: ")),
    PARAM_REGEX_VERIFY_FAIL(new DevelopException("请求参数正则验证失败: ")),
    PARAM_LENGTH_ERROR(new DevelopException("请求参数长度异常: "));

    public final DevelopException e;
}