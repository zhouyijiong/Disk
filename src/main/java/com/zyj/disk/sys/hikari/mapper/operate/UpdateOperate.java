package com.zyj.disk.sys.hikari.mapper.operate;

import com.zyj.disk.sys.annotation.mapper.base.Update;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: ZYJ
 * @Date: 2022/6/21 13:18
 * @Remark:
 */
public interface UpdateOperate{
    boolean updateCheck(ProceedingJoinPoint joinPoint, Update update);

    String updateExplain(ProceedingJoinPoint joinPoint,Update update);
}