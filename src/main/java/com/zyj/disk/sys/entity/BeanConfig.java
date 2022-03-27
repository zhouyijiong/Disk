package com.zyj.disk.sys.entity;

import com.zyj.disk.sys.hikari.Actuator;
import com.zyj.disk.sys.hikari.mapper.explain.DeleteMapper;


public class BeanConfig{
    DeleteMapper DeleteMapper(Actuator actuator){
        return new DeleteMapper(actuator);
    }
}