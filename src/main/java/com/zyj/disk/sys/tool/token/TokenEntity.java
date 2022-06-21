package com.zyj.disk.sys.tool.token;

import com.zyj.disk.sys.entity.BaseEntity;
import com.zyj.disk.sys.tool.rsa.RSA;
import lombok.Getter;
import java.math.BigInteger;

/**
 * @Author: ZYJ
 * @Date: 2022/6/16 13:14
 * @Remark:
 */
@Getter
public final class TokenEntity extends BaseEntity{
    private final int id;

    private final BigInteger pk;

    private final BigInteger sk;

    private final BigInteger product;

    private final String privateKey;

    public TokenEntity(int id,String privateKey,RSA rsa){
        this.id = id;
        this.pk = rsa.PK.getNum();
        this.sk = rsa.SK.getNum();
        this.product = rsa.SK.getProduct();
        this.privateKey = privateKey;
    }
}