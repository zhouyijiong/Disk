package com.zyj.disk.sys.entity;

public class BaseEntity implements Cloneable{
    protected BaseEntity clone()throws CloneNotSupportedException{
        return (BaseEntity)super.clone();
    }
}