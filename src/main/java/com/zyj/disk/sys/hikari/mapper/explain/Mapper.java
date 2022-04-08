package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.entity.Record;
import com.zyj.disk.sys.hikari.mapper.match.Match;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.sql.Connection;

/**
 * @Author: ZYJ
 * @Date: 2022/3/26 11:05
 * @Remark: 注解映射处理
 */
@Component
public abstract class Mapper{
    protected Match match;
    protected final Record record;
    protected final DataSource dataSource;

    public Mapper(DataSource dataSource,Class<? extends Mapper> clazz){
        this.dataSource = dataSource;
        this.record = Record.initialize(clazz);
    }

    abstract Match init(Annotation annotation);

    private boolean check(ProceedingJoinPoint joinPoint,Annotation annotation){
        match = init(annotation);
        return match.check(joinPoint,annotation);
    }

    String explain(ProceedingJoinPoint joinPoint,Annotation annotation){
        String sql = match.explain(joinPoint,annotation);
        System.out.println(sql);
        return check(joinPoint,annotation) ? null : sql;
    }

    public Object actuator(ProceedingJoinPoint joinPoint,Annotation annotation){
        try(Connection connection = dataSource.getConnection()){
            return connection.prepareStatement(explain(joinPoint,annotation)).executeUpdate();
        }catch(Exception e){
            record.error(e);
        }
        return 0;
    }
}