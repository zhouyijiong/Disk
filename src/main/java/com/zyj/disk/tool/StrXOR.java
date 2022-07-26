package com.zyj.disk.tool;

/** 异或加解密 String */
public final class StrXOR extends XOR{
    public StrXOR(boolean isChaos,String privateKey){
        super(isChaos,privateKey);
    }

    public String encrypt(String info){
        int hash = hash(info.hashCode());
        return defEncrypt(getHeadMsg(hash),info,hash);
    }

    public String decrypt(String cipher){
        return defDecrypt(cipher);
    }
}