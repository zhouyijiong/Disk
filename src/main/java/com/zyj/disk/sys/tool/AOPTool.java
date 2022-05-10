package com.zyj.disk.sys.tool;

import com.zyj.disk.sys.exception.Server;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public final class AOPTool{
    public Method getMethod(ProceedingJoinPoint joinPoint){
        return ((MethodSignature)joinPoint.getSignature()).getMethod();
    }

    public HttpServletRequest getRequest(){
        return getHttpServletContext().getRequest();
    }

    public HttpServletResponse getResponse(){
        return getHttpServletContext().getResponse();
    }

    private ServletRequestAttributes getHttpServletContext(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes == null) throw Server.REQUEST_PARAM_LOOS.exception;
        return attributes;
    }
}