package com.zyj.disk.sys.aop;

import com.zyj.disk.sys.annotation.verify.Level;
import com.zyj.disk.sys.annotation.verify.ParamsCheck;
import com.zyj.disk.sys.annotation.verify.Access;
import com.zyj.disk.sys.entity.AnnotationParse;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public final class GlobalVerify {
    private final AnnotationParse annotationParse;

    @Around("@annotation(access)")
    public Object global(ProceedingJoinPoint pjp, Access access) {
        return annotationParse.run(Access.class, access, pjp);
    }

    @Around("@annotation(level)")
    public Object global(ProceedingJoinPoint pjp, Level level) {
        return annotationParse.run(Level.class, level, pjp);
    }

    @Around("@annotation(paramsCheck)")
    public Object global(ProceedingJoinPoint pjp, ParamsCheck paramsCheck) {
        return annotationParse.run(ParamsCheck.class, paramsCheck, pjp);
    }
}