package com.zyj.disk.sys.exception.client;

import com.zyj.disk.sys.exception.GlobalException;

public final class ClientException extends GlobalException {
    private static final long serialVersionUID = -5170157112649306860L;

    /**
     * 客户端异常
     */
    public ClientException(int code, String msg) {
        super(code, msg);
    }
}