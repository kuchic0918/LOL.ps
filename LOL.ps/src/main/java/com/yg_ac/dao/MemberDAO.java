
package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yg_ac.dto.MemberDTO;
import com.yg_ac.dto.MypageIntroduceDto;
import com.yg_ac.dto.MypageProfileChangeDto;

public class MemberDAO {
	Y_DBmanager db = new Y_DBmanager();
	Connection conn = db.getConnection();
	//이메일 중복체크 
	public boolean emailisOverlap(String Email) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
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
	//	닉네임 중복
	public boolean NickNameIsOverlap(String NickName) {
		String sql = "select count(*) cnt from member where nickname = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
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
	// 로그인 진행
	public boolean loginvalid (String email, String pw) { 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
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
	public boolean isVaildEmail(String email) {
		String sql = "select count(*) cnt from member where email = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
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
	// 비밀번호 유효 검사
	public boolean isVaildPW(String pw) {
		String sql = "select count(*) cnt from member where pw = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
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
	// 세션 받아오기
	public MemberDTO findByEmailPwMemberInfo(String email, String pw) {
		String sql = "select * from member where email = ? and pw = ?";
		MemberDTO member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
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
	// 회원가입 완료	
	public void signin (MemberDTO member) {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into member values(temp_seq.nextval,?,?,?,'anne1.jpg',?,?)";
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
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// 네이버, 카톡 회원가입
	public void snsSignIn(MemberDTO member) {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into member values(temp_seq.nextval, ? , 'snsAdmin' , ? , 'anne1.jpg', ? ,? )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getNickname());
			pstmt.setString(3, member.getIntroduce());
			pstmt.setString(4, member.getAdmin());
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// 네이버, 카톡은 이메일과 닉네임 밖에 못가져옴.
	public MemberDTO findByEmailNicknameMemberInfo(String email, String nickname) {
		String sql = "select * from member where email = ? and nickname = ?";
		MemberDTO member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
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
	// 마이페이지 소개
	public MypageIntroduceDto getMypageIntroduce(int key) {
		MypageIntroduceDto get = null;
		String sql = "SELECT * from member where memberkey = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, key);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				get = new MypageIntroduceDto(rs.getString("introduce"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return get;
	}
	// 마이페이지 이미지
	public MypageIntroduceDto getMypageImage(int key) {
		MypageIntroduceDto get = null;
		String sql = "SELECT * from member where memberkey = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, key);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				get = new MypageIntroduceDto(rs.getString("image"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return get;
	}
	// 마이페이지 소개글 업데이트
	public void updateMypageIntroduce(int key, String introduce) {
		String sql = "update member set introduce = ? where memberkey = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, introduce);
			pstmt.setInt(2, key);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// 마이페이지 로그인 되있으면 세션으로 멤버키 찾기
	public void getMypageMemberSecession(int key) {
		String sql = "DELETE FROM member WHERE memberkey=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, key);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// 마이페이지 비밀번호 변경
	public void getMypagePasswordChangeDao(int key, String password) {
		String sql = "update member set pw = ? where memberkey = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setInt(2, key);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			try {
				pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// 마이페이지 프로필 사진 변경
	public MypageProfileChangeDto getmypageProfileChange(int key, String image) {
		MypageProfileChangeDto get = null;
		String sql = "update member set image = ? where memberkey = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, image);
			pstmt.setInt(2, key);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return get;
	}
}
