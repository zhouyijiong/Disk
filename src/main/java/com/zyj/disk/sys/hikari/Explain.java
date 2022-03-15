package com.zyj.disk.sys.hikari;

import com.zyj.disk.sys.annotation.mapper.Delete;
import com.zyj.disk.sys.annotation.mapper.Insert;
import com.zyj.disk.sys.annotation.mapper.Select;
import com.zyj.disk.sys.annotation.mapper.Update;
import com.zyj.disk.sys.entity.BaseEntity;
import com.zyj.disk.sys.exception.GlobalException;
import com.zyj.disk.sys.exception.Use;
import com.zyj.disk.sys.tool.ClassTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.lang.reflect.Parameter;

/** 解释器 */
@Component
@RequiredArgsConstructor
public final class Explain{
    private final ClassTool classTool;

    public String getInsertSql(Insert insert,Object[] args)throws IllegalAccessException,NoSuchFieldException{
        if(args.length > 1) throw new GlobalException(Use.SQL_PARAM_REDUNDANT);
        BaseEntity[] entities = getEntityArray(args[0]);
        StringBuilder sql = new StringBuilder("insert into ").append(classTool.getRealName(entities[0].getClass()));
        String[] target = insert.target();
        if(target.length> 0){
            sql.append("(").append(String.join(",",target)).append(")");
        }else{
            //默认值不写
        }
        sql.append(" values(");
        sql.append(classTool.getAllFieldByEntities(entities,target));
        System.out.println(sql);
        return sql.toString();
    }

    public String getDeleteSql(Delete delete,Object[] args){
        return "null";
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
                where = replace(where,key,val);
            }
        }
        if(count == 0) return null;
        sb.append(" where ").append(where);
        if(!"".equals(select.limit())) sb.append(" limit ").append(select.limit());
        if(select.print()) System.out.println(sb);
        return sb.toString();
    }

    private static String replace(String str,String key,Object val){
        int start = 0,end = 0,tag = '#';
        for(int i=0,len=str.length();i<len;i++){
            if(str.charAt(i) == tag){
                if(start == 0) start = i;
                else end = i;
                tag = '}';
                if(end > start){
                    if(key.equals(str.substring(start + 2,end))){
                        tag = 0;
                        break;
                    }
                    start = 0; end = 0; tag = '#';
                }
            }
        }
        if(tag != 0) return str;
        String value = (val instanceof String) ? "'" + val + "'" : val.toString();
        return new StringBuilder(str).replace(start,end + 1,value).toString();
    }

    private BaseEntity[] getEntityArray(Object obj){
        return (obj instanceof BaseEntity) ? new BaseEntity[]{(BaseEntity)obj} : (BaseEntity[])obj;
    }
}