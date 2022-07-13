package com.yg_ac.dto;

public class ChampionSummarySelectPositionDto {
	private double pickRate;
	
	public ChampionSummarySelectPositionDto() { }
	
	public ChampionSummarySelectPositionDto(double pickRate) {
		this.pickRate = pickRate;
	}

	public double getPickRate() {
		return pickRate;
	}
	public void setPickRate(double pickRate) {
		this.pickRate = pickRate;
	}
}
