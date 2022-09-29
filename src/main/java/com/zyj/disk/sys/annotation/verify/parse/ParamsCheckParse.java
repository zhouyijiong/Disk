package com.zyj.disk.sys.annotation.verify.parse;

import com.zyj.disk.sys.annotation.verify.ParamsCheck;
import com.zyj.disk.sys.entity.Record;
import com.zyj.disk.sys.entity.Rules;
import com.zyj.disk.sys.exception.client.ClientError;
import com.zyj.disk.sys.exception.develop.DevelopError;
import com.zyj.disk.sys.exception.server.ServerError;
import com.zyj.disk.sys.exception.server.ServerException;
import com.zyj.disk.sys.tool.AOPTool;
import com.zyj.disk.sys.tool.JsonTool;
import com.zyj.disk.sys.tool.encryption.codec.Base64;
import com.zyj.disk.sys.tool.encryption.rsa.RsaSet;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

public interface ParamsCheckParse {
    Record RECORD = new Record(ParamsCheckParse.class);

    static Object parse(Annotation annotation, ProceedingJoinPoint pjp, AOPTool aopTool) {
        ParamsCheck paramsCheck = (ParamsCheck) annotation;
        ServletRequestAttributes servletRequestAttributes = aopTool.getHttpServletContext();
        String params = RsaSet.REQUEST.RSA.SK.decrypt(servletRequestAttributes.getRequest().getParameter("params"));
        if (!Rules.BASE64.rules.matcher(params).matches()) {
            RECORD.info(params);
            throw ClientError.KEY_EXPIRED;
        }
        params = Base64.decodeToString(params);
        Map<String, Object> map = JsonTool.toMap(params);
        if (map == null) throw ClientError.INFO_TAMPER;
        for (ParamsCheck.Param check : paramsCheck.value()) {
            String name = check.name();
            Object value = map.get(name);
            if (check.required() && value == null) throw DevelopError.PARAM_REQUIRED.addArgs(name);
            String valStr = value.toString();
            if (check.regex() != Rules.NULL && !check.regex().rules.matcher(valStr).matches())
                throw DevelopError.PARAM_REGEX_VERIFY_FAIL.addArgs(name, value);
            int length = check.length();
            if (length != -1 && valStr.trim().length() != length)
                throw DevelopError.PARAM_LENGTH_ERROR.addArgs(name, value);
        }
        Object[] args = pjp.getArgs();
        Method method = aopTool.getMethod(pjp);
        if (paramsCheck.isSet()) {
            args[0] = JsonTool.fromJson(params, method.getParameterTypes()[0]);
        } else {
            String[] paramNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);
            if (paramNames == null) throw ServerError.LOCAL_PARAM_LOOS;
            for (int i = 0; i < paramNames.length; ++i) {
                String name = paramNames[i];
                if (map.get(name) != null) args[i] = map.get(name);
            }
        }
        try {
            return pjp.proceed(args);
        } catch (Throwable throwable) {
            throw new ServerException(throwable);
        }
    }
}