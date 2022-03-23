package com.zyj.disk.mapper.user;

import com.zyj.disk.entity.user.UserEntity;
import com.zyj.disk.sys.annotation.mapper.Delete;
import com.zyj.disk.sys.annotation.mapper.Insert;
import com.zyj.disk.sys.annotation.mapper.Select;
import com.zyj.disk.sys.annotation.mapper.MapperProxy;
import com.zyj.disk.sys.entity.MapperMatch;
import org.springframework.stereotype.Component;

@Component
@MapperProxy
public interface UserMapper{
    @Insert
    int insert(UserEntity user);

    @Insert
    int insert(UserEntity[] users);

    @Delete(where = "id=#{id}")
    int delete(Integer id);

/*    @Delete(where = "id=${id}")
    int delete(UserEntity user);

    @Delete(mapperMatch = MapperMatchInteface.ENTITY_MATCH)
    int delete(UserEntity user);*/

    @Select(where = "username=#{name}",result = UserEntity.class)
    UserEntity queryByName(String name);
}