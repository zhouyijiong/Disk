package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.entity.Record;
import org.aspectj.lang.ProceedingJoinPoint;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.sql.Connection;

/** 注解映射处理 */
public abstract class Mapper{
    protected final Record record;
    protected final DataSource dataSource;

    public Mapper(DataSource dataSource,Class<? extends Mapper> clazz){
        this.dataSource = dataSource;
        this.record = new Record(clazz);
    }

    abstract String explain(ProceedingJoinPoint joinPoint,Annotation annotation);

    public Object actuator(ProceedingJoinPoint joinPoint,Annotation annotation){
        try(Connection connection = dataSource.getConnection()){
            return connection.prepareStatement(explain(joinPoint,annotation)).executeUpdate();
        }catch(Exception e){
            record.error(e);
        }
        return 0;
    }
}