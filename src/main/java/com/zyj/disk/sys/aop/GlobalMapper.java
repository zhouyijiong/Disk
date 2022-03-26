package com.zyj.disk.sys.aop;

import com.zyj.disk.sys.annotation.mapper.Delete;
import com.zyj.disk.sys.annotation.mapper.Insert;
import com.zyj.disk.sys.annotation.mapper.Select;
import com.zyj.disk.sys.annotation.mapper.Update;
import com.zyj.disk.sys.hikari.mapper.explain.Mapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public final class GlobalMapper{
    private final Mapper mapper;

    @Around("@annotation(insert)")
    public int insert(ProceedingJoinPoint joinPoint,Insert insert){
        return toInt(mapper.handle(joinPoint,insert));
    }

    @Around("@annotation(delete)")
    public int delete(ProceedingJoinPoint joinPoint,Delete delete){
        return toInt(mapper.handle(joinPoint,delete));
    }

    @Around("@annotation(update)")
    public int update(ProceedingJoinPoint joinPoint,Update update){
        return toInt(mapper.handle(joinPoint,update));
    }

    @Around("@annotation(select)")
    public Object select(ProceedingJoinPoint joinPoint,Select select){
        return mapper.handle(joinPoint,select);
    }

    private int toInt(Object val){
        return val == null ? 0 : Integer.parseInt(val.toString());
    }
}