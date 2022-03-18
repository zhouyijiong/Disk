package com.zyj.disk.sys.annotation.mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 如果采用 BaseEntity 参数,那么 sql 语句失效
 * 改成遍历 BaseEntity 参数,默认 {name == value}
 * 如果采用 base param 参数,那么 sql 语句生效
 * 改成遍历 sql ,寻找 #{key} ,并替换成对应的 value
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MapperProxy{}