package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yg_ac.dto.MemberDTO;

public class MemberDAO {
	//이메일 중복체크 
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
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	//	멤버키 중복 시퀀스로 대체
	//	public boolean MemberkeyIsOverlap (int Memberkey, PreparedStatement pstmt, Connection conn, ResultSet rs) {
	//		try  {
	//			String sql = "select count(*) cnt from member where memberkey = ?";
	//			pstmt = conn.prepareStatement(sql); // sql 문 저장
	//			pstmt.setInt(1, Memberkey);
	//			rs = pstmt.executeQuery();
	//			
	//			if(rs.next()) {
	//				int cnt = rs.getInt("cnt");
	//				
	//				if (cnt > 0) {
	//					return true;
	//					
	//				}
	//			}
	//		}catch(SQLException e) {
	//			e.printStackTrace();
	//		}
	//		
	//		return false;
	//	}

	//	닉네임 중복
	public boolean NickNameIsOverlap(String NickName, PreparedStatement pstmt, Connection conn, ResultSet rs) {
		String sql = "select count(*) cnt from member where nickname = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, NickName);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int cnt = rs.getInt("cnt");

				if(cnt == 0) {
					return false;
				}
			}

		}catch(SQLException e) {
			System.out.println("1111");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	public int issueMemberkey(String email,String pw,Connection conn, PreparedStatement pstmt, ResultSet rs  ) throws SQLException {

		String sql = "select memberkey mk from member where email = ? and Pw = ?";
		int memberkey = 0;

		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				memberkey = rs.getInt("mk");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return memberkey;
	} 

	public boolean loginvalid (String email, String pw, Connection conn, PreparedStatement pstmt, ResultSet rs ) { 
		try {
			String sql = "select count(*) cnt from member where email = ? and Pw = ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();

			if(rs.next()) { 
				int cnt = rs.getInt("cnt");

				if(cnt==0) {
					return false;
				}
			}

		}
		catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}



	// email 중복
	public boolean isVaildEmail(String email ,Connection conn, PreparedStatement pstmt, ResultSet rs) {
		String sql = "select count(*) cnt from member where email = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				
				if(cnt==0) {
					return false;
				}
			}


		} catch (SQLException e) {
			System.out.println("2222");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;
	}
	public boolean isVaildPW(String pw ,Connection conn, PreparedStatement pstmt, ResultSet rs) {
		String sql = "select count(*) cnt from member where pw = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				
				if(cnt==0) {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;
	}
	public MemberDTO findByEmailPwMemberInfo(String email,String pw ,Connection conn, PreparedStatement pstmt, ResultSet rs) {
		String sql = "select * from member where email = ? and pw = ?";
		MemberDTO member = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int DBmemberkey = rs.getInt("memberkey");
				String DBemail = rs.getString("email");
				String DBpw = rs.getString("pw");
				String DBnickname = rs.getString("nickname");
				String DBimage = rs.getString("image");
				String DBintroduce = rs.getString("introduce");
				String admin = rs.getString("admin");
				member = new MemberDTO(DBmemberkey, DBemail,DBpw,DBnickname,DBimage,DBintroduce,admin);
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return member;
	}

	public void signin (MemberDTO member ,Connection conn, PreparedStatement pstmt, ResultSet rs ) {
		try {
			String sql = "insert into member values(temp_seq.nextval,?,?,?,'anne.jpg',?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPw()); 		
			pstmt.setString(3, member.getNickname()); 	
			pstmt.setString(4, member.getIntroduce()); //한줄소개
			pstmt.setString(5, member.getAdmin()); //관리자여부
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void snsSignIn(MemberDTO member , Connection conn , PreparedStatement pstmt , ResultSet rs) {
		try {
			String sql = "insert into member values(temp_seq.nextval, ? , 'kakaoAdmin' , ? , 'anne.jpg', ? ,? )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getNickname());
			pstmt.setString(3, member.getIntroduce());
			pstmt.setString(4, member.getAdmin());
			pstmt.executeQuery();
		}
		catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public MemberDTO findByEmailNicknameMemberInfo(String email,String nickname ,Connection conn, PreparedStatement pstmt, ResultSet rs) {
		String sql = "select * from member where email = ? and nickname = ?";
		MemberDTO member = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, nickname);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int DBmemberkey = rs.getInt("memberkey");
				String DBemail = rs.getString("email");
				String DBpw = rs.getString("pw");
				String DBnickname = rs.getString("nickname");
				String DBimage = rs.getString("image");
				String DBintroduce = rs.getString("introduce");
				String admin = rs.getString("admin");
				member = new MemberDTO(DBmemberkey, DBemail,DBpw,DBnickname,DBimage,DBintroduce,admin);
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}
}