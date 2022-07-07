package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yg_ac.dto.ChampBasicSkillDto;

public class ChampBasicSkillDao {
	public ArrayList<ChampBasicSkillDto> getChampBasicSkill(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name) {
		ArrayList<ChampBasicSkillDto> getChampBasicSkill = new ArrayList<ChampBasicSkillDto>();
		try {
			String sql = "select champ name, SHOW_NAME skillName, SKILL_KEY skillKey, FUNCTION, image from SKILL_INFO where champ = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String name = rs.getString("name");
				String skillLine = rs.getString("skillName");
				String skillKey = rs.getString("skillKey");
				String function = rs.getString("function");
				String image = rs.getString("image");
				getChampBasicSkill.add(new ChampBasicSkillDto(name, skillLine, skillKey, function, image));
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
		return getChampBasicSkill;
	}
}
