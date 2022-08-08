package com.zyj.disk.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyj.disk.entity.user.User;
import com.zyj.disk.mapper.user.UserMapper;
import com.zyj.disk.sys.entity.Response;
import com.zyj.disk.sys.exception.server.ServerError;
import com.zyj.disk.sys.identity.IdentitySet;
import com.zyj.disk.sys.tool.ClassTool;
import com.zyj.disk.sys.tool.encryption.PrivateKey;
import com.zyj.disk.sys.tool.encryption.xor.Codec;
import com.zyj.disk.sys.tool.structure.HashPair;
import com.zyj.disk.sys.tool.structure.Pair;
import com.zyj.disk.sys.tool.encryption.token.Token;
import com.zyj.disk.sys.tool.encryption.md5.MD5;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户业务类
 */
@Service
@RequiredArgsConstructor
public final class UserService extends UserTemplate {
    private final UserMapper userMapper;
    private final MD5 md5;
    private final Codec codec;

    @Override
    User queryUserByName(String username) {
        QueryWrapper<User> wrapper = ClassTool.queryBuild(User.noArgs().username(username));
        if (wrapper == null) throw ServerError.SQL_BUILD_FAIL;
        return userMapper.selectOne(wrapper);
    }

    @Override
    User initUser(String username, String password) {
        return User.defaultArgs()
                .username(username)
                .password(md5.encrypt(password))
                .path(md5.encrypt(username));
    }

    @Override
    int saveUser(User userEntity) {
        return userMapper.insert(userEntity);
    }

    @Override
    boolean userVerify(String sourcePwd, String requestPwd) {
        return !sourcePwd.equals(md5.encrypt(requestPwd));
    }

    @Override
    Response<String> result(String username, String token) {
        Pair<String, String> pair = new HashPair<>();
        pair.put("access", username);
        pair.put("token", token);
        return Response.success(pair);
    }

    @Override
    String getToken(User user) {
        Token<String, Object> token = new Token<>();
        token.put("user", Codec.codingObj(user, PrivateKey.OFFSET));
        token.put("identity", Codec.codingObj(IdentitySet.USER, PrivateKey.OFFSET));
        return token.generate();
    }
}