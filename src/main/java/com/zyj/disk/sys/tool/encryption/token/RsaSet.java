package com.zyj.disk.sys.tool.encryption.token;

import com.zyj.disk.sys.tool.encryption.rsa.RSA;
import lombok.AllArgsConstructor;

/**
 * @Author: ZYJ
 * @Date: 2022/6/13 15:41
 * @Remark: rsa set
 */
@AllArgsConstructor
public enum RsaSet {
    PUBLIC(new RSA(2048, 65537)),
    RESPONSE(new RSA(2048, 65537));

    public final RSA RSA;
}