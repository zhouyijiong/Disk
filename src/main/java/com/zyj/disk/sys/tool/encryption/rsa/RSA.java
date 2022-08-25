package com.zyj.disk.sys.tool.encryption.rsa;

import com.zyj.disk.sys.tool.encryption.codec.Base64;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * RSA 非对称加密
 */
public class RSA {
    public final Key PK;
    public final Key SK;

    @Getter
    @AllArgsConstructor
    public static final class Key {
        private final BigInteger num;
        private final BigInteger product;

        public String encrypt(String info) {
            byte[] bytes = info.getBytes(StandardCharsets.UTF_8);
            bytes = new BigInteger(bytes).modPow(num, product).toByteArray();
            return new String(Base64.encode(bytes), StandardCharsets.UTF_8);
        }

        public String decrypt(String info) {
            byte[] bytes = info.getBytes(StandardCharsets.UTF_8);
            bytes = new BigInteger(Base64.decode(bytes)).modPow(num, product).toByteArray();
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }

    public RSA(int len, int e) {
        int factorLen = len >> 1;
        Random random = new Random();
        BigInteger p = BigInteger.probablePrime(factorLen, random);
        BigInteger q = BigInteger.probablePrime(factorLen, random);
        BigInteger N = p.multiply(q);
        BigInteger f = N.subtract(p).subtract(q).add(BigInteger.ONE);
        BigInteger E = BigInteger.valueOf(e);
        BigInteger D = getD(E, f).getD(f);
        if (E.multiply(D).mod(f).intValue() != 1) throw new RuntimeException();
        PK = new Key(E, N);
        SK = new Key(D, N);
    }

    private Result getD(BigInteger a, BigInteger b) {
        if (b.compareTo(BigInteger.ZERO) == 0) return new Result(a, BigInteger.ONE, BigInteger.ZERO);
        Result result = getD(b, a.mod(b));
        return new Result(result.R, result.N2, result.N1.subtract(a.divide(b).multiply(result.N2)));
    }

    @AllArgsConstructor
    static final class Result {
        public final BigInteger R;
        public final BigInteger N1;
        public final BigInteger N2;

        public BigInteger getD(BigInteger f) {
            return N1.compareTo(BigInteger.ZERO) < 0 ? N1.add(f) : N1;
        }
    }
}