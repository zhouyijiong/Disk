package com.zyj.disk.controller.filecategory;

import com.zyj.disk.entity.filecategory.FileCategory;
import com.zyj.disk.service.filecategory.FileCategoryService;
import com.zyj.disk.sys.annotation.verify.ParamsCheck;
import com.zyj.disk.sys.aop.GlobalVerify;
import com.zyj.disk.sys.entity.Response;
import lombok.RequiredArgsConstructor;
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
    @PostMapping("/addFileCategory")
    @ParamsCheck(@ParamsCheck.Param(name = "category"))
    public Response<String> addFileCategory(String category) {
        filecategoryService.addFileCategory(GlobalVerify.current.getId(), category);
        return Response.success(null);
    }

    @PostMapping("/getFileCategoryList")
    public Response<List<FileCategory>> getFileCategoryList(){
        List<FileCategory> fileCategoryList = filecategoryService.getFileCategoryList(GlobalVerify.current.getId());
        filecategoryService.decodingFileCategory(fileCategoryList);
        return Response.success(fileCategoryList);
    }
}