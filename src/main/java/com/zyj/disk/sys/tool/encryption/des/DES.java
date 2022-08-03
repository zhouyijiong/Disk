package com.zyj.disk.sys.tool.encryption.des;

import com.zyj.disk.sys.entity.Record;
import com.zyj.disk.sys.exception.server.Server;
import com.zyj.disk.sys.exception.server.ServerException;
import com.zyj.disk.sys.tool.encryption.PrivateKey;
import com.zyj.disk.sys.tool.encryption.token.RsaSet;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * DES加解密
 */
public final class DES {
    static final Cipher ENCRYPT_MODE;
    static final Cipher DECRYPT_MODE;
    private static final Record record = new Record(DES.class);

    static {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            SecureRandom secureRandom =
                    new SecureRandom(RsaSet.PUBLIC.RSA.SK.encrypt(PrivateKey.PK).getBytes(StandardCharsets.UTF_8));
            generator.init(secureRandom);
            Key key = generator.generateKey();
            ENCRYPT_MODE = Cipher.getInstance("DES");
            DECRYPT_MODE = Cipher.getInstance("DES");
            ENCRYPT_MODE.init(Cipher.ENCRYPT_MODE, key);
            DECRYPT_MODE.init(Cipher.DECRYPT_MODE, key);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            record.error(e);
            throw new ServerException(e.toString());
        }
    }

    /**
     * 加密
     *
     * @param source 原文
     * @return 密文
     */
    public static String encrypt(String source) {
        byte[] bytes = encrypt(source.getBytes(StandardCharsets.UTF_8));
        if(bytes == null) throw Server.DES_ENCRYPT_FAIL.e;
        return new BigInteger(bytes).toString(32);
    }

    /**
     * 解密
     *
     * @param cipher 密文
     * @return 原文
     */
    public static String decrypt(String cipher) {
        byte[] bytes = decrypt(new BigInteger(cipher, 32).toByteArray());
        if (bytes == null) throw Server.DES_DECRYPT_FAIL.e;
        return new String(bytes);
    }

    /**
     * 加密核心
     *
     * @param sourceByteArray 原文字节数组
     * @return 密文字节数组
     */
    private static byte[] encrypt(byte[] sourceByteArray) {
        try {
            return ENCRYPT_MODE.doFinal(sourceByteArray);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            record.error(e);
            return null;
        }
    }

    /**
     * 解密核心
     *
     * @param cipherByteArray 密文字节数组
     * @return 原文字节数组
     */
    private static byte[] decrypt(byte[] cipherByteArray) {
        try {
            return DECRYPT_MODE.doFinal(cipherByteArray);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            record.error(e);
            return null;
        }
    }
}