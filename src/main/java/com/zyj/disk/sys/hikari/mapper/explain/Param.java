package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.annotation.mapper.Delete;
import com.zyj.disk.sys.annotation.mapper.Insert;
import com.zyj.disk.sys.annotation.mapper.Select;
import com.zyj.disk.sys.annotation.mapper.Update;
import com.zyj.disk.sys.hikari.mapper.Mapper;
import com.zyj.disk.sys.hikari.mapper.MapperMatchSuper;
import com.zyj.disk.sys.tool.AOPTool;
import jdk.internal.org.objectweb.asm.commons.Method;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.data.relational.core.sql.In;

import java.lang.annotation.Annotation;

/**
 * @Author: ZYJ
 * @Date: 2022/3/23 17:50
 * @Remark: { 有参数; 通过'#{}'对普通参数进行匹配 或 通过 ${*.*} 对 'Entity' 参数匹配; 返回一条SQL; }
 */
@RequiredArgsConstructor
public final class Param implements Mapper{
    private final AOPTool aopTool;
    public static final Param MATCH = new Param();

//    @Override
//    boolean check(ProceedingJoinPoint joinPoint, Integer integer){
//        return false;
//    }
//
//    @Override
//    boolean check(ProceedingJoinPoint joinPoint,Delete delete){
//        return "".equals(delete.where());
//    }

    @Override
    public boolean check(ProceedingJoinPoint joinPoint,Annotation annotation){
        if(annotation instanceof Insert){
            Insert insert = (Insert) annotation;

        }else if(annotation instanceof Delete){
            Delete delete = (Delete) annotation;
            return "".equals(delete.where());
        }else if(annotation instanceof Update){
            Update update = (Update) annotation;
            return "".equals(update.where());
        }else if(annotation instanceof Select){
            Select select = (Select) annotation;

        }
        aopTool.getMethod(joinPoint).isAnnotationPresent(annotation.getClass());
        return "".equals(delete.where());
    }

    @Override
    public String explain(ProceedingJoinPoint joinPoint, Annotation annotation) {
        return null;
    }
}