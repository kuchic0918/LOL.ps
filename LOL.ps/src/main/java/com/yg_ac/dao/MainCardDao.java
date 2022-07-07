package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yg_ac.dto.*;

public class MainCardDao {
	public ArrayList<MainCardDto> getMainCard(Connection conn, PreparedStatement pstmt,ResultSet rs) throws SQLException {
		ArrayList<MainCardDto> mainCard = new ArrayList<MainCardDto>();
		
		try {
			String sql = "select t.* , image.IMAGE_FULL image"
					+ "      from ("
					+ "          select tier.name,"
					+ "                 tier.line,"
					+ "                 tier.win_rate                            w,"
					+ "                 tier_before.win_rate                     w_before,"
					+ "                 (tier_before.win_rate - tier.win_rate)   win_vari,"
					+ "                 tier.pick_rate                           p,"
					+ "                 tier_before.pick_rate                    p_before,"
					+ "                 (tier_before.pick_rate - tier.pick_rate) pick_vari,"
					+ "                 tier.ban_rate                            b,"
					+ "                 tier_before.ban_rate                     b_before,"
					+ "                 (tier_before.ban_rate - tier.ban_rate)   ban_vari"
					+ "          from c_champ_tier tier,"
					+ "               c_champ_tier_before tier_before"
					+ "          where tier.name = tier_before.name"
					+ "           and tier.line = tier_before.line"
					+ "      ) t, CHAMP_SKILL image"
					+ "      where t.name = image.name"
					+ "       and( t.win_vari > 2 or t.win_vari < -2.5 )"
					+ "       and ROWNUM < 9"
					+ "      order by win_vari desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int row = 0;

			while(rs.next()) {
				String name = rs.getString("name");
				String line = rs.getString("line");
				double win = rs.getDouble("w");
				double winBefore = rs.getDouble("w_before");
				double winVari = rs.getDouble("win_vari");
				double pick = rs.getDouble("p");
				double pickBefore = rs.getDouble("p_before");
				double pickVari = rs.getDouble("pick_vari");
				double ban = rs.getDouble("b");
				double banBefore = rs.getDouble("b_before");
				double banVari = rs.getDouble("ban_vari");
				String image = rs.getString("image");
				
				row++;
				
				if(row < 10 || row > 220) {
					mainCard.add(new MainCardDto(name, line, win, winBefore, winVari, pick, pickBefore, pickVari, ban, banBefore, banVari, image));
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
	
		
		return mainCard;
	}
	
	
	public MainCardDto getMainCard(Connection conn, PreparedStatement pstmt,ResultSet rs, String champion_name, String champion_line) throws SQLException {
		MainCardDto mainCard = null;
		try {
			String sql = "select t.* , image.IMAGE_FULL" + 
						" from ( select tier.name, tier.line, tier.win_rate w, tier_before.win_rate w_before, (tier_before.win_rate - tier.win_rate) win_vari," + 
						" tier.pick_rate p, tier_before.pick_rate p_before, (tier_before.pick_rate - tier.pick_rate) pick_vari, tier.ban_rate b," + 
						" tier_before.ban_rate b_before, (tier_before.ban_rate - tier.ban_rate) ban_vari" + 
						" from c_champ_tier tier, c_champ_tier_before tier_before" + 
						" where tier.name = tier_before.name" + 
						" and tier.line = tier_before.line ) t, CHAMP_SKILL image" + 
						" where t.name = image.name" + 
						" and t.name = ?" + 
						" and t.line = ?" + 
						" order by win_vari desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String name = rs.getString("name");
				String line = rs.getString("line");
				double win = rs.getDouble("w");
				double winBefore = rs.getDouble("w_before");
				double winVari = rs.getDouble("win_vari");
				double pick = rs.getDouble("p");
				double pickBefore = rs.getDouble("p_before");
				double pickVari = rs.getDouble("pick_vari");
				double ban = rs.getDouble("b");
				double banBefore = rs.getDouble("b_before");
				double banVari = rs.getDouble("ban_vari");
				String image = rs.getString("image_full");
				mainCard = new MainCardDto(name, line, win, winBefore, winVari, pick, pickBefore, pickVari, ban, banBefore, banVari, image);
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
		return mainCard;
	}
}
