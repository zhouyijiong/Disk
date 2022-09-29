package com.zyj.disk.sys.annotation.verify.parse;

import com.zyj.disk.sys.annotation.verify.Level;
import com.zyj.disk.sys.entity.IdentitySet;
import com.zyj.disk.sys.exception.client.ClientError;
import com.zyj.disk.sys.exception.server.ServerException;
import com.zyj.disk.sys.tool.AOPTool;
import com.zyj.disk.sys.tool.encryption.codec.Codec;
import com.zyj.disk.sys.tool.encryption.token.Token;
import com.zyj.disk.sys.tool.encryption.token.Tokens;
import com.zyj.disk.sys.tool.structure.Pair;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.annotation.Annotation;

public interface LevelParse {
    static Object parse(Annotation annotation, ProceedingJoinPoint pjp, AOPTool aopTool) {
        Level level = (Level) annotation;
        String token = aopTool.getRequest().getHeader("token");
        if (token == null) throw ClientError.TOKEN_NOT_EXISTS;
        if ((token = Token.parse(token)) == null) throw ClientError.TOKEN_EXPIRED;
        try {
            Pair<String, String> pair = Pair.fromPair(token);
            if (pair == null) throw ClientError.INFO_TAMPER;
            IdentitySet identitySet = Codec.decodingObj(pair.get("identity"), IdentitySet.class);
            if (identitySet == null) throw ClientError.INFO_TAMPER;
            if (!identitySet.check(level.value())) throw ClientError.IDENTITY_VERIFY_FAIL;
            Tokens.TOKEN.set(pair.get("user"));
            return pjp.proceed();
        } catch (Throwable throwable) {
            throw new ServerException(throwable);
        }
    }
}