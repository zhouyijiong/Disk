package com.zyj.disk.sys.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import com.zyj.disk.sys.annotation.verify.ParamsCheck;
import com.zyj.disk.sys.annotation.verify.ParamsCheck.Param;
import com.zyj.disk.sys.annotation.verify.Access;
import com.zyj.disk.sys.entity.Rules;
import com.zyj.disk.sys.exception.GlobalException;
import com.zyj.disk.sys.exception.User;
import com.zyj.disk.sys.tool.AOPTool;
import com.zyj.disk.sys.tool.structure.HashPair;
import com.zyj.disk.sys.tool.structure.Pair;
import com.zyj.disk.sys.tool.structure.ResponsiveCache;
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

	private static final ResponsiveCache<String,Pair<String,Param>> paramCache =
			new ResponsiveCache<>(2048,60 * 60 * 24);

	@Around("@annotation(access)")
	public Object global(ProceedingJoinPoint joinPoint,Access access){
		HttpServletRequest request = aopTool.getRequest();
		//IdentitySet.check(access.identity(),aopTool.getRequest().getAttribute("identity"));
		try{
			String head = request.getHeader("token");
			return head == null || "null".equals(head) ? "login/login" : joinPoint.proceed();
		}catch(Throwable throwable){
			throw new GlobalException(throwable);
		}
	}

	@Around("@annotation(paramsCheck)")
	public Object global(ProceedingJoinPoint joinPoint,ParamsCheck paramsCheck){
		Method method = aopTool.getMethod(joinPoint);
		String key = joinPoint.getThis() + method.getName() + paramsCheck;
		Pair<String,Param> methodParamsCheck = paramCache.get(key);
		if(methodParamsCheck == null){
			methodParamsCheck = new HashPair<>();
			Param[] params = paramsCheck.params();
			for(Param param : params) methodParamsCheck.put(param.name(),param);
			paramCache.put(key,methodParamsCheck,129600);
		}
		for(Parameter parameter : method.getParameters()){
			String name = parameter.getName();
			HttpServletRequest request = aopTool.getRequest();
			Param param = methodParamsCheck.get(name);
			if(param == null) continue;
			String value = request.getParameter(name);
			if(param.required() && value == null)
				throw new GlobalException(User.REQ_PARAM_REQUIRED,name);
			if(param.regex() != Rules.NULL && !param.regex().rules.matcher(value).matches())
				throw new GlobalException(User.REQ_PARAM_REGEX_ERROR,name,value);
			int length = param.length();
			if(length != -1 && value.trim().length() != length)
				throw new GlobalException(User.REQ_PARAM_LEN_ERROR,name,value);
		}
		try{
			return joinPoint.proceed();
		}catch(Throwable throwable){
			throw new GlobalException(throwable);
		}
	}
}