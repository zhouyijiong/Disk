package com.zyj.disk.sys.entity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {
    default T selectOne(QueryWrapper<T> queryWrapper) {
        return com.baomidou.mybatisplus.core.mapper.BaseMapper.super.selectOne(queryWrapper.last("limit 1"));
    }
}