package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yg_ac.dto.BoardDto;

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
			rs.next();
			image = rs.getString("image");
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
	public String getWriter(int memberkey) {
		String writer = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select nickname name " + 
				"from member " + 
				"where memberkey = ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,memberkey);
			rs = pstmt.executeQuery();
			rs.next();
			writer = rs.getString("name");
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
		
		return writer;
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
		}
		return detail;
	}
}
