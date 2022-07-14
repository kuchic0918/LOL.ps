package com.yg_ac.dao;

import java.sql.Connection;		
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yg_ac.dto.ChampBasicSkillDto;
import com.yg_ac.dto.ChampBasicStatDto;
import com.yg_ac.dto.ChampMatchListDto;
import com.yg_ac.dto.ChampPatchHistoryDto;
import com.yg_ac.dto.ChampRoleDto;
import com.yg_ac.dto.ChampStartItemDto;
import com.yg_ac.dto.ChampionQWERDto;
import com.yg_ac.dto.ChampionRuneDto;
import com.yg_ac.dto.ChampionSummary11Dto;
import com.yg_ac.dto.ChampionSummaryHeadDto;
import com.yg_ac.dto.ChampionSummaryHighPositionDto;
import com.yg_ac.dto.ChampionSummarySelectPositionDto;
import com.yg_ac.dto.ChampionSummaryWinPickBanRateDto;
import com.yg_ac.dto.CoreCombineDto;
import com.yg_ac.dto.CoreEachDto;
import com.yg_ac.dto.GetSkillMasterDto;
import com.yg_ac.dto.RecommendedSpellsDto;
import com.yg_ac.dto.RuneCombineDto;
import com.yg_ac.dto.RuneShardDto;
import com.yg_ac.dto.SkillMasterDto;
import com.yg_ac.dto.SkillSeqDto;

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
				String sql = "select ps_score from c_champ_tier where c_champ_tier.name = ? and line = ?";
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
		private ArrayList<String> championRuneInfoMain(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
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
				}
				rs.close();
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
		private ArrayList<String> selectRuneMain(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
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
					}
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return selectRuneMain;
		}
		//통합
		public ArrayList<ChampionRuneDto> championSummaryMainRune(Connection conn, PreparedStatement pstmt, ResultSet rs,  String champion_name, String champion_line, String champion_rate) {
			ArrayList<String> championRuneInfoMain = championRuneInfoMain(conn, pstmt, rs, champion_name, champion_line, champion_rate);
			ArrayList<String> selectRuneMain = selectRuneMain(conn, pstmt, rs, champion_name, champion_line, champion_rate);

			ArrayList<ChampionRuneDto> get = new ArrayList<ChampionRuneDto>();

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
							get.add(new ChampionRuneDto(name, image, function));
						} else {
							get.add(new ChampionRuneDto(name, image_d, function));
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
					get.add(new ChampionRuneDto("name", "none.png", "function"));
				}
			}
			return get;
		}
	// 보조룬
		// 포함된 보조룬
		private ArrayList<String> championRuneInfoAssist(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
			ArrayList<String> championRuneInfoAssist = new ArrayList<String>();
			String sql = " select name from rune_info_assist where"
					+ " (select build from rune_info_assist where"
					+ " (select c_rune_combine.pick5 from c_rune_combine where name = ? and"
					+ " (select max(c_rune_combine." + champion_rate + "_rate)"
					+ " from c_rune_combine where name = ? and line = ? AND c_rune_combine.pick_rate > 5)"
					+ " = c_rune_combine." + champion_rate + "_rate) = name) = build";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, champion_name);
				pstmt.setString(2, champion_name);
				pstmt.setString(3, champion_line);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					String name = rs.getString("name"); 
					championRuneInfoAssist.add(name);
				}
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return championRuneInfoAssist;
		}
		// 통합
		public ArrayList<ChampionRuneDto> championSummaryAssisRune(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
			ArrayList<String> championRuneInfoAssist = championRuneInfoAssist(conn, pstmt, rs, champion_name, champion_line, champion_rate);
			ArrayList<String> selectRuneMain = selectRuneMain(conn, pstmt, rs, champion_name, champion_line, champion_rate);
			
			ArrayList<ChampionRuneDto> get = new ArrayList<ChampionRuneDto>();
			
			String sql = "SELECT rui.name, rui.image, rui.image_d, rui.function FROM rune_info rui WHERE rui.name = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				
				for(int i=0; i<championRuneInfoAssist.size(); i++) {
					pstmt.setString(1, championRuneInfoAssist.get(i));
					rs = pstmt.executeQuery();
		
					if(rs.next()) {
						String name = rs.getString("name");
						String image = rs.getString("image");
						String image_d = rs.getString("image_d");
						String function = rs.getString("function");
						
						if(name.equals(selectRuneMain.get(0)) || name.equals(selectRuneMain.get(1)) || name.equals(selectRuneMain.get(2)) || name.equals(selectRuneMain.get(3)) || name.equals(selectRuneMain.get(4)) || name.equals(selectRuneMain.get(5)) ) {
							get.add(new ChampionRuneDto(name, image, function));
						} else {
							get.add(new ChampionRuneDto(name, image_d, function));
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
				for(int j=0; j<30; j++) {
					get.add(new ChampionRuneDto("name", "none2.png", "function"));
				}
			}
			return get;
		}
	// 서브룬
		// 선택한 서브룬
		public ArrayList<String> selectRuneSub(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
			ArrayList<String> selectRuneSub = new ArrayList<String>();

			try {
				for(int i=1; i<4; i++) {
					String sql = "SELECT max(crs.pick" + i + ") keep(dense_rank last ORDER BY crs." + champion_rate + "_rate) AS name FROM c_rune_shard crs WHERE crs.name = ? AND crs.line = ? AND crs.pick_rate > 5";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,champion_name);
					pstmt.setString(2, champion_line);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						String name =rs.getString("name");
						selectRuneSub.add(name);
						//System.out.println(name);
					}
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
			return selectRuneSub;
		}
		//통합
		public ArrayList<ChampionRuneDto> championSummarySubRune(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
			ArrayList<String> selectRuneSub = selectRuneSub(conn, pstmt, rs, champion_name, champion_line, champion_rate);
			String[] runeSubArr = {"적응형 능력치 +9","공격속도 +10%","스킬 가속 +8","적응형 능력치 +9","방어력 +6","마법저항력 +8","체력 +15~140 (레벨에 비례)","방어력 +6","마법저항력 +8"};
			
			ArrayList<ChampionRuneDto> get = new ArrayList<ChampionRuneDto>();
			
			String sql = "SELECT * FROM rune_info rui WHERE rui.name = ?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				for(int i=0; i<3; i++) {
					pstmt.setString(1, runeSubArr[i]);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						String name = rs.getString("name");
						String image = rs.getString("image");
						String image_d = rs.getString("image_d");
						if( name.equals(selectRuneSub.get(0)) ) {
							get.add(new ChampionRuneDto(name, image_d, null));
						} else {
							get.add(new ChampionRuneDto(name, image, null));
						}
					}
					rs.close();
				}
				for(int i=3; i<6; i++) {
					pstmt.setString(1, runeSubArr[i]);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						String name = rs.getString("name");
						String image = rs.getString("image");
						String image_d = rs.getString("image_d");
						if( name.equals(selectRuneSub.get(1)) ) {
							get.add(new ChampionRuneDto(name, image_d, null));
						} else {
							get.add(new ChampionRuneDto(name, image, null));
						}
					}
					rs.close();
				}
				for(int i=6; i<9; i++) {
					pstmt.setString(1, runeSubArr[i]);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						String name = rs.getString("name");
						String image = rs.getString("image");
						String image_d = rs.getString("image_d");
						if( name.equals(selectRuneSub.get(2)) ) {
							get.add(new ChampionRuneDto(name, image_d, null));
						} else {
							get.add(new ChampionRuneDto(name, image, null));
						}
					}
					rs.close();
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
			return get;
		}
	// 1코어
		public ArrayList<ChampionRuneDto> championSummaryItemEach1(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
			ArrayList<ChampionRuneDto> get = new ArrayList<ChampionRuneDto>();
			String andPick_rate = null;
			if(champion_rate.equals("win")) {
				andPick_rate = " and cc.pick_rate >=5 ";
			} else andPick_rate = " ";
			try {
				String sql = "select cc.name, cc.rank, cc.pick item_name, info.image item_image, cc.win_rate, cc.pick_rate, info.function from c_core_each cc, item_info info where cc.rank = '1코어' and cc.name = ? and cc.line = ? and cc.pick = info.name" + andPick_rate + "order by cc.win_rate desc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, champion_name);
				pstmt.setString(2, champion_line);
				rs = pstmt.executeQuery();
				int cnt = 0;
				while(rs.next()) {
					if(cnt<3) {
						String name = rs.getString("item_name");
						String image = rs.getString("item_image");
						String function = rs.getString("function");
						get.add(new ChampionRuneDto(name, image, function));
						cnt++;
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
			return get;
		}	
	// 2코어
		public ArrayList<ChampionRuneDto> championSummaryItemEach2(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
			ArrayList<ChampionRuneDto> get = new ArrayList<ChampionRuneDto>();
			String andPick_rate = null;
			if(champion_rate.equals("win")) {
				andPick_rate = " and cc.pick_rate >=5 ";
			} else andPick_rate = " ";
			try {
				String sql = "select cc.name, cc.rank, cc.pick item_name, info.image item_image, cc.win_rate, cc.pick_rate, info.function from c_core_each cc, item_info info where cc.rank = '2코어' and cc.name = ? and cc.line = ? and cc.pick = info.name" + andPick_rate + "order by cc.win_rate desc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, champion_name);
				pstmt.setString(2, champion_line);
				rs = pstmt.executeQuery();
				int cnt = 0;
				while(rs.next()) {
					if(cnt<3) {
						String name = rs.getString("item_name");
						String image = rs.getString("item_image");
						String function = rs.getString("function");
						get.add(new ChampionRuneDto(name, image, function));
						cnt++;
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
			return get;
		}
	// 3코어
		public ArrayList<ChampionRuneDto> championSummaryItemEach3(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
			ArrayList<ChampionRuneDto> get = new ArrayList<ChampionRuneDto>();
			String andPick_rate = null;
			if(champion_rate.equals("win")) {
				andPick_rate = " and cc.pick_rate >=5 ";
			} else andPick_rate = " ";
			try {
				String sql = "select cc.name, cc.rank, cc.pick item_name, info.image item_image, cc.win_rate, cc.pick_rate, info.function from c_core_each cc, item_info info where cc.rank = '3코어' and cc.name = ? and cc.line = ? and cc.pick = info.name" + andPick_rate + "order by cc.win_rate desc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, champion_name);
				pstmt.setString(2, champion_line);
				rs = pstmt.executeQuery();
				int cnt = 0;
				while(rs.next()) {
					if(cnt<3) {
						String name = rs.getString("item_name");
						String image = rs.getString("item_image");
						String function = rs.getString("function");
						get.add(new ChampionRuneDto(name, image, function));
						cnt++;
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
			return get;
		}
	// 상대하기 쉬움
		public ArrayList<ChampMatchListDto> getChampMatchListHard(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
			ArrayList<ChampMatchListDto> getChampMatchListHard = new ArrayList<ChampMatchListDto>();
			try {
				String sql = "select match.enemy name, match.line line, match.COUNT count, match.WIN_RATE winRate, image.IMAGE_HEAD image"
						+ " from C_CHAMP_MATCH match, CHAMP_SKILL image"
						+ " where match.enemy = image.name"
						+ " and match.name = ?"
						+ " and match.line = ?"
						+ " and match.WIN_RATE < 50"
						+ " order by match.WIN_RATE";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, champion_name);
				pstmt.setString(2, champion_line);
				rs = pstmt.executeQuery();
				
				if(rs.next()==false) {
					getChampMatchListHard.add(new ChampMatchListDto(null, null, null, 0, null));
				} else {
					while(rs.next()) {
						String name = rs.getString("name");
						String line = rs.getString("line");
						String count = rs.getString("count");
						double winRate = rs.getDouble("winRate");
						String image = rs.getString("image");
						getChampMatchListHard.add(new ChampMatchListDto(name, line, count, winRate, image));
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
			return getChampMatchListHard;
		}
	// 상대하기 어려움
		public ArrayList<ChampMatchListDto> getChampMatchListEasy(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
			ArrayList<ChampMatchListDto> getChampMatchListHard = new ArrayList<ChampMatchListDto>();
			try {
				String sql = "select match.enemy name, match.line line, match.COUNT count, match.WIN_RATE winRate, image.IMAGE_HEAD image"
						+ " from C_CHAMP_MATCH match, CHAMP_SKILL image"
						+ " where match.enemy = image.name"
						+ " and match.name = ?"
						+ " and match.line = ?"
						+ " and match.WIN_RATE > 50"
						+ " order by match.WIN_RATE desc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, champion_name);
				pstmt.setString(2, champion_line);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					String name = rs.getString("name");
					String line = rs.getString("line");
					String count = rs.getString("count");
					double winRate = rs.getDouble("winRate");
					String image = rs.getString("image");
					getChampMatchListHard.add(new ChampMatchListDto(name, line, count, winRate, image));
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
			return getChampMatchListHard;
		}
	// 스펠
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
	// 스타트 아이템	
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
	// 신발
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
	// 1코어 아이템
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
	// 2코어 아이템
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
	// 3코어 아이템
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
	// 2코어 아이템(2개)
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
	// 3코어 아이템(3개)
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
	// 4코어 아이템(4개)
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
	// 스킬 마스터리 순서
		public ArrayList<SkillMasterDto> getSkillMaster(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
			ArrayList<SkillMasterDto> getSkillMaster = new ArrayList<SkillMasterDto>();
			try {
				String sql = "select cs.pick1 pick1, si1.image h1, si1.skill_key key1, si1.function f1, cs.pick2 pick2, si2.image h2, si2.skill_key key2, si2.function f2, cs.pick3 pick3, si3.image h3, si3.skill_key key3, si3.function f3, cs.win_rate w, cs.pick_rate p, cs.count c " + 
						"from c_skill_master cs, skill_info si1, skill_info si2, skill_info si3 " + 
						"where cs.pick1 = si1.name " + 
						"and cs.pick2 = si2.name " + 
						"and cs.pick3 = si3.name " + 
						"and cs.name = ? " + 
						"and cs.line = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, champion_name);
				pstmt.setString(2, champion_line);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					String pick1 = rs.getString("pick1");
					String image1 = rs.getString("h1");
					String key1 = rs.getString("key1");
					String function1 = rs.getString("f1");
					
					String pick2 = rs.getString("pick2");
					String image2 = rs.getString("h2");
					String key2 = rs.getString("key2");
					String function2 = rs.getString("f2");
					
					String pick3 = rs.getString("pick3");
					String image3 = rs.getString("h3");
					String key3 = rs.getString("key3");
					String function3 = rs.getString("f3");
					
					double winRate = rs.getDouble("w");
					double pickRate = rs.getDouble("p");
					String count = rs.getString("c");
					
					getSkillMaster.add(new SkillMasterDto(pick1, image1, key1, function1, pick2, image2, key2, function2, pick3, image3, key3, function3, winRate, pickRate, count));
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
	// 스킬순서 3
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
	// 스킬순서 6
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
	// 스킬순서9
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
	// 룬조합
		public ArrayList<RuneCombineDto> getRuneCombine(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
			ArrayList<RuneCombineDto> getRuneCombine = new ArrayList<RuneCombineDto>();
			String sql = " select cr.pick1, ri1.image h1, ri1.function f1, cr.pick2, ri2.image h2, ri2.function f2, cr.pick3, ri3.image h3, ri3.function f3, cr.pick4, ri4.image h4, ri4.function f4, cr.pick5, ri5.image h5, ri5.function f5, cr.pick6, ri6.image h6, ri6.function f6, cr.win_rate w, cr.pick_rate p "
						+ " from c_rune_combine cr, rune_info ri1 , rune_info ri2, rune_info ri3, rune_info ri4, rune_info ri5, rune_info ri6 "
						+ " where cr.pick1 = ri1.name "
						+ " and cr.pick2 = ri2.name "
						+ " and cr.pick3 = ri3.name "
						+ " and cr.pick4 = ri4.name "
						+ " and cr.pick5 = ri5.name "
						+ " and cr.pick6 = ri6.name "
						+ " and cr.name = ?"
						+ " and cr.line = ?";
			
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
					String function1 = rs.getString("f1");
					String function2 = rs.getString("f2");
					String function3 = rs.getString("f3");
					String function4 = rs.getString("f4");
					String function5 = rs.getString("f5");
					String function6 = rs.getString("f6");
					String image1 = rs.getString("h1");
					String image2 = rs.getString("h2");
					String image3 = rs.getString("h3");
					String image4 = rs.getString("h4");
					String image5 = rs.getString("h5");
					String image6 = rs.getString("h6");
					Double winRate = rs.getDouble("w");
					Double pickRate = rs.getDouble("p");
					getRuneCombine.add(new RuneCombineDto(pick1, pick2, pick3, pick4, pick5, pick6, image1, image2, image3, image4, image5, image6, function1, function2, function3, function4, function5, function6, winRate, pickRate));
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return getRuneCombine;
		}
	// 룬파편조합
		public ArrayList<RuneShardDto> getRuneShard(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
			ArrayList<RuneShardDto> getRuneShard = new ArrayList<RuneShardDto>();
			String sql = " select cr.pick1 p1, r1.image h1, r2.image h2, r3.image h3, cr.pick2 p2, cr.pick3 p3, cr.win_rate w, cr.pick_rate p "
						+ " from c_rune_shard cr, rune_info r1, rune_info r2, rune_info r3 "
						+ " where cr.name = ?"
						+ " and cr.line = ?"
						+ " and cr.pick1 = r1.name "
						+ " and cr.pick2 = r2.name "
						+ " and cr.pick3 = r3.name";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, champion_name);
				pstmt.setString(2, champion_line);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					String pick1 = rs.getString("p1");
					String pick2 = rs.getString("p2");
					String pick3 = rs.getString("p3");
					String image1 = rs.getString("h1");
					String image2 = rs.getString("h2");
					String image3 = rs.getString("h3");
					Double winRate = rs.getDouble("w");
					Double pickRate = rs.getDouble("p");
					getRuneShard.add(new RuneShardDto(pick1, pick2, pick3, image1, image2, image3, winRate, pickRate));
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			return getRuneShard;
		}
	// 기본 능력치
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
	// 기본 스킬
		public ArrayList<ChampBasicSkillDto> getChampBasicSkill(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name) {
			ArrayList<ChampBasicSkillDto> getChampBasicSkill = new ArrayList<ChampBasicSkillDto>();
			try {
				String sql = "select champ name, SHOW_NAME skillName, SKILL_KEY skillKey, FUNCTION, image from SKILL_INFO where champ = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, champion_name);
				rs = pstmt.executeQuery();

				while(rs.next()) {
					String name = rs.getString("name");
					String skillLine = rs.getString("skillName");
					String skillKey = rs.getString("skillKey");
					String function = rs.getString("function");
					String image = rs.getString("image");
					getChampBasicSkill.add(new ChampBasicSkillDto(name, skillLine, skillKey, function, image));
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
			return getChampBasicSkill;
		}
	// 기본 상세정보
		public ArrayList<ChampRoleDto> getChampRole(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name) {
			ArrayList<ChampRoleDto> getChampRole = new ArrayList<ChampRoleDto>();
			try {
				String sql = "select role1 r1, nvl(role2,'없음') r2 "
						+ "from champ_skill "
						+ "where name = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, champion_name);
				rs = pstmt.executeQuery();

				while(rs.next()) {
					String role1 = rs.getString("r1");
					String role2 = rs.getString("r2");
					getChampRole.add(new ChampRoleDto(role1, role2));
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
			return getChampRole;
		}
	// 히스토리
		public ArrayList<ChampPatchHistoryDto> getChampPatchHistory(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name) {
			ArrayList<ChampPatchHistoryDto> getChampPatchHistory = new ArrayList<ChampPatchHistoryDto>();
			try {
				String sql = "SELECT craw.name name, craw.VERSION2 version, craw.SKILL_KEY skillKey, image.SHOW_NAME skillName, craw.FUNCTION function, image.IMAGE image"
						+ "            FROM c_patch_history craw, SKILL_INFO image"
						+ "            WHERE craw.name = image.champ"
						+ "              and craw.SKILL_KEY = image.SKILL_KEY"
						+ "              and craw.name = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, champion_name);
				rs = pstmt.executeQuery();

				while(rs.next()) {
					String name = rs.getString("name");
					String version = rs.getString("version");
					String skillKey = rs.getString("skillKey");
					String skillName = rs.getString("skillName");
					String function = rs.getString("function");
					String image = rs.getString("image");
					getChampPatchHistory.add(new ChampPatchHistoryDto(name, version, skillKey, skillName, function, image));
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
			return getChampPatchHistory;
		}
}
