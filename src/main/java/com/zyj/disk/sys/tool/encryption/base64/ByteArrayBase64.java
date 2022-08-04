package com.zyj.disk.sys.tool.encryption.base64;

/**
 * byte array base64
 */
public final class ByteArrayBase64 extends AbstractBase64 {
    public static String encodeToString(byte[] bytes) {
        return new String(encode(bytes));
    }
}