package com.zyj.disk.sys.generate;

import com.zyj.disk.sys.annotation.GenerateParam;
import lombok.Getter;

import java.io.IOException;

@Getter
public final class Demo {
    @GenerateParam(primary = true)
    Integer id;

    @GenerateParam(length = "(11)")
    Integer userId;

    @GenerateParam(length = "(14)")
    String platform = "Win32";

    @GenerateParam(length = "(6)")
    String language = "zh-CN";

    @GenerateParam(length = "(150)")
    String lastClientInfo = "";

    @GenerateParam(length = "(150)")
    String thisClientInfo;

    @GenerateParam(length = "(4)")
    Integer deviceMemory = 0;

    @GenerateParam(length = "(4)")
    Integer hardwareConcurrency = 0;

    @GenerateParam(length = "(4)")
    Integer networkSpeed = 0;

    @GenerateParam(length = "(4)")
    String networkType = "";

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        new Generate(Demo.class, "UserAttach").start(FileTypeSet.entity(),FileTypeSet.service(),FileTypeSet.controller(),FileTypeSet.mapper(),FileTypeSet.sql());
    }
}