package com.zyj.disk.sys.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志处理
 */
public final class Record {
    private final Logger logger;

    public Record(Class<?> clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    public void error(RuntimeException e) {
        logger.error("错误信息:{}", e.getMessage());
    }

    public void error(Exception e) {
        logger.error("method:{},error:{}", Thread.currentThread().getStackTrace()[2].getMethodName(), e.toString());
    }
}