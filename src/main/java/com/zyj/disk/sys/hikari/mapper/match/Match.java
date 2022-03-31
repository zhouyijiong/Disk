package com.zyj.disk.sys.hikari.mapper.match;

import com.zyj.disk.sys.annotation.mapper.base.*;
import org.aspectj.lang.ProceedingJoinPoint;
import java.lang.annotation.Annotation;

public abstract class Match{
//    public boolean check(ProceedingJoinPoint joinPoint,Annotation annotation){
//        if(annotation instanceof Insert){
//            return insertCheck(joinPoint,(Insert) annotation);
//        }else if(annotation instanceof Delete){
//            return deleteCheck(joinPoint,(Delete) annotation);
//        }else if(annotation instanceof Update){
//            return updateCheck(joinPoint,(Update) annotation);
//        }else if(annotation instanceof Select){
//            return selectCheck(joinPoint,(Select) annotation);
//        }
//        return false;
//    }

    public String explain(ProceedingJoinPoint joinPoint,Annotation annotation){
        if(annotation instanceof Insert){
            return insertExplain(joinPoint,(Insert) annotation);
        }else if(annotation instanceof Delete){
            return deleteExplain(joinPoint,(Delete) annotation);
        }else if(annotation instanceof Update){
            return updateExplain(joinPoint,(Update) annotation);
        }else if(annotation instanceof Select){
            return selectExplain(joinPoint,(Select) annotation);
        }
        return null;
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