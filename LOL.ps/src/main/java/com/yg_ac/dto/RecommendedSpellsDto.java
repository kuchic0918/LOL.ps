package com.yg_ac.dto;

public class RecommendedSpellsDto {
	private String name;
	private String image;
	private String function;
	
	public RecommendedSpellsDto() {
		
	}

	public RecommendedSpellsDto(String image, String name, String function) {
		this.setImage(image);
		this.setName(name);
		this.setFunction(function);
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

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}
	
}
