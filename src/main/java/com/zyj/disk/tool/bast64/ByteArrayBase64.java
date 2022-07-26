package com.zyj.disk.tool.bast64;

/**
 * @Author: ZYJ
 * @Date: 2022/6/14 9:14
 * @Remark: 字节数组
 */
public final class ByteArrayBase64 extends AbstractBase64{
    public static String encodeToString(byte[] bytes){
        return new String(encode(bytes));
    }
}