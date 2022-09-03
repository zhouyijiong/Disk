package com.zyj.disk.entity.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode(callSuper = false)
public final class User {
	private Integer id;
	private String username;
	private String password;
	private String ip;
	private String lastIp;

	public static User noArgs(){
		return new User();
	}

	public static User defaultArgs(){
		return noArgs()
			.ip("")
			.lastIp("");
	}

	public User id(Integer val){
		id = val;
		return this;
	}

	public User username(String val){
		username = val;
		return this;
	}

	public User password(String val){
		password = val;
		return this;
	}

	public User ip(String val){
		ip = val;
		return this;
	}

	public User lastIp(String val){
		lastIp = val;
		return this;
	}
}