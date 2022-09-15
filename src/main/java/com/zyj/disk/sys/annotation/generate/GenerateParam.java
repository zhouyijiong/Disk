package com.zyj.disk.sys.annotation.generate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 代码生成注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GenerateParam {
    boolean primary() default false;

    boolean unique() default false;

    boolean required() default true;

    String length() default "";
}