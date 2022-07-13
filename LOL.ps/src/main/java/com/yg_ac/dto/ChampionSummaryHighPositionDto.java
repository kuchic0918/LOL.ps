package com.yg_ac.dto;

public class ChampionSummaryHighPositionDto {
	private String line;
	private double pickRate;

	public ChampionSummaryHighPositionDto() { }
	
	public ChampionSummaryHighPositionDto(String line, double pickRate) {
		this.line = line;
		this.pickRate = pickRate;
	}
	
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public double getPickRate() {
		return pickRate;
	}
	public void setPickRate(double pickRate) {
		this.pickRate = pickRate;
	}
	
}
