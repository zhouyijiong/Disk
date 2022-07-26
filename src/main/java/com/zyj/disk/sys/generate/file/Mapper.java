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
		bos.write(imports("com.baomidou.mybatisplus.core.mapper.BaseMapper;"));
		bos.write(imports(packageName + "entity." + sourceName.toLowerCase() + "." + oneStrToUp(sourceName) + ";"));
		bos.write(imports("org.apache.ibatis.annotations.Mapper;"));
		bos.write("\n".getBytes(StandardCharsets.UTF_8));
		bos.write(annotations("Mapper"));
	}
	
	@Override
	void createBody(BufferedOutputStream bos)throws IOException{
		bos.write(("\npublic interface " + className + " extends BaseMapper<" + oneStrToUp(sourceName) + ">{}").getBytes(StandardCharsets.UTF_8));
	}
}