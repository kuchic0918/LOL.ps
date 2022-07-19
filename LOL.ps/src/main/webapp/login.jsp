<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


<% 
	if("wrong".equals((String) request.getAttribute("member"))) {
		%>
<script>
				alert('다시 로그인 해주세요.');
			</script>
<% 
	} else if("passwordWrong".equals((String) request.getAttribute("member"))) {
		%>
<script>
				alert('비밀번호가 올바르지 않습니다.');
			</script>
<%
	} else if("emailWrong".equals((String) request.getAttribute("member"))) {
		%>
<script>
				alert('이메일이 올바르지 않습니다.');
			</script>
<%
	} else if("passwordAlright".equals((String) request.getAttribute("mypage"))) {
		%>
<script>
				alert('비밀번호가 변경되었습니다. 다시로그인 해주세요.');
			</script>
<%
	} else if("secession".equals((String) request.getAttribute("mypage"))) {
		%>
<script>
				alert('회원탈퇴.');
			</script>
<%
	} else if("success".equals((String) request.getAttribute("signin"))) {
		%>
<script>
				alert("회원가입이 완료되었습니다. 로그인 해주세요.");
			</script>
<% 	
	}
%>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>
<link rel="stylesheet" href="Css/all.css">
<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css'
	rel='stylesheet' type='text/css'>
<script src="Js/jquery-3.6.0.min.js"></script>
<script src="https://accounts.google.com/gsi/client" async defer></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src='https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js'></script>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script>
    	$(function() {    		
			$(".member-form").validate({
				rules : {
					email : {
						required : true,
						email : true
					},
					password : {
						required :true,
						minlength  : 1
					},
				},
				messages : {
					email : {
						required : '필수사항을 입력하세요',
						email : "형식을 지켜주세요"
					},
					password : {
						required : '필수사항을 입력하세요',
						minlength  : '최소8자 이상 입력하세요'
					}
				},
    		});
		});  	
    </script>
</head>
<body class="member-body">
	<!-- 위에 navbar 부분입니다 -->
	<header class="header-mainnav">
		<div class="header-container">
			<a href="main.jsp"> <img src="Images/header-logo.webp"
				alt="LOL.PS">
			</a>
			<div class="nav-item-container">
				<a class="nav-items" href="../notice/notice.html">공지사항</a> <a
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
					<h2 class="member-page-title">로그인</h2>
				</div>
				<div class="member-row">
					<div class="member-email_login_box">
						<h3 class="member-h3">이메일 로그인</h3>
						<form class="member-form" method="post" action="Controller"
							novalidate="novalidate">
							<input type="hidden" name="csrfmiddlewaretoken"
								value="7HxOd1EHsTRdbB8LGU9AhEcjiPyXKw6HMAlOdG0UPnD8b8s0Ys2BKaMtsP9rRRLa">
							<div class="member-form-group">
								<label class="member-label" for="id_email">이메일 주소</label> <input
									required type="email" name="email" class="member-form-control"
									placeholder="gamer@lol.ps" id="id_email">
							</div>
							<div class="member-form-group">
								<label class="member-label" for="id_password">비밀번호</label> <input
									required type="password" name="password"
									class="member-form-control" placeholder="비밀번호" id="id_password">
							</div>
							<button type="submit" class="member-btn-theme member-btn-big"
								name="command" value="login">이메일 계정 로그인</button>
							<div class="member-text-center">
								<a href="#" class="member-btn-light-theme member-btn-small">이메일
									찾기</a> <a href="#" class="member-btn-light-theme member-btn-small">비밀번호
									찾기</a>
							</div>
						</form>
					</div>

					<div class="member-sign-sns signin">
						<h3 class="member-h3" style = "margin-left : 38px;" >소셜계정으로 로그인</h3>
						<ul class="member-ul">
							<li class="member-li">
								<a href="javascript:kakaoLogin();">
									<img src="Images/kakao_login_medium_narrow.png" alt="카카오계정 로그인" style="height: 50px;" />
								</a> 
								<script type="text/javascript">
								 	 window.Kakao.init('e1ef84940dda0239cd6f5ad9c860e5b6');
								 		function kakaoLogin(){
								 			window.Kakao.Auth.login({
								 				scope:'account_email , profile_nickname',
								 				success:function(response) {
								 					console.log(response) // 로그인 성공하면 받아오는 데이터
								                    window.Kakao.API.request({ // 사용자 정보 가져오기 
								                        url: '/v2/user/me',
								                        success: (res) => {
								                            const kakao_account = res.kakao_account;
								                            console.log(kakao_account);
								                            var kakaoEmail =res.kakao_account.email ;
								                            var kakaoNickname =res.kakao_account.profile.nickname;
								                            location.href = "Controller?command=kakaoLogin&kakaoEmail="+kakaoEmail+"&kakaoNickname="+kakaoNickname;
								 				}
								 			});
								 		},
								 		fail:function(error) {
								 			console.log(error);
								 			}
								 		});
								 		}
							 	</script>
							</li>
							<li class="member-li"><a id="naver_id_login"> <script
										type="text/javascript">
											  	var naver_id_login = new naver_id_login("79hXuwg9931gTF0Q5VRD", "http://localhost:9090/LOL.ps/naverCallback.jsp");
											  	var state = naver_id_login.getUniqState();
											  	naver_id_login.setButton("green", 3,30);
											  	naver_id_login.setDomain("http://localhost:9090/LOL.ps/login.jsp");
											  	naver_id_login.setState(state);
// 											  	naver_id_login.setPopup();
											  	naver_id_login.init_naver_id_login();
  										</script>
							</a></li>
							<li class = "member-li">
	                            	<script>
								        function handleCredentialResponse(response) {
								        	const responsePayload = parseJwt(response.credential);
								        	console.log("ID: " + responsePayload.sub);
								            console.log('Full Name: ' + responsePayload.name);
								            console.log('Given Name: ' + responsePayload.given_name);
								            console.log('Family Name: ' + responsePayload.family_name);
								            console.log("Image URL: " + responsePayload.picture);
								            console.log("Email: " + responsePayload.email);
								            var googleEmail = responsePayload.email;
								            var googleNickname = responsePayload.name;
								            location.href = "Controller?command=googleLogin&googleEmail="+googleEmail+"&googleNickName="+googleNickname;
								            
								        }
								        function parseJwt (token) {
								            var base64Url = token.split('.')[1];
								            var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
								            var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
								                return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
								            }).join(''));
								
								            return JSON.parse(jsonPayload);
								        };
								        window.onload = function () {
								          google.accounts.id.initialize({
								            client_id: "617468967353-01kodva3scdm890l5ahl1t02rm12b42h.apps.googleusercontent.com",
								            callback: handleCredentialResponse
								          });
								          google.accounts.id.renderButton(
								            document.getElementById("buttonDiv"),
								            { theme: "outline", size: "middle" }  // customization attributes
								          );
								          google.accounts.id.prompt(); // also display the One Tap dialog
								        }
							    </script>
							    <div id="buttonDiv"></div> 
	                            </li>

						</ul>
					</div>
				</div>
			</div>
		</div>

		</main>

	</div>

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