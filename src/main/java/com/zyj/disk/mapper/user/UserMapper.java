package com.zyj.disk.mapper.user;

import com.zyj.disk.entity.user.UserEntity;
import com.zyj.disk.sys.annotation.mapper.base.*;
import com.zyj.disk.sys.annotation.mapper.MapperProxy;
import java.util.List;

/** 用户映射器 */
@MapperProxy
public interface UserMapper{
    @Insert(match = Insert.Match.ENTITY)
    int insert(UserEntity user);

    @Insert(match = Insert.Match.ARRAY_ENTITY)
    int insert(List<UserEntity> users);

    @Delete(where = "where id=#{id}",match = Delete.Match.PARAM)
    int delete(Integer id);

    @Delete(match = Delete.Match.ENTITY)
    int delete(UserEntity entity);

    @Select(where = "where username=#{name}",result = UserEntity.class,match = Select.Match.PARAM)
    UserEntity queryByName(String name);
}