package com.zyj.disk.sys.annotation.verify.parse;

import com.zyj.disk.sys.annotation.verify.Access;
import com.zyj.disk.sys.entity.IdentitySet;
import com.zyj.disk.sys.exception.client.ClientError;
import com.zyj.disk.sys.exception.server.ServerException;
import com.zyj.disk.sys.tool.AOPTool;
import com.zyj.disk.sys.tool.encryption.token.Token;
import com.zyj.disk.sys.tool.encryption.xor.XOR;
import org.aspectj.lang.ProceedingJoinPoint;

import javax.servlet.http.Cookie;
import java.lang.annotation.Annotation;

public interface AccessParse {
    static Object parse(Annotation annotation, ProceedingJoinPoint pjp, AOPTool aopTool) {
        Access access = (Access) annotation;
        if (IdentitySet.UNLIMITED.checkOnly(access.identity())) return access.path();
        Cookie[] cookies = aopTool.getRequest().getCookies();
        if (cookies == null) return "login/login";
        Cookie identityCookie = null;
        for (Cookie item : cookies) {
            if ("identity".equals(item.getName())) {
                identityCookie = item;
                break;
            }
        }
        if (identityCookie == null) return "login/login";
        String identity = identityCookie.getValue();
        try {
            if ((identity = Token.parse(identity)) == null) throw ClientError.TOKEN_EXPIRED;
            if ((identity = XOR.decrypt(identity)) == null) throw ClientError.INFO_TAMPER;
            if (!IdentitySet.check(identity, access.identity())) throw ClientError.IDENTITY_VERIFY_FAIL;
            return access.path();
        } catch (Throwable throwable) {
            identityCookie.setPath("/");
            identityCookie.setMaxAge(0);
            aopTool.getResponse().addCookie(identityCookie);
            throw new ServerException(throwable);
        }
    }
}