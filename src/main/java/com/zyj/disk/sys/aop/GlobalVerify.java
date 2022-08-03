package com.zyj.disk.sys.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import com.alibaba.fastjson.JSONObject;
import com.zyj.disk.entity.user.User;
import com.zyj.disk.sys.annotation.verify.ParamsCheck;
import com.zyj.disk.sys.annotation.verify.ParamsCheck.Param;
import com.zyj.disk.sys.annotation.verify.Access;
import com.zyj.disk.sys.entity.Rules;
import com.zyj.disk.sys.exception.client.Client;
import com.zyj.disk.sys.exception.develop.Develop;
import com.zyj.disk.sys.exception.server.ServerException;
import com.zyj.disk.sys.identity.IdentitySet;
import com.zyj.disk.sys.tool.AOPTool;
import com.zyj.disk.sys.tool.structure.HashPair;
import com.zyj.disk.sys.tool.structure.Pair;
import com.zyj.disk.sys.tool.structure.ResponsiveCache;
import com.zyj.disk.sys.tool.encryption.token.Token;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@RequiredArgsConstructor
public final class GlobalVerify {
    private final AOPTool aopTool;

    private static final ResponsiveCache<String, Pair<String, Param>> paramCache =
            new ResponsiveCache<>(2048, 60 * 60 * 24);

    public static User current;

    @Around("@annotation(access)")
    public Object global(ProceedingJoinPoint joinPoint, Access access) {
        String token = null;
        HttpServletRequest request = aopTool.getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return "login/login";
        for (Cookie item : cookies) {
            if (!"token".equals(item.getName())) continue;
            token = item.getValue();
            break;
        }
        if (token == null) return "login/login";
        try {
            String str = Token.parse(token);
            System.out.println(str);
            JSONObject jsonObject = JSONObject.parseObject(str);
            IdentitySet identitySet = (IdentitySet) Token.deSerializeParam(jsonObject.get("identity").toString());
            if(!identitySet.identity.check(access.identity())) throw Client.IDENTITY_VERIFY_FAIL.e;
            current = (User) Token.deSerializeParam(jsonObject.get("user").toString());
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(0);
            aopTool.getResponse().addCookie(cookie);
            throw new ServerException(throwable);
        }
    }

    @Around("@annotation(paramsCheck)")
    public Object global(ProceedingJoinPoint joinPoint, ParamsCheck paramsCheck) {
        Method method = aopTool.getMethod(joinPoint);
        String key = joinPoint.getThis() + method.getName() + paramsCheck;
        Pair<String, Param> methodParamsCheck = paramCache.get(key);
        if (methodParamsCheck == null) {
            methodParamsCheck = new HashPair<>();
            Param[] params = paramsCheck.value();
            for (Param param : params) methodParamsCheck.put(param.name(), param);
            paramCache.put(key, methodParamsCheck, 129600);
        }
        HttpServletRequest request = aopTool.getRequest();
        for (Parameter parameter : method.getParameters()) {
            String name = parameter.getName();
            Param param = methodParamsCheck.get(name);
            if (param == null) continue;
            String value = request.getParameter(name);
            if (param.required() && value == null)
                throw Develop.PARAM_REQUIRED.e.addArgs(name);
            if (param.regex() != Rules.NULL && !param.regex().rules.matcher(value).matches())
                throw Develop.PARAM_REGEX_VERIFY_FAIL.e.addArgs(name, value);
            int length = param.length();
            if (length != -1 && value.trim().length() != length)
                throw Develop.PARAM_LENGTH_ERROR.e.addArgs(name, value);
        }
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw new ServerException(throwable);
        }
    }
}