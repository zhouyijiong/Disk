package com.zyj.disk.sys.generate;

import java.io.IOException;
import com.zyj.disk.sys.annotation.GenerateParam;
import lombok.Getter;

@Getter
public final class Demo{
	@GenerateParam(primary = true)
	Integer id;

	@GenerateParam(length = "(10)",unique = true)
	String username;
	
	@GenerateParam(length = "(32)")
	String password;
	
	@GenerateParam(length = "(32)",unique = true)
	String path;
	
	@GenerateParam(length = "(13)")
	Long capacity = 3221225472L;
	
	@GenerateParam(length = "(4)")
	Integer fileCount = 100;
	
	@GenerateParam(length = "(13)")
	Long fileSize = 3221225472L;

	@GenerateParam(length = "(13)")
	Long totalFileSize = 3221225472L;

	@GenerateParam(length = "(1)")
	Integer authority = 0;

	public static void main(String[] args)throws InstantiationException,IllegalAccessException,IOException{
		Generate.start(Demo.class,"File");
	}
}