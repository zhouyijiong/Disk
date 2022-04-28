package com.zyj.disk.controller.user;

import com.zyj.disk.service.user.UserService;
import com.zyj.disk.sys.annotation.verify.ParamsCheck;
import com.zyj.disk.sys.annotation.verify.ParamsCheck.Param;
import com.zyj.disk.sys.result.Pair;
import com.zyj.disk.sys.result.Response;
import com.zyj.disk.sys.entity.Rules;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZYJ
 * @Date: 2022/04/01
 * @Remark: 用户控制器
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UseController{
	private final UserService userService;

	/**
	 * @Author: ZYJ
	 * @Date: 2022/04/01
	 * @Remark: 注册 API
	 */
	@PostMapping("/registered")
	@ParamsCheck(
			params = {
					@Param(name = "username",regex = Rules.NUM_CHAR_LOW),
					@Param(name = "password",regex = Rules.NUM_CHAR_LOW_32)
			}
	)
	public Response<String> registered(String username,String password){
		userService.registered(username,password);
		Pair<String,String> pair = new Pair<>("username",username);
		pair.put("token",password);
		return Response.success(pair.toJSONString());
	}

	/**
	 * @Author: ZYJ
	 * @Date: 2022/04/01
	 * @Remark: 登录 API
	 */
	@PostMapping("/login")
	@ParamsCheck(
			params = {
					@Param(name = "username",regex = Rules.NUM_CHAR_LOW),
					@Param(name = "password",regex = Rules.NUM_CHAR_LOW_32)
			}
	)
	public Response<String> login(String username,String password){
		userService.login(username,password);
		Pair<String,String> pair = new Pair<>("username",username);
		pair.put("token",password);
		return Response.success(pair.toJSONString());
	}
}