package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.hikari.Actuator;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZYJ
 * @Date: 2022/3/26 11:05
 * @Remark:
 */
@Component
@RequiredArgsConstructor
public abstract class Mapper{
    protected final Actuator actuator;

    public abstract boolean check(ProceedingJoinPoint joinPoint, Annotation annotation);

    public abstract String explain(ProceedingJoinPoint joinPoint,Annotation annotation);

    public abstract Object handle(ProceedingJoinPoint joinPoint,Annotation annotation);

    /**
     * TODO 考虑改用 long存储 用[1,2]表示,超过64位暂时无法处理
     * 探测 SQL 分布状况
     * @param sql 参数
     */
    List<Integer> probe(String sql){
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