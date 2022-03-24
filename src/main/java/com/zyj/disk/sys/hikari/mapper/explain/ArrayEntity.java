package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.hikari.mapper.Mapper;
import org.aspectj.lang.ProceedingJoinPoint;
import java.lang.annotation.Annotation;

/**
 * @Author: ZYJ
 * @Date: 2022/3/23 17:57
 * @Remark: { 无参数; 遍历'Entity Array'数组,循环获取每项中' !=null 的参数 == 判断'; 返回一组SQL; }
 */
public final class ArrayEntity implements Mapper{
    public static final ArrayEntity MATCH = new ArrayEntity();

    @Override
    public boolean check(ProceedingJoinPoint joinPoint, Annotation annotation) {
        return !(joinPoint.getArgs().length == 1);
    }

    @Override
    public String explain(ProceedingJoinPoint joinPoint, Annotation annotation) {
        return null;
    }
}