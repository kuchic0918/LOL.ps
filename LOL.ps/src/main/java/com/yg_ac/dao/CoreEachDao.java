package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yg_ac.dto.CoreEachDto;

public class CoreEachDao {
	public ArrayList<CoreEachDto> getCore1(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		ArrayList<CoreEachDto> getCore1 = new ArrayList<CoreEachDto>();
		try {
			String sql = "select cc.pick, ii.image h, ii.function f, cc.win_rate w, cc.pick_rate p, cc.count c "
					+ "from c_core_each cc, item_info ii "
					+ "where cc.pick = ii.name "
					+ "and cc.rank = '1코어' "
					+ "and cc.name = ? "
					+ "and cc.line = ? "
					+ "order by cc.pick_rate desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String pick = rs.getString("pick");
				String image = rs.getString("h");
				String function = rs.getString("f");
				double winRate = rs.getDouble("w");
				double pickRate = rs.getDouble("p");
				String count = rs.getString("c");
				getCore1.add(new CoreEachDto(pick, image, function, winRate, pickRate, count));
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
		return getCore1;
	}
	public ArrayList<CoreEachDto> getCore2(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		ArrayList<CoreEachDto> getCore2 = new ArrayList<CoreEachDto>();
		try {
			String sql = "select cc.pick, ii.image h, ii.function f, cc.win_rate w, cc.pick_rate p, cc.count c "
					+ "from c_core_each cc, item_info ii "
					+ "where cc.pick = ii.name "
					+ "and cc.rank = '2코어' "
					+ "and cc.name = ? "
					+ "and cc.line = ? "
					+ "order by cc.pick_rate desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String pick = rs.getString("pick");
				String image = rs.getString("h");
				String function = rs.getString("f");
				double winRate = rs.getDouble("w");
				double pickRate = rs.getDouble("p");
				String count = rs.getString("c");
				getCore2.add(new CoreEachDto(pick, image, function, winRate, pickRate, count));
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
		return getCore2;
	}
	public ArrayList<CoreEachDto> getCore3(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		ArrayList<CoreEachDto> getCore3 = new ArrayList<CoreEachDto>();
		try {
			String sql = "select cc.pick, ii.image h, ii.function f, cc.win_rate w, cc.pick_rate p, cc.count c "
					+ "from c_core_each cc, item_info ii "
					+ "where cc.pick = ii.name "
					+ "and cc.rank = '3코어' "
					+ "and cc.name = ? "
					+ "and cc.line = ? "
					+ "order by cc.pick_rate desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String pick = rs.getString("pick");
				String image = rs.getString("h");
				String function = rs.getString("f");
				double winRate = rs.getDouble("w");
				double pickRate = rs.getDouble("p");
				String count = rs.getString("c");
				getCore3.add(new CoreEachDto(pick, image, function, winRate, pickRate, count));
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
		return getCore3;
	}
}
