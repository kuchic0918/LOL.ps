<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.net.URLDecoder" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.yg_ac.dao.*" %>
<%@ page import="com.yg_ac.dto.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<%
	//JDBC
	Y_DBmanager db = new Y_DBmanager();
	Champion champion = new Champion();
	Connection conn = db.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//sql용 변수
	// String champion_name = "갱플랭크";
	// String champion_line = "탑";
	String champion_name = request.getParameter("name");
	String champion_line = request.getParameter("line");
	String champion_rate = "pick";
	String champion_rate2 = "win";
// 	session.getAttribute("memberInfo");
// 	System.out.println(session.getAttribute("memberInfo"));
	//요약
	
	//챔피언 해드 이미지, 이름
	String championName = champion.championSummaryHead(conn, pstmt, rs, champion_name).get(0);
	String championImg = champion.championSummaryHead(conn, pstmt, rs, champion_name).get(1);
	
	//높은 포지션 (라인 이름, 퍼센트)
	String[] championLineHighNamePer = new String[6];
	int clhnp = 0;
	for(String str : champion.championSummaryHighPosition(conn, pstmt, rs, champion_name) ) {
		championLineHighNamePer[clhnp] = str;
		clhnp++;
	}
	
	//선택한 포지션 (퍼센트)
	String[] championLineSelectPer = new String[5];
	int clsp = 0;
	for(String str : champion.championSummarySelectPosition(conn, pstmt, rs, champion_name) ) {
		championLineSelectPer[clsp] = str;
		clsp++;
	}
	
	//ps스코어 전
	String championSummaryPsRankBefore = champion.championSummaryPsRankBefore(conn, pstmt, rs, champion_name, champion_line);
	if(championSummaryPsRankBefore==null) {
		championSummaryPsRankBefore = "no score";
	}
	
	//ps스코어 현
	String championSummaryPsRankNow = champion.championSummaryPsRankNow(conn, pstmt, rs, champion_name, champion_line);
	if(championSummaryPsRankNow==null) {
		championSummaryPsRankNow = "no score";
	}
	
	//챔피언 순위 전
	String championSummaryRankingBefore = champion.championSummaryRankingBefore(conn, pstmt, rs, champion_name, champion_line);
	if(championSummaryRankingBefore==null) {
		championSummaryRankingBefore = "no rank";
	} else {
		championSummaryRankingBefore = championSummaryRankingBefore + "등";
	}
	
	//챔피언 순위 현
	String championSummaryRankingNow = champion.championSummaryRankingNow(conn, pstmt, rs, champion_name, champion_line);
	if(championSummaryRankingNow==null) {
		championSummaryRankingNow = "no rank";
	} else {
		championSummaryRankingNow = championSummaryRankingNow + "등";
	}
	
	// 승률 픽률 밴율 카운트
	String championSummaryWin_rate = " ";
	String championSummaryPick_rate = " ";
	String championSummaryBan_rate = " ";
	String championSummaryCount = " ";
	if(champion.championSummaryWinPickBan_rate(conn, pstmt, rs, champion_name, champion_line).size()!=0) {
		championSummaryWin_rate = champion.championSummaryWinPickBan_rate(conn, pstmt, rs, champion_name, champion_line).get(0);
		championSummaryPick_rate = champion.championSummaryWinPickBan_rate(conn, pstmt, rs, champion_name, champion_line).get(1);
		championSummaryBan_rate = champion.championSummaryWinPickBan_rate(conn, pstmt, rs, champion_name, champion_line).get(2);
		championSummaryCount = champion.championSummaryWinPickBan_rate(conn, pstmt, rs, champion_name, champion_line).get(3);	
	} else {
		championSummaryWin_rate = "0";
		championSummaryPick_rate = "0";
		championSummaryBan_rate = "0";
		championSummaryCount = "0"; 
	}
	
	// 챔피언 메인룬
	String[] championSummaryMainRune = new String[42];
	int csmr = 0;
	for(String str : champion.championSummaryMainRune(conn, pstmt, rs, champion_name, champion_line, champion_rate) ) {
		championSummaryMainRune[csmr] = str;
		csmr++;
	}
	String[] championSummaryMainRune2 = new String[42];
	int csmr2 = 0;
	for(String str : champion.championSummaryMainRune(conn, pstmt, rs, champion_name, champion_line, champion_rate2) ) {
		championSummaryMainRune2[csmr2] = str;
		csmr2++;
	}
	
	// 챔피언 보조룬
	String[] championSummaryAssisRune = new String[35];
	int csar = 0;
	if(champion.championSummaryAssisRune(conn, pstmt, rs, champion_name, champion_line, champion_rate).size()!=0) {
		for(String str : champion.championSummaryAssisRune(conn, pstmt, rs, champion_name, champion_line, champion_rate) ) {
			championSummaryAssisRune[csar] = str;
			csar++;
		}
	}
	String[] championSummaryAssisRune2 = new String[35];
	int csar2 = 0;
	if(champion.championSummaryAssisRune(conn, pstmt, rs, champion_name, champion_line, champion_rate2).size()!=0) {
		for(String str : champion.championSummaryAssisRune(conn, pstmt, rs, champion_name, champion_line, champion_rate2) ) {
			championSummaryAssisRune2[csar2] = str;
			csar2++;
		}
	}
	
	// 챔피언 서브룬
	String[] championSummarySubRune = new String[18];
	int cssr = 0;
	if(champion.championSummarySubRune(conn, pstmt, rs, champion_name, champion_line, champion_rate).size()!=0) {
		for(String str : champion.championSummarySubRune(conn, pstmt, rs, champion_name, champion_line, champion_rate) ) {
			championSummarySubRune[cssr] = str;
			cssr++;
		}	
	}
	String[] championSummarySubRune2 = new String[18];
	int cssr2 = 0;
	if(champion.championSummarySubRune(conn, pstmt, rs, champion_name, champion_line, champion_rate2).size()!=0) {
		for(String str : champion.championSummarySubRune(conn, pstmt, rs, champion_name, champion_line, champion_rate2) ) {
			championSummarySubRune2[cssr2] = str;
			cssr2++;
		}	
	}
	
	// 챔피언 1~3 코어 아이템
	String[] championSummaryItemEach1 = new String[9];
	int csie = 0;
	if(champion.championSummaryItemEach1(conn, pstmt, rs, champion_name, champion_line, champion_rate).size()!=0) {
		for(String str : champion.championSummaryItemEach1(conn, pstmt, rs, champion_name, champion_line, champion_rate) ) {
			championSummaryItemEach1[csie] = str;
			csie++;
		}	
	}
	String[] championSummaryItemEach12 = new String[9];
	int csie2 = 0;
	if(champion.championSummaryItemEach1(conn, pstmt, rs, champion_name, champion_line, champion_rate2).size()!=0) {
		for(String str : champion.championSummaryItemEach1(conn, pstmt, rs, champion_name, champion_line, champion_rate2) ) {
			championSummaryItemEach12[csie2] = str;
			csie2++;
		}	
	}
	
	String[] championSummaryItemEach2 = new String[9];
	csie = 0;
	if(champion.championSummaryItemEach2(conn, pstmt, rs, champion_name, champion_line, champion_rate).size()!=0) {
		for(String str : champion.championSummaryItemEach2(conn, pstmt, rs, champion_name, champion_line, champion_rate) ) {
			championSummaryItemEach2[csie] = str;
			csie++;
		}	
	}
	String[] championSummaryItemEach22 = new String[9];
	csie2 = 0;
	if(champion.championSummaryItemEach2(conn, pstmt, rs, champion_name, champion_line, champion_rate2).size()!=0) {
		for(String str : champion.championSummaryItemEach2(conn, pstmt, rs, champion_name, champion_line, champion_rate2) ) {
			championSummaryItemEach22[csie2] = str;
			csie2++;
		}	
	}
	
	String[] championSummaryItemEach3 = new String[9];
	csie = 0;
	if(champion.championSummaryItemEach3(conn, pstmt, rs, champion_name, champion_line, champion_rate).size()!=0) {
		for(String str : champion.championSummaryItemEach3(conn, pstmt, rs, champion_name, champion_line, champion_rate) ) {
			championSummaryItemEach3[csie] = str;
			csie++;
		}
	}
	String[] championSummaryItemEach32 = new String[9];
	csie2 = 0;
	if(champion.championSummaryItemEach3(conn, pstmt, rs, champion_name, champion_line, champion_rate2).size()!=0) {
		for(String str : champion.championSummaryItemEach3(conn, pstmt, rs, champion_name, champion_line, champion_rate2) ) {
			championSummaryItemEach32[csie2] = str;
			csie2++;
		}
	}
	// 챔피언 스킬 마스터 추천 순서
	StatisticsDao statisticsDao = new StatisticsDao();
	ArrayList<GetSkillMasterDto> gsmlist = statisticsDao.getSkillMaster(conn, pstmt, rs, champion_name, champion_line, champion_rate);
	ArrayList<GetSkillMasterDto> gsmlist2 = statisticsDao.getSkillMaster(conn, pstmt, rs, champion_name, champion_line, champion_rate2);
	// 챔피언 추천 스펠
	ArrayList<RecommendedSpellsDto> reslist = statisticsDao.recommendedSpells(conn, pstmt, rs, champion_name, champion_line, champion_rate);
	ArrayList<RecommendedSpellsDto> reslist2 = statisticsDao.recommendedSpells(conn, pstmt, rs, champion_name, champion_line, champion_rate2);
	// 챔피언 시작 아이템
	ArrayList<RecommendedSpellsDto> stilist = statisticsDao.startItem(conn, pstmt, rs, champion_name, champion_line, champion_rate);
	ArrayList<RecommendedSpellsDto> stilist2 = statisticsDao.startItem(conn, pstmt, rs, champion_name, champion_line, champion_rate2);
	// 신발
	ArrayList<RecommendedSpellsDto> shoeslist = statisticsDao.shoes(conn, pstmt, rs, champion_name, champion_line, champion_rate);
	ArrayList<RecommendedSpellsDto> shoeslist2 = statisticsDao.shoes(conn, pstmt, rs, champion_name, champion_line, champion_rate2);
	// 챔피언 qwer 이미지
	ArrayList<ChampionQWERDto> csilist = statisticsDao.championSkillImageQWER(conn, pstmt, rs, champion_name);
	// 검색한 챔피언 스킬 11 (순서)
	ArrayList<ChampionSummary11Dto> csylist = statisticsDao.championSummary11(conn, pstmt, rs, champion_name, champion_line, champion_rate);
	ArrayList<ChampionSummary11Dto> csylist2 = statisticsDao.championSummary11(conn, pstmt, rs, champion_name, champion_line, champion_rate2);
	
	// 요약
	
	
	//statistics
	
	//상대하기 쉬움, 어려움
	ChampMatchListDao champMatchListDao = new ChampMatchListDao();
	ArrayList<ChampMatchListDto> matchHard = champMatchListDao.getChampMatchListHard(conn, pstmt, rs, champion_name, champion_line);
	ArrayList<ChampMatchListDto> matchEasy = champMatchListDao.getChampMatchListEasy(conn, pstmt, rs, champion_name, champion_line);
	
	//스펠, 스타트아이템, 신발
	ChampStartItemDao champStartItemDao = new ChampStartItemDao();
	ArrayList<ChampStartItemDto> selectSpell = champStartItemDao.getSpell(conn, pstmt, rs, champion_name, champion_line);
	ArrayList<ChampStartItemDto> selectStartItem = champStartItemDao.getStartItem(conn, pstmt, rs, champion_name, champion_line);
	ArrayList<ChampStartItemDto> selectShoes = champStartItemDao.getShoes(conn, pstmt, rs, champion_name, champion_line);
	
	//1,2,3 코어
	CoreEachDao coreEachDao = new CoreEachDao();
	ArrayList<CoreEachDto> core1 = coreEachDao.getCore1(conn, pstmt, rs, champion_name, champion_line);
	ArrayList<CoreEachDto> core2 = coreEachDao.getCore2(conn, pstmt, rs, champion_name, champion_line);
	ArrayList<CoreEachDto> core3 = coreEachDao.getCore3(conn, pstmt, rs, champion_name, champion_line);
	
	//2,3,4 코어조합
	CoreCombineDao CoreCombineDao = new CoreCombineDao();
	ArrayList<CoreCombineDto> coreCombine2 = CoreCombineDao.get2CoreCombine(conn, pstmt, rs, champion_name, champion_line);
	ArrayList<CoreCombineDto> coreCombine3 = CoreCombineDao.get3CoreCombine(conn, pstmt, rs, champion_name, champion_line);
	ArrayList<CoreCombineDto> coreCombine4 = CoreCombineDao.get4CoreCombine(conn, pstmt, rs, champion_name, champion_line);
	
	//스킬 마스터 순서
	SkillMasterDao SkillMasterDao = new SkillMasterDao();
	ArrayList<SkillMasterDto> skillMaster = SkillMasterDao.getSkillMaster(conn, pstmt, rs, champion_name, champion_line);
	
	//스킬 순서
	SkillSeqDao SkillSeqDao = new SkillSeqDao();
	ArrayList<SkillSeqDto> skillSeq3 = SkillSeqDao.getSkillSeq3(conn, pstmt, rs, champion_name, champion_line);
	ArrayList<SkillSeqDto> skillSeq6 = SkillSeqDao.getSkillSeq6(conn, pstmt, rs, champion_name, champion_line);
	ArrayList<SkillSeqDto> skillSeq11 = SkillSeqDao.getSkillSeq11(conn, pstmt, rs, champion_name, champion_line);
	
	//룬조합
	RuneCombineDao RuneCombineDao = new RuneCombineDao();
	ArrayList<RuneCombineDto> runeCombine = RuneCombineDao.getRuneCombine(conn, pstmt, rs, champion_name, champion_line);
	
	//룬파편조합
	RuneShardDao RuneShardDao = new RuneShardDao();
	ArrayList<RuneShardDto> runeShard = RuneShardDao.getRuneShard(conn, pstmt, rs, champion_name, champion_line);
	
	//statistics
	
