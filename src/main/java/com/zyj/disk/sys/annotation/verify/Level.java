package com.zyj.disk.sys.annotation.verify;

import com.zyj.disk.sys.entity.IdentitySet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Level{
    IdentitySet[] value();
}