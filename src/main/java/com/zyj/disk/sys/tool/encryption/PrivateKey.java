package com.zyj.disk.sys.tool.encryption;

import java.util.UUID;

/**
 * 私钥
 */
public final class PrivateKey {
    public static final String PK = UUID.randomUUID().toString();
    public static final int OFFSET = (int) (System.currentTimeMillis() / 1000);
}