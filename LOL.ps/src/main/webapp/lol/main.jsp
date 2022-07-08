<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.yg_ac.dao.*" %>
<%@ page import="com.yg_ac.dto.*" %>
<%@ page import="java.util.*" %>
<%
	Y_DBmanager db = new Y_DBmanager();
	
	ResultSet rs = null;
	Connection conn = db.getConnection();
	PreparedStatement pstmt = null;
	
	MainCardDao mainCardDao = new MainCardDao();
	
	ArrayList<MainCardDto> mainCardList =  mainCardDao.getMainCard(conn, pstmt, rs);
%>
<!DOCTYPE html>
<html>
<%
	if(request.getParameter("searchedChampion")!=null) {
%>
		<script>
			alert('챔피언 검색 결과가 없습니다.');
			history.back();
		</script>
<%
	}
%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메인페이지</title>
    <link rel="stylesheet" href="Css/all.css">
    <link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css"/>
    <script src="Js/jquery-3.6.0.min.js"></script>
    <script>
    	$(function() {
    		
    		$('.main-small-card-image').click(function (e) {
    		    $('.main-slide-image-small').parent().parent().css({display: 'block'})
    		    $('.main-swiper-wrapper').remove();

    		    var name = $(this).siblings('input').eq(0).val();
    			var line = $(this).siblings('input').eq(1).val();
    			//alert('name' + name + 'line' + line);
	    			$.ajax({
	    				type: 'post',
	    				url: '../MainCard',
	    				data: { name : name, line : line },
	    				datatype: 'json',
	    				success: function(data) {
	    					let card = `<div class="main-swiper-wrapper swiper-slide">
	    		                   <div class="main-swiper-slide">
	    		                       <div class="main-slide-image">
	    		                           <img class="main-small-card-image" style="border-radius: 8px 0px 0px 8px" src="Images/champion/full/\${data.image}">
	    		                       </div>
	    		       
	    		                       <div class="main-champslide-infobox">
	    		                           <div class="champ-status">
	    		                               <h2 style = "margin:0; margin-bottom : 20px;">\${data.name}(\${data.line})</h2>
	    		       
	    		                               <li class="main-champ-status-header">
	    		                                   <span></span>
	    		                                   <span class="main-before">이전패치</span>
	    		                                   <span>현재패치</span>
	    		                               </li>
	    		                               <li class="main-champ-status-item">
	    		                                   <h4>승률</h4>
	    		                                   <span class="main-before">\${data.winRateBefore}</span>
	    		                                   <span>\${data.winRate}</span>
	    		                               </li>
	    		                               <li class="main-champ-status-item">
	    		                                   <h4>픽률</h4>
	    		                                   <span class="main-before">\${data.pickRateBefore}</span>
	    		                                   <span>\${data.pickRate}</span>
	    		                               </li>
	    		                               <li class="main-champ-status-item">
	    		                                   <h4>벤율</h4>
	    		                                   <span class="main-before">\${data.banRateBefore}</span>
	    		                                   <span>\${data.banRate}</span>
	    		                               </li>
	    		                           </div>
	    		       
	    		                           <div class="main-champ-point">
	    		                               <h4>주목해야 할 포인트</h4>
	    		                               <p style="font-size: 12px;">최근 패치에서 \${data.name}의 승률이 \${data.winVari} 변동 되었습니다. 곧 구체적인 원인을 분석하여 업데이트 될 예정입니다.</p>
	    		                           </div>
	    		       
	    		                           <div style="width:135px; padding: 10px 0px 0px 170px;">
	    		                               <a class="main-champ-link" href="statistics.jsp?name=\${data.name}&line=\${data.line}">챔피언 상세정보 →</a>
	    		                           </div>
	    		                       </div>
	    		                   </div>
	    		               </div>`;
	    		            $(e.target).parent().parent().parent().before(card);
	    		            $(e.target).parent().parent().parent().css({display: 'none'});
	    				},
	    				error: function(r,s,e) {
	    					alert('에러');
	    				}
    			});
    		})
    	});
    </script>
</head>
<body class="main-body">

