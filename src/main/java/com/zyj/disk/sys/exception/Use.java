package com.zyj.disk.sys.exception;

import lombok.AllArgsConstructor;

/** code : [6000,6999] */
@AllArgsConstructor
public enum Use{
    REQ_PARAM_REQUIRED(6001,"request param required "),
    SQL_PARAM_REDUNDANT(6002,"sql param redundant")
    ;

    public final int code;
    public final String msg;
}