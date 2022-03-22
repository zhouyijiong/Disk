package com.zyj.disk.sys.hikari.mapper;

import org.aspectj.lang.ProceedingJoinPoint;
import java.lang.annotation.Annotation;

/**
 * @Author: ZYJ
 * @Date: 2022/3/22 9:44
 * @Remark: mapper interface
 */
public interface Mapper{
    boolean check(ProceedingJoinPoint joinPoint,Annotation annotation);

    String explain(ProceedingJoinPoint joinPoint,Annotation annotation);
}