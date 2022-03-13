package com.zyj.disk.sys.entity;

import org.springframework.beans.factory.FactoryBean;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MapperFactory<T> implements FactoryBean<T>{
    private final Class<?> interfaceType;

    public MapperFactory(Class<?> interfaceType){
        this.interfaceType = interfaceType;
    }

    static class MapperProxy implements InvocationHandler{
        private final Class<?> clazz;

        public MapperProxy(Class<?> clazz){this.clazz = clazz;}

        public Object proxy(){return Proxy.newProxyInstance(clazz.getClassLoader(),new Class<?>[]{clazz},this);}

        @Override
        public Object invoke(Object proxy,Method method,Object[] args)throws Throwable{return method.invoke(clazz,args);}
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public T getObject(){
        return (T) new MapperProxy(interfaceType).proxy();
    }

    @Override
    public Class<?> getObjectType(){return interfaceType;}

    @Override
    public boolean isSingleton(){return true;}
}