package com.zyj.disk.sys.exception.server;

public final class ServerException extends RuntimeException {
    private static final long serialVersionUID = 9160286743273225619L;

    /**
     * 服务器异常
     */
    public ServerException(String msg) {
        super(msg);
    }

    public ServerException(Throwable throwable) {
        super(throwable);
    }
}