package com.zyj.disk.sys.tool.encryption.token;

import com.zyj.disk.sys.exception.server.Server;
import com.zyj.disk.sys.tool.ClassTool;
import com.zyj.disk.sys.tool.encryption.base64.AbstractBase64;
import com.zyj.disk.sys.tool.encryption.base64.ByteArrayBase64;
import com.zyj.disk.sys.tool.encryption.des.DES;
import com.zyj.disk.sys.tool.structure.HashPair;
import com.zyj.disk.sys.tool.structure.Pair;

import java.nio.charset.StandardCharsets;

/**
 * 令牌机制
 */
public final class Token<K, V> {
    private final Pair<K, V> pair;

    public Token() {
        pair = new HashPair<>();
    }

    public void put(K key, V val) {
        pair.put(key, val);
    }

    public String generate() {
        return DES.encrypt(pair.toJSONString());
    }

    public static String parse(String token) {
        return DES.decrypt(token);
    }

    public String serializeParam(Object o){
        byte[] bytes = ClassTool.serialize(o);
        if(bytes == null) throw Server.SERIALIZE_FAIL.e;
        return ByteArrayBase64.encodeToString(bytes);
    }

    public static Object deSerializeParam(String str){
        Object obj = ClassTool.deserialize(AbstractBase64.decode(str.getBytes(StandardCharsets.UTF_8)));
        if(obj == null) throw Server.DESERIALIZE_FAIL.e;
        return obj;
    }
}