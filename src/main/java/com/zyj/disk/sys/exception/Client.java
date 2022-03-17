package com.zyj.disk.sys.exception;

/** client caused : [4000,4999] */
public enum Client{
    VERIFY_ERROR(4001,"verify error"),
    USER_EXIST(4002,"user exist"),
    USER_NOT_EXIST(4003,"user not exist"),
    REQ_PARAM_REGEX_ERROR(4004,"request param regex error "),
    REQ_PARAM_LEN_ERROR(4005,"request param length error "),
    ;

    public final int code;
    public final String msg;

    Client(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
}