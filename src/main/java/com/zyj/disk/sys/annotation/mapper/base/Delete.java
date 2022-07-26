package com.zyj.disk.sys.annotation.mapper.base;

import com.zyj.disk.sys.hikari.mapper.match.Entity;
import com.zyj.disk.sys.hikari.mapper.match.Param;
import com.zyj.disk.sys.hikari.mapper.operate.DeleteOperate;
import lombok.AllArgsConstructor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 删除映射注解 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Delete{
    String where() default "";

    boolean print() default false;

    Match match() default Match.ENTITY;

    @AllArgsConstructor
    enum Match{
        /**
         * 有参数
         * 通过'#{}'对普通参数进行匹配 或 通过 ${*.*} 对 'Entity' 参数匹配
         * 返回一条SQL
         */
        PARAM(new Param()),

        /**
         * 无参数
         * 遍历'Entity'参数,取' !=null 的参数 == 判断'
         * 返回一条SQL
         */
        ENTITY(new Entity());

        public final DeleteOperate match;
    }
}