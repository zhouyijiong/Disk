package com.zyj.disk.service.user;

import com.zyj.disk.entity.user.UserEntity;
import com.zyj.disk.mapper.user.UserMapper;
import com.zyj.disk.tool.Encryption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author: ZYJ
 * @Date: 2022/04/01
 * @Remark: 用户业务类
 */
@Service
@RequiredArgsConstructor
public final class UserService extends UserTemplate{
    private final UserMapper userMapper;
    private final Encryption encryption;

    @Override
    UserEntity queryByName(String username){
        return userMapper.queryByName(username);
    }

    @Override
    int saveUser(String username,String password){
        return userMapper.insert(UserEntity.defaultArgs()
                .username(username)
                .password(encryption.md5(password))
                .path(encryption.md5(username)));
    }

    @Override
    boolean userVerify(UserEntity user,String password){
        return user == null || !user.getPassword().equals(encryption.md5(password));
    }
}