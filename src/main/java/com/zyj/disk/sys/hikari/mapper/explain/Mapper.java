package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.entity.Record;
import com.zyj.disk.sys.hikari.mapper.match.Match;
import org.aspectj.lang.ProceedingJoinPoint;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.sql.Connection;

/** 注解映射处理 */
public abstract class Mapper{
    protected Match match;
    protected final Record record;
    protected final DataSource dataSource;

    public Mapper(DataSource dataSource,Class<? extends Mapper> clazz){
        this.dataSource = dataSource;
        this.record = new Record(clazz);
    }

    abstract Match init(Annotation annotation);

    String explain(ProceedingJoinPoint joinPoint,Annotation annotation){
        match = init(annotation);
        if(match.check(joinPoint,annotation)) return null;
        return match.explain(joinPoint,annotation);
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