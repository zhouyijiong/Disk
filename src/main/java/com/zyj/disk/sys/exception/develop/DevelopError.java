package com.zyj.disk.sys.exception.develop;

/**
 * 开发者异常
 */
public interface DevelopError {
    DevelopException PARAM_REQUIRED = new DevelopException(6000, "请求参数必须有: ");
    DevelopException PARAM_REGEX_VERIFY_FAIL = new DevelopException(6001, "请求参数正则验证失败: ");
    DevelopException PARAM_LENGTH_ERROR = new DevelopException(6002, "请求参数长度异常: ");
}