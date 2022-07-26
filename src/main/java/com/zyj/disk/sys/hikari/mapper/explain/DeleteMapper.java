package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.annotation.mapper.base.Delete;
import com.zyj.disk.sys.hikari.mapper.operate.DeleteOperate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;

/** delete mapper */
@Component
public final class DeleteMapper extends Mapper{
    public DeleteMapper(DataSource dataSource){
        super(dataSource,DeleteMapper.class);
    }

    @Override
    String explain(ProceedingJoinPoint joinPoint,Annotation annotation){
        Delete delete = (Delete) annotation;
        DeleteOperate operateMatch = delete.match().match;
        String sql = operateMatch.deleteExplain(joinPoint,delete);
        if(delete.print()) System.out.println(sql);
        return operateMatch.deleteCheck(joinPoint,delete) ? sql : null;
    }
}