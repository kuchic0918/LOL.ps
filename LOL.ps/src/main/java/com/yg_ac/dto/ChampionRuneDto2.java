package com.yg_ac.dto;

public class ChampionRuneDto2 {
	private String name;
	private String image;
	private String function;
	
	public ChampionRuneDto2() { }
	
	public ChampionRuneDto2(String name, String image, String function) {
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
