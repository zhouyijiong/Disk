package com.zyj.disk.sys.entity;

import com.zyj.disk.sys.annotation.verify.Access;
import com.zyj.disk.sys.annotation.verify.Level;
import com.zyj.disk.sys.annotation.verify.ParamsCheck;
import com.zyj.disk.sys.annotation.verify.parse.AccessParse;
import com.zyj.disk.sys.annotation.verify.parse.LevelParse;
import com.zyj.disk.sys.annotation.verify.parse.ParamsCheckParse;
import com.zyj.disk.sys.tool.AOPTool;
import com.zyj.disk.sys.tool.structure.HashPair;
import com.zyj.disk.sys.tool.structure.Pair;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
@RequiredArgsConstructor
public final class AnnotationParse {
    private final AOPTool aopTool;
    private static final Pair<Class<? extends Annotation>, Task> PAIR = new HashPair<>();

    static {
        PAIR.put(Access.class, AccessParse::parse);
        PAIR.put(Level.class, LevelParse::parse);
        PAIR.put(ParamsCheck.class, ParamsCheckParse::parse);
    }

    interface Task {
        Object run(Annotation annotation, ProceedingJoinPoint pjp, AOPTool aopTool);
    }

    public Object run(Class<? extends Annotation> clazz, Annotation annotation, ProceedingJoinPoint pjp) {
        return PAIR.get(clazz).run(annotation, pjp, aopTool);
    }
}