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
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public final class AnnotationParse {
    private static final Pair<Class<? extends Annotation>, Task> pair = new HashPair<>();

    static {
        pair.put(Access.class, AccessParse::parse);
        pair.put(Level.class, LevelParse::parse);
        pair.put(ParamsCheck.class, ParamsCheckParse::parse);
    }

    interface Task {
        Object run(Annotation annotation, ProceedingJoinPoint pjp, AOPTool aopTool);
    }

    public Object run(Class<? extends Annotation> clazz, Annotation annotation, ProceedingJoinPoint pjp, AOPTool aopTool) {
        return pair.get(clazz).run(annotation, pjp, aopTool);
    }
}