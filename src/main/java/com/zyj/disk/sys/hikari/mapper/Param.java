package com.zyj.disk.sys.hikari.mapper;

import com.zyj.disk.sys.annotation.mapper.Delete;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: ZYJ
 * @Date: 2022/3/23 17:50
 * @Remark: { 有参数; 通过'#{}'对普通参数进行匹配 或 通过 ${*.*} 对 'Entity' 参数匹配; 返回一条SQL; }
 */
public final class Param extends MapperMatchSuper{
    public static final Param MATCH = new Param();

    @Override
    boolean check(ProceedingJoinPoint joinPoint, Integer integer){
        return false;
    }

    @Override
    boolean check(ProceedingJoinPoint joinPoint,Delete delete){
        return "".equals(delete.where());
    }
}