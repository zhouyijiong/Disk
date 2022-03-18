package com.zyj.disk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main{
	//为什么不推荐使用 static 方法 https://www.zhihu.com/question/36615154

	/**
	 * 1.整合 Redis, 并大改 cache
	 * 2.sql 大改
	 * 3.initSql不需要手动输入
	 *
	 * 附加
	 * 1.使用 APT 技术编译时解析注解
	 * 2.制作前端拖动预设组件自适应框架
	 */
	public static void main(String[] args){SpringApplication.run(Main.class,args);}
}