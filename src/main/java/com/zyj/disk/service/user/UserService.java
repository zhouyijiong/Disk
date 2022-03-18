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

@Service
@RequiredArgsConstructor
public final class UserService{
    private final UserMapper mapper;
    private final Encryption encryption;

    public Map<String,Object> registered(String username,String password){
        if(mapper.queryByName(username) != null) throw new GlobalException(Client.USER_EXIST);
        String pwd = encryption.md5(password);
        String path = encryption.md5(username);
        UserEntity user = UserEntity.defaultArgs().username(username).password(pwd).path(path);
        int rows = mapper.insert(user);
        if(rows == 0) throw new GlobalException(Server.DATABASE_RESULT_ERROR);
        return Result.init().put("access",username);
    }

    public Map<String,Object> login(String username,String password){
        UserEntity user = mapper.queryByName(username);
        if(user == null) throw new GlobalException(Client.USER_NOT_EXIST);
        if(!user.getPassword().equals(encryption.md5(password))) throw new GlobalException(Client.VERIFY_ERROR);
        return Result.init().put("access",username);
    }
}