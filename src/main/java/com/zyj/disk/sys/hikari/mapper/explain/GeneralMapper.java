package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.hikari.mapper.match.Match;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.annotation.Annotation;

public class GeneralMapper{
    String explain(ProceedingJoinPoint joinPoint,Annotation annotation,Match match){
        return match.explain(joinPoint,annotation);
    }
}