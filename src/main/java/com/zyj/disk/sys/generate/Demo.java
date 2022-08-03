package com.zyj.disk.sys.generate;

import java.io.IOException;
import com.zyj.disk.sys.annotation.GenerateParam;
import lombok.Getter;

@Getter
public final class Demo{
	@GenerateParam(primary = true)
	Integer id;

	@GenerateParam(length = "(13)")
	Integer userId;

	@GenerateParam(length = "(32)")
	String fileCategoryHash;

	public static void main(String[] args)throws InstantiationException,IllegalAccessException,IOException{
		new Generate(Demo.class,"fileCategory").start(FileTypeSet.sql());
	}
}