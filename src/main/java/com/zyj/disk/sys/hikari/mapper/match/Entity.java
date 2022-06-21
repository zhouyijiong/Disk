package com.zyj.disk.sys.hikari.mapper.match;

import com.zyj.disk.sys.annotation.mapper.base.*;
import com.zyj.disk.sys.entity.BaseEntity;
import com.zyj.disk.sys.entity.Record;
import com.zyj.disk.sys.exception.User;
import com.zyj.disk.sys.hikari.mapper.operate.*;
import org.aspectj.lang.ProceedingJoinPoint;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 匹配模式 : 实体类
 * 无SQL,实体类字段匹配
 */
public final class Entity implements Match,InsertOperate,DeleteOperate,UpdateOperate,SelectOperate{
    public static final Record record = new Record(Entity.class);

    public boolean insertCheck(ProceedingJoinPoint joinPoint,Insert insert){
        return checkEntity(joinPoint);
    }

    public String insertExplain(ProceedingJoinPoint joinPoint,Insert insert){
        BaseEntity entity = (BaseEntity) joinPoint.getArgs()[0];
        Class<? extends BaseEntity> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder sb = new StringBuilder(fields.length * 15);
        boolean targetExists = insert.target().length > 0;
        sb.append("insert into ")
                .append(classTool.getRealName(clazz))
                .append(targetExists ? formatTarget(insert.target()) : classTool.getEntityFieldName(fields))
                .append("values");
        try{
            if(targetExists){
                sb.append(classTool.getEntityFieldValueByName(entity,fields,insert.target()));
            }else{
                sb.append(classTool.getEntityValueADefault(entity));
            }
        }catch(InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e){
            record.error(e);
        }
        return sb.toString();
    }

    public boolean deleteCheck(ProceedingJoinPoint joinPoint,Delete delete){
        if(!"".equals(delete.where())) throw User.SQL_PARAM_REDUNDANT.exception;
        return checkEntity(joinPoint);
    }

    public String deleteExplain(ProceedingJoinPoint joinPoint,Delete delete){
        BaseEntity entity = (BaseEntity) joinPoint.getArgs()[0];
        Class<? extends BaseEntity> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder sb = new StringBuilder(fields.length * 15);
        sb.append("delete from ")
                .append(classTool.getRealName(clazz))
                .append(" where 1=1");
        try{
            sb.append(classTool.getEntityFieldValueAFieldName(entity,fields));
        }catch(IllegalAccessException e){
            record.error(e);
        }
        return sb.toString();
    }

    public boolean selectCheck(ProceedingJoinPoint joinPoint,Select select){
        return checkEntity(joinPoint);
    }

    public String selectExplain(ProceedingJoinPoint joinPoint,Select select){

        return null;
    }

    public boolean updateCheck(ProceedingJoinPoint joinPoint, Update update){
        return checkEntity(joinPoint);
    }

    public String updateExplain(ProceedingJoinPoint joinPoint,Update update){
        return null;
    }

    boolean checkEntity(ProceedingJoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        if(args.length != 1) throw User.SQL_PARAM_REDUNDANT.exception;
        if(!(args[0] instanceof BaseEntity)) throw User.SQL_PARAM_TYPE_ERROR.exception.addArgs("must be a BaseEntity");
        return true;
    }
}