package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yg_ac.dto.MypageIntroduceDto;

public class MypageIntroduceDao {
	
	public MypageIntroduceDto getMypageIntroduce(Connection conn, PreparedStatement pstmt, ResultSet rs, int key) {
		MypageIntroduceDto get = null;
		String sql = "SELECT * from member where memberkey = ?";
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
	
	public void updateMypageIntroduce(Connection conn, PreparedStatement pstmt, int key, String introduce) {
		String sql = "update member set introduce = ? where memberkey = ?";
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
}
