package com.zyj.disk.sys.tool;

import com.zyj.disk.tool.PairXOR;
import com.zyj.disk.tool.RSA;

/** 令牌机制 */
public final class Token<K,V>{
    private final PairXOR<K,V> pair;
    public static final RSA RSA = new RSA(2048,65537);
    String privateKey = "password";

    public Token(){
        pair = new PairXOR<>();
    }

    public void put(K key,V val){
        pair.put(key,val);
    }

    public String generate(){
        String encryptPrivateKey = RSA.PK.encrypt(privateKey);
        return pair.encrypt(encryptPrivateKey);
    }

    public String parse(String token){
        String encryptPrivateKey = RSA.PK.encrypt(privateKey);
        return pair.decrypt(token,encryptPrivateKey);
    }
}