package com.zyj.disk.sys.hikari.mapper;

import com.zyj.disk.sys.annotation.mapper.Delete;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: ZYJ
 * @Date: 2022/3/23 11:20
 * @Remark: { 无参数; 不匹配; 不能传参; 返回一条SQL; }
 */
public final class No extends MapperMatchSuper{
    public static final No MATCH = new No();

    @Override
    boolean check(ProceedingJoinPoint joinPoint, Integer integer){
        return false;
    }

    @Override
    boolean check(ProceedingJoinPoint joinPoint,Delete delete){
        return joinPoint.getArgs().length != 0 || !"".equals(delete.where());
    }
}