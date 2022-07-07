package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yg_ac.dto.CoreCombineDto;

public class CoreCombineDao {
	public ArrayList<CoreCombineDto> get2CoreCombine(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		ArrayList<CoreCombineDto> get2CoreCombine = new ArrayList<CoreCombineDto>();
		try {
			String sql = "select cc.pick1 p1, cc.pick2 p2, cc.pick3 p3, cc.pick4 p4, i1.function f1, i2.function f2, i3.function f3, i4.function f4, i1.image h1, i2.image h2, i3.image h3, i4.image h4, cc.win_rate w, cc.pick_rate p, cc.count c "
					+ "from c_core_combine cc, item_info i1, item_info i2, item_info i3, item_info i4 "
					+ "where cc.pick1 = i1.name "
					+ "and cc.pick2 = i2.name "
					+ "and nvl(cc.pick3,'없음') = i3.name "
					+ "and nvl(cc.pick4,'없음') = i4.name "
					+ "and cc.name = ? "
					+ "and cc.line = ? "
					+ "and cc.rank = '2코어 조합'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String pick1 = rs.getString("p1");
				String image1 = rs.getString("h1");
				String function1 = rs.getString("f1");
				
				String pick2 = rs.getString("p2");
				String image2 = rs.getString("h2");
				String function2 = rs.getString("f2");
				
				double winRate = rs.getDouble("w");
				double pickRate = rs.getDouble("p");
				String count = rs.getString("c");
				get2CoreCombine.add(new CoreCombineDto(pick1, image1, function1, pick2, image2, function2, winRate, pickRate, count));
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
		return get2CoreCombine;
	}
	public ArrayList<CoreCombineDto> get3CoreCombine(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		ArrayList<CoreCombineDto> get3CoreCombine = new ArrayList<CoreCombineDto>();
		try {
			String sql = "select cc.pick1 p1, cc.pick2 p2, cc.pick3 p3, cc.pick4 p4, i1.function f1, i2.function f2, i3.function f3, i4.function f4, i1.image h1, i2.image h2, i3.image h3, i4.image h4, cc.win_rate w, cc.pick_rate p, cc.count c "
					+ "from c_core_combine cc, item_info i1, item_info i2, item_info i3, item_info i4 "
					+ "where cc.pick1 = i1.name "
					+ "and cc.pick2 = i2.name "
					+ "and nvl(cc.pick3,'없음') = i3.name "
					+ "and nvl(cc.pick4,'없음') = i4.name "
					+ "and cc.name = ? "
					+ "and cc.line = ? "
					+ "and cc.rank = '3코어 조합'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String pick1 = rs.getString("p1");
				String image1 = rs.getString("h1");
				String function1 = rs.getString("f1");
				
				String pick2 = rs.getString("p2");
				String image2 = rs.getString("h2");
				String function2 = rs.getString("f2");
				
				String pick3 = rs.getString("p3");
				String image3 = rs.getString("h3");
				String function3 = rs.getString("f3");
				
				double winRate = rs.getDouble("w");
				double pickRate = rs.getDouble("p");
				String count = rs.getString("c");
				get3CoreCombine.add(new CoreCombineDto(pick1, image1, function1, pick2, image2, function2, pick3, image3, function3, winRate, pickRate, count));
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
		return get3CoreCombine;
	}
	public ArrayList<CoreCombineDto> get4CoreCombine(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		ArrayList<CoreCombineDto> get4CoreCombine = new ArrayList<CoreCombineDto>();
		try {
			String sql = "select cc.pick1 p1, cc.pick2 p2, cc.pick3 p3, cc.pick4 p4, i1.function f1, i2.function f2, i3.function f3, i4.function f4, i1.image h1, i2.image h2, i3.image h3, i4.image h4, cc.win_rate w, cc.pick_rate p, cc.count c "
					+ "from c_core_combine cc, item_info i1, item_info i2, item_info i3, item_info i4 "
					+ "where cc.pick1 = i1.name "
					+ "and cc.pick2 = i2.name "
					+ "and nvl(cc.pick3,'없음') = i3.name "
					+ "and nvl(cc.pick4,'없음') = i4.name "
					+ "and cc.name = ? "
					+ "and cc.line = ? "
					+ "and cc.rank = '4코어 조합'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String pick1 = rs.getString("p1");
				String image1 = rs.getString("h1");
				String function1 = rs.getString("f1");
				
				String pick2 = rs.getString("p2");
				String image2 = rs.getString("h2");
				String function2 = rs.getString("f2");
				
				String pick3 = rs.getString("p3");
				String image3 = rs.getString("h3");
				String function3 = rs.getString("f3");
				
				String pick4 = rs.getString("p4");
				String image4 = rs.getString("h4");
				String function4 = rs.getString("f4");
				
				double winRate = rs.getDouble("w");
				double pickRate = rs.getDouble("p");
				String count = rs.getString("c");
				get4CoreCombine.add(new CoreCombineDto(pick1, image1, function1, pick2, image2, function2, pick3, image3, function3, pick4, image4, function4, winRate, pickRate, count));
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
		return get4CoreCombine;
	}
}
