package com.zyj.disk.sys.hikari.mapper.match;

import com.zyj.disk.sys.annotation.mapper.base.Delete;
import com.zyj.disk.sys.annotation.mapper.base.Select;
import com.zyj.disk.sys.annotation.mapper.base.Update;
import com.zyj.disk.sys.hikari.mapper.operate.DeleteOperate;
import com.zyj.disk.sys.hikari.mapper.operate.SelectOperate;
import com.zyj.disk.sys.hikari.mapper.operate.UpdateOperate;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 匹配模式 : 参数
 * 通过 '#{}' 或 '#{*.*}' 进行参数匹配
 */
public final class Param implements Match,DeleteOperate,UpdateOperate,SelectOperate{
    public boolean deleteCheck(ProceedingJoinPoint joinPoint, Delete delete){
        return false;
    }

    public String deleteExplain(ProceedingJoinPoint joinPoint,Delete delete){
        return null;
    }

    public boolean selectCheck(ProceedingJoinPoint joinPoint, Select select){
        return false;
    }

    public String selectExplain(ProceedingJoinPoint joinPoint,Select select){
        return null;
    }

    public boolean updateCheck(ProceedingJoinPoint joinPoint, Update update){
        return false;
    }

    public String updateExplain(ProceedingJoinPoint joinPoint,Update update){
        return null;
    }
}