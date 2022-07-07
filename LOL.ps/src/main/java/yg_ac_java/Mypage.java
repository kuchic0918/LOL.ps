package yg_ac_java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Mypage {
	Scanner sc = new Scanner(System.in);
	private String introduceStr = null;	//한줄소개문자열
	private String nicknameStr = null;	//닉네임문자열
	//한줄소개
	public void Introduce(Connection conn, PreparedStatement pstmt, LogIn lg) {
		String sql = "update member\r\n" + 
				"set introduce = ?\r\n" + 
				"where memberkey = ?";
		System.out.print("내용 입력 : ");
		this.introduceStr = sc.nextLine();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, this.introduceStr);
			pstmt.setInt(2, lg.getMemberKey());
			pstmt.executeUpdate();
			System.out.println("변경 되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//닉네임변경
	public void Nickname(Connection conn, PreparedStatement pstmt, LogIn lg) {
		String sql = "update member set nickname = ? where memberkey = ?";
		System.out.print("변경할 닉네임 입력 : ");
		this.nicknameStr = sc.nextLine();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, this.nicknameStr);
			pstmt.setInt(2, lg.getMemberKey());
			pstmt.executeUpdate();
			System.out.println("변경 되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//회원탈퇴
	public void Secession(Connection conn, PreparedStatement pstmt, LogIn lg) {
		String sql = "delete from member where memberkey = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lg.getMemberKey());
			pstmt.executeUpdate();
			System.out.println("탈퇴 되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//전체 프로필 이미지 보여주기
	public void showAllImage(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		String sql = "select name from profile_image";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String imgName = rs.getString("name");
				System.out.print(imgName + ", ");
			}
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//프로필 사진 설정하기
	public void setProfile(Scanner sc, Connection conn, PreparedStatement pstmt, LogIn lg) {
		String Sql = "update MEMBER set image = ? where memberkey = ?";

		try {
			String img = sc.next();

			pstmt = conn.prepareStatement(Sql);
			pstmt.setString(1, img);
			pstmt.setInt(2, lg.getMemberKey());
			pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//지금 내 프로필 사진
	public void showMyProfile(Connection conn, PreparedStatement pstmt, ResultSet rs, LogIn lg) {
		String Sql = "select image, introduce from member where memberkey = '" + lg.getMemberKey() + "'";

		try {

			pstmt = conn.prepareStatement(Sql);
			rs = pstmt.executeQuery();

			rs.next();
			String img = rs.getString("image");
			String introduce = rs.getString("introduce");
			System.out.println();
			System.out.println(img + " " + introduce);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
