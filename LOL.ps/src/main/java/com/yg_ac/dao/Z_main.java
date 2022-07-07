package com.yg_ac.dao;

import java.sql.Connection;		
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.yg_ac.dto.GetSkillMasterDto;

public class Z_main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Y_DBmanager db = new Y_DBmanager();
		Champion champion = new Champion(); 
		
		ResultSet rs = null;
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String champion_name = "샤코";
		String champion_line = "서폿";
		String champion_rate = "win";
		//champion.championSummaryPsRankBefore(conn, pstmt, rs, champion_name, champion_line);
		//champion.championSummaryRankingBefore(conn, pstmt, rs, champion_name, champion_line);
		//champion.championSummaryRankingNow(conn, pstmt, rs, champion_name, champion_line);
		//champion.championSummaryWinPickBan_rate(conn, pstmt, rs, champion_name, champion_line);
		//champion.championSummaryMainRune(conn, pstmt, rs, champion_name, champion_line, champion_rate);
		//champion.championRuneInfoAssist(conn, pstmt, rs, champion_name, champion_line, champion_rate);
		//champion.championSummaryAssisRune(conn, pstmt, rs, champion_name, champion_line, champion_rate);
		//champion.selectRuneSub(conn, pstmt, rs, champion_name, champion_line, champion_rate);
		//champion.championSummarySubRune(conn, pstmt, rs, champion_name, champion_line, champion_rate);
		//champion.championSummaryItemEach1(conn, pstmt, rs, champion_name, champion_line, champion_rate);
		//champion.championSummaryItemEach2(conn, pstmt, rs, champion_name, champion_line, champion_rate);
		//champion.championSummaryItemEach3(conn, pstmt, rs, champion_name, champion_line, champion_rate);
		
		GetSkillMasterDto getSkillMaster = new GetSkillMasterDto();
	}	 
}
