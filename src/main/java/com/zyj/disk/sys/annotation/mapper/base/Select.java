package com.zyj.disk.sys.annotation.mapper.base;

import com.zyj.disk.sys.entity.BaseEntity;
import com.zyj.disk.sys.hikari.mapper.match.Entity;
import com.zyj.disk.sys.hikari.mapper.match.Param;
import com.zyj.disk.sys.hikari.mapper.operate.SelectOperate;
import lombok.AllArgsConstructor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 查询映射注解 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Select{
	/** 要查询的字段{"arg0,arg1,arg2..."} */
	String query() default "*";

	/** 参数名和 #{key} 相等即可 */
	String where() default "";

	/** 默认有多少条返回多少条 */
	String limit() default "";

	/** return type required BaseEntity */
	Class<? extends BaseEntity> result() default BaseEntity.class;

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

		public final SelectOperate match;
	}
}