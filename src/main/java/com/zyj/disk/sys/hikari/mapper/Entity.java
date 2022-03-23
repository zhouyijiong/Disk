package com.zyj.disk.sys.hikari.mapper;

import com.zyj.disk.sys.annotation.mapper.Delete;
import com.zyj.disk.sys.entity.BaseEntity;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: ZYJ
 * @Date: 2022/3/23 17:56
 * @Remark: { 无参数; 遍历'Entity'参数,取' !=null 的参数 == 判断'; 返回一条SQL; }
 */
public final class Entity extends MapperMatchSuper{
    public static final Entity MATCH = new Entity();

    @Override
    boolean check(ProceedingJoinPoint joinPoint, Integer integer){
        return false;
    }

    @Override
    boolean check(ProceedingJoinPoint joinPoint,Delete delete){
        if(!"".equals(delete.where())) return true;
        for(Object param : joinPoint.getArgs()) if(!(param instanceof BaseEntity)) return true;
        return false;
    }
}