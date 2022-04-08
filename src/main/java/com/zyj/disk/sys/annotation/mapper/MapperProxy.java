package com.zyj.disk.sys.annotation.mapper;

import org.springframework.stereotype.Component;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: ZYJ
 * @Date: 2022/04/08
 * @Remark: 添加匹配模式 => {
 *     1 : MapperMatch.NO
 *     2 : MapperMatch.PARAM
 *     3 : MapperMatch.ENTITY
 *     4 : MapperMatch.ARRAY_ENTITY
 * }
 */
@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MapperProxy{}