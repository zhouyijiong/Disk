package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.annotation.mapper.Update;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import java.lang.annotation.Annotation;

/**
 * @Author: ZYJ
 * @Date: 2022/3/25 9:43
 * @Remark: update mapper
 */
@Component
public final class UpdateMapper implements Mapper{
    private Update update;

    @Override
    public boolean check(ProceedingJoinPoint joinPoint,Annotation annotation){
        if(!(annotation instanceof Update)) return true;
        update = (Update) annotation;
        return update.mapperMatch().MATCH.check(joinPoint,update);
    }

    @Override
    public String explain(ProceedingJoinPoint joinPoint,Annotation annotation){
        return check(joinPoint,annotation) ? update.mapperMatch().MATCH.explain(joinPoint,update) : null;
    }
}