%>
<html class="statistics-main-html" lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>statistics</title>
    <link rel="stylesheet" href="Css/all.css">
    <style>
		*{box-sizing: border-box;}
		.display-none {
			display: none;
		}
    </style>
    <script src="Js/jquery-3.6.0.min.js"></script>
    <script>
   		 /* 3레벨, 6레벨, 11레벨 */
   		 $(document).ready(function(){
	   		var write = "";
	   		var champName = "<%=champion_name%>";
	   		var champLine = "<%=champion_line%>";
		   	$(document).on("click","#seq3",function(){
		   		write = `<ul class="statistics-spell-list2 statistics-display-block" id="skillSeqList"></ul>`;
				$("#skillSeq").html(write);
		   		$('.statistics-what-level-container').find('.statistics-what-level-active').removeClass('statistics-what-level-active');
				$('#seq3').addClass('statistics-what-level-active');
				$.ajax({
					type:"get",
					url:"../SkillSeqServlet",
					data:{"name":champName,"line":champLine,"select":"3"},
					datatype:"json",
					success:function(data){
						for(var i = 0;i<data.length;i++){
							write = `<li class="statistics-list-items statistics-border-bottom">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/skill/\${data[i].image1}" alt="img"/>
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick1}</b><br/>
													<br />\${data[i].function1}
												</span>
											</span> 
											<span class='tooltip'> 
												<img src="Images/skill/\${data[i].image2}" alt="img"/>
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick2}</b><br/>
													<br />\${data[i].function2}
												</span>
											</span> 
											<span class='tooltip'> 
												<img src="Images/skill/\${data[i].image3}" alt="img"/>
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick3}</b><br/>
													<br />\${data[i].function3}
												</span>
											</span> 
										</div>
										<div class="statistics-spell-percent" style="width: 40%;">
											<span style="width: 23.3%;">\${data[i].winrate}</span> 
											<span style="width: 23.3%;">\${data[i].pickrate}</span> 
											<span style="width: 23.3%;">\${data[i].count}</span>
										</div>
									</li>`;
							$("#skillSeqList").append(write);
						}
					},
					error:function(r,s,e){
						alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e);
					}
				});
		   	});
		    $(document).on("click","#seq6",function(){
		    	write = `<ul class="statistics-spell-list2 statistics-display-block" id="skillSeqList"></ul>`;
				$("#skillSeq").html(write);
		   		$('.statistics-what-level-container').find('.statistics-what-level-active').removeClass('statistics-what-level-active');
				$('#seq6').addClass('statistics-what-level-active');
				$.ajax({
					type:"get",
					url:"../SkillSeqServlet",
					data:{"name":champName,"line":champLine,"select":"6"},
					datatype:"json",
					success:function(data){
						for(var i = 0;i<data.length;i++){
							write = `<li class="statistics-list-items statistics-border-bottom">
								<div class="statistics-spell">
									<span class='tooltip'> 
										<img src="Images/skill/\${data[i].image1}" alt="img"/>
										<span class='tooltiptext tooltip-right'> 
											<b style='color: #ffc107;'>\${data[i].pick1}</b><br/>
											<br />\${data[i].function1}
										</span>
									</span> 
									<span class='tooltip'> 
										<img src="Images/skill/\${data[i].image2}" alt="img"/>
										<span class='tooltiptext tooltip-right'> 
											<b style='color: #ffc107;'>\${data[i].pick2}</b><br/>
											<br />\${data[i].function2}
										</span>
									</span> 
									<span class='tooltip'> 
										<img src="Images/skill/\${data[i].image3}" alt="img"/>
										<span class='tooltiptext tooltip-right'> 
											<b style='color: #ffc107;'>\${data[i].pick3}</b><br/>
											<br />\${data[i].function3}
										</span>
									</span>
									<span class='tooltip'> 
										<img src="Images/skill/\${data[i].image4}" alt="img"/>
										<span class='tooltiptext tooltip-right'> 
											<b style='color: #ffc107;'>\${data[i].pick4}</b><br/>
											<br />\${data[i].function4}
										</span>
									</span> 
									<span class='tooltip'> 
										<img src="Images/skill/\${data[i].image5}" alt="img"/>
										<span class='tooltiptext tooltip-right'> 
											<b style='color: #ffc107;'>\${data[i].pick5}</b><br/>
											<br />\${data[i].function5}
										</span>
									</span> 
									<span class='tooltip'> 
										<img src="Images/skill/\${data[i].image6}" alt="img"/>
										<span class='tooltiptext tooltip-right'> 
											<b style='color: #ffc107;'>\${data[i].pick6}</b><br/>
											<br />\${data[i].function6}
										</span>
									</span>
								</div>
								<div class="statistics-spell-percent" style="width: 40%;">
									<span style="width: 23.3%;">\${data[i].winrate}</span> 
									<span style="width: 23.3%;">\${data[i].pickrate}</span> 
									<span style="width: 23.3%;">\${data[i].count}</span>
								</div>
							</li>`;
							$("#skillSeqList").append(write);
						}
					},
					error:function(r,s,e){
						alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e);
					}
				});
		   	});
		    $(document).on("click","#seq11",function(){
		    	write = `<ul class="statistics-spell-list2 statistics-display-block" id="skillSeqList"></ul>`;
				$("#skillSeq").html(write);
		   		$('.statistics-what-level-container').find('.statistics-what-level-active').removeClass('statistics-what-level-active');
				$('#seq11').addClass('statistics-what-level-active');
				$.ajax({
					type:"get",
					url:"../SkillSeqServlet",
					data:{"name":champName,"line":champLine,"select":"11"},
					datatype:"json",
					success:function(data){
						for(var i = 0;i<data.length;i++){
							write = `<li class="statistics-list-items statistics-border-bottom">
								<div class="statistics-spell">
									<span class='tooltip'> 
										<img src="Images/skill/\${data[i].image1}" alt="img"/>
										<span class='tooltiptext tooltip-right'> 
											<b style='color: #ffc107;'>\${data[i].pick1}</b><br/>
											<br />\${data[i].function1}
										</span>
									</span> 
									<span class='tooltip'> 
										<img src="Images/skill/\${data[i].image2}" alt="img"/>
										<span class='tooltiptext tooltip-right'> 
											<b style='color: #ffc107;'>\${data[i].pick2}</b><br/>
											<br />\${data[i].function2}
										</span>
									</span> 
									<span class='tooltip'> 
										<img src="Images/skill/\${data[i].image3}" alt="img"/>
										<span class='tooltiptext tooltip-right'> 
											<b style='color: #ffc107;'>\${data[i].pick3}</b><br/>
											<br />\${data[i].function3}
										</span>
									</span>
									<span class='tooltip'> 
										<img src="Images/skill/\${data[i].image4}" alt="img"/>
										<span class='tooltiptext tooltip-right'> 
											<b style='color: #ffc107;'>\${data[i].pick4}</b><br/>
											<br />\${data[i].function4}
										</span>
									</span> 
									<span class='tooltip'> 
										<img src="Images/skill/\${data[i].image5}" alt="img"/>
										<span class='tooltiptext tooltip-right'> 
											<b style='color: #ffc107;'>\${data[i].pick5}</b><br/>
											<br />\${data[i].function5}
										</span>
									</span> 
									<span class='tooltip'> 
										<img src="Images/skill/\${data[i].image6}" alt="img"/>
										<span class='tooltiptext tooltip-right'> 
											<b style='color: #ffc107;'>\${data[i].pick6}</b><br/>
											<br />\${data[i].function6}
										</span>
									</span>
									<span class='tooltip'> 
										<img src="Images/skill/\${data[i].image7}" alt="img"/>
										<span class='tooltiptext tooltip-right'> 
											<b style='color: #ffc107;'>\${data[i].pick7}</b><br/>
											<br />\${data[i].function7}
										</span>
									</span> 
									<span class='tooltip'> 
										<img src="Images/skill/\${data[i].image8}" alt="img"/>
										<span class='tooltiptext tooltip-right'> 
											<b style='color: #ffc107;'>\${data[i].pick8}</b><br/>
											<br />\${data[i].function8}
										</span>
									</span>
									<span class='tooltip'> 
										<img src="Images/skill/\${data[i].image9}" alt="img"/>
										<span class='tooltiptext tooltip-right'> 
											<b style='color: #ffc107;'>\${data[i].pick9}</b><br/>
											<br />\${data[i].function9}
										</span>
									</span> 
									<span class='tooltip'> 
										<img src="Images/skill/\${data[i].image10}" alt="img"/>
										<span class='tooltiptext tooltip-right'> 
											<b style='color: #ffc107;'>\${data[i].pick10}</b><br/>
											<br />\${data[i].function10}
										</span>
									</span> 
									<span class='tooltip'> 
										<img src="Images/skill/\${data[i].image11}" alt="img"/>
										<span class='tooltiptext tooltip-right'> 
											<b style='color: #ffc107;'>\${data[i].pick11}</b><br/>
											<br />\${data[i].function11}
										</span>
									</span>
								</div>
								<div class="statistics-spell-percent" style="width: 40%;">
									<span style="width: 23.3%;">\${data[i].winrate}</span> 
									<span style="width: 23.3%;">\${data[i].pickrate}</span> 
									<span style="width: 23.3%;">\${data[i].count}</span>
								</div>
							</li>`;
							$("#skillSeqList").append(write);
						}
					},
					error:function(r,s,e){ 
						alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e);
					}
				});
		   	});
   		 });
    	/* 통계, 기본정보,패치히스토리, 커뮤니티 선택이벤트 핸들러 */
    	$(function(){
    		 /*탑, 정글, 미드, 원딜, 서폿 버튼태그*/
	       	 <%
	       	 	if(champion_line.equals("탑")) {
	       	 		%>
	       	 			$('.line-button').eq(0).addClass('button-active');
	       	 		<%
	       	 	} else if(champion_line.equals("정글")) {
	       	 		%>
	       	 			$('.line-button').eq(1).addClass('button-active');
	       	 		<%
	       	 	}  else if(champion_line.equals("미드")) {
	       	 		%>
	       	 			$('.line-button').eq(2).addClass('button-active');
	       	 		<%
	       	 	}  else if(champion_line.equals("원딜")) {
	       	 		%>
	       	 			$('.line-button').eq(3).addClass('button-active');
	       	 		<%
	       	 	}  else if(champion_line.equals("서폿")) {
	       	 		%>
	       	 			$('.line-button').eq(4).addClass('button-active');
	       	 		<%
	       	 	}
	       	 %>
	       	 /*고승률 빌드, 대중적인 빌드*/
	       	 $('.pick-build-btn').click(function() {
	       		$('#pick-build').animate({ opacity: '1' });
	       		$('#win-build').animate({ opacity: '0' },500);
	       		
	       		$('#pick-build').removeClass('display-none');
	       		$('#win-build').addClass('display-none');
	       	 });
	       	 $('.win-build-btn').click(function() {
	       		$('#win-build').animate({ opacity: '1' });
	       		$('#pick-build').animate({ opacity: '0' },500);
	       		
	       		$('#pick-build').addClass('display-none');
	       		$('#win-build').removeClass('display-none');
	       	 });
	       	 
	       	 
	       	$("#champ-nav1").on("click", function() {
		   		var champName = "<%=champion_name%>";
		   		var champLine = "<%=champion_line%>";
		   		var isGray = "";
		   		var write = `<div id="statistics">
		   					<div class="statistics-champ-match-container" id="counter">
					            <div class="statistics-title">가렌 상대 챔피언</div>
					            <div class="statistics-champ-match">
						            <div id="match-hard" class="statistics-match-list">
			                    	 	<h4>상대하기 어려움</h4>
			                    	 </div>
						            <div id="match-easy" class="statistics-match-list">
			                    	 	<h4>상대하기 쉬움</h4>
			                    	 </div>
		                    	 </div>
		                    </div>
	                    	<div class="statistics-content-container" id="spell-startitem">
		                   		<div class="statistics-spell-items" id="spell-startitem">
		     						<div class="statistics-title">스펠, 아이템 선택</div>
		     						<div class="statistics-spell-item-content">
		     							<div class="statistics-spell-box">
		     								<h4 style="padding: 5px">스펠</h4>
		     								<div class="statistics-number">
		     									<span class="statistics-number-items">승률</span> 
		     									<span class="statistics-number-items">선택률</span> 
		     									<span class="statistics-number-items">카운트수</span>
		     								</div>
		     								<ul class="statistics-spell-list" id="spellList">
		     								</ul>
		     							</div>
		     							<div class="statistics-spell-box">
											<h4 style="padding: 5px">스타트 아이템</h4>
											<div class="statistics-number">
												<span class="statistics-number-items">승률</span> 
												<span class="statistics-number-items">선택률</span> 
												<span class="statistics-number-items">카운트수</span>
											</div>
											<ul class="statistics-spell-list" id="startItemList">
											</ul>
										</div>
										<div class="statistics-spell-box" style="border-right: none;">
											<h4 style="padding: 5px">신발</h4>
											<div class="statistics-number">
												<span class="statistics-number-items">승률</span> 
												<span class="statistics-number-items">선택률</span>
												<span class="statistics-number-items">카운트수</span>
											</div>
											<ul class="statistics-spell-list" id="shoesList">
											</ul>
										</div>
		     						</div>
		     					</div>
		     				</div>
		     				<div class="statistics-content-container statistics-core-each" id="coreitem">
								<div class="statistics-spell-items">
									<div class="statistics-title">코어템 통계</div>
									<div class="statistics-spell-item-content">
										<div class="statistics-spell-box">
											<h4 style="padding: 5px">1코어</h4>
											<div class="statistics-number">
												<span class="statistics-number-items">승률</span> 
												<span class="statistics-number-items">선택률</span> 
												<span class="statistics-number-items">카운트수</span>
											</div>
											<ul class="statistics-spell-list" id="first">
											</ul>
										</div>
										<div class="statistics-spell-box">
											<h4 style="padding: 5px">2코어</h4>
											<div class="statistics-number">
												<span class="statistics-number-items">승률</span> 
												<span class="statistics-number-items">선택률</span> 
												<span class="statistics-number-items">카운트수</span>
											</div>
											<ul class="statistics-spell-list" id="secend">
											</ul>
										</div>
										<div class="statistics-spell-box" style="border-right: none;">
											<h4 style="padding: 5px;">3코어</h4>
											<div class="statistics-number">
												<span class="statistics-number-items">승률</span> 
												<span class="statistics-number-items">선택률</span> 
												<span class="statistics-number-items">카운트수</span>
											</div>
											<ul class="statistics-spell-list" id="therd">
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="statistics-content-container statistics-core-combine">
								<div class="statistics-spell-items">
									<div class="statistics-title">코어템 조합 통계</div>
									<div class="statistics-spell-item-content">
										<div class="statistics-spell-box">
											<h4 style="padding: 5px">2코어 조합</h4>
											<div class="statistics-number">
												<span class="statistics-number-items">승률</span> 
												<span class="statistics-number-items">선택률</span> 
												<span class="statistics-number-items">카운트수</span>
											</div>
											<ul class="statistics-spell-list" id="2CoreCombine">
											</ul>
										</div>
										<div class="statistics-spell-box">
											<h4 style="padding: 5px">3코어 조합</h4>
											<div class="statistics-number">
												<span class="statistics-number-items">승률</span> 
												<span class="statistics-number-items">선택률</span> 
												<span class="statistics-number-items">카운트수</span>
											</div>
											<ul class="statistics-spell-list" id="3CoreCombine">
											</ul>
										</div>
										<div class="statistics-spell-box" style="border-right: none;">
											<h4 style="padding: 5px;">4코어 조합</h4>
											<div class="statistics-number">
												<span class="statistics-number-items">승률</span> 
												<span class="statistics-number-items">선택률</span> 
												<span class="statistics-number-items">카운트수</span>
											</div>
											<ul class="statistics-spell-list" id="4CoreCombine">
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="statistics-content-container statistics-skill" id="skills">
								<div class="statistics-spell-items">
									<div class="statistics-title">스킬</div>
									<div class="statistics-spell-item-content">
										<div class="statistics-spell-box" style="border-right: none; width: 35%;">
											<h4 style="padding: 5px">마스터 순서</h4>
											<div class="statistics-number">
												<span class="statistics-number-items">승률</span> 
												<span class="statistics-number-items">선택률</span> 
												<span class="statistics-number-items">카운트수</span>
											</div>
											<ul class="statistics-spell-list" id="skillMaster">
											</ul>
										</div>
										<div class="statistics-spell-box" style="border-right: none; width: 65%; padding: 12px 12px;">
											<div class="statistics-what-level-container">
												<span class="statistics-what-level statistics-what-level-active" id="seq3">3레벨까지</span> 
												<span class="statistics-what-level" id="seq6">6레벨 까지</span> 
												<span class="statistics-what-level" id="seq11">11레벨 까지</span>
											</div>
											<div class="statistics-number">
												<span class="statistics-number-items">승률</span> 
												<span class="statistics-number-items">선택률</span> 
												<span class="statistics-number-items">카운트수</span>
											</div>
											<div id="skillSeq">
												<ul class="statistics-spell-list2 statistics-display-block" id="skillSeqList">
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="statistics-content-container statistics-rune" id="runes">
								<div class="statistics-spell-items">
									<div class="statistics-title">룬</div>
									<div class="statistics-spell-item-content">
										<div class="statistics-spell-box"
											style="width: 60%; padding: 15px;">
											<h4 style="padding: 5px">조합별 통계</h4>
											<div class="statistics-number">
												<span class="statistics-number-items">승률</span> 
												<span class="statistics-number-items">선택률</span> 
											</div>
											<ul class="statistics-spell-list" id="runeCombine">
											</ul>
										</div>
										<div class="statistics-spell-box" style="border-right: none; width: 40%; padding: 15px;">
											<h4 style="padding: 5px">파편 조합 통계</h4>
											<div class="statistics-number">
												<span class="statistics-number-items">승률</span> 
												<span class="statistics-number-items">선택률</span> 
											</div>
											<ul class="statistics-spell-list" id="runeShard">
											</ul>
										</div>
									</div>
								</div>
							</div>
			     		</div>`;
				$("#loadContents").html(write);
		   		
				$('.champ-nav').find('.champ-nav-active').removeClass('champ-nav-active');
				$('#champ-nav1').addClass('champ-nav-active');
				$.ajax({
				   	type:"get",
					url:"../MatchListServlet",
					data:{"name":champName,"line":champLine,"counter":"hard"},
					datatype:"json",
					async: false,
					success:function(data){
						for(var i = 0; i < data.length; i++) {
							if(data[0].name==null) {
								write =	`<span></span>`;
								$("#match-hard").append(write);
							} else {
								if (i % 2 == 0) {
									isGray = "statistics-gray";
								} else {
									isGray = "";
								}
								write = `<a class="statistics-hard-list \${isGray}" href="#"> 
											<span style="width: 10%;"> 
												<img src="Images/champion/head/\${data[i].image}" alt="img">
											</span> 
											<span style="width: 60%; padding: 10px 0px 0px 20px; text-align: left;">
													<span>\${data[i].name}</span>
											</span> 
											<span style="width: 30%; padding-top: 10px;"> 
												<span>\${data[i].count}</span> 
											<span class="statistics-hard">\${data[i].winrate}%</span>
											</span>
										</a>`;
								$("#match-hard").append(write);
							}
						}
					},
					error:function(r,s,e){
						alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e);
					}
			   });
			   $.ajax({
					type:"get",
					url:"../MatchListServlet",
					data:{"name":champName,"line":champLine,"counter":"easy"},
					datatype:"json",
					async: false,
					success:function(data){
						for(var i = 0; i < data.length; i++) {
							if (i % 2 == 0) {
								isGray = "statistics-gray";
							} else {
								isGray = "";
							}
							write = `<a class="statistics-hard-list \${isGray}" href="#"> 
										<span style="width: 10%;"> 
											<img src="Images/champion/head/\${data[i].image}" alt="img">
										</span> 
										<span style="width: 60%; padding: 10px 0px 0px 20px; text-align: left;">
												<span>\${data[i].name}</span>
										</span> 
										<span style="width: 30%; padding-top: 10px;"> 
											<span>\${data[i].count}</span> 
										<span class="statistics-easy">\${data[i].winrate}%</span>
										</span>
									</a>`;
							$("#match-easy").append(write);
						}
					},
					error:function(r,s,e){
						alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e);
					}
			   });
			   $.ajax({
					type:"get",
					url:"../StartItemServlet",
					data:{"name":champName,"line":champLine,"select":"spell"},
					datatype:"json",
					async: false,
					success:function(data){
						for(var i = 0; i < data.length; i++) {
							if (i % 2 == 0) {
								isGray = "statistics-gray";
							} else {
								isGray = "";
							}
							write = `<li class="statistics-list-items \${isGray}">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/spell/\${data[i].pick1} " alt="img" /> 
												<span class='tooltiptext tooltip-right'>
													<b style='color: #ffc107;'>\${data[i].name1}</b>
													<br />
													<br />\${data[i].function1}
												</span>
											</span> 
											<span class='tooltip'> 
												<img src="Images/spell/\${data[i].pick2} " alt="img" /> 
												<span class='tooltiptext tooltip-right'>
													<b style='color: #ffc107;'>\${data[i].name2}</b>
													<br />
													<br />\${data[i].function2}
												</span>
											</span>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;">\${data[i].winrate}</span>
											<span style="width: 23.3%;">\${data[i].pickrate}</span>
											<span style="width: 23.3%;">\${data[i].count}</span>
										</div>
									</li>`;
							$("#spellList").append(write);
						}
					},
					error:function(r,s,e){
						alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e);
					}
				});
			   $.ajax({
					type:"get",
					url:"../StartItemServlet",
					data:{"name":champName,"line":champLine,"select":"startItem"},
					datatype:"json",
					async: false,
					success:function(data){
						for(var i = 0; i < data.length; i++) {
							if (i % 2 == 0) {
								isGray = "statistics-gray";
							} else {
								isGray = "";
							}
							write = `<li class="statistics-list-items \${isGray}">
										<div class="statistics-spell">
											<span class='tooltip'> 
											<img src="Images/item/\${data[i].pick1}" alt="img" /> 
												<span class='tooltiptext tooltip-right'>
													<b style='color: #ffc107;'>\${data[i].name1}</b>
													<br />
													<br />\${data[i].function1}
												</span>
											</span>`;
							if(data[i].name2==="없음"){
								
							}else{			
								write +=`<span class='tooltip'> 
											<img src="Images/item/\${data[i].pick2}" alt="img" /> 
												<span class='tooltiptext tooltip-right'>
													<b style='color: #ffc107;'>\${data[i].name2}</b>
													<br />
													<br />\${data[i].function2}
												</span>
											</span>`;
							}				
								write += `</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;">\${data[i].winrate}</span>
											<span style="width: 23.3%;">\${data[i].pickrate}</span>
											<span style="width: 23.3%;">\${data[i].count}</span>
										</div>
									</li>`;
							$("#startItemList").append(write);
						}
					},
					error:function(r,s,e){
						alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e);
					}
				});
			   $.ajax({
					type:"get",
					url:"../StartItemServlet",
					data:{"name":champName,"line":champLine,"select":"shoes"},
					datatype:"json",
					async: false,
					success:function(data){
						for(var i = 0; i < data.length; i++) {
							if (i % 2 == 0) {
								isGray = "statistics-gray";
							} else {
								isGray = "";
							}
							write = `<li class="statistics-list-items \${isGray}">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/item/\${data[i].pick1}" alt="img" /> 
												<span class='tooltiptext tooltip-right'>
													<b style='color: #ffc107;'>\${data[i].name1}</b>
													<br />
													<br />\${data[i].function1}
												</span>
											</span>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;">\${data[i].winrate}</span>
											<span style="width: 23.3%;">\${data[i].pickrate}</span>
											<span style="width: 23.3%;">\${data[i].count}</span>
										</div>
									</li>`;
							$("#shoesList").append(write);
						}
					},
					error:function(r,s,e){ 
						alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e);
					}
				});
			   $.ajax({
					type:"get",
					url:"../CoreEachServlet",
					data:{"name":champName,"line":champLine,"select":"first"},
					datatype:"json",
					async: false,
					success:function(data){
						for(var i = 0; i < data.length; i++) {
							if (i % 2 == 0) {
								isGray = "statistics-gray";
							} else {
								isGray = "";
							}
							write = `<li class="statistics-list-items \${isGray}">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/item/\${data[i].image}" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick}</b>
													<br />
													<br />\${data[i].function1}
												</span>
											</span>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;">\${data[i].winrate}</span>
											<span style="width: 23.3%;">\${data[i].pickrate}</span>
											<span style="width: 23.3%;">\${data[i].count}</span>
										</div>
									</li>`;
							$("#first").append(write);
						}
					},
					error:function(r,s,e){ 
						alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e);
					}
			   });
			   $.ajax({
					type:"get",
					url:"../CoreEachServlet",
					data:{"name":champName,"line":champLine,"select":"secend"},
					datatype:"json",
					async: false,
					success:function(data){
						for(var i = 0; i < data.length; i++) {
							if (i % 2 == 0) {
								isGray = "statistics-gray";
							} else {
								isGray = "";
							}
							write = `<li class="statistics-list-items \${isGray}">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/item/\${data[i].image}" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick}</b>
													<br />
													<br />\${data[i].function2}
												</span>
											</span>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;">\${data[i].winrate}</span>
											<span style="width: 23.3%;">\${data[i].pickrate}</span>
											<span style="width: 23.3%;">\${data[i].count}</span>
										</div>
									</li>`;
							$("#secend").append(write);
						}
					},
					error:function(r,s,e){ 
						alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e);
					}
			   });
			   $.ajax({
					type:"get",
					url:"../CoreEachServlet",
					data:{"name":champName,"line":champLine,"select":"therd"},
					datatype:"json",
					async: false,
					success:function(data){
						for(var i = 0; i < data.length; i++) {
							if (i % 2 == 0) {
								isGray = "statistics-gray";
							} else {
								isGray = "";
							}
							write = `<li class="statistics-list-items \${isGray}">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/item/\${data[i].image}" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick}</b>
													<br />
													<br />\${data[i].function3}
												</span>
											</span>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;">\${data[i].winrate}</span>
											<span style="width: 23.3%;">\${data[i].pickrate}</span>
											<span style="width: 23.3%;">\${data[i].count}</span>
										</div>
									</li>`;
							$("#therd").append(write);
						}
					},
					error:function(r,s,e){ 
						alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e);
					}
			   });
				$.ajax({
					type:"get",
					url:"../CoreCombineServlet",
					data:{"name":champName,"line":champLine,"select":"2core"},
					datatype:"json",
					async: false,
					success:function(data){
						for(var i = 0; i < data.length; i++) {
							if (i % 2 == 0) {
								isGray = "statistics-gray";
							} else {
								isGray = "";
							}
							write = `<li class="statistics-list-items \${isGray}">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/item/\${data[i].image1}" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick1}</b><br />
													<br />\${data[i].function1}
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/item/\${data[i].image2}" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick2}</b><br />
													<br />\${data[i].function2}
												</span>
											</span>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;">\${data[i].winrate}</span> 
											<span style="width: 23.3%;">\${data[i].pickrate}</span> 
											<span style="width: 23.3%;">\${data[i].count}</span>
										</div>
									</li>`;
							$("#2CoreCombine").append(write);
						}
					},
					error:function(r,s,e){ alert('1');
						/* alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e); */
					}
			   });
				$.ajax({
					type:"get",
					url:"../CoreCombineServlet",
					data:{"name":champName,"line":champLine,"select":"3core"},
					datatype:"json",
					async: false,
					success:function(data){
						for(var i = 0; i < data.length; i++) {
							if (i % 2 == 0) {
								isGray = "statistics-gray";
							} else {
								isGray = "";
							}
							write = `<li class="statistics-list-items \${isGray}">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/item/\${data[i].image1}" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick1}</b><br />
													<br />\${data[i].function1}
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/item/\${data[i].image2}" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick2}</b><br />
													<br />\${data[i].function2}
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/item/\${data[i].image3}" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick3}</b><br />
													<br />\${data[i].function3}
												</span>
											</span>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;">\${data[i].winrate}</span> 
											<span style="width: 23.3%;">\${data[i].pickrate}</span> 
											<span style="width: 23.3%;">\${data[i].count}</span>
										</div>
									</li>`;
							$("#3CoreCombine").append(write);
						}
					},
					error:function(r,s,e){ alert('2');
						/* alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e); */
					}
			   });
				$.ajax({
					type:"get",
					url:"../CoreCombineServlet",
					data:{"name":champName,"line":champLine,"select":"4core"},
					datatype:"json",
					async: false,
					success:function(data){
						for(var i = 0; i < data.length; i++) {
							if (i % 2 == 0) {
								isGray = "statistics-gray";
							} else {
								isGray = "";
							}
							write = `<li class="statistics-list-items \${isGray}">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/item/\${data[i].image1}" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick1}</b><br />
													<br />\${data[i].function1}
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/item/\${data[i].image2}" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick2}</b><br />
													<br />\${data[i].function2}
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/item/\${data[i].image3}" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick3}</b><br />
													<br />\${data[i].function3}
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/item/\${data[i].image4}" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick4}</b><br />
													<br />\${data[i].function4}
												</span>
											</span>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;">\${data[i].winrate}</span> 
											<span style="width: 23.3%;">\${data[i].pickrate}</span> 
											<span style="width: 23.3%;">\${data[i].count}</span>
										</div>
									</li>`;
							$("#4CoreCombine").append(write);
						}
					},
					error:function(r,s,e){ alert('3');
						/* alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e); */
					}
			   });
				$.ajax({
					type:"get",
					url:"../SkillMasterServlet",
					data:{"name":champName,"line":champLine},
					datatype:"json",
					async: false,
					success:function(data){
						for(var i = 0;i<data.length;i++){
							write = `<li class="statistics-list-items statistics-border-bottom">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/skill/\${data[i].image1}" alt="img"/>
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick1}</b><br/>
													<br />\${data[i].function1}
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/skill/\${data[i].image2}" alt="img"/>
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick2}</b><br/>
													<br />\${data[i].function2}
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/skill/\${data[i].image3}" alt="img"/>
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick3}</b><br/>
													<br />\${data[i].function3}
												</span>
											</span>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;">\${data[i].winrate}</span> 
											<span style="width: 23.3%;">\${data[i].pickrate}</span> 
											<span style="width: 23.3%;">\${data[i].count}</span>
										</div>
									</li>`;
							$("#skillMaster").append(write);
						}
					},
					error:function(r,s,e){ alert('4');
						/* alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e); */
					}
				});
				$.ajax({
					type:"get",
					url:"../SkillSeqServlet",
					data:{"name":champName,"line":champLine,"select":"3"},
					datatype:"json",
					async: false,
					success:function(data){
						for(var i = 0;i<data.length;i++){
							write = `<li class="statistics-list-items statistics-border-bottom">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/skill/\${data[i].image1}" alt="img"/>
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick1}</b><br/>
													<br />\${data[i].function1}
												</span>
											</span> 
											<span class='tooltip'> 
												<img src="Images/skill/\${data[i].image2}" alt="img"/>
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick2}</b><br/>
													<br />\${data[i].function2}
												</span>
											</span> 
											<span class='tooltip'> 
												<img src="Images/skill/\${data[i].image3}" alt="img"/>
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick3}</b><br/>
													<br />\${data[i].function3}
												</span>
											</span> 
										</div>
										<div class="statistics-spell-percent" style="width: 40%;">
											<span style="width: 23.3%;">\${data[i].winrate}</span> 
											<span style="width: 23.3%;">\${data[i].pickrate}</span> 
											<span style="width: 23.3%;">\${data[i].count}</span>
										</div>
									</li>`;
							$("#skillSeq").append(write);
						}
					},
					error:function(r,s,e){ alert('5');
						/* alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e); */
					}
				});
				$.ajax({
					type:"get",
					url:"../RuneCombineServlet",
					data:{"name":champName,"line":champLine},
					datatype:"json",
					async: false,
					success:function(data){
						for(var i = 0; i < data.length; i++) {
							if (i % 2 == 0) {
								isGray = "statistics-gray";
							} else {
								isGray = "";
							}
							write = `<li class="statistics-list-items \${isGray}">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/rune/\${data[i].image1}" alt="img"> 
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick1}</b><br/>
													<br />\${data[i].function1}
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/rune/\${data[i].image2}" alt="img"> 
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick2}</b><br/>
													<br />\${data[i].function2}
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/rune/\${data[i].image3}" alt="img"> 
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick3}</b><br/>
													<br />\${data[i].function3}
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/rune/\${data[i].image4}" alt="img"> 
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick4}</b><br/>
													<br />\${data[i].function4}
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/rune/\${data[i].image5}" alt="img"> 
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick5}</b><br/>
													<br />\${data[i].function5}
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/rune/\${data[i].image6}" alt="img"> 
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>\${data[i].pick6}</b><br/>
													<br />\${data[i].function6}
												</span>
											</span>
										</div>
										<div class="statistics-spell-percent" style="width: 45%;">
											<span style="width: 23.3%;">\${data[i].winrate}</span> 
											<span style="width: 23.3%;">\${data[i].pickrate}</span> 
										</div>
									</li>`;
							$("#runeCombine").append(write);
						}
					},
					error:function(r,s,e){ alert('6');
						/* alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e); */
					}
				});
				$.ajax({
					type:"get",
					url:"../RuneShardServlet",
					data:{"name":champName,"line":champLine},
					datatype:"json",
					async: false,
					success:function(data){
						for(var i = 0; i < data.length; i++) {
							if (i % 2 == 0) {
								isGray = "statistics-gray";
							} else {
								isGray = "";
							}
							write = `<li class="statistics-list-items \${isGray}">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/rune/\${data[i].image1}" alt="img"> 
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>공격</b><br/>
													<br />\${data[i].pick1}
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/rune/\${data[i].image2}" alt="img"> 
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>유연</b><br/>
													<br />\${data[i].pick2}
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/rune/\${data[i].image3}" alt="img"> 
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>방어</b><br/>
													<br />\${data[i].pick3}
												</span>
											</span>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;">\${data[i].winrate}</span> 
											<span style="width: 23.3%;">\${data[i].pickrate}</span> 
										</div>
									</li>`;
							$("#runeShard").append(write);
						}
					},
					error:function(r,s,e){ alert('7');
						/* alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e); */
					}
				});
			});
    		
		   	//기본정보
		   	$("#champ-nav2").on("click", function() {
		   		var champName = "<%=champion_name%>";
		   		var code = "<div class = 'basic-info-container' id='basic-info'><h3 style='margin :0 0 8px'>기본정보</h3></div>";
        	   $("#loadContents").html(code);
			   
			   $('.champ-nav').find('.champ-nav-active').removeClass('champ-nav-active');
			   $('#champ-nav2').addClass('champ-nav-active');
			   $.ajax({
					type:"get",
					url:"../BasicInfoServlet",
					data:{"name":champName},
					datatype:"json",
					async: false,
					success:function(data){
						var bottom = "";
		            	var color = "";
		            	var write = `<div class = "basic-info-basic-stat-box">
				                        <h4 style="margin: 24px 0 12px;">기본 능력치</h4>
				                        <div class = "basic-info-stat-div-top">
				                            <span class="basic-info-stat-name" style = "background-color:transparent;"></span>
				                            <span class = "basic-info-stat-basic">기본능력치 (+레벨 당 상승)</span>
				                            <span class = "basic-info-stat-final">최종수치</span>
				                            <span class = "basic-info-stat-rank">챔피언 순위</span>
				                        </div>
				                        <div id="loadstat">
				                        </div>
									</div>`;
						$("#basic-info").html(write);
		            	for(var i = 0; i < data.length; i++) {
		            		if(i%2==1){
		            			color = "color";
		            		}else{
		            			color = "";
		            		}
		            		if(i==data.length-1){
		            			bottom = "-bottom";
		            		}
			            write = `<div class = 'basic-info-stat-div\${bottom}' id='\${color}'>
			           				<span class='basic-info-stat-name' >\${data[i].stat}</span>
			            			<span class = 'basic-info-stat-basic'>\${data[i].start}</span>
			            			<span class = 'basic-info-stat-final'>\${data[i].finalstat}</span>
			            			<span class = 'basic-info-stat-rank'>\${data[i].rank}</span>
			            		</div>`;
		            	$("#loadstat").append(write);
		            	}
					},
					error:function(r,s,e){ 
						alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e);
					}
			   });
			   $.ajax({
				   	type:"get",
					url:"../BasicSkillServlet",
					data:{"name":champName},
					datatype:"json",
					async: false,
					success: function(data){
						var write = `<div class = "basic-info-skill-detail-box">
				            <h4 style="margin: 24px 0 12px;">스킬</h4>            
				            <p class = "basic-info-explanation">클릭하여 상세 설명을 볼 수 있습니다.</p>
				            
				                <h4 style="margin:24px 0 12px;">상세정보</h4>
					            <a href="#skill-p" class = "basic-info-skill-detail-info">
					                <img class = "basic-info-champ-img" src="Images/skill/\${data[1].image}"/>
					                <span class = "basic-info-champ-skill">\${data[1].skillkey}</span>
					                <p class = "basic-info-skill-name">\${data[1].skillname}</p>
					            </a>
					            <a href="#skill-q" class = "basic-info-skill-detail-info">
					                <img class = "basic-info-champ-img" src="Images/skill/\${data[2].image}"/>
					                <span class = "basic-info-champ-skill">\${data[2].skillkey}</span>
					                <p class = "basic-info-skill-name">\${data[2].skillname}</p>
					            </a>
					            <a href="#skill-w" class = "basic-info-skill-detail-info">
					                <img class = "basic-info-champ-img" src="Images/skill/\${data[4].image}"/>
					                <span class = "basic-info-champ-skill">\${data[4].skillkey}</span>
					                <p class = "basic-info-skill-name">\${data[4].skillname}</p>
					            </a>
					            <a href="#skill-e" class = "basic-info-skill-detail-info">
					                <img class = "basic-info-champ-img" src="Images/skill/\${data[0].image}"/>
					                <span class = "basic-info-champ-skill">\${data[0].skillkey}</span>
					                <p class = "basic-info-skill-name">\${data[0].skillname}</p>
					            </a>
					            <a href="#skill-r" class = "basic-info-skill-detail-info">
					                <img class = "basic-info-champ-img" src="Images/skill/\${data[3].image}"/>
					                <span class = "basic-info-champ-skill">\${data[3].skillkey}</span>
					                <p class = "basic-info-skill-name">\${data[3].skillname}</p>
					            </a>
				            
				            <div id="champrole">
				            	
				            </div>
				            
				        </div>

				        <div style="clear: both; height:100px;" id="skill-p"></div>

				        <div class = "basic-info-skill-container">
					            <h4>스킬 상세설명</h4>
					            
					            <div class = "basic-info-skill-info-box" style="border-top: 1px solid rgba(126, 155, 255, .5);">
					                <div class = "basic-info-skill-info-name">
					                    <span class = "basic-info-skill-kind" id = "skill-q">\${data[1].skillkey}</span>
					                    <h5 class = "basic-info-skill-kor-name">\${data[1].skillname}</h5>                   
					                </div>
					                <div class = "basic-info-skill-info">
					                    <img src="Images/skill/\${data[1].image}" style="display: block;" />
					                  
					                    <p class="basic-info-skill-text-style">\${data[1].skillfunction}</p>
					                </div>
					            </div>
					            
					            <div class = "basic-info-skill-info-box" style="border-top: 1px solid rgba(126, 155, 255, .5);">
					                <div class = "basic-info-skill-info-name">
					                    <span class = "basic-info-skill-kind" id = "skill-w">\${data[2].skillkey}</span>
					                    <h5 class = "basic-info-skill-kor-name">\${data[2].skillname}</h5>                   
					                </div>
					                <div class = "basic-info-skill-info">
					                    <img src="Images/skill/\${data[2].image}" style="display: block;" />
					                  
					                    <p class="basic-info-skill-text-style">\${data[2].skillfunction}</p>
					                </div>
					            </div>
					            
					            <div class = "basic-info-skill-info-box" style="border-top: 1px solid rgba(126, 155, 255, .5);">
					                <div class = "basic-info-skill-info-name">
					                    <span class = "basic-info-skill-kind" id = "skill-e">\${data[4].skillkey}</span>
					                    <h5 class = "basic-info-skill-kor-name">\${data[4].skillname}</h5>                   
					                </div>
					                <div class = "basic-info-skill-info">
					                    <img src="Images/skill/\${data[4].image}" style="display: block;" />
					                  
					                    <p class="basic-info-skill-text-style">\${data[4].skillfunction}</p>
					                </div>
					            </div>
					            
					            <div class = "basic-info-skill-info-box" style="border-top: 1px solid rgba(126, 155, 255, .5);">
					                <div class = "basic-info-skill-info-name">
					                    <span class = "basic-info-skill-kind" id = "skill-r">\${data[0].skillkey}</span>
					                    <h5 class = "basic-info-skill-kor-name">\${data[0].skillname}</h5>                   
					                </div>
					                <div class = "basic-info-skill-info">
					                    <img src="Images/skill/\${data[0].image}" style="display: block;" />
					                  
					                    <p class="basic-info-skill-text-style">\${data[0].skillfunction}</p>
					                </div>
					            </div>
					            
					            <div class = "basic-info-skill-info-box" style="border-top: 1px solid rgba(126, 155, 255, .5);">
					                <div class = "basic-info-skill-info-name">
					                    <span class = "basic-info-skill-kind" >\${data[3].skillkey}</span>
					                    <h5 class = "basic-info-skill-kor-name">\${data[3].skillname}</h5>                   
					                </div>
					                <div class = "basic-info-skill-info">
					                    <img src="Images/skill/\${data[3].image}" style="display: block;" />
					                  
					                    <p class="basic-info-skill-text-style">\${data[3].skillfunction}</p>
					                </div>
					            </div>
				        </div>`;
				    $("#basic-info").append(write);
					},
					error: function(r,s,e){
						alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e);
					}
			   });
			   $.ajax({
				   type:"get",
					url:"../ChampRoleServlet",
					data:{"name":champName},
					datatype:"json",
					success: function(data){
						var write = `<span style="color: rgba(47,62,78 , .7); font-size: 12px;">역할군</span>
			                <span style="font-size: 14px; font-weight : 700; color: #353945;">\${data[0].role1}</span>
			            	<br/>`;
			            if(data[0].role2==="없음"){
			            	
			            }else{
		                    write += `<span style="color: rgba(47,62,78 , .7); font-size: 12px;">역할군</span>
		                    <span style="font-size: 14px; font-weight : 700; color: #353945;">\${data[0].role2}</span>`;
		                }
			            $("#champrole").html(write);
					},
					error: function(r,s,e){
						alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e);
					}
			   });
			});
		   	
		   	//패치 히스토리
		   	$("#champ-nav3").on("click", function() {
		   		var champName = "<%=champion_name%>";
				$('.champ-nav').find('.champ-nav-active').removeClass('champ-nav-active');
				$('#champ-nav3').addClass('champ-nav-active');
				$.ajax({
				   	type:"get",
					url:"../PatchHistoryServlet",
					data:{"name":champName},
					datatype:"json",
					success:function(data){
						var write = `<div id="patch-history">
							<section style="padding-bottom:400px;" id="patch_history_section">
							<h3>\${data[0].name} 패치 히스토리</h3>
							</section>
							</div>`;
						$("#loadContents").html(write);
						for(var i = 0;i < data.length;i++){
							write = `<div class="patch-history-patch">
										<div class="patch-history-ver">
										<h4 class="patch-history-h4">\${data[i].version}</h4>
											<div class="patch-history-skill-imgbox">
												<span><img class="patch-history-img" src="Images/skill/\${data[i].image}"/></span>
											</div>
											<div class="patch-history-content">
												<ul class="patch-history-ul">
													<li class="patch-history-li">\${data[i].content}</li>
												</ul>
											</div>
										</div>
									</div>`;
							$("#patch_history_section").append(write);
						}
					},
					error: function(r,s,e){
						alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e);
					}
				});
			});
		   	
		   	//커뮤니티
		   	$("#champ-nav4").on("click", function() {
		   		var champName = "<%=champion_name%>";
			   $('.champ-nav').find('.champ-nav-active').removeClass('champ-nav-active');
			   $('#champ-nav4').addClass('champ-nav-active');
			   $.ajax({
				   	type:"get",
					url:"../PatchHistoryServlet",
					data:{"name":champName},
					datatype:"json",
					success:function(data){
						var write = `<div id="champ-community">
									    <div class = "champ-community-community-container">
									        <div class = "champ-community-first-row">
									            <h2 class = "champ-community-champ-name">블라디미르 게시판</h2>
									            <a class = "champ-community-board-btn" href="../community/write-post.html">
									                <span class = "icno-font">
									                    <i class="fa-regular champ-community-fa-pen"></i>
									                </span>
									                <span>게시물 쓰기</span>
									            </a>
									        </div>
									        <div class = "champ-community-board-header">
									            <span class ="champ-community-board-champion">챔피언</span>
									            <span class ="champ-community-board-title">제목</span>
									            <span class ="champ-community-board-writer">작성자</span>
									            <span class ="champ-community-board-date">날짜</span>
									            <span class ="champ-community-board-count">조회</span>
									            <span class ="champ-community-board-recommand">추천</span>
									        </div>
									        <div class ="champ-community-board-list-container">
									            <div class = "champ-community-board-list" id="champCommunity">
									            </div>
											</div>
										</div>
										<ul class = "champ-community-page-box">
									        <li class="champ-community-page-back">
									            <span class = "champ-community-page-link" aria-hidden="true" title="Curremt Page"></span>
									        </li>
									        <li class = "champ-community-page-active">
									            <span class = "champ-community-page-link2" aria-label="CurremtPage" title="Curremt Page"></span>
									        </li>
									        <li class = "champ-community-page-back">
									            <span class = "champ-community-page-link" aria-hidden="true" title="Curremt Page"></span>
									        </li>
									    </ul>
									    <div class = "champ-community-board-btn2" >
									        <a class = "champ-community-btn-write" href="../community/write-post.html">
									            <i class="fa-regular fa-pen"></i>
									            <span>게시물 쓰기</span>
									        </a>
									    </div>
								    </div>`;
						$("#loadContents").html(write);
						for(var i = 0;i < data.length;i++){
							write = `<a class = "champ-community-board-item" href="../community/community-post-build.html">
					                    <span class = "champ-community-post-img">
					                        <img src="img/icon.webp" style="width: 40px; height : 40px;"/>
					                    </span>
					                    <span class = "champ-community-board-detail-box">
					                    	<span class ="champ-community-board-detail-title">[상남자의 라인, TOP] 이게 왜 좋은거지..? 화공탱 거드라 가렌
					                    		<i class = "champ-community-board-detail-comment">[\${i}]</i>
					                    	</span>
					                    </span>
					                    <span class = "champ-community-board-detail-writer">작성자 닉네임</span>
					                    <span class = "champ-community-board-detail-date">2022-05-25</span>
					                    <span class = "champ-community-board-detail-count">579</span>
					                    <span class = "champ-community-board-detail-recommand">10</span>
					                </a>`;
							$("#champCommunity").append(write);
						}
					},
					error: function(r,s,e){
						alert("에러 \n code:"+r.s+"; \n"+"message:"+r.responseText+"; \n"+"error:"+e);
					}
			   });
			});
		   	
	 		/*pick*/
		   	const csmrSize = <%=champion.championSummaryMainRune(conn, pstmt, rs, champion_name, champion_line, champion_rate).size()%>;
    		if(csmrSize==36) {
    			const csmr361 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[0]%></b></br></br><%=championSummaryMainRune[2]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[4]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[3]%></b></br></br><%=championSummaryMainRune[5]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[7]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[6]%></b></br></br><%=championSummaryMainRune[8]%></span></div>";
        		const csmr362 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[10]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[9]%></b></br></br><%=championSummaryMainRune[11]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[13]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[12]%></b></br></br><%=championSummaryMainRune[14]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[16]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[15]%></b></br></br><%=championSummaryMainRune[17]%></span></div>";
        		const csmr363 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[19]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[18]%></b></br></br><%=championSummaryMainRune[20]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[22]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[21]%></b></br></br><%=championSummaryMainRune[23]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[25]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[24]%></b></br></br><%=championSummaryMainRune[26]%></span></div>";
        		const csmr364 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[28]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[27]%></b></br></br><%=championSummaryMainRune[29]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[31]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[30]%></b></br></br><%=championSummaryMainRune[32]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[34]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[33]%></b></br></br><%=championSummaryMainRune[35]%></span></div>";
        		$('#main-rune-1').html(csmr361);
        		$('#main-rune-2').html(csmr362);
        		$('#main-rune-3').html(csmr363);
        		$('#main-rune-4').html(csmr364);
        	}else if(csmrSize==39) {
        		const csmr391 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[0]%></b></br></br><%=championSummaryMainRune[2]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[4]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[3]%></b></br></br><%=championSummaryMainRune[5]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[7]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[6]%></b></br></br><%=championSummaryMainRune[8]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[10]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[9]%></b></br></br><%=championSummaryMainRune[11]%></span></div>";
        		const csmr392 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[13]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[12]%></b></br></br><%=championSummaryMainRune[14]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[16]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[15]%></b></br></br><%=championSummaryMainRune[17]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[19]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[18]%></b></br></br><%=championSummaryMainRune[20]%></span></div>";
        		const csmr393 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[22]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[21]%></b></br></br><%=championSummaryMainRune[23]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[25]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[24]%></b></br></br><%=championSummaryMainRune[26]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[28]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[27]%></b></br></br><%=championSummaryMainRune[29]%></span></div>";
        		const csmr394 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[31]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[30]%></b></br></br><%=championSummaryMainRune[32]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[34]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[33]%></b></br></br><%=championSummaryMainRune[35]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[37]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[36]%></b></br></br><%=championSummaryMainRune[38]%></span></div>";
        		$('#main-rune-1').html(csmr391);
        		$('#main-rune-2').html(csmr392);
        		$('#main-rune-3').html(csmr393);
        		$('#main-rune-4').html(csmr394);
        	}else if(csmrSize==42){
        		const csmr421 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[0]%></b></br></br><%=championSummaryMainRune[2]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[4]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[3]%></b></br></br><%=championSummaryMainRune[5]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[7]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[6]%></b></br></br><%=championSummaryMainRune[8]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[10]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[9]%></b></br></br><%=championSummaryMainRune[11]%></span></div>";
        		const csmr422 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[13]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[12]%></b></br></br><%=championSummaryMainRune[14]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[16]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[15]%></b></br></br><%=championSummaryMainRune[17]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[19]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[18]%></b></br></br><%=championSummaryMainRune[20]%></span></div>";
        		const csmr423 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[22]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[21]%></b></br></br><%=championSummaryMainRune[23]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[25]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[24]%></b></br></br><%=championSummaryMainRune[26]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[28]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[27]%></b></br></br><%=championSummaryMainRune[29]%></span></div>";
        		const csmr424 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[31]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[30]%></b></br></br><%=championSummaryMainRune[32]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[34]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[33]%></b></br></br><%=championSummaryMainRune[35]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[37]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[36]%></b></br></br><%=championSummaryMainRune[38]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune[40]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune[39]%></b></br></br><%=championSummaryMainRune[41]%></span></div>";
        		$('#main-rune-1').html(csmr421);
        		$('#main-rune-2').html(csmr422);
        		$('#main-rune-3').html(csmr423);
        		$('#main-rune-4').html(csmr424);
        	}
    		/*pick end*/
    		/*win*/
    		const csmrSize2 = <%=champion.championSummaryMainRune(conn, pstmt, rs, champion_name, champion_line, champion_rate2).size()%>;
    		if(csmrSize2==36) {
    			const csmr361 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[0]%></b></br></br><%=championSummaryMainRune2[2]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[4]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[3]%></b></br></br><%=championSummaryMainRune2[5]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[7]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[6]%></b></br></br><%=championSummaryMainRune2[8]%></span></div>";
        		const csmr362 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[10]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[9]%></b></br></br><%=championSummaryMainRune2[11]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[13]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[12]%></b></br></br><%=championSummaryMainRune2[14]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[16]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[15]%></b></br></br><%=championSummaryMainRune2[17]%></span></div>";
        		const csmr363 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[19]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[18]%></b></br></br><%=championSummaryMainRune2[20]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[22]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[21]%></b></br></br><%=championSummaryMainRune2[23]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[25]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[24]%></b></br></br><%=championSummaryMainRune2[26]%></span></div>";
        		const csmr364 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[28]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[27]%></b></br></br><%=championSummaryMainRune2[29]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[31]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[30]%></b></br></br><%=championSummaryMainRune2[32]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[34]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[33]%></b></br></br><%=championSummaryMainRune2[35]%></span></div>";
        		$('#main-rune-12').html(csmr361);
        		$('#main-rune-22').html(csmr362);
        		$('#main-rune-32').html(csmr363);
        		$('#main-rune-42').html(csmr364);
        	}else if(csmrSize2==39) {
        		const csmr391 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[0]%></b></br></br><%=championSummaryMainRune2[2]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[4]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[3]%></b></br></br><%=championSummaryMainRune2[5]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[7]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[6]%></b></br></br><%=championSummaryMainRune2[8]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[10]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[9]%></b></br></br><%=championSummaryMainRune2[11]%></span></div>";
        		const csmr392 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[13]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[12]%></b></br></br><%=championSummaryMainRune2[14]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[16]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[15]%></b></br></br><%=championSummaryMainRune2[17]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[19]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[18]%></b></br></br><%=championSummaryMainRune2[20]%></span></div>";
        		const csmr393 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[22]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[21]%></b></br></br><%=championSummaryMainRune2[23]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[25]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[24]%></b></br></br><%=championSummaryMainRune2[26]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[28]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[27]%></b></br></br><%=championSummaryMainRune2[29]%></span></div>";
        		const csmr394 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[31]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[30]%></b></br></br><%=championSummaryMainRune2[32]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[34]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[33]%></b></br></br><%=championSummaryMainRune2[35]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[37]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[36]%></b></br></br><%=championSummaryMainRune2[38]%></span></div>";
        		$('#main-rune-12').html(csmr391);
        		$('#main-rune-22').html(csmr392);
        		$('#main-rune-32').html(csmr393);
        		$('#main-rune-42').html(csmr394);
        	}else if(csmrSize2==42){
        		const csmr421 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[0]%></b></br></br><%=championSummaryMainRune2[2]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[4]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[3]%></b></br></br><%=championSummaryMainRune2[5]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[7]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[6]%></b></br></br><%=championSummaryMainRune2[8]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[10]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[9]%></b></br></br><%=championSummaryMainRune2[11]%></span></div>";
        		const csmr422 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[13]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[12]%></b></br></br><%=championSummaryMainRune2[14]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[16]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[15]%></b></br></br><%=championSummaryMainRune2[17]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[19]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[18]%></b></br></br><%=championSummaryMainRune2[20]%></span></div>";
        		const csmr423 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[22]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[21]%></b></br></br><%=championSummaryMainRune2[23]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[25]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[24]%></b></br></br><%=championSummaryMainRune2[26]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[28]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[27]%></b></br></br><%=championSummaryMainRune2[29]%></span></div>";
        		const csmr424 = "<div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[31]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[30]%></b></br></br><%=championSummaryMainRune2[32]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[34]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[33]%></b></br></br><%=championSummaryMainRune2[35]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[37]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[36]%></b></br></br><%=championSummaryMainRune2[38]%></span></div><div class='tooltip'><img src='Images/rune/<%=championSummaryMainRune2[40]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryMainRune2[39]%></b></br></br><%=championSummaryMainRune2[41]%></span></div>";
        		$('#main-rune-12').html(csmr421);
        		$('#main-rune-22').html(csmr422);
        		$('#main-rune-32').html(csmr423);
        		$('#main-rune-42').html(csmr424);
        	}
    		/*win end*/
    		/*pick*/
    		const csieSize1 = <%=champion.championSummaryItemEach1(conn, pstmt, rs, champion_name, champion_line, champion_rate).size()%>;
    		const csieSize2 = <%=champion.championSummaryItemEach2(conn, pstmt, rs, champion_name, champion_line, champion_rate).size()%>;
    		const csieSize3 = <%=champion.championSummaryItemEach3(conn, pstmt, rs, champion_name, champion_line, champion_rate).size()%>;
    		if(csieSize1==3){
    			const csie11 = "1코어 <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach1[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach1[0]%></b></br></br><%=championSummaryItemEach1[2]%></span></span>";
    			$('#item-core-summary1').html(csie11);
    		}else if(csieSize1==6) {
    			const csie12 = "1코어 <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach1[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach1[0]%></b></br></br><%=championSummaryItemEach1[2]%></span></span> or <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach1[4]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach1[3]%></b></br></br><%=championSummaryItemEach1[5]%></span></span>";
    			$('#itme-core-summary1').html(csie12);
    		}else if(csieSize1==9) {
    			const csie13 = "1코어 <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach1[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach1[0]%></b></br></br><%=championSummaryItemEach1[2]%></span></span> or <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach1[4]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach1[3]%></b></br></br><%=championSummaryItemEach1[5]%></span></span> or <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach1[7]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach1[6]%></b></br></br><%=championSummaryItemEach1[8]%></span></span>";
    			$('#itme-core-summary1').html(csie13);
    		}
    		if(csieSize2==3){
    			const csie21 = "2코어 <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach2[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach2[0]%></b></br></br><%=championSummaryItemEach2[2]%></span></span>";
    			$('#item-core-summary2').html(csie21);
    		}else if(csieSize2==6) {
    			const csie22 = "2코어 <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach2[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach2[0]%></b></br></br><%=championSummaryItemEach2[2]%></span></span> or <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach2[4]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach2[3]%></b></br></br><%=championSummaryItemEach2[5]%></span></span>";
    			$('#itme-core-summary2').html(csie22);
    		}else if(csieSize2==9) {
    			const csie23 = "2코어 <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach2[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach2[0]%></b></br></br><%=championSummaryItemEach2[2]%></span></span> or <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach2[4]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach2[3]%></b></br></br><%=championSummaryItemEach2[5]%></span></span> or <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach2[7]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach2[6]%></b></br></br><%=championSummaryItemEach2[8]%></span></span>";
    			$('#itme-core-summary2').html(csie23);
    		}
    		if(csieSize3==3){
    			const csie31 = "3코어 <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach3[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach3[0]%></b></br></br><%=championSummaryItemEach3[2]%></span></span>";
    			$('#item-core-summary3').html(csie31);
    		}else if(csieSize3==6) {
    			const csie32 = "3코어 <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach3[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach3[0]%></b></br></br><%=championSummaryItemEach3[2]%></span></span> or <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach3[4]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach3[3]%></b></br></br><%=championSummaryItemEach3[5]%></span></span>";
    			$('#itme-core-summary3').html(csie32);
    		}else if(csieSize3==9) {
    			const csie33 = "3코어 <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach3[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach3[0]%></b></br></br><%=championSummaryItemEach3[2]%></span></span> or <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach3[4]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach3[3]%></b></br></br><%=championSummaryItemEach3[5]%></span></span> or <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach3[7]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach3[6]%></b></br></br><%=championSummaryItemEach3[8]%></span></span>";
    			$('#itme-core-summary3').html(csie33);
    		}
    		/*pick end*/
    		/*win*/
    		const csieSize12 = <%=champion.championSummaryItemEach1(conn, pstmt, rs, champion_name, champion_line, champion_rate2).size()%>;
    		const csieSize22 = <%=champion.championSummaryItemEach2(conn, pstmt, rs, champion_name, champion_line, champion_rate2).size()%>;
    		const csieSize32 = <%=champion.championSummaryItemEach3(conn, pstmt, rs, champion_name, champion_line, champion_rate2).size()%>;
    		if(csieSize12==3){
    			const csie11 = "1코어 <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach12[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach12[0]%></b></br></br><%=championSummaryItemEach12[2]%></span></span>";
    			$('#item-core-summary12').html(csie11);
    		}else if(csieSize12==6) {
    			const csie12 = "1코어 <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach12[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach12[0]%></b></br></br><%=championSummaryItemEach12[2]%></span></span> or <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach12[4]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach12[3]%></b></br></br><%=championSummaryItemEach12[5]%></span></span>";
    			$('#itme-core-summary12').html(csie12);
    		}else if(csieSize12==9) {
    			const csie13 = "1코어 <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach12[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach12[0]%></b></br></br><%=championSummaryItemEach12[2]%></span></span> or <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach12[4]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach12[3]%></b></br></br><%=championSummaryItemEach12[5]%></span></span> or <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach12[7]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach12[6]%></b></br></br><%=championSummaryItemEach12[8]%></span></span>";
    			$('#itme-core-summary12').html(csie13);
    		}
    		if(csieSize22==3){
    			const csie21 = "2코어 <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach22[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach22[0]%></b></br></br><%=championSummaryItemEach22[2]%></span></span>";
    			$('#item-core-summary22').html(csie21);
    		}else if(csieSize22==6) {
    			const csie22 = "2코어 <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach22[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach22[0]%></b></br></br><%=championSummaryItemEach22[2]%></span></span> or <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach22[4]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach22[3]%></b></br></br><%=championSummaryItemEach22[5]%></span></span>";
    			$('#itme-core-summary22').html(csie22);
    		}else if(csieSize22==9) {
    			const csie23 = "2코어 <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach22[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach22[0]%></b></br></br><%=championSummaryItemEach22[2]%></span></span> or <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach22[4]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach22[3]%></b></br></br><%=championSummaryItemEach22[5]%></span></span> or <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach22[7]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach22[6]%></b></br></br><%=championSummaryItemEach22[8]%></span></span>";
    			$('#itme-core-summary22').html(csie23);
    		}
    		if(csieSize32==3){
    			const csie31 = "3코어 <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach32[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach32[0]%></b></br></br><%=championSummaryItemEach32[2]%></span></span>";
    			$('#item-core-summary32').html(csie31);
    		}else if(csieSize32==6) {
    			const csie32 = "3코어 <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach32[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach32[0]%></b></br></br><%=championSummaryItemEach32[2]%></span></span> or <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach32[4]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach32[3]%></b></br></br><%=championSummaryItemEach32[5]%></span></span>";
    			$('#itme-core-summary32').html(csie32);
    		}else if(csieSize32==9) {
    			const csie33 = "3코어 <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach32[1]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach32[0]%></b></br></br><%=championSummaryItemEach32[2]%></span></span> or <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach32[4]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach32[3]%></b></br></br><%=championSummaryItemEach32[5]%></span></span> or <span class='tooltip'><img src='Images/item/<%=championSummaryItemEach32[7]%>' alt='img'><span class='tooltiptext tooltip-right'><b style='color:#ffc107;'><%=championSummaryItemEach32[6]%></b></br></br><%=championSummaryItemEach32[8]%></span></span>";
    			$('#itme-core-summary32').html(csie33);
    		}
    		/*win end*/
    		/*pick*/
    		<%
            for(int i=0; i<csylist.size(); i++) {
                	if(csylist.get(i).getSkill().equals("Q")) {
                		%>
               				$('#csylistQ').append("<div class='skill-box' style='background-color: #ffc030;'><%=i+1%></div>");
               				$('#csylistW').append("<div class='skill-box'><%=i+1%></div>");
               				$('#csylistE').append("<div class='skill-box'><%=i+1%></div>");
               				$('#csylistR').append("<div class='skill-box'><%=i+1%></div>");
               			<%
                	} else if(csylist.get(i).getSkill().equals("W")) {
                		%>
               				$('#csylistQ').append("<div class='skill-box'><%=i+1%></div>");
               				$('#csylistW').append("<div class='skill-box' style='background-color: #ffc030;'><%=i+1%></div>");
               				$('#csylistE').append("<div class='skill-box'><%=i+1%></div>");
               				$('#csylistR').append("<div class='skill-box'><%=i+1%></div>");
                		<%
                	} else if(csylist.get(i).getSkill().equals("E")) {
                		%>
               				$('#csylistQ').append("<div class='skill-box'><%=i+1%></div>");
               				$('#csylistW').append("<div class='skill-box'><%=i+1%></div>");
               				$('#csylistE').append("<div class='skill-box' style='background-color: #ffc030;'><%=i+1%></div>");
               				$('#csylistR').append("<div class='skill-box'><%=i+1%></div>");
                		<%
                	} else if(csylist.get(i).getSkill().equals("R")) {
                		%>
               				$('#csylistQ').append("<div class='skill-box'><%=i+1%></div>");
               				$('#csylistW').append("<div class='skill-box'><%=i+1%></div>");
               				$('#csylistE').append("<div class='skill-box'><%=i+1%></div>");
               				$('#csylistR').append("<div class='skill-box' style='background-color: #ffc030;'><%=i+1%></div>");
           				<% 
       				}
            	}
			%>
			/*pick end*/
			/*win*/
			<%
            for(int i=0; i<csylist2.size(); i++) {
                	if(csylist2.get(i).getSkill().equals("Q")) {
                		%>
               				$('#csylistQ2').append("<div class='skill-box' style='background-color: #ffc030;'><%=i+1%></div>");
               				$('#csylistW2').append("<div class='skill-box'><%=i+1%></div>");
               				$('#csylistE2').append("<div class='skill-box'><%=i+1%></div>");
               				$('#csylistR2').append("<div class='skill-box'><%=i+1%></div>");
               			<%
                	} else if(csylist2.get(i).getSkill().equals("W")) {
                		%>
               				$('#csylistQ2').append("<div class='skill-box'><%=i+1%></div>");
               				$('#csylistW2').append("<div class='skill-box' style='background-color: #ffc030;'><%=i+1%></div>");
               				$('#csylistE2').append("<div class='skill-box'><%=i+1%></div>");
               				$('#csylistR2').append("<div class='skill-box'><%=i+1%></div>");
                		<%
                	} else if(csylist2.get(i).getSkill().equals("E")) {
                		%>
               				$('#csylistQ2').append("<div class='skill-box'><%=i+1%></div>");
               				$('#csylistW2').append("<div class='skill-box'><%=i+1%></div>");
               				$('#csylistE2').append("<div class='skill-box' style='background-color: #ffc030;'><%=i+1%></div>");
               				$('#csylistR2').append("<div class='skill-box'><%=i+1%></div>");
                		<%
                	} else if(csylist2.get(i).getSkill().equals("R")) {
                		%>
               				$('#csylistQ2').append("<div class='skill-box'><%=i+1%></div>");
               				$('#csylistW2').append("<div class='skill-box'><%=i+1%></div>");
               				$('#csylistE2').append("<div class='skill-box'><%=i+1%></div>");
               				$('#csylistR2').append("<div class='skill-box' style='background-color: #ffc030;'><%=i+1%></div>");
           				<% 
       				}
            	}
			%>
			/*win end*/
    	});
	</script>
