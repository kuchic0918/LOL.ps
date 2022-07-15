package com.yg_ac.dto;

public class BoardDto {
	private String memberkey;
	private String bno;
	private String title;
	private String content;
	private String writedate;
	private String good;
	private String bad;
	private String count;
	private String category;
	private String champName;
	
	public BoardDto(String memberkey, String bno, String title, String content, String writedate, String good,
			String bad, String count, String category, String champName) {
		this.memberkey = memberkey;
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writedate = writedate;
		this.good = good;
		this.bad = bad;
		this.count = count;
		this.category = category;
		this.champName = champName;
	}
	
	public String getMemberkey() {
		return memberkey;
	}
	public void setMemberkey(String memberkey) {
		this.memberkey = memberkey;
	}
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
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
	public String getGood() {
		return good;
	}
	public void setGood(String good) {
		this.good = good;
	}
	public String getBad() {
		return bad;
	}
	public void setBad(String bad) {
		this.bad = bad;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getChampName() {
		return champName;
	}
	public void setChampName(String champName) {
		this.champName = champName;
	}
	
}
