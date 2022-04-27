package com.zyj.disk.sys.generate;

import com.zyj.disk.sys.generate.file.*;
import lombok.AllArgsConstructor;

/**
 * @Author: ZYJ
 * @Date: 2022/4/13 9:54
 * @Remark:
 */
@AllArgsConstructor
public enum FileTypeSet{
    ENTITY(new Entity()),
    Mapper(new Mapper()),
    Service(new Service()),
    Controller(new Controller()),
    SQL(new SQL());

    public final FileType fileType;
}