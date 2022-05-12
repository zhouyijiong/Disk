package com.zyj.disk.sys.hikari.mapper.match;

import com.zyj.disk.sys.annotation.mapper.base.*;
import com.zyj.disk.sys.tool.ClassTool;
import com.zyj.disk.sys.tool.structure.ResponsiveCache;
import org.aspectj.lang.ProceedingJoinPoint;
import java.lang.annotation.Annotation;
import java.util.Arrays;

/** 映射匹配调度类 */
public abstract class Match{
    protected ClassTool classTool = new ClassTool();

    private static final ResponsiveCache<String,String> sqlCache = new ResponsiveCache<>(3000,60);

    public boolean check(ProceedingJoinPoint joinPoint,Annotation annotation){
        if(annotation instanceof Insert){
            return insertCheck(joinPoint,(Insert) annotation);
        }else if(annotation instanceof Delete){
            return deleteCheck(joinPoint,(Delete) annotation);
        }else if(annotation instanceof Update){
            return updateCheck(joinPoint,(Update) annotation);
        }else if(annotation instanceof Select){
            return selectCheck(joinPoint,(Select) annotation);
        }
        return false;
    }

    public String explain(ProceedingJoinPoint joinPoint,Annotation annotation){
        String key = Arrays.toString(joinPoint.getArgs()) + annotation;
        String sql;
        if((sql = sqlCache.get(key)) != null) return sql;
        if(annotation instanceof Insert){
            sql = insertExplain(joinPoint,(Insert) annotation);
        }else if(annotation instanceof Delete){
            sql = deleteExplain(joinPoint,(Delete) annotation);
        }else if(annotation instanceof Update){
            sql = updateExplain(joinPoint,(Update) annotation);
        }else if(annotation instanceof Select){
            sql = selectExplain(joinPoint,(Select) annotation);
        }
        if(sql != null) sqlCache.put(key,sql,90);
        return sql;
    }

    abstract boolean insertCheck(ProceedingJoinPoint joinPoint, Insert insert);
    abstract String insertExplain(ProceedingJoinPoint joinPoint,Insert insert);

    abstract boolean deleteCheck(ProceedingJoinPoint joinPoint, Delete delete);
    abstract String deleteExplain(ProceedingJoinPoint joinPoint,Delete delete);

    abstract boolean updateCheck(ProceedingJoinPoint joinPoint, Update update);
    abstract String updateExplain(ProceedingJoinPoint joinPoint,Update update);

    abstract boolean selectCheck(ProceedingJoinPoint joinPoint, Select select);
    abstract String selectExplain(ProceedingJoinPoint joinPoint,Select select);
}