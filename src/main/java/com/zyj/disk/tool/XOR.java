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
     * @param isChaos {true : 随机加密 | false : 固定加密} 不影响解密结果
     */
    public String encryption(boolean isChaos){
        int offset = storage.hashCode();
        String headMsg = getHeadMsg(isChaos,offset);
        StringBuilder result = new StringBuilder();
        for(Map.Entry<String,String> item : storage.entrySet()){
            offset = headMsg.hashCode();
            result.append(item.getKey()).append(":").append(item.getValue()).append(",");
        }
        result.delete(result.length()-1,result.length());
        return core_encryption(headMsg,headMsg.length())
                + "-" + core_encryption(result.toString(),offset);
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/16
     * @Remark: 异或解密
     * @param info 密文
     */
    public static Map<String,String> decrypt(String info){
        int index = info.lastIndexOf('-');
        String head = info.substring(0,index);
        head = core_decrypt(head,head.length() >> 1);
        head = core_decrypt(info.substring(index + 1),head.hashCode());
        return convert(head);
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
    private String core_encryption(String info,int offset){
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
        char[] chars = String.valueOf(System.currentTimeMillis()).toCharArray();
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
    private String xorOffset(int num,int offset){
        return Integer.toHexString(((~num & 0xff) + (offset % 0x10)) % 0xff);
    }
}