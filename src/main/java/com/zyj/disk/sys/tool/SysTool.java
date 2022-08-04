package com.zyj.disk.sys.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SYS Tool
 */
public final class SysTool {
    private static final Pattern humpPattern = Pattern.compile("[A-Z]");
    private static final Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 驼峰转下划线
     *
     * @param str 转换内容
     * @return 转换结果
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     *
     * @param str 转换内容
     * @return 转换结果
     */
    public static String LineToHump(String str) {
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}