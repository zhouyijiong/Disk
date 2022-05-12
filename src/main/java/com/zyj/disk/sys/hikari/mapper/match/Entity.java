package com.zyj.disk.sys.hikari.mapper.match;

import com.zyj.disk.sys.annotation.mapper.base.*;
import com.zyj.disk.sys.entity.BaseEntity;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 匹配模式 : 实体类
 * 无SQL,实体类字段匹配
 */
public final class Entity extends Match{
    @Override
    public boolean insertCheck(ProceedingJoinPoint joinPoint,Insert insert){
        return joinPoint.getArgs().length == 1;
    }

    @Override
    public String insertExplain(ProceedingJoinPoint joinPoint,Insert insert){
        BaseEntity entity = (BaseEntity) joinPoint.getArgs()[0];
        String realName = classTool.getRealName(entity.getClass());
        //insert into realName (a1,a2,a3)
        return null;
    }

    @Override
    public boolean deleteCheck(ProceedingJoinPoint joinPoint,Delete delete){
        if(!"".equals(delete.where())) return true;
        for(Object param : joinPoint.getArgs()) if(!(param instanceof BaseEntity)) return true;
        return false;
    }

    @Override
    public String deleteExplain(ProceedingJoinPoint joinPoint,Delete delete){
        return null;
    }

    @Override
    public boolean updateCheck(ProceedingJoinPoint joinPoint,Update update){
        return false;
    }

    @Override
    public String updateExplain(ProceedingJoinPoint joinPoint,Update update){
        return null;
    }

    @Override
    public boolean selectCheck(ProceedingJoinPoint joinPoint,Select select){
        return false;
    }

    @Override
    public String selectExplain(ProceedingJoinPoint joinPoint,Select select){
        return null;
    }
}