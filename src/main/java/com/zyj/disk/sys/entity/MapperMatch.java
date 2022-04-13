package com.zyj.disk.sys.entity;

import com.zyj.disk.sys.hikari.mapper.match.*;
import lombok.AllArgsConstructor;

/**
 * @Author: ZYJ
 * @Date: 2022/3/22 9:29
 * @Remark: 映射匹配
 */
@AllArgsConstructor
public enum MapperMatch{
    /**
     * 无参数
     * 不匹配,不能传参
     * 返回一条SQL
     */
    NO(new No()),

    /**
     * 有参数
     * 通过'#{}'对普通参数进行匹配 或 通过 ${*.*} 对 'Entity' 参数匹配
     * 返回一条SQL
     */
    PARAM(new Param()),

    /**
     * 无参数
     * 遍历'Entity'参数,取' !=null 的参数 == 判断'
     * 返回一条SQL
     */
    ENTITY(new Entity()),

    /**
     * 无参数
     * 遍历'Entity Array'数组,循环获取每项中' !=null 的参数 == 判断'
     * 返回一组SQL
     */
    ARRAY_ENTITY(new ArrayEntity());

    public final Match match;
}