package com.zyj.disk.sys.hikari;

import com.zyj.disk.sys.entity.BaseEntity;
import com.zyj.disk.sys.entity.Record;
import com.zyj.disk.sys.tool.ClassTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.Connection;

/** 执行器 */
@Component
@RequiredArgsConstructor
public final class Actuator{
    private final ClassTool classTool;
    private final DataSource dataSource;
    private final Processor processor;
    private final Record record = Record.initialize(this.getClass());

    public int insert(String sql){
        try(Connection connection = dataSource.getConnection()){
            return connection.prepareStatement(sql).executeUpdate();
        }catch(Exception e){
            record.error(e);
        }
        return 0;
    }

    public int delete(String sql){
        try(Connection connection = dataSource.getConnection()){
            return connection.prepareStatement(sql).executeUpdate();
        }catch(Exception e){
            record.error(e);
        }
        return 0;
    }

    public int update(String sql){
        try(Connection connection = dataSource.getConnection()){
            return connection.prepareStatement(sql).executeUpdate();
        }catch(Exception e){
            record.error(e);
        }
        return 0;
    }

    public Object select(String sql){
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