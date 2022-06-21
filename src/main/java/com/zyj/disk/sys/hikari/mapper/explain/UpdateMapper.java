package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.annotation.mapper.base.Update;
import com.zyj.disk.sys.hikari.mapper.operate.UpdateOperate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;

/** update mapper */
@Component
public final class UpdateMapper extends Mapper{
    public UpdateMapper(DataSource dataSource){
        super(dataSource,UpdateMapper.class);
    }

    @Override
    String explain(ProceedingJoinPoint joinPoint,Annotation annotation){
        Update update = (Update) annotation;
        UpdateOperate operateMatch = update.match().match;
        String sql = operateMatch.updateExplain(joinPoint,update);
        if(update.print()) System.out.println(sql);
        return operateMatch.updateCheck(joinPoint,update) ? sql : null;
    }
}