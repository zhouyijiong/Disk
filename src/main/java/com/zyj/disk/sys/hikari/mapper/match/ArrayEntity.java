package com.zyj.disk.sys.hikari.mapper.match;

import com.zyj.disk.sys.annotation.mapper.base.*;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 匹配模式 : 数组实体类
 * 无SQL,循环匹配实体类
 */
public final class ArrayEntity extends Match{
    @Override
    boolean insertCheck(ProceedingJoinPoint joinPoint,Insert insert){
        return false;
    }

    @Override
    public String insertExplain(ProceedingJoinPoint joinPoint,Insert insert){
        return null;
    }

    @Override
    boolean deleteCheck(ProceedingJoinPoint joinPoint,Delete delete){
        return !(joinPoint.getArgs().length == 1);
    }

    @Override
    public String deleteExplain(ProceedingJoinPoint joinPoint,Delete delete){
        return null;
    }

    @Override
    boolean updateCheck(ProceedingJoinPoint joinPoint,Update update){
        return false;
    }

    @Override
    public String updateExplain(ProceedingJoinPoint joinPoint,Update update){
        return null;
    }

    @Override
    boolean selectCheck(ProceedingJoinPoint joinPoint,Select select){
        return false;
    }

    @Override
    public String selectExplain(ProceedingJoinPoint joinPoint,Select select){
        return null;
    }
}