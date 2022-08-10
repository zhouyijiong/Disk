package com.zyj.disk.service.user;

import com.zyj.disk.entity.user.User;
import com.zyj.disk.sys.entity.Response;
import com.zyj.disk.sys.exception.client.ClientError;
import com.zyj.disk.sys.exception.server.ServerError;

/**
 * 用户业务模板类
 */
public interface UserTemplate {
    /**
     * 注册账户
     *
     * @param username 用户名
     * @param password 密码
     */
    default User registered(String username, String password) {
        if (queryUserByName(username) != null) throw ClientError.USER_EXIST;
        User user = initUser(username, password);
        if (saveUser(user) == 0) throw ServerError.SQL_RESULT_FAIL;
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
        if (user == null || userVerify(user.getPassword(), password))
            throw ClientError.ACCOUNT_VERIFY_FAIL;
        return user;
    }

    /**
     * 返回响应类
     *
     * @param user 用户
     * @return Response<String>
     */
    default Response<String> result(User user) {
        String token = getToken(user);
        return result(user.getUsername(), token);
    }

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return User
     */
    User queryUserByName(String username);

    /**
     * 初始化用户实体类
     *
     * @param username 用户名
     * @param password 密码
     * @return User
     */
    User initUser(String username, String password);

    /**
     * 新增用户
     *
     * @param userEntity 用户实体类
     * @return int SQL影响行数
     */
    int saveUser(User userEntity);

    /**
     * 用户登录校验
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
     * @return Response<String>
     */
    Response<String> result(String username, String token);

    /**
     * 获取 Token
     *
     * @param user 用户
     * @return String token
     */
    String getToken(User user);
}