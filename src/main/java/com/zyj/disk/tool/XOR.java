package com.zyj.disk.tool;

import lombok.AllArgsConstructor;

/** 异或加解密(对称加密) */
@AllArgsConstructor
public abstract class XOR{
    protected final boolean isChaos;
    protected final String privateKey;

    /** 密码头 */
    protected String setHead(String head){
        return coreEncrypt(head,head.length());
    }

    /**
     * 获取密码体
     * @param offset 偏移量
     */
    protected String setBody(String body,int offset){
        return coreEncrypt(body,offset);
    }

    /**
     * 获取校验码
     * @param offset 偏移量
     */
    protected String setCode(int offset){
        return coreEncrypt(String.valueOf(offset),offset);
    }

    /**
     * 获取加密头
     * @param offset 偏移量
     */
    protected String getHeadMsg(int offset){
        if(!isChaos) return Integer.toHexString(offset);
        long hash = System.nanoTime();//* 0x39c204abfde6a;
        return String.valueOf(hash << 31 ^ (hash >>> 31));
    }

    protected int hash(int hash){
        return hash ^ (hash >>> 16);
    }

    /**
     * 默认加密
     * @param head 密码头
     * @param source 原文
     * @param offset 偏移量
     * @return String 密文
     */
    protected String defEncrypt(String head,String source,int offset){
        return setHead(head) + "-" + setBody(source,hash(head.hashCode())) + "-" + setCode(offset);
    }

    /**
     * 默认解密
     * @param cipher 密文
     * @return String 原文
     */
    protected String defDecrypt(String cipher){
        String[] ciphers = cipher.split("-");
        cipher = ciphers[0];
        int hash = hash(coreDecrypt(cipher,cipher.length() >> 1).hashCode());
        cipher = coreDecrypt(ciphers[1],hash);
        hash = hash(cipher.hashCode());
        return coreEncrypt(String.valueOf(hash),hash).equals(ciphers[2]) ? cipher : null;
    }

    /**
     * 加密核心
     * @param info 原文
     * @param offset 偏移量
     * @return String 密文
     */
    protected String coreEncrypt(String info,int offset){
        int len = info.length();
        StringBuilder sb = new StringBuilder(len << 1);
        for(int i=0;i<len;i++){
            int a = ~info.charAt(i) & 0xff;
            int b = (offset + i) & 0x20;
            sb.append(Integer.toHexString((a + b) & 0xff));
        }
        return sb.toString();
    }

    /**
     * 解密核心
     * @param info 密文
     * @param offset 偏移量
     * @return String 原文
     */
    protected String coreDecrypt(String info,int offset){
        int len = info.length();
        int offsetLen = len >> 1;
        char[] chars = new char[offsetLen];
        for(int i=0,k=0;i<len;++k){
            int a = Integer.parseInt(info.substring(i,i += 2),0x10);
            int b = (offset + k) & 0x20;
            chars[k] = (char)(0xff - a + b);//(~a & 0xff) + b
        }
        return new String(chars,0,offsetLen);
    }
}