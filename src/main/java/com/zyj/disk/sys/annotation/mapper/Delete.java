package com.zyj.disk.sys.annotation.mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Delete{
    /** 为空默认不判断,非空则根据指定的表进行自由sql */
    String table() default "";

    /** 为空则 all delete,非空则根据条件删除 */
    String where() default "";
}