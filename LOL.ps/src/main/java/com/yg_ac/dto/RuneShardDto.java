package com.yg_ac.dto;

public class RuneShardDto {
	private String pick1;
	private String pick2;
	private String pick3;
	private String image1;
	private String image2;
	private String image3;
	private double winRate;
	private double pickRate;
	
	public RuneShardDto() { }

	public RuneShardDto(String pick1, String pick2, String pick3, String image1, String image2, String image3, double winRate2, double pickRate2) {
		this.pick1 = pick1;
		this.pick2 = pick2;
		this.pick3 = pick3;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.winRate = winRate2;
		this.pickRate = pickRate2;
	}

	public String getPick1() {
		return pick1;
	}

	public void setPick1(String pick1) {
		this.pick1 = pick1;
	}

	public String getPick2() {
		return pick2;
	}

	public void setPick2(String pick2) {
		this.pick2 = pick2;
	}

	public String getPick3() {
		return pick3;
	}

	public void setPick3(String pick3) {
		this.pick3 = pick3;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public double getWinRate() {
		return winRate;
	}

	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}

	public double getPickRate() {
		return pickRate;
	}

	public void setPickRate(double pickRate) {
		this.pickRate = pickRate;
	}
	
}
