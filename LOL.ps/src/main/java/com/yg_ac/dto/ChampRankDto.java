package com.yg_ac.dto;

public class ChampRankDto {	
	private String name;
    private String line;
    private double psScore;
    private double honeyScore;
    private double winRate;
    private double pickRate;
    private double banRate;
    private int count;
    private String count2;
    private String image;

    public ChampRankDto(String name, String line, double psScore, double honeyScore, double winRate, double pickRate, double banRate, int count, String count2, String image) {
        this.name = name;
        this.line = line;
        this.psScore = psScore;
        this.honeyScore = honeyScore;
        this.winRate = winRate;
        this.pickRate = pickRate;
        this.banRate = banRate;
        this.count = count;
        this.count2 = count2;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public double getPsScore() {
        return psScore;
    }

    public void setPsScore(double psScore) {
        this.psScore = psScore;
    }

    public double getHoneyScore() {
        return honeyScore;
    }

    public void setHoneyScore(double honeyScore) {
        this.honeyScore = honeyScore;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCount2() {
        return count2;
    }

    public void setCount2(String count2) {
        this.count2 = count2;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
