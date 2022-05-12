package com.zyj.disk.sys.annotation.mapper.base;

import com.zyj.disk.sys.entity.BaseEntity;
import com.zyj.disk.sys.entity.MapperMatch;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 更新映射注解 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Update{
    /**
     * 1.set() param size must <= method params
     * 2.set() param must is base param
     */
    String set() default "";

    /** where() param must is any base param or one BaseEntity */
    String where() default "";

    Class<? extends BaseEntity> operate() default BaseEntity.class;

    boolean print() default false;

    MapperMatch mapperMatch() default MapperMatch.NO;
}