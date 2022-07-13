package com.yg_ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yg_ac.dto.ChampMatchListDto;
import com.yg_ac.dto.SkillSeqDto;

public class SkillSeqDao {
	public ArrayList<SkillSeqDto> getSkillSeq3(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		ArrayList<SkillSeqDto> getSkillSeq3 = new ArrayList<SkillSeqDto>();
		String sql = "select css.pick1, si1.image h1, si1.function f1, css.pick2, si2.image h2, si2.function f2, css.pick3, si3.image h3, si3.function f3, css.win_rate w, css.pick_rate p, css.count c  " + 
				" from c_skill_seq css, skill_info si1, skill_info si2, skill_info si3 " + 
				" where css.pick1 = si1.name " + 
				" and css.pick2 = si2.name " + 
				" and css.pick3 = si3.name " + 
				" and css.name = ? " + 
				" and css.line = ? " + 
				" and css.what_level = 3 " + 
				" order by pick_rate desc ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String pick1 = rs.getString("pick1"); 
				String pick2 = rs.getString("pick2"); 
				String pick3 = rs.getString("pick3"); 
				String image1 = rs.getString("h1"); 
				String image2 = rs.getString("h2"); 
				String image3 = rs.getString("h3"); 
				String function1 = rs.getString("f1"); 
				String function2 = rs.getString("f2"); 
				String function3 = rs.getString("f3"); 
				double winRate = rs.getDouble("w");
				double pickRate = rs.getDouble("p");
				String count = rs.getString("c");
				
				getSkillSeq3.add(new SkillSeqDto( pick1,  pick2,  pick3, image1,  image2,  image3,
						 function1,  function2,  function3,  winRate,  pickRate,  count));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return getSkillSeq3;
	}
	public ArrayList<SkillSeqDto> getSkillSeq6(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		ArrayList<SkillSeqDto> getSkillSeq6 = new ArrayList<SkillSeqDto>();
		String sql = "select css.pick1, si1.image h1, si1.function f1, css.pick2, si2.image h2, si2.function f2, css.pick3, si3.image h3, si3.function f3, css.pick4, si4.image h4, si4.function f4, " + 
				" css.pick5, si5.image h5, si5.function f5, css.pick6, si6.image h6, si6.function f6, css.win_rate w, css.pick_rate p, css.count c  " + 
				" from c_skill_seq css, skill_info si1, skill_info si2, skill_info si3, skill_info si4, skill_info si5, skill_info si6 " + 
				" where css.pick1 = si1.name " + 
				" and css.pick2 = si2.name " + 
				" and css.pick3 = si3.name " + 
				" and css.pick4 = si4.name " + 
				" and css.pick5 = si5.name " + 
				" and css.pick6 = si6.name " + 
				" and css.name = ? " + 
				" and css.line = ? " + 
				" and css.what_level = 6 " + 
				" order by pick_rate desc ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String pick1 = rs.getString("pick1"); 
				String pick2 = rs.getString("pick2"); 
				String pick3 = rs.getString("pick3"); 
				String pick4 = rs.getString("pick4"); 
				String pick5 = rs.getString("pick5"); 
				String pick6 = rs.getString("pick6"); 
				String image1 = rs.getString("h1"); 
				String image2 = rs.getString("h2"); 
				String image3 = rs.getString("h3"); 
				String image4 = rs.getString("h4"); 
				String image5 = rs.getString("h5"); 
				String image6 = rs.getString("h6"); 
				String function1 = rs.getString("f1"); 
				String function2 = rs.getString("f2"); 
				String function3 = rs.getString("f3"); 
				String function4 = rs.getString("f4"); 
				String function5 = rs.getString("f5"); 
				String function6 = rs.getString("f6"); 
				double winRate = rs.getDouble("w");
				double pickRate = rs.getDouble("p");
				String count = rs.getString("c");
				
				getSkillSeq6.add(new SkillSeqDto(pick1,pick2,pick3,pick4,pick5,pick6,image1,image2,image3,
						image4,image5,image6,function1,function2,function3,function4,function5,
						function6,winRate,pickRate,count));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return getSkillSeq6;
	}
	public ArrayList<SkillSeqDto> getSkillSeq11(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		ArrayList<SkillSeqDto> getSkillSeq11 = new ArrayList<SkillSeqDto>();
		String sql = "select css.pick1, si1.image h1, si1.function f1, css.pick2, si2.image h2, si2.function f2, css.pick3, si3.image h3, si3.function f3, css.pick4, si4.image h4, si4.function f4, " + 
				" css.pick5, si5.image h5, si5.function f5, css.pick6, si6.image h6, si6.function f6, css.pick7, si7.image h7, si7.function f7, css.pick8, si8.image h8, si8.function f8, css.pick9, si9.image h9,  " + 
				" si9.function f9, css.pick10, si10.image h10, si10.function f10, css.pick11, si11.image h11, si11.function f11, " + 
				" css.win_rate w, css.pick_rate p, css.count c  " + 
				" from c_skill_seq css, skill_info si1, skill_info si2, skill_info si3, skill_info si4, skill_info si5, skill_info si6,  " + 
				" skill_info si7, skill_info si8, skill_info si9, skill_info si10, skill_info si11 " + 
				" where css.pick1 = si1.name " + 
				" and css.pick2 = si2.name " + 
				" and css.pick3 = si3.name " + 
				" and css.pick4 = si4.name " + 
				" and css.pick5 = si5.name " + 
				" and css.pick6 = si6.name " + 
				" and css.pick7 = si7.name " + 
				" and css.pick8 = si8.name " + 
				" and css.pick9 = si9.name " + 
				" and css.pick10 = si10.name " + 
				" and css.pick11 = si11.name " + 
				" and css.name = ? " + 
				" and css.line = ? " + 
				" and css.what_level = 11 " + 
				" order by pick_rate desc ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String pick1 = rs.getString("pick1"); 
				String pick2 = rs.getString("pick2"); 
				String pick3 = rs.getString("pick3"); 
				String pick4 = rs.getString("pick4"); 
				String pick5 = rs.getString("pick5"); 
				String pick6 = rs.getString("pick6"); 
				String pick7 = rs.getString("pick7"); 
				String pick8 = rs.getString("pick8"); 
				String pick9 = rs.getString("pick9"); 
				String pick10 = rs.getString("pick10"); 
				String pick11 = rs.getString("pick11"); 
				String image1 = rs.getString("h1"); 
				String image2 = rs.getString("h2"); 
				String image3 = rs.getString("h3"); 
				String image4 = rs.getString("h4"); 
				String image5 = rs.getString("h5"); 
				String image6 = rs.getString("h6"); 
				String image7 = rs.getString("h7"); 
				String image8 = rs.getString("h8"); 
				String image9 = rs.getString("h9"); 
				String image10 = rs.getString("h10"); 
				String image11 = rs.getString("h11");
				String function1 = rs.getString("f1"); 
				String function2 = rs.getString("f2"); 
				String function3 = rs.getString("f3"); 
				String function4 = rs.getString("f4"); 
				String function5 = rs.getString("f5"); 
				String function6 = rs.getString("f6"); 
				String function7 = rs.getString("f7"); 
				String function8 = rs.getString("f8"); 
				String function9 = rs.getString("f9"); 
				String function10 = rs.getString("f10"); 
				String function11 = rs.getString("f11");
				double winRate = rs.getDouble("w");
				double pickRate = rs.getDouble("p");
				String count = rs.getString("c");
				
				getSkillSeq11.add(new SkillSeqDto(pick1,pick2,pick3,pick4,pick5,pick6,pick7,
						pick8,pick9,pick10,pick11,image1,image2,image3,
						image4,image5,image6,image7,image8,image9,image10,
						image11,function1,function2,function3,function4,function5,
						function6,function7,function8,function9,function10,
						function11,winRate,pickRate,count));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return getSkillSeq11;
	}
}
