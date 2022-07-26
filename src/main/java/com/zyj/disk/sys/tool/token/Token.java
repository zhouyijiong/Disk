package com.zyj.disk.sys.tool.token;

import com.zyj.disk.tool.PairXOR;
import com.zyj.disk.sys.tool.rsa.RSA;

/** 令牌机制 */
public final class Token<K,V>{
    private final PairXOR<K,V> pair;
    private final RSA rsa;
    private final String privateKey;

    public Token(String privateKey,RSA rsa){
        this.privateKey = privateKey;
        pair = new PairXOR<>();
        this.rsa = rsa;
    }

    public void put(K key,V val){
        pair.put(key,val);
    }

    public String generate(){
        String encryptPrivateKey = rsa.SK.encrypt(privateKey);
        return pair.encrypt(encryptPrivateKey);
    }

    public String parse(String token){
        String encryptPrivateKey = rsa.SK.encrypt(privateKey);
        return pair.decrypt(token,encryptPrivateKey);
    }
}