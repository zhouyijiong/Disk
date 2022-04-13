package com.zyj.disk.sys.generate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.zyj.disk.sys.annotation.GenerateParam;
import com.zyj.disk.sys.generate.file.*;

public final class Generate{
	private static final List<FieldInfo> fieldInfos = new ArrayList<>();

	public static void start(Class<?> clazz,String name,FileTypes...fileTypes)
			throws InstantiationException,IllegalAccessException{
		String path = init(clazz);
		Arrays.stream(fileTypes).forEach(fileType -> {
			switch(fileType){
				case ENTITY:
					new Entity(name,fieldInfos).create(path);
					break;
				case Mapper:
					new Mapper(name,fieldInfos).create(path);
					break;
				case Service:
					new Service(name,fieldInfos).create(path);
					break;
				case Controller:
					new Controller(name,fieldInfos).create(path);
					break;
				case SQL:new SQL(name,fieldInfos).create(path);
			}
		});
	}

	private static String init(Class<?> clazz)throws InstantiationException,IllegalAccessException{
		Object obj = clazz.newInstance();
		String className = clazz.getName();
		Field[] fields = clazz.getDeclaredFields();
		Class<GenerateParam> annotationClass = GenerateParam.class;
		String projectPath = className.substring(0,className.indexOf("sys"));
		for(Field field : fields){
			if(field.isAnnotationPresent(annotationClass)){
				field.setAccessible(true);
				GenerateParam annotation = field.getAnnotation(annotationClass);
				FieldInfo fieldInfo = new FieldInfo(
						getType(0,field,null),
						field.getName(),
						field.get(obj),
						annotation.primary(),
						annotation.unique(),
						annotation.required(),
						getType(1,field,annotation.length()));
				fieldInfos.add(fieldInfo);
			}
		}
		return projectPath.replace(".","/");
	}

	private static String getType(int status,Field field,String length){
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