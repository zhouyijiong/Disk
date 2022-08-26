package com.zyj.disk.sys.tool.encryption.xor;

import com.zyj.disk.sys.entity.Record;
import com.zyj.disk.sys.tool.encryption.md5.MD5;

/**
 * 异或加解密(对称加密)
 */
public final class XOR {
    private static final Record record = new Record(XOR.class);

    public static String encrypt(String str) {
        String head = getRandomFactor();
        return getHead(head) + "-" + getBody(str, hash(head)) + "-" + getCode(hash(str));
    }

    public static String decrypt(String str) {
        try {
            return decryptCore(str.split("-"));
        }catch (RuntimeException e){
            record.error(e);
            return null;
        }
    }

    public static String encrypt(String str, String privateKey) {
        String head = privateKeyGarble(privateKey);
        return getBody(str, hash(head)) + "-" + getCode(hash(str));
    }

    public static String decrypt(String str, String privateKey) {
        return decryptCore((privateKeyGarble(privateKey) + "-" + str).split("-"));
    }

    /**
     * 获取密码头
     *
     * @param str 待加密信息
     * @return 加密头
     */
    static String getHead(String str) {
        return encrypt(str, str.length());
    }

    /**
     * 获取密码体
     *
     * @param offset 偏移量
     * @return 密码体
     */
    static String getBody(String body, int offset) {
        return encrypt(body, offset);
    }

    /**
     * 获取校验码
     *
     * @param offset 偏移量
     * @return 校验码
     */
    static String getCode(int offset) {
        return encrypt(String.valueOf(offset), offset);
    }

    /**
     * 获取随机因子
     *
     * @return 随即因子
     */
    static String getRandomFactor() {
        return String.valueOf(System.nanoTime() * 0x39c204abfde6aL << 31 ^ (System.nanoTime() >>> 31));
    }


    /**
     * calc hash
     *
     * @param str 信息
     * @return hash
     */
    static int hash(String str) {
        if (str == null) return 0;
        int hash = str.hashCode();
        return hash ^ (hash >>> 16);
    }

    /**
     * 加密核心
     *
     * @param info   原文
     * @param offset 偏移量
     * @return String 密文
     */
    static String encrypt(String info, int offset) {
        int len = info.length();
        StringBuilder sb = new StringBuilder(len << 1);
        for (int i = 0; i < len; i++) {
            int a = ~info.charAt(i) & 0xff;
            int b = (offset + i) & 0x20;
            sb.append(Integer.toHexString((a + b) & 0xff));
        }
        return sb.toString();
    }

    static String decryptCore(String[] ciphers) {
        String str = ciphers[0];
        int hash = hash(decrypt(str, str.length() >> 1));
        str = decrypt(ciphers[1], hash);
        hash = hash(str);
        return encrypt(String.valueOf(hash), hash).equals(ciphers[2]) ? str : null;
    }

    /**
     * 解密核心
     *
     * @param info   密文
     * @param offset 偏移量
     * @return String 原文
     */
    static String decrypt(String info, int offset) {
        int len = info.length();
        int offsetLen = len >> 1;
        char[] chars = new char[offsetLen];
        for (int i = 0, k = 0; i < len; ++k) {
            int a = Integer.parseInt(info.substring(i, i += 2), 0x10);
            int b = (offset + k) & 0x20;
            chars[k] = (char) (0xff - a + b);//(~a & 0xff) + b
        }
        return new String(chars, 0, offsetLen);
    }

    static String privateKeyGarble(String privateKey) {
        return MD5.encrypt(privateKey);
    }
}