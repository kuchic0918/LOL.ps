package com.yg_ac.dto;

public class ChampBasicStatDto {
	private String name;
    private String stat;
    private String statStart;
    private String statFinal;
    private String statRank;
    
    public ChampBasicStatDto() {
    	
	}

    public ChampBasicStatDto(String name, String stat, String statStart, String statFinal, String statRank) {
        this.name = name;
        this.stat = stat;
        this.statStart = statStart;
        this.statFinal = statFinal;
        this.statRank = statRank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getStatStart() {
        return statStart;
    }

    public void setStatStart(String statStart) {
        this.statStart = statStart;
    }

    public String getStatFinal() {
        return statFinal;
    }

    public void setStatFinal(String statFinal) {
        this.statFinal = statFinal;
    }

    public String getStatRank() {
        return statRank;
    }

    public void setStatRank(String statRank) {
        this.statRank = statRank;
    }


}
