package com.zyj.disk.sys.hikari.mapper.operate;

import com.zyj.disk.sys.annotation.mapper.base.Insert;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: ZYJ
 * @Date: 2022/6/21 13:16
 * @Remark:
 */
public interface InsertOperate{
    boolean insertCheck(ProceedingJoinPoint joinPoint, Insert insert);

    String insertExplain(ProceedingJoinPoint joinPoint,Insert insert);

    default String formatTarget(String[] target){
        if(target.length == 1) return "(" + target[0] + ")";
        StringBuilder sb = new StringBuilder(target.length * 11);
        sb.append("(").append(target[0]);
        for(int i=1;i<target.length;++i) sb.append(",").append(target[i]);
        return sb.append(")").toString();
    }
}