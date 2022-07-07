package com.yg_ac.dto;

public class CoreCombineDto {
	private String pick1; 
	private String image1;
	private String function1;
	private String pick2;
	private String image2;
	private String function2;
	private String pick3;
	private String image3;
	private String function3;
	private String pick4;
	private String image4;
	private String function4;
	private double winRate;
	private double pickRate;
	private String count;
	
	public CoreCombineDto() {}
	public CoreCombineDto(String pick1, String image1, String function1, String pick2, String image2, String function2,
			String pick3, String image3, String function3, String pick4, String image4, String function4,
			double winRate, double pickRate, String count) {
		this.pick1 = pick1;
		this.image1 = image1;
		this.function1 = function1;
		this.pick2 = pick2;
		this.image2 = image2;
		this.function2 = function2;
		this.pick3 = pick3;
		this.image3 = image3;
		this.function3 = function3;
		this.pick4 = pick4;
		this.image4 = image4;
		this.function4 = function4;
		this.winRate = winRate;
		this.pickRate = pickRate;
		this.count = count;
	}
	
	public CoreCombineDto(String pick1, String image1, String function1, String pick2, String image2, String function2,
			String pick3, String image3, String function3, double winRate, double pickRate, String count) {
		this.pick1 = pick1;
		this.image1 = image1;
		this.function1 = function1;
		this.pick2 = pick2;
		this.image2 = image2;
		this.function2 = function2;
		this.pick3 = pick3;
		this.image3 = image3;
		this.function3 = function3;
		this.winRate = winRate;
		this.pickRate = pickRate;
		this.count = count;
	}
	
	public CoreCombineDto(String pick1, String image1, String function1, String pick2, String image2, String function2,
			double winRate, double pickRate, String count) {
		this.pick1 = pick1;
		this.image1 = image1;
		this.function1 = function1;
		this.pick2 = pick2;
		this.image2 = image2;
		this.function2 = function2;
		this.winRate = winRate;
		this.pickRate = pickRate;
		this.count = count;
	}
	
	public String getPick1() {
		return pick1;
	}
	public void setPick1(String pick1) {
		this.pick1 = pick1;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getFunction1() {
		return function1;
	}
	public void setFunction1(String function1) {
		this.function1 = function1;
	}
	public String getPick2() {
		return pick2;
	}
	public void setPick2(String pick2) {
		this.pick2 = pick2;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getFunction2() {
		return function2;
	}
	public void setFunction2(String function2) {
		this.function2 = function2;
	}
	public String getPick3() {
		return pick3;
	}
	public void setPick3(String pick3) {
		this.pick3 = pick3;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public String getFunction3() {
		return function3;
	}
	public void setFunction3(String function3) {
		this.function3 = function3;
	}
	public String getPick4() {
		return pick4;
	}
	public void setPick4(String pick4) {
		this.pick4 = pick4;
	}
	public String getImage4() {
		return image4;
	}
	public void setImage4(String image4) {
		this.image4 = image4;
	}
	public String getFunction4() {
		return function4;
	}
	public void setFunction4(String function4) {
		this.function4 = function4;
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
