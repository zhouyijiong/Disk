package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.annotation.mapper.base.Select;
import com.zyj.disk.sys.entity.BaseEntity;
import com.zyj.disk.sys.hikari.Processor;
import com.zyj.disk.sys.tool.ClassTool;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.sql.Connection;

/**
 * @Author: ZYJ
 * @Date: 2022/3/25 9:44
 * @Remark: select mapper
 */
@Component
public final class SelectMapper extends Mapper{
    private Select select;
    private final ClassTool classTool;
    private final Processor processor;

    public SelectMapper(DataSource dataSource,ClassTool classTool,Processor processor){
        super(dataSource,SelectMapper.class);
        this.classTool = classTool;
        this.processor = processor;
    }

    @Override
    public boolean check(ProceedingJoinPoint joinPoint,Annotation annotation){
        select = (Select) annotation;
        return select.mapperMatch().MATCH.check(joinPoint,select);
    }

    @Override
    public String explain(ProceedingJoinPoint joinPoint,Annotation annotation){
        if(check(joinPoint,annotation)) return null;
        String sql = select.mapperMatch().MATCH.explain(joinPoint,select);
        if(select.print()) System.out.println(sql);
        return sql;
    }

    @Override
    public Object actuator(ProceedingJoinPoint joinPoint,Annotation annotation){
        String sql = explain(joinPoint,annotation);
        try(Connection connection = dataSource.getConnection()){
            BaseEntity[] result= processor.select(
                    connection.prepareStatement(sql).executeQuery(),
                    classTool.getEntityBySelect(sql)
            );
            switch(result.length){
                case 0 : return null;
                case 1 : return result[0];
                default: return result;
            }
        }catch(Exception e){
            record.error(e);
        }
        return null;
    }
}