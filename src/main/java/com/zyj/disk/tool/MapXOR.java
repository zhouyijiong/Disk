package com.zyj.disk.tool;

import java.util.HashMap;
import java.util.Map;

/** 异或加解密 Map */
public class MapXOR extends XOR{
    private final Map<String,String> storage = new HashMap<>();

    public MapXOR(boolean isChaos,String privateKey){
        super(isChaos,privateKey);
    }

    /**
     * 投递加密消息
     * @param k key
     * @param v val
     */
    public MapXOR put(String k,String v){
        storage.put(k,v);
        return this;
    }

    /** xor encryption */
    public String encryption(){
        String info = storage.toString();
        int hash = hash(info.hashCode());
        return def_encryption(getHeadMsg(hash),info,hash);
    }

    /** xor map decrypt */
    public Map<String,String> decrypt(String cipher){
        String result = def_decrypt(cipher);
        return result == null ? null : convert(result);
    }

    /**
     * 解密结果
     * @param source 源信息
     * @return 转换为 Map
     */
    private Map<String,String> convert(String source){
        Map<String,String> result = new HashMap<>();
        for(String s : source.substring(1,source.length()-1).split(", ")){
            int index = s.indexOf('=');
            result.put(s.substring(0,index),s.substring(index + 1));
        }
        return result;
    }
}