<div class="loader"></div>

    <header class="header-mainnav">
        <div class="header-container">
            <a href="main.jsp">
                <img src="Images/header-logo.webp" alt="LOL.PS">
            </a>
            <div class = "nav-item-container">
            	<a class="nav-items" href="../notice/notice.html">공지사항</a>
                <a class="nav-items" href="ChampRank.jsp">챔피언 랭킹</a>
                <a class="nav-items" href="../community/build.html">빌드게시판</a>
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
            			<form class="signin" action= "MypageServlet" >마이페이지</form>
		                <form class="login" action = "LogoutServlet" >로그아웃</form>     
            	<%
            		}
            	%>
            </div>
        </div>
    </header>
    <main class="main-main">
        <div class="main-search">

            <form action="../MainStatistics" method="get" id="search_form" style="z-index:11; position : relative;" autocomplete="off">
                <div class="main-searchbar">
                    <div style="width: 560px;">
                        <input type="text" name="name" placeholder="챔피언 이름을 입력하세요">
                    </div>
                    <button type="submit">
                        <img src="Images/icon_search.webp" style="width: 20px; height: 20px;">
                    </button>
                </div>

                <div class="main-realtime">
                    <span class="main-realspan">실시간 검색어</span>
                   	<img src="Images/icon_chevron-white.png" alt="img" style="float : right; cursor:pointer;" onclick="callFunction();">
                    <div class="main-word_wrapper main-overflow">
                        <ul class="main-ul" id="realtime_search">
                        	<%
			                    for(int i = 0; i < mainCardList.size(); i++) {
			                    %>
				                <li>
                            		<a class="main-realtime_word main-realtime-color-white" href="statistics.jsp?name=<%=mainCardList.get(i).getName() %>&line=<%=mainCardList.get(i).getLine() %>">
                               		 	<span><%=i+1 %></span><%=mainCardList.get(i).getName() %>
                            		</a>
                           		 </li>
			                    <%
			                    }
		                    %>    
                        </ul>
                    </div>
                </div>
            </form>
        </div>

        <h3  style="color: #fff;">주목해야 할 챔피언 (KR) <small style="color: darkgray;">05-24 10:46 기준</small></h3>
         <div class="swiper">
            <div class="main-mainslide_container swiper-wrapper" style="color: #fff; z-index: 1;">
                    <%
                    for(int i = 0; i < mainCardList.size(); i++) {
                    %>
					<div class="main-swiper-wrapper-small swiper-slide">
	                    <div class="main-swiper-slide">
	                        <div class="main-slide-image-small">
	                            <input type="hidden" name="name" value="<%=mainCardList.get(i).getName() %>">
	                            <input type="hidden" name="line" value="<%=mainCardList.get(i).getLine() %>">
	                            <img class="main-small-card-image" src="Images/champion/full/<%=mainCardList.get(i).getImage() %>">
	                            <h3 class="main-champion-name"><%=mainCardList.get(i).getName() %><small>(<%=mainCardList.get(i).getLine() %>)</small></h3>
	                        </div>
	                    </div>
	                </div>
                    <%
                    }
                    %>    
            </div>
        </div>
    </main>

    <div style="height: 300px;"></div>


    <div class="main-community-container">
        <div class="main-community">
            <h3 class="main-title">빌드 연구소</h3>
            <ul class="main-community-list-container">
                <li class="main-community-list" style="border-top: 1px solid #dbdde1;">
                    <span class="main-post-name">바루스</span>
                    <a class="main-post-link" href="#">
                        <span class="main-post-title">다이아1 승률 58%  AP 바루스</span>
                        <span class="main-post-comment">[3]</span>
                    </a>
                    <span class="main-post-like">4</span>
                </li>
                <li class="main-community-list">
                    <span class="main-post-name">바루스</span>
                    <a class="main-post-link" href="#">
                        <span class="main-post-title">다이아1 승률 58%  AP 바루스</span>
                        <span class="main-post-comment">[3]</span>
                    </a>
                    <span class="main-post-like">4</span>
                </li>
                <li class="main-community-list">
                    <span class="main-post-name">바루스</span>
                    <a class="main-post-link" href="#">
                        <span class="main-post-title">다이아1 승률 58%  AP 바루스</span>
                        <span class="main-post-comment">[3]</span>
                    </a>
                    <span class="main-post-like">4</span>
                </li>
                <li class="main-community-list">
                    <span class="main-post-name">바루스</span>
                    <a class="main-post-link" href="#">
                        <span class="main-post-title">다이아1 승률 58%  AP 바루스</span>
                        <span class="main-post-comment">[3]</span>
                    </a>
                    <span class="main-post-like">4</span>
                </li>
                <li class="main-community-list">
                    <span class="main-post-name">바루스</span>
                    <a class="main-post-link" href="#">
                        <span class="main-post-title">다이아1 승률 58%  AP 바루스</span>
                        <span class="main-post-comment">[3]</span>
                    </a>
                    <span class="main-post-like">4</span>
                </li>
                <li class="main-community-list">
                    <span class="main-post-name">바루스</span>
                    <a class="main-post-link" href="#">
                        <span class="main-post-title">다이아1 승률 58%  AP 바루스</span>
                        <span class="main-post-comment">[3]</span>
                    </a>
                    <span class="main-post-like">4</span>
                </li>
                <li class="main-community-list">
                    <span class="main-post-name">바루스</span>
                    <a class="main-post-link" href="#">
                        <span class="main-post-title">다이아1 승률 58%  AP 바루스</span>
                        <span class="main-post-comment">[3]</span>
                    </a>
                    <span class="main-post-like">4</span>
                </li>
                <li class="main-community-list">
                    <span class="main-post-name">바루스</span>
                    <a class="main-post-link" href="#">
                        <span class="main-post-title">다이아1 승률 58%  AP 바루스</span>
                        <span class="main-post-comment">[3]</span>
                    </a>
                    <span class="main-post-like">4</span>
                </li>
                <li class="main-community-list">
                    <span class="main-post-name">바루스</span>
                    <a class="main-post-link" href="#">
                        <span class="main-post-title">다이아1 승률 58%  AP 바루스</span>
                        <span class="main-post-comment">[3]</span>
                    </a>
                    <span class="main-post-like">4</span>
                </li>
                <li class="main-community-list" style="border-bottom: 1px solid #dbdde1;">
                    <span class="main-post-name">바루스</span>
                    <a class="main-post-link" href="#">
                        <span class="main-post-title">다이아1 승률 58%  AP 바루스</span>
                        <span class="main-post-comment">[3]</span>
                    </a>
                    <span class="main-post-like">4</span>
                </li>


            </ul>
        </div>

        <div class="main-community">
            <h3 class="main-title">빌드 연구소</h3>
            <ul class="main-community-list-container">
                <li class="main-community-list" style="border-top: 1px solid #dbdde1;">
                    <span class="main-post-name">바루스</span>
                    <a class="main-post-link" href="#">
                        <span class="main-post-title">다이아1 승률 58%  AP 바루스</span>
                        <span class="main-post-comment">[3]</span>
                    </a>
                    <span class="main-post-like">4</span>
                </li>
                <li class="main-community-list">
                    <span class="main-post-name">바루스</span>
                    <a class="main-post-link" href="#">
                        <span class="main-post-title">다이아1 승률 58%  AP 바루스</span>
                        <span class="main-post-comment">[3]</span>
                    </a>
                    <span class="main-post-like">4</span>
                </li>
                <li class="main-community-list">
                    <span class="main-post-name">바루스</span>
                    <a class="main-post-link" href="#">
                        <span class="main-post-title">다이아1 승률 58%  AP 바루스</span>
                        <span class="main-post-comment">[3]</span>
                    </a>
                    <span class="main-post-like">4</span>
                </li>
                <li class="main-community-list">
                    <span class="main-post-name">바루스</span>
                    <a class="main-post-link" href="#">
                        <span class="main-post-title">다이아1 승률 58%  AP 바루스</span>
                        <span class="main-post-comment">[3]</span>
                    </a>
                    <span class="main-post-like">4</span>
                </li>
                <li class="main-community-list">
                    <span class="main-post-name">바루스</span>
                    <a class="main-post-link" href="#">
                        <span class="main-post-title">다이아1 승률 58%  AP 바루스</span>
                        <span class="main-post-comment">[3]</span>
                    </a>
                    <span class="main-post-like">4</span>
                </li>
                <li class="main-community-list">
                    <span class="main-post-name">바루스</span>
                    <a class="main-post-link" href="#">
                        <span class="main-post-title">다이아1 승률 58%  AP 바루스</span>
                        <span class="main-post-comment">[3]</span>
                    </a>
                    <span class="main-post-like">4</span>
                </li>
                <li class="main-community-list">
                    <span class="main-post-name">바루스</span>
                    <a class="main-post-link" href="#">
                        <span class="main-post-title">다이아1 승률 58%  AP 바루스</span>
                        <span class="main-post-comment">[3]</span>
                    </a>
                    <span class="main-post-like">4</span>
                </li>
                <li class="main-community-list">
                    <span class="main-post-name">바루스</span>
                    <a class="main-post-link" href="#">
                        <span class="main-post-title">다이아1 승률 58%  AP 바루스</span>
                        <span class="main-post-comment">[3]</span>
                    </a>
                    <span class="main-post-like">4</span>
                </li>
                <li class="main-community-list">
                    <span class="main-post-name">바루스</span>
                    <a class="main-post-link" href="#">
                        <span class="main-post-title">다이아1 승률 58%  AP 바루스</span>
                        <span class="main-post-comment">[3]</span>
                    </a>
                    <span class="main-post-like">4</span>
                </li>
                <li class="main-community-list" style="border-bottom: 1px solid #dbdde1;">
                    <span class="main-post-name">바루스</span>
                    <a class="main-post-link" href="#">
                        <span class="main-post-title">다이아1 승률 58%  AP 바루스</span>
                        <span class="main-post-comment">[3]</span>
                    </a>
                    <span class="main-post-like">4</span>
                </li>
            </ul>
        </div>
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