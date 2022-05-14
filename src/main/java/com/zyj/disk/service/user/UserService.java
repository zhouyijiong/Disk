package com.zyj.disk.service.user;

import com.zyj.disk.entity.user.UserEntity;
import com.zyj.disk.mapper.user.UserMapper;
import com.zyj.disk.sys.entity.Response;
import com.zyj.disk.sys.tool.structure.HashPair;
import com.zyj.disk.sys.tool.structure.Pair;
import com.zyj.disk.tool.Encryption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** 用户业务类 */
@Service
@RequiredArgsConstructor
public final class UserService extends UserTemplate{
    private final UserMapper userMapper;
    private final Encryption encryption;

    @Override
    UserEntity queryUserByName(String username){
        return userMapper.queryByName(username);
    }

    @Override
    UserEntity initUserEntity(String username,String password){
        return UserEntity.defaultArgs()
                .username(username)
                .password(encryption.md5(password))
                .path(encryption.md5(username));
    }

    @Override
    int saveUser(UserEntity userEntity){
        return userMapper.insert(userEntity);
    }

    @Override
    boolean userVerify(String sourcePwd,String requestPwd){
        return !sourcePwd.equals(encryption.md5(requestPwd));
    }

    @Override
    Response<String> result(String username,String token){
        Pair<String,String> pair = new HashPair<>();
        pair.put("access",username);
        pair.put("token",token);
        return Response.success(pair);
    }

    @Override
    String getToken(String username){
        return username;
    }
}