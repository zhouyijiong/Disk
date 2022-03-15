package com.zyj.disk.sys.exception;

import lombok.AllArgsConstructor;

/** code : [5000,5999] */
@AllArgsConstructor
public enum Server{
    REQUEST_PARAM_LOOS(5000,"request param loos"),
    DATABASE_RESULT_ERROR(5001,"database result error")
    ;

    public final int code;
    public final String msg;
}