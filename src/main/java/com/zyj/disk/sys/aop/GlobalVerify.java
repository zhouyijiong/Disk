package com.zyj.disk.sys.aop;

import com.zyj.disk.sys.annotation.verify.Level;
import com.zyj.disk.sys.annotation.verify.ParamsCheck;
import com.zyj.disk.sys.annotation.verify.Access;
import com.zyj.disk.sys.entity.AnnotationParse;
import com.zyj.disk.sys.entity.Record;
import com.zyj.disk.sys.tool.AOPTool;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public final class GlobalVerify {
    private final AOPTool aopTool;
    private final AnnotationParse annotationParse;
    private static final Record record = new Record(GlobalVerify.class);

    @Around("@annotation(access)")
    public Object global(ProceedingJoinPoint pjp, Access access) {
//        if (IdentitySet.UNLIMITED.checkOnly(access.identity())) return access.path();
//        Cookie[] cookies = aopTool.getRequest().getCookies();
//        if (cookies == null) return "login/login";
//        Cookie identityCookie = null;
//        for (Cookie item : cookies) {
//            if ("identity".equals(item.getName())) {
//                identityCookie = item;
//                break;
//            }
//        }
//        if (identityCookie == null) return "login/login";
//        String identity = identityCookie.getValue();
//        try {
//            if ((identity = Token.parse(identity)) == null) throw ClientError.TOKEN_EXPIRED;
//            if ((identity = XOR.decrypt(identity)) == null) throw ClientError.INFO_TAMPER;
//            if (!IdentitySet.check(identity, access.identity())) throw ClientError.IDENTITY_VERIFY_FAIL;
//            return access.path();
//        } catch (Throwable throwable) {
//            identityCookie.setPath("/");
//            identityCookie.setMaxAge(0);
//            aopTool.getResponse().addCookie(identityCookie);
//            throw new ServerException(throwable);
//        }
        return annotationParse.run(Access.class, access, pjp, aopTool);
    }

    @Around("@annotation(level)")
    public Object global(ProceedingJoinPoint pjp, Level level) {
//        String token = aopTool.getRequest().getHeader("token");
//        if (token == null) throw ClientError.TOKEN_NOT_EXISTS;
//        if ((token = Token.parse(token)) == null) throw ClientError.TOKEN_EXPIRED;
//        try {
//            Pair<String, String> pair = Pair.fromPair(token);
//            if (pair == null) throw ClientError.INFO_TAMPER;
//            IdentitySet identitySet = Codec.decodingObj(pair.get("identity"), IdentitySet.class);
//            if (identitySet == null) throw ClientError.INFO_TAMPER;
//            if (!identitySet.check(level.value())) throw ClientError.IDENTITY_VERIFY_FAIL;
//            Tokens.token.set(pair.get("user"));
//            return pjp.proceed();
//        } catch (Throwable throwable) {
//            throw new ServerException(throwable);
//        }
        return annotationParse.run(Level.class, level, pjp, aopTool);
    }

    @Around("@annotation(paramsCheck)")
    public Object global(ProceedingJoinPoint pjp, ParamsCheck paramsCheck) {
//        ServletRequestAttributes servletRequestAttributes = aopTool.getHttpServletContext();
//        String params = RsaSet.REQUEST.RSA.SK.decrypt(servletRequestAttributes.getRequest().getParameter("params"));
//        if (!Rules.BASE64.rules.matcher(params).matches()) {
//            record.info(params);
//            throw ClientError.KEY_EXPIRED;
//        }
//        params = Base64.decodeToString(params);
//        Map<String, Object> map = GsonTool.toMap(params);
//        if (map == null) throw ClientError.INFO_TAMPER;
//        for (Param check : paramsCheck.value()) {
//            String name = check.name();
//            Object value = map.get(name);
//            if (check.required() && value == null) throw DevelopError.PARAM_REQUIRED.addArgs(name);
//            String valStr = value.toString();
//            if (check.regex() != Rules.NULL && !check.regex().rules.matcher(valStr).matches())
//                throw DevelopError.PARAM_REGEX_VERIFY_FAIL.addArgs(name, value);
//            int length = check.length();
//            if (length != -1 && valStr.trim().length() != length)
//                throw DevelopError.PARAM_LENGTH_ERROR.addArgs(name, value);
//        }
//        Object[] args = pjp.getArgs();
//        Method method = aopTool.getMethod(pjp);
//        if (paramsCheck.isSet()) {
//            args[0] = GsonTool.fromJson(params, method.getParameterTypes()[0]);
//        } else {
//            int index = -1;
//            String[] paramNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);
//            for (String name : paramNames) {
////                if("javax.servlet.http.HttpServletRequest".equals(parameter.getParameterizedType().getTypeName())){
////                    args[++index] = servletRequestAttributes.getRequest();
////                    continue;
////                }
////                if("javax.servlet.http.HttpServletResponse".equals(parameter.getParameterizedType().getTypeName())){
////                    args[++index] = servletRequestAttributes.getResponse();
////                    continue;
////                }
//                args[++index] = map.get(name);
//            }
//        }
//        try {
//            return pjp.proceed(args);
//        } catch (Throwable throwable) {
//            throw new ServerException(throwable);
//        }
        return annotationParse.run(ParamsCheck.class, paramsCheck, pjp, aopTool);
    }
}