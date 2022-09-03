package com.zyj.disk.sys.generate;

import com.zyj.disk.sys.annotation.GenerateParam;
import lombok.Getter;

import java.io.IOException;

@Getter
public final class Demo {
    @GenerateParam(primary = true)
    Integer id;

    @GenerateParam(length = "(10)", unique = true)
    String username;

    @GenerateParam(length = "(32)")
    String password;

    @GenerateParam(length = "(15)")
    String ip = "";

    @GenerateParam(length = "(15)")
    String lastIp = "";

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        new Generate(Demo.class, "User").start(FileTypeSet.sql());
    }
}