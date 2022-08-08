package com.zyj.disk.sys.tool.encryption.token;

import com.zyj.disk.sys.tool.encryption.des.DES;
import com.zyj.disk.sys.tool.structure.HashPair;
import com.zyj.disk.sys.tool.structure.Pair;

/**
 * 令牌机制
 */
public final class Token<K, V> {
    private static final DES des = new DES();
    private final Pair<K, V> pair = new HashPair<>();

    public void put(K key, V val) {
        pair.put(key, val);
    }

    public String generate() {
        return des.encrypt(pair.toJSONString());
    }

    public static String parse(String token) {
        return des.decrypt(token);
    }
}