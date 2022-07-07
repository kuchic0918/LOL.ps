package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yg_ac.dto.RuneCombineDto;

public class RuneCombineDao {
	public ArrayList<RuneCombineDto> getRuneCombine(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		ArrayList<RuneCombineDto> getRuneCombine = new ArrayList<RuneCombineDto>();
		String sql = " select cr.pick1, ri1.image h1, ri1.function f1, cr.pick2, ri2.image h2, ri2.function f2, cr.pick3, ri3.image h3, ri3.function f3, cr.pick4, ri4.image h4, ri4.function f4, cr.pick5, ri5.image h5, ri5.function f5, cr.pick6, ri6.image h6, ri6.function f6, cr.win_rate w, cr.pick_rate p "
					+ " from c_rune_combine cr, rune_info ri1 , rune_info ri2, rune_info ri3, rune_info ri4, rune_info ri5, rune_info ri6 "
					+ " where cr.pick1 = ri1.name "
					+ " and cr.pick2 = ri2.name "
					+ " and cr.pick3 = ri3.name "
					+ " and cr.pick4 = ri4.name "
					+ " and cr.pick5 = ri5.name "
					+ " and cr.pick6 = ri6.name "
					+ " and cr.name = ?"
					+ " and cr.line = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String pick1 = rs.getString("pick1");
				String pick2 = rs.getString("pick2");
				String pick3 = rs.getString("pick3");
				String pick4 = rs.getString("pick4");
				String pick5 = rs.getString("pick5");
				String pick6 = rs.getString("pick6");
				String function1 = rs.getString("f1");
				String function2 = rs.getString("f2");
				String function3 = rs.getString("f3");
				String function4 = rs.getString("f4");
				String function5 = rs.getString("f5");
				String function6 = rs.getString("f6");
				String image1 = rs.getString("h1");
				String image2 = rs.getString("h2");
				String image3 = rs.getString("h3");
				String image4 = rs.getString("h4");
				String image5 = rs.getString("h5");
				String image6 = rs.getString("h6");
				Double winRate = rs.getDouble("w");
				Double pickRate = rs.getDouble("p");
				getRuneCombine.add(new RuneCombineDto(pick1, pick2, pick3, pick4, pick5, pick6, image1, image2, image3, image4, image5, image6, function1, function2, function3, function4, function5, function6, winRate, pickRate));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return getRuneCombine;
	}
}
