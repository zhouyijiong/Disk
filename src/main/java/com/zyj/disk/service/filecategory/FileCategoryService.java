package com.zyj.disk.service.filecategory;

import com.zyj.disk.entity.filecategory.FileCategory;
import com.zyj.disk.sys.tool.encryption.PrivateKey;
import com.zyj.disk.sys.tool.encryption.xor.Codec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.zyj.disk.mapper.filecategory.FileCategoryMapper;

@Service
@RequiredArgsConstructor
public final class FileCategoryService implements FileCategoryTemplate {
    private final FileCategoryMapper filecategoryMapper;

    @Override
    public String codingCategory(String category) {
        return Codec.complex(category, PrivateKey.OFFSET);
    }

    @Override
    public FileCategory initFileCategory(Integer userId, String categoryCode) {
        return FileCategory.noArgs().userId(userId).categoryCode(categoryCode);
    }

    @Override
    public int saveFileCategory(FileCategory fileCategory) {
        return filecategoryMapper.insert(fileCategory);
    }
}