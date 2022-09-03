package com.zyj.disk.entity.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public final class UserAttach{
	private Integer id;
	private Integer userId;
	private String path;
	private Long capacity;
	private Integer fileCount;
	private Long fileSize;
	private Long totalFileSize;
	private String platform;
	private String language;
	private Integer cores;
	private Integer thread;
	private Integer network;

	public static UserAttach noArgs(){
		return new UserAttach();
	}

	public static UserAttach defaultArgs(){
		return noArgs()
			.capacity(3221225472L)
			.fileCount(5)
			.fileSize(3221225472L)
			.totalFileSize(3221225472L)
			.platform("Win32")
			.language("zh-CN")
			.cores(0)
			.thread(0)
			.network(0);
	}

	public UserAttach id(Integer val){
		id = val;
		return this;
	}

	public UserAttach userId(Integer val){
		userId = val;
		return this;
	}

	public UserAttach path(String val){
		path = val;
		return this;
	}

	public UserAttach capacity(Long val){
		capacity = val;
		return this;
	}

	public UserAttach fileCount(Integer val){
		fileCount = val;
		return this;
	}

	public UserAttach fileSize(Long val){
		fileSize = val;
		return this;
	}

	public UserAttach totalFileSize(Long val){
		totalFileSize = val;
		return this;
	}

	public UserAttach platform(String val){
		platform = val;
		return this;
	}

	public UserAttach language(String val){
		language = val;
		return this;
	}

	public UserAttach cores(Integer val){
		cores = val;
		return this;
	}

	public UserAttach thread(Integer val){
		thread = val;
		return this;
	}

	public UserAttach network(Integer val){
		network = val;
		return this;
	}
}