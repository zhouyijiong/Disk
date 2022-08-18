package com.zyj.disk.sys.tool.encryption.codec;

import java.nio.charset.StandardCharsets;

/**
 * string base64
 */
public class StringBase64 extends AbstractBase64 {
    public static String encodeToString(String str) {
        return new String(encode(str.getBytes(StandardCharsets.UTF_8)));
    }
}