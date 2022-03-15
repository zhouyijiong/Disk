package com.zyj.disk.sys.aop;

import com.zyj.disk.sys.annotation.mapper.MapperProxy;
import com.zyj.disk.sys.entity.ClassScanner;
import com.zyj.disk.sys.entity.MapperFactory;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public final class ServiceBeanDefinitionRegistry implements BeanDefinitionRegistryPostProcessor,ResourceLoaderAware,ApplicationContextAware{
    @Override
    @SneakyThrows
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)throws BeansException{
        String classname = this.getClass().getName();
        String path = "src/main/java/" + classname.substring(0,classname.indexOf("sys") - 1).replace(".","/");
        for(Class<?> clazz : new ClassScanner().getFilterClass(path,MapperProxy.class)){
            GenericBeanDefinition definition = (GenericBeanDefinition)BeanDefinitionBuilder.genericBeanDefinition(clazz).getRawBeanDefinition();
            definition.getConstructorArgumentValues().addGenericArgumentValue(clazz);
            definition.setBeanClass(MapperFactory.class);
            definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
            registry.registerBeanDefinition(clazz.getSimpleName(),definition);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)throws BeansException{}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)throws BeansException{}

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader){}
}