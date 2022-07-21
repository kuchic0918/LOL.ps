package com.yg_ac.dto;

public class CommentDto {
	private int memberkey;
	private int bno;
	private int cno;
	private int rno;
	private String content;
	private String writedate;
	
	public CommentDto() { }
	
	public CommentDto(int memberkey, int bno, int cno, int rno, String content, String writedate) {
		this.memberkey = memberkey;
		this.bno = bno;
		this.cno = cno;
		this.rno = rno;
		this.content = content;
		this.writedate = writedate;
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

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
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
	
}
