<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.yg_ac.dao.*" %>
<%@ page import="com.yg_ac.dto.*" %>
<%
	ChampRankDao champRankDao = new ChampRankDao();
	session.getAttribute("memberInfo");
	ArrayList<ChampRankDto> rankAll = champRankDao.getChampRankAll();
	ArrayList<ChampRankDto> rankTopLine = champRankDao.getChampRankByLine("탑");
	ArrayList<ChampRankDto> rankJungleLine = champRankDao.getChampRankByLine("정글");
	ArrayList<ChampRankDto> rankMidLine = champRankDao.getChampRankByLine("미드");
	ArrayList<ChampRankDto> rankBotLine = champRankDao.getChampRankByLine("원딜");
	ArrayList<ChampRankDto> rankSupLine = champRankDao.getChampRankByLine("서폿");
	
%>
<!DOCTYPE html>
<html class = "rank-html">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>챔피언랭킹</title>
    <link rel="stylesheet" href="Css/all.css">
    <link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <script src="Js/jquery-3.6.0.min.js"></script>
</head>
<body class = "rank-body">
    <!-- 위에 navbar 부분입니다 -->
    <header class="all-header-mainnav header-mainnav">
        <div class="header-container">
            <a href="main.jsp">
                <img src="Images/header-logo.webp" alt="LOL.PS">
            </a>
            <div class = "nav-item-container">
            	<a class="nav-items" href="../notice/notice.html">공지사항</a>
                <a class="nav-items" href="ChampRank.jsp">챔피언 랭킹</a>
                <a class="nav-items" href="community.jsp?category=빌드 연구소">빌드 연구소</a>
                <a class="nav-items" href="community.jsp?category=자유 게시판">자유 게시판</a>
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
            			<form action= "my-page.jsp" >
            				<input class="signin" type="submit" value="마이페이지"/>
            			</form>
		                <form action = "login.jsp" >
		                	<input class="login" type="submit" value="로그아웃"/>
	                	</form>    
            	<%
            		}
            	%>
            </div>
        </div>
    </header>

    <div class="all-main">
        <div class="rank-main-content">
            <p>챔피언 랭킹</p>
            <span class="rank-main-content-item rank-main-content-active">라인별 챔피언 티어</span>
				<form action="Controller" method="get" id="search_form" autocomplete="off">
					<input class="main-input" type="text" name="name" placeholder="챔피언 이름을 입력하세요">
					<button style="opacity:0;" type="submit" name="command" value="search"></button>
				</form>
            <div style="clear: both;"></div>
        </div>
    </div>
    
    <div class="rank-google-ad">
    </div>

    <div class="rank-champ-rank-container"  id="rank-top-champ">
        <div >
            <h3 style="color: #213a86; margin: 0; font-size: 16px;">라인별 챔피언 티어</h3>
            <p style = "color: rgba(47,62,78,.7); font-size : 12px; margin-top: 10px; margin-bottom: 40px;">
                라인별 픽률 0.5% 이상의 챔피언을 원하는 정렬에 따라 볼 수 있습니다.
            </p>

            <ul class="rank-position">
                <li class="rank-position-button rank-active">탑</li>
                <li class="rank-position-button">정글</il>
                <li class="rank-position-button">미드</li>
                <li class="rank-position-button">원딜</li>
                <li class="rank-position-button">서폿</li>
                <li class="rank-position-button">전체</li>
            </ul>
        </div>
        
        <div>
	        	<span class="rank-declaration">표본수</span>
	        	<span class="rank-declaration">밴율</span>
	        	<span class="rank-declaration">픽률</span>
	        	<span class="rank-declaration">승률</span>
	        	<span class="rank-declaration">꿀챔 점수</span>
	        	<span class="rank-declaration">PS Score</span>
        </div>
        
        <div class="rank-champ-list-tier">
	        <div class="rank-champ-list">
        <%
        	for(int i = 0; i < rankTopLine.size(); i++) {
        	
        %>
	            <a class="rank-list" href="statistics.jsp?name=<%=rankTopLine.get(i).getName() %>&line=<%=rankTopLine.get(i).getLine() %>" style="text-decoration: none; color: black;">
	                <span style="width:30px;"><%=i+1%></span>
	                <span style='width: 40px; padding: 0;'>
	                    <img class="rank-list-image" src="Images/champion/head/<%=rankTopLine.get(i).getImage() %>" alt="img">
	                </span>
	                <span style="width:250px;"><%=rankTopLine.get(i).getName() %></span>
	                <span class="rank-list-number"><%=rankTopLine.get(i).getLine() %></span>
	                <span class="rank-list-number"><%=rankTopLine.get(i).getPsScore() %></span>
	                <span class="rank-list-number"><%=rankTopLine.get(i).getHoneyScore() %></span>
	                <span class="rank-list-number"><%=rankTopLine.get(i).getWinRate() %>%</span>
	                <span class="rank-list-number"><%=rankTopLine.get(i).getPickRate() %>%</span>
	                <span class="rank-list-number"><%=rankTopLine.get(i).getBanRate() %>%</span>
	                <span class="rank-list-number"><%=rankTopLine.get(i).getCount2() %></span>
	            </a>
        <%
        	}
        %>
            
	       	</div>
        </div>
    </div>
        

    <div class="top-button">
        <span style="color: #6c757d;">UP</span>
    </div>


    <footer class="footer">

        <div class="footer-left">
            <span class="footer-left-item">공지사항</span>
            <span class="footer-left-item">버그리포팅</span>
            <span class="footer-left-item">파트너 신청</span></br>
            <div style="margin-bottom: 10px;"></div>
            <span class="footer-left-item">이용약관</span>
            <span class="footer-left-item">개인정보처리방침</span>
        </div>

        <div class="footer-right">
            <h5>PS Analytics, Inc. © 2020</h5>
            <p>
                lol.ps is hosted by PS Analytics, Inc. lol.ps isn’t endorsed by Riot Games and doesn’t reflect the views or opinions of Riot Games or anyone officially involved in 
                producing or managing League of Legends. League of Legends and Riot Games are trademarks or registered trademarks of Riot Games, Inc. 
                League of Legends © Riot Games, Inc.
            </p>
        </div>

    </footer>
    
<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
<script src="Js/all.js"></script>
</body>
</html>