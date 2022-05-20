package com.zyj.disk.tool;

import lombok.AllArgsConstructor;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

/**
 * @Author: ZYJ
 * @Date: 2022/5/19 10:32
 * @Remark: RSA 非对称加密
 */
public class RSA{
    private final BigInteger N;
    private final BigInteger E = new BigInteger("65537");
    private final BigInteger D;

    @AllArgsConstructor
    static final class Key{
        private final BigInteger num;
        private final BigInteger total;

        public String encrypt(String info){
            byte[] dataBytes = info.getBytes(StandardCharsets.UTF_8);
            return Base64.getEncoder().encodeToString(new BigInteger(dataBytes).modPow(num,total).toByteArray());
        }

        public String decrypt(String info){
            byte[] dataBytes = info.getBytes(StandardCharsets.UTF_8);
            byte[] decodeData = Base64.getDecoder().decode(dataBytes);
            return new String(new BigInteger(decodeData).modPow(num,total).toByteArray());
        }
    }

    public RSA(int len){
        int factorLen = len >> 1;
        Random random = new Random();
        BigInteger p = BigInteger.probablePrime(factorLen,random);
        BigInteger q = BigInteger.probablePrime(factorLen,random);
        N = p.multiply(q);
        BigInteger f = N.subtract(p).subtract(q).add(BigInteger.ONE);
        D = getD(E,f).getD(f);
        if(E.multiply(D).mod(f).intValue() != 1) throw new RuntimeException();
    }

    private Result getD(BigInteger a,BigInteger b){
        if(b.compareTo(BigInteger.ZERO) == 0) return new Result(a,BigInteger.ONE,BigInteger.ZERO);
        Result result = getD(b,a.mod(b));
        return new Result(result.R,result.N2,result.N1.subtract(a.divide(b).multiply(result.N2)));
    }

    @AllArgsConstructor
    static class Result{
        public final BigInteger R;
        public final BigInteger N1;
        public final BigInteger N2;

        public BigInteger getD(BigInteger f){
            return N1.compareTo(BigInteger.ZERO) < 0 ? N1.add(f) : N1;
        }
    }

    public Key getPK(){
        return new Key(E,N);
    }

    public Key getSK(){
        return new Key(D,N);
    }
}