package com.zyj.disk.service.user;

import com.zyj.disk.entity.user.UserEntity;
import com.zyj.disk.sys.exception.Client;
import com.zyj.disk.sys.exception.Server;

/** 用户业务模板类 */
public abstract class UserTemplate{
    /**
     * 注册账户
     * @param username 用户名
     * @param password 密码
     */
    public void registered(String username,String password){
        if(queryByName(username) != null) throw Client.USER_EXIST.exception;
        if(saveUser(username,password) == 0) throw Server.SQL_RESULT_ERROR.exception;
    }

    /**
     * 登录账户
     * @param username 用户名
     * @param password 密码
     */
    public void login(String username,String password){
        if(userVerify(queryByName(username),password)) throw Client.ACCOUNT_OR_PASSWORD_ERROR.exception;
    }

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户实体类
     */
    abstract UserEntity queryByName(String username);

    /**
     * 新增用户
     * @param username 用户名
     * @param password 密码
     * @return SQL 影响数
     */
    abstract int saveUser(String username,String password);

    /**
     * 用户登录校验
     * @param user 用户实体类
     * @param password 密码
     * @return true : 校验失败 | false : 校验成功
     */
    abstract boolean userVerify(UserEntity user,String password);
}