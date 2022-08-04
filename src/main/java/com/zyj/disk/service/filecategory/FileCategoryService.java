package com.zyj.disk.service.filecategory;

import com.zyj.disk.entity.filecategory.FileCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.zyj.disk.mapper.filecategory.FileCategoryMapper;

@Service
@RequiredArgsConstructor
public final class FileCategoryService implements FileCategoryTemplate {
    private final FileCategoryMapper filecategoryMapper;

    @Override
    public FileCategory initFileCategory(Integer userId, String fileCategoryHash) {
        return FileCategory.noArgs().userId(userId).fileCategoryHash(fileCategoryHash);
    }

    @Override
    public int saveFileCategory(FileCategory fileCategory) {
        return filecategoryMapper.insert(fileCategory);
    }
}