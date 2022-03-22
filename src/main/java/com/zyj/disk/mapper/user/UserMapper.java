package com.zyj.disk.mapper.user;

import com.zyj.disk.entity.user.UserEntity;
import com.zyj.disk.sys.annotation.mapper.Delete;
import com.zyj.disk.sys.annotation.mapper.Insert;
import com.zyj.disk.sys.annotation.mapper.Select;
import com.zyj.disk.sys.annotation.mapper.MapperProxy;
import org.springframework.stereotype.Component;

@Component
@MapperProxy
public interface UserMapper{
    @Insert
    int insert(UserEntity user);

    @Insert
    int insert(UserEntity[] users);

    @Delete(operate = UserEntity.class,print = true)
    int delete(UserEntity user);

    @Delete(where = "id = #{id} and username = #{username}",operate = UserEntity.class,print = true)
    int delete(Integer id,String username);

    @Select(where = "username=#{name}",result = UserEntity.class)
    UserEntity queryByName(String name);
}