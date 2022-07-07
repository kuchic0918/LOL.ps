package com.yg_ac.dto;

public class GetSkillMasterDto {	// 스킬 - 마스터 순서
	private String image;
	private String name;
	private String function;
	
	public GetSkillMasterDto() {
		
	}
	
	public GetSkillMasterDto(String image, String name, String function) {
		this.setImage(image);
		this.setName(name);
		this.setFunction(function);
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
