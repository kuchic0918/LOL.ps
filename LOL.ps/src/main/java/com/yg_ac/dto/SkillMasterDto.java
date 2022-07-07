package com.yg_ac.dto;

public class SkillMasterDto {
	String pick1;
	String image1;
	String key1;
	String function1;
	String pick2;
	String image2;
	String key2;
	String function2;
	String pick3;
	String image3;
	String key3;
	String function3;
	double winRate;
	double pickRate;
	String count;
	
	public SkillMasterDto() {}
	public SkillMasterDto(String pick1, String image1, String key1, String function1, String pick2, String image2,
			String key2, String function2, String pick3, String image3, String key3, String function3, double winRate,
			double pickRate, String count) {
		this.pick1 = pick1;
		this.image1 = image1;
		this.key1 = key1;
		this.function1 = function1;
		this.pick2 = pick2;
		this.image2 = image2;
		this.key2 = key2;
		this.function2 = function2;
		this.pick3 = pick3;
		this.image3 = image3;
		this.key3 = key3;
		this.function3 = function3;
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
	public String getKey1() {
		return key1;
	}
	public void setKey1(String key1) {
		this.key1 = key1;
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
	public String getKey2() {
		return key2;
	}
	public void setKey2(String key2) {
		this.key2 = key2;
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
	public String getKey3() {
		return key3;
	}
	public void setKey3(String key3) {
		this.key3 = key3;
	}
	public String getFunction3() {
		return function3;
	}
	public void setFunction3(String function3) {
		this.function3 = function3;
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
