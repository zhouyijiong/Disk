package com.zyj.disk.controller.filecategory;

import com.zyj.disk.service.filecategory.FileCategoryService;
import com.zyj.disk.sys.annotation.verify.ParamsCheck;
import com.zyj.disk.sys.aop.GlobalVerify;
import com.zyj.disk.sys.entity.Response;
import com.zyj.disk.sys.entity.Rules;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fileCategory")
@RequiredArgsConstructor
public class FileCategoryController {
    private final FileCategoryService filecategoryService;

    /**
     * 新增文件分类 API
     *
     * @param fileCategoryHash 文件类别hash
     */
    @PostMapping("/addFileCategory")
    @ParamsCheck(@ParamsCheck.Param(name = "fileCategoryHash", regex = Rules.NUM_CHAR_LOW_32))
    public Response<String> addFileCategory(String fileCategoryHash) {
        filecategoryService.addFileCategory(GlobalVerify.current.getId(), fileCategoryHash);
        return null;
    }
}