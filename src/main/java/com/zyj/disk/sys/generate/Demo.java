package com.zyj.disk.sys.generate;

import com.zyj.disk.sys.annotation.GenerateParam;
import lombok.Getter;

import java.io.IOException;

@Getter
public final class Demo {
    @GenerateParam(primary = true)
    Integer id;

    @GenerateParam(length = "(32)")
    String userHash;

    @GenerateParam(length = "(40)")
    String categoryCode;

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        new Generate(Demo.class, "fileBategory").start(FileTypeSet.controller());
    }
}