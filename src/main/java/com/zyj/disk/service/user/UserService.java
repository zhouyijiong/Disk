package com.zyj.disk.service.user;

import com.zyj.disk.entity.user.UserEntity;
import com.zyj.disk.mapper.user.UserMapper;
import com.zyj.disk.tool.Encryption;
import com.zyj.disk.sys.exception.Client;
import com.zyj.disk.sys.exception.GlobalException;
import com.zyj.disk.sys.exception.Server;
import com.zyj.disk.tool.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * @Author: ZYJ
 * @Date: 2022/4/1
 * @Remark: 用户业务类
 */
@Service
@RequiredArgsConstructor
public final class UserService{
    private final UserMapper userMapper;
    private final Encryption encryption;

    /**
     * @Author: ZYJ
     * @Date: 2022/4/1
     * @Remark: 注册账户
     * @return java.util.Map
     */
    public Map<String,Object> registered(String username,String password){
        if(userMapper.queryByName(username) != null) throw new GlobalException(Client.USER_EXIST);
        String pwd = encryption.md5(password);
        String path = encryption.md5(username);
        int rows = userMapper.insert(UserEntity.defaultArgs().username(username).password(pwd).path(path));
        if(rows == 0) throw new GlobalException(Server.SQL_RESULT_ERROR);
        return Result.init().put("access",username);
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/4/1
     * @Remark: 登陆账户
     * @return java.util.Map
     */
    public Map<String,Object> login(String username,String password){
        UserEntity user = userMapper.queryByName(username);
        if(user == null) throw new GlobalException(Client.USER_NOT_EXIST);
        if(!user.getPassword().equals(encryption.md5(password))) throw new GlobalException(Client.VERIFY_ERROR);
        return Result.init().put("access",username);
    }
}