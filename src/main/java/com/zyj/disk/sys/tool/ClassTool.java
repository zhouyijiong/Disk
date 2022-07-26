package com.zyj.disk.sys.tool;

import com.zyj.disk.sys.entity.BaseEntity;
import org.springframework.stereotype.Component;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Component
public final class ClassTool{
    public BaseEntity instance(Class<? extends BaseEntity> clazz) throws InstantiationException,IllegalAccessException{
        return clazz.newInstance();
    }

    public String getRealName(Class<? extends BaseEntity> clazz){
        String simpleName = clazz.getSimpleName();
        return simpleName.substring(0,simpleName.lastIndexOf('E')).toLowerCase();
    }

    public void setFieldValue(Field field,BaseEntity entity,Object val) throws IllegalAccessException{
        field.setAccessible(true);
        field.set(entity,val);
    }

    public Object getFieldValue(Field field,Object obj)throws IllegalAccessException{
        field.setAccessible(true);
        Object val = field.get(obj);
        return val == null ? null : (val instanceof String) ? "'" + val + "'" : val;
    }

    public String getEntityFieldValueByName(BaseEntity entity,Field[] fields,String[] names) throws IllegalAccessException{
        int len = names.length;
        StringBuilder sb = new StringBuilder(len * 15);
        sb.append("(");
        for(String fieldName : names){
            for(Field field : fields){
                if(fieldName.equals(field.getName())){
                    sb.append(getFieldValue(field,entity));
                    if(--len > 0) sb.append(",");
                }
            }
        }
        return sb.append(")").toString();
    }

    public String getEntityFieldValueAFieldName(BaseEntity entity,Field[] fields) throws IllegalAccessException{
        StringBuilder sb = new StringBuilder(fields.length * 15);
        for(Field field : fields){
            Object val = getFieldValue(field,entity);
            if(val == null) continue;
            String name = field.getName();
            sb.append(" and ").append(name).append("=").append(val);
        }
        return sb.toString();
    }

    public String getEntityFieldName(Field[] fields){
        if(fields.length == 1) return "(" + fields[0].getName() + ")";
        StringBuilder sb = new StringBuilder(fields.length * 11);
        sb.append("(").append(fields[0].getName());
        for(int i=1;i<fields.length;++i) sb.append(",").append(fields[i].getName());
        return sb.append(")").toString();
    }

    public String getEntityValueADefault(BaseEntity entity) throws InstantiationException,
            IllegalAccessException,NoSuchMethodException,InvocationTargetException{
        Class<? extends BaseEntity> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        BaseEntity defaultEntity = (BaseEntity) clazz.getDeclaredMethod("defaultArgs").invoke(null);
        Field[] defaultFields = defaultEntity.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder(fields.length * 5);
        Object value = getFieldValue(fields[0],entity);
        sb.append("(").append(value == null ? getFieldValue(defaultFields[0],defaultEntity) : value);
        if(fields.length == 1) return sb.append(")").toString();
        for(int i=1;i<fields.length;++i){
            Object val = getFieldValue(fields[i],entity);
            sb.append(",").append(val == null ? getFieldValue(defaultFields[i],defaultEntity) : val);
        }
        return sb.append(")").toString();
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