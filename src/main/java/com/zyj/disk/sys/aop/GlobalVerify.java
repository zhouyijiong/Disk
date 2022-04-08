package com.zyj.disk.sys.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;
import cn.dev33.satoken.stp.StpUtil;
import com.zyj.disk.sys.annotation.verify.Access;
import com.zyj.disk.sys.annotation.verify.RequestParam;
import com.zyj.disk.sys.annotation.verify.Token;
import com.zyj.disk.sys.entity.Rules;
import com.zyj.disk.sys.exception.Client;
import com.zyj.disk.sys.exception.GlobalException;
import com.zyj.disk.sys.exception.User;
import com.zyj.disk.sys.tool.AOPTool;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@RequiredArgsConstructor
public final class GlobalVerify{
	private final AOPTool aopTool;

	@Around("@annotation(token)")
	public Object global(ProceedingJoinPoint joinPoint,Token token){
		try{
			Object result = joinPoint.proceed();
			StpUtil.login(ThreadLocalRandom.current().nextInt());
			return result;
		}catch(Throwable throwable){
			throw new GlobalException(throwable);
		}
	}

	@Around("@annotation(access)")
	public Object global(ProceedingJoinPoint joinPoint,Access access){
		try{
			return StpUtil.isLogin() ? joinPoint.proceed() : "login/login";
		}catch(Throwable throwable){
			throw new GlobalException(throwable);
		}
	}

	@Around("within(com.zyj.*.controller.business..*)")
	public Object global(ProceedingJoinPoint joinPoint){
		Method method = aopTool.getMethod(joinPoint);
		HttpServletRequest request = aopTool.getRequest();
		Class<RequestParam> annotationClazz = RequestParam.class;
		for(Parameter item : method.getParameters()){
			String key = item.getName();
			String value = request.getParameter(key);
			if(!item.isAnnotationPresent(annotationClazz)) continue;
			RequestParam annotation = item.getAnnotation(annotationClazz);
			if(annotation.required() && value == null) throw new GlobalException(User.REQ_PARAM_REQUIRED,method.getName(),key);
			if(annotation.regex() != Rules.NULL && !annotation.regex().rules.matcher(value).matches())
				throw new GlobalException(Client.REQ_PARAM_REGEX_ERROR);
			int length = annotation.length();
			if(length != -1 && value.trim().length() != length) throw new GlobalException(Client.REQ_PARAM_LEN_ERROR);
		}
		try{
			return joinPoint.proceed();
		}catch(Throwable e){
			throw new GlobalException(e);
		}
	}
}