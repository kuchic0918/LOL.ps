package com.yg_ac.dto;

public class BoardDto {
	private int memberkey;
	private int bno;
	private String title;
	private String content;
	private String writedate;
	private int good;
	private int bad;
	private int count;
	private String champName;
	
	public BoardDto(int memberkey, int bno, String title, String content, String writedate, int good, int bad,
			int count, String champName) {
		this.memberkey = memberkey;
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writedate = writedate;
		this.good = good;
		this.bad = bad;
		this.count = count;
		this.champName = champName;
	}

	public int getMemberkey() {
		return memberkey;
	}

	public void setMemberkey(int memberkey) {
		this.memberkey = memberkey;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}

	public int getGood() {
		return good;
	}

	public void setGood(int good) {
		this.good = good;
	}

	public int getBad() {
		return bad;
	}

	public void setBad(int bad) {
		this.bad = bad;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getChampName() {
		return champName;
	}

	public void setChampName(String champName) {
		this.champName = champName;
	}
	
}
