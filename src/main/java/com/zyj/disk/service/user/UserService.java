package com.zyj.disk.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyj.disk.entity.request.user.UserWhole;
import com.zyj.disk.entity.user.User;
import com.zyj.disk.entity.user.UserAttach;
import com.zyj.disk.mapper.user.UserMapper;
import com.zyj.disk.sys.entity.Response;
import com.zyj.disk.sys.exception.server.ServerError;
import com.zyj.disk.sys.entity.IdentitySet;
import com.zyj.disk.sys.tool.ClassTool;
import com.zyj.disk.sys.tool.encryption.codec.Codec;
import com.zyj.disk.sys.tool.encryption.xor.XOR;
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
public final class UserService implements UserTemplate {
    private final UserMapper userMapper;
    private final UserAttachService userAttachService;

    @Override
    public User queryUserByName(String username) {
        QueryWrapper<User> wrapper = ClassTool.queryBuild(User.noArgs().username(username));
        if (wrapper == null) throw ServerError.SQL_BUILD_FAIL;
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User initUser(UserWhole userWhole) {
        return User.defaultArgs()
                .username(userWhole.getUsername())
                .password(MD5.encrypt(userWhole.getPassword()));
    }

    public UserAttach initUserAttach(UserWhole userWhole) {
        return UserAttach.defaultArgs()
                .path(MD5.encrypt(userWhole.getUsername()))
                .platform(userWhole.getPlatform())
                .language(userWhole.getLanguage())
                .cores(userWhole.getCores())
                .thread(userWhole.getThread())
                .network(userWhole.getNetwork());
    }

    @Override
    public int saveUser(User user, UserAttach userAttach) {
        return userMapper.insert(user) + userAttachService.saveUserAttach(userAttach.userId(user.getId()));
    }

    @Override
    public boolean userVerify(String sourcePwd, String requestPwd) {
        return !sourcePwd.equals(MD5.encrypt(requestPwd));
    }

    @Override
    public String result(String username, String token, String identity) {
        Pair<String, String> pair = new HashPair<>();
        pair.put("access", username);
        pair.put("token", token);
        pair.put("identity", identity);
        return pair.toJSONString();
    }

    @Override
    public String getToken(User user) {
        Pair<String, String> pair = new HashPair<>();
        pair.put("user", Codec.codingObj(user));
        pair.put("identity", Codec.codingObj(IdentitySet.USER));
        return Token.generate(pair.toJSONString());
    }

    @Override
    public String getIdentity() {
        return Token.generate(XOR.encrypt(IdentitySet.USER.toString()));
    }
}