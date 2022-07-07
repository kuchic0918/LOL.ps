package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yg_ac.dto.ChampStartItemDto;

public class ChampStartItemDao {
	public ArrayList<ChampStartItemDto> getSpell(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		ArrayList<ChampStartItemDto> getSpell = new ArrayList<ChampStartItemDto>();
		try {
			String sql = "select craw.name name, craw.line line,image.name name1, image.IMAGE pick1, image.function function1, image2.name name2, image2.IMAGE pick2, image2.function function2, craw.WIN_RATE winRate, craw.PICK_RATE pickRate, craw.COUNT count"
					+ " from C_SPELL_ITEM craw, ITEM_INFO image, ITEM_INFO image2"
					+ " where craw.pick1 = image.name"
					+ " and craw.pick2 = image2.name"
					+ " and craw.CATEGORY = '스펠'"
					+ " and craw.name = ?"
					+ " and craw.line = ?"
					+ " order by craw.PICK_RATE desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String name = rs.getString("name");
				String line = rs.getString("line");
				String name1 = rs.getString("name1");
				String pick1 = rs.getString("pick1");
				String function1 = rs.getString("function1");
				String name2 = rs.getString("name2");
				String pick2 = rs.getString("pick2");
				String function2 = rs.getString("function2");
				double winRate = rs.getDouble("winRate");
				double pickRate = rs.getDouble("pickRate");
				String count = rs.getString("count");
				getSpell.add(new ChampStartItemDto(name, line, name1, pick1, function1, name2, pick2, function2, winRate, pickRate, count));
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
		return getSpell;
	}
	
	public ArrayList<ChampStartItemDto> getStartItem(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		ArrayList<ChampStartItemDto> getStartItem = new ArrayList<ChampStartItemDto>();
		try {
			String sql = "select craw.name name, craw.line line, image.name name1, image.IMAGE pick1, image.function function1, image2.name name2, nvl(image2.IMAGE, '없음') pick2, image2.function function2, craw.WIN_RATE winRate, craw.PICK_RATE pickRate, craw.COUNT count"
					+ " from C_SPELL_ITEM craw, ITEM_INFO image, ITEM_INFO image2"
					+ " where craw.pick1 = image.name"
					+ " and nvl(craw.pick2, '없음') = image2.name"
					+ " and craw.CATEGORY = '스타트 아이템'"
					+ " and craw.name = ?"
					+ " and craw.line = ?"
					+ " order by craw.PICK_RATE desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("name");
				String line = rs.getString("line");
				String name1 = rs.getString("name1");
				String pick1 = rs.getString("pick1");
				String function1 = rs.getString("function1");
				String name2 = rs.getString("name2");
				String pick2 = rs.getString("pick2");
				String function2 = rs.getString("function2");
				double winRate = rs.getDouble("winRate");
				double pickRate = rs.getDouble("pickRate");
				String count = rs.getString("count");
				getStartItem.add(new ChampStartItemDto(name, line, name1, pick1, function1, name2, pick2, function2, winRate, pickRate, count));
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
		return getStartItem;
	}
	public ArrayList<ChampStartItemDto> getShoes(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		ArrayList<ChampStartItemDto> getShoes = new ArrayList<ChampStartItemDto>();
		try {
			String sql = "select craw.name name, craw.line line, image.name name1, image.IMAGE pick1, image.function function1, image2.name name2, nvl(image2.IMAGE, '없음') pick2, image2.function function2, craw.WIN_RATE winRate, craw.PICK_RATE pickRate, craw.COUNT count"
					+ " from C_SPELL_ITEM craw, ITEM_INFO image, ITEM_INFO image2"
					+ " where craw.pick1 = image.name"
					+ " and nvl(craw.pick2, '없음') = image2.name"
					+ " and craw.CATEGORY = '신발'"
					+ " and craw.name = ?"
					+ " and craw.line = ?"
					+ " order by craw.PICK_RATE desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("name");
				String line = rs.getString("line");
				String name1 = rs.getString("name1");
				String pick1 = rs.getString("pick1");
				String function1 = rs.getString("function1");
				String name2 = rs.getString("name2");
				String pick2 = rs.getString("pick2");
				String function2 = rs.getString("function2");
				double winRate = rs.getDouble("winRate");
				double pickRate = rs.getDouble("pickRate");
				String count = rs.getString("count");
				getShoes.add(new ChampStartItemDto(name, line, name1, pick1, function1, name2, pick2, function2, winRate, pickRate, count));
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
		return getShoes;
	}
}
