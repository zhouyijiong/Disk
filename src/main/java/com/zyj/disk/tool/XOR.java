package com.zyj.disk.tool;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author: ZYJ
 * @Date: 2022/4/16 9:11
 * @Remark: 异或加解密
 */
public final class XOR{
    private final Map<String,String> storage;

    private XOR(){ storage = new HashMap<>(); }

    public static XOR init(){ return new XOR(); }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/16
     * @Remark: 异或加密
     * @param privateKey 私钥
     * @param isChaos    {true : 随机加密 | false : 固定加密} 不影响解密结果
     * @param successive {true : 链状加密 | false : 整合加密} 不影响解密结果
     */
    public String encryption(boolean isChaos,boolean successive,String privateKey){
        int offset = storage.hashCode();
        String headMsg = privateKey == null ? getHeadMsg(isChaos,offset) : Integer.toHexString(privateKey.hashCode());
        StringBuilder result = new StringBuilder();
        if(successive){
            result.append(core_encryption(privateKey == null ?
                    headMsg : String.valueOf(System.nanoTime()),headMsg.length()));
        }
        int count = storage.size();
        for(Map.Entry<String,String> item : storage.entrySet()){
            offset = headMsg.hashCode();
            if(successive){
                headMsg = item.getKey() + ":" + item.getValue() + (--count > 0 ? "," : "");
                result.append("-").append(core_encryption(headMsg,offset));
            }else{
                result.append(item.getKey()).append(":").append(item.getValue()).append(",");
            }
        }
        System.out.println(privateKey + " : " + headMsg + ", offset: " + offset + ", default: " + "".hashCode());
        if(successive) return result.toString();
        result.delete(result.length()-2,result.length());
        return (privateKey == null ? core_encryption(headMsg,headMsg.length()) : String.valueOf(System.nanoTime()))
                + "-" + core_encryption(result.toString(),offset);
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/16
     * @Remark: 异或加密
     * @param isChaos    {true : 随机加密 | false : 固定加密} 不影响解密结果
     * @param successive {true : 链状加密 | false : 整合加密} 不影响解密结果
     */
    public String encryption(boolean isChaos,boolean successive){
        return encryption(isChaos,successive,null);
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/16
     * @Remark: 异或解密
     * @param info 密文
     */
    public static Map<String,String> decrypt(String info,String privateKey){
        StringBuilder storage = new StringBuilder();
        decrypt(info,storage,privateKey);
        return convert(storage.toString());
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/16
     * @Remark: 异或解密
     * @param info 密文
     */
    public static Map<String,String> decrypt(String info){
        return decrypt(info,null);
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/16
     * @Remark: 异或解密
     * @param info 密文
     */
    private static String decrypt(String info,StringBuilder storage,String privateKey){
        if(privateKey != null){
            info = Integer.toHexString(privateKey.hashCode()) + info.substring(info.indexOf('-'));
        }
        System.out.println("info: " + info);
        int index = info.lastIndexOf('-');
        boolean key = index > -1;
        if(key){
            int temp = index;
            index = decrypt(info.substring(0,temp),storage,null).hashCode();
            info = info.substring(temp + 1);
        }
        String str = core_decrypt(info,key ? index : info.length() >> 1);
        if(key) storage.append(str);
        return str;
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/16
     * @Remark: 投递加密消息,return this
     * @param k key
     * @param v val
     */
    public XOR put(String k,String v){
        storage.put(k,v);
        return this;
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/16
     * @Remark: 加密核心
     * @param info 加密信息
     * @param offset 偏移量
     */
    private static String core_encryption(String info,int offset){
        int len = info.length();
        StringBuilder sb = new StringBuilder(len << 1);
        for(int i=0;i<len;i++) sb.append(xorOffset(info.charAt(i),offset + i));
        return sb.toString();
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/16
     * @Remark: 解密核心
     * @param info 密文
     * @param offset 偏移量
     */
    private static String core_decrypt(String info,int offset){
        int len = info.length();
        int offsetLen = len >> 1;
        char[] chars = new char[offsetLen];
        for(int i=0,k=0;i<len;++k){
            chars[k] = (char)(0xff - (Integer.parseInt(info.substring(i,i += 2),0x10) - (offset + k) % 0x10));
        }
        return new String(chars,0,offsetLen);
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/16
     * @Remark: 转换为 map
     * @param json 解密结果
     */
    private static Map<String,String> convert(String json){
        Map<String,String> storage = new HashMap<>();
        for(String s : json.split(",")){
            int index = s.indexOf(':');
            storage.put(s.substring(0,index),s.substring(index + 1));
        }
        return storage;
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/16
     * @Remark: 获取加密头
     * @param isChaos 是否随机
     * @param offset 偏移量
     */
    private String getHeadMsg(boolean isChaos,int offset){
        if(!isChaos) return Integer.toHexString(offset);
        char[] chars = String.valueOf(System.nanoTime()).toCharArray();
        Random random = new Random();
        StringBuilder record = new StringBuilder();
        for(int i=chars.length-1;i>0;i--){
            char temp = chars[i];
            offset = random.nextInt(i);
            chars[i] = chars[offset];
            chars[offset] = temp;
            if(temp > 45) record.append(chars[i]);
        }
        return record.reverse().toString();
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/16
     * @Remark: 异或加密并附加偏移量
     * @param num 基础值
     * @param offset 偏移量
     */
    private static String xorOffset(int num,int offset){
        return Integer.toHexString(((~num & 0xff) + (offset % 0x10)) % 0xff);
    }

    public static void main(String[] args){
        XOR xor = new XOR();
        xor.put("name","aress");
        xor.put("age","20");
        //xor.put("url","https://translate.google.cn/?sl=en&tl=zh-CN&text=successive&op=translate");
        String msg = xor.encryption(false,false,"salt");
        System.out.println("msg: " + msg);
        System.out.println(XOR.decrypt(msg,"salt"));
        System.out.println("================");
        System.out.println(XOR.core_decrypt("919f949dc9a393a19495dda9a4a7d3dc",1512030448));
        //System.out.println(map);
    }
}