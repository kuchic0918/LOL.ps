package com.yg_ac.dto;

public class MainCardDto {
	private String name;
	
	private String line;
	private Double win;
	private Double winBefore;
	private Double winVari;
	private Double pick;
	private Double pickBefore;
	private Double pickVari;
	private Double ban;
	private Double banBefore;
	private Double banVari;
    private String image;

	public MainCardDto() { }
	
	public MainCardDto(String name, String line, Double win, Double winBefore, Double winVari, Double pick,
			Double pickBefore, Double pickVari, Double ban, Double banBefore, Double banVari, String image) {
		this.name = name;
		this.line = line;
		this.win = win;
		this.winBefore = winBefore;
		this.winVari = winVari;
		this.pick = pick;
		this.pickBefore = pickBefore;
		this.pickVari = pickVari;
		this.ban = ban;
		this.banBefore = banBefore;
		this.banVari = banVari;
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

	public Double getWin() {
		return win;
	}

	public void setWin(Double win) {
		this.win = win;
	}

	public Double getWinBefore() {
		return winBefore;
	}

	public void setWinBefore(Double winBefore) {
		this.winBefore = winBefore;
	}

	public Double getWinVari() {
		return winVari;
	}

	public void setWinVari(Double winVari) {
		this.winVari = winVari;
	}

	public Double getPick() {
		return pick;
	}

	public void setPick(Double pick) {
		this.pick = pick;
	}

	public Double getPickBefore() {
		return pickBefore;
	}

	public void setPickBefore(Double pickBefore) {
		this.pickBefore = pickBefore;
	}

	public Double getPickVari() {
		return pickVari;
	}

	public void setPickVari(Double pickVari) {
		this.pickVari = pickVari;
	}

	public Double getBan() {
		return ban;
	}

	public void setBan(Double ban) {
		this.ban = ban;
	}

	public Double getBanBefore() {
		return banBefore;
	}

	public void setBanBefore(Double banBefore) {
		this.banBefore = banBefore;
	}

	public Double getBanVari() {
		return banVari;
	}

	public void setBanVari(Double banVari) {
		this.banVari = banVari;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
