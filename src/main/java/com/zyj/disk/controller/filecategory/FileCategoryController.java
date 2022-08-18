package com.zyj.disk.controller.filecategory;

import com.zyj.disk.entity.filecategory.FileCategory;
import com.zyj.disk.service.filecategory.FileCategoryService;
import com.zyj.disk.sys.annotation.verify.Level;
import com.zyj.disk.sys.annotation.verify.ParamsCheck;
import com.zyj.disk.sys.entity.Response;
import com.zyj.disk.sys.identity.IdentitySet;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fileCategory")
@RequiredArgsConstructor
public class FileCategoryController {
    private final FileCategoryService filecategoryService;

    /**
     * 新增文件分类 API
     *
     * @param category 文件类别
     */
    @Level(IdentitySet.USER)
    @PostMapping("/addFileCategory")
    @ParamsCheck(@ParamsCheck.Param(name = "category"))
    public Response<String> addFileCategory(String category) {
        filecategoryService.addFileCategory(-1, category);//GlobalVerify.current.getId()
        return Response.success(null);
    }

    @Level(IdentitySet.USER)
    @GetMapping("/getFileCategoryList")
    public Response<List<FileCategory>> getFileCategoryList() {
        List<FileCategory> fileCategoryList = filecategoryService.getFileCategoryList(-1);//GlobalVerify.current.getId()
        filecategoryService.decodingFileCategory(fileCategoryList);
        return Response.success(fileCategoryList);
    }
}