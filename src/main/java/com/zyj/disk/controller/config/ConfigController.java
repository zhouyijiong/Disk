package com.zyj.disk.controller.config;

import com.zyj.disk.sys.entity.Response;
import com.zyj.disk.sys.tool.encryption.rsa.RsaSet;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置控制器
 */
@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
public class ConfigController {
    /**
     * 获取公钥
     */
    @GetMapping("/getPublicKey")
    public Response<String> getPublicKey() {
        return Response.success(RsaSet.REQUEST.RSA.PK.getProduct().toString());
    }
}