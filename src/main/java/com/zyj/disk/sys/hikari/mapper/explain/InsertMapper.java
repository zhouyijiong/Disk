package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.annotation.mapper.base.Insert;
import com.zyj.disk.sys.hikari.mapper.operate.InsertOperate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;

/** insert mapper */
@Component
public final class InsertMapper extends Mapper{
    public InsertMapper(DataSource dataSource){
        super(dataSource,InsertMapper.class);
    }

    @Override
    String explain(ProceedingJoinPoint joinPoint,Annotation annotation){
        Insert insert = (Insert) annotation;
        InsertOperate operateMatch = insert.match().match;
        String sql = operateMatch.insertExplain(joinPoint,insert);
        if(insert.print()) System.out.println(sql);
        return operateMatch.insertCheck(joinPoint,insert) ? sql : null;
    }
}