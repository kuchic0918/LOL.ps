package yg_ac_java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class LogIn {
	private int memberKey;
	String myEmail;
	String myPW;

	public int getMemberKey() {
		return memberKey;
	}

	public void setMemberKey(int memberKey) {
		this.memberKey = memberKey;
	}

	public String getMyEmail() {
		return myEmail;
	}

	public void setMyEmail(String myEmail) {
		this.myEmail = myEmail;
	}

	public String getMyPW() {
		return myPW;
	}

	public void setMyPW(String myPW) {
		this.myPW = myPW;
	}

	//오늘 날짜 받아오기
	public String getDate(LocalDate now, DateTimeFormatter formatter) {
		// 포맷 적용
		String formatedNow = now.format(formatter);

		return formatedNow;
	}

	// email 맞냐?
	public boolean isVaildEmail(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		String sql = "select * from member where email = '" + myEmail + "' ";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String getEmail = rs.getString("email");

				if(myEmail.equals(getEmail)) {
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// pw 맞냐?
	public boolean isVaildPW(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		String sql = "select * from member where email = '" + myEmail + "' and pw = '" + myPW + "' ";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String getPW = rs.getString("PW");

				if(myPW.equals(getPW)) {
					return true;
				}
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}


	// 로그인 
	public void logIn(Scanner sc, Connection conn, PreparedStatement pstmt,  ResultSet rs) {
		System.out.print("이메일 : ");
		this.myEmail = sc.next();

		System.out.print("패스워드 : ");
		this.myPW = sc.next();

		if(isVaildEmail(conn, pstmt, rs) && isVaildPW(conn, pstmt, rs)) {
			System.out.println("로그인 성공.");	

			try {
				String sql = "select * from member where email = '" + myEmail + "'";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					this.memberKey = rs.getInt("memberKey");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else System.out.println("아이디 혹은 비밀번호가 틀렸습니다. 다시 입력 부탁드립니다."); 

	}
}