<%@page import="org.apache.catalina.tribes.membership.MemberImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "yg_ac_java.*"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.yg_ac.dao.*" %>
<%@ page import="com.yg_ac.dto.*" %>
<%
	MemberDTO member = (MemberDTO) session.getAttribute("memberInfo");
%>
<!DOCTYPE html>
<html>
<%	
	if("passwordWrong".equals((String) request.getAttribute("mypage"))) {
		%>
			<script>
				alert('현재 비밀번호가 맞지 않습니다.');
			</script>
		<%
	}else if("passwordAlright".equals((String) request.getAttribute("mypage"))) {
		%>
			<script>
				alert('비밀번호가 변경되었습니다.');
			</script>
		<%
	}else if("imageChange".equals((String) request.getAttribute("mypage"))) {
		%>
			<script>
				alert('프로필사진이 변경되었습니다.');
			</script>
		<%
	}else if("profile".equals((String) request.getAttribute("mypage"))) {
		%>
			<script>
				alert('프로필글이 변경되었습니다.');
			</script>
		<%
	}
	MypageIntroduceDto mypageIntroduce = null;
	MypageIntroduceDto mypageImage = null;
	if(member==null) {
		%>
			<script>
				alert('로그인 먼저 하세요.');
				location.href = 'login.jsp';
			</script>
		<%
	} else {
		MemberDAO mDao = new MemberDAO();
		mypageIntroduce = mDao.getMypageIntroduce(member.getMemberkey());
		mypageImage = mDao.getMypageImage(member.getMemberkey());		
	}
