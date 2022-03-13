package com.zyj.disk.sys.entity;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public final class ClassScanner{
    public Set<Class<?>> getFilterClass(String path,Class<? extends Annotation> annotation)throws ClassNotFoundException{
        Set<Class<?>> set = new LinkedHashSet<>();
        getAllFile(new File(path),set);
        Set<Class<?>> filterSet = new HashSet<>();
        for(Class<?> clazz : set) if(clazz.isAnnotationPresent(annotation)) filterSet.add(clazz);
        return filterSet;
    }

    private void getAllFile(File file,Set<Class<?>> set)throws ClassNotFoundException{
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if(files == null) return;
            for(File item : files) getAllFile(item,set);
        }else{
            String filePath = file.getPath();
            String className = (filePath.substring(14,filePath.length() - 5)).replace("\\",".");
            Class<?> clazz = Class.forName(className);
            set.add(clazz);
        }
    }
}