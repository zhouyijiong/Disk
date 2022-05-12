package com.zyj.disk.mapper.user;

import com.zyj.disk.entity.user.UserEntity;
import com.zyj.disk.sys.annotation.mapper.base.*;
import com.zyj.disk.sys.annotation.mapper.MapperProxy;
import com.zyj.disk.sys.entity.MapperMatch;

/** 用户映射器 */
@MapperProxy
public interface UserMapper{
    @Insert(mapperMatch = MapperMatch.ENTITY)
    int insert(UserEntity user);

    @Insert(mapperMatch = MapperMatch.ARRAY_ENTITY)
    int insert(UserEntity[] users);

    @Delete(where = "where id=#{id}",mapperMatch = MapperMatch.PARAM)
    int delete(Integer id);

    @Select(where = "where username=#{name}",result = UserEntity.class,mapperMatch = MapperMatch.PARAM)
    UserEntity queryByName(String name);
}