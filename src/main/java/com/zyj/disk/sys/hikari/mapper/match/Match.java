package com.zyj.disk.sys.hikari.mapper.match;

import com.zyj.disk.sys.tool.ClassTool;
import com.zyj.disk.sys.tool.structure.ResponsiveCache;

/**
 * @Author: ZYJ
 * @Date: 2022/6/20 13:59
 * @Remark:
 */
public interface Match{
    ClassTool classTool = new ClassTool();

    ResponsiveCache<String,String> sqlCache = new ResponsiveCache<>(3000,60);
}