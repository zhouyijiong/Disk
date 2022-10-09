package com.zyj.disk.mapper.user;

import com.zyj.disk.entity.user.User;
import com.zyj.disk.sys.entity.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}