package com.zyj.disk.sys.hikari.mapper.match;

import com.zyj.disk.sys.annotation.mapper.base.Insert;
import com.zyj.disk.sys.entity.BaseEntity;
import com.zyj.disk.sys.entity.Record;
import com.zyj.disk.sys.exception.User;
import com.zyj.disk.sys.hikari.mapper.operate.InsertOperate;
import org.aspectj.lang.ProceedingJoinPoint;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 匹配模式 : 数组实体类
 * 无SQL,循环匹配实体类
 */
public final class ArrayEntity implements Match,InsertOperate{
    public static final Record record = new Record(ArrayEntity.class);

    @Override
    public boolean insertCheck(ProceedingJoinPoint joinPoint,Insert insert){
        Object[] args = joinPoint.getArgs();
        if(args.length != 1) throw User.SQL_PARAM_REDUNDANT.exception;
        if(!(args[0] instanceof List)) throw User.SQL_PARAM_TYPE_ERROR.exception.addArgs("must be a List");
        return true;
    }

    @Override
    public String insertExplain(ProceedingJoinPoint joinPoint,Insert insert){
        List<BaseEntity> entityList = objToList(joinPoint.getArgs()[0]);
        Class<? extends BaseEntity> clazz = entityList.get(0).getClass();
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder sb = new StringBuilder(fields.length * 15 * entityList.size());
        boolean targetExists = insert.target().length > 0;
        sb.append("insert into ")
                .append(classTool.getRealName(clazz))
                .append(targetExists ? formatTarget(insert.target()) : classTool.getEntityFieldName(fields))
                .append("values");
        try{
            if(targetExists){
                sb.append(classTool.getEntityFieldValueByName(entityList.get(0),fields,insert.target()));
            }else{
                sb.append(classTool.getEntityValueADefault(entityList.get(0)));
            }
            if(entityList.size() == 1) return sb.toString();
            for(int i=1;i<entityList.size();++i){
                sb.append(",");
                if(targetExists){
                    sb.append(classTool.getEntityFieldValueByName(entityList.get(i),fields,insert.target()));
                }else{
                    sb.append(classTool.getEntityValueADefault(entityList.get(i)));
                }
            }
        }catch(InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e){
            record.error(e);
        }
        return sb.toString();
    }

    List<BaseEntity> objToList(Object obj){
        List<BaseEntity> result = new ArrayList<>();
        try{
            if(obj instanceof ArrayList<?>){
                for(Object item : (List<?>) obj){
                    result.add((BaseEntity) item);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}