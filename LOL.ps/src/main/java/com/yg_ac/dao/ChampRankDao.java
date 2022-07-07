package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yg_ac.dto.ChampRankDto;

public class ChampRankDao {
	
	//챔피언 랭킹 라인별
	public ArrayList<ChampRankDto> getChampRankByLine(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_line) {
		ArrayList<ChampRankDto> getChampRankByLine = new ArrayList<ChampRankDto>();
		try {
			String sql = "SELECT tier.*, image.IMAGE_HEAD"
					+ "            FROM c_champ_tier tier, champ_skill image"
					+ "            WHERE tier.name = image.name"
					+ "            AND tier.line = ?"
					+ "            ORDER BY tier.ps_score desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_line);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String name = rs.getString("name");
				String line = rs.getString("line");
			    double psScore = rs.getDouble("ps_score");
			    double honeyScore = rs.getDouble("honey_score");
			    double winRate = rs.getDouble("win_rate");
			    double pickRate = rs.getDouble("pick_rate");
			    double banRate = rs.getDouble("ban_rate");
				int count1 = rs.getInt("count");
				String count2 = rs.getString("count2");
				String imageHead = rs.getString("image_head");
				getChampRankByLine.add(new ChampRankDto(name, line, psScore, honeyScore, winRate,
														pickRate, banRate, count1, count2, imageHead));
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
		return getChampRankByLine;
	}
	
	
	// 챔피언 랭킨 전체
	public ArrayList<ChampRankDto> getChampRankAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		ArrayList<ChampRankDto> getChampRankAll = new ArrayList<ChampRankDto>();
		try {
			String sql = "SELECT tier.*, image.IMAGE_HEAD\r\n"
					+ "            FROM c_champ_tier tier, champ_skill image\r\n"
					+ "            WHERE tier.name = image.name\r\n"
					+ "            ORDER BY tier.ps_score desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String name = rs.getString("name");
				String line = rs.getString("line");
			    double psScore = rs.getDouble("ps_score");
			    double honeyScore = rs.getDouble("honey_score");
			    double winRate = rs.getDouble("win_rate");
			    double pickRate = rs.getDouble("pick_rate");
			    double banRate = rs.getDouble("ban_rate");
				int count1 = rs.getInt("count");
				String count2 = rs.getString("count2");
				String imageHead = rs.getString("image_head");
				getChampRankAll.add(new ChampRankDto(name, line, psScore, honeyScore, winRate,
														pickRate, banRate, count1, count2, imageHead));
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
		return getChampRankAll;
	}
}
