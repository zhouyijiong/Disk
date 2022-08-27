package com.zyj.disk.controller.page;

import com.zyj.disk.sys.annotation.verify.Access;
import com.zyj.disk.sys.entity.IdentitySet;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 页面控制器
 */
@Mapper
@Controller
public interface PageController {
    /**
     * 访问首页
     */
    @GetMapping
    @Access(
            identity = IdentitySet.VISITOR,
            path = "index/index"
    )
    String index();

    /**
     * 访问用户后台
     */
    @GetMapping("/management")
    @Access(
            identity = IdentitySet.USER,
            path = "management/user/test"
    )
    String management();
}