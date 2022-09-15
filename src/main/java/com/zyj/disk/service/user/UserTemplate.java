package com.zyj.disk.service.user;

import com.zyj.disk.entity.request.user.UserWhole;
import com.zyj.disk.entity.user.User;
import com.zyj.disk.entity.user.UserAttach;
import com.zyj.disk.sys.exception.client.ClientError;
import com.zyj.disk.sys.exception.server.ServerError;

/**
 * 用户业务模板类
 */
public interface UserTemplate {
    /**
     * 注册账户
     *
     * @param userWhole 用户完整信息
     */
    default User registered(UserWhole userWhole) {
        if (queryUserByName(userWhole.getUsername()) != null) throw ClientError.USER_EXIST;
        User user = initUser(userWhole);
        if (saveUser(user, initUserAttach(userWhole)) != 2) throw ServerError.SQL_RESULT_FAIL;
        return user;
    }

    /**
     * 登录账户
     *
     * @param username 用户名
     * @param password 密码
     */
    default User login(String username, String password) {
        User user = queryUserByName(username);
        if (user == null || userVerify(user.getPassword(), password)) throw ClientError.ACCOUNT_VERIFY_FAIL;
        return user;
    }

    /**
     * 返回响应类
     *
     * @param user 用户
     * @return Response<String>
     */
    default String result(User user) {
        return result(user.getUsername(), getToken(user), getIdentity());
    }

    /**
     * query user by name
     *
     * @param username 用户名
     * @return User
     */
    User queryUserByName(String username);

    /**
     * init user entity
     *
     * @param userWhole 用户完整信息
     * @return User
     */
    User initUser(UserWhole userWhole);

    /**
     * init user attach entity
     *
     * @param userWhole 用户完整信息
     * @return UserAttach
     */
    UserAttach initUserAttach(UserWhole userWhole);

    /**
     * add user
     *
     * @param userEntity 用户实体类
     * @return int SQL影响行数
     */
    int saveUser(User userEntity, UserAttach userAttach);

    /**
     * user login verify
     *
     * @param sourcePwd  用户密码
     * @param requestPwd 请求密码
     * @return true : 校验失败 | false : 校验成功
     */
    boolean userVerify(String sourcePwd, String requestPwd);

    /**
     * 返回响应类
     *
     * @param username 用户名
     * @param token    token
     */
    String result(String username, String token, String identity);

    /**
     * 获取信息凭证
     *
     * @param user 用户
     * @return token
     */
    String getToken(User user);

    /**
     * 获取身份凭证
     *
     * @return identity
     */
    String getIdentity();
}