%>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
    <link rel="stylesheet" href="Css/all.css">
    <link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <script src="Js/jquery-3.6.0.min.js"></script>
    <script src = 'https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js'></script>
    <script>
    	$(function() {
			//인풋 비우기
			function clear() {
				$('#now-password').val('')
				$('#new-password').val('')
				$('#new-password-confirm').val('')
				$('#password-confirm-tag').html('')
			}

    		//프로필 설정
    		$(".change-img div.profile-settings").hover(function() {
    			$(this).find("img:first-child").animate({opacity:0},250);// ('opacity','0');
    			$(this).find("img").eq(1).animate({opacity:1},250); // .css('opacity','1');
    			$(this).find("button").animate({opacity:1},250); //css('opacity','1');
    			$(this).find("img").eq(2).animate({opacity:1},250);  //.css('opacity','1');
    		}, function() {
    			$(this).find("img:first-child").animate({opacity:1});  //.css('opacity','1');
    			$(this).find("img").eq(1).animate({opacity:0});  //.css('opacity','0');
    			$(this).find("button").animate({opacity:0});  //.css('opacity','0');
    			$(this).find("img").eq(2).animate({opacity:0});  //.css('opacity','0');
    		});
    		//한줄소개변경
    		$('#introduce-modal-id').animate({opacity:0},25);
    		$('#change-pw-modal-id').animate({opacity:0},25);
    		$('#member-secession-modal-id').animate({opacity:0},25);
    		
    		$("#introduce-btn").click(function() {
    			$('#fake-body').animate({opacity:0.7},25);
    			$('#introduce-modal-id').animate({opacity:1});
    			
    			$('.background-gray').removeClass('modal-del');
    			$('.background-gray').addClass('modal-show');
    			$('html, body').addClass('modal-body');
    			
    			$('#introduce-modal-id').css({
					"margin-top" : (($(window).height() - $('#introduce-modal-id').outerHeight())/2 + $(window).scrollTop()) + "px",
					"margin-left" : (($(window).width() - $('#introduce-modal-id').outerWidth())/2 + $(window).scrollLeft()) + "px"
					});
    			
    			$('.background-gray').click(function(e) {
    				if(e.target.id === 'background-gray-id') {
    					$('#fake-body').animate({opacity:1});
    					$('#introduce-modal-id').animate({opacity:0},25);
    					$('.background-gray').removeClass('modal-show');
    					$('.background-gray').addClass('modal-del');
    					
    					$('html, body').removeClass('modal-body');
    					$('#element').off('scroll touchmove mousewheel', function(event) {
    		    			event.preventDefault();
    		    			event.stopPropagation();
    		    			return false;
    		    		});
    				}
    			});
    			$('.introduce-modal-btn').click(function(e) {
    				if(e.target.id === 'introduce-modal-btn-id') {
    					$('#fake-body').animate({opacity:1});
    					$('#introduce-modal-id').animate({opacity:0},25);
    					$('.background-gray').removeClass('modal-show');
    					$('.background-gray').addClass('modal-del');
    					
    					$('html, body').removeClass('modal-body');
    					$('#element').off('scroll touchmove mousewheel', function(event) {
    		    			event.preventDefault();
    		    			event.stopPropagation();
    		    			return false;
    		    		});
    				}
    			});
    			$('.btn-save').click(function(e) {
    				if(e.target.id === 'btn-save-id') {
    					$('#fake-body').animate({opacity:1});
    					$('#introduce-modal-id').animate({opacity:0},25);
    					$('.background-gray').removeClass('modal-show');
    					$('.background-gray').addClass('modal-del');
    					
    					$('html, body').removeClass('modal-body');
    					$('#element').off('scroll touchmove mousewheel', function(event) {
    		    			event.preventDefault();
    		    			event.stopPropagation();
    		    			return false;
    		    		});
    				}
    			});
    			
    			$('#element').on('scroll touchmove mousewheel', function(event) {
    			  event.preventDefault();
    			  event.stopPropagation();
    			  return false;
    			});
    		});
    		// 비밀번호 변경
    		$("#change-pw-btn").click(function() {
    			$('#fake-body').animate({opacity:0.7},25);
    			$('#change-pw-modal-id').animate({opacity:1});
    			
    			$('.background-gray2').removeClass('modal-del');
    			$('.background-gray2').addClass('modal-show');
    			$('html, body').addClass('modal-body');
    			
    			$('#change-pw-modal-id').css({
					"margin-top" : (($(window).height() - $('#change-pw-modal-id').outerHeight())/2 + $(window).scrollTop()) + "px",
					"margin-left" : (($(window).width() - $('#change-pw-modal-id').outerWidth())/2 + $(window).scrollLeft()) + "px"
					});
    			
    			$('.background-gray2').click(function(e) {
    				if(e.target.id === 'background-gray-id2') {
    					$('#fake-body').animate({opacity:1});
    					$('#change-pw-modal-id').animate({opacity:0},25);
    					$('.background-gray2').removeClass('modal-show');
    					$('.background-gray2').addClass('modal-del');
    					
    					$('html, body').removeClass('modal-body');
    					$('#element').off('scroll touchmove mousewheel', function(event) {
    		    			event.preventDefault();
    		    			event.stopPropagation();
    		    			return false;
    		    		});
						clear();
    				}
					
    			});
    			
    			$('.change-pw-modal-btn').click(function(e) {
    				if(e.target.id === 'change-pw-modal-btn-id') {
    					$('#fake-body').animate({opacity:1});
    					$('#change-pw-modal-id').animate({opacity:0},25);
    					$('.background-gray2').removeClass('modal-show');
    					$('.background-gray2').addClass('modal-del');
    					
    					$('html, body').removeClass('modal-body');
    					$('#element').off('scroll touchmove mousewheel', function(event) {
    		    			event.preventDefault();
    		    			event.stopPropagation();
    		    			return false;
    		    		});
    				}
					clear();
    			});
				
    			$('#element').on('scroll touchmove mousewheel', function(event) {
    			  event.preventDefault();
    			  event.stopPropagation();
    			  return false;
    			});
    		});
    		//회원탈퇴
    		$("#member-secession-btn").click(function() {
    			$('#fake-body').animate({opacity:0.7},25);
    			$('#member-secession-modal-id').animate({opacity:1});
    			
    			$('.background-gray3').removeClass('modal-del');
    			$('.background-gray3').addClass('modal-show');
    			$('html, body').addClass('modal-body');
    			
    			$('#member-secession-modal-id').css({
					"margin-top" : (($(window).height() - $('#member-secession-modal-id').outerHeight())/2 + $(window).scrollTop()) + "px",
					"margin-left" : (($(window).width() - $('#member-secession-modal-id').outerWidth())/2 + $(window).scrollLeft()) + "px"
					});
    			
    			$('.background-gray3').click(function(e) {
    				if(e.target.id === 'background-gray-id3') {
    					$('#fake-body').animate({opacity:1});
    					$('#member-secession-modal-id').animate({opacity:0},25);
    					$('.background-gray3').removeClass('modal-show');
    					$('.background-gray3').addClass('modal-del');
    					
    					$('html, body').removeClass('modal-body');
    					$('#element').off('scroll touchmove mousewheel', function(event) {
    		    			event.preventDefault();
    		    			event.stopPropagation();
    		    			return false;
    		    		});
    				}
    			});
    			$('.member-secession-modal-btn').click(function(e) {
    				if(e.target.id === 'member-secession-modal-btn-id') {
    					$('#fake-body').animate({opacity:1});
    					$('#member-secession-modal-id').animate({opacity:0},25);
    					$('.background-gray3').removeClass('modal-show');
    					$('.background-gray3').addClass('modal-del');
    					
    					$('html, body').removeClass('modal-body');
    					$('#element').off('scroll touchmove mousewheel', function(event) {
    		    			event.preventDefault();
    		    			event.stopPropagation();
    		    			return false;
    		    		});
    				}
    			});
    			$('#btn-save-id3').click(function(e) {
    				if(e.target.id === 'btn-save-id3') {
    					$('#fake-body').animate({opacity:1});
    					$('#member-secession-modal-id').animate({opacity:0},25);
    					$('.background-gray3').removeClass('modal-show');
    					$('.background-gray3').addClass('modal-del');
    					
    					$('html, body').removeClass('modal-body');
    					$('#element').off('scroll touchmove mousewheel', function(event) {
    		    			event.preventDefault();
    		    			event.stopPropagation();
    		    			return false;
    		    		});
    				}
    			});
    			
    			$('#element').on('scroll touchmove mousewheel', function(event) {
    			  event.preventDefault();
    			  event.stopPropagation();
    			  return false;
    			});
    		});

    		$('.password-form').validate({
				rules: {
					new_pw: {
						required : true,
						minlength  : 8
					},
					
					new_pw2 : {
						required : true,
						minlength  : 8,
						equalTo : '#new-password'
					},
				},
				messages: {
					new_pw : {
						required : '암호를 입력하세요.',
						minlength  : '8자 이상 입력하세요'
					},
					new_pw2 : {
						required : '암호를 입력하세요.',
						equalTo : '값이 다릅니다 다시 입력해주세요.',
						minlength  : '8자 이상 입력하세요'
					}
				},
			});
			
			
			$('#logout').click(function(){
				location.href = "login.jsp";
			});
    	});
    </script>
