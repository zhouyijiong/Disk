package com.zyj.disk.tool;

import lombok.AllArgsConstructor;

/**
 * @Author: ZYJ
 * @Date: 2022/4/16 9:11
 * @Remark: 异或加解密(对称加密)
 */
@AllArgsConstructor
public abstract class XOR{
    protected final boolean isChaos;
    protected final String privateKey;

    /**
     * @Author: ZYJ
     * @Date: 2022/04/19
     * @Remark: 密码头
     */
    protected String setHead(String head){
        return encryption(head,head.length());
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/19
     * @Remark: 密码体
     */
    protected String setBody(String body,int offset){
        return encryption(body,offset);
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/19
     * @Remark: 校验码
     */
    protected String setCode(int offset){
        return encryption(String.valueOf(offset),offset);
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/19
     * @Remark: 默认加密
     */
    protected String def_encryption(String head,String source,int offset){
        return setHead(head) + "-" + setBody(source,hash(head.hashCode())) + "-" + setCode(offset);
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/19
     * @Remark: 默认解密
     */
    protected String def_decrypt(String cipher){
        String[] ciphers = cipher.split("-");
        cipher = ciphers[0];
        int hash = hash(decrypt(cipher,cipher.length() >> 1).hashCode());
        cipher = decrypt(ciphers[1],hash);
        hash = hash(cipher.hashCode());
        return encryption(String.valueOf(hash),hash).equals(ciphers[2]) ? cipher : null;
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/16
     * @Remark: 加密核心
     * @param info 加密信息
     * @param offset 偏移量
     */
    protected String encryption(String info,int offset){
        int len = info.length();
        StringBuilder sb = new StringBuilder(len << 1);
        for(int i=0;i<len;i++){
            int a = ~info.charAt(i) & 0xff;
            int b = (offset + i) % 0x20;
            sb.append(Integer.toHexString((a + b) % 0xff));
        }
        return sb.toString();
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/16
     * @Remark: 解密核心
     * @param info 密文
     * @param offset 偏移量
     */
    protected String decrypt(String info,int offset){
        int len = info.length();
        int offsetLen = len >> 1;
        char[] chars = new char[offsetLen];
        for(int i=0,k=0;i<len;++k){
            int a = Integer.parseInt(info.substring(i,i += 2),0x10);
            int b = (offset + k) % 0x20;
            chars[k] = (char)(0xff - a + b);//(~a & 0xff) + b
        }
        return new String(chars,0,offsetLen);
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/16
     * @Remark: 获取加密头
     * @param offset 偏移量
     */
    protected String getHeadMsg(int offset){
        if(!isChaos) return Integer.toHexString(offset);
            long hash = System.nanoTime();//* 0x39c204abfde6aL;
        return String.valueOf(hash << 31 ^ (hash >>> 31));
    }

    protected int hash(int hash){
        return hash ^ (hash >>> 16);
    }
}