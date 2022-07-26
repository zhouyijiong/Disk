package com.zyj.disk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    /**
     * 1.整合 Redis, 并大改 cache
     * —— 2.sql 大改
     * 3.initSql不需要手动输入
     * <p>
     * 附加
     * 1.使用 APT 技术编译时解析注解
     * 2.制作前端拖动预设组件自适应框架
     * <p>
     * 无状态
     * 1.用户的信息不进行存储，每一位新用户有一个 UUID
     * 2.用户在每次退出系统前可以选择 令牌登出,令牌的字节小于一定数量时返回字符串,超过临界点返回图片
     * 3.用户可以选择用新身份进入系统但会损失先前的信息,或者用本地令牌访问
     * 4.所有请求参数统一成 json 并加密
     * 5.生成超长的token,用户信息存储在token中,每次更新都会重置 token ,token 格式还没定
     */
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}