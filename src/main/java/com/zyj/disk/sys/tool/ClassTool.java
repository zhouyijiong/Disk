package com.zyj.disk.sys.tool;

//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyj.disk.sys.exception.GlobalException;
import com.zyj.disk.sys.exception.Server;

import java.lang.reflect.Field;

/**
 * @Author: ZYJ
 * @Date: 2022/7/6 11:04
 * @Remark:
 */
public final class ClassTool {
//    public static <Entity, Vo> QueryWrapper<Entity> queryBuild(Vo entity) {
//        Object val;
//        QueryWrapper<Entity> wrapper = new QueryWrapper<>();
//        try {
//            for (Field field : entity.getClass().getDeclaredFields()) {
//                field.setAccessible(true);
//                if ((val = field.get(entity)) == null) continue;
//                wrapper.eq(field.getName(), val);
//            }
//        } catch (IllegalAccessException e) {
//            throw new GlobalException(Server.SQL_BUILD_ERROR.exception);
//        }
//        return wrapper;
//    }
}