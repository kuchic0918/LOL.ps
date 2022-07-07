package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yg_ac.dto.ChampMatchListDto;

public class ChampMatchListDao {
	public ArrayList<ChampMatchListDto> getChampMatchListHard(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		ArrayList<ChampMatchListDto> getChampMatchListHard = new ArrayList<ChampMatchListDto>();
		try {
			String sql = "select match.enemy name, match.line line, match.COUNT count, match.WIN_RATE winRate, image.IMAGE_HEAD image"
					+ " from C_CHAMP_MATCH match, CHAMP_SKILL image"
					+ " where match.enemy = image.name"
					+ " and match.name = ?"
					+ " and match.line = ?"
					+ " and match.WIN_RATE < 50"
					+ " order by match.WIN_RATE";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();
			
			if(rs.next()==false) {
				getChampMatchListHard.add(new ChampMatchListDto(null, null, null, 0, null));
			} else {
				while(rs.next()) {
					String name = rs.getString("name");
					String line = rs.getString("line");
					String count = rs.getString("count");
					double winRate = rs.getDouble("winRate");
					String image = rs.getString("image");
					getChampMatchListHard.add(new ChampMatchListDto(name, line, count, winRate, image));
				}
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
		return getChampMatchListHard;
	}
	public ArrayList<ChampMatchListDto> getChampMatchListEasy(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		ArrayList<ChampMatchListDto> getChampMatchListHard = new ArrayList<ChampMatchListDto>();
		try {
			String sql = "select match.enemy name, match.line line, match.COUNT count, match.WIN_RATE winRate, image.IMAGE_HEAD image"
					+ " from C_CHAMP_MATCH match, CHAMP_SKILL image"
					+ " where match.enemy = image.name"
					+ " and match.name = ?"
					+ " and match.line = ?"
					+ " and match.WIN_RATE > 50"
					+ " order by match.WIN_RATE desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("name");
				String line = rs.getString("line");
				String count = rs.getString("count");
				double winRate = rs.getDouble("winRate");
				String image = rs.getString("image");
				getChampMatchListHard.add(new ChampMatchListDto(name, line, count, winRate, image));
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
		return getChampMatchListHard;
	}
}
