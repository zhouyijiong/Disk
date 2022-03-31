package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.annotation.mapper.base.Select;
import com.zyj.disk.sys.entity.BaseEntity;
import com.zyj.disk.sys.hikari.Processor;
import com.zyj.disk.sys.hikari.mapper.match.Match;
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
    private final ClassTool classTool;
    private final Processor processor;

    public SelectMapper(DataSource dataSource,ClassTool classTool,Processor processor){
        super(dataSource,SelectMapper.class);
        this.classTool = classTool;
        this.processor = processor;
    }

    @Override
    Match init(Annotation annotation){
        return ((Select) annotation).mapperMatch().match;
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