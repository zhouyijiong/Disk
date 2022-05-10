package com.zyj.disk.service.user;

import com.zyj.disk.entity.user.UserEntity;
import com.zyj.disk.sys.exception.Client;
import com.zyj.disk.sys.exception.Server;

/**
 * @Author: ZYJ
 * @Date: 2022/5/10 11:15
 * @Remark: 用户服务模板类
 */
public abstract class UserServiceTemplate{
    /**
     * @Author: ZYJ
     * @Date: 2022/04/01
     * @Remark: 注册账户
     */
    public void registered(String username,String password){
        if(queryByName(username) != null) throw Client.USER_EXIST.exception;
        if(saveUser(username,password) == 0) throw Server.SQL_RESULT_ERROR.exception;
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/01
     * @Remark: 登陆账户
     */
    public void login(String username,String password){
        if(userVerify(queryByName(username),password)) throw Client.ACCOUNT_OR_PASSWORD_ERROR.exception;
    }

    abstract UserEntity queryByName(String username);

    abstract int saveUser(String username,String password);

    abstract boolean userVerify(UserEntity user,String password);
}