package com.yg_ac.dto;

public class MemberDTO {
	private int memberkey;
	private String email;
	private String pw;
	private String nickname;
	private String image;
	private String introduce;
	private String admin;
	
	public MemberDTO(int memberkey, String email, String pw, String nickname, String image, String introduce,
			String admin) {
		this.memberkey = memberkey;
		this.email = email;
		this.pw = pw;
		this.nickname = nickname;
		this.image = image;
		this.introduce = introduce;
		this.admin = admin;
	}

	public MemberDTO(String nickname, String image, String introduce) {
		super();
		this.nickname = nickname;
		this.image = image;
		this.introduce = introduce;
	}


	public int getMemberkey() {
		return memberkey;
	}

	public void setMemberkey(int memberkey) {
		this.memberkey = memberkey;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String id) {
		this.email = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
	
	
}
