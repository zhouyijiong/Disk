package com.zyj.disk.sys.hikari.mapper;

import com.zyj.disk.sys.annotation.mapper.Delete;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: ZYJ
 * @Date: 2022/3/23 17:57
 * @Remark: { 无参数; 遍历'Entity Array'数组,循环获取每项中' !=null 的参数 == 判断'; 返回一组SQL; }
 */
public final class ArrayEntity extends MapperMatchSuper{
    public static final ArrayEntity MATCH = new ArrayEntity();

    @Override
    boolean check(ProceedingJoinPoint joinPoint, Integer integer) {
        return false;
    }

    @Override
    boolean check(ProceedingJoinPoint joinPoint,Delete delete){
        return !(joinPoint.getArgs().length == 1);
    }
}