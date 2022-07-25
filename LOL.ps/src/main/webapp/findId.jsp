<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" href="Css/all.css">
<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css'
	rel='stylesheet' type='text/css'>
<script src="Js/jquery-3.6.0.min.js"></script>
<%
if("notfound".equals((String) request.getAttribute("find"))) {
%>
	<script>
		alert('회원정보가 없습니다.');
	</script>
<%	
}else if("found".equals((String) request.getAttribute("find"))) {
%>
	<script>
		alert('회원가입된 아이디 입니다.');
	</script>
<%	
}
%>
</head>
<body class="member-body">
	<header class="header-mainnav">
		<div class="header-container">
			<a href="main.jsp"> <img src="Images/header-logo.webp"
				alt="LOL.PS"></a>
			<div class="nav-item-container">
				<a class="nav-items" href="community.jsp?category=공지사항">공지사항</a> <a
					class="nav-items" href="ChampRank.jsp">챔피언 랭킹</a> <a
					class="nav-items" href="community.jsp?category=빌드 연구소">빌드 연구소</a> <a
					class="nav-items" href="community.jsp?category=자유 게시판">자유 게시판</a>
			</div>
			<div class="sign-login">
				<a class="signin" href="signin.jsp">회원가입</a> <a class="login"
					href="login.jsp">로그인</a>
			</div>
		</div>
	</header>
	<div class="all-main">
		<main class="member-main">
			<div class="member-side-left" style="height: 754.578;">
				<section class="member-ad"></section>
			</div>
			<div class="member-contents">
				<div class=" member-row-sign">
					<div class="member-col-12">
						<h2 class="member-page-title">아이디 찾기</h2>
					</div>
					<div class="member-row">
						<div class="member-email_login_box" style="margin-top: 130px;">
							<h3 class="member-h3" style="margin-left: 23px;">아이디 찾기</h3>
							
							<form class="member-form" method="post" action="Controller" novalidate="novalidate">
								<input type="hidden" name="csrfmiddlewaretoken" value="7HxOd1EHsTRdbB8LGU9AhEcjiPyXKw6HMAlOdG0UPnD8b8s0Ys2BKaMtsP9rRRLa">
								<div class="member-form-group">
									<label class="member-label" for="id_email">이메일 주소</label> 
									<input required type="email" name="email" class="member-form-control" placeholder="gamer@lol.ps" id="id_email">
								</div>
								<button type="submit" class="member-btn-theme member-btn-big" style="margin-left: 31px;" name="command" value="findId">
								이메일 아이디 찾기</button>
							</form>
							
						</div>
					</div>
				</div>
			</div>
		</main>
	</div>
	<footer class="footer">

		<div class="footer-left">
			<span class="footer-left-item">공지사항</span> <span
				class="footer-left-item">버그리포팅</span> <span class="footer-left-item">파트너
				신청</span><br />
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