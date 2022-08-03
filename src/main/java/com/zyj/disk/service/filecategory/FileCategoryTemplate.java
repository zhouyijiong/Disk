package com.zyj.disk.service.filecategory;

import com.zyj.disk.entity.filecategory.FileCategory;
import com.zyj.disk.sys.exception.server.Server;

/**
 * @Author: ZYJ
 * @Date: 2022/7/27 10:03
 * @Remark:
 */
public interface FileCategoryTemplate {
    default void addFileCategory(Integer userId,String fileCategoryHash){
        FileCategory fileCategory = initFileCategory(userId,fileCategoryHash);
        if (saveFileCategory(fileCategory) == 0) throw Server.SQL_RESULT_ERROR.e;
    }

    FileCategory initFileCategory(Integer userId,String fileCategoryHash);

    int saveFileCategory(FileCategory fileCategory);
}