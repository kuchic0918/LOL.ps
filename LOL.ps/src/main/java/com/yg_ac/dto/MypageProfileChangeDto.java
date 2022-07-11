package com.yg_ac.dto;

public class MypageProfileChangeDto {
	private String image;

	public MypageProfileChangeDto() { }
	
	public MypageProfileChangeDto(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
