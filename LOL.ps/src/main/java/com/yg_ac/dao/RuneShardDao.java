package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.yg_ac.dto.RuneShardDto;

public class RuneShardDao {
	public ArrayList<RuneShardDto> getRuneShard(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		ArrayList<RuneShardDto> getRuneShard = new ArrayList<RuneShardDto>();
		String sql = " select cr.pick1 p1, r1.image h1, r2.image h2, r3.image h3, cr.pick2 p2, cr.pick3 p3, cr.win_rate w, cr.pick_rate p "
					+ " from c_rune_shard cr, rune_info r1, rune_info r2, rune_info r3 "
					+ " where cr.name = ?"
					+ " and cr.line = ?"
					+ " and cr.pick1 = r1.name "
					+ " and cr.pick2 = r2.name "
					+ " and cr.pick3 = r3.name";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String pick1 = rs.getString("p1");
				String pick2 = rs.getString("p2");
				String pick3 = rs.getString("p3");
				String image1 = rs.getString("h1");
				String image2 = rs.getString("h2");
				String image3 = rs.getString("h3");
				Double winRate = rs.getDouble("w");
				Double pickRate = rs.getDouble("p");
				getRuneShard.add(new RuneShardDto(pick1, pick2, pick3, image1, image2, image3, winRate, pickRate));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return getRuneShard;
	}
}
