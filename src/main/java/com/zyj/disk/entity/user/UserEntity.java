package com.zyj.disk.entity.user;

import com.zyj.disk.sys.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @Author: ZYJ
 * @Date: 2022/04/08
 * @Remark: 用户实体类
 */
@Getter
@EqualsAndHashCode(callSuper = false)
public final class UserEntity extends BaseEntity{
	private Integer id;
	private String username;
	private String password;
	private String path;
	private Long capacity;
	private Integer fileCount;
	private Long fileSize;
	private Long totalFileSize;
	private Integer authority;

	public static UserEntity noArgs(){
		return new UserEntity();
	}

	public static UserEntity defaultArgs(){
		return noArgs().capacity(3221225472L)
				.fileCount(100)
				.fileSize(3221225472L)
				.totalFileSize(3221225472L)
				.authority(0);
	}

	public UserEntity id(Integer val){
		id = val;
		return this;
	}
	public UserEntity username(String val){
		username = val;
		return this;
	}
	public UserEntity password(String val){
		password = val;
		return this;
	}
	public UserEntity path(String val){
		path = val;
		return this;
	}
	public UserEntity capacity(Long val){
		capacity = val;
		return this;
	}
	public UserEntity fileCount(Integer val){
		fileCount = val;
		return this;
	}
	public UserEntity fileSize(Long val){
		fileSize = val;
		return this;
	}
	public UserEntity totalFileSize(Long val){
		totalFileSize = val;
		return this;
	}
	public UserEntity authority(Integer val){
		authority = val;
		return this;
	}
}