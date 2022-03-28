package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.entity.Record;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZYJ
 * @Date: 2022/3/26 11:05
 * @Remark: 注解映射处理
 */
@Component
@RequiredArgsConstructor
public abstract class Mapper{
    protected final DataSource dataSource;
    protected final Record record;

    public Mapper(DataSource dataSource,Class<?> clazz){
        this.dataSource = dataSource;
        this.record = Record.initialize(clazz);
    }

    public abstract boolean check(ProceedingJoinPoint joinPoint, Annotation annotation);

    public abstract String explain(ProceedingJoinPoint joinPoint,Annotation annotation);

    public Object actuator(ProceedingJoinPoint joinPoint,Annotation annotation){
        try(Connection connection = dataSource.getConnection()){
            return connection.prepareStatement(explain(joinPoint,annotation)).executeUpdate();
        }catch(Exception e){
            record.error(e);
        }
        return 0;
    }

    /**
     * TODO 考虑改用 long存储 用[1,2]表示,超过64位暂时无法处理
     * 探测 SQL 分布状况
     * @param sql 参数
     */
    List<Integer> probe(String sql){
        List<Integer> result = new ArrayList<>();
        char[] chars = sql.toCharArray();
        for(char c : chars){
            switch(c){
                case '#':result.add((int)'#');
                    break;
                case '$':result.add((int)'$');
            }
        }
        return result;
    }
}