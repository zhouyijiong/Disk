package com.zyj.disk.controller.filecategory;

import com.zyj.disk.service.filecategory.FileCategoryService;
import com.zyj.disk.sys.annotation.verify.Level;
import com.zyj.disk.sys.annotation.verify.ParamsCheck;
import com.zyj.disk.sys.entity.BaseController;
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
public class FileCategoryController extends BaseController {
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
        filecategoryService.addFileCategory(super.init().getId(), category);
        return Response.success(null);
    }

    @Level(IdentitySet.USER)
    @GetMapping("/getFileCategoryList")
    public Response<List<String>> getFileCategoryList() {
        return Response.success(
                filecategoryService.decodingFileCategory(
                        filecategoryService.getFileCategoryList(super.init().getId())
                )
        );
    }
}