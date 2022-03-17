package com.zyj.disk.sys.hikari;

import com.zyj.disk.sys.annotation.mapper.Delete;
import com.zyj.disk.sys.annotation.mapper.Insert;
import com.zyj.disk.sys.annotation.mapper.Mark;
import com.zyj.disk.sys.annotation.mapper.Select;
import com.zyj.disk.sys.annotation.mapper.Update;
import com.zyj.disk.sys.entity.BaseEntity;
import com.zyj.disk.sys.exception.GlobalException;
import com.zyj.disk.sys.exception.User;
import com.zyj.disk.sys.tool.AOPTool;
import com.zyj.disk.sys.tool.ClassTool;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

/** 解释器 */
@Component
@RequiredArgsConstructor
public final class Explain{
    private final ClassTool classTool;
    private final AOPTool aopTool;

    public String getInsertSql(Insert insert,Object[] args)
            throws IllegalAccessException{
        if(args.length > 1) throw new GlobalException(User.SQL_PARAM_REDUNDANT);
        BaseEntity[] entities = getEntityArray(args[0]);
        BaseEntity entity = entities[0];
        Class<? extends BaseEntity> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder sql = new StringBuilder("insert into ").append(classTool.getRealName(clazz));
        String[] target = insert.target();
        boolean key = target.length > 0;
        if(key) sql.append("(").append(String.join(",",target)).append(")");
        sql.append(" values(");
        sql.append(key ? classTool.getFieldByName(entity,fields,target)
                : classTool.getFieldByAnnotation(entity,fields,Mark.class));
        sql.delete(sql.length() - 1,sql.length()).append(")");
        System.out.println(sql);
        return sql.toString();
    }

    public String getDeleteSql(Delete delete,ProceedingJoinPoint joinPoint)throws IllegalAccessException{
        Object[] args = joinPoint.getArgs();
        String where = delete.where();
        Class<? extends BaseEntity> clazz = delete.operate();
        StringBuilder sql = new StringBuilder("delete from ");
        sql.append(classTool.getRealName(clazz));
        switch(args.length){
            case 0: break;
            case 1:
                sql.append(" where 1=1");
                BaseEntity entity = (BaseEntity)args[0];
                Field[] fields = clazz.getDeclaredFields();
                for(Field field : fields){
                    Object val = classTool.getFieldValue(field,entity);
                    if(val == null) continue;
                    sql.append(" AND ").append(field.getName()).append("=").append(val);
                }
                break;
            default:
                Parameter[] parameters = aopTool.getMethod(joinPoint).getParameters();
                for(int i=0;i<args.length;i++){
                    String key = parameters[i].getName();
                    Object val = args[i];
                    if(val != null) where = replace(where,key,val);
                }
                sql.append(" where ").append(where);
        }
        if(delete.print()) System.out.println(sql);
        return sql.toString();
    }

    public String getUpdateSql(Update update,Object[] args){
        return "null";
    }

    public String getSelectSql(Select select,Object[] args,Parameter[] parameters){
        String simpleName = select.result().getSimpleName();
        int count = 0;
        String where = select.where();
        StringBuilder sb = new StringBuilder("select ").append(select.query()).append(" from ").append(simpleName,0,simpleName.lastIndexOf('E'));
        for(int i=0;i<args.length;i++){
            Parameter parameter = parameters[i];
            String key = parameter.getName();
            Object val = args[i];
            if(val != null){
                ++count;
                replace(where,key,val);
            }
        }
        if(count == 0) return null;
        sb.append(" where ").append(where);
        if(!"".equals(select.limit())) sb.append(" limit ").append(select.limit());
        if(select.print()) System.out.println(sb);
        return sb.toString();
    }

    public static String replace(String str,String key,Object val){
        key = "#{" + key;
        int keyIndex = str.indexOf(key);
        if(keyIndex == -1) throw new GlobalException(User.SQL_PARAM_NOT_EXIST);
        StringBuilder sb = new StringBuilder(str);
        String value = (val instanceof String) ? "'" + val + "'" : val.toString();
        return sb.replace(keyIndex,keyIndex + key.length() + 1,value).toString();
    }

    private BaseEntity[] getEntityArray(Object obj){
        return (obj instanceof BaseEntity) ? new BaseEntity[]{(BaseEntity)obj} : (BaseEntity[])obj;
    }
}