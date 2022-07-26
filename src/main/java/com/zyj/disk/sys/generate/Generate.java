package com.zyj.disk.sys.generate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import com.zyj.disk.sys.annotation.GenerateParam;
import com.zyj.disk.sys.generate.file.*;

public final class Generate{
	private final String path;
	private static final List<FieldInfo> fieldInfos = new ArrayList<>();

	public Generate(Class<?> clazz,String name) throws InstantiationException,IllegalAccessException{
		this.path = init(clazz);
		FileType.init(name,fieldInfos);
	}

	public void start(FileType...fileType){
		for(FileType type : fileType) type.create(path);
	}

	private String init(Class<?> clazz) throws InstantiationException,IllegalAccessException{
		String className = clazz.getName();
		Class<GenerateParam> annotationClass = GenerateParam.class;
		for(Field field : clazz.getDeclaredFields()){
			if(field.isAnnotationPresent(annotationClass)){
				field.setAccessible(true);
				GenerateParam annotation = field.getAnnotation(annotationClass);
				FieldInfo fieldInfo = new FieldInfo(
						getType(0,field,null),
						field.getName(),
						field.get(clazz.newInstance()),
						annotation.primary(),
						annotation.unique(),
						annotation.required(),
						getType(1,field,annotation.length()));
				fieldInfos.add(fieldInfo);
			}
		}
		return className.substring(0,className.indexOf("sys")).replace(".","/");
	}

	private String getType(int status,Field field,String length){
		switch(field.getType().getSimpleName().hashCode()){
			case 2086184     : return status == 0 ? "Byte"       : "tinyint"    + length;
			case 79860828    : return status == 0 ? "Short"      : "smallint"   + length;
			case -672261858  : return status == 0 ? "Integer"    : "int"        + length;
			case 2374300     : return status == 0 ? "Long"       : "bigint"     + length;
			case 67973692    : return status == 0 ? "Float"      : "float"      + length;
			case 2052876273  : return status == 0 ? "Double"     : "double"     + length;
			case -1808118735 : return status == 0 ? "String"     : "varchar"    + length;
			case 1438607953  : return status == 0 ? "BigDecimal" : "BigDecimal" + length;
			default          : return null;
		}
	}
}