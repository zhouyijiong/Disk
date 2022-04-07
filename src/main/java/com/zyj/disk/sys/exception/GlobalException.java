package com.zyj.disk.sys.exception;

import lombok.Getter;

@Getter
public final class GlobalException extends RuntimeException{
    private final int code;
    private final String msg;
    private static final long serialVersionUID = 631924228114738472L;

    public GlobalException(Throwable throwable){
        //GlobalException temp = (GlobalException)throwable;
        this.code = throwable instanceof Exception ? 5000 : -1;
        this.msg = throwable.toString();
    }

    public GlobalException(Client client){
        this.code = client.code;
        this.msg = client.msg;
    }

    public GlobalException(Server server){
        this.code = server.code;
        this.msg  = server.msg;
    }

    public GlobalException(User use,String...infos){
        this.code = use.code;
        this.msg  = use.msg + String.join("; ",infos);
    }
}