</head>
<body class="community-body">

	<div class="background-gray modal-del" id="background-gray-id">
		<div class="introduce-modal" id="introduce-modal-id">
			<div>한줄소개 변경<button type="button" class="introduce-modal-btn" id="introduce-modal-btn-id">✖</button></div>
			<form action="Controller" method="post">
				<textarea class="introduce-modal-insert" placeholder="한줄소개 입력" id="introduce-modal-insert-id" name="mypage-textarea"></textarea>
				<div style="float:right; margin-right:2px; width:65px; height:50px;">
					<button type="submit" class="btn-save" id="btn-save-id" name="command" value="introduceSave">저장</button>
				</div>
			</form>
		</div>
	</div>
	
	<div class="background-gray2 modal-del" id="background-gray-id2">
		<div class="change-pw-modal" id="change-pw-modal-id">
		
			<div>비밀번호 변경<button class="change-pw-modal-btn" id="change-pw-modal-btn-id">✖</button></div>
			
			<form action="Controller" class="password-form" method="post" novalidate>
				<div style="width:240px; height: 180px; padding-top:40px; margin:auto;">
					<p style="font-size:12px;">현재 비밀번호</p>
					<input class="pw-input" id="now-password" name="nowPw" type="password" />
					<p style="font-size:12px;">새로운 비밀번호</p>
					<input class="pw-input" id="new-password" name="newPw" type="password" />
					<p style="font-size:12px;">새로운 비밀번호 확인</p>
					<input class="pw-input" id="new-password-confirm" name="new_pw2" type="password" />
					<p id="password-confirm-tag" style="color: #ff4040; margin-top: 5px; font-size: 14px;"></p>
				</div>
			
				<div style="float:right; margin-right:2px; width:120px; height:50px;">
					<button type="submit" style="font-size:12px; margin-top: 30px;" class="btn-save" name="command" value="passwordChange">비밀번호 재설정</button>
				</div>
			</form>
			
		</div>
	</div>
	
	<div class="background-gray3 modal-del" id="background-gray-id3">
		<div class="member-secession-modal" id="member-secession-modal-id">
			<div>회원탈퇴<button class="member-secession-modal-btn" id="member-secession-modal-btn-id">✖</button></div>
			<div style="height:58px;">
				<p class="member-secession-div">회원 탈퇴를 하게 되면 보유하신 프로필, 업적 등이 모두 사라지며, 작성한 글, 댓글 등이 모두 사라집니다. 동의하십니까?</p>
			</div>
			<form action="Controller" method="post">
				<div style="float:right; width:90px; height:50px;">
					<button type="submit" name="command" value="secession" style="font-size:12px; border-radius: 2px;" class="btn-save" id="btn-save-id3">동의합니다</button>
				</div>
			</form>
		</div>
	</div>
	
