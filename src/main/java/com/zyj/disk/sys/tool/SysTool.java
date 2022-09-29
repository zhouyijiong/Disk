package com.zyj.disk.sys.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SYS Tool
 */
public final class SysTool {
    private static final Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern LINE_PATTERN = Pattern.compile("_(\\w)");

    /**
     * 驼峰转下划线
     *
     * @param str 转换内容
     * @return 转换结果
     */
    public static String humpToLine(String str) {
        Matcher matcher = HUMP_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        return matcher.appendTail(sb).toString();
    }

    /**
     * 下划线转驼峰
     *
     * @param str 转换内容
     * @return 转换结果
     */
    public static String lineToHump(String str) {
        Matcher matcher = LINE_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}