package com.zyj.disk.sys.tool.encryption.codec;

import com.zyj.disk.sys.entity.Record;
import com.zyj.disk.sys.tool.JsonTool;
import com.zyj.disk.sys.tool.encryption.PrivateKey;
import org.springframework.stereotype.Component;

/**
 * 快速混淆编解码
 */
@Component
public final class Codec {
    private static final Record RECORD = new Record(Codec.class);

    public static String complex(String str, int offset) {
        char[] chars = str.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len; ++i) {
            chars[i] = (char) (((-chars[i] & 0xffff) + ((offset + i) % 0x20)) & 0xffff);
        }
        return new String(chars, 0, len);
    }

    public static byte[] simple(byte[] bytes, int offset) {
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = (byte) ((-bytes[i] & 0xff) + ((offset + i) % 0x10) & 0xff);
        }
        return bytes;
    }

    public static String codingObj(Object o) {
        return complex(JsonTool.toJson(o), PrivateKey.OFFSET);
    }

    public static <T> T decodingObj(String json, Class<T> tClass) {
        String str = Codec.complex(json, PrivateKey.OFFSET);
        try {
            return JsonTool.fromJson(str, tClass);
        }catch (RuntimeException e){
            RECORD.error(e);
            return null;
        }
    }
}