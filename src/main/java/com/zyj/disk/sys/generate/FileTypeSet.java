package com.zyj.disk.sys.generate;

import com.zyj.disk.sys.generate.file.*;

/**
 * fileType 模型
 */
public final class FileTypeSet {
    public static FileType entity() {
        return new Entity();
    }

    public static FileType mapper() {
        return new Mapper();
    }

    public static FileType service() {
        return new Service();
    }

    public static FileType controller() {
        return new Controller();
    }

    public static FileType sql() {
        return new SQL();
    }

    public static FileType[] all() {
        return new FileType[]{entity(), mapper(), service(), controller(), sql()};
    }
}