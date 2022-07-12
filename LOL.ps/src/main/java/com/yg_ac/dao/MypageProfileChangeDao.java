package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.yg_ac.dto.MypageProfileChangeDto;

public class MypageProfileChangeDao {
	public MypageProfileChangeDto getmypageProfileChange(Connection conn, PreparedStatement pstmt, int key, String image) {
		MypageProfileChangeDto get = null;
		String sql = "update member set image = ? where memberkey = ?";
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
