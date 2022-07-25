<%@page import="org.apache.catalina.tribes.membership.MemberImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "yg_ac_java.*"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.yg_ac.dao.*" %>
<%@ page import="com.yg_ac.dto.*" %>
<!DOCTYPE html>
<html>
<%
	MemberDTO member = (MemberDTO) session.getAttribute("memberInfo");
	if(member==null) {
		%>
			<script>
				alert('로그인 먼저 하세요.');
				location.href = 'login.jsp';
			</script>
		<%
	}
	String category = request.getParameter("category");
	ChampRankDao dao = new ChampRankDao();
	ArrayList<ChampNameDto> list = dao.getChampName();
%>
<head>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>커뮤니티-포스트</title>
    <link rel="stylesheet" href="Css/all.css">
    <link rel="stylesheet" href="Css/summernote/summernote-lite.css">
    <link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <script src="Js/jquery-3.6.0.min.js"></script>
</head>
<body class="community-body">
	<!-- 위에 navbar 부분입니다 -->
    <header class="all-header-mainnav header-mainnav">
        <div class="header-container">
            <a href="main/main.html">
                <img src="Images/header-logo.webp" alt="LOL.PS">
            </a>
            <div class = "nav-item-container">
            	<a class="nav-items" href="community.jsp?category=공지사항">공지사항</a>
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
		                <form action = "Controller" >
		                	<input type="hidden" name="command" value="login"/>
		                	<input class="login" type="submit" value="로그아웃"/>
	                	</form>  
            	<%
            		}
            	%>
			</div>
        </div>
    </header>

    <div class="all-main">
        <div class="first-title">게시물쓰기</div>
        <div class="second-title">
        	<form action="Controller" method="get" id="search_form" autocomplete="off">
				<input class="main-input" type="text" name="name" placeholder="챔피언 이름을 입력하세요">
				<button style="opacity:0;" type="submit" name="command" value="search"></button>
			</form>
        </div>
        <div style="clear: both;"></div>
    </div>
	
	<!-- 중단 -->
    <main class="community-main">
		<div class="whiteDiv"></div>
      	<!-- 포스트 -->  	
      	<div class="write-post-post-detail">
      		<form action="Controller" method="post">
	      		<!-- 제목 -->
	      		<div class="write-post-title">
	      			<div style="font-size:15px; color:#7e9bff; padding-bottom:20px;"><b>게시물 쓰기</b></div>
	      			<div style="height:20px;"></div>
	      			<span style="font-size:15px;"><b>제목</b></span>
	      			
	      			<input style="width:678px; border: 1px solid rgb(126, 155, 255);" class="input-title" type="text" name="writeTitle"/>
	      			
	           		<div style="height:50px;"></div>
	           		<%
	           		if(category.equals("빌드 연구소")) {
	           		%>
           			<span style="font-size:15px;"><b>챔피언</b></span>
	           			<select style="width:700px; border-color:rgb(126, 155, 255);" class="input-title" name="champion">
	           				<%
	           				for(int i=0; i<list.size(); i++) {
		      				%>
	           				<option><%=list.get(i).getName()%></option>
	           				<%
	           				}
	           				%>
	           			</select>
	           		<%	
	           		}
	           		%>
	           		<!-- 내용 -->
	           		
	           		<div style="height:50px;"></div>
	           		
	           		<div style="padding:20px 0px;">
	      				<div style="font-size:15px;"><b>본문</b></div>
	      				<div style="height:20px;"></div>
						<!-- form 안에 에디터를 사용하는 경우 (보통 이경우를 많이 사용하는듯)-->
						<textarea style="resize: none;" id="summernote" class="summernote" name="editordata"></textarea>
					</div>
					<input type="hidden" name="category" value="<%=category%>"/>
	      		</div>
				
				<div class="complete-btn-div">
					<div style="font-size:12px; color: darkgray;">
						※ 저작권 등 다른 사람의 권리를 침해하거나 명예를 훼손하는 게시글은 이용약관 및 관련법률에 의해 제재를<br/>
						받으실 수 있습니다.
					</div>
					<div style="padding-left: 250px;">
						<button type="submit" class="complete-btn" name="command" value="write">작성완료</button>
					</div>
				</div>
			</form>				
        </div>
    </main>
	
	<!-- 하단고정 -->
	<div class="top-button">
        <span style="color: #6c757d;">UP</span>
    </div>
	
   	<footer class="footer">

        <div class="footer-left">
            <span class="footer-left-item">공지사항</span>
            <span class="footer-left-item">버그리포팅</span>
            <span class="footer-left-item">파트너 신청</span>
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
<script src="Js/summernote.js"></script>
<script src="Js/summernote-ko-KR.js"></script>
<script src="Js/summernote-lite.js"></script>
</body>
</html>