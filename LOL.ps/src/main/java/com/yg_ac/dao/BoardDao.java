package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yg_ac.dto.BoardDto;
import com.yg_ac.dto.MemberDTO;

public class BoardDao {
	Y_DBmanager db = new Y_DBmanager();
	Connection conn = db.getConnection();
	
	public ArrayList<BoardDto> getBoardList(String category, int startBno, int endBno){
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * " + 
				"from( " + 
				"    select rownum rnum, b1.* " + 
				"    from( " + 
				"        select * " + 
				"        from community " + 
				"        where category = ? " + 
				"        order by bno desc) b1) " + 
				"where rnum >= ? " + 
				"and rnum <= ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, startBno);
			pstmt.setInt(3, endBno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int memberkey = rs.getInt("memberkey");
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writedate = rs.getString("writedate");
				int good = rs.getInt("good");
				int bad = rs.getInt("bad");
				int count = rs.getInt("count");
				String champName = rs.getString("champname");
				list.add(new BoardDto(memberkey,bno,title,content,writedate,good,bad,count,champName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	public int getAllBoardList(String category){
		int cnt=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) cnt from community where category = ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	public String getImage(String champName) {
		String image = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select image_head image " + 
				"from champ_skill " + 
				"where name = ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,champName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				image = rs.getString("image");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return image;
	}
	public MemberDTO getWriter(int memberkey) {
		MemberDTO member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select nickname name, image, introduce " + 
				"from member " + 
				"where memberkey = ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,memberkey);
			rs = pstmt.executeQuery();
			rs.next();
			String writer = rs.getString("name");
			String image = rs.getString("image");
			String introduce = rs.getString("introduce");
			member = new MemberDTO(writer,image,introduce);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return member;
	}
	public BoardDto getDetail(int bno) {
		BoardDto detail = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * " + 
				"from community " + 
				"where bno = ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			rs.next();
			int memberkey = rs.getInt("memberkey");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String writedate = rs.getString("writedate");
			int good = rs.getInt("good");
			int bad = rs.getInt("bad");
			int count = rs.getInt("count");
			String category = rs.getString("category");
			String champName = rs.getString("champname");
			detail = new BoardDto(memberkey,title,content,writedate,good,bad,count,category,champName);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return detail;
	}
	public void writeAction(int memberkey, String title, String content, String category, String champName) {
		String sql = "insert into community values ( ? , BNO_SEQ.nextval, ? , ? , sysdate, 0, 0, 0, ? , ? )";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,memberkey);
			pstmt.setString(2,title);
			pstmt.setString(3,content);
			pstmt.setString(4,category);
			pstmt.setString(5,champName);
			pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public ArrayList<Integer> getFirstLastBno(String category) {
		ArrayList<Integer> get = new ArrayList<Integer>();
		String sql = "select * from community where category = ? order by bno desc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int bno = rs.getInt("bno");
				get.add(bno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return get;
	}
	public ArrayList<BoardDto> getBoardListTitle(String category, String name, int startBno, int endBno){
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * " + 
				"from( " + 
				"    select rownum rnum, b1.* " + 
				"    from( " + 
				"        select * " + 
				"        from community " + 
				"        where category = ? " + 
				"        and title = ? " + 
				"        order by bno desc) b1) " + 
				"where rnum >= ? " + 
				"and rnum <= ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, name);
			pstmt.setInt(3, startBno);
			pstmt.setInt(4, endBno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int memberkey = rs.getInt("memberkey");
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writedate = rs.getString("writedate");
				int good = rs.getInt("good");
				int bad = rs.getInt("bad");
				int count = rs.getInt("count");
				String champName = rs.getString("champname");
				list.add(new BoardDto(memberkey,bno,title,content,writedate,good,bad,count,champName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public int getAllBoardListTitle(String category, String name){
		int cnt=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) cnt from community where category = ? title = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
}
