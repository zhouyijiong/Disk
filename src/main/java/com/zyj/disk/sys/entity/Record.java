package com.zyj.disk.sys.entity;

import com.zyj.disk.sys.exception.GlobalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ZYJ
 * @Date: 2022/03/27 23:15
 * @Remark: 日志
 */
public final class Record{
    private final Logger logger;

    private Record(Class<?> clazz){
        logger = LoggerFactory.getLogger(clazz);
    }

    public static Record initialize(Class<?> clazz){
        return new Record(clazz);
    }

    public void error(GlobalException e){
        logger.error("错误信息:{}",e.getMessage());
    }

    public void error(Exception e){
        logger.error("method:{},error:{}",Thread.currentThread().getStackTrace()[2].getMethodName(),e.toString());
    }
}