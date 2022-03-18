package com.zyj.disk.sys.annotation.mapper;

import com.zyj.disk.sys.entity.BaseEntity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Update{
    /**
     * 1.set() param size must <= method params
     * 2.set() param must is base param
     */
    String set() default "";

    /**
     * 1.where() param must is any base param or one BaseEntity
     */
    String where() default "";

    Class<? extends BaseEntity> operate() default BaseEntity.class;

    /** 为 true 就打印 sql 语句,默认不打印 */
    boolean print() default false;
}