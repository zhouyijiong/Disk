package com.zyj.disk.sys.exception;

import lombok.Getter;

/**
 * @Author: ZYJ
 * @Date: 2022/04/09
 * @Remark: 全局异常
 */
@Getter
public final class GlobalException extends RuntimeException{
    private final int code;
    private static final long serialVersionUID = 631924228114738472L;

    public GlobalException(Throwable throwable){
        super(throwable);
        this.code = throwable instanceof Exception ? 5000 : -1;
    }

    public GlobalException(Client client){
        super(client.msg);
        this.code = client.code;
    }

    public GlobalException(Server server){
        super(server.msg);
        this.code = server.code;
    }

    public GlobalException(User use,String...infos){
        super(use.msg + String.join(" ",infos));
        this.code = use.code;
    }
}