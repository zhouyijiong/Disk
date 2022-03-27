package com.zyj.disk.sys.entity;

import com.zyj.disk.sys.hikari.mapper.match.ArrayEntity;
import com.zyj.disk.sys.hikari.mapper.match.Entity;
import com.zyj.disk.sys.hikari.mapper.match.Match;
import com.zyj.disk.sys.hikari.mapper.match.No;
import com.zyj.disk.sys.hikari.mapper.match.Param;

/**
 * @Author: ZYJ
 * @Date: 2022/3/22 9:29
 * @Remark: 映射匹配
 */
public enum MapperMatch{
    /**
     * 无参数
     * 不匹配,不能传参
     * 返回一条SQL
     */
    NO(No.MATCH),

    /**
     * 有参数
     * 通过'#{}'对普通参数进行匹配 或 通过 ${*.*} 对 'Entity' 参数匹配
     * 返回一条SQL
     */
    PARAM(Param.MATCH),

    /**
     * 无参数
     * 遍历'Entity'参数,取' !=null 的参数 == 判断'
     * 返回一条SQL
     */
    ENTITY(Entity.MATCH),

    /**
     * 无参数
     * 遍历'Entity Array'数组,循环获取每项中' !=null 的参数 == 判断'
     * 返回一组SQL
     */
    ARRAY_ENTITY(ArrayEntity.MATCH)
    ;

    public final Match MATCH;

    MapperMatch(Match MATCH){this.MATCH = MATCH;}
}