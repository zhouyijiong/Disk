package com.zyj.disk.sys.annotation.mapper.base;

import com.zyj.disk.sys.entity.MapperMatch;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 插入映射注解 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Insert{
    /** 为空默认全部插入,非空必须和数据库属性名对应 */
    String[] target() default {};

    boolean print() default false;

    MapperMatch mapperMatch();
}