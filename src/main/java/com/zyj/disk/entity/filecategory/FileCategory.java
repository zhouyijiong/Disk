package com.zyj.disk.entity.filecategory;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public final class FileCategory{
	private Integer id;
	private Integer userId;
	private String categoryCode;

	public static FileCategory noArgs(){
		return new FileCategory();
	}

	public FileCategory id(Integer val){
		id = val;
		return this;
	}

	public FileCategory userId(Integer val){
		userId = val;
		return this;
	}

	public FileCategory categoryCode(String val){
		categoryCode = val;
		return this;
	}
}