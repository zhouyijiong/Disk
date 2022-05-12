package com.zyj.disk.sys.exception;

import lombok.Getter;
import java.util.Arrays;

/** 全局异常 */
@Getter
public class GlobalException extends RuntimeException{
    private static final long serialVersionUID = 631924228114738472L;

    public GlobalException(Throwable throwable){
        super(throwable.getLocalizedMessage());
    }

    public GlobalException(String msg){
        super(msg);
    }

    public GlobalException addArgs(Object...args){
        return new GlobalException(this.getMessage() + Arrays.toString(args));
    }
}