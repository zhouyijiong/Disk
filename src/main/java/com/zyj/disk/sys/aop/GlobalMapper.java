package com.zyj.disk.sys.aop;

import com.zyj.disk.sys.annotation.mapper.base.*;
import com.zyj.disk.sys.hikari.mapper.explain.*;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public final class GlobalMapper{
    private final InsertMapper insertMapper;
    private final DeleteMapper deleteMapper;
    private final UpdateMapper updateMapper;
    private final SelectMapper selectMapper;

    @Around("@annotation(insert)")
    public int insert(ProceedingJoinPoint joinPoint,Insert insert){
        return toInt(insertMapper.actuator(joinPoint,insert));
    }

    @Around("@annotation(delete)")
    public int delete(ProceedingJoinPoint joinPoint,Delete delete){
        return toInt(deleteMapper.actuator(joinPoint,delete));
    }

    @Around("@annotation(update)")
    public int update(ProceedingJoinPoint joinPoint,Update update){
        return toInt(updateMapper.actuator(joinPoint,update));
    }

    @Around("@annotation(select)")
    public Object select(ProceedingJoinPoint joinPoint,Select select){
        return selectMapper.actuator(joinPoint,select);
    }

    private int toInt(Object val){
        return val == null ? 0 : Integer.parseInt(val.toString());
    }
}