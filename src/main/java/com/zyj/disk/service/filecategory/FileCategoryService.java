package com.zyj.disk.service.filecategory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyj.disk.entity.filecategory.FileCategory;
import com.zyj.disk.sys.tool.ClassTool;
import com.zyj.disk.sys.tool.encryption.PrivateKey;
import com.zyj.disk.sys.tool.encryption.md5.MD5;
import com.zyj.disk.sys.tool.encryption.codec.Codec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.zyj.disk.mapper.filecategory.FileCategoryMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class FileCategoryService implements FileCategoryTemplate {
    private final FileCategoryMapper filecategoryMapper;

    @Override
    public String codingUserId(Integer userId) {
        return MD5.encrypt(userId + PrivateKey.PK);
    }

    @Override
    public String codingCategory(String category) {
        return Codec.complex(category, PrivateKey.OFFSET);
    }

    @Override
    public FileCategory initFileCategory(String userHash, String categoryCode) {
        return FileCategory.noArgs().userHash(userHash).categoryCode(categoryCode);
    }

    @Override
    public int saveFileCategory(FileCategory fileCategory) {
        return filecategoryMapper.insert(fileCategory);
    }

    @Override
    public List<FileCategory> getFileCategoryList(FileCategory fileCategory) {
        QueryWrapper<FileCategory> wrapper = ClassTool.queryBuild(fileCategory);
        return filecategoryMapper.selectList(wrapper.select("category_code"));
    }
}