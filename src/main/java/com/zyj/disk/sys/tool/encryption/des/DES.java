package com.zyj.disk.sys.tool.encryption.des;

import com.zyj.disk.sys.exception.GlobalException;
import com.zyj.disk.sys.tool.encryption.PrivateKey;
import com.zyj.disk.sys.tool.encryption.token.RsaSet;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @Author: ZYJ
 * @Date: 2022/7/25 17:19
 * @Remark:
 */
public class DES {
    static final Cipher ENCRYPT_MODE;
    static final Cipher DECRYPT_MODE;

    static {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            SecureRandom secureRandom = new SecureRandom(
                    RsaSet.PUBLIC.RSA.SK.encrypt(PrivateKey.PK).getBytes(StandardCharsets.UTF_8));
            generator.init(secureRandom);
            Key key = generator.generateKey();
            ENCRYPT_MODE = Cipher.getInstance("DES");
            DECRYPT_MODE = Cipher.getInstance("DES");
            ENCRYPT_MODE.init(Cipher.ENCRYPT_MODE, key);
            DECRYPT_MODE.init(Cipher.DECRYPT_MODE, key);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            throw new GlobalException(e);
        }
    }

    /**
     * 加密
     *
     * @param source 原文
     * @return 密文
     */
    public static String encrypt(String source) {
        try {
            return new BigInteger(encrypt(source.getBytes(StandardCharsets.UTF_8))).toString(32);
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }

    /**
     * 解密
     *
     * @param cipher 密文
     * @return 原文
     */
    public static String decrypt(String cipher) {
        try {
            return new String(decrypt(new BigInteger(cipher, 32).toByteArray()));
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }

    /**
     * 加密核心
     *
     * @param sourceByteArray 原文字节数组
     * @return 密文字节数组
     */
    private static byte[] encrypt(byte[] sourceByteArray) throws Exception {
        return ENCRYPT_MODE.doFinal(sourceByteArray);
    }

    /**
     * 解密核心
     *
     * @param cipherByteArray 密文字节数组
     * @return 原文字节数组
     */
    private static byte[] decrypt(byte[] cipherByteArray) throws Exception {
        return DECRYPT_MODE.doFinal(cipherByteArray);
    }
}