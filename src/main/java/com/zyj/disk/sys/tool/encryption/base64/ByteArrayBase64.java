package com.zyj.disk.sys.tool.encryption.base64;

/**
 * @Author: ZYJ
 * @Date: 2022/6/14 9:14
 * @Remark: byte array base64
 */
public final class ByteArrayBase64 extends AbstractBase64 {
    public static String encodeToString(byte[] bytes) {
        return new String(encode(bytes));
    }
}