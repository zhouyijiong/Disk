package com.zyj.disk.sys.generate;

import com.zyj.disk.sys.generate.file.*;

/** fileType 模型 */
public final class FileTypeSet{
    public FileType fileType;

    private FileTypeSet(FileType fileType){
        this.fileType = fileType;
    }

    public static FileType entity(){
        return new Entity();
    }

    public static FileType mapper(){
        return new Mapper();
    }

    public static FileType service(){
        return new Service();
    }

    public static FileType controller(){
        return new Controller();
    }

    public static FileType sql(){
        return new SQL();
    }
}