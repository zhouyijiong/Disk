package com.zyj.disk.controller.config;

import com.zyj.disk.sys.entity.Response;
import com.zyj.disk.sys.tool.encryption.codec.ByteArrayBase64;
import com.zyj.disk.sys.tool.encryption.rsa.RsaSet;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

/**
 * 配置控制器
 */
@RestController
@RequestMapping("/config")
public class ConfigController {
    /**
     * 获取公钥
     */
    @PostMapping("/getPublicKey")
    public Response<String> registered() {
        return Response.success(RsaSet.REQUEST.RSA.PK.getProduct().toString());
    }

    @PostMapping("/test")
    public String test(String num) {
        System.out.println(num);
        System.out.println(RsaSet.REQUEST.RSA.SK.decrypt(ByteArrayBase64.encodeToString(new BigInteger(num).toByteArray())));
        return null;
    }
}