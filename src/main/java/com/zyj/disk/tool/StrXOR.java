package com.zyj.disk.tool;

/**
 * @Author: ZYJ
 * @Date: 2022/4/19 10:07
 * @Remark: 异或加解密 String
 */
public final class StrXOR extends XOR{
    public StrXOR(boolean isChaos,String privateKey){
        super(isChaos,privateKey);
    }

    public String encryption(String info){
        int hash = hash(info.hashCode());
        return def_encryption(getHeadMsg(hash),info,hash);
    }

    public String decrypt(String cipher){
        return def_decrypt(cipher);
    }
}