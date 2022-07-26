package com.zyj.disk.tool;

import com.zyj.disk.sys.tool.structure.HashPair;
import com.zyj.disk.sys.tool.structure.Pair;
import java.util.HashMap;
import java.util.Map;

/** 异或加解密 Map */
public class PairXOR<K,V> extends XOR{
    private final Pair<K,V> storage = new HashPair<>();

    /**
     * 投递加密消息
     *
     * @param key key
     * @param val val
     */
    public void put(K key,V val){
        storage.put(key,val);
    }

    public String encrypt(String privateKey){
        return encrypt(storage.toJSONString(),false,privateKey);
    }

    @Override
    public String decrypt(String info,String privateKey){
        return defDecrypt(info,privateKey);
    }

    /**
     * 格式化解密结果
     *
     * @param source 源信息
     * @return 转换为 Map
     */
    public Map<String,String> convert(String source){
        Map<String,String> result = new HashMap<>();
        for(String s : source.substring(1,source.length() - 1).split(",")){
            int index = s.indexOf('=');
            result.put(s.substring(0,index),s.substring(index + 1));
        }
        return result;
    }
}