package com.zyj.disk.sys.hikari.mapper.operate;

import com.zyj.disk.sys.annotation.mapper.base.Delete;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: ZYJ
 * @Date: 2022/6/21 13:16
 * @Remark:
 */
public interface DeleteOperate{
    boolean deleteCheck(ProceedingJoinPoint joinPoint, Delete delete);

    String deleteExplain(ProceedingJoinPoint joinPoint,Delete delete);
}