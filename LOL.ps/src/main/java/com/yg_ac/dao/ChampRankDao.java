package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yg_ac.dto.ChampNameDto;
import com.yg_ac.dto.ChampRankDto;

public class ChampRankDao {
	Y_DBmanager db = new Y_DBmanager();
	Connection conn = db.getConnection();
	//전체 챔피언
	public ArrayList<ChampNameDto> getChampName() {
		ArrayList<ChampNameDto> get = new ArrayList<ChampNameDto>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select name from champ_skill";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				get.add(new ChampNameDto(name));
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
		return get;
	}
	//챔피언 랭킹 라인별
	public ArrayList<ChampRankDto> getChampRankByLine(String champion_line) {
		ArrayList<ChampRankDto> getChampRankByLine = new ArrayList<ChampRankDto>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
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
	public ArrayList<ChampRankDto> getChampRankAll() {
		ArrayList<ChampRankDto> getChampRankAll = new ArrayList<ChampRankDto>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
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
