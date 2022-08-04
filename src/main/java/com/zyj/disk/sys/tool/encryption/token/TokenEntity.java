package com.zyj.disk.sys.tool.encryption.token;

import com.zyj.disk.sys.tool.encryption.rsa.RSA;
import lombok.Getter;

import java.math.BigInteger;

/**
 * Token Entity
 */
@Getter
public final class TokenEntity {
    private final Integer id;

    private final BigInteger pk;

    private final BigInteger sk;

    private final BigInteger product;

    public TokenEntity(int id, RSA rsa) {
        this.id = id;
        this.pk = rsa.PK.getNum();
        this.sk = rsa.SK.getNum();
        this.product = rsa.SK.getProduct();
    }
}