package com.zyj.disk.sys.tool.token;

import com.zyj.disk.sys.tool.rsa.RSA;
import lombok.AllArgsConstructor;

/**
 * @Author: ZYJ
 * @Date: 2022/6/13 15:41
 * @Remark: rsa set
 */
@AllArgsConstructor
public enum RsaSet{
    TOKEN(new RSA(2048,65537)),
    RESPONSE(new RSA(2048,65537));

    public final RSA rsa;
}