package com.zyj.disk.sys.annotation.verify;

import com.zyj.disk.sys.entity.Rules;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: ZYJ
 * @Date: 2022/4/8 13:13
 * @Remark: 参数校验注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamsCheck{
    @interface Param{
        String name();

        Rules regex() default Rules.NULL;

        int length() default -1;

        int minLen() default 0;

        int maxLen() default 0;

        boolean required() default true;
    }

    Param[] value();
}