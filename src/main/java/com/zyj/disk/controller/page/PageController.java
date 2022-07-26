package com.zyj.disk.controller.page;

import com.zyj.disk.sys.annotation.verify.Access;
import com.zyj.disk.sys.identity.IdentitySet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 页面控制器
 */
@Controller
public class PageController {
    private static final String USER = "management/user/b78ba6e0a8460310bc11b9daaa826a2c";

    /**
     * 访问首页
     */
    @GetMapping
    public String index() {
        return "index/index";
    }

    /**
     * 访问用户后台
     */
    @Access(identity = {IdentitySet.USER})
    @GetMapping("/management")
    public String management() {
        //TODO 取消方法内容，切面解耦动态访问，附加：置换成接口
        return USER;
    }
}