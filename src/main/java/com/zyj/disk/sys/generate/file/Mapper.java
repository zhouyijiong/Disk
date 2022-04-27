package com.zyj.disk.sys.generate.file;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author: ZYJ
 * @Date: 2022/4/13
 * @Remark: 生成映射类
 */
public final class Mapper extends FileType{
	public Mapper(){super("Mapper");}

	@Override
	void createHead(BufferedOutputStream bos,String packageName)throws IOException{
		bos.write(packages(packageName + "mapper." + sourceName.toLowerCase()));
		bos.write(imports("org.springframework.stereotype.Component;"));
		bos.write(imports(packageName + "sys.annotation.mapper.MapperProxy;"));
		bos.write("\n".getBytes(StandardCharsets.UTF_8));
		bos.write(annotations("Component"));
		bos.write(annotations("MapperProxy"));
	}
	
	@Override
	void createBody(BufferedOutputStream bos)throws IOException{
		bos.write(("\npublic interface "+ className +"{}").getBytes(StandardCharsets.UTF_8));
	}
}