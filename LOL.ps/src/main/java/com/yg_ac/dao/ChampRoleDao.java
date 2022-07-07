package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yg_ac.dto.ChampRoleDto;

public class ChampRoleDao {
	public ArrayList<ChampRoleDto> getChampRole(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name) {
		ArrayList<ChampRoleDto> getChampRole = new ArrayList<ChampRoleDto>();
		try {
			String sql = "select role1 r1, nvl(role2,'없음') r2 "
					+ "from champ_skill "
					+ "where name = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String role1 = rs.getString("r1");
				String role2 = rs.getString("r2");
				getChampRole.add(new ChampRoleDto(role1, role2));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return getChampRole;
	}
}
