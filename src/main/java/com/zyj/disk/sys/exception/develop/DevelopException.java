package com.zyj.disk.sys.exception.develop;

import com.zyj.disk.sys.exception.GlobalException;

import java.util.Arrays;

public final class DevelopException extends GlobalException {
    private static final long serialVersionUID = -5916897751183230597L;

    /**
     * 开发者异常
     */
    public DevelopException(int code, String msg) {
        super(code, msg);
    }

    public DevelopException addArgs(Object... args) {
        return new DevelopException(this.code, this.getMessage() + Arrays.toString(args));
    }
}