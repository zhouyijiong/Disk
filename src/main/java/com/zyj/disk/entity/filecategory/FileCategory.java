package com.zyj.disk.entity.filecategory;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public final class FileCategory{
	private Integer id;
	private String userHash;
	private String categoryCode;

	public static FileCategory noArgs(){
		return new FileCategory();
	}

	public FileCategory id(Integer val){
		id = val;
		return this;
	}

	public FileCategory userHash(String val){
		userHash = val;
		return this;
	}

	public FileCategory categoryCode(String val){
		categoryCode = val;
		return this;
	}
}