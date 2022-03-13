package com.zyj.disk.sys.tool;

import com.zyj.disk.sys.entity.BaseEntity;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;

@Component
public final class ClassTool{
    public BaseEntity getEntity(Class<? extends BaseEntity> clazz)throws InstantiationException,IllegalAccessException{
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

    public String getAllFieldByEntities(BaseEntity[] entities,String[] data)throws IllegalAccessException,NoSuchFieldException{//TODO 拆分优化
        StringBuilder sb = new StringBuilder();
        for(BaseEntity entity : entities){
            boolean key = data.length == 0;
            Field[] fields = entity.getClass().getDeclaredFields();
            Class<? extends BaseEntity> clazz = entity.getClass();
            int size = key ? fields.length : data.length;
            sb.append(key?getFieldValue(fields[0],entity) :
                    getFieldValue(clazz.getDeclaredField(data[0]),entity));
            for(int i=1;i<size;i++){
                Field field = key ? fields[i] : clazz.getDeclaredField(data[i]);
                sb.append(",").append(getFieldValue(field,entity));
            }
            sb.append("),(");
        }
        return sb.delete(sb.length() - 2,sb.length()).toString();
    }
}