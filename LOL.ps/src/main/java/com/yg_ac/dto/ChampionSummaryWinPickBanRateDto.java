package com.yg_ac.dto;

public class ChampionSummaryWinPickBanRateDto {
	private double winRate;
	private double pickRate;
	private double banRate;
	private String count;
	
	public ChampionSummaryWinPickBanRateDto() { }
	
	public ChampionSummaryWinPickBanRateDto(double winRate, double pickRate, double banRate, String count) {
		this.winRate = winRate;
		this.pickRate = pickRate;
		this.banRate = banRate;
		this.count = count;
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

	public double getBanRate() {
		return banRate;
	}

	public void setBanRate(double banRate) {
		this.banRate = banRate;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}
