package com.zyj.disk.sys.hikari.mapper;

import com.zyj.disk.sys.annotation.mapper.Delete;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: ZYJ
 * @Date: 2022/3/23 17:24
 * @Remark:
 */
public abstract class MapperMatchSuper{
    abstract boolean check(ProceedingJoinPoint joinPoint,Integer integer);

    abstract boolean check(ProceedingJoinPoint joinPoint,Delete delete);
}