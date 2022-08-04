package com.zyj.disk.sys.tool.encryption.xor;

/**
 * 异或加解密 String
 */
public final class StrXOR extends XOR {
    @Override
    public String decrypt(String info, String privateKey) {
        return defDecrypt(info, privateKey);
    }
}