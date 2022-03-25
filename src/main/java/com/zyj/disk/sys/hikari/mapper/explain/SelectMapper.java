package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.annotation.mapper.Select;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import java.lang.annotation.Annotation;

/**
 * @Author: ZYJ
 * @Date: 2022/3/25 9:44
 * @Remark: select mapper
 */
@Component
public final class SelectMapper implements Mapper {
    private Select select;

    @Override
    public boolean check(ProceedingJoinPoint joinPoint,Annotation annotation){
        if(!(annotation instanceof Select)) return true;
        select = (Select) annotation;
        return select.mapperMatch().MATCH.check(joinPoint,select);
    }

    @Override
    public String explain(ProceedingJoinPoint joinPoint,Annotation annotation){
        return check(joinPoint,annotation) ? select.mapperMatch().MATCH.explain(joinPoint,select) : null;
    }
}