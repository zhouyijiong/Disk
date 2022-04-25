package com.zyj.disk.controller.user;

import com.zyj.disk.service.user.UserService;
import com.zyj.disk.sys.annotation.verify.ParamsCheck;
import com.zyj.disk.sys.annotation.verify.ParamsCheck.Param;
import com.zyj.disk.sys.entity.Rules;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

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
		cookie = true,
		params = {
				@Param(name = "username",regex = Rules.NUM_CHAR_LOW),
				@Param(name = "password",regex = Rules.NUM_CHAR_LOW_32)
		})
	public Map<String,Object> registered(String username,String password){
		return userService.registered(username,password);
	}

	/**
	 * @Author: ZYJ
	 * @Date: 2022/04/01
	 * @Remark: 登录 API
	 */
	@PostMapping("/login")
	@ParamsCheck(
		cookie = true,
		params = {
				@Param(name = "username",regex = Rules.NUM_CHAR_LOW),
				@Param(name = "password",regex = Rules.NUM_CHAR_LOW_32)
		})
	public Map<String,Object> login(String username,String password){
		return userService.login(username,password);
	}
}