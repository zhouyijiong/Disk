package com.zyj.disk.sys.hikari;

import com.zyj.disk.sys.entity.BaseEntity;
import com.zyj.disk.sys.tool.ClassTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/** 处理器 */
@Component
@RequiredArgsConstructor
public final class Processor{
    private final ClassTool classTool;
    public static String projectPath;

    static{
        String className = Processor.class.getName();
        Processor.projectPath = className.substring(0,className.lastIndexOf(".sys.") + 1);
    }

    public BaseEntity[] select(ResultSet result,BaseEntity entity)
            throws SQLException,NoSuchFieldException,IllegalAccessException{
        Class<? extends BaseEntity> clazz = entity.getClass();
        BaseEntity[] data = new BaseEntity[getRows(result)];
        while(result.next()){
            for(int i=0;i<data.length;i++){
                ResultSetMetaData metaData = result.getMetaData();
                for(int j=1;j<metaData.getColumnCount() + 1;j++){
                    Field field = clazz.getDeclaredField(metaData.getColumnName(j));
                    classTool.setFieldValue(field,entity,result.getObject(j));
                }
                data[i] = entity;
            }
        }
        return data;
    }

    private int getRows(ResultSet resultSet)throws SQLException{
        resultSet.last();
        int row = resultSet.getRow();
        resultSet.beforeFirst();
        return row;
    }
}