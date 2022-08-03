package com.zyj.disk.tool;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @Author: ZYJ
 * @Date: 2022/6/6 9:10
 * @Remark:
 */
public class XOR3{
    public static void main(String[] args) {
        String str = "InsertInterface";
        String result = encrypt(str);
        System.out.println(result);
    }

    public static String encrypt(String info){
        byte[] bytes = info.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(bytes));
        System.out.println(1);
        return null;
    }

    public static int[] temp(byte[] a){
        int keep;
        int byteLength = a.length;
        for(keep = 0;keep < byteLength && a[keep] == 0;++keep);
        // Allocate new array and copy relevant part of input array
        int intLength = ((byteLength - keep) + 3) >>> 2;
        int[] result = new int[intLength];
        int b = byteLength - 1;
        for (int i = intLength-1; i >= 0; i--) {
            result[i] = a[b--] & 0xff;
            int bytesRemaining = b - keep + 1;
            int bytesToTransfer = Math.min(3, bytesRemaining);
            for (int j=8; j <= (bytesToTransfer << 3); j += 8)
                result[i] |= ((a[b--] & 0xff) << j);
        }
        return result;
    }

    static int mag[];
    static int signum;
    static BigInteger[] longRadix = {
            null,null,
            BigInteger.valueOf(0x4000000000000000L),BigInteger.valueOf(0x383d9170b85ff80bL),
            BigInteger.valueOf(0x4000000000000000L),BigInteger.valueOf(0x6765c793fa10079dL),
            BigInteger.valueOf(0x41c21cb8e1000000L),BigInteger.valueOf(0x3642798750226111L),
            BigInteger.valueOf(0x1000000000000000L),BigInteger.valueOf(0x12bf307ae81ffd59L),
            BigInteger.valueOf(0xde0b6b3a7640000L),BigInteger.valueOf(0x4d28cb56c33fa539L),
            BigInteger.valueOf(0x1eca170c00000000L),BigInteger.valueOf(0x780c7372621bd74dL),
            BigInteger.valueOf(0x1e39a5057d810000L),BigInteger.valueOf(0x5b27ac993df97701L),
            BigInteger.valueOf(0x1000000000000000L),BigInteger.valueOf(0x27b95e997e21d9f1L),
            BigInteger.valueOf(0x5da0e1e53c5c8000L),BigInteger.valueOf( 0xb16a458ef403f19L),
            BigInteger.valueOf(0x16bcc41e90000000L),BigInteger.valueOf(0x2d04b7fdd9c0ef49L),
            BigInteger.valueOf(0x5658597bcaa24000L),BigInteger.valueOf( 0x6feb266931a75b7L),
            BigInteger.valueOf( 0xc29e98000000000L),BigInteger.valueOf(0x14adf4b7320334b9L),
            BigInteger.valueOf(0x226ed36478bfa000L),BigInteger.valueOf(0x383d9170b85ff80bL),
            BigInteger.valueOf(0x5a3c23e39c000000L),BigInteger.valueOf( 0x4e900abb53e6b71L),
            BigInteger.valueOf( 0x7600ec618141000L),BigInteger.valueOf( 0xaee5720ee830681L),
            BigInteger.valueOf(0x1000000000000000L),BigInteger.valueOf(0x172588ad4f5f0981L),
            BigInteger.valueOf(0x211e44f7d02c1000L),BigInteger.valueOf(0x2ee56725f06e5c71L),
            BigInteger.valueOf(0x41c21cb8e1000000L)
    };
//    public static String smallToString(int radix){
//        // Compute upper bound on number of digit groups and allocate space
//        int maxNumDigitGroups = (4*mag.length + 6)/7;
//        String[] digitGroup = new String[maxNumDigitGroups];
//
//        int numGroups = 0;
//        while (signum != 0) {
//            BigInteger d = longRadix[radix];
//            MutableBigInteger q = new MutableBigInteger(),
//                    InsertInterface = new MutableBigInteger(tmp.mag),
//                    b = new MutableBigInteger(d.mag);
//            MutableBigInteger r = InsertInterface.divide(b, q);
//            BigInteger q2 = q.toBigInteger(tmp.signum * d.signum);
//            BigInteger r2 = r.toBigInteger(tmp.signum * d.signum);
//
//            digitGroup[numGroups++] = Long.toString(r2.longValue(), radix);
//            tmp = q2;
//        }
//
//        // Put sign (if any) and first digit group into result buffer
//        StringBuilder buf = new StringBuilder(numGroups*digitsPerLong[radix]+1);
//        if (signum < 0) {
//            buf.append('-');
//        }
//        buf.append(digitGroup[numGroups-1]);
//
//        // Append remaining digit groups padded with leading zeros
//        for (int i=numGroups-2; i >= 0; i--) {
//            // Prepend (any) leading zeros for this digit group
//            int numLeadingZeros = digitsPerLong[radix]-digitGroup[i].length();
//            if (numLeadingZeros != 0) {
//                buf.append(zeros[numLeadingZeros]);
//            }
//            buf.append(digitGroup[i]);
//        }
//        return buf.toString();
//    }
}