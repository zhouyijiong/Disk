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

	public static void main(String[] args) {
		long p = 17;
		long q = 23;
		long sum = p*q;
		long e = 3;
		long c = (p-1)*(q-1);
		long d = 235;


		System.out.println((e * d) % c);

		//PBK: (e,sum)    PRK: (d,sum)

		/* 加密 */
		int m = 'a';//314
		System.out.println(Math.pow(m,e));
		long m1 = (long)(Math.pow(m,e) % sum);
		System.out.println(m1);

		/* 解密 */
		//System.out.println(Math.pow(m1,d) % sum);
		System.out.println(test(m1,d,sum));
	}

	public static long test(long g,long x,long p){
		long c = g % p;
		long d = x;
		long r = 1;
		while(d > 0){
			if(d % 2 == 1){
				r = (r*c) % p;
			}
			d = d/2;
			c = (c * c) % p;
		}
		return r;
	}
}