package com.zyj.disk.tool;

import java.nio.charset.StandardCharsets;

/** 异或加解密(对称加密) */
public class XOR1{
    /**
     * 加密核心
     * @param info 原文
     * @return String 密文
     */

    //[48,57]  [65,90]  [97,122]
    protected String coreEncrypt(String info,long offset){
        byte[] bytes = info.getBytes(StandardCharsets.UTF_8);
        StringBuilder str = new StringBuilder();
        str.append(1);
        for(int i=0;i<bytes.length;++i){
            offset ^= bytes[i];
            if(offset < 48){
                str.append(1);
                bytes[i] = (byte) (offset + 48);
            }else{
                str.append(0);
                bytes[i] = (byte) offset;
            }
            offset = bytes[i];
        }
        return Long.parseLong(str.toString(),2) + "-" + new String(bytes);
    }

    protected String coreDecrypt(String info,long offset){
        int index = info.indexOf('-');
        byte[] target = Long.toBinaryString(Long.parseLong(info.substring(0,index))).substring(1).getBytes(StandardCharsets.UTF_8);
        byte[] bytes = info.substring(index + 1).getBytes(StandardCharsets.UTF_8);
        for(int i=bytes.length-1;i>0;--i){
            if(target[i] == 49) bytes[i] -= 48;
            bytes[i] ^= bytes[i-1];
        }
        bytes[0] ^= offset;
        return new String(bytes);
    }

    public static void main(String[] args) {
        int offset = 65537;
        XOR1 xor1 = new XOR1();
        String result = xor1.coreEncrypt("Administrator",offset);
        System.out.println(result);
        System.out.println(xor1.coreDecrypt(result,offset));
    }
}