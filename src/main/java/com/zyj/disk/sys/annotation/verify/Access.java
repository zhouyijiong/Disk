package com.zyj.disk.sys.annotation.verify;

import com.zyj.disk.sys.entity.IdentitySet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限制特定的身份访问特定的页面
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Access {
    String path();

    IdentitySet[] identity();
}