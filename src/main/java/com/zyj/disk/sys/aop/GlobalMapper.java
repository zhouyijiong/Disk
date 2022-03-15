package com.zyj.disk.sys.aop;

import com.zyj.disk.sys.entity.AnnotationProcessor;
import com.zyj.disk.sys.annotation.mapper.Delete;
import com.zyj.disk.sys.annotation.mapper.Insert;
import com.zyj.disk.sys.annotation.mapper.Select;
import com.zyj.disk.sys.annotation.mapper.Update;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public final class GlobalMapper{
    private final AnnotationProcessor annotationProcessor;

    @Around("@annotation(insert)")
    public int insert(ProceedingJoinPoint joinPoint,Insert insert){
        return annotationProcessor.insert(joinPoint,insert);
    }

    @Around("@annotation(delete)")
    public int delete(ProceedingJoinPoint joinPoint,Delete delete){
        return annotationProcessor.delete(joinPoint,delete);
    }

    @Around("@annotation(update)")
    public int update(ProceedingJoinPoint joinPoint,Update update){
        return annotationProcessor.update(joinPoint,update);
    }

    @Around("@annotation(select)")
    public Object select(ProceedingJoinPoint joinPoint,Select select){
        return annotationProcessor.select(joinPoint,select);
    }
}