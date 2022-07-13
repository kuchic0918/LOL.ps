package com.yg_ac.dao;

import java.sql.Connection;	
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yg_ac.dto.ChampionQWERDto;
import com.yg_ac.dto.ChampionSummary11Dto;
import com.yg_ac.dto.ChampionSummaryHeadDto;
import com.yg_ac.dto.ChampionSummaryHighPositionDto;
import com.yg_ac.dto.ChampionSummaryMainRuneDto;
import com.yg_ac.dto.ChampionSummarySelectPositionDto;
import com.yg_ac.dto.ChampionSummaryWinPickBanRateDto;
import com.yg_ac.dto.GetSkillMasterDto;
import com.yg_ac.dto.RecommendedSpellsDto;

public class StatisticsDao {
	// 스킬 마스터 추천 순서
	public ArrayList<GetSkillMasterDto> getSkillMaster(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
		ArrayList<GetSkillMasterDto> getSkillMaster = new ArrayList<GetSkillMasterDto>();
		try {
			String sql = "select cs.pick1 p1, s1.image h1, s1.function f1, cs.pick2 p2, s2.image h2, s2.function f2, cs.pick3 p3, s3.image h3, s3.function f3, cs.win_rate w, cs.pick_rate p, cs.count c "
					+ " from c_skill_master cs, skill_info s1, skill_info s2, skill_info s3 "
					+ " where cs.name = ? and cs.line = ? "
					+ " and cs.pick1 = s1.name "
					+ " and cs.pick2 = s2.name "
					+ " and cs.pick3 = s3.name"
					+ " order by cs." + champion_rate + "_rate desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String name1 = rs.getString("p1");
				String image1 = rs.getString("h1");
				String function1 = rs.getString("f1");
				getSkillMaster.add(new GetSkillMasterDto(image1, name1, function1));
				String name2 = rs.getString("p2");
				String image2 = rs.getString("h2");
				String function2 = rs.getString("f2");
				getSkillMaster.add(new GetSkillMasterDto(image2, name2, function2));
				String name3 = rs.getString("p3");
				String image3 = rs.getString("h3");
				String function3 = rs.getString("f3");
				getSkillMaster.add(new GetSkillMasterDto(image3, name3, function3));
			}
			
			if(getSkillMaster.size()==0) {
				for(int i=0; i<3; i++) {
					getSkillMaster.add(new GetSkillMasterDto("0.png", "", ""));
					getSkillMaster.add(new GetSkillMasterDto("0.png", "", ""));
					getSkillMaster.add(new GetSkillMasterDto("0.png", "", ""));
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
		return getSkillMaster;
	}
	
	// 추천 스펠
	public ArrayList<RecommendedSpellsDto> recommendedSpells(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
		ArrayList<RecommendedSpellsDto> recommendedSpells = new ArrayList<RecommendedSpellsDto>();
		try {
			for(int i=1; i<=2; i++) {
				String sql = "select cs.pick"+i+" name,"+
						" ii.image image,"+
						" ii.function function"+ 
						" from c_spell_item cs, item_info ii"+ 
						" where cs.pick"+i+" = ii.name and cs.name = ?"+ 
						" and cs.category = '스펠'"+
						" and cs."+champion_rate+"_rate = ("+
						" select max("+champion_rate+"_rate)"+
						" from c_spell_item"+
						" where name = ?"+
						" and line = ?"+
						" and category = '스펠')";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, champion_name);
				pstmt.setString(2, champion_name);
				pstmt.setString(3, champion_line);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					String name = rs.getString("name");
					String image = rs.getString("image");
					String function = rs.getString("function");
					recommendedSpells.add(new RecommendedSpellsDto(image, name, function));
				}
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
		return recommendedSpells;
	}
	
	// 시작 아이템
	public ArrayList<RecommendedSpellsDto> startItem(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
		ArrayList<RecommendedSpellsDto> startItem = new ArrayList<RecommendedSpellsDto>();
		try {
			for(int i=1; i<=2; i++) {
				String sql = "select cs.pick"+i+" name,"+
						" ii.image image,"+
						" ii.function function"+ 
						" from c_spell_item cs, item_info ii"+ 
						" where cs.pick"+i+" = ii.name and cs.name = ?"+ 
						" and cs.category = '스타트 아이템'"+
						" and cs."+champion_rate+"_rate = ("+
						" select max("+champion_rate+"_rate)"+
						" from c_spell_item"+
						" where name = ?"+
						" and line = ?"+
						" and category = '스타트 아이템')";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, champion_name);
				pstmt.setString(2, champion_name);
				pstmt.setString(3, champion_line);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					String name = rs.getString("name");
					String image = rs.getString("image");
					String function = rs.getString("function");
					startItem.add(new RecommendedSpellsDto(image, name, function));
				}
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
		return startItem;
	}
	// 신발
	public ArrayList<RecommendedSpellsDto> shoes(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
		ArrayList<RecommendedSpellsDto> shoes = new ArrayList<RecommendedSpellsDto>();
		try {
			String sql = "select cs.pick1 name,"+
					" ii.image image,"+
					" ii.function function"+ 
					" from c_spell_item cs, item_info ii"+ 
					" where cs.pick1 = ii.name and cs.name = ?"+ 
					" and cs.category = '신발'"+
					" and cs."+champion_rate+"_rate = ("+
					" select max("+champion_rate+"_rate)"+
					" from c_spell_item"+
					" where name = ?"+
					" and line = ?"+
					" and category = '신발')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_name);
			pstmt.setString(3, champion_line);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				String image = rs.getString("image");
				String function = rs.getString("function");
				shoes.add(new RecommendedSpellsDto(image, name, function));
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
		return shoes;
	}
	// qwer 이미지
	public ArrayList<ChampionQWERDto> championSkillImageQWER(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name) {
		ArrayList<ChampionQWERDto> championSkillImageQWER = new ArrayList<ChampionQWERDto>();
			try {
				String sql = "select siq.image q, siw.image w, sie.image e, sir.image r from champ_skill cs, skill_info siq, skill_info siw, skill_info sie, skill_info sir" +
							" where cs.name = ?"+
							" and cs.p = siq.name"+
							" and cs.w = sie.name"+
							" and cs.e = siw.name"+
							" and cs.r = sir.name";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, champion_name);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					String q = rs.getString("q");
					String w = rs.getString("w");
					String e = rs.getString("e");
					String r = rs.getString("r");
					championSkillImageQWER.add(new ChampionQWERDto(q,w,e,r));
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return championSkillImageQWER;
	}
	// 검색한 챔피언 스킬 11 (순서)
	public ArrayList<ChampionSummary11Dto> championSummary11 (Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
		ArrayList<ChampionSummary11Dto> championSummary11 = new ArrayList<ChampionSummary11Dto>();
		try {
			for(int i=1; i<=11; i++) {
				String sql = "select si.skill_key k, si.image h" +
						" from c_skill_seq cs, skill_info si" +
						" where cs.name = ? and cs.line = ? and cs.what_level = 11" +
						" and cs.pick" + i + " = si.name" +
						" and " + champion_rate + "_rate = (select max(" + champion_rate + "_rate) from c_skill_seq where name = ? and line = ? and what_level = 11)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, champion_name);
				pstmt.setString(2, champion_line);
				pstmt.setString(3, champion_name);
				pstmt.setString(4, champion_line);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					String skill = rs.getString("k");
					championSummary11.add(new ChampionSummary11Dto(skill));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return championSummary11;
	}
	// 챔피언 해드 이미지, 이름
	public ChampionSummaryHeadDto championSummaryHead(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name) {
		ChampionSummaryHeadDto championSummaryHead = null;
		String sql = "SELECT * FROM champ_skill cs WHERE cs.name = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				String name = rs.getString("name");
				String image = rs.getString("image_head");
				championSummaryHead = new ChampionSummaryHeadDto(name,image);
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
		return championSummaryHead;
	}
	// 높은 포지션 라인이름, 퍼센트
	public ArrayList<ChampionSummaryHighPositionDto> championSummaryHighPosition(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name) {
		ArrayList<ChampionSummaryHighPositionDto> championSummarySelectPosition = new ArrayList<ChampionSummaryHighPositionDto>();
		try {
			String sql = "select rownum rnum, b2.* from (select * from c_high_pick b where name = ? order by pickrate desc) b2 where rownum < 4";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,champion_name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String line = rs.getString("line");
				double pickRate = rs.getDouble("pickrate");
				championSummarySelectPosition.add(new ChampionSummaryHighPositionDto(line, pickRate));
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
		return championSummarySelectPosition;
	}
	// 선택한 포지션 퍼센트
	public ArrayList<ChampionSummarySelectPositionDto> championSummarySelectPosition(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name) {
		ArrayList<ChampionSummarySelectPositionDto> championSummarySelectPosition = new ArrayList<ChampionSummarySelectPositionDto>();
		try {
			String sql = "select * from c_high_pick where name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				double pickRate = rs.getDouble("pickrate");
				championSummarySelectPosition.add(new ChampionSummarySelectPositionDto(pickRate));
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
		return championSummarySelectPosition;
	}
	// ps스코어 전
		public String championSummaryPsRankBefore(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
			String get = null;
			try {
				String sql = "select ps_score from c_champ_tier_before where c_champ_tier_before.name = ? and line = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,champion_name);
				pstmt.setString(2,champion_line);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					String score = rs.getString("ps_score");
					get = score;
				}
				if(get==null) get = "no score";
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
			return get;
		}
	// ps스코어 현
		public String championSummaryPsRankNow(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
			String get = null;
			try {
				String sql = "select ps_score name from c_champ_tier where c_champ_tier.name = ? and line = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,champion_name);
				pstmt.setString(2,champion_line);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					String score = rs.getString("ps_score");
					get = score;
				}
				if(get==null) get = "no score";
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
			return get;
		}
	// 챔피언순위 전
		public String championSummaryRankingBefore(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
			String get = null;
			try {
				String sql = "select rnum from (select rownum rnum, b2.* from(select * from c_champ_tier_before b order by ps_score desc) b2) where name = ? and line = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, champion_name);
				pstmt.setString(2, champion_line);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					String rnum = rs.getString("rnum");
					get = rnum + "등";
				}
				if(get==null) get = "160등";
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					pstmt.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return get;
		}
	// 챔피언순위 현재
		public String championSummaryRankingNow(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
			String get = null;
			try {
				String sql = "select rnum from (select rownum rnum, b2.* from(select * from c_champ_tier b order by ps_score desc) b2) where name = ? and line = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, champion_name);
				pstmt.setString(2, champion_line);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					String rnum = rs.getString("rnum");
					get = rnum + "등";
				}
				if(get==null) get = "160등";
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					pstmt.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return get;
		}
	// 챔피언 승률 픽률 밴율
		public ChampionSummaryWinPickBanRateDto championSummaryWinPickBanRate(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
			ChampionSummaryWinPickBanRateDto get = null;
			try {
				String sql = "select * from c_champ_tier where c_champ_tier.name = ? and line = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,champion_name);
				pstmt.setString(2,champion_line);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					double winRate = rs.getDouble("win_rate");
					double pickRate = rs.getDouble("pick_rate");
					double banRate = rs.getDouble("ban_rate");
					String count = rs.getString("count2");
					get = new ChampionSummaryWinPickBanRateDto(winRate, pickRate, banRate, count);
				}
				if(get==null) get = new ChampionSummaryWinPickBanRateDto(0, 0, 0, " ");
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
			return get;
		}
	//메인 룬
		//포함된 모든 룬
		public ArrayList<String> championRuneInfoMain(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
			ArrayList<String> championRuneInfoMain = new ArrayList<String>();
			try {
				String sql = " select name from rune_info where" + 
							 " (select build from rune_info where" + 
							 " (select c_rune_combine.pick1 from c_rune_combine where name = ? and" + 
							 " (select max(c_rune_combine." + champion_rate + "_rate)" + 
							 " from c_rune_combine where name = ? and line = ? AND c_rune_combine.pick_rate > 5)" +
							 " = c_rune_combine." + champion_rate + "_rate) = name) = build";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,champion_name);
				pstmt.setString(2,champion_name);
				pstmt.setString(3,champion_line);
				rs = pstmt.executeQuery(); 
				while(rs.next()) {
					String name = rs.getString("name");
					championRuneInfoMain.add(name);
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return championRuneInfoMain;
		}
		//사용하는 룬
		public ArrayList<String> selectRuneMain(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
			ArrayList<String> selectRuneMain = new ArrayList<String>();
			try {
				for(int i=1; i<7; i++) {
					String sql = "SELECT max(crc.pick" + i + ") keep(dense_rank last ORDER BY crc." + champion_rate + "_rate) AS name FROM c_rune_combine crc WHERE crc.name = ? AND crc.line = ? AND crc.pick_rate > 5";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,champion_name);
					pstmt.setString(2,champion_line);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						String name =rs.getString("name");
						selectRuneMain.add(name);
						rs.close();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return selectRuneMain;
		}
		//통합
		public ArrayList<ChampionSummaryMainRuneDto> championSummaryMainRune(Connection conn, PreparedStatement pstmt, ResultSet rs,  String champion_name, String champion_line, String champion_rate) {
			ArrayList<String> championRuneInfoMain = championRuneInfoMain(conn, pstmt, rs, champion_name, champion_line, champion_rate);
			ArrayList<String> selectRuneMain = selectRuneMain(conn, pstmt, rs, champion_name, champion_line, champion_rate);

			ArrayList<ChampionSummaryMainRuneDto> get = new ArrayList<ChampionSummaryMainRuneDto>();

			try {
				// 룬 이름과 설명
				String sql = "SELECT rui.name, rui.image, rui.image_d, rui.function FROM rune_info rui WHERE rui.name = ?";
				pstmt = conn.prepareStatement(sql);
				for(int i=0; i<championRuneInfoMain.size(); i++) {
					pstmt.setString(1, championRuneInfoMain.get(i));
					rs = pstmt.executeQuery();
					if(rs.next()) {
						String name = rs.getString("name");
						String image = rs.getString("image");
						String image_d = rs.getString("image_d");
						String function = rs.getString("function");
						if(name.equals(selectRuneMain.get(0)) || name.equals(selectRuneMain.get(1)) || name.equals(selectRuneMain.get(2)) || name.equals(selectRuneMain.get(3)) || name.equals(selectRuneMain.get(4)) || name.equals(selectRuneMain.get(5))) {
							get.add(new ChampionSummaryMainRuneDto(name, image, function));
						} else {
							get.add(new ChampionSummaryMainRuneDto(name, image_d, function));
						}
						rs.close();
					} 
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(get.size()==0) {
				for(int j=0; j<42; j++) {
					get.add(new ChampionSummaryMainRuneDto("name", "none.png", "function"));
				}
			}
			return get;
		}
}
