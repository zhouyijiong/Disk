package com.zyj.disk.service.user;

import com.zyj.disk.entity.user.User;
import com.zyj.disk.sys.entity.Response;
import com.zyj.disk.sys.exception.client.Client;
import com.zyj.disk.sys.exception.server.Server;

/**
 * 用户业务模板类
 */
public abstract class UserTemplate {
    /**
     * 注册账户
     *
     * @param username 用户名
     * @param password 密码
     */
    public final User registered(String username, String password) {
        if (queryUserByName(username) != null) throw Client.USER_EXIST.e;
        User user = initUser(username, password);
        if (saveUser(user) == 0) throw Server.SQL_RESULT_ERROR.e;
        return user;
    }

    /**
     * 登录账户
     *
     * @param username 用户名
     * @param password 密码
     */
    public final User login(String username, String password) {
        User user = queryUserByName(username);
        if (user == null || userVerify(user.getPassword(), password))
            throw Client.ACCOUNT_VERIFY_FAIL.e;
        return user;
    }

    /**
     * 返回响应类
     *
     * @param user 用户
     * @return Response<String>
     */
    public final Response<String> result(User user) {
        String token = getToken(user);
        return result(user.getUsername(), token);
    }

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return User
     */
    abstract User queryUserByName(String username);

    /**
     * 初始化用户实体类
     *
     * @param username 用户名
     * @param password 密码
     * @return User
     */
    abstract User initUser(String username, String password);

    /**
     * 新增用户
     *
     * @param userEntity 用户实体类
     * @return int SQL影响行数
     */
    abstract int saveUser(User userEntity);

    /**
     * 用户登录校验
     *
     * @param sourcePwd  用户密码
     * @param requestPwd 请求密码
     * @return true : 校验失败 | false : 校验成功
     */
    abstract boolean userVerify(String sourcePwd, String requestPwd);

    /**
     * 返回响应类
     *
     * @param username 用户名
     * @param token    token
     * @return Response<String>
     */
    abstract Response<String> result(String username, String token);

    /**
     * 获取 Token
     *
     * @param user 用户
     * @return String token
     */
    abstract String getToken(User user);
}