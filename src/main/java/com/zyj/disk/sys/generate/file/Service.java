package com.zyj.disk.sys.generate.file;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author: ZYJ
 * @Date: 2022/4/13
 * @Remark: 生成业务类
 */
public final class Service extends FileType{
	public Service(){super("Service");}

	@Override
	void createHead(BufferedOutputStream bos,String packageName)throws IOException{
		String lowName = sourceName.toLowerCase();
		String oneToUpName = oneStrToUp(sourceName);
		bos.write(packages(packageName + "service." + lowName));
		bos.write(imports("lombok.RequiredArgsConstructor;"));
		bos.write(imports("org.springframework.stereotype.Service;"));
		bos.write(imports(packageName + "mapper." + lowName + "." + oneToUpName + "Mapper;"));
		bos.write("\n".getBytes(StandardCharsets.UTF_8));
		bos.write(annotations("Service"));
		bos.write(annotations("RequiredArgsConstructor"));
	}
	
	@Override
	void createBody(BufferedOutputStream bos)throws IOException{
		String oneToUpName = oneStrToUp(sourceName);
		String mapper = sourceName.toLowerCase() + "Mapper";
		bos.write(("\npublic final class "+ className +"{").getBytes(StandardCharsets.UTF_8));
		bos.write(("\n\tprivate final " + oneToUpName + "Mapper " + mapper + ";").getBytes(StandardCharsets.UTF_8));
		bos.write("\n}".getBytes(StandardCharsets.UTF_8));
	}
}