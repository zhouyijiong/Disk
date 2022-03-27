package com.zyj.disk.sys.annotation.mapper;

import com.zyj.disk.sys.entity.MapperMatch;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Insert{
    /** 为空默认全部插入,非空必须和数据库属性名对应 */
    String[] target() default {};

    /** 参数匹配模式: { MapperProxy.class } */
    MapperMatch mapperMatch() default MapperMatch.PARAM;
}