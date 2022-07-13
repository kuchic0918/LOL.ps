package com.yg_ac.dao;

import java.sql.Connection;						
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Champion {
//	Scanner sc = new Scanner(System.in);
//	String champion_name;
//	int champion_line;
//	String[] champion_lineArr = {"탑","정글","미드","원딜","서폿"};
//	String champion_lineChoice;
//	int champion_pick_win;
//	String[] champion_pick_winArr = {"pick","win"};
//	String champion_pick_winChoice;

	// champion 이름 검색
//	public void championSearchName(Connection conn, PreparedStatement pstmt, ResultSet rs) {
//		String sql = "select name from champ_skill where name = ?";
//		String name = null;
//		boolean boo = true;
//		try {
//			while(boo) {
//				System.out.print("챔피언 이름 : "); 
//				champion_name = sc.nextLine();
//				System.out.println();
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, champion_name);
//				rs = pstmt.executeQuery();
//				while(rs.next()) {
//					name = rs.getString("name");
//				}
//				if( !(champion_name.equals(name) )
//						) { name = null; boo=true; } else break;
//			} } catch (SQLException e) {
//				e.printStackTrace();
//			}
//	}
	// 대중적인 빌드 , 고승률 빌드 선택 메소드
//	public void championPickWinChoice() {
//		while(true) { 
//			try {
//				System.out.print("(1 : 대중적인 빌드) (2 : 고승률 빌드)");
//				champion_pick_win = sc.nextInt();
//				if(!(champion_pick_win==1||champion_pick_win==2)) {
//					continue;
//				} else {
//					champion_pick_winChoice = champion_pick_winArr[champion_pick_win-1];
//					System.out.println();
//					if(champion_pick_win==1) {
//						System.out.println("선택한 빌드 (대중적인 빌드)");
//						break;
//					} else if(champion_pick_win==2){
//						System.out.println("선택한 빌드 (고승률 빌드)");
//						break;
//					} 
//				} 
//			}catch(InputMismatchException e) {
//				System.out.println("선택사항 없음");
//				System.out.println("종료됨.");
//				System.exit(0);
//			}
//		}
//	}
	// 라인 선택 메소드
