package com.zyj.disk.sys.aop;

import cn.dev33.satoken.util.SaResult;
import com.zyj.disk.sys.entity.Record;
import com.zyj.disk.sys.exception.GlobalException;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

@Configuration
@ControllerAdvice
@ResponseBody
public class GlobalHandler{
    private final Record record = Record.initialize(this.getClass());

    public static final class Result extends SaResult{
        private static final long serialVersionUID = -4408646213088219736L;
        public Result(GlobalException e){
            super(e.getCode(),e.getMsg(),null);
        }
    }

    @ExceptionHandler(GlobalException.class)
    public Map<String,Object> batteryException(GlobalException exception){
        record.error(exception);
        return new Result(exception);
    }
}