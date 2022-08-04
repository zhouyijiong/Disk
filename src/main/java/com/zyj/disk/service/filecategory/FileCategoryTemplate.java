package com.zyj.disk.service.filecategory;

import com.zyj.disk.entity.filecategory.FileCategory;
import com.zyj.disk.sys.exception.server.ServerError;

/**
 * 文件类别业务模板类
 */
public interface FileCategoryTemplate {
    default void addFileCategory(Integer userId, String fileCategoryHash) {
        FileCategory fileCategory = initFileCategory(userId, fileCategoryHash);
        if (saveFileCategory(fileCategory) == 0) throw ServerError.SQL_RESULT_FAIL;
    }

    FileCategory initFileCategory(Integer userId, String fileCategoryHash);

    int saveFileCategory(FileCategory fileCategory);
}