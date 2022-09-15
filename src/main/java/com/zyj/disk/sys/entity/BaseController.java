package com.zyj.disk.sys.entity;

import com.zyj.disk.entity.user.User;
import com.zyj.disk.sys.exception.client.ClientError;
import com.zyj.disk.sys.exception.server.ServerError;
import com.zyj.disk.sys.tool.encryption.codec.Codec;
import com.zyj.disk.sys.tool.encryption.token.Tokens;

public class BaseController {
    protected static final Response<Void> SUCCESS = new Response<>();

    protected User init() {
        String token = Tokens.token.get();
        if(token == null) throw ServerError.TOKEN_LOST;
        User user = Codec.decodingObj(token, User.class);
        if(user == null) throw ClientError.INFO_TAMPER;
        return user;
    }
}