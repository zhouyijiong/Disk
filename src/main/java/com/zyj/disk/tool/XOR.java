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
    private final Map<String,String> storage = new HashMap<>();

    /** 对偏移量附加 0x10 最低值,不影响结果,但还是无法保证部分字符加解密 */
    boolean isFix = false;

    /** 每次加密结果不一样,不影响解密结果 */
    boolean chaos = true;

    public XOR put(String k,String v){
        storage.put(k,v);
        return this;
    }

    public String encryption(){
        int offset = storage.hashCode();
        String info = headInfo(offset);
        StringBuilder result = new StringBuilder();
        for(Map.Entry<String,String> item : storage.entrySet()){
            offset = info.hashCode();
            result.append(item.getKey()).append(":").append(item.getValue()).append(",");
        }
        return core(info,info.length()) + "-" + core(result.delete(result.length()-1,result.length()).toString(),offset);
    }

    public String decrypt(String msg){
        int index = msg.lastIndexOf('-');
        boolean key = index > -1;
        if(key){
            int temp = index;
            index = decrypt(msg.substring(0,temp)).hashCode();
            msg = msg.substring(temp + 1);
        }
        int len = msg.length();
        int offsetLen = len >> 1;
        char[] chars = new char[offsetLen];
        for(int i=0,k=0;i<len;++k){
            int offset = (key ? index + k : offsetLen + k) % 0x10;
            if(isFix) offset += 0x10;
            chars[k] = (char)(0xff - (Integer.parseInt(msg.substring(i,i += 2),0x10) - offset));
        }
        String str = new String(chars,0,offsetLen);
        if(key) convert(str);
        return str;
    }

    public String get(String k){
        return storage.get(k);
    }

    private String core(String info,int offset){
        int len = info.length();
        StringBuilder sb = new StringBuilder(len << 1);
        for(int i=0;i<len;i++) sb.append(xorOffset(info.charAt(i),offset + i));
        return sb.toString();
    }

    private void convert(String json){
        storage.clear();
        for(String s : json.split(",")){
            int index = s.indexOf(':');
            storage.put(s.substring(0,index),s.substring(index + 1));
        }
    }

    private String headInfo(int offset){
        if(!chaos) return Integer.toHexString(offset);
        char[] chars = String.valueOf(offset + (int) ((System.currentTimeMillis())/1000)).toCharArray();
        Random random = new Random();
        StringBuilder record = new StringBuilder();
        for(int i=chars.length-1;i>0;i--){
            char temp = chars[i];
            offset = random.nextInt(i);
            chars[i] = chars[offset];
            chars[offset] = temp;
            if(temp > 45) record.append(temp);
        }
        return record.toString();
    }

    private String xorOffset(int num,int offset){
        int sum = ((~num & 0xff) + (offset % 0x10)) % 0xff;
        if(isFix) sum = (sum + 0x10) % 0xff;
        return Integer.toHexString(sum);
    }
}