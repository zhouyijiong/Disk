package com.zyj.disk.sys.hikari.mapper.operate;

import com.zyj.disk.sys.annotation.mapper.base.Select;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: ZYJ
 * @Date: 2022/6/21 13:19
 * @Remark:
 */
public interface SelectOperate{
    boolean selectCheck(ProceedingJoinPoint joinPoint,Select select);

    String selectExplain(ProceedingJoinPoint joinPoint,Select select);
}