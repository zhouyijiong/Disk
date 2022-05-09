package com.zyj.disk.service.user;

import com.zyj.disk.entity.user.UserEntity;
import com.zyj.disk.mapper.user.UserMapper;
import com.zyj.disk.tool.Encryption;
import com.zyj.disk.sys.exception.Client;
import com.zyj.disk.sys.exception.GlobalException;
import com.zyj.disk.sys.exception.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author: ZYJ
 * @Date: 2022/04/01
 * @Remark: 用户业务类
 */
@Service
@RequiredArgsConstructor
public final class UserService{
    private final UserMapper userMapper;
    private final Encryption encryption;

    /**
     * @Author: ZYJ
     * @Date: 2022/04/01
     * @Remark: 注册账户
     */
    public void registered(String username,String password){
        if(userMapper.queryByName(username) != null) throw new GlobalException(Client.USER_EXIST);
        if(userMapper.insert(UserEntity.defaultArgs()
                .username(username)
                .password(encryption.md5(password))
                .path(encryption.md5(username))
        ) == 0) throw new GlobalException(Server.SQL_RESULT_ERROR);
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/01
     * @Remark: 登陆账户
     */
    public void login(String username,String password){
        UserEntity user = userMapper.queryByName(username);
        if(user == null) throw new GlobalException(Client.USER_NOT_EXIST);
        if(!user.getPassword().equals(encryption.md5(password))) throw new GlobalException(Client.VERIFY_ERROR);
    }
}