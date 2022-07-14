package com.yg_ac.dto;

public class ChampionRuneDto {
	private String name;
	private String image;
	private String function;
	
	public ChampionRuneDto() { }
	
	public ChampionRuneDto(String name, String image, String function) {
		this.name = name;
		this.image = image;
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
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	
}
