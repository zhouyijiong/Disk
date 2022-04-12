package com.zyj.disk.sys.aop;

import com.zyj.disk.sys.annotation.mapper.MapperProxy;
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
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Component
public final class ServiceBeanDefinitionRegistry implements BeanDefinitionRegistryPostProcessor,
                                                            ResourceLoaderAware,
                                                            ApplicationContextAware{

    @Override
    @SneakyThrows
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry){
        for(Class<?> clazz : getFilterClass(this.getClass().getName())){
            GenericBeanDefinition definition
                    = (GenericBeanDefinition)BeanDefinitionBuilder.genericBeanDefinition(clazz).getRawBeanDefinition();
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

    private Set<Class<?>> getFilterClass(String className)throws ClassNotFoundException{
        String rootPath = "src/main/java/";
        String projectPath = className.substring(0,className.indexOf("sys") - 1).replace(".","/");
        Set<Class<?>> set = new HashSet<>();
        getAllFile(new File(rootPath + projectPath),set);
        Set<Class<?>> filterSet = new HashSet<>();
        for(Class<?> clazz : set) if(clazz.isAnnotationPresent(MapperProxy.class)) filterSet.add(clazz);
        return filterSet;
    }

    private void getAllFile(File file,Set<Class<?>> set)throws ClassNotFoundException{
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if(files == null) return;
            for(File item : files) getAllFile(item,set);
        }else{
            String filePath = file.getPath();
            String classFullName = filePath.substring("src/main/java/".length(),filePath.length() - ".java".length());
            String className = classFullName.replace("\\",".");
            Class<?> clazz = Class.forName(className);
            set.add(clazz);
        }
    }
}