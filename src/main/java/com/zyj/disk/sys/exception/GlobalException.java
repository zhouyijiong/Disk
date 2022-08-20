package com.zyj.disk.sys.exception;

import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {
    protected int code;
    private static final long serialVersionUID = 146827119868746218L;

    public GlobalException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}