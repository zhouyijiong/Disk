package com.zyj.disk.sys.hikari.mapper.match;

import com.zyj.disk.sys.annotation.mapper.Delete;
import com.zyj.disk.sys.annotation.mapper.Insert;
import com.zyj.disk.sys.annotation.mapper.Select;
import com.zyj.disk.sys.annotation.mapper.Update;
import org.aspectj.lang.ProceedingJoinPoint;
import java.lang.annotation.Annotation;

/**
 * @Author: ZYJ
 * @Date: 2022/3/25 9:51
 * @Remark:  映射匹配模式解析接口
 */
public interface Match{
    boolean insertCheck(ProceedingJoinPoint joinPoint,Insert insert);
    String insertExplain(ProceedingJoinPoint joinPoint,Insert insert);

    boolean deleteCheck(ProceedingJoinPoint joinPoint,Delete delete);
    String deleteExplain(ProceedingJoinPoint joinPoint,Delete delete);

    boolean updateCheck(ProceedingJoinPoint joinPoint,Update update);
    String updateExplain(ProceedingJoinPoint joinPoint,Update update);

    boolean selectCheck(ProceedingJoinPoint joinPoint,Select select);
    String selectExplain(ProceedingJoinPoint joinPoint,Select select);

    default boolean check(ProceedingJoinPoint joinPoint,Annotation annotation){
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

    default String explain(ProceedingJoinPoint joinPoint,Annotation annotation){
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
}