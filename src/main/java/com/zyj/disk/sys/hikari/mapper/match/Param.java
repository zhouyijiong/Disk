package com.zyj.disk.sys.hikari.mapper.match;

import com.zyj.disk.sys.annotation.mapper.base.*;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: ZYJ
 * @Date: 2022/3/23 17:50
 * @Remark: { 有参数; 通过'#{}'对普通参数进行匹配 或 通过 ${*.*} 对 'Entity' 参数匹配; 返回一条SQL; }
 */
public final class Param extends Match{
    public static final Match MATCH = new Param();

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
        Object[] args = joinPoint.getArgs();

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