package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MypageMemberSecessionDao {
	public void getMypageMemberSecession(Connection conn, PreparedStatement pstmt, int key) {
		String sql = "DELETE FROM member WHERE memberkey=?";
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
}
