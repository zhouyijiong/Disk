package com.zyj.disk.sys.tool.encryption.codec;

import java.util.Arrays;

/**
 * base64
 */
public class AbstractBase64 {
    static final int[] INV = new int[256];

    static final char[] CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

    static {
        Arrays.fill(INV, -1);
        for (int i = 0, iS = CHARS.length; i < iS; i++) INV[CHARS[i]] = i;
        INV['='] = 0;
    }

    public static byte[] encode(byte[] arr) {
        if (arr == null || arr.length == 0) return new byte[0];
        int len = arr.length;
        int lens = (len / 3) * 3;
        int cnt = ((len - 1) / 3 + 1) << 2;
        byte[] dest = new byte[cnt];
        for (int s = 0, d = 0; s < lens; ) {
            int i = (arr[s++] & 0xff) << 16 | (arr[s++] & 0xff) << 8 | (arr[s++] & 0xff);
            dest[d++] = (byte) CHARS[(i >>> 18) & 0x3f];
            dest[d++] = (byte) CHARS[(i >>> 12) & 0x3f];
            dest[d++] = (byte) CHARS[(i >>> 6) & 0x3f];
            dest[d++] = (byte) CHARS[i & 0x3f];
        }
        int left = len - lens;
        if (left > 0) {
            int i = ((arr[lens] & 0xff) << 10) | (left == 2 ? ((arr[len - 1] & 0xff) << 2) : 0);
            dest[cnt - 4] = (byte) CHARS[i >> 12];
            dest[cnt - 3] = (byte) CHARS[(i >>> 6) & 0x3f];
            dest[cnt - 2] = left == 2 ? (byte) CHARS[i & 0x3f] : (byte) '=';
            dest[cnt - 1] = '=';
        }
        return dest;
    }

    public static byte[] decode(byte[] arr) {
        int length = arr.length;
        if (length == 0) return new byte[0];
        int sndx = 0, endx = length - 1;
        int pad = arr[endx] == '=' ? (arr[endx - 1] == '=' ? 2 : 1) : 0;
        int cnt = endx - sndx + 1;
        int sepCnt = length > 76 ? (arr[76] == '\r' ? cnt / 78 : 0) << 1 : 0;
        int len = ((cnt - sepCnt) * 6 >> 3) - pad;
        byte[] dest = new byte[len];
        int d = 0;
        for (int cc = 0, eLen = (len / 3) * 3; d < eLen; ) {
            int i = INV[arr[sndx++]] << 18 | INV[arr[sndx++]] << 12 | INV[arr[sndx++]] << 6 | INV[arr[sndx++]];
            dest[d++] = (byte) (i >> 16);
            dest[d++] = (byte) (i >> 8);
            dest[d++] = (byte) i;
            if (sepCnt > 0 && ++cc == 19) {
                sndx += 2;
                cc = 0;
            }
        }
        if (d < len) {
            int i = 0;
            for (int j = 0; sndx <= endx - pad; j++) i |= INV[arr[sndx++]] << (18 - j * 6);
            for (int r = 16; d < len; r -= 8) dest[d++] = (byte) (i >> r);
        }
        return dest;
    }
}