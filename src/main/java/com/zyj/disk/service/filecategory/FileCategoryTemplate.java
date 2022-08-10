package com.zyj.disk.service.filecategory;

import com.zyj.disk.entity.filecategory.FileCategory;
import com.zyj.disk.sys.exception.server.ServerError;

import java.util.List;

/**
 * 文件类别业务模板类
 */
public interface FileCategoryTemplate {
    default void addFileCategory(Integer userId, String category) {
        FileCategory fileCategory = initFileCategory(codingUserId(userId), codingCategory(category));
        if (saveFileCategory(fileCategory) == 0) throw ServerError.SQL_RESULT_FAIL;
    }

    default List<FileCategory> getFileCategoryList(Integer userId) {
        return getFileCategoryList(FileCategory.noArgs().userHash(codingUserId(userId)));
    }

    default void decodingFileCategory(List<FileCategory> fileCategoryList) {
        fileCategoryList.forEach(item -> item.categoryCode(codingCategory(item.getCategoryCode())));
    }

    String codingUserId(Integer userId);

    String codingCategory(String category);

    FileCategory initFileCategory(String userHash, String fileCategoryHash);

    int saveFileCategory(FileCategory fileCategory);

    List<FileCategory> getFileCategoryList(FileCategory fileCategory);
}