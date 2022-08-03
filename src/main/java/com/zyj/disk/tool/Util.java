package com.zyj.disk.tool;

public final class Util {
    /**
     * 四舍五入到小数点后 'bit' 位
     *
     * @return double
     */
    public double round(double b, int bit) {
        String str = String.valueOf(b);
        int index = str.indexOf(".");
        String body = str.substring(index + 1);
        if (body.length() <= bit) return b;
        String head = str.substring(0, index);
        String tail = body.substring(0, bit);
        if (body.charAt(bit) < 53) return Double.parseDouble(head + "." + tail);
        String temp = String.valueOf(Long.parseLong(tail) + 1);
        return tail.length() == temp.length() ? Double.valueOf(head + "." + temp) : Double.valueOf(Long.parseLong(head) + 1);
    }
}