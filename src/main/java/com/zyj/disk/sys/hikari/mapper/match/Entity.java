package com.zyj.disk.sys.hikari.mapper.match;

import com.zyj.disk.sys.annotation.mapper.base.*;
import com.zyj.disk.sys.entity.BaseEntity;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: ZYJ
 * @Date: 2022/3/23 17:56
 * @Remark: { 无参数; 遍历'Entity'参数,取' !=null 的参数 == 判断'; 返回一条SQL; }
 */
public final class Entity extends Match{
    public static final Entity MATCH = new Entity();

    @Override
    public boolean insertCheck(ProceedingJoinPoint joinPoint,Insert insert){
        return false;
    }

    @Override
    public String insertExplain(ProceedingJoinPoint joinPoint,Insert insert){
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