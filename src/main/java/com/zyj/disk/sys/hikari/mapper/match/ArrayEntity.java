package com.zyj.disk.sys.hikari.mapper.match;

import com.zyj.disk.sys.annotation.mapper.base.*;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: ZYJ
 * @Date: 2022/3/23 17:57
 * @Remark: { 无参数; 遍历'Entity Array'数组,循环获取每项中' !=null 的参数 == 判断'; 返回一组SQL; }
 */
public final class ArrayEntity extends Match{
    public static final ArrayEntity MATCH = new ArrayEntity();

    @Override
    boolean insertCheck(ProceedingJoinPoint joinPoint,Insert insert){
        return false;
    }

    @Override
    public String insertExplain(ProceedingJoinPoint joinPoint,Insert insert){
        if(insertCheck(joinPoint,insert)) return null;
        return null;
    }

    @Override
    boolean deleteCheck(ProceedingJoinPoint joinPoint,Delete delete){
        return !(joinPoint.getArgs().length == 1);
    }

    @Override
    public String deleteExplain(ProceedingJoinPoint joinPoint,Delete delete){
        if(deleteCheck(joinPoint,delete)) return null;
        return null;
    }

    @Override
    boolean updateCheck(ProceedingJoinPoint joinPoint,Update update){
        return false;
    }

    @Override
    public String updateExplain(ProceedingJoinPoint joinPoint,Update update){
        if(updateCheck(joinPoint,update)) return null;
        return null;
    }

    @Override
    boolean selectCheck(ProceedingJoinPoint joinPoint,Select select){
        return false;
    }

    @Override
    public String selectExplain(ProceedingJoinPoint joinPoint,Select select){
        if(selectCheck(joinPoint,select)) return null;
        return null;
    }
}