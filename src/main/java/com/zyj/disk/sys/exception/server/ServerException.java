package com.zyj.disk.sys.exception.server;

import com.zyj.disk.sys.exception.GlobalException;

public final class ServerException extends GlobalException {
    private static final long serialVersionUID = 9160286743273225619L;

    /**
     * 服务器异常
     */
    public ServerException(int code, String msg) {
        super(code, msg);
    }

    public ServerException(Throwable throwable) {
        super((throwable instanceof GlobalException) ? ((GlobalException) throwable).getCode() : 1000, throwable.getLocalizedMessage());
    }
}