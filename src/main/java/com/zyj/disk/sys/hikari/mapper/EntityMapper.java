package com.zyj.disk.sys.hikari.mapper;

import com.zyj.disk.sys.annotation.mapper.base.*;
import java.util.List;

/**
 * @Author: ZYJ
 * @Date: 2022/6/16 13:29
 * @Remark: 映射器基类
 */
public interface EntityMapper<T>{
    @Insert(match = Insert.Match.ENTITY)
    int insert(T entity);

    @Insert(match = Insert.Match.ARRAY_ENTITY)
    int insertAll(List<T> data);

    @Delete(match = Delete.Match.ENTITY)
    int delete(T entity);

    @Update(match = Update.Match.ENTITY)
    int update(T entity);

    @Select(match = Select.Match.ENTITY)
    T select(T entity);
}