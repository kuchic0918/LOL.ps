<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.yg_ac.dao.*" %>
<%@ page import="com.yg_ac.dto.*" %>
<!DOCTYPE html>
<%	
	int pageNum;
	if(request.getParameter("page")==null){
		pageNum = 1;
	}else{
		pageNum = Integer.parseInt(request.getParameter("page"));
	}
	String category = request.getParameter("category");
	int startBno = pageNum * 15 - 14;
	int endBno = pageNum * 15;
	
	String categoryImage;
	if(category.equals("자유 게시판")) {
		categoryImage = "소환사";
	}else {
		categoryImage = "챔피언";
	}
	
	BoardDao bDao = new BoardDao();
	ArrayList<BoardDto> list = new ArrayList<BoardDto>();
	list = bDao.getBoardList(category, startBno, endBno);
	int allList = bDao.getAllBoardList(category);
%>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>빌드 연구소</title>
	<link rel="stylesheet" href="Css/all.css">
    <link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <script src="Js/jquery-3.6.0.min.js"></script>
    <script>
    	$(function(){
    		
    	});
    </script>
</head>
<body  class="community-body">
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
        <div class="first-title"><%=category %></div>
        <div class="second-title">
        	<a class="main-button" href="write-post.html">✎게시물 쓰기</a>
        	<form method="post">
        		<input class="main-input" spellcheck="false" placeholder="챔피언이름을 입력하세요"/>
        	</form>
        </div>
        <div style="clear: both;"></div>
    </div>
	
    <main class="community-main">
        <div class="contents">
           	<div class="whiteDiv"></div>
           	<div class="firstDiv">
           		<div class="firstDivLeft"><%=category %></div>
           		<div class="justify-content-start">
           			<select class="select">
           				<option>제목</option>
           				<option>작성자</option>
           			</select>
           			<input class="contents-input" spellcheck="false" placeholder="게시물 검색"/>
           			<button class="main-button">✎게시물쓰기</button>
           		</div>
           	</div>
           	<div class="title-build">
           		<span style="padding-left:8px;"><%=categoryImage%></span>
           		<span style="padding-left:8px;">제목</span>
           		<span style="padding-left:660px;">작성자</span>
           		<span style="padding-left:50px;">날짜</span>
           		<span style="padding-left:40px;">조회</span>
           		<span style="padding-left:22px;">추천</span>
           	</div>
        </div>
        <div>
        	<%
        	for(BoardDto dto:list){
        		String image = bDao.getImage(dto.getChampName());
        		MemberDTO writer = bDao.getWriter(dto.getMemberkey());
        		int like = dto.getGood()-dto.getBad();
        		if(like < 0){
        			like = 0;
        		}
        	%>
	        <a class="contents-item" href="ViewDetail.jsp?bno=<%=dto.getBno()%>">
           		<%
         		if(category.equals("자유 게시판")){
          		%>
          		<span class="build">
          			<img class="champion-head" src="Images/profile/<%=writer.getImage()%>"/>
          		</span>
          		<span class="build1">
          			 <%=dto.getTitle() %>
          		</span>
          		<%
          		} else {
           		%>
       			<span class="build">
          			<img class="champion-head" src="Images/champion/head/<%=image%>"/>
          		</span>
          		<span class="build1">
          			 [<%=dto.getChampName()%>]<%=dto.getTitle() %>
          		</span>
	           	<%
           		}
           		%>
           		<span class="build2" style="width:150px;">
           			<%=writer.getNickname() %>
           		</span>
           		<span class="build2" style="width:100px;">
           			<%=dto.getWritedate() %>
           		</span>
           		<span class="build2" style="width:30px;">
           			<%=like %>
           		</span>
           		<span class="build2" style="width:30px">
           			<%=dto.getCount() %>
           		</span>
			</a>
			<%
        	}
			%>
        </div>
		
    </main>
    
    <div class="bottom-btn">
    	<%
    	String firstPage = "bottom-btn-in";
    	String endPage = "bottom-btn-in";
    	String href = "";
    	String href2 = "";
    	if(pageNum==1){
    		firstPage = "bottom-btn-in-off";
    		href = "";
    	}else{
    		firstPage = "bottom-btn-in";
    		href = "community.jsp?category="+category+"&page=1";
    	}
    	%>
		<a class="<%=firstPage %>" href="<%=href %>" id="first"><<</a>
		<%
    	
		int end = allList/15+1;
		String act = "bottom-btn-in";
		for(int i=1;i<=end;i++){
			if(i==pageNum){
				act = "bottom-btn-in-active";
			}else{
				act = "bottom-btn-in";
			}
		%>
		<a class="<%=act%>" href="community.jsp?category=<%=category %>&page=<%=i %>"><%=i %></a>
		<%
		}
		
		if(pageNum==end){
			endPage = "bottom-btn-in-off";
			href2 = "";
		}else{
			endPage = "bottom-btn-in";
			href2 = "community.jsp?category="+category+"&page="+end;
		}
		%>
		<a class="<%=endPage %>" href="<%=href2 %>" id="end">>></a>
		<%
		
		%>
		<span class="bottom-btn-in2">
			<button class="main-button">✎게시물쓰기</button>
		</span>
		
	</div>
	
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
</body>
</html>