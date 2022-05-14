package com.zyj.disk.service.user;

import com.zyj.disk.entity.user.UserEntity;
import com.zyj.disk.sys.entity.Response;
import com.zyj.disk.sys.exception.Client;
import com.zyj.disk.sys.exception.Server;

/** 用户业务模板类 */
public abstract class UserTemplate{
    /**
     * 注册账户
     *
     * @param username 用户名
     * @param password 密码
     */
    public void registered(String username,String password){
        if(queryUserByName(username) != null) throw Client.USER_EXIST.exception;
        UserEntity entity = initUserEntity(username,password);
        if(saveUser(entity) == 0) throw Server.SQL_RESULT_ERROR.exception;
    }

    /**
     * 登录账户
     *
     * @param username 用户名
     * @param password 密码
     */
    public void login(String username,String password){
        UserEntity user = queryUserByName(username);
        if(user == null || userVerify(user.getPassword(),password))
            throw Client.ACCOUNT_OR_PASSWORD_ERROR.exception;
    }

    /**
     * 返回响应类
     *
     * @param username 用户名
     * @return Response<String>
     */
    public Response<String> result(String username){
        String token = getToken(username);
        return result(username,token);
    }

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return UserEntity
     */
    abstract UserEntity queryUserByName(String username);

    /**
     * 初始化用户实体类
     *
     * @param username 用户名
     * @param password 密码
     * @return UserEntity
     */
    abstract UserEntity initUserEntity(String username,String password);

    /**
     * 新增用户
     *
     * @param userEntity 用户实体类
     * @return int SQL影响行数
     */
    abstract int saveUser(UserEntity userEntity);

    /**
     * 用户登录校验
     *
     * @param sourcePwd 用户密码
     * @param requestPwd 请求密码
     * @return true : 校验失败 | false : 校验成功
     */
    abstract boolean userVerify(String sourcePwd,String requestPwd);

    /**
     * 返回响应类
     *
     * @param username 用户名
     * @param token token
     * @return Response<String>
     */
    abstract Response<String> result(String username,String token);

    /**
     * 获取 Token
     *
     * @param username 用户名
     * @return String token
     */
    abstract String getToken(String username);
}