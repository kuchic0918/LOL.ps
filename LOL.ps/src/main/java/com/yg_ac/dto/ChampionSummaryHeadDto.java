package com.yg_ac.dto;

public class ChampionSummaryHeadDto {
	private String name;
	private String image;
	
	public ChampionSummaryHeadDto() { }
	
	public ChampionSummaryHeadDto(String name, String image) {
		super();
		this.name = name;
		this.image = image;
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
