package com.zyj.disk.sys.tool.encryption.token;

import com.zyj.disk.sys.tool.encryption.des.DES;

/**
 * 令牌机制
 */
public final class Token {
    public static String generate(String data) {
        return DES.encrypt(data);
    }

    public static String parse(String token) {
        return DES.decrypt(token);
    }
}