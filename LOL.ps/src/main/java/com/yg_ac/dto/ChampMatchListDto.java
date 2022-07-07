package com.yg_ac.dto;

public class ChampMatchListDto {
    private String name;
    private String line;
    private String count;
    private double winRate;
    private String image;

    public ChampMatchListDto(String name, String line, String count, double winRate, String image) {
        this.name = name;
        this.line = line;
        this.count = count;
        this.winRate = winRate;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
