package com.yg_ac.dto;

public class CoreEachDto {
	private String pick;
	private String image;
	private String function;
	private double winRate;
	private double pickRate;
	private String count;
	
	public CoreEachDto(String pick, String image, String function, double winRate, double pickRate, String count) {
		this.pick = pick;
		this.image = image;
		this.function = function;
		this.winRate = winRate;
		this.pickRate = pickRate;
		this.count = count;
	}

	public String getPick() {
		return pick;
	}

	public void setPick(String pick) {
		this.pick = pick;
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

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	
}