</head>

<body class="statistics-main-body" style="height: auto;">
	<header class="header-mainnav">
		<div class="header-container">
			<a href="main.jsp"> <img src="Images/header-logo.webp"
				alt="LOL.PS">
			</a>
			<div class="nav-item-container">
				<a class="nav-items" href="notice/notice.html">공지사항</a> <a
					class="nav-items"  href="ChampRank.jsp">챔피언 랭킹</a> <a
					class="nav-items" href="community/build.html">빌드게시판</a> <a
					class="nav-items" href="community/free.html">자유게시판</a>
			</div>
			<div class="sign-login">
				<%
            		if(session.getAttribute("memberInfo") == null) {
            			
           	 	%>
		                <a class="signin" href="signin.jsp">회원가입</a>
		                <a class="login" href="login.jsp">로그인</a>           	 		
           	 	<% 
            		}
            		else {
            	%>
            			<form action= "MypageServlet" >
            				<input class="signin" type="submit" value="마이페이지"/>
            			</form>
		                <form action = "LogoutServlet" >
		                	<input class="login" type="submit" value="로그아웃"/>
	                	</form>  
            	<%
            		}
            	%>
			</div>
		</div>
	</header>

	<div class="main" id="yoyack"></div>

	<div class="main-content-container">
		<div class="menu-scroll">
			<div class="category">
				<div class="category-item">
					<a href="#yoyack">요약</a> <a href="#counter">카운터</a> <a href="#spell-startitem">스펠,아이템</a> 
					<a href="#coreitem">코어템</a> <a href="#skills">스킬</a> <a href="#runes">룬</a>
				</div>
			</div>
			<div class="container">
				<div class="line-and-input">
					<div class="select-line">
						<a href="statistics.jsp?name=<%=champion_name%>&line=탑">
							<button class="line-button"
								style="border-radius: 6px 0px 0px 6px;">
								<img src="Images/icon/line-top.png" alt="img" /> <span>탑</span> 
								<span><%=championLineSelectPer[0]%>%</span>
							</button>
						</a>
						<a href="statistics.jsp?name=<%=champion_name%>&line=정글">
							<button class="line-button">
								<img src="Images/icon/line-jun.png" alt="img" /> <span>정글</span>
								<span><%=championLineSelectPer[1]%>%</span>
							</button>
						</a>
						<a href="statistics.jsp?name=<%=champion_name%>&line=미드">
							<button class="line-button">
								<img src="Images/icon/line-mid.png" alt="img" /> <span>미드</span>
								<span><%=championLineSelectPer[2]%>%</span>
							</button>
						</a>
						<a href="statistics.jsp?name=<%=champion_name%>&line=원딜">
							<button class="line-button">
								<img src="Images/icon/line-bot.png" alt="img" /> <span>원딜</span>
								<span><%=championLineSelectPer[3]%>%</span>
							</button>
						</a>
						<a href="statistics.jsp?name=<%=champion_name%>&line=서폿">
							<button class="line-button"
								style="border-radius: 0px 6px 6px 0px; border-right: none;">
								<img src="Images/icon/line-sup.png" alt="img" /> <span>서폿</span>
								<span><%=championLineSelectPer[4]%>%</span>
							</button>
						</a>
					</div>
					<div class="input-box">
						<form action="../MainStatisticsServlet" method="get" id="search_form" autocomplete="off">
							<input class="main-input" type="text" name="name" placeholder="챔피언 이름을 입력하세요">
						</form>
					</div>
					<div style="clear: both;"></div>
				</div>
			</div>
		</div>
		
		
		<div class="statistics-continer">
			<div class="champ-summary-header">
				<div class="champ-head">
					<div class="head">
						<img src="Images/champion/head/<%=championImg%>" alt="img" />
					</div>
					<h1 style="font-weight: 400; font-size: 38px; margin: 0; width: 100%; margin-top: 15px; margin-left: 190px;"><%=championName%></h1>
				</div>
				<div class="counter-champ">
                    <div class="counter-text">카운터</div>
                    <%
                    	int matchHardCnt = 0;
                    	if(matchHard.size()>5) {
                    		matchHardCnt = 5;
                    	} else { matchHardCnt = matchHard.size(); }
                    	
               			for(int i=0; i<matchHardCnt; i++) {
               				if(matchHard.get(0).getName()==null) {
               					%>
               						<span></span>
               					<%
               				} else {
               				
		                    %>
		                    <div>
		                        <img class="counter-img" src="Images/champion/head/<%=matchHard.get(i).getImage()%>" /> <br/>
		                        <span class="counter-rate"><%=matchHard.get(i).getWinRate()%>%</span>
		                    </div>
		                    <%
                    		}
               			}
                    %>
                </div>
				<button class="statistics-summary-button pick-build-btn statistics-summary-button-active">대중적인 빌드</button>
				<button class="statistics-summary-button win-build-btn" style="left: 33%;">고승률 빌드</button>
			</div>
			<div class="champ-summary">
			</div>
			<!--pick-->
			<div class="champ-summary" id="pick-build">
				<div class="rate-content">

					<div class="number-box">
						<div style="padding-top: 10px; width: 80px;">
							<div class="rate yellow">승률</div>
							<div class="rate"><%=championSummaryWin_rate%>%
							</div>
						</div>

						<div style="padding-top: 10px; width: 80px;">
							<div class="rate yellow">픽률</div>
							<div class="rate"><%=championSummaryPick_rate%>%
							</div>
						</div>

						<div style="padding-top: 10px; width: 80px;">
							<div class="rate yellow">벤율</div>
							<div class="rate"><%=championSummaryBan_rate%>%
							</div>
						</div>

						<div style="padding-top: 10px; width: 165px;">
							<div class="rate yellow">PS스코어</div>
							<div class="rate" style="color: #FFFFFFA6;">
								12.9 패치
								<%=championSummaryPsRankBefore%></div>
							<div class="rate">
								12.10 패치
								<%=championSummaryPsRankNow%></div>
						</div>
						<div style="padding-top: 10px; width: 140px;">
							<div class="rate yellow">챔피언순위</div>
							<div class="rate" style="color: #FFFFFFA6;">
								12.9
								<%=championSummaryRankingBefore%></div>
							<div class="rate">
								12.10
								<%=championSummaryRankingNow%></div>
						</div>
						<div
							style="padding-top: 10px; width: 230px; display: flex; flex-wrap: wrap;">
							<div class="rate yellow">주로 선택하는 포지션</div>
							<div class="rate" style="width: 33.3%;">
								<p><%=championLineHighNamePer[0]%></p>
								<span style="color: #FFFFFFA6;"><%=championLineHighNamePer[1]%>%</span>
							</div>
							<div class="rate" style="width: 33.3%;">
								<p><%=championLineHighNamePer[2]%></p>
								<span style="color: #FFFFFFA6;"><%=championLineHighNamePer[3]%>%</span>
							</div>
							<div class="rate" style="width: 33.3%;">
								<p><%=championLineHighNamePer[4]%></p>
								<span style="color: #FFFFFFA6;"><%=championLineHighNamePer[5]%>%</span>
							</div>
						</div>

					</div>
				</div>

				<div class="rune-item-skill-container">
					<div class="main-rune">
						<div class="rune-summary">
							<h4 style="margin: 0; color: #ae9056">메인룬</h4>
							<div class="rune-select" id="main-rune-1"
								style="margin-top: 5px;"></div>
							<div class="rune-select" id="main-rune-2"></div>
							<div class="rune-select" id="main-rune-3"></div>
							<div class="rune-select" id="main-rune-4"></div>
							<div class="rune-select" style="margin-top: 30px;">
								<span style="color: #FFFFFFA6; font-size: 12px;">승률 <span
									class="yellow"><%=championSummaryWin_rate%>%</span></span>
							</div>
							<div class="rune-select">
								<span style="color: #FFFFFFA6; font-size: 12px;">게임 수 <span
									class="yellow"><%=championSummaryCount%></span></span>
							</div>
						</div>
					</div>

					<div class="sub-rune">
						<div class="rune-summary">
							<h4 style="margin: 0; color: #ae9056">보조 룬</h4>
							
							<div class="rune-select" style="margin-top: 5px;">
								<div class='tooltip'>
									<img src="Images/rune/<%=championSummaryAssisRune[1]%>"
										alt="img" /> <span class='tooltiptext tooltip-right'><b
										style='color: #ffc107;'> <%=championSummaryAssisRune[0]%></b><br />
									<br /><%=championSummaryAssisRune[2]%> </span>
								</div>
								<div class='tooltip'>
									<img src="Images/rune/<%=championSummaryAssisRune[4]%>"
										alt="img" /> <span class='tooltiptext tooltip-right'><b
										style='color: #ffc107;'> <%=championSummaryAssisRune[3]%></b><br />
									<br /><%=championSummaryAssisRune[5]%> </span>
								</div>
								<div class='tooltip'>
									<img src="Images/rune/<%=championSummaryAssisRune[7]%>"
										alt="img" /> <span class='tooltiptext tooltip-right'><b
										style='color: #ffc107;'> <%=championSummaryAssisRune[6]%></b><br />
									<br /><%=championSummaryAssisRune[8]%> </span>
								</div>
							</div>
							
							<div class="rune-select">
								<div class='tooltip'>
									<img src="Images/rune/<%=championSummaryAssisRune[10]%>"
										alt="img" /> <span class='tooltiptext tooltip-right'><b
										style='color: #ffc107;'> <%=championSummaryAssisRune[9]%></b><br />
									<br /><%=championSummaryAssisRune[11]%> </span>
								</div>
								<div class='tooltip'>
									<img src="Images/rune/<%=championSummaryAssisRune[13]%>"
										alt="img" /> <span class='tooltiptext tooltip-right'><b
										style='color: #ffc107;'> <%=championSummaryAssisRune[12]%></b><br />
									<br /><%=championSummaryAssisRune[14]%> </span>
								</div>
								<div class='tooltip'>
									<img src="Images/rune/<%=championSummaryAssisRune[16]%>"
										alt="img" /> <span class='tooltiptext tooltip-right'><b
										style='color: #ffc107;'> <%=championSummaryAssisRune[15]%></b><br />
									<br /><%=championSummaryAssisRune[17]%> </span>
								</div>
							</div>
							
							<div class="rune-select">
								<div class='tooltip'>
									<img src="Images/rune/<%=championSummaryAssisRune[19]%>"
										alt="img" /> <span class='tooltiptext tooltip-right'><b
										style='color: #ffc107;'> <%=championSummaryAssisRune[18]%></b><br />
									<br /><%=championSummaryAssisRune[20]%> </span>
								</div>
								<div class='tooltip'>
									<img src="Images/rune/<%=championSummaryAssisRune[22]%>"
										alt="img" /> <span class='tooltiptext tooltip-right'><b
										style='color: #ffc107;'> <%=championSummaryAssisRune[21]%></b><br />
									<br /><%=championSummaryAssisRune[23]%> </span>
								</div>
								<div class='tooltip'>
									<img src="Images/rune/<%=championSummaryAssisRune[25]%>"
										alt="img" /> <span class='tooltiptext tooltip-right'><b
										style='color: #ffc107;'> <%=championSummaryAssisRune[24]%></b><br />
									<br /><%=championSummaryAssisRune[26]%> </span>
								</div>
							</div>

							<div style="padding-top: 30px;">
								<div class="rune-select">
									<img style="width: 25px; height: 25px"
										src="Images/rune/<%=championSummarySubRune[1]%>" alt="img" />
									<img style="width: 25px; height: 25px"
										src="Images/rune/<%=championSummarySubRune[3]%>" alt="img" />
									<img style="width: 25px; height: 25px"
										src="Images/rune/<%=championSummarySubRune[5]%>" alt="img" />
								</div>
								<div class="rune-select">
									<img style="width: 25px; height: 25px"
										src="Images/rune/<%=championSummarySubRune[7]%>" alt="img" />
									<img style="width: 25px; height: 25px"
										src="Images/rune/<%=championSummarySubRune[9]%>" alt="img" />
									<img style="width: 25px; height: 25px"
										src="Images/rune/<%=championSummarySubRune[11]%>" alt="img" />
								</div>
								<div class="rune-select">
									<img style="width: 25px; height: 25px"
										src="Images/rune/<%=championSummarySubRune[13]%>" alt="img" />
									<img style="width: 25px; height: 25px"
										src="Images/rune/<%=championSummarySubRune[15]%>" alt="img" />
									<img style="width: 25px; height: 25px"
										src="Images/rune/<%=championSummarySubRune[17]%>" alt="img" />
								</div>
							</div>
							
						</div>
					</div>

					<div class="item-skill-summary">
						<div class="item-core-summary">
							<div id="itme-core-summary1" class="item-core-summary-items">
								1코어</div>
							<div id="itme-core-summary2" class="item-core-summary-items">
								2코어</div>
							<div id="itme-core-summary3" class="item-core-summary-items">
								3코어</div>
						</div>

						<div class="skill-summary-container">
							<div class="skill-summary">
								<div class="skill-seq-row">
									<div class="skill-title">Q</div>
									<img style="width: 60px; margin-right: 4px;"
										src="Images/skill/<%=csilist.get(0).getQ()%>" alt="img" />
									<div id="csylistQ" style="display: flex;"></div>
								</div>

								<div class="skill-seq-row">
									<div class="skill-title">W</div>
									<img style="width: 60px; margin-right: 4px;"
										src="Images/skill/<%=csilist.get(0).getW()%>" alt="img" />
									<div id="csylistW" style="display: flex;"></div>
								</div>

								<div class="skill-seq-row">
									<div class="skill-title">E</div>
									<img style="width: 60px; margin-right: 4px;"
										src="Images/skill/<%=csilist.get(0).getE()%>" alt="img" />
									<div id="csylistE" style="display: flex;"></div>
								</div>

								<div class="skill-seq-row">
									<div class="skill-title">R</div>
									<img style="width: 60px; margin-right: 4px;"
										src="Images/skill/<%=csilist.get(0).getR()%>" alt="img" />
									<div id="csylistR" style="display: flex;"></div>
								</div>
							</div>
						</div>

						<div class="skill-mastar-summary">
							<div class="good-skill-master">
								<p>스킬 마스터 순서</p>
								<%
									for (int i = 0; i < 3; i++) {
								%>
								<span class='tooltip'> <img
									src='Images/skill/<%=gsmlist.get(i).getImage()%>' alt='img' />
									<span class='tooltiptext tooltip-right'> <b
										style='color: #ffc107;'><%=gsmlist.get(i).getName()%></b><br />
									<br /><%=gsmlist.get(i).getFunction()%>
								</span>
								</span>
								<%
									}
								%>
							</div>
							<div class="good-spell">
								<p>추천 스펠</p>
								<%
									for (int i = 0; i < reslist.size(); i++) {
								%>
								<span class='tooltip'> <img
									src='Images/spell/<%=reslist.get(i).getImage()%>' alt='img' />
									<span class='tooltiptext tooltip-right'> <b
										style='color: #ffc107;'><%=reslist.get(i).getName()%></b><br />
									<br /><%=reslist.get(i).getFunction()%>
								</span>
								</span>
								<%
									}
								%>
							</div>
							<div class="good-start-item">
								<p>시작아이템</p>
								<%
									for (int i = 0; i < stilist.size(); i++) {
								%>
								<span class='tooltip'> <img
									src='Images/item/<%=stilist.get(i).getImage()%>' alt='img' /> <span
									class='tooltiptext tooltip-right'> <b
										style='color: #ffc107;'><%=stilist.get(i).getName()%></b><br />
									<br /><%=stilist.get(i).getFunction()%>
								</span>
								</span>
								<%
									}
								%>
							</div>
							<div class="good-shoes">
								<p>신발</p>
								<%
									for (int i = 0; i < shoeslist.size(); i++) {
								%>
								<span class='tooltip'> <img
									src='Images/item/<%=shoeslist.get(i).getImage()%>' alt='img' />
									<span class='tooltiptext tooltip-right'> <b
										style='color: #ffc107;'><%=shoeslist.get(i).getName()%></b><br />
									<br /><%=shoeslist.get(i).getFunction()%>
								</span>
								</span>
								<%
									}
								%>
							</div>
							<div class="good-core">
								<p>코어템</p>
								<%
									for (int i = 0; i < 1; i++) {
										if(coreCombine4.size()==0) {
											%>
												<span></span>
											<%
										} else {
								%>
								<span class='tooltip'> <img
									src='Images/item/<%=coreCombine4.get(i).getImage1()%>' alt='img' /> <span
									class='tooltiptext tooltip-right'> <b
										style='color: #ffc107;'><%=coreCombine4.get(i).getPick1()%></b><br />
									<br /><%=coreCombine4.get(i).getFunction1()%>
								</span>
								</span>
								<span class='tooltip'> <img
									src='Images/item/<%=coreCombine4.get(i).getImage2()%>' alt='img' /> <span
									class='tooltiptext tooltip-right'> <b
										style='color: #ffc107;'><%=coreCombine4.get(i).getPick2()%></b><br />
									<br /><%=coreCombine4.get(i).getFunction2()%>
								</span>
								</span>
								<span class='tooltip'> <img
									src='Images/item/<%=coreCombine4.get(i).getImage3()%>' alt='img' /> <span
									class='tooltiptext tooltip-right'> <b
										style='color: #ffc107;'><%=coreCombine4.get(i).getPick3()%></b><br />
									<br /><%=coreCombine4.get(i).getFunction3()%>
								</span>
								</span>
								<span class='tooltip'> <img
									src='Images/item/<%=coreCombine4.get(i).getImage4()%>' alt='img' /> <span
									class='tooltiptext tooltip-right'> <b
										style='color: #ffc107;'><%=coreCombine4.get(i).getPick4()%></b><br />
									<br /><%=coreCombine4.get(i).getFunction4()%>
								</span>
								</span>
								<%
										}
									}
								%>
							</div>
						</div>
					</div>
				</div>
			</div>
		<!--여기까지pick-->
		<!--win-->
			<div class="champ-summary display-none" id="win-build" style="opacity:0;">
				<div class="rate-content">

					<div class="number-box">
						<div style="padding-top: 10px; width: 80px;">
							<div class="rate yellow">승률</div>
							<div class="rate"><%=championSummaryWin_rate%>%
							</div>
						</div>

						<div style="padding-top: 10px; width: 80px;">
							<div class="rate yellow">픽률</div>
							<div class="rate"><%=championSummaryPick_rate%>%
							</div>
						</div>

						<div style="padding-top: 10px; width: 80px;">
							<div class="rate yellow">벤율</div>
							<div class="rate"><%=championSummaryBan_rate%>%
							</div>
						</div>

						<div style="padding-top: 10px; width: 165px;">
							<div class="rate yellow">PS스코어</div>
							<div class="rate" style="color: #FFFFFFA6;">
								12.9 패치
								<%=championSummaryPsRankBefore%></div>
							<div class="rate">
								12.10 패치
								<%=championSummaryPsRankNow%></div>
						</div>
						<div style="padding-top: 10px; width: 140px;">
							<div class="rate yellow">챔피언순위</div>
							<div class="rate" style="color: #FFFFFFA6;">
								12.9
								<%=championSummaryRankingBefore%></div>
							<div class="rate">
								12.10
								<%=championSummaryRankingNow%></div>
						</div>
						<div
							style="padding-top: 10px; width: 230px; display: flex; flex-wrap: wrap;">
							<div class="rate yellow">주로 선택하는 포지션</div>
							<div class="rate" style="width: 33.3%;">
								<p><%=championLineHighNamePer[0]%></p>
								<span style="color: #FFFFFFA6;"><%=championLineHighNamePer[1]%>%</span>
							</div>
							<div class="rate" style="width: 33.3%;">
								<p><%=championLineHighNamePer[2]%></p>
								<span style="color: #FFFFFFA6;"><%=championLineHighNamePer[3]%>%</span>
							</div>
							<div class="rate" style="width: 33.3%;">
								<p><%=championLineHighNamePer[4]%></p>
								<span style="color: #FFFFFFA6;"><%=championLineHighNamePer[5]%>%</span>
							</div>
						</div>

					</div>
				</div>

				<div class="rune-item-skill-container">
					<div class="main-rune">
						<div class="rune-summary">
							<h4 style="margin: 0; color: #ae9056">메인룬</h4>
							<div class="rune-select" id="main-rune-12"
								style="margin-top: 5px;"></div>
							<div class="rune-select" id="main-rune-22"></div>
							<div class="rune-select" id="main-rune-32"></div>
							<div class="rune-select" id="main-rune-42"></div>
							<div class="rune-select" style="margin-top: 30px;">
								<span style="color: #FFFFFFA6; font-size: 12px;">승률 <span
									class="yellow"><%=championSummaryWin_rate%>%</span></span>
							</div>
							<div class="rune-select">
								<span style="color: #FFFFFFA6; font-size: 12px;">게임 수 <span
									class="yellow"><%=championSummaryCount%></span></span>
							</div>
						</div>
					</div>

					<div class="sub-rune">
						<div class="rune-summary">
							<h4 style="margin: 0; color: #ae9056">보조 룬</h4>
							
							<div class="rune-select" style="margin-top: 5px;">
								<div class='tooltip'>
									<img src="Images/rune/<%=championSummaryAssisRune2[1]%>"
										alt="img" /> <span class='tooltiptext tooltip-right'><b
										style='color: #ffc107;'> <%=championSummaryAssisRune2[0]%></b><br />
									<br /><%=championSummaryAssisRune2[2]%> </span>
								</div>
								<div class='tooltip'>
									<img src="Images/rune/<%=championSummaryAssisRune2[4]%>"
										alt="img" /> <span class='tooltiptext tooltip-right'><b
										style='color: #ffc107;'> <%=championSummaryAssisRune2[3]%></b><br />
									<br /><%=championSummaryAssisRune2[5]%> </span>
								</div>
								<div class='tooltip'>
									<img src="Images/rune/<%=championSummaryAssisRune2[7]%>"
										alt="img" /> <span class='tooltiptext tooltip-right'><b
										style='color: #ffc107;'> <%=championSummaryAssisRune2[6]%></b><br />
									<br /><%=championSummaryAssisRune2[8]%> </span>
								</div>
							</div>
							
							<div class="rune-select">
								<div class='tooltip'>
									<img src="Images/rune/<%=championSummaryAssisRune2[10]%>"
										alt="img" /> <span class='tooltiptext tooltip-right'><b
										style='color: #ffc107;'> <%=championSummaryAssisRune2[9]%></b><br />
									<br /><%=championSummaryAssisRune2[11]%> </span>
								</div>
								<div class='tooltip'>
									<img src="Images/rune/<%=championSummaryAssisRune2[13]%>"
										alt="img" /> <span class='tooltiptext tooltip-right'><b
										style='color: #ffc107;'> <%=championSummaryAssisRune2[12]%></b><br />
									<br /><%=championSummaryAssisRune2[14]%> </span>
								</div>
								<div class='tooltip'>
									<img src="Images/rune/<%=championSummaryAssisRune2[16]%>"
										alt="img" /> <span class='tooltiptext tooltip-right'><b
										style='color: #ffc107;'> <%=championSummaryAssisRune2[15]%></b><br />
									<br /><%=championSummaryAssisRune2[17]%> </span>
								</div>
							</div>
							
							<div class="rune-select">
								<div class='tooltip'>
									<img src="Images/rune/<%=championSummaryAssisRune2[19]%>"
										alt="img" /> <span class='tooltiptext tooltip-right'><b
										style='color: #ffc107;'> <%=championSummaryAssisRune2[18]%></b><br />
									<br /><%=championSummaryAssisRune2[20]%> </span>
								</div>
								<div class='tooltip'>
									<img src="Images/rune/<%=championSummaryAssisRune2[22]%>"
										alt="img" /> <span class='tooltiptext tooltip-right'><b
										style='color: #ffc107;'> <%=championSummaryAssisRune2[21]%></b><br />
									<br /><%=championSummaryAssisRune2[23]%> </span>
								</div>
								<div class='tooltip'>
									<img src="Images/rune/<%=championSummaryAssisRune2[25]%>"
										alt="img" /> <span class='tooltiptext tooltip-right'><b
										style='color: #ffc107;'> <%=championSummaryAssisRune2[24]%></b><br />
									<br /><%=championSummaryAssisRune2[26]%> </span>
								</div>
							</div>

							<div style="padding-top: 30px;">
								<div class="rune-select">
									<img style="width: 25px; height: 25px"
										src="Images/rune/<%=championSummarySubRune2[1]%>" alt="img" />
									<img style="width: 25px; height: 25px"
										src="Images/rune/<%=championSummarySubRune2[3]%>" alt="img" />
									<img style="width: 25px; height: 25px"
										src="Images/rune/<%=championSummarySubRune2[5]%>" alt="img" />
								</div>
								<div class="rune-select">
									<img style="width: 25px; height: 25px"
										src="Images/rune/<%=championSummarySubRune2[7]%>" alt="img" />
									<img style="width: 25px; height: 25px"
										src="Images/rune/<%=championSummarySubRune2[9]%>" alt="img" />
									<img style="width: 25px; height: 25px"
										src="Images/rune/<%=championSummarySubRune2[11]%>" alt="img" />
								</div>
								<div class="rune-select">
									<img style="width: 25px; height: 25px"
										src="Images/rune/<%=championSummarySubRune2[13]%>" alt="img" />
									<img style="width: 25px; height: 25px"
										src="Images/rune/<%=championSummarySubRune2[15]%>" alt="img" />
									<img style="width: 25px; height: 25px"
										src="Images/rune/<%=championSummarySubRune2[17]%>" alt="img" />
								</div>
							</div>
							
						</div>
					</div>

					<div class="item-skill-summary">
						<div class="item-core-summary">
							<div id="itme-core-summary12" class="item-core-summary-items">
								1코어</div>
							<div id="itme-core-summary22" class="item-core-summary-items">
								2코어</div>
							<div id="itme-core-summary32" class="item-core-summary-items">
								3코어</div>
						</div>

						<div class="skill-summary-container">
							<div class="skill-summary">
								<div class="skill-seq-row">
									<div class="skill-title">Q</div>
									<img style="width: 60px; margin-right: 4px;"
										src="Images/skill/<%=csilist.get(0).getQ()%>" alt="img" />
									<div id="csylistQ2" style="display: flex;"></div>
								</div>

								<div class="skill-seq-row">
									<div class="skill-title">W</div>
									<img style="width: 60px; margin-right: 4px;"
										src="Images/skill/<%=csilist.get(0).getW()%>" alt="img" />
									<div id="csylistW2" style="display: flex;"></div>
								</div>

								<div class="skill-seq-row">
									<div class="skill-title">E</div>
									<img style="width: 60px; margin-right: 4px;"
										src="Images/skill/<%=csilist.get(0).getE()%>" alt="img" />
									<div id="csylistE2" style="display: flex;"></div>
								</div>

								<div class="skill-seq-row">
									<div class="skill-title">R</div>
									<img style="width: 60px; margin-right: 4px;"
										src="Images/skill/<%=csilist.get(0).getR()%>" alt="img" />
									<div id="csylistR2" style="display: flex;"></div>
								</div>
							</div>
						</div>

						<div class="skill-mastar-summary">
							<div class="good-skill-master">
								<p>스킬 마스터 순서</p>
								<%
									for (int i = 0; i < 3; i++) {
								%>
								<span class='tooltip'> <img
									src='Images/skill/<%=gsmlist.get(i).getImage()%>' alt='img' />
									<span class='tooltiptext tooltip-right'> <b
										style='color: #ffc107;'><%=gsmlist.get(i).getName()%></b><br />
									<br /><%=gsmlist.get(i).getFunction()%>
								</span>
								</span>
								<%
									}
								%>
							</div>
							<div class="good-spell">
								<p>추천 스펠</p>
								<%
									for (int i = 0; i < reslist.size(); i++) {
								%>
								<span class='tooltip'> <img
									src='Images/spell/<%=reslist.get(i).getImage()%>' alt='img' />
									<span class='tooltiptext tooltip-right'> <b
										style='color: #ffc107;'><%=reslist.get(i).getName()%></b><br />
									<br /><%=reslist.get(i).getFunction()%>
								</span>
								</span>
								<%
									}
								%>
							</div>
							<div class="good-start-item">
								<p>시작아이템</p>
								<%
									for (int i = 0; i < stilist.size(); i++) {
								%>
								<span class='tooltip'> <img
									src='Images/item/<%=stilist.get(i).getImage()%>' alt='img' /> <span
									class='tooltiptext tooltip-right'> <b
										style='color: #ffc107;'><%=stilist.get(i).getName()%></b><br />
									<br /><%=stilist.get(i).getFunction()%>
								</span>
								</span>
								<%
									}
								%>
							</div>
							<div class="good-shoes">
								<p>신발</p>
								<%
									for (int i = 0; i < shoeslist.size(); i++) {
								%>
								<span class='tooltip'> <img
									src='Images/item/<%=shoeslist.get(i).getImage()%>' alt='img' />
									<span class='tooltiptext tooltip-right'> <b
										style='color: #ffc107;'><%=shoeslist.get(i).getName()%></b><br />
									<br /><%=shoeslist.get(i).getFunction()%>
								</span>
								</span>
								<%
									}
								%>
							</div>
							<div class="good-core">
								<p>코어템</p>
								<%
									for (int i = 0; i < 1; i++) {
										if(coreCombine4.size()==0) {
											%>
												<span></span>
											<%
										} else {
								%>
								<span class='tooltip'> <img
									src='Images/item/<%=coreCombine4.get(i).getImage1()%>' alt='img' /> <span
									class='tooltiptext tooltip-right'> <b
										style='color: #ffc107;'><%=coreCombine4.get(i).getPick1()%></b><br />
									<br /><%=coreCombine4.get(i).getFunction1()%>
								</span>
								</span>
								<span class='tooltip'> <img
									src='Images/item/<%=coreCombine4.get(i).getImage2()%>' alt='img' /> <span
									class='tooltiptext tooltip-right'> <b
										style='color: #ffc107;'><%=coreCombine4.get(i).getPick2()%></b><br />
									<br /><%=coreCombine4.get(i).getFunction2()%>
								</span>
								</span>
								<span class='tooltip'> <img
									src='Images/item/<%=coreCombine4.get(i).getImage3()%>' alt='img' /> <span
									class='tooltiptext tooltip-right'> <b
										style='color: #ffc107;'><%=coreCombine4.get(i).getPick3()%></b><br />
									<br /><%=coreCombine4.get(i).getFunction3()%>
								</span>
								</span>
								<span class='tooltip'> <img
									src='Images/item/<%=coreCombine4.get(i).getImage4()%>' alt='img' /> <span
									class='tooltiptext tooltip-right'> <b
										style='color: #ffc107;'><%=coreCombine4.get(i).getPick4()%></b><br />
									<br /><%=coreCombine4.get(i).getFunction4()%>
								</span>
								</span>
								<%
										}
									}
								%>
							</div>
						</div>
					</div>
				</div>
			</div>
		<!--여기까지 win-->
		</div>

		<div style="width: 100%; height: 400px;"></div>

		<div class="champ-nav" id="counter">
			<a id="champ-nav1" class="champ-nav-items champ-nav-active">챔피언 통계</a>
			 <a id="champ-nav2" class="champ-nav-items">기본 정보</a>
			 <a id="champ-nav3" class="champ-nav-items">패치 히스토리</a> 
			 <a id="champ-nav4" class="champ-nav-items">커뮤니티</a>
		</div>






		<!-- 기본정보 스킬 container -->
		<div id="loadContents">

			<div id="statistics">
				<div class="statistics-champ-match-container">
					<div class="statistics-title">가렌 상대 챔피언</div>
					<div class="statistics-champ-match">
						<div id="match-hard" class="statistics-match-list">
							<h4>상대하기 어려움</h4>
							<%
								String isGray = "";
								for (int i = 0; i < matchHard.size(); i++) {
									if(matchHard.get(0).getName()==null) {
										%>
											<span></span>
										<%
									} else {
										if (i % 2 == 0) {
											isGray = "statistics-gray";
										} else {
											isGray = "";
										}
									%>
									<a class="statistics-hard-list <%=isGray%>" href="statistics.jsp?name=<%=matchHard.get(i).getName()%>&line=<%=champion_line%>"> 
										<span style="width: 10%;"> 
											<img src="Images/champion/head/<%=matchHard.get(i).getImage()%>" alt="img">
										</span> 
										<span style="width: 60%; padding: 10px 0px 0px 20px; text-align: left;">
											<span><%=matchHard.get(i).getName()%></span>
										</span> 
										<span style="width: 30%; padding-top: 10px;"> 
											<span><%=matchHard.get(i).getCount()%></span>
											<span class="statistics-hard"><%=matchHard.get(i).getWinRate()%>%</span>
										</span>
									</a>
									<%
									}
								}
							%>

						</div>

						<div class="statistics-match-list">
							<h4>상대하기 쉬움</h4>
							<%
								isGray = "";
								for (int i = 0; i < matchEasy.size(); i++) {
									if (i % 2 == 0) {
										isGray = "statistics-gray";
									} else {
										isGray = "";
									}
							%>
							<a class="statistics-hard-list <%=isGray%>" href="statistics.jsp?name=<%=matchEasy.get(i).getName()%>&line=<%=champion_line%>"> 
								<span style="width: 10%;"> 
									<img src="Images/champion/head/<%=matchEasy.get(i).getImage()%>" alt="img">
								</span> 
								<span style="width: 60%; padding: 10px 0px 0px 20px; text-align: left;">
									<span><%=matchEasy.get(i).getName()%></span>
								</span> 
								<span style="width: 30%; padding-top: 10px;"> 
									<span><%=matchEasy.get(i).getCount()%></span> 
									<span class="statistics-easy"><%=matchEasy.get(i).getWinRate()%>%</span>
								</span>
							</a>
							<%
								}
							%>
							<div id="spell-startitem"></div>
						</div>
					</div>
				</div>

				<div class="statistics-content-container">
					<div class="statistics-spell-items" id="spell-startitem">
						<div class="statistics-title">스펠, 아이템 선택</div>
						<div class="statistics-spell-item-content">
							<div class="statistics-spell-box">
								<h4 style="padding: 5px">스펠</h4>
								<div class="statistics-number">
									<span class="statistics-number-items">승률</span> <span
										class="statistics-number-items">선택률</span> <span
										class="statistics-number-items">카운트수</span>
								</div>
								<ul class="statistics-spell-list">
									<%
										isGray = "";
										for (int i = 0; i < selectSpell.size(); i++) {
											if (i % 2 == 0) {
												isGray = "statistics-gray";
											} else {
												isGray = "";
											}
									%>
									<li class="statistics-list-items <%=isGray%>">
										<div class="statistics-spell">
											<span class='tooltip'> <img
												src="Images/spell/<%=selectSpell.get(i).getPick1()%> "
												alt="img" /> <span class='tooltiptext tooltip-right'>
													<b style='color: #ffc107;'><%=selectSpell.get(i).getName1()%></b><br />
												<br /><%=selectSpell.get(i).getFunction1()%>
											</span>
											</span> <span class='tooltip'> <img
												src="Images/spell/<%=selectSpell.get(i).getPick2()%> "
												alt="img" /> <span class='tooltiptext tooltip-right'>
													<b style='color: #ffc107;'><%=selectSpell.get(i).getName2()%></b><br />
												<br /><%=selectSpell.get(i).getFunction2()%>
											</span>
											</span>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;"><%=selectSpell.get(i).getWinRate()%></span>
											<span style="width: 23.3%;"><%=selectSpell.get(i).getPickRate()%></span>
											<span style="width: 23.3%;"><%=selectSpell.get(i).getCount()%></span>
										</div>
									</li>
									<%
										}
									%>
								</ul>
							</div>
							<div class="statistics-spell-box">
								<h4 style="padding: 5px">스타트 아이템</h4>
								<div class="statistics-number">
									<span class="statistics-number-items">승률</span> <span
										class="statistics-number-items">선택률</span> <span
										class="statistics-number-items">카운트수</span>
								</div>
								<ul class="statistics-spell-list">
									<%
			                        	isGray = "";
			                        	for(int i=0;i<selectStartItem.size();i++){
			                        		if(i%2==0){
			                            		isGray = "statistics-gray";
			                            	}else{
			                            		isGray = "";
			                            	}
		                        	%>
									<li class="statistics-list-items <%=isGray %>">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/item/<%=selectStartItem.get(i).getPick1()%>" alt="img" /> 
												<span class='tooltiptext tooltip-right'>
													<b style='color: #ffc107;'><%=selectStartItem.get(i).getName1()%></b><br/><br/><%=selectStartItem.get(i).getFunction1()%>
												</span>
											</span> 
											<%
												if(selectStartItem.get(i).getFunction2()== null) {
													%>
														<span></span>
													<%
												} else {
											%>
											<span class='tooltip'> 
												<img src="Images/item/<%=selectStartItem.get(i).getPick2()%>" alt="img" /> 
												<span class='tooltiptext tooltip-right'>
													<b style='color: #ffc107;'><%=selectStartItem.get(i).getName2()%></b><br/><br/><%=selectStartItem.get(i).getFunction2()%>
												</span>
											</span>
											<%
												}
											%>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;"><%=selectStartItem.get(i).getWinRate() %></span>
											<span style="width: 23.3%;"><%=selectStartItem.get(i).getPickRate() %></span>
											<span style="width: 23.3%;"><%=selectStartItem.get(i).getCount() %></span>
										</div>
									</li>
									<%
                        	}
                            %>
								</ul>
							</div>
							<div class="statistics-spell-box" style="border-right: none;">
								<h4 style="padding: 5px">신발</h4>
								<div class="statistics-number">
									<span class="statistics-number-items">승률</span> <span
										class="statistics-number-items">선택률</span> <span
										class="statistics-number-items">카운트수</span>
								</div>
								<ul class="statistics-spell-list">
									<%
                        	isGray = "";
                        	for(int i=0;i<selectShoes.size();i++){
                        		if(i%2==0){
                            		isGray = "statistics-gray";
                            	}else{
                            		isGray = "";
                            	}
                        	%>
									<li class="statistics-list-items <%=isGray %>">
										<div class="statistics-spell">
											<span class='tooltip'> <img
												src="Images/item/<%=selectShoes.get(i).getPick1()%>"
												alt="img" /> <span class='tooltiptext tooltip-right'>
													<b style='color: #ffc107;'><%=selectShoes.get(i).getName1()%></b><br />
												<br /><%=selectShoes.get(i).getFunction1()%>
											</span>
											</span>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;"><%=selectShoes.get(i).getWinRate() %></span>
											<span style="width: 23.3%;"><%=selectShoes.get(i).getPickRate() %></span>
											<span style="width: 23.3%;"><%=selectShoes.get(i).getCount() %></span>
										</div>
									</li>
									<%
                        	}
                            %>
								</ul>
							</div>
						</div>
					</div>
				</div>

				<div class="statistics-content-container statistics-core-each" id="coreitem">
					<div class="statistics-spell-items">
						<div class="statistics-title">코어템 통계</div>
						<div class="statistics-spell-item-content">
							<div class="statistics-spell-box">

								<h4 style="padding: 5px">1코어</h4>

								<div class="statistics-number">
									<span class="statistics-number-items">승률</span> <span
										class="statistics-number-items">선택률</span> <span
										class="statistics-number-items">카운트수</span>
								</div>
								<ul class="statistics-spell-list">
									<%
                        	isGray = "";
                        	for(int i=0;i<core1.size();i++){
                        		if(i%2==0){
                            		isGray = "statistics-gray";
                            	}else{
                            		isGray = "";
                            	}
                        	%>
									<li class="statistics-list-items <%=isGray%>">
										<div class="statistics-spell">
											<span class='tooltip'> <img
												src="Images/item/<%=core1.get(i).getImage()%>" alt="img" />
												<span class='tooltiptext tooltip-right'> <b
													style='color: #ffc107;'><%=core1.get(i).getPick()%></b><br />
												<br /><%=core1.get(i).getFunction()%>
											</span>
											</span>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;"><%=core1.get(i).getWinRate()%></span>
											<span style="width: 23.3%;"><%=core1.get(i).getPickRate()%></span>
											<span style="width: 23.3%;"><%=core1.get(i).getCount()%></span>
										</div>
									</li>
									<%
                        	}
                            %>
								</ul>
							</div>
							<div class="statistics-spell-box">

								<h4 style="padding: 5px">2코어</h4>

								<div class="statistics-number">
									<span class="statistics-number-items">승률</span> <span
										class="statistics-number-items">선택률</span> <span
										class="statistics-number-items">카운트수</span>
								</div>
								<ul class="statistics-spell-list">
									<%
                        	isGray = "";
                        	for(int i=0;i<core2.size();i++){
                        		if(i%2==0){
                            		isGray = "statistics-gray";
                            	}else{
                            		isGray = "";
                            	}
                        	%>
									<li class="statistics-list-items <%=isGray%>">
										<div class="statistics-spell">
											<span class='tooltip'> <img
												src="Images/item/<%=core2.get(i).getImage()%>" alt="img" />
												<span class='tooltiptext tooltip-right'> <b
													style='color: #ffc107;'><%=core2.get(i).getPick()%></b><br />
												<br /><%=core2.get(i).getFunction()%>
											</span>
											</span>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;"><%=core2.get(i).getWinRate()%></span>
											<span style="width: 23.3%;"><%=core2.get(i).getPickRate()%></span>
											<span style="width: 23.3%;"><%=core2.get(i).getCount()%></span>
										</div>
									</li>
									<%
                        	}
                            %>
								</ul>
							</div>
							<div class="statistics-spell-box" style="border-right: none;">

								<h4 style="padding: 5px;">3코어</h4>

								<div class="statistics-number">
									<span class="statistics-number-items">승률</span> <span
										class="statistics-number-items">선택률</span> <span
										class="statistics-number-items">카운트수</span>
								</div>
								<ul class="statistics-spell-list">
							<%
                        	isGray = "";
                        	for(int i=0;i<core3.size();i++){
                        		if(i%2==0){
                            		isGray = "statistics-gray";
                            	}else{
                            		isGray = "";
                            	}
                        	%>
									<li class="statistics-list-items <%=isGray%>">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/item/<%=core3.get(i).getImage()%>" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=core3.get(i).getPick()%></b><br />
													<br /><%=core3.get(i).getFunction()%>
												</span>
											</span>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;"><%=core3.get(i).getWinRate()%></span>
											<span style="width: 23.3%;"><%=core3.get(i).getPickRate()%></span>
											<span style="width: 23.3%;"><%=core3.get(i).getCount()%></span>
										</div>
									</li>
									<%
                            }
                            %>
								</ul>
							</div>
						</div>
					</div>
				</div>

				<div class="statistics-content-container statistics-core-combine">
					<div class="statistics-spell-items">
						<div class="statistics-title">코어템 조합 통계</div>
						<div class="statistics-spell-item-content">
							<div class="statistics-spell-box">
								<h4 style="padding: 5px">2코어 조합</h4>
								<div class="statistics-number">
									<span class="statistics-number-items">승률</span> <span
										class="statistics-number-items">선택률</span> <span
										class="statistics-number-items">카운트수</span>
								</div>

								<ul class="statistics-spell-list">
							<%
                        	isGray = "";
                        	for(int i=0;i<coreCombine2.size();i++){
                        		if(i%2==0){
                            		isGray = "statistics-gray";
                            	}else{
                            		isGray = "";
                            	}
                        	%>
									<li class="statistics-list-items <%=isGray%>">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/item/<%=coreCombine2.get(i).getImage1()%>" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=coreCombine2.get(i).getPick1()%></b><br />
													<br /><%=coreCombine2.get(i).getFunction1()%>
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/item/<%=coreCombine2.get(i).getImage2()%>" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=coreCombine2.get(i).getPick2()%></b><br />
													<br /><%=coreCombine2.get(i).getFunction2()%>
												</span>
											</span>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;"><%=coreCombine2.get(i).getWinRate()%></span> 
											<span style="width: 23.3%;"><%=coreCombine2.get(i).getPickRate()%></span> 
											<span style="width: 23.3%;"><%=coreCombine2.get(i).getCount()%></span>
										</div>
									</li>
							<%
                        	}
							%>
								</ul>

							</div>
							<div class="statistics-spell-box">
								<h4 style="padding: 5px">3코어 조합</h4>
								<div class="statistics-number">
									<span class="statistics-number-items">승률</span> <span
										class="statistics-number-items">선택률</span> <span
										class="statistics-number-items">카운트수</span>
								</div>
								<ul class="statistics-spell-list">
								<%
	                        	isGray = "";
	                        	for(int i=0;i<coreCombine3.size();i++){
	                        		if(i%2==0){
	                            		isGray = "statistics-gray";
	                            	}else{
	                            		isGray = "";
	                            	}
	                        	%>
									<li class="statistics-list-items <%=isGray%>">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/item/<%=coreCombine3.get(i).getImage1()%>" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=coreCombine3.get(i).getPick1()%></b><br />
													<br /><%=coreCombine3.get(i).getFunction1()%>
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/item/<%=coreCombine3.get(i).getImage2()%>" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=coreCombine3.get(i).getPick2()%></b><br />
													<br /><%=coreCombine3.get(i).getFunction2()%>
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/item/<%=coreCombine3.get(i).getImage3()%>" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=coreCombine3.get(i).getPick3()%></b><br />
													<br /><%=coreCombine3.get(i).getFunction3()%>
												</span>
											</span>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;"><%=coreCombine3.get(i).getWinRate()%></span> 
											<span style="width: 23.3%;"><%=coreCombine3.get(i).getPickRate()%></span> 
											<span style="width: 23.3%;"><%=coreCombine3.get(i).getCount()%></span>
										</div>
									</li>
								<%
	                        	}
								%>
								</ul>
							</div>
							<div class="statistics-spell-box" style="border-right: none;">
								<h4 style="padding: 5px;">4코어 조합</h4>
								<div class="statistics-number">
									<span class="statistics-number-items">승률</span> <span
										class="statistics-number-items">선택률</span> <span
										class="statistics-number-items">카운트수</span>
								</div>
								<ul class="statistics-spell-list">
								<%
	                        	isGray = "";
	                        	for(int i=0;i<coreCombine4.size();i++){
	                        		if(i%2==0){
	                            		isGray = "statistics-gray";
	                            	}else{
	                            		isGray = "";
	                            	}
	                        	%>
									<li class="statistics-list-items <%=isGray%>">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/item/<%=coreCombine4.get(i).getImage1()%>" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=coreCombine4.get(i).getPick1()%></b><br />
													<br /><%=coreCombine4.get(i).getFunction1()%>
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/item/<%=coreCombine4.get(i).getImage2()%>" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=coreCombine4.get(i).getPick2()%></b><br/>
													<br /><%=coreCombine4.get(i).getFunction2()%>
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/item/<%=coreCombine4.get(i).getImage3()%>" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=coreCombine4.get(i).getPick3()%></b><br/>
													<br /><%=coreCombine4.get(i).getFunction3()%>
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/item/<%=coreCombine4.get(i).getImage4()%>" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=coreCombine4.get(i).getPick4()%></b><br/>
													<br /><%=coreCombine4.get(i).getFunction4()%>
												</span>
											</span>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;"><%=coreCombine4.get(i).getWinRate()%></span> 
											<span style="width: 23.3%;"><%=coreCombine4.get(i).getPickRate()%></span> 
											<span style="width: 23.3%;"><%=coreCombine4.get(i).getCount()%></span>
										</div>
									</li>
								<%
	                        	}
								%>
								</ul>
							</div>
						</div>
					</div>
						<div id="skills"></div>
				</div>

				<div class="statistics-content-container statistics-skill">
					<div class="statistics-spell-items">
						<div class="statistics-title">스킬</div>
						<div class="statistics-spell-item-content">
							<div class="statistics-spell-box"
								style="border-right: none; width: 35%;">
								<h4 style="padding: 5px">마스터 순서</h4>
								<div class="statistics-number">
									<span class="statistics-number-items">승률</span> <span
										class="statistics-number-items">선택률</span> <span
										class="statistics-number-items">카운트수</span>
								</div>
								<ul class="statistics-spell-list">
								<%
								for(int i=0;i<skillMaster.size();i++){
								%>
									<li class="statistics-list-items statistics-border-bottom">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/skill/<%=skillMaster.get(i).getImage1() %>" alt="img"/>
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=skillMaster.get(i).getPick1() %></b><br/>
													<br /><%=skillMaster.get(i).getFunction1()%>
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/skill/<%=skillMaster.get(i).getImage2() %>" alt="img"/>
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=skillMaster.get(i).getPick2() %></b><br/>
													<br /><%=skillMaster.get(i).getFunction2()%>
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/skill/<%=skillMaster.get(i).getImage3() %>" alt="img"/>
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=skillMaster.get(i).getPick3() %></b><br/>
													<br /><%=skillMaster.get(i).getFunction3()%>
												</span>
											</span>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;"><%=skillMaster.get(i).getWinRate() %></span> 
											<span style="width: 23.3%;"><%=skillMaster.get(i).getPickRate() %></span> 
											<span style="width: 23.3%;"><%=skillMaster.get(i).getCount() %></span>
										</div>
									</li>
								<%
								}
								%>
								</ul>
							</div>
							<div class="statistics-spell-box"
								style="border-right: none; width: 65%; padding: 12px 12px;">

								<div class="statistics-what-level-container">
									<span class="statistics-what-level statistics-what-level-active" id="seq3">3레벨까지</span> 
									<span class="statistics-what-level" id="seq6">6레벨 까지</span> 
									<span class="statistics-what-level" id="seq11">11레벨 까지</span>
								</div>
								
								<div class="statistics-number">
									<span class="statistics-number-items">승률</span> <span
										class="statistics-number-items">선택률</span> <span
										class="statistics-number-items">카운트수</span>
								</div>
								<!--  3레벨 -->
								<div id="skillSeq">
									<ul class="statistics-spell-list2 statistics-display-block" id="skillSeqList">
										<%
										for(int i=0;i<skillSeq3.size();i++){
										%>
										<li class="statistics-list-items statistics-border-bottom">
											<div class="statistics-spell">
												<span class='tooltip'> 
													<img src="Images/skill/<%=skillSeq3.get(i).getImage1() %>" alt="img"/>
													<span class='tooltiptext tooltip-right'> 
														<b style='color: #ffc107;'><%=skillSeq3.get(i).getPick1() %></b><br/>
														<br /><%=skillSeq3.get(i).getFunction1() %>
													</span>
												</span> 
												<span class='tooltip'> 
													<img src="Images/skill/<%=skillSeq3.get(i).getImage2() %>" alt="img"/>
													<span class='tooltiptext tooltip-right'> 
														<b style='color: #ffc107;'><%=skillSeq3.get(i).getPick2() %></b><br/>
														<br /><%=skillSeq3.get(i).getFunction2() %>
													</span>
												</span> 
												<span class='tooltip'> 
													<img src="Images/skill/<%=skillSeq3.get(i).getImage3() %>" alt="img"/>
													<span class='tooltiptext tooltip-right'> 
														<b style='color: #ffc107;'><%=skillSeq3.get(i).getPick3() %></b><br/>
														<br /><%=skillSeq3.get(i).getFunction3() %>
													</span>
												</span> 
											</div>
											<div class="statistics-spell-percent" style="width: 40%;">
												<span style="width: 23.3%;"><%=skillSeq3.get(i).getWinRate() %></span> 
												<span style="width: 23.3%;"><%=skillSeq3.get(i).getPickRate() %></span> 
												<span style="width: 23.3%;"><%=skillSeq3.get(i).getCount() %></span>
											</div>
										</li>
										<%
										}
										%>
									</ul>
									</div>
								<div id="runes"></div>
							</div>
						</div>
					</div>
				</div>

				<div class="statistics-content-container statistics-rune">
					<div class="statistics-spell-items">
						<div class="statistics-title">룬</div>
						<div class="statistics-spell-item-content">
							<div class="statistics-spell-box"
								style="width: 60%; padding: 15px;">
								<h4 style="padding: 5px">조합별 통계</h4>
								<div class="statistics-number">
									<span class="statistics-number-items">승률</span> 
									<span class="statistics-number-items">선택률</span> 
								</div>
								<ul class="statistics-spell-list">
									<%
		                        	isGray = "";
		                        	for(int i=0;i<runeCombine.size();i++){
		                        		if(i%2==0){
		                            		isGray = "statistics-gray";
		                            	}else{
		                            		isGray = "";
		                            	}
	                        		%>
									<li class="statistics-list-items <%=isGray%>">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/rune/<%=runeCombine.get(i).getImage1() %>" alt="img"> 
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=runeCombine.get(i).getPick1() %></b><br/>
													<br /><%=runeCombine.get(i).getFunction1() %>
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/rune/<%=runeCombine.get(i).getImage2() %>" alt="img"> 
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=runeCombine.get(i).getPick2() %></b><br/>
													<br /><%=runeCombine.get(i).getFunction2() %>
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/rune/<%=runeCombine.get(i).getImage3() %>" alt="img"> 
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=runeCombine.get(i).getPick3() %></b><br/>
													<br /><%=runeCombine.get(i).getFunction3() %>
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/rune/<%=runeCombine.get(i).getImage4() %>" alt="img"> 
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=runeCombine.get(i).getPick4() %></b><br/>
													<br /><%=runeCombine.get(i).getFunction4() %>
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/rune/<%=runeCombine.get(i).getImage5() %>" alt="img"> 
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=runeCombine.get(i).getPick5() %></b><br/>
													<br /><%=runeCombine.get(i).getFunction5() %>
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/rune/<%=runeCombine.get(i).getImage6() %>" alt="img"> 
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=runeCombine.get(i).getPick6() %></b><br/>
													<br /><%=runeCombine.get(i).getFunction6() %>
												</span>
											</span>
										</div>
										<div class="statistics-spell-percent" style="width: 45%;">
											<span style="width: 23.3%;"><%=runeCombine.get(i).getWinRate() %></span> 
											<span style="width: 23.3%;"><%=runeCombine.get(i).getPickRate() %></span> 
										</div>
									</li>
									<%
		                        	}
									%>
								</ul>
							</div>
							<div class="statistics-spell-box"
								style="border-right: none; width: 40%; padding: 15px;">
								<h4 style="padding: 5px">파편 조합 통계</h4>
								<div class="statistics-number">
									<span class="statistics-number-items">승률</span> 
									<span class="statistics-number-items">선택률</span> 
								</div>
								<ul class="statistics-spell-list">
									<%
		                        	isGray = "";
		                        	for(int i=0;i<runeShard.size();i++){
		                        		if(i%2==0){
		                            		isGray = "statistics-gray";
		                            	}else{
		                            		isGray = "";
		                            	}
	                        		%>
									<li class="statistics-list-items <%=isGray%>">
										<div class="statistics-spell">
											<span class='tooltip'> 
												<img src="Images/rune/<%=runeShard.get(i).getImage1() %>" alt="img"> 
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>공격</b><br/>
													<br /><%=runeShard.get(i).getPick1() %>
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/rune/<%=runeShard.get(i).getImage2() %>" alt="img"> 
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>유연</b><br/>
													<br /><%=runeShard.get(i).getPick2() %>
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/rune/<%=runeShard.get(i).getImage3() %>" alt="img"> 
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'>방어</b><br/>
													<br /><%=runeShard.get(i).getPick3() %>
												</span>
											</span>
										</div>
										<div class="statistics-spell-percent">
											<span style="width: 23.3%;"><%=runeShard.get(i).getWinRate() %></span> 
											<span style="width: 23.3%;"><%=runeShard.get(i).getPickRate() %></span> 
										</div>
									</li>
									<%
		                        	}
									%>
								</ul>
							</div>
						</div>
					</div>
				</div>
				
			</div>
			
		</div>

	</div>
	<!--  이거 지우면 깨지니까 지우지 말자 -->





	<div class="top-button">
		<span style="color: #6c757d;">UP</span>
	</div>


	<footer class="footer">

		<div class="footer-left">
			<span class="footer-left-item">공지사항</span> <span
				class="footer-left-item">버그리포팅</span> <span class="footer-left-item">파트너
				신청</span></br>
			<div style="margin-bottom: 10px;"></div>
			<span class="footer-left-item">이용약관</span> <span
				class="footer-left-item">개인정보처리방침</span>
		</div>
	
		<div class="footer-right">
			<h5>PS Analytics, Inc. © 2020</h5>
			<p>lol.ps is hosted by PS Analytics, Inc. lol.ps isn’t endorsed
				by Riot Games and doesn’t reflect the views or opinions of Riot
				Games or anyone officially involved in producing or managing League
				of Legends. League of Legends and Riot Games are trademarks or
				registered trademarks of Riot Games, Inc. League of Legends © Riot
				Games, Inc.</p>
		</div>

	</footer>
	<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
	<script src="Js/all.js"></script>
</body>
</html>