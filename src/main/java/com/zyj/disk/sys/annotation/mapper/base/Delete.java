package com.zyj.disk.sys.annotation.mapper.base;

import com.zyj.disk.sys.entity.BaseEntity;
import com.zyj.disk.sys.entity.MapperMatch;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Delete{
    /**
     * 为空则 all delete,非空则根据条件删除
     * param1:{BaseEntity}
     * param2:{arg0,arg1,arg2...}
     */
    String where() default "";

    Class<? extends BaseEntity> operate() default BaseEntity.class;

    boolean print() default false;

    MapperMatch mapperMatch() default MapperMatch.PARAM;
}