package com.zyj.disk.sys.annotation;

import com.zyj.disk.sys.entity.Rules;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam{
    Rules regex() default Rules.NULL;

    int length() default -1;

    int minLen() default 0;

    int maxLen() default 0;

    boolean required() default true;
}