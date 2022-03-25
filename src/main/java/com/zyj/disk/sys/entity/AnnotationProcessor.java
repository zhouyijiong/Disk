package com.zyj.disk.sys.entity;

import com.zyj.disk.sys.annotation.mapper.Delete;
import com.zyj.disk.sys.annotation.mapper.Insert;
import com.zyj.disk.sys.annotation.mapper.Select;
import com.zyj.disk.sys.annotation.mapper.Update;
import com.zyj.disk.sys.hikari.Actuator;
import com.zyj.disk.sys.hikari.Explain;
import com.zyj.disk.sys.hikari.mapper.explain.DeleteMapper;
import com.zyj.disk.sys.tool.AOPTool;
import com.zyj.disk.sys.tool.ClassTool;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class AnnotationProcessor{
    private final DeleteMapper deleteMapper;
    private final Explain explain;
    private final AOPTool aopTool;
    private final Actuator actuator;
    private final ClassTool classTool;
    private final Record record = Record.initialize(this.getClass());

    public int insert(ProceedingJoinPoint joinPoint,Insert insert){
        try{
            String sql = explain.getInsertSql(insert,joinPoint.getArgs());
            return sql == null ? 0 : actuator.insert(sql);
        }catch(Exception e){
            record.error(e);
        }
        return 0;
    }

    public int delete(ProceedingJoinPoint joinPoint,Delete delete){
        String sql = deleteMapper.explain(joinPoint,delete);
        return sql == null ? 0 : actuator.delete(sql);
    }

    public int update(ProceedingJoinPoint joinPoint,Update update){
        try{
            String sql = explain.getUpdateSql(joinPoint,update);
            return sql == null ? 0 : actuator.update(sql);
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }
        return 0;
    }

    public Object select(ProceedingJoinPoint joinPoint,Select select){
        try{
            String sql = explain.getSelectSql(select,joinPoint.getArgs(),aopTool.getMethod(joinPoint).getParameters());
            BaseEntity[] result = actuator.select(classTool.getEntity(select.result()),sql);
            if(result == null) return null;
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