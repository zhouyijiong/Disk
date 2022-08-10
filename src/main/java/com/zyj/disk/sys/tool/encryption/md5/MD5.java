package com.zyj.disk.sys.tool.encryption.md5;

import com.zyj.disk.sys.entity.Record;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5
 */
public final class MD5 {
    private static MessageDigest md5;
    private static final Record record = new Record(MD5.class);

    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            record.error(e);
        }
    }

    private static String view(byte[] bytes) {
        StringBuilder hexValue = new StringBuilder();
        for (byte b : bytes) {
            int val = ((int) b) & 0xff;
            if (val < 16) hexValue.append(0);
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static String encrypt(String str) {
        return view(md5.digest(str.getBytes(StandardCharsets.UTF_8)));
    }
}