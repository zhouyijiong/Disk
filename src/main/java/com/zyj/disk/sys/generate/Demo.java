package com.zyj.disk.sys.generate;

import com.zyj.disk.sys.annotation.generate.GenerateParam;
import lombok.Getter;

import java.io.IOException;

@Getter
public final class Demo {
    @GenerateParam(primary = true)
    Integer id;

    @GenerateParam(length = "(11)", unique = true)
    private Integer userId;

    @GenerateParam(length = "char(32)", unique = true)
    private String path;

    @GenerateParam(length = "(13)")
    private final Long capacity = 3221225472L;

    @GenerateParam(length = "(4)")
    private final Integer fileCount = 5;

    @GenerateParam(length = "(13)")
    private final Long fileSize = 3221225472L;

    @GenerateParam(length = "(13)")
    private final Long totalFileSize = 3221225472L;

    @GenerateParam(length = "(14)")
    private final String platform = "";

    @GenerateParam(length = "(6)")
    private final String language = "";

    @GenerateParam(length = "(4)")
    private final Integer cores = 0;

    @GenerateParam(length = "(4)")
    private Integer thread = 0;

    @GenerateParam(length = "double(4,2)")
    private Double network = 0.0;

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        new Generate(Demo.class, "UserAttach").start(FileTypeSet.sql());
    }
}