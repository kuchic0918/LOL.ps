<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.yg_ac.dao.*" %>
<%@ page import="com.yg_ac.dto.*" %>
<!DOCTYPE html>
<%
	int bno = Integer.parseInt(request.getParameter("bno"));
	BoardDao bDao = new BoardDao();
	BoardDto dto = bDao.getDetail(bno);
	MemberDTO writer = bDao.getWriter(dto.getMemberkey());
	String introduce = writer.getIntroduce();
	if(writer.getIntroduce()==null){
		introduce = "";
	}
%>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><%=dto.getCategory() %></title>
	<link rel="stylesheet" href="Css/all.css">
    <link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <script src="Js/jquery-3.6.0.min.js"></script>
    <script>
    	$(function(){
    		
    	});
    </script>
</head>
<body class="community-body">
	<header class="all-header-mainnav header-mainnav">
        <div class="header-container">
            <a href="main.jsp">
                <img src="Images/header-logo.webp" alt="LOL.PS">
            </a>
            <div class = "nav-item-container">
            	<a class="nav-items" href="../notice/notice.html">공지사항</a>
                <a class="nav-items" href="ChampRank.jsp">챔피언 랭킹</a>
                <a class="nav-items" href="buildCommunity.jsp">빌드 연구소</a>
                <a class="nav-items" href="../community/free.html">자유게시판</a>
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
        <div class="first-title"><%=dto.getCategory() %></div>
        <div class="second-title">
        	<a class="main-button" href="write-post.html">✎게시물 쓰기</a>
        	<form method="post">
        		<input class="main-input" spellcheck="false" placeholder="챔피언이름을 입력하세요"/>
        	</form>
        </div>
        <div style="clear: both;"></div>
    </div>
	
	<!-- 중단 -->
    <main class="community-main">
		<div class="whiteDiv"></div>
      	<!-- 포스트 -->  	
      	<div class="community-post-post-detail">
      		<!-- 포스트제목 -->
      		<div class="title">
      			<div style="font-size:15px; color:#7e9bff;"><b><%=dto.getCategory() %></b></div>
      			<%
      			if(dto.getCategory().equals("자유 게시판")) {
      			%>
      			<h3 style="padding-top: 15px;"><%=dto.getTitle() %></h3>
      			<%	
      			} else {
      			%>
     			<h3 style="padding-top: 15px;">[<%=dto.getChampName() %>] <%=dto.getTitle() %></h3>
      			<%	
      			}
      			%>
      		</div>
      		<!-- 포스트내용 -->
      		<div class="write">
			<%=dto.getContent() %>
			</div>
      		<!-- 포스트글쓴이 -->
      		<div class="writer">
      			<div>
      				<img class="writer-img" src="Images/profile/<%=writer.getImage() %>"/>
      			</div>
      			
      			<div class="writer-mid">
      				<div style="padding:5px 10px; font-size:12px; color:darkgray;">글쓴이</div>
      				<div style="padding:5px 10px;"><b><%=writer.getNickname() %></b></div>
      				<div style="padding:5px 10px; font-size:15px; color:gray;"><%=introduce %></div>
      			</div>
      			
      			<div class="writer-bot">
      				<span>댓글 100 |</span>
      				<span> 조회수 <%=dto.getCount() %></span>
      			</div>
      		</div>
      		
      		<!-- 포스트하단 -->
           	<div class="content-function">
           		<a style="padding-left:30px;" class="prev-next" href="#">←이전</a>
           		
           		<div class="justify-content-start">
           			<button style="white-space:pre;" class="recommend" type="button"><span class="pre">&uarr;    </span>0</button>
           			<button style="white-space:pre;" class="recommend" type="button"><span class="pre">&darr;    </span>0</button>
           		</div>
           		
           		<a style="padding-right:30px;" class="prev-next" href="#">다음→</a>
           	</div>
        </div>
      	<div style="height:40px;"></div>
      	<!-- 댓글 -->
      	<div class="comments">
      		<img class="comments-img" src="../Images/anne1.jpg"/>
      		<div style="padding-left: 20px;">
      			<div style="padding-bottom: 5px; color:rgb(69, 76, 107); font-size:14px;"><b>인베당한 녹턴</b></div>
      			<div style="font-size:14px;">
      			이게 왜 좋아보이냐?
      			</div>
      			<div style="padding-top:5px">
      				<span style="font-size: 14px; color:gray;">2022년 3월 5일 2:53 오후</span>
      				<button class="write-comment">↳댓글</button>
      			</div>
      		</div>
      	</div>
      	<!-- 대댓글 -->
      	<div class="comments-comment">
      		<img class="comments-img" src="../Images/anne1.jpg"/>
      		<div style="padding-left: 20px;">
      			<div style="padding-bottom: 5px; color:rgb(69, 76, 107); font-size:14px;"><b>인베당한 녹턴</b></div>
      			<div style="font-size:14px;">
      			이게 왜 좋아보이냐?
      			</div>
      			<div style="padding-top:5px">
      				<span style="font-size: 14px; color:gray;">2022년 3월 5일 2:53 오후</span>
      				<button class="write-comment">↳댓글</button>
      			</div>
      		</div>
      	</div>
      	<!-- 댓글 -->
      	<div class="comments">
      		<img class="comments-img" src="../Images/anne1.jpg"/>
      		<div style="padding-left: 20px;">
      			<div style="padding-bottom: 5px; color:rgb(69, 76, 107); font-size:14px;"><b>인베당한 녹턴</b></div>
      			<div style="font-size:14px;">
      			이게 왜 좋아보이냐?
      			</div>
      			<div style="padding-top:5px">
      				<span style="font-size: 14px; color:gray;">2022년 3월 5일 2:53 오후</span>
      				<button class="write-comment">↳댓글</button>
      			</div>
      		</div>
      	</div>
		<!-- 댓글쓰기 -->
        <div class="bottom-comment">
        	<div class="comment">
           		<div class="comment-name">댓글쓰기</div>
           		<textarea class="comment-space"></textarea>
           		<button class="comment-regist" type="button">등록</button>
           	</div>
           	<div class="list-under-div">
           		<a style="border-radius:6%;" class="list-under" href="write-post.html">게시물 쓰기</a>
           		<a style="border-radius:10%;" class="list-under" href="build.html">목록</a>
     		</div>
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
</body>
</html>