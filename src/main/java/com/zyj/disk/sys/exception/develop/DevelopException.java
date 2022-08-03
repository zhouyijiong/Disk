package com.zyj.disk.sys.exception.develop;

import java.util.Arrays;

public final class DevelopException extends RuntimeException {
    private static final long serialVersionUID = -5916897751183230597L;

    /**
     * 开发者异常
     */
    public DevelopException(String msg) {
        super(msg);
    }

    public DevelopException addArgs(Object... args) {
        return new DevelopException(this.getMessage() + Arrays.toString(args));
    }
}