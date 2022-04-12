package com.zyj.disk.sys.hikari.mapper.match;

import com.zyj.disk.sys.annotation.mapper.base.*;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: ZYJ
 * @Date: 2022/3/23 11:20
 * @Remark: { 无参数; 不匹配; 返回一条SQL; }
 */
public final class No extends Match{
    @Override
    public boolean insertCheck(ProceedingJoinPoint joinPoint,Insert insert){
        return true;
    }

    @Override
    public String insertExplain(ProceedingJoinPoint joinPoint,Insert insert){
        return null;
    }

    @Override
    public boolean deleteCheck(ProceedingJoinPoint joinPoint,Delete delete){
        return joinPoint.getArgs().length != 0;
    }

    @Override
    public String deleteExplain(ProceedingJoinPoint joinPoint,Delete delete){
        return "delete from " + classTool.getRealName(delete.operate());
    }

    @Override
    public boolean updateCheck(ProceedingJoinPoint joinPoint,Update update){
        return true;
    }

    @Override
    public String updateExplain(ProceedingJoinPoint joinPoint,Update update){
        return null;
    }

    @Override
    public boolean selectCheck(ProceedingJoinPoint joinPoint,Select select){
        return joinPoint.getArgs().length != 0;
    }

    @Override
    public String selectExplain(ProceedingJoinPoint joinPoint,Select select){
        return "select " + select.query()
                + " from " + classTool.getRealName(select.result())
                + select.where() + select.limit();
    }
}