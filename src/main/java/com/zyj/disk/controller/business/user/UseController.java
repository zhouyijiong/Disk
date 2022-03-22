package com.zyj.disk.controller.business.user;

import com.zyj.disk.service.user.UserService;
import com.zyj.disk.sys.annotation.RequestParam;
import com.zyj.disk.sys.annotation.verify.Token;
import com.zyj.disk.sys.entity.Rules;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UseController{
	private final UserService userService;

	@Token
	@PostMapping("/login")
	public Map<String,Object> login(
			@RequestParam(regex = Rules.NUM_CHAR_LOW) String username,
			@RequestParam(regex = Rules.NUM_CHAR_LOW_32) String password){
		return userService.login(username,password);
	}

	@Token
	@PostMapping("/registered")
	public Map<String,Object> registered(
			@RequestParam(regex = Rules.NUM_CHAR_LOW) String username,
			@RequestParam(regex = Rules.NUM_CHAR_LOW_32) String password){
		return userService.registered(username,password);
	}
}