package com.zyj.disk.tool;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZYJ
 * @Date: 2022/4/18 9:19
 * @Remark: 异或加解密 Map
 */
public class MapXOR extends XOR{
    private final Map<String,String> storage = new HashMap<>();

    public MapXOR(boolean isChaos,String privateKey){
        super(isChaos,privateKey);
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/16
     * @Remark: 投递加密消息,return this
     * @param k key
     * @param v val
     */
    public MapXOR put(String k,String v){
        storage.put(k,v);
        return this;
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/16
     * @Remark: xor encryption
     */
    public String encryption(){
        String source = storage.toString();
        int hash = source.hashCode();
        return def_encryption(getHeadMsg(hash),source,hash);
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/16
     * @Remark: xor map decrypt
     */
    public Map<String,String> decrypt(String cipher){
        String result = def_decrypt(cipher);
        return result == null ? null : convert(result);
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/16
     * @Remark: 转换为 map
     * @param source 解密结果
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