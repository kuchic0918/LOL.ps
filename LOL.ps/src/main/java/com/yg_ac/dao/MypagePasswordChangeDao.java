package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MypagePasswordChangeDao {
	public void getMypagePasswordChangeDao(Connection conn, PreparedStatement pstmt, int key, String password) {
		String sql = "update member set pw = ? where memberkey = ?";
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
}
