package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.annotation.mapper.Delete;
import com.zyj.disk.sys.hikari.mapper.Mapper;
import org.aspectj.lang.ProceedingJoinPoint;
import java.lang.annotation.Annotation;

/**
 * @Author: ZYJ
 * @Date: 2022/3/23 11:20
 * @Remark: { 无参数; 不匹配; 不能传参; 返回一条SQL; }
 */
public final class No implements Mapper {
    public static final No MATCH = new No();

//    @Override
//    boolean check(ProceedingJoinPoint joinPoint, Integer integer){
//        return false;
//    }
//
//    @Override
//    boolean check(ProceedingJoinPoint joinPoint,Delete delete){
//        return joinPoint.getArgs().length != 0 || !"".equals(delete.where());
//    }

    @Override
    public boolean check(ProceedingJoinPoint joinPoint,Annotation annotation){

        Delete delete = (Delete) annotation;
        return joinPoint.getArgs().length != 0 || !"".equals(delete.where());
    }

    @Override
    public String explain(ProceedingJoinPoint joinPoint,Annotation annotation){
        return null;
    }
}