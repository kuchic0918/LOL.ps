package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yg_ac.dto.ChampPatchHistoryDto;

public class ChampPatchHistoryDao {
	public ArrayList<ChampPatchHistoryDto> getChampPatchHistory(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name) {
		ArrayList<ChampPatchHistoryDto> getChampPatchHistory = new ArrayList<ChampPatchHistoryDto>();
		try {
			String sql = "SELECT craw.name name, craw.VERSION2 version, craw.SKILL_KEY skillKey, image.SHOW_NAME skillName, craw.FUNCTION function, image.IMAGE image"
					+ "            FROM c_patch_history craw, SKILL_INFO image"
					+ "            WHERE craw.name = image.champ"
					+ "              and craw.SKILL_KEY = image.SKILL_KEY"
					+ "              and craw.name = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String name = rs.getString("name");
				String version = rs.getString("version");
				String skillKey = rs.getString("skillKey");
				String skillName = rs.getString("skillName");
				String function = rs.getString("function");
				String image = rs.getString("image");
				getChampPatchHistory.add(new ChampPatchHistoryDto(name, version, skillKey, skillName, function, image));
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
		return getChampPatchHistory;
	}
}
