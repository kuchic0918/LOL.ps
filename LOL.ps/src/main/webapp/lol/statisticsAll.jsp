<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.yg_ac.dao.*" %>
<%@ page import="com.yg_ac.dto.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<%
	Y_DBmanager db = new Y_DBmanager();
	Champion champion = new Champion();
	Connection conn = db.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	/* String champion_name = request.getParameter("name");
	String champion_line = request.getParameter("line"); */
	String champion_name = "가렌";
	String champion_line = "탑";
	String champion_rate = "win";
	
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
	
	
	//basic stat

	ChampBasicStatDao champBasicStatDao = new ChampBasicStatDao();
	ChampBasicSkillDao champBasicSkillDao = new ChampBasicSkillDao();
	ChampRoleDao ChampRoleDao = new ChampRoleDao();
	
	ArrayList<ChampBasicStatDto> basicStatList = champBasicStatDao.getChampBasicStat(conn, pstmt, rs, champion_name);
	ArrayList<ChampBasicSkillDto> basicSkillList = champBasicSkillDao.getChampBasicSkill(conn, pstmt, rs, champion_name);
	ArrayList<ChampRoleDto> champRole = ChampRoleDao.getChampRole(conn, pstmt, rs, champion_name);
		
	//basic stat
	
	//patch-history
	ChampPatchHistoryDao champPatchHistoryDao = new ChampPatchHistoryDao();
	ArrayList<ChampPatchHistoryDto> patchHistoryList = champPatchHistoryDao.getChampPatchHistory(conn, pstmt, rs, champion_name);
	
	//patch-history
%>
<html>
<head>
	<meta charset="UTF-8">
	<title>statistics contants</title>
	<link rel="stylesheet" href="Css/all.css">
