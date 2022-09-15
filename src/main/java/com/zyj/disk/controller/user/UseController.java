package com.zyj.disk.controller.user;

import com.zyj.disk.entity.request.user.UserWhole;
import com.zyj.disk.service.user.UserService;
import com.zyj.disk.sys.annotation.verify.ParamsCheck;
import com.zyj.disk.sys.annotation.verify.ParamsCheck.Param;
import com.zyj.disk.sys.entity.Response;
import com.zyj.disk.sys.entity.Rules;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UseController {
    private final UserService userService;

    /**
     * 注册 API
     *
     * @param userWhole 用户完整信息
     */
    @PostMapping("/registered")
    @ParamsCheck(value = {
            @Param(name = "username", regex = Rules.NUM_CHAR_LOW),
            @Param(name = "password", regex = Rules.NUM_CHAR_LOW_32)
    }, isSet = true)
    public Response<String> registered(UserWhole userWhole) {
        return Response.success(userService.result(userService.registered(userWhole)));
    }

    /**
     * 登录 API
     *
     * @param username 登陆用户名
     * @param password 登录密码
     */
    @PostMapping("/login")
    @ParamsCheck({
            @Param(name = "username", regex = Rules.NUM_CHAR_LOW),
            @Param(name = "password", regex = Rules.NUM_CHAR_LOW_32)
    })
    public Response<String> login(String password, String username, HttpServletRequest request, HttpServletResponse response) {//
        System.out.println(request);
        System.out.println(request == null);
        System.out.println(request.getParameter("params"));
        return Response.success(userService.result(userService.login(username, password)));
    }
}