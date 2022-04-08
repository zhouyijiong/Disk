package com.zyj.disk.sys.exception;

/** client caused : [4000,4999] */
public enum Client{
    VERIFY_ERROR(4001,"验证失败"),
    USER_EXIST(4002,"用户存在"),
    USER_NOT_EXIST(4003,"用户不存在"),
    ;

    public final int code;
    public final String msg;

    Client(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
}