</head>
<body>
<!-- 기본정보 -->
	<div class = "basic-info-container" id="basic-info">
	        <h3 style="margin :0 0 8px">기본정보</h3>
        <div class = "basic-info-basic-stat-box">
            <h4 style="margin: 24px 0 12px;">기본 능력치</h4>
            <div class = "basic-info-stat-div-top">
                <span class="basic-info-stat-name" style = 'background-color:transparent;'></span>
                <span class = "basic-info-stat-basic">기본능력치 (+레벨 당 상승)</span>
                <span class = "basic-info-stat-final">최종수치</span>
                <span class = "basic-info-stat-rank">챔피언 순위</span>
            </div>
            <%
            	String bottom = "";
            	String color = "";
            	for(int i = 0; i < basicStatList.size(); i++) {
            		if(i%2==1){
            			color = "color";
            		}else{
            			color = "";
            		}
            		if(i==basicStatList.size()-1){
            			bottom = "-bottom";
            		}
            %>
	            <div class = "basic-info-stat-div<%=bottom%> " id="<%=color%>">
	                <span class="basic-info-stat-name" ><%=basicStatList.get(i).getStat() %></span>
	                <span class = "basic-info-stat-basic"><%=basicStatList.get(i).getStatStart() %></span>
	                <span class = "basic-info-stat-final"><%=basicStatList.get(i).getStatFinal() %></span>
	                <span class = "basic-info-stat-rank"><%=basicStatList.get(i).getStatRank() %></span>
	            </div>
            
            <%
            	}
            %>
            
            
        </div>
	        <div class = "basic-info-skill-detail-box">
            <h4 style="margin: 24px 0 12px;">스킬</h4>            
            <p class = "basic-info-explanation">클릭하여 상세 설명을 볼 수 있습니다.</p>
            
                <h4 style="margin:24px 0 12px;">상세정보</h4>
	            <a href="#skill-p" class = "basic-info-skill-detail-info">
	                <img class = "basic-info-champ-img" src="Images/skill/<%=basicSkillList.get(1).getImage() %>"/>
	                <span class = "basic-info-champ-skill"><%=basicSkillList.get(1).getSkillKey() %></span>
	                <p class = "basic-info-skill-name"><%=basicSkillList.get(1).getSkillName() %></p>
	            </a>
	            <a href="#skill-q" class = "basic-info-skill-detail-info">
	                <img class = "basic-info-champ-img" src="Images/skill/<%=basicSkillList.get(2).getImage() %>"/>
	                <span class = "basic-info-champ-skill"><%=basicSkillList.get(2).getSkillKey() %></span>
	                <p class = "basic-info-skill-name"><%=basicSkillList.get(2).getSkillName() %></p>
	            </a>
	            <a href="#skill-w" class = "basic-info-skill-detail-info">
	                <img class = "basic-info-champ-img" src="Images/skill/<%=basicSkillList.get(4).getImage() %>"/>
	                <span class = "basic-info-champ-skill"><%=basicSkillList.get(4).getSkillKey() %></span>
	                <p class = "basic-info-skill-name"><%=basicSkillList.get(4).getSkillName() %></p>
	            </a>
	            <a href="#skill-e" class = "basic-info-skill-detail-info">
	                <img class = "basic-info-champ-img" src="Images/skill/<%=basicSkillList.get(0).getImage() %>"/>
	                <span class = "basic-info-champ-skill"><%=basicSkillList.get(0).getSkillKey() %></span>
	                <p class = "basic-info-skill-name"><%=basicSkillList.get(0).getSkillName() %></p>
	            </a>
	            <a href="#skill-r" class = "basic-info-skill-detail-info">
	                <img class = "basic-info-champ-img" src="Images/skill/<%=basicSkillList.get(3).getImage() %>"/>
	                <span class = "basic-info-champ-skill"><%=basicSkillList.get(3).getSkillKey() %></span>
	                <p class = "basic-info-skill-name"><%=basicSkillList.get(3).getSkillName() %></p>
	            </a>
            
            <div>
                <span style="color: rgba(47,62,78 , .7); font-size: 12px;">역할군</span>
                <span style="font-size: 14px; font-weight : 700; color: #353945;"><%=champRole.get(0).getRole1() %></span>
            	<br/>
                <%
                if(champRole.get(0).getRole2().equals("없음")){}
            	else{
                %>
                <span style="color: rgba(47,62,78 , .7); font-size: 12px;">역할군</span>
                <span style="font-size: 14px; font-weight : 700; color: #353945;"><%=champRole.get(0).getRole2() %></span>
            	<%
                }
            	%>
            </div>
            
        </div>

        <div style="clear: both; height:100px;" id="skill-p"></div>

        <div class = "basic-info-skill-container">
	            <h4>스킬 상세설명</h4>
	            
	            <div class = "basic-info-skill-info-box" style="border-top: 1px solid rgba(126, 155, 255, .5);">
	                <div class = "basic-info-skill-info-name">
	                    <span class = "basic-info-skill-kind" id = "skill-q"><%=basicSkillList.get(1).getSkillKey() %></span>
	                    <h5 class = "basic-info-skill-kor-name"><%=basicSkillList.get(1).getSkillName() %></h5>                   
	                </div>
	                <div class = "basic-info-skill-info">
	                    <img src="Images/skill/<%=basicSkillList.get(1).getImage() %>" style="display: block;" />
	                  
	                    <p class="basic-info-skill-text-style"><%=basicSkillList.get(1).getFunction() %></p>
	                </div>
	            </div>
	            
	            <div class = "basic-info-skill-info-box" style="border-top: 1px solid rgba(126, 155, 255, .5);">
	                <div class = "basic-info-skill-info-name">
	                    <span class = "basic-info-skill-kind" id = "skill-w"><%=basicSkillList.get(2).getSkillKey() %></span>
	                    <h5 class = "basic-info-skill-kor-name"><%=basicSkillList.get(2).getSkillName() %></h5>                   
	                </div>
	                <div class = "basic-info-skill-info">
	                    <img src="Images/skill/<%=basicSkillList.get(2).getImage() %>" style="display: block;" />
	                  
	                    <p class="basic-info-skill-text-style"><%=basicSkillList.get(2).getFunction() %></p>
	                </div>
	            </div>
	            
	            <div class = "basic-info-skill-info-box" style="border-top: 1px solid rgba(126, 155, 255, .5);">
	                <div class = "basic-info-skill-info-name">
	                    <span class = "basic-info-skill-kind" id = "skill-e"><%=basicSkillList.get(4).getSkillKey() %></span>
	                    <h5 class = "basic-info-skill-kor-name"><%=basicSkillList.get(4).getSkillName() %></h5>                   
	                </div>
	                <div class = "basic-info-skill-info">
	                    <img src="Images/skill/<%=basicSkillList.get(4).getImage() %>" style="display: block;" />
	                  
	                    <p class="basic-info-skill-text-style"><%=basicSkillList.get(4).getFunction() %></p>
	                </div>
	            </div>
	            
	            <div class = "basic-info-skill-info-box" style="border-top: 1px solid rgba(126, 155, 255, .5);">
	                <div class = "basic-info-skill-info-name">
	                    <span class = "basic-info-skill-kind" id = "skill-r"><%=basicSkillList.get(0).getSkillKey() %></span>
	                    <h5 class = "basic-info-skill-kor-name"><%=basicSkillList.get(0).getSkillName() %></h5>                   
	                </div>
	                <div class = "basic-info-skill-info">
	                    <img src="Images/skill/<%=basicSkillList.get(0).getImage() %>" style="display: block;" />
	                  
	                    <p class="basic-info-skill-text-style"><%=basicSkillList.get(0).getFunction() %></p>
	                </div>
	            </div>
	            
	            <div class = "basic-info-skill-info-box" style="border-top: 1px solid rgba(126, 155, 255, .5);">
	                <div class = "basic-info-skill-info-name">
	                    <span class = "basic-info-skill-kind" ><%=basicSkillList.get(3).getSkillKey() %></span>
	                    <h5 class = "basic-info-skill-kor-name"><%=basicSkillList.get(3).getSkillName() %></h5>                   
	                </div>
	                <div class = "basic-info-skill-info">
	                    <img src="Images/skill/<%=basicSkillList.get(3).getImage() %>" style="display: block;" />
	                  
	                    <p class="basic-info-skill-text-style"><%=basicSkillList.get(3).getFunction() %></p>
	                </div>
	            </div>
            
        </div>
    </div>
	            
	
	
	
	
	
	
	
	
	<div id="patch-history">
		<section style="padding-bottom:400px;">
			<h3>패치 히스토리</h3>
			
			<%
				for(int i = 0; i < patchHistoryList.size(); i++) {
			%>
					<div class="patch-history-patch">
						<div class="patch-history-ver">
							<h4 class="patch-history-h4"><%=patchHistoryList.get(i).getVersion() %></h4>
							<div class="patch-history-skill-imgbox">
								<span><img class="patch-history-img" src="Images/skill/<%=patchHistoryList.get(i).getImage() %>"/></span>
							</div>
							<div class="patch-history-content">
								<ul class="patch-history-ul">
									<li class="patch-history-li"><%=patchHistoryList.get(i).getContent() %></li>
								</ul>
							</div>
						</div>
					</div>
			
			<%
				}
			%>
			
			
		</section>
    </div>
	
	
	
	
	
	
	<div id="champ-community">
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
	            <div class = "champ-community-board-list">
	                <a class = "champ-community-board-item" href="../community/community-post-build.html">
	                    <span class = "champ-community-post-img">
	                        <img src="img/icon.webp" style="width: 40px; height : 40px;"/>
	                    </span>
	                    <span class = "champ-community-board-detail-box">
	                    	<span class ="champ-community-board-detail-title">[상남자의 라인, TOP] 이게 왜 좋은거지..? 화공탱 거드라 가렌
	                    		<i class = "champ-community-board-detail-comment">[0]</i>
	                    	</span>
	                    </span>
	                    <span class = "champ-community-board-detail-writer">작성자 닉네임</span>
	                    <span class = "champ-community-board-detail-date">2022-05-25</span>
	                    <span class = "champ-community-board-detail-count">579</span>
	                    <span class = "champ-community-board-detail-recommand">10</span>
	                </a>
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
    </div>
	
	
	
	
	<div id="statistics">
        <div class="statistics-champ-match-container" id="counter">
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
										<span><%=matchHard.get(i).getCount()%> 게임</span>
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
							<a class="statistics-hard-list <%=isGray%>" href="statistics.jsp?name=<%=matchEasy.get(i).getName()%>&line=<%=champion_line%>"> <span
								style="width: 10%;"> <img
									src="Images/champion/head/<%=matchEasy.get(i).getImage()%>"
									alt="img">
							</span> <span
								style="width: 60%; padding: 10px 0px 0px 20px; text-align: left;">
									<span><%=matchEasy.get(i).getName()%></span>
							</span> <span style="width: 30%; padding-top: 10px;"> <span><%=matchEasy.get(i).getCount()%>
										게임</span> <span class="statistics-easy"><%=matchEasy.get(i).getWinRate()%>%</span>
							</span>
							</a>
							<%
								}
							%>
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
											<span class='tooltip'> <img
												src="Images/item/<%=selectStartItem.get(i).getPick1()%>"
												alt="img" /> <span class='tooltiptext tooltip-right'>
													<b style='color: #ffc107;'><%=selectStartItem.get(i).getName1()%></b><br />
												<br /><%=selectStartItem.get(i).getFunction1()%>
											</span>
											</span> <span class='tooltip'> <img
												src="Images/item/<%=selectStartItem.get(i).getPick2()%>"
												alt="img" /> <span class='tooltiptext tooltip-right'>
													<b style='color: #ffc107;'><%=selectStartItem.get(i).getName2()%></b><br />
												<br /><%=selectStartItem.get(i).getFunction2()%>
											</span>
											</span>
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

				<div class="statistics-content-container statistics-core-each"
					id="coreitem">
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
													<b style='color: #ffc107;'><%=coreCombine4.get(i).getPick2()%></b><br />
													<br /><%=coreCombine4.get(i).getFunction2()%>
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/item/<%=coreCombine4.get(i).getImage3()%>" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=coreCombine4.get(i).getPick3()%></b><br />
													<br /><%=coreCombine4.get(i).getFunction3()%>
												</span>
											</span>
											<span class='tooltip'> 
												<img src="Images/item/<%=coreCombine4.get(i).getImage4()%>" alt="img" />
												<span class='tooltiptext tooltip-right'> 
													<b style='color: #ffc107;'><%=coreCombine4.get(i).getPick4()%></b><br />
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
				</div>

        <div class="statistics-content-container statistics-skill"
					id="skills">
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
								<div id="skill-seq">
								<ul class="statistics-spell-list2 statistics-display-block" id="skill-seq3">
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
    
    <!-- 3레벨 -->
    <div id="skill-seq3-load">
	    <ul class="statistics-spell-list2 statistics-display-block" id="skill-seq3">
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
	
	<!-- 6레벨 -->
	<div id="skill-seq6-load">
	    <ul class="statistics-spell-list2 statistics-display-block" id="skill-seq6">
			<%
			for(int i=0;i<skillSeq6.size();i++){
			%>
			<li class="statistics-list-items statistics-border-bottom">
				<div class="statistics-spell">
					<span class='tooltip'> 
						<img src="Images/skill/<%=skillSeq6.get(i).getImage1() %>" alt="img"/>
						<span class='tooltiptext tooltip-right'> 
							<b style='color: #ffc107;'><%=skillSeq6.get(i).getPick1() %></b><br/>
							<br /><%=skillSeq6.get(i).getFunction1() %>
						</span>
					</span> 
					<span class='tooltip'> 
						<img src="Images/skill/<%=skillSeq6.get(i).getImage2() %>" alt="img"/>
						<span class='tooltiptext tooltip-right'> 
							<b style='color: #ffc107;'><%=skillSeq6.get(i).getPick2() %></b><br/>
							<br /><%=skillSeq6.get(i).getFunction2() %>
						</span>
					</span> 
					<span class='tooltip'> 
						<img src="Images/skill/<%=skillSeq6.get(i).getImage3() %>" alt="img"/>
						<span class='tooltiptext tooltip-right'> 
							<b style='color: #ffc107;'><%=skillSeq6.get(i).getPick3() %></b><br/>
							<br /><%=skillSeq6.get(i).getFunction3() %>
						</span>
					</span>
					<span class='tooltip'> 
						<img src="Images/skill/<%=skillSeq6.get(i).getImage4() %>" alt="img"/>
						<span class='tooltiptext tooltip-right'> 
							<b style='color: #ffc107;'><%=skillSeq6.get(i).getPick4() %></b><br/>
							<br /><%=skillSeq6.get(i).getFunction4() %>
						</span>
					</span> 
					<span class='tooltip'> 
						<img src="Images/skill/<%=skillSeq6.get(i).getImage5() %>" alt="img"/>
						<span class='tooltiptext tooltip-right'> 
							<b style='color: #ffc107;'><%=skillSeq6.get(i).getPick5() %></b><br/>
							<br /><%=skillSeq6.get(i).getFunction5() %>
						</span>
					</span> 
					<span class='tooltip'> 
						<img src="Images/skill/<%=skillSeq6.get(i).getImage6() %>" alt="img"/>
						<span class='tooltiptext tooltip-right'> 
							<b style='color: #ffc107;'><%=skillSeq6.get(i).getPick6() %></b><br/>
							<br /><%=skillSeq6.get(i).getFunction6() %>
						</span>
					</span>
				</div>
				<div class="statistics-spell-percent" style="width: 40%;">
					<span style="width: 23.3%;"><%=skillSeq6.get(i).getWinRate() %></span> 
					<span style="width: 23.3%;"><%=skillSeq6.get(i).getPickRate() %></span> 
					<span style="width: 23.3%;"><%=skillSeq6.get(i).getCount() %></span>
				</div>
			</li>
			<%
			}
			%>
		</ul>
	</div>
	
	<!-- 11레벨 -->
	<div id="skill-seq11-load">
		<ul class="statistics-spell-list2 statistics-display-block" id="skill-seq11">
			<%
			for(int i=0;i<skillSeq11.size();i++){
			%>
			<li class="statistics-list-items statistics-border-bottom">
				<div class="statistics-spell">
					<span class='tooltip'> 
						<img src="Images/skill/<%=skillSeq11.get(i).getImage1() %>" alt="img"/>
						<span class='tooltiptext tooltip-right'> 
							<b style='color: #ffc107;'><%=skillSeq11.get(i).getPick1() %></b><br/>
							<br /><%=skillSeq11.get(i).getFunction1() %>
						</span>
					</span> 
					<span class='tooltip'> 
						<img src="Images/skill/<%=skillSeq11.get(i).getImage2() %>" alt="img"/>
						<span class='tooltiptext tooltip-right'> 
							<b style='color: #ffc107;'><%=skillSeq11.get(i).getPick2() %></b><br/>
							<br /><%=skillSeq11.get(i).getFunction2() %>
						</span>
					</span> 
					<span class='tooltip'> 
						<img src="Images/skill/<%=skillSeq11.get(i).getImage3() %>" alt="img"/>
						<span class='tooltiptext tooltip-right'> 
							<b style='color: #ffc107;'><%=skillSeq11.get(i).getPick3() %></b><br/>
							<br /><%=skillSeq11.get(i).getFunction3() %>
						</span>
					</span>
					<span class='tooltip'> 
						<img src="Images/skill/<%=skillSeq11.get(i).getImage4() %>" alt="img"/>
						<span class='tooltiptext tooltip-right'> 
							<b style='color: #ffc107;'><%=skillSeq11.get(i).getPick4() %></b><br/>
							<br /><%=skillSeq11.get(i).getFunction4() %>
						</span>
					</span> 
					<span class='tooltip'> 
						<img src="Images/skill/<%=skillSeq11.get(i).getImage5() %>" alt="img"/>
						<span class='tooltiptext tooltip-right'> 
							<b style='color: #ffc107;'><%=skillSeq11.get(i).getPick5() %></b><br/>
							<br /><%=skillSeq11.get(i).getFunction5() %>
						</span>
					</span> 
					<span class='tooltip'> 
						<img src="Images/skill/<%=skillSeq11.get(i).getImage6() %>" alt="img"/>
						<span class='tooltiptext tooltip-right'> 
							<b style='color: #ffc107;'><%=skillSeq11.get(i).getPick6() %></b><br/>
							<br /><%=skillSeq11.get(i).getFunction6() %>
						</span>
					</span>
					<span class='tooltip'> 
						<img src="Images/skill/<%=skillSeq11.get(i).getImage7() %>" alt="img"/>
						<span class='tooltiptext tooltip-right'> 
							<b style='color: #ffc107;'><%=skillSeq11.get(i).getPick7() %></b><br/>
							<br /><%=skillSeq11.get(i).getFunction7() %>
						</span>
					</span> 
					<span class='tooltip'> 
						<img src="Images/skill/<%=skillSeq11.get(i).getImage8() %>" alt="img"/>
						<span class='tooltiptext tooltip-right'> 
							<b style='color: #ffc107;'><%=skillSeq11.get(i).getPick8() %></b><br/>
							<br /><%=skillSeq11.get(i).getFunction8() %>
						</span>
					</span>
					<span class='tooltip'> 
						<img src="Images/skill/<%=skillSeq11.get(i).getImage9() %>" alt="img"/>
						<span class='tooltiptext tooltip-right'> 
							<b style='color: #ffc107;'><%=skillSeq11.get(i).getPick9() %></b><br/>
							<br /><%=skillSeq11.get(i).getFunction9() %>
						</span>
					</span> 
					<span class='tooltip'> 
						<img src="Images/skill/<%=skillSeq11.get(i).getImage10() %>" alt="img"/>
						<span class='tooltiptext tooltip-right'> 
							<b style='color: #ffc107;'><%=skillSeq11.get(i).getPick10() %></b><br/>
							<br /><%=skillSeq11.get(i).getFunction10() %>
						</span>
					</span> 
					<span class='tooltip'> 
						<img src="Images/skill/<%=skillSeq11.get(i).getImage11() %>" alt="img"/>
						<span class='tooltiptext tooltip-right'> 
							<b style='color: #ffc107;'><%=skillSeq11.get(i).getPick11() %></b><br/>
							<br /><%=skillSeq11.get(i).getFunction11() %>
						</span>
					</span>
				</div>
				<div class="statistics-spell-percent" style="width: 40%;">
					<span style="width: 23.3%;"><%=skillSeq11.get(i).getWinRate() %></span> 
					<span style="width: 23.3%;"><%=skillSeq11.get(i).getPickRate() %></span> 
					<span style="width: 23.3%;"><%=skillSeq11.get(i).getCount() %></span>
				</div>
			</li>
			<%
			}
			%>
		</ul>
	</div>
	
	
	
</body>
</html>