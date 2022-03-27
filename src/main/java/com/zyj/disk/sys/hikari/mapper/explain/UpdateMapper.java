package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.annotation.mapper.Update;
import com.zyj.disk.sys.hikari.Actuator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import java.lang.annotation.Annotation;

/**
 * @Author: ZYJ
 * @Date: 2022/3/25 9:43
 * @Remark: update mapper
 */
@Component
public final class UpdateMapper extends Mapper{
    private Update update;

    public UpdateMapper(Actuator actuator){
        super(actuator);
    }

    @Override
    public boolean check(ProceedingJoinPoint joinPoint,Annotation annotation){
        update = (Update) annotation;
        return update.mapperMatch().MATCH.check(joinPoint,update);
    }

    @Override
    public String explain(ProceedingJoinPoint joinPoint,Annotation annotation){
        return check(joinPoint,annotation) ? update.mapperMatch().MATCH.explain(joinPoint,update) : null;
    }

    @Override
    public Object handle(ProceedingJoinPoint joinPoint,Annotation annotation){
        return actuator.update(explain(joinPoint,update));
    }
}