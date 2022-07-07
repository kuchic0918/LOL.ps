package com.yg_ac.dto;

public class ChampStartItemDto {
	 private String name;
	    private String line;
	    private String name1;
	    private String pick1;
	    private String function1;
	    private String name2;
	    private String pick2;
	    private String function2;
	    private double winRate;
	    private double pickRate;
	    private String count;
		
	    public ChampStartItemDto(String name, String line, String name1, String pick1, String function1, String name2,
				String pick2, String function2, double winRate, double pickRate, String count) {
			this.name = name;
			this.line = line;
			this.name1 = name1;
			this.pick1 = pick1;
			this.function1 = function1;
			this.name2 = name2;
			this.pick2 = pick2;
			this.function2 = function2;
			this.winRate = winRate;
			this.pickRate = pickRate;
			this.count = count;
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

		public String getName1() {
			return name1;
		}

		public void setName1(String name1) {
			this.name1 = name1;
		}

		public String getPick1() {
			return pick1;
		}

		public void setPick1(String pick1) {
			this.pick1 = pick1;
		}

		public String getFunction1() {
			return function1;
		}

		public void setFunction1(String function1) {
			this.function1 = function1;
		}

		public String getName2() {
			return name2;
		}

		public void setName2(String name2) {
			this.name2 = name2;
		}

		public String getPick2() {
			return pick2;
		}

		public void setPick2(String pick2) {
			this.pick2 = pick2;
		}

		public String getFunction2() {
			return function2;
		}

		public void setFunction2(String function2) {
			this.function2 = function2;
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
