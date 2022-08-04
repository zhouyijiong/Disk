package com.zyj.disk.sys.tool.encryption.base64;

/**
 * char array base64
 */
public final class CharArrayBase64 extends AbstractBase64 {
    public static String encodeToString(char[] chars) {
        return new String(encode(toBytes(chars)));
    }

    private static byte[] toBytes(char[] chars) {
        byte[] temp = new byte[chars.length];
        for (int i = 0; i < temp.length; ++i) temp[i] = (byte) chars[i];
        return temp;
    }
}