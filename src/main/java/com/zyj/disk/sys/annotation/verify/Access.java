package com.zyj.disk.sys.annotation.verify;

import com.zyj.disk.sys.identity.IdentitySet;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//TODO 取消依赖 token ,自行实现 token 机制
/** 限制被标记的方法访问状态必须为登录 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Access{
    IdentitySet[] identity();
}