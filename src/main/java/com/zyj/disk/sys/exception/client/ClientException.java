package com.zyj.disk.sys.exception.client;

public final class ClientException extends RuntimeException {
    private static final long serialVersionUID = -5170157112649306860L;

    /**
     * 客户端异常
     */
    public ClientException(String msg) {
        super(msg);
    }
}