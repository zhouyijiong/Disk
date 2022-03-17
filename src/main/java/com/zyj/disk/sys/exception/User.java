package com.zyj.disk.sys.exception;

import lombok.AllArgsConstructor;

/** user caused : [6000,6999] */
@AllArgsConstructor
public enum User{
    REQ_PARAM_REQUIRED(6001,"request param required "),
    SQL_PARAM_REDUNDANT(6002,"sql param redundant"),
    SQL_PARAM_NOT_EXIST(6003,"sql param not exist")
    ;

    public final int code;
    public final String msg;
}