package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yg_ac.dto.ChampBasicStatDto;

public class ChampBasicStatDao {
	public ArrayList<ChampBasicStatDto> getChampBasicStat(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name) {
		ArrayList<ChampBasicStatDto> getChampBasicStat = new ArrayList<ChampBasicStatDto>();
		try {
			String sql = "select name, stat, STAT_START startStat, STAT_FINAL finalStat, STAT_RANK statRank "
					+ "from C_BASICSTAT "
					+ "where name = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String name = rs.getString("name");
				String stat = rs.getString("stat");
				String startStat = rs.getString("startStat");
				String finalStat = rs.getString("finalStat");
				String statRank = rs.getString("statRank");
				getChampBasicStat.add(new ChampBasicStatDto(name, stat, startStat, finalStat, statRank));
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
		return getChampBasicStat;
	}
}
