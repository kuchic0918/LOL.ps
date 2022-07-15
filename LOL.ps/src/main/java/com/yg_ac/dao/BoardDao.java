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
	
	public ArrayList<BoardDto> getBuildList(int startBno, int endBno){
		ArrayList<BoardDto> buildList = new ArrayList<BoardDto>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select *" + 
				"from(" + 
				"    select rownum rnum, b1.*" + 
				"    from(" + 
				"        select * " + 
				"        from community" + 
				"        where category = '빌드 연구소'" + 
				"        order by bno desc) b1)" + 
				"where rnum >= ?" + 
				"and rnum <= ?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startBno);
			pstmt.setInt(2, endBno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buildList;
	}
	public ArrayList<BoardDto> getFreeList(int startBno, int endBno){
		ArrayList<BoardDto> freeList = new ArrayList<BoardDto>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select *" + 
				"from(" + 
				"    select rownum rnum, b1.*" + 
				"    from(" + 
				"        select * " + 
				"        from community" + 
				"        where category = '자유 게시판'" + 
				"        order by bno desc) b1)" + 
				"where rnum >= ?" + 
				"and rnum <= ?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startBno);
			pstmt.setInt(2, endBno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return freeList;
	}
}
