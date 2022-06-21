package com.zyj.disk.sys.annotation.mapper.base;

import com.zyj.disk.sys.hikari.mapper.match.ArrayEntity;
import com.zyj.disk.sys.hikari.mapper.match.Entity;
import com.zyj.disk.sys.hikari.mapper.operate.InsertOperate;
import lombok.AllArgsConstructor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 插入映射注解 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Insert{
    /** 为空默认全部插入,非空必须和数据库属性名对应 */
    String[] target() default {};

    boolean print() default false;

    Match match() default Match.ENTITY;

    @AllArgsConstructor
    enum Match{
        /**
         * 无参数
         * 遍历'Entity'参数,取' !=null 的参数 == 判断'
         * 返回一条SQL
         */
        ENTITY(new Entity()),

        /**
         * 无参数
         * 遍历'Entity Array'数组,循环获取每项中' !=null 的参数 == 判断'
         * 返回一组SQL
         */
        ARRAY_ENTITY(new ArrayEntity());

        public final InsertOperate match;
    }
}