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
 * 添加匹配模式 => { 1:参数匹配, 2:实体匹配 }
 *      1:通过 #{} 对提交的普通参数进行匹配 或 通过 ${} 对 Entity 参数匹配
 *      2:无参数,直接遍历 Entity 参数,取 ！=null 的参数 == 判断
 *      3:无参数,直接遍历 Entity Array 参数,取 !=null 的参数 == 判断,返回一组sql
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MapperProxy{}