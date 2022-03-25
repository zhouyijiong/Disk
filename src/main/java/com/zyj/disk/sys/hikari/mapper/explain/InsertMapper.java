package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.annotation.mapper.Insert;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import java.lang.annotation.Annotation;

/**
 * @Author: ZYJ
 * @Date: 2022/3/25 9:37
 * @Remark: insert mapper
 */
@Component
public class InsertMapper implements Mapper{
    private Insert insert;

    @Override
    public boolean check(ProceedingJoinPoint joinPoint,Annotation annotation){
        if(!(annotation instanceof Insert)) return true;
        insert = (Insert) annotation;
        return insert.mapperMatch().MATCH.check(joinPoint,insert);
    }

    @Override
    public String explain(ProceedingJoinPoint joinPoint,Annotation annotation){
        return check(joinPoint,annotation) ? insert.mapperMatch().MATCH.explain(joinPoint,insert) : null;
    }
}