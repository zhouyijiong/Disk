package com.zyj.disk.sys.hikari.mapper.match;

import com.zyj.disk.sys.annotation.mapper.base.*;
import com.zyj.disk.sys.tool.ClassTool;
import org.aspectj.lang.ProceedingJoinPoint;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Match{
    protected ClassTool classTool = new ClassTool();

    //升级单独的类，能够进行简单的时间管理
    private static final Map<String,String> container = new LinkedHashMap<>();

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
        String sql;
        String key = Arrays.toString(joinPoint.getArgs()) + annotation.toString();
        if((sql = container.get(key)) != null) return sql;
        if(annotation instanceof Insert){
            sql = insertExplain(joinPoint,(Insert)annotation);
            if( ((Insert)annotation).print() ) System.out.println(sql);
        }else if(annotation instanceof Delete){
            sql = deleteExplain(joinPoint,(Delete)annotation);
            if( ((Delete)annotation).print() ) System.out.println(sql);
        }else if(annotation instanceof Update){
            sql = updateExplain(joinPoint,(Update)annotation);
            if( ((Update)annotation).print() ) System.out.println(sql);
        }else if(annotation instanceof Select){
            sql = selectExplain(joinPoint,(Select)annotation);
            if( ((Select)annotation).print() ) System.out.println(sql);
        }
        if(sql != null) container.put(key,sql);
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