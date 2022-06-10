package com.zyj.disk.tool;

import lombok.AllArgsConstructor;
import lombok.ToString;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/** RSA 非对称加密 */
public class RSA{
    public final Key PK;
    public final Key SK;

    @ToString
    @AllArgsConstructor
    public static final class Key{
        private final BigInteger num;
        private final BigInteger total;

        public String encrypt(String info){
            return new BigInteger(info.getBytes(StandardCharsets.UTF_8)).modPow(num,total).toString(32);
        }

        public String decrypt(String info){
            return new String(new BigInteger(info,32).modPow(num,total).toByteArray());
        }
    }

    public RSA(int len,int base){
        int factorLen = len >> 1;
        Random random = new Random();
        BigInteger p = BigInteger.probablePrime(factorLen,random);
        BigInteger q = BigInteger.probablePrime(factorLen,random);
        BigInteger N = p.multiply(q);
        BigInteger f = N.subtract(p).subtract(q).add(BigInteger.ONE);
        BigInteger E = BigInteger.valueOf(base);
        BigInteger D = getD(E,f).getD(f);
        if(E.multiply(D).mod(f).intValue() != 1) throw new RuntimeException();
        PK = new Key(E,N);
        SK = new Key(D,N);
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

    public static void main(String[] args) {
        RSA rsa = new RSA(2048,65537);
        String result = rsa.PK.encrypt("Administrator");
        rsa.SK.decrypt(result);
    }
}