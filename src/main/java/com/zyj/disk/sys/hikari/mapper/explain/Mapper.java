package com.zyj.disk.sys.hikari.mapper.explain;

import org.aspectj.lang.ProceedingJoinPoint;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZYJ
 * @Date: 2022/3/22 9:44
 * @Remark: mapper interface
 */
public interface Mapper{
    boolean check(ProceedingJoinPoint joinPoint,Annotation annotation);

    String explain(ProceedingJoinPoint joinPoint,Annotation annotation);

    /**
     * TODO 考虑改用 long存储 用[1,2]表示,超过64位暂时无法处理
     * 探测 SQL 分布状况
     * @param sql 参数
     */
    default List<Integer> probe(String sql){
        List<Integer> result = new ArrayList<>();
        char[] chars = sql.toCharArray();
        for(char c : chars){
            switch(c){
                case '#':result.add((int)'#');
                    break;
                case '$':result.add((int)'$');
            }
        }
        return result;
    }
}