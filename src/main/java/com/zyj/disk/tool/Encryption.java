package com.zyj.disk.tool;

import com.zyj.disk.sys.entity.Record;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public final class Encryption{
    private final Record record = Record.initialize(this.getClass());

    /** MD5 */
    private String view(byte[] bytes){
        StringBuilder hexValue = new StringBuilder();
        for(byte b : bytes){
            int val = ((int)b) & 0xff;
            if(val < 16) hexValue.append(0);
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public String md5(String str){
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return view(md5.digest(str.getBytes(StandardCharsets.UTF_8)));
        }catch(NoSuchAlgorithmException e){
            record.error(e);
            return null;
        }
    }

    public String md5(String ...info){
        return md5(String.join("",info));
    }

    /** XOR */
    public String xor(String str){
        StringBuilder sb = new StringBuilder(str.length() << 1);
        for(char c : str.toCharArray()) sb.append(Integer.toHexString(~c & 0xff));
        return sb.toString();
    }

    public String deXor(String str){
        int len = str.length();
        StringBuilder sb = new StringBuilder(len >> 1);
        for(int i=0;i<len;) sb.append((char)(0xff - Integer.parseInt(str.substring(i,i += 2),16)));
        return sb.toString();
    }
}