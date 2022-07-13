package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yg_ac.dto.SkillMasterDto;

public class SkillMasterDao {
	public ArrayList<SkillMasterDto> getSkillMaster(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		ArrayList<SkillMasterDto> getSkillMaster = new ArrayList<SkillMasterDto>();
		try {
			String sql = "select cs.pick1 pick1, si1.image h1, si1.skill_key key1, si1.function f1, cs.pick2 pick2, si2.image h2, si2.skill_key key2, si2.function f2, cs.pick3 pick3, si3.image h3, si3.skill_key key3, si3.function f3, cs.win_rate w, cs.pick_rate p, cs.count c " + 
					"from c_skill_master cs, skill_info si1, skill_info si2, skill_info si3 " + 
					"where cs.pick1 = si1.name " + 
					"and cs.pick2 = si2.name " + 
					"and cs.pick3 = si3.name " + 
					"and cs.name = ? " + 
					"and cs.line = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String pick1 = rs.getString("pick1");
				String image1 = rs.getString("h1");
				String key1 = rs.getString("key1");
				String function1 = rs.getString("f1");
				
				String pick2 = rs.getString("pick2");
				String image2 = rs.getString("h2");
				String key2 = rs.getString("key2");
				String function2 = rs.getString("f2");
				
				String pick3 = rs.getString("pick3");
				String image3 = rs.getString("h3");
				String key3 = rs.getString("key3");
				String function3 = rs.getString("f3");
				
				double winRate = rs.getDouble("w");
				double pickRate = rs.getDouble("p");
				String count = rs.getString("c");
				
				getSkillMaster.add(new SkillMasterDto(pick1, image1, key1, function1, pick2, image2, key2, function2, pick3, image3, key3, function3, winRate, pickRate, count));
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
		return getSkillMaster;
	}
}
