package com.zyj.disk.sys.tool.encryption.base64;

import java.nio.charset.StandardCharsets;

/**
 * @Author: ZYJ
 * @Date: 2022/6/14 9:18
 * @Remark: string base64
 */
public class StringBase64 extends AbstractBase64{
    public static String encodeToString(String str){
        return new String(encode(str.getBytes(StandardCharsets.UTF_8)));
    }
}