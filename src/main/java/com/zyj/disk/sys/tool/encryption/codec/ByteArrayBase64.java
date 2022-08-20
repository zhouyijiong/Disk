package com.zyj.disk.sys.tool.encryption.codec;

import java.nio.charset.StandardCharsets;

/**
 * byte array base64
 */
public final class ByteArrayBase64 extends AbstractBase64 {
    public static String encodeToString(byte[] bytes) {
        return new String(encode(bytes), StandardCharsets.UTF_8);
    }

    public static String decodeToString(byte[] bytes) {
        return new String(decode(bytes), StandardCharsets.UTF_8);
    }
}