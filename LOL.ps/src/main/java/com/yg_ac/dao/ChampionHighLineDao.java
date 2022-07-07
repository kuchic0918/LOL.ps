package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yg_ac.dto.ChampionHighLineDto;

public class ChampionHighLineDao {
	public ChampionHighLineDto getChampionHighLine(Connection conn, PreparedStatement pstmt,ResultSet rs, String champion_name) {
		ChampionHighLineDto getChampionHighLine = null;
		
		String sql = "select line from c_high_pick where name = ? order by pickrate desc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String line = rs.getString("line");
				getChampionHighLine = new ChampionHighLineDto(line);
			}
			if(rs.next()==false) {
				getChampionHighLine = new ChampionHighLineDto(null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return getChampionHighLine;
	}
}
