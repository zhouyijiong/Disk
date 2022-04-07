package com.zyj.disk.sys.tool;

import com.zyj.disk.sys.entity.BaseEntity;
import com.zyj.disk.sys.hikari.Processor;
import org.springframework.stereotype.Component;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@Component
public final class ClassTool{
    public BaseEntity getEntityBySelect(String sql)
            throws ClassNotFoundException,InstantiationException,IllegalAccessException{
        int start = sql.indexOf(" from ") + 6;
        String realName = sql.substring(start,sql.indexOf(' ',start));
        return (BaseEntity) Class.forName(
                Processor.projectPath + realName.toLowerCase() + "." + realName + "Entity"
        ).newInstance();
    }

    public BaseEntity instance(Class<? extends BaseEntity> clazz)
            throws InstantiationException,IllegalAccessException{
        return clazz.newInstance();
    }

    public String getRealName(Class<? extends BaseEntity> clazz){
        String simpleName = clazz.getSimpleName();
        return simpleName.substring(0,simpleName.lastIndexOf('E')).toLowerCase();
    }

    public void setFieldValue(Field field,BaseEntity entity,Object val)throws IllegalAccessException{
        field.setAccessible(true);
        field.set(entity,val);
    }

    public Object getFieldValue(Field field,Object obj)throws IllegalAccessException{
        field.setAccessible(true);
        Object val = field.get(obj);
        return val == null ? null : (val instanceof String) ? "'" + val + "'" : val;
    }

    public String getFieldByName(BaseEntity entity,Field[] fields,String[] names)throws IllegalAccessException{
        StringBuilder sb = new StringBuilder();
        for(Field field : fields){
            for(String name : names){
                if(name.equals(field.getName())){
                    sb.append(getFieldValue(field,entity)).append(",");
                }
            }
        }
        return sb.toString();
    }

    public String getFieldByAnnotation(BaseEntity entity,Field[] fields,Class<? extends Annotation> clazz)throws IllegalAccessException{
        StringBuilder sb = new StringBuilder();
        for(Field field : fields){
            if(!field.isAnnotationPresent(clazz)){
                sb.append(getFieldValue(field,entity)).append(",");
            }
        }
        return sb.toString();
    }
}