package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.annotation.mapper.Delete;
import com.zyj.disk.sys.entity.BaseEntity;
import com.zyj.disk.sys.hikari.mapper.Mapper;
import com.zyj.disk.sys.hikari.mapper.MapperMatchSuper;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.annotation.Annotation;

/**
 * @Author: ZYJ
 * @Date: 2022/3/23 17:56
 * @Remark: { 无参数; 遍历'Entity'参数,取' !=null 的参数 == 判断'; 返回一条SQL; }
 */
public final class Entity implements Mapper{
    public static final Entity MATCH = new Entity();

    @Override
    public boolean check(ProceedingJoinPoint joinPoint, Annotation annotation) {
        Delete delete = (Delete) annotation;
        if(!"".equals(delete.where())) return true;
        for(Object param : joinPoint.getArgs()) if(!(param instanceof BaseEntity)) return true;
        return false;
    }

    @Override
    public String explain(ProceedingJoinPoint joinPoint, Annotation annotation) {
        return null;
    }
}