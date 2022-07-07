package yg_ac_java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SignUp {
	public boolean emailisOverlap(String Email, PreparedStatement pstmt, Connection conn, ResultSet rs) {
		try {
			String sql = "select count(*) cnt from member where email = ? "; //
			pstmt = conn.prepareStatement(sql); // sql 문 저장
			pstmt.setString(1, Email); 		//첫번째 물음표에 값 지정 
			rs = pstmt.executeQuery(); 	//저장된 sql 문을 실행 시켜 Resultset 변수에 담아둠
			
			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				
				if(cnt > 0) {
					return true;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean MemberkeyIsOverlap (int Memberkey, PreparedStatement pstmt, Connection conn, ResultSet rs) {
		try  {
			String sql = "select count(*) cnt from member where memberkey = ?";
			pstmt = conn.prepareStatement(sql); // sql 문 저장
			pstmt.setInt(1, Memberkey);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				
				if (cnt > 0) {
					return true;
					
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean NickNameIsOverlap(String NickName, PreparedStatement pstmt, Connection conn, ResultSet rs) {
		try {
			String sql = "select count(*) cnt from member where nickname = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, NickName);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				
				if(cnt > 0) {
					return true;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void signUp(Connection conn, PreparedStatement pstmt, ResultSet rs, Scanner sc, LogIn lg) {
		System.out.println("--------------------------");
		System.out.println("회원가입 ");
		System.out.println("--------------------------");
			
		int Memberkey = (int)(Math.random()*30000000)+1;
		
		while(true) {
			if ( MemberkeyIsOverlap(Memberkey, pstmt, conn, rs) == true) {
				continue;
			}else {
				break;
			}
		}
		
		String Email ;
		
		while(true) {
			System.out.println("아이디를 입력하세요 :");
			 Email = sc.nextLine().trim();
			//.trim() 앞뒤 공백제거	
			
			String emailForm = "^[a-zA-Z0-9_!#$%&'\\*+/=?{|}~^.-]+@[a-zA-Z0-9.-]+$"; 
			//이메일 유효성 표현식 이메일 형식에 허용하는 모든문자 사용 가능 
			//이메일 정규표현식 패턴에 넣고 매칭함
			
			try {
				if(emailisOverlap(Email, pstmt, conn, rs) ) {
					System.out.println("중복된 아이디입니다.");
					continue;
				}
			}
			catch(NullPointerException e) {
				e.printStackTrace();
			}
			
			if(!(Email.matches(emailForm))) {
				System.out.println("올바르지 않은양식입니다.");
				continue;
			}
			if(Email.matches(emailForm) && !(emailisOverlap(Email, pstmt, conn, rs))) {
				System.out.println("아이디를 다시 입력하세요 :");
				String emailCheck = sc.nextLine().trim();
				if(emailCheck.equals(Email)) {
					System.out.println("아이디 입력이 완료되었습니다.");
					break;
				}
				else {
					System.out.println("아이디가 틀렸습니다 다시 입력해주세요 ...");
					continue;
				}
			}
		}
		
		String Pw = null ;
		boolean PwChecker = true;
		
		while(PwChecker) {
			System.out.println("패스워드를 입력하세요 (영문, 특수문자, 숫자 포함 8자 이상)");
			Pw = sc.next();
			String PwForm ="^[a-zA-Z\\\\d`~!@#$%^&*()-_=+]{8,24}$";
					
			if(!(Pw.matches(PwForm))) {
				System.out.println("8글자 이상 24글자 이하로 입력해주세요 . . .");
			}
			else if(Pw.matches(PwForm)){
				System.out.println("비밀번호 재확인 . . .");
				String PwCheck = sc.next();
				if(PwCheck.equals(Pw)) {
					System.out.println("비밀번호 입력이 완료되었습니다.");
					break;
				}
				else {
					PwChecker =  false;
				}
			}
		}
		
		String NickName = null ;
		
		while(true) {
			System.out.println("사용할 닉네임을 입력하세요 . . .");
			NickName = sc.next();
			
			if(NickNameIsOverlap(NickName, pstmt, conn, rs)) {
				System.out.println("이미있는 닉네임입니다 . ");
				continue;
			}
			
			System.out.println("사용하실 닉네임은 " + NickName + "입니다 사용하시겠습니까 ?");
			System.out.println("1.예 | 2.아니오");
			int num = sc.nextInt();
			
			if(num == 2) {
				continue;
			}else if(num == 1) {
				break;
			}
		}
		
		try {
			String sql = "insert into member values(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql); // sql 문 저장
			pstmt.setInt(1, Memberkey); 		//첫번째 물음표에 값 지정 
			pstmt.setString(2, Email); 		
			pstmt.setString(3, Pw); 		
			pstmt.setString(4, NickName); 		
			pstmt.setString(5, null);
			pstmt.setString(6, null);
			pstmt.setInt(7, 0);
			pstmt.setString(8, null);
			pstmt.executeQuery();
			
			System.out.println("환영합니다 !");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}