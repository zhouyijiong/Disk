package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.annotation.mapper.base.Insert;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;

/**
 * @Author: ZYJ
 * @Date: 2022/3/25 9:37
 * @Remark: insert mapper
 */
@Component
public class InsertMapper extends Mapper{
    private Insert insert;

    public InsertMapper(DataSource dataSource){
        super(dataSource,InsertMapper.class);
    }

    @Override
    public boolean check(ProceedingJoinPoint joinPoint,Annotation annotation){
        insert = (Insert) annotation;
        return insert.mapperMatch().MATCH.check(joinPoint,insert);
    }

    @Override
    public String explain(ProceedingJoinPoint joinPoint,Annotation annotation){
        if(check(joinPoint,annotation)) return null;
        String sql = insert.mapperMatch().MATCH.explain(joinPoint,insert);
        if(insert.print()) System.out.println(sql);
        return sql;
    }
}