//	public void championLineChoice() {
//		while(true) { 
//			try {
//				System.out.print("라인 선택 \n (1 : 탑) (2 : 정글) (3 : 미드) (4 : 원딜) (5 : 서폿)");
//				champion_line = sc.nextInt();
//				if(!(champion_line==1||champion_line==2||champion_line==3||champion_line==4||champion_line==5)) {
//					continue;
//				} else {
//					champion_lineChoice = champion_lineArr[champion_line-1]; 
//					System.out.println();
//					System.out.println("선택한 라인 (" + champion_lineChoice + ")");
//					break;
//				}
//			}catch (InputMismatchException e) {
//				System.out.println("선택사항 없음");
//				System.out.println("종료됨.");
//				System.exit(0);
//			}
//		}
//	}

	// 검색한 챔피언 요약 1(챔피언 초상화 및 챔프 이름)
	public ArrayList<String> championSummaryHead(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name) {
		ArrayList<String> championSummaryHead = new ArrayList<String>();
		String sql = "SELECT * FROM champ_skill cs WHERE cs.name = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				String name = rs.getString("name");
				String image_head = rs.getString("image_head");
				championSummaryHead.add(name);
				championSummaryHead.add(image_head);
				//System.out.println("챔피언 이름 : " + name + "          챔피언 이미지 : " + image_head);
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
//	// 검색한 챔피언 요약 2(챔피언 라인별 순위)-챔피언순위메소드(전)
//	public int championLineRateBefore(Connection conn, PreparedStatement pstmt, ResultSet rs) {
//		int cnt = 0;
//		String sql = "select * from c_champ_tier_before where c_champ_tier_before.line = '" + champion_lineChoice + "' and ps_score >= (select ps_score from c_champ_tier_before where name = '" + champion_name + "' and line = '" + champion_lineChoice + "') order by ps_score desc";
//		try {
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery(sql);
//			while(rs.next()) {
//				cnt++;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return cnt;
//	}
//	// 검색한 챔피언 요약 2(챔피언 라인별 순위)-챔피언순위메소드(현)
//	public int championLineRate(Connection conn, PreparedStatement pstmt, ResultSet rs) {
//		int cnt = 0;
//		String sql = "select * from c_champ_tier where c_champ_tier.line = '" + champion_lineChoice + "' and ps_score >= (select ps_score from c_champ_tier where name = '" + champion_name + "' and line = '" + champion_lineChoice + "') order by ps_score desc";
//		try {
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery(sql);
//			while(rs.next()) {
//				cnt++;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return cnt;
//	}
	// 검색한 챔피언 요약 2(카운터-5명)
//	public void championSummaryCounter(Connection conn, PreparedStatement pstmt, ResultSet rs) {
//		String str = null;
//		try {
//			String sql = "select cc.enemy enemy, cs.image_head h, cc.win_rate win_rate from c_champ_match cc, champ_skill cs where cc.name = '" + champion_name + "' and cc.line = '" + champion_lineChoice + "' and cc.enemy = cs.name order by cc.win_rate asc";
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery(sql);
//			System.out.println("카운터 : ");
//			int cnt = 0;
//			while(rs.next()) {
//				String enemy = rs.getString("enemy");
//				String h = rs.getString("h");
//				Double win_rate = rs.getDouble("win_rate");
//				str = rs.getString("enemy");
//				cnt++;
//				System.out.print(cnt + "." + enemy + "(" + h + ") - " + win_rate + "%   ");
//				if(cnt>=5) break;
//			}
//			if(str == null) System.out.print("등록된 정보가 없습니다.");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				rs.close();
//				pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	// 검색한 챔피언 요약 2(승률,픽률,밴율)
	public ArrayList<String> championSummaryWinPickBan_rate(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		ArrayList<String> championSummaryWinPickBan_rate = new ArrayList<String>();
		try {
			String sql = "select * from c_champ_tier where c_champ_tier.name = ? and line = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,champion_name);
			pstmt.setString(2,champion_line);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String win_rate = rs.getString("win_rate");
				String pick_rate = rs.getString("pick_rate");
				String ban_rate = rs.getString("ban_rate");
				String count2 = rs.getString("count2");
				championSummaryWinPickBan_rate.add(win_rate);
				championSummaryWinPickBan_rate.add(pick_rate);
				championSummaryWinPickBan_rate.add(ban_rate);
				championSummaryWinPickBan_rate.add(count2);
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
		return championSummaryWinPickBan_rate;
	}
	// 챔피언 순위 
	// 전
	public String championSummaryRankingBefore(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		String championSummaryRankingBefore = null;
		try {
			String sql = "select rnum from (select rownum rnum, b2.* from(select * from c_champ_tier_before b order by ps_score desc) b2) where name = ? and line = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String rnum = rs.getString("rnum");
				championSummaryRankingBefore = rnum;
				//System.out.println(rnum);
			}
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
		return championSummaryRankingBefore;
	}
	// 현
	public String championSummaryRankingNow(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		String championSummaryRankingNow = null;
		try {
			String sql = "select rnum from (select rownum rnum, b2.* from(select * from c_champ_tier b order by ps_score desc) b2) where name = ? and line = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			pstmt.setString(2, champion_line);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String rnum = rs.getString("rnum");
				championSummaryRankingNow = rnum;
				//System.out.println(rnum);
			}
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
		return championSummaryRankingNow;
	}
	// 검색한 챔피언 요약 2(PS 스코어,챔피언 순위)
	// 전
	public String championSummaryPsRankBefore(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		//String str = null;
		String championSummaryPsRank = null;
		try {
			String sql = "select ps_score name from c_champ_tier_before where c_champ_tier_before.name = ? and line = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,champion_name);
			pstmt.setString(2,champion_line);
			rs = pstmt.executeQuery();
			//System.out.println("\nbefore version : ");
			if(rs.next()) {
				String tierName = rs.getString("name");
				championSummaryPsRank = tierName;
				//str = rs.getString("name");
				//System.out.print("PS 스코어 : " + tierName + "   ");
				//System.out.print(championLineRateBefore(conn, pstmt, rs) + "위 ");
			}
			//if(str == null) System.out.print("등록된 정보가 없습니다.");
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
		return championSummaryPsRank;
	}
	// 현
	public String championSummaryPsRankNow(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
		String championSummaryPsRankNow = null;
		try {
			String sql = "select ps_score name from c_champ_tier where c_champ_tier.name = ? and line = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,champion_name);
			pstmt.setString(2,champion_line);
			rs = pstmt.executeQuery();
			//System.out.println("\ncurrent version : ");
			if(rs.next()) {
				String tierName = rs.getString("name");
				championSummaryPsRankNow = tierName;
				//str = rs.getString("name");
				//System.out.print("PS 스코어 : " + tierName + "   ");
				//System.out.print(championLineRate(conn, pstmt, rs) + "위 ");
			}
			//if(str == null) System.out.print("등록된 정보가 없습니다.");
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
		return championSummaryPsRankNow;
	}
	
	// 검색한 챔피언 요약 2(주로 선택하는 포지션)
	public ArrayList<String> championSummaryHighPosition(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name) {
		String str = null;
		ArrayList<String> championSummarySelectPosition = new ArrayList<String>();
		try {
			String sql = "select rownum rnum, b2.* from (select * from c_high_pick b where name = ? order by pickrate desc) b2 where rownum < 4";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,champion_name);
			rs = pstmt.executeQuery();
			//System.out.println();
			//System.out.print("주로 선택하는 포지션");
			while(rs.next()) {
				str = rs.getString("line");
				String highPickLine = rs.getString("line");
				double highPickRate = rs.getDouble("pickrate");
				championSummarySelectPosition.add(highPickLine);
				championSummarySelectPosition.add(highPickRate+"");
				
				//System.out.print(highPickLine + " - " + highPickRate + "% ");
			}
			//System.out.println();
			//if(str == null) System.out.println("등록된 정보가 없습니다.");
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
	public ArrayList<String> championSummarySelectPosition(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name) {
		ArrayList<String> championSummarySelectPosition = new ArrayList<String>();
		try {
			String sql = "select * from c_high_pick where name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, champion_name);
			rs = pstmt.executeQuery();
			//System.out.println();
			//System.out.print("주로 선택하는 포지션");
			while(rs.next()) {
				double highPickRate = rs.getDouble("pickrate");
				championSummarySelectPosition.add(highPickRate+"");
				//System.out.print(highPickLine + " - " + highPickRate + "% ");
			}
			//System.out.println();
			//if(str == null) System.out.println("등록된 정보가 없습니다.");
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
	// 검색한 챔피언 룬 
	//메인
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
	public ArrayList<String> championSummaryMainRune(Connection conn, PreparedStatement pstmt, ResultSet rs,  String champion_name, String champion_line, String champion_rate) {
		ArrayList<String> championSummaryMainRune = new ArrayList<String>();
		
		ArrayList<String> championRuneInfoMain = championRuneInfoMain(conn, pstmt, rs, champion_name, champion_line, champion_rate);
		
		ArrayList<String> selectRuneMain = selectRuneMain(conn, pstmt, rs, champion_name, champion_line, champion_rate);

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
						championSummaryMainRune.add(name);
						championSummaryMainRune.add(image);
						championSummaryMainRune.add(function);
						//System.out.println(name + " " + image);
					} else {
						championSummaryMainRune.add(name);
						championSummaryMainRune.add(image_d);
						championSummaryMainRune.add(function);
						//System.out.println(name + " " + image_d);
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
		if(championSummaryMainRune.size()==0) {
			for(int j=0; j<42; j++) {
				championSummaryMainRune.add("none.png");
				//System.out.println(championSummaryMainRune.get(j));
			}
		}
		return championSummaryMainRune;
	}
	// 보조룬
	// 포함된 보조룬
	public ArrayList<String> championRuneInfoAssist(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
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
	public ArrayList<String> championSummaryAssisRune(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
		ArrayList<String> championSummaryAssisRune = new ArrayList<String>();
		
		ArrayList<String> championRuneInfoAssist = championRuneInfoAssist(conn, pstmt, rs, champion_name, champion_line, champion_rate);
		
		ArrayList<String> selectRuneMain = selectRuneMain(conn, pstmt, rs, champion_name, champion_line, champion_rate);
		
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
						championSummaryAssisRune.add(name);
						championSummaryAssisRune.add(image);
						championSummaryAssisRune.add(function);
						//System.out.println(name + image + function);
					} else {
						championSummaryAssisRune.add(name);
						championSummaryAssisRune.add(image_d);
						championSummaryAssisRune.add(function);
						//System.out.println(name + image_d + function);
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
		if(championSummaryAssisRune.size()==0) {
			for(int j=0; j<30; j++) {
				championSummaryAssisRune.add("none2.png");
				//System.out.println(championSummaryAssisRune.get(j));
			}
		}
		return championSummaryAssisRune;
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
	public ArrayList<String> championSummarySubRune(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
		ArrayList<String> championSummarySubRune = new ArrayList<String>();
		
		ArrayList<String> selectRuneSub = selectRuneSub(conn, pstmt, rs, champion_name, champion_line, champion_rate);
		
		String[] runeSubArr = {"적응형 능력치 +9","공격속도 +10%","스킬 가속 +8","적응형 능력치 +9","방어력 +6","마법저항력 +8","체력 +15~140 (레벨에 비례)","방어력 +6","마법저항력 +8"};
		
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
						championSummarySubRune.add(name);
						championSummarySubRune.add(image_d);
						//System.out.println(name + " " + image_d);
					} else {
						championSummarySubRune.add(name);
						championSummarySubRune.add(image);
						//System.out.println(name + " " + image);
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
						championSummarySubRune.add(name);
						championSummarySubRune.add(image_d);
						//System.out.println(name + " " + image_d);
					} else {
						championSummarySubRune.add(name);
						championSummarySubRune.add(image);
						//System.out.println(name + " " + image);
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
						championSummarySubRune.add(name);
						championSummarySubRune.add(image_d);
						//System.out.println(name + " " + image_d);
					} else {
						championSummarySubRune.add(name);
						championSummarySubRune.add(image);
						//System.out.println(name + " " + image);
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
		return championSummarySubRune;
	}
		
	// 검색한 챔피언 코어 아이템
	// 1코어
	public ArrayList<String> championSummaryItemEach1(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
		ArrayList<String> championSummaryItemEach = new ArrayList<String>();
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
					championSummaryItemEach.add(name);
					championSummaryItemEach.add(image);
					championSummaryItemEach.add(function);
					//System.out.println(name + "(" + image + ") - " + function);
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
		return championSummaryItemEach;
	}	
	// 2코어
	public ArrayList<String> championSummaryItemEach2(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
		ArrayList<String> championSummaryItemEach = new ArrayList<String>();
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
					championSummaryItemEach.add(name);
					championSummaryItemEach.add(image);
					championSummaryItemEach.add(function);
					//System.out.println(name + "(" + image + ") - " + function);
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
		return championSummaryItemEach;
	}
	// 3코어
	public ArrayList<String> championSummaryItemEach3(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line, String champion_rate) {
		ArrayList<String> championSummaryItemEach = new ArrayList<String>();
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
					championSummaryItemEach.add(name);
					championSummaryItemEach.add(image);
					championSummaryItemEach.add(function);
					//System.out.println(name + "(" + image + ") - " + function);
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
		return championSummaryItemEach;
	}
	// 검색한 챔피언 스킬 11레벨 5
//	public void championSummary11(Connection conn, PreparedStatement pstmt, ResultSet rs) {
//		ArrayList<Integer> Q = new ArrayList<Integer>();
//		ArrayList<Integer> W = new ArrayList<Integer>();
//		ArrayList<Integer> E = new ArrayList<Integer>();
//		ArrayList<Integer> R = new ArrayList<Integer>();
//		ArrayList<String> H = new ArrayList<String>();
//		ArrayList<Character> K = new ArrayList<Character>();
//		String[][] QWER = new String[4][11];
//		String[] qwer = { "Q" , "W" , "E" , "R" };
//		try {
//			for(int i=1; i<=11; i++) {
//				String sql = "select si.skill_key k, si.image h \r\n" + 
//						"from c_skill_seq cs, skill_info si  \r\n" + 
//						"where cs.name = '" + champion_name + "' and cs.line = '" + champion_lineChoice + "' and cs.what_level = 11 and cs.pick" + i + " = si.name\r\n" + 
//						"and " + champion_pick_winChoice + "_rate = (select max(" + champion_pick_winChoice + "_rate) from c_skill_seq where name = '" + champion_name + "' and line = '" + champion_lineChoice + "' and what_level = 11)";
//				pstmt = conn.prepareStatement(sql);
//				rs = pstmt.executeQuery();
//				while(rs.next()) {
//					String k = rs.getString("k");
//					if(k.equals("Q")) {
//						Q.add(i);
//					} else if(k.equals("W")) {
//						W.add(i);
//					} else if(k.equals("E")) {
//						E.add(i);
//					} else if(k.equals("R")) {
//						R.add(i);
//					}
//				}
//			}
//			for(int i=0; i<4; i++) {
//				String sql = "select si.image h, si.skill_key k\r\n" + 
//						"from champ_skill cs, skill_info si\r\n" + 
//						"where cs." + qwer[i] + " = si.name \r\n" +
//						"and cs.name = '" + champion_name + "'";
//				pstmt = conn.prepareStatement(sql);
//				rs = pstmt.executeQuery();
//				while(rs.next()) {
//					String h = rs.getString("h");
//					String k = rs.getString("k");
//					char kk = k.charAt(0);
//					H.add("(" + h + ")");
//					K.add(kk);
//				}
//			}
//			for(int i=0; i<QWER.length; i++) {
//				for(int j=0; j<QWER[0].length; j++) {
//					QWER[i][j] = " ■ ";
//				}
//			}
//			String[] test = { "①","②","③","④","⑤","⑥","⑦","⑧","⑨","⑩","⑪" };
//			for(int i=0; i<Q.size(); i++) {
//				QWER[K.get(0)-81][Q.get(i)-1] = " " + test[Q.get(i)-1] + " ";
//			}
//			for(int i=0; i<W.size(); i++) {
//				QWER[K.get(1)-86][W.get(i)-1] = " " + test[W.get(i)-1] + " ";
//			}
//			for(int i=0; i<E.size(); i++) {
//				QWER[K.get(2)-67][E.get(i)-1] = " " + test[E.get(i)-1] + " ";
//			}
//			for(int i=0; i<R.size(); i++) {
//				QWER[K.get(3)-79][R.get(i)-1] = " " + test[R.get(i)-1] + " ";
//			}
//			for(int i=0; i<QWER.length; i++) {
//				System.out.print(K.get(i) + H.get(i));
//				for(int j=0; j<QWER[0].length; j++) {
//					System.out.print(QWER[i][j]);
//				}
//				System.out.println();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				rs.close();
//				pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	// 카운터 상대하기 어려움
//	public void getVsChampHard(Connection conn, PreparedStatement pstmt, ResultSet rs) {
//		String sql =  "select cc.name name, cc.enemy enemy, cs.image_head h, cc.line line, cc.count count, cc.win_rate win_rate from C_CHAMP_MATCH cc, champ_skill cs where cc.enemy = cs.name and cc.name = '" + champion_name + "' and cc.line = '" + champion_lineChoice + "' order by cc.win_rate asc";
//		try {
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			System.out.println("상대하기 어려운 챔피언 : ");
//			while(rs.next()) {
//				String name = rs.getString("name");
//				String enemy = rs.getString("enemy");
//				String h = rs.getString("h");
//				String line = rs.getString("line");
//				String count = rs.getString("count");
//				Double win_rate = rs.getDouble("win_rate");
//				System.out.println(name + " vs " + enemy + "(" + h + ") (" + line + ") " + count + "게임 " + win_rate + "%");
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				rs.close();
//				pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	// 카운터 상대하기 쉬움
//	public void getVsChampEasy(Connection conn, PreparedStatement pstmt, ResultSet rs) {
//		String sql =  "select cc.name name, cc.enemy enemy, cs.image_head h, cc.line line, cc.count count, cc.win_rate win_rate from C_CHAMP_MATCH cc, champ_skill cs where cc.enemy = cs.name and cc.name = '" + champion_name + "' and cc.line = '" + champion_lineChoice + "' order by cc.win_rate desc";
//		try {
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			System.out.println("상대하기 쉬운 챔피언 : ");
//			while(rs.next()) {
//				String name = rs.getString("name");
//				String enemy = rs.getString("enemy");
//				String h = rs.getString("h");
//				String line = rs.getString("line");
//				String count = rs.getString("count");
//				Double win_rate = rs.getDouble("win_rate");
//				System.out.println(name + " vs " + enemy + "(" + h + ") (" + line + ") " + count + "게임 " + win_rate + "%");
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				rs.close();
//				pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	// 스펠 아이템 선택
//	public void getSpell(Connection conn, PreparedStatement pstmt, ResultSet rs) {
//		String[] sSS = {"스펠", "스타트 아이템", "신발"};
//		try {
//			for(int s=0; s<3; s++) {
//				String sql =  "select cs.pick1, ii.image h1, ii.function f1, cs.pick2, ii2.image h2, ii2.function f2, cs.win_rate, cs.pick_rate, cs.count from C_SPELL_ITEM cs, item_info ii , item_info ii2 where cs.pick1 = ii.name and nvl(cs.pick2, '없음') = ii2.name and cs.category = '" + sSS[s] + "' and cs.name = '" + champion_name + "' and cs.line = '" + champion_lineChoice + "' order by cs.pick_rate desc";
//				pstmt = conn.prepareStatement(sql);
//				rs = pstmt.executeQuery();
//				System.out.println(sSS[s] + " : ");
//				while(rs.next()) {
//					String pick1 = rs.getString("pick1");
//					String h1 = rs.getString("h1");
//					String f1 = rs.getString("f1");
//					String pick2 = rs.getString("pick2");
//					String h2 = rs.getString("h2");
//					String f2 = rs.getString("f2");
//					Double win_rate = rs.getDouble("win_rate");
//					Double pick_rate = rs.getDouble("pick_rate");
//					String count = rs.getString("count");
//					System.out.println(pick1 + "(" + h1 + ") - " + f1 + " / " + pick2 + "(" + h2 + ") - " + f2 + " / 승률 : " + win_rate + " / 픽률 : " + pick_rate + " / 카운트 수 : " + count);
//				} 
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				rs.close();
//				pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	// 코어템 통계
//	public void getCoreEach(Connection conn,PreparedStatement pstmt, ResultSet rs) {
//		try {
//			for(int i=1; i<=3; i++) {
//				String sql =  "select cc.pick, ii.image h, ii.function f, cc.win_rate w, cc.pick_rate p, cc.count c from c_core_each cc, item_info ii where cc.pick = ii.name and cc.rank = '" + i + "코어' and cc.name = '" + champion_name + "' and cc.line = '" + champion_lineChoice + "' order by cc.pick_rate desc";
//				pstmt = conn.prepareStatement(sql);
//				rs = pstmt.executeQuery();
//				System.out.println(i + "코어 : ");
//				while(rs.next()) {
//					String pick = rs.getString("pick");
//					String image = rs.getString("h");
//					String function = rs.getString("f");
//					Double win_rate = rs.getDouble("w");
//					Double pick_rate = rs.getDouble("p");
//					String count = rs.getString("c");
//					System.out.println(pick +  "(" + image + ") - " + function + " / 승률 : " + win_rate + " / 픽률 : " + pick_rate + " / 카운트 수 : " + count);
//				} 
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				rs.close();
//				pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	// 코어템 조합 통계
//	public void getCoreCombine(Connection conn, PreparedStatement pstmt, ResultSet rs) {
//		try {
//			for(int i=2; i<=4; i++) {
//				String sql =  "select cc.pick1 p1, cc.pick2 p2, cc.pick3 p3, cc.pick4 p4, i1.function f1, i2.function f2, i3.function f3, i4.function f4, i1.image h1, i2.image h2, i3.image h3, i4.image h4, cc.win_rate w, cc.pick_rate p, cc.count c from c_core_combine cc, item_info i1, item_info i2, item_info i3, item_info i4 where cc.pick1 = i1.name and cc.pick2 = i2.name and nvl(cc.pick3,'없음') = i3.name and nvl(cc.pick4,'없음') = i4.name and cc.name = '" + champion_name + "' and cc.line = '" + champion_lineChoice + "' and cc.rank = '" + i + "코어 조합'";
//				pstmt = conn.prepareStatement(sql);
//				rs = pstmt.executeQuery();
//				System.out.println(i + "코어조합 : ");
//				while(rs.next()) {
//					String pick1 = rs.getString("p1");
//					String h1 = rs.getString("h1");
//					String f1 = rs.getString("f1");
//					String pick2 = rs.getString("p2");
//					String h2 = rs.getString("h2");
//					String f2 = rs.getString("f2");
//					String pick3 = rs.getString("p3");
//					String h3 = rs.getString("h3");
//					String f3 = rs.getString("f3");
//					String pick4 = rs.getString("p4");
//					String h4 = rs.getString("h4");
//					String f4 = rs.getString("f4");
//					Double win_rate = rs.getDouble("w");
//					Double pick_rate = rs.getDouble("p");
//					String count = rs.getString("c");
//					System.out.println(pick1 + "(" + h1 + ") - " + f1 + " / " + pick2 + "(" + h2 + ") - " + f2 + " / " + pick3 + "(" + h3 + ") - " + f3 + " / " + pick4 + "(" + h4 + ") - " + f4 + " / 승률 : " + win_rate + " / 픽률 : " + pick_rate + " / 카운트 수 : " + count);
//				} 
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				rs.close();
//				pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	// 스킬 - 레벨별 마스터 순서 ( 3, 6, 11 )
//	public void getSkillSeq(Connection conn, PreparedStatement pstmt, ResultSet rs, String champion_name, String champion_line) {
//		int[] what_level = {3, 6, 11};
//		try {
//			for(int i=0; i<3; i++) {
//				String sql = "select css.pick1, si1.image h1, si1.function f1, css.pick2, si2.image h2, si2.function f2, css.pick3, si3.image h3, si3.function f3, css.pick4, si4.image h4, si4.function f4," + 
//						" css.pick5, si5.image h5, si5.function f5, css.pick6, si6.image h6, si6.function f6, css.pick7, si7.image h7, si7.function f7, css.pick8, si8.image h8, si8.function f8, css.pick9, si9.image h9, " + 
//						" si9.function f9, css.pick10, si10.image h10, si10.function f10, css.pick11, si11.image h11, si11.function f11," + 
//						" css.win_rate w, css.pick_rate p, css.count c " + 
//						" from c_skill_seq css, skill_info si1, skill_info si2, skill_info si3, skill_info si4, skill_info si5, skill_info si6, " + 
//						" skill_info si7, skill_info si8, skill_info si9, skill_info si10, skill_info si11" + 
//						" where css.pick1 = si1.name" + 
//						" and css.pick2 = si2.name" + 
//						" and css.pick3 = si3.name" + 
//						" and nvl(css.pick4, '없음') = si4.name" + 
//						" and nvl(css.pick5, '없음') = si5.name" + 
//						" and nvl(css.pick6, '없음') = si6.name" + 
//						" and nvl(css.pick7, '없음') = si7.name" + 
//						" and nvl(css.pick8, '없음') = si8.name" + 
//						" and nvl(css.pick9, '없음') = si9.name" + 
//						" and nvl(css.pick10, '없음') = si10.name" + 
//						" and nvl(css.pick11, '없음') = si11.name" + 
//						" and css.name = ?" + 
//						" and css.line = ?" + 
//						" and css.what_level = " + what_level[i] + "" + 
//						" order by pick_rate desc";
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, champion_name);
//				pstmt.setString(2, champion_line);
//				rs = pstmt.executeQuery();
//				System.out.println(what_level[i] + "레벨별 마스터 순서 : ");
//				while(rs.next()) {
//					String p1 = rs.getString("pick1");
//					String p2 = rs.getString("pick2");
//					String p3 = rs.getString("pick3");
//					String p4 = rs.getString("pick4");
//					String p5 = rs.getString("pick5");
//					String p6 = rs.getString("pick6");
//					String p7 = rs.getString("pick7");
//					String p8 = rs.getString("pick8");
//					String p9 = rs.getString("pick9");
//					String p10 = rs.getString("pick10");
//					String p11 = rs.getString("pick11");
//					String h1 = "(" + rs.getString("h1") + ") : ";
//					String h2 = "(" + rs.getString("h2") + ") : ";
//					String h3 = "(" + rs.getString("h3") + ") : ";
//					String h4 = "(" + rs.getString("h4") + ") : ";
//					String h5 = "(" + rs.getString("h5") + ") : ";
//					String h6 = "(" + rs.getString("h6") + ") : ";
//					String h7 = "(" + rs.getString("h7") + ") : ";
//					String h8 = "(" + rs.getString("h8") + ") : ";
//					String h9 = "(" + rs.getString("h9") + ") : ";
//					String h10 = "(" + rs.getString("h10") + ") : ";
//					String h11 = "(" + rs.getString("h11") + ") : ";
//					String f1 = rs.getString("f1") + " / ";
//					String f2 = rs.getString("f2") + " / ";
//					String f3 = rs.getString("f3") + " / ";
//					String f4 = rs.getString("f4") + " / ";
//					String f5 = rs.getString("f5") + " / ";
//					String f6 = rs.getString("f6") + " / ";
//					String f7 = rs.getString("f7") + " / ";
//					String f8 = rs.getString("f8") + " / ";
//					String f9 = rs.getString("f9") + " / ";
//					String f10 = rs.getString("f10") + " / ";
//					String f11 = rs.getString("f11") + " / ";
//					Double w = rs.getDouble("w");
//					Double p = rs.getDouble("p");
//					String c = rs.getString("c");
//					if(p4==null) {
//						System.out.println("******************** ********************\n" +
//								p1 + h1 + f1 + "\n" + p2 + h2 + f2 + "\n" + p3 + h3 + f3 + "\n" +
//								"승률 : " + w + " / 픽률 : " + p + " / 카운트 수 : " + c);
//					} else if(p7==null) {
//						System.out.println("******************** ******************** ********************\n" +
//								p1 + h1 + f1 + "\n" + p2 + h2 + f2 + "\n" + p3 + h3 + f3 + "\n" +
//								p4 + h4 + f4 + "\n" + p5 + h5 + f5 + "\n" + p6 + h6 + f6 + "\n" +
//								"승률 : " + w + " / 픽률 : " + p + " / 카운트 수 : " + c);
//					} else {
//						System.out.println("******************** ******************** ******************** ********************\n" +
//								p1 + h1 + f1 + "\n" + p2 + h2 + f2 + "\n" + p3 + h3 + f3 + "\n" +
//								p4 + h4 + f4 + "\n" + p5 + h5 + f5 + "\n" + p6 + h6 + f6 + "\n" +
//								p7 + h7 + f7 + "\n" + p8 + h8 + f8 + "\n" + p9 + h9 + f9 + "\n" +
//								p10 + h10 + f10 + p11 + h11 + f11 + "\n" + 
//								"승률 : " + w + " / 픽률 : " + p + " / 카운트 수 : " + c);
//					}
//					System.out.println();
//				}
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				rs.close();
//				pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	// 룬 - 조합별 통계
//	public void getRuneCombine(Connection conn, PreparedStatement pstmt, ResultSet rs) {
//		try {
//			String sql = "select cr.pick1, ri1.image h1, ri1.function f1, cr.pick2, ri2.image h2, ri2.function f2, cr.pick3, ri3.image h3, ri3.function f3, cr.pick4, ri4.image h4, ri4.function f4, cr.pick5, ri5.image h5, ri5.function f5, cr.pick6, ri6.image h6, ri6.function f6, cr.win_rate w, cr.pick_rate p from c_rune_combine cr, rune_info ri1 , rune_info ri2, rune_info ri3, rune_info ri4, rune_info ri5, rune_info ri6 where cr.pick1 = ri1.name and cr.pick2 = ri2.name and cr.pick3 = ri3.name and cr.pick4 = ri4.name and cr.pick5 = ri5.name and cr.pick6 = ri6.name and cr.name = '" + champion_name + "' and cr.line = '" + champion_lineChoice + "'";
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			System.out.println("조합별 통계");
//			while(rs.next()) {
//				String pick1 = rs.getString("pick1");
//				String pick2 = rs.getString("pick2");
//				String pick3 = rs.getString("pick3");
//				String pick4 = rs.getString("pick4");
//				String pick5 = rs.getString("pick5");
//				String pick6 = rs.getString("pick6");
//				String f1 = rs.getString("f1");
//				String f2 = rs.getString("f2");
//				String f3 = rs.getString("f3");
//				String f4 = rs.getString("f4");
//				String f5 = rs.getString("f5");
//				String f6 = rs.getString("f6");
//				String h1 = rs.getString("h1");
//				String h2 = rs.getString("h2");
//				String h3 = rs.getString("h3");
//				String h4 = rs.getString("h4");
//				String h5 = rs.getString("h5");
//				String h6 = rs.getString("h6");
//				Double win_rate = rs.getDouble("w");
//				Double pick_rate = rs.getDouble("p");
//				System.out.println(pick1 + "(" + h1 + ") - " + f1 + " ___/___ " + pick2 +  "(" + h2 + ") - " + f2 + " ___/___ " + pick3 +  "(" + h3 + ") - " + f3 + " ___/___ " + pick4 +  "(" + h4 + ") - " + f4 + " ___/___ " + pick5 +  "(" + h5 + ") - " + f5 + " ___/___ " + pick6 +  "(" + h6 + ") - " + f6 + " ___/___ 승률 : " + win_rate + " ___/___ 픽률 : " + pick_rate);
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				rs.close();
//				pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	// 룬 - 파편 조합 통계
//	public void getRuneShard(Connection conn, PreparedStatement pstmt, ResultSet rs) {
//		try {
//			String sql = "select cr.pick1 p1, r1.image h1, r2.image h2, r3.image h3, cr.pick2 p2, cr.pick3 p3, cr.win_rate w, cr.pick_rate p from c_rune_shard cr, rune_info r1, rune_info r2, rune_info r3 where cr.name = '" + champion_name + "' and cr.line = '" + champion_lineChoice + "' and cr.pick1 = r1.name and cr.pick2 = r2.name and cr.pick3 = r3.name";
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			System.out.println("파편 조합 통계");
//			while(rs.next()) {
//				String pick1 = rs.getString("p1");
//				String pick2 = rs.getString("p2");
//				String pick3 = rs.getString("p3");
//				String h1 = rs.getString("h1");
//				String h2 = rs.getString("h2");
//				String h3 = rs.getString("h3");
//				Double win_rate = rs.getDouble("w");
//				Double pick_rate = rs.getDouble("p");
//				System.out.println("공격(" + h1 + ") : - " + pick1 + " ___/___ " + "유연(" + h2 + ") : - " + pick2 + " ___/___ " + "방어(" + h3 + ") : - " + pick3 + "___/___ 승률 : " + win_rate + " ___/___ 픽률 : " + pick_rate);
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				rs.close();
//				pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	// 기본정보 능력치
//	public void basicStats(Connection conn, PreparedStatement pstmt, ResultSet rs) {
//		String sql = "select stat s, stat_start ss, stat_final sf, stat_rank sr from c_basicstat where name = ?";
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, champion_name);
//			rs = pstmt.executeQuery();
//			System.out.println("              " + "[기본능력치(+레벨 당 상승)] " + "[최종 수치] " + "[챔피언 순위]" );
//			while(rs.next()) {
//				String stat = rs.getString("s") + "\t\t";
//				String stat_start = "[" + rs.getString("ss") + "] ";
//				String stat_final = "[" + rs.getString("sf") + "] ";
//				String stat_rank = "[" + rs.getString("sr") + "]";
//				System.out.println(stat + stat_start + stat_final + stat_rank);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				rs.close();
//				pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	// 스킬
//	public void basicSkill1(Connection conn, PreparedStatement pstmt, ResultSet rs) {
//		String sql = "select cs.p, si1.image h1, cs.q, si2.image h2, cs.w, si3.image h3, cs.e, si4.image h4, cs.r, si5.image h5 \r\n" + 
//				"from champ_skill cs\r\n" + 
//				", skill_info si1\r\n" + 
//				", skill_info si2\r\n" + 
//				", skill_info si3\r\n" + 
//				", skill_info si4\r\n" + 
//				", skill_info si5\r\n" + 
//				"where cs.name = ? \r\n" + 
//				"and cs.p = si1.name \r\n" + 
//				"and cs.q = si2.name \r\n" + 
//				"and cs.w = si3.name \r\n" + 
//				"and cs.e = si4.name \r\n" + 
//				"and cs.r = si5.name";
//		System.out.println();	// 줄바꿈
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, champion_name);
//			rs = pstmt.executeQuery();
//			System.out.println("스킬" );
//			while(rs.next()) {
//				String p = rs.getString("p") + " P";
//				String q = rs.getString("q") + " Q";
//				String w = rs.getString("w") + " W";
//				String e = rs.getString("e") + " E";
//				String r = rs.getString("r") + " R";
//				String h1 = "(" + rs.getString("h1") + ") ";
//				String h2 = "(" + rs.getString("h2") + ") ";
//				String h3 = "(" + rs.getString("h3") + ") ";
//				String h4 = "(" + rs.getString("h4") + ") ";
//				String h5 = "(" + rs.getString("h5") + ") ";
//				System.out.println(p + h1 + q + h2 + w + h3 + e + h4 + r + h5);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				rs.close();
//				pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	public void basicRole(Connection conn, PreparedStatement pstmt, ResultSet rs) {
//		String sql = "select role1 r1, role2 r2 from champ_skill where name = ?";
//
//		System.out.println(); 	// 줄바꿈
//
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, champion_name);
//			rs = pstmt.executeQuery();
//			System.out.println("기본정보" );
//			while(rs.next()) {
//				String r1 = "역할군 : " + rs.getString("r1");
//				String r2 = rs.getString("r2");
//				if(r2 == null) {
//					r2 = " ";
//				} 
//				System.out.println(r1 + " " + r2);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				rs.close();
//				pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	//
//	public void basicSkill2(Connection conn, PreparedStatement pstmt, ResultSet rs) {
//		String[] qwer = { "q", "w", "e", "r" };
//		System.out.println("");
//		System.out.println("스킬 상세 설명");
//		for(int i=0; i<4; i++) {
//			String sql = "select cs." + qwer[i] + " name, si.image h, si.function f \r\n" + 
//					"from champ_skill cs\r\n" + 
//					", skill_info si\r\n" + 
//					"where cs.name = ? \r\n" + 
//					"and cs." + qwer[i] + " = si.name";
//			try {
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, champion_name);
//				rs = pstmt.executeQuery();
//				while(rs.next()) {
//					String name = rs.getString("name");
//					String h = "(" + rs.getString("h") + ")";
//					String f = " " + rs.getString("F");
//					System.out.println(name + h + f);
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					rs.close();
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//	// image 메소드
//	public String image(String k, Connection conn, PreparedStatement pstmt, ResultSet rs) {
//		String image = null;
//		String sql = "select si.image i \r\n" + 
//				"from skill_info si, champ_skill cs \r\n" + 
//				"where si.name = cs." + k + " \r\n" + 
//				"and cs.name = '" + champion_name + "'";
//		try {
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				image = rs.getString("i");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				rs.close();
//				pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return image;
//	}
//
//	// 패치 히스토리
//	public void patchHistory(Connection conn, PreparedStatement pstmt, ResultSet rs) {
//		ArrayList<String> pqwer = new ArrayList<String>();
//		// version
//		String sql = "select version2 v, skill_key k, function f \r\n" + 
//				"from c_patch_history \r\n" + 
//				"where name = ?";
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, champion_name);
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				String v = rs.getString("v");
//				String k = rs.getString("k");
//				String f = rs.getString("f");
//				System.out.println(v + " " + image(k, conn, pstmt, rs) + " " + f);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				rs.close();
//				pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
}
