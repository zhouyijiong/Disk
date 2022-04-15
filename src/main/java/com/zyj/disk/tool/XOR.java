package com.zyj.disk.tool;

/**
 * @Author: ZYJ
 * @Date: 2022/4/15 17:59
 * @Remark: 异或加解密
 */
public final class XOR{
    public String encryption(String info){
        int hash = info.hashCode();
        if(hash < 0) hash = -hash;
        String head = Integer.toHexString(hash);
        return core(head,head.length()) + "-" + core(info,hash);
    }

    public String decrypt(String ciphertext){
        int index = ciphertext.indexOf('-');
        boolean key = index > -1;
        if(key){
            int temp = index;
            index = Integer.parseInt(decrypt(ciphertext.substring(0,temp)),0x10);
            ciphertext = ciphertext.substring(temp + 1);
        }
        int len = ciphertext.length();
        int offsetLen = len >> 1;
        char[] chars = new char[offsetLen];
        for(int i=0,k=0;i<len;++k){
            String str = ciphertext.substring(i,i += 2);
            chars[k] = (char)(0xff - (Integer.parseInt(str,0x10) - (key ? (index + k) % 0x10 : offsetLen + k)));
        }
        return new String(chars,0,offsetLen);
    }

    private String xorOffset(int num,int offset){
        return Integer.toHexString(((~num & 0xff) + (offset % 0x10)) % 0xff);
    }

    private String core(String info,int offset){
        int len = info.length();
        StringBuilder sb = new StringBuilder(len << 1);
        for(int i=0;i<len;i++) sb.append(xorOffset(info.charAt(i),offset + i));
        return sb.toString();
    }
}