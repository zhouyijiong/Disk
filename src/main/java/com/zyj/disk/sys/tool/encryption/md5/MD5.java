package com.zyj.disk.sys.tool.encryption.md5;

import com.zyj.disk.sys.entity.Record;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public final class MD5 {
    private static final Record record = new Record(MD5.class);

    /**
     * MD5
     */
    private String view(byte[] bytes) {
        StringBuilder hexValue = new StringBuilder();
        for (byte b : bytes) {
            int val = ((int) b) & 0xff;
            if (val < 16) hexValue.append(0);
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public String encrypt(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return view(md5.digest(str.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            record.error(e);
            return null;
        }
    }
}