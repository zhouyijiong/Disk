package com.zyj.disk.sys.aop;

import com.zyj.disk.sys.entity.Record;
import com.zyj.disk.sys.entity.Result;
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

    @ExceptionHandler(GlobalException.class)
    public Map<String,Object> batteryException(GlobalException exception){
        record.error(exception);
        return Result.init().result(exception);
    }
}