<div id="fake-body">
	<!-- 위에 navbar 부분입니다 -->
    <header class="all-header-mainnav header-mainnav">
        <div class="header-container">
            <a href="main.jsp">
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
        <div class="first-title">마이페이지</div>
        <div class="second-title">
			<form action="Controller" method="get" id="search_form" autocomplete="off">
				<input class="main-input" type="text" name="name" placeholder="챔피언 이름을 입력하세요">
				<button style="opacity:0;" type="submit" name="command" value="search"></button>
			</form>
       	</div>
        <div style="clear: both;"></div>
    </div>
	
	<!-- 중단 -->
    <main class="my-page-main community-main">
      	<!-- 포스트 -->  	
      	<div class="post-detail">
      		<!-- 제목 -->
      		<div class="my-page-title">
      		
				<div style="color:rgb(23, 60, 189);"><b>내 프로필</b></div>
				
				<div class="my-page-writer">
      				<div>
      					<img class="my-image" src="Images/profile/<%=mypageImage.getIntroduce()%>"/>
      				</div>
      				<div style="padding-left: 30px; width: 580px;">
      					<div style="padding-bottom: 10px; color: darkgray;">
      						한줄소개
      					</div>
      					<div class="introduce">
							<%
								if (mypageIntroduce.getIntroduce() == null) {
							%>
								<div id="introduce-text"></div>
							<%
								} else {
							%>
								<div id="introduce-text">
									<%=mypageIntroduce.getIntroduce()%>
								</div>
							<%
								}
							%>
						</div>
      					
      					<div>
      						<button id="introduce-btn" class="first-btn">한줄소개 변경</button>
      					</div>
      				</div>
      				
      				<div style="position:absolute; top:210px; right:50px;">
      					<button id="change-pw-btn" style="width:100px;" class="first-btn">비밀번호 변경</button>
      				</div>
      				
      				<div style="position:absolute; top:250px; right:50px;">
      					<button id="member-secession-btn" style="width:100px;" class="first-btn">회원탈퇴</button>
      				</div>
      				
      			</div>
      		</div>

        </div>
        
        <div style="width:100%; height:350px;"></div>
        
        <div style="color:rgb(23, 60, 189); padding-top: 100px;"><b>프로필 앨범</b></div>
    
        <div class="change-img">
        	<div style="height:150px;">
				<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="anne1.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/anne1.jpg"/>
	       			</div>
	       		</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne2.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="anne2.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/anne2.jpg"/>
	       			</div>
	       		</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/bro.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="bro.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/bro.jpg"/>
	       			</div>
	       		</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/dk.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="dk.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/dk.jpg"/>
	       			</div>
	       		</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/DRX.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="DRX.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/DRX.jpg"/>
	       			</div>
	       		</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/gen.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="gen.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/gen.jpg"/>
	       			</div>
	       		</form>
	       			
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/hle.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="hle.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/hle.jpg"/>
	       			</div>
	       		</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/jhin.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="jhin.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/jhin.jpg"/>
	       			</div>
	       		</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/jinx1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="jinx1.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/jinx1.jpg"/>
	       			</div>
	       		</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/jinx2.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="jinx2.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/jinx2.jpg"/>
	       			</div>
	       		</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/kaisa.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="kaisa.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/kaisa.jpg"/>
	       			</div>
	       		</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/KDF.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="KDF.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/KDF.jpg"/>
	       			</div>
	       		</form>
	       			
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/kt.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="kt.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/kt.jpg"/>
	       			</div>
	       		</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/lsb.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="lsb.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/lsb.jpg"/>
	       			</div>
	       		</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/nasus.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="nasus.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/nasus.jpg"/>
	       			</div>
	       		</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/ns.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="ns.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/ns.jpg"/>
	       			</div>
	       		</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/t1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="t1.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/t1.jpg"/>
	       			</div>
	       		</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/yuumi1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="yuumi1.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/yuumi1.jpg"/>
	       			</div>
	       		</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/yuumi2.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="yuumi2.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/yuumi2.jpg"/>
	       			</div>
	       		</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/blackcat.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="blackcat.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/blackcat.jpg"/>
	       			</div>
	       		</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/whitecat.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="whitecat.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/whitecat.jpg"/>
	       			</div>
	       		</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/sofi.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="sofi.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/sofi.jpg"/>
	       			</div>
	       		</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/rengoku.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="rengoku.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/rengoku.jpg"/>
	       			</div>
       			</form>
	       		<form action="Controller" method="post">
	       			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/satoru.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<input type="hidden" name="imageName" value="satoru.jpg"/>
	        			<button name="command" value="profileImage">프로필로<br/>설정</button>
	        			<img class="img" src="Images/profile/satoru.jpg"/>
	       			</div>
        		</form>
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
</div>
<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
<script src="Js/all.js"></script>
</body>
</html>