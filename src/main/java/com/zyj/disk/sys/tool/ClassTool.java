package com.zyj.disk.sys.tool;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyj.disk.sys.entity.Record;
import com.zyj.disk.sys.exception.server.ServerError;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

/**
 * Class Tool
 */
public final class ClassTool {
    private static final Record RECORD = new Record(ClassTool.class);

    public static <Entity, Vo> QueryWrapper<Entity> queryBuild(Vo entity) {
        Object val;
        QueryWrapper<Entity> wrapper = new QueryWrapper<>();
        try {
            for (Field field : entity.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if ((val = field.get(entity)) == null) continue;
                wrapper.eq(SysTool.humpToLine(field.getName()), val);
            }
            return wrapper;
        } catch (IllegalAccessException e) {
            RECORD.error(e);
            throw ServerError.SQL_BUILD_FAIL;
        }
    }

    public static byte[] serialize(Object obj) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            new ObjectOutputStream(out).writeObject(obj);
            return out.toByteArray();
        } catch (IOException e) {
            RECORD.error(e);
            return null;
        }
    }

    public static Object deserialize(byte[] data) {
        try (ByteArrayInputStream in = new ByteArrayInputStream(data)) {
            return new ObjectInputStream(in).readObject();
        } catch (IOException | ClassNotFoundException e) {
            RECORD.error(e);
            return null;
        }
    }
}