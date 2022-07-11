<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "yg_ac_java.*"%>
<%
// 	System.out.println(session.getAttribute("memberInfo"));
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
    <link rel="stylesheet" href="Css/all.css">
    <link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <script src="Js/jquery-3.6.0.min.js"></script>
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
    			//$(this).find("img:first-child").css('display','none');
    			//$(this).find("img").eq(2).css('display','block');
    			//$(this).find("div").css('display','block');
    			//$(this).find("img").eq(1).css('display','block');
    			/* $(this).find("img:first-child").css('opacity','0');
    			$(this).find("img").eq(1).css('opacity','1');
    			$(this).find("div").css('opacity','1');
    			$(this).find("img").eq(2).css('opacity','1'); */
    			$(this).find("img:first-child").animate({opacity:0},250);// ('opacity','0');
    			$(this).find("img").eq(1).animate({opacity:1},250); // .css('opacity','1');
    			$(this).find("div").animate({opacity:1},250); //css('opacity','1');
    			$(this).find("img").eq(2).animate({opacity:1},250);  //.css('opacity','1');
    		}, function() {
    			//$(this).find("img:first-child").css('display','block');
    			//$(this).find("img").eq(2).css('display','none');
    			//$(this).find("div").css('display','none');
    			//$(this).find("img").eq(1).css('display','none');
    			/* $(this).find("img:first-child").css('opacity','1');
    			$(this).find("img").eq(1).css('opacity','0');
    			$(this).find("div").css('opacity','0');
    			$(this).find("img").eq(2).css('opacity','0'); */
    			$(this).find("img:first-child").animate({opacity:1});  //.css('opacity','1');
    			$(this).find("img").eq(1).animate({opacity:0});  //.css('opacity','0');
    			$(this).find("div").animate({opacity:0});  //.css('opacity','0');
    			$(this).find("img").eq(2).animate({opacity:0});  //.css('opacity','0');
    		});
    		//한줄소개변경
    		$("#btn-save-id").click(function() {
    	   		var text = $("#introduce-modal-insert-id").val();
    	    	$("#introduce-text").html(text);
    	    	alert("한줄소개가 변경되었습니다.");
    	    	location.href = "my-page-action.jsp?text="+text;
    	    });
    		
    		$("#btn-save-id3").click(function() {
    	    	alert("회원탈퇴 완료.");
    	    });
    		
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
				
    			const cnt = 0;
    			$('#btn-save-id2').click(function(e) {
    				if(e.target.id === 'btn-save-id2'  && $('#new-password').val() === $('#new-password-confirm').val() && cnt==0) {
    					alert('비밀번호가 변경되었습니다!')
    					clear();
    					
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
    				} cnt++;
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


			$('#new-password-confirm').on('change', () => {
				if($('#new-password').val() === $('#new-password-confirm').val()) {
					$('#password-confirm-tag').html('')
				}else {
					$('#password-confirm-tag').html('새로운 비밀번호와 일치하지 않습니다!!')
				}
			})
			$('#logout').click(function(){
<%-- 				<%session.removeAttribute("memberInfo");%> --%>
				location.href = "login.jsp";
			});
    	});
    </script>
</head>
<body class="community-body">
<%
session.getAttribute("memberInfo");
System.out.println(session.getAttribute("memberInfo"));

%>
<script>
<%-- console.log('<%=session.getAttribute("memberInfo")%>'); --%>
</script>

	<div class="background-gray modal-del" id="background-gray-id">
		<div class="introduce-modal" id="introduce-modal-id">
		
			<div>한줄소개 변경<button class="introduce-modal-btn" id="introduce-modal-btn-id">✖</button></div>
			
			<textarea class="introduce-modal-insert" placeholder="한줄소개 입력" id="introduce-modal-insert-id"></textarea>

			<div style="float:right; margin-right:2px; width:120px; height:50px;">
				<button style="margin-right:5px;" class = "btn-cancel">취소</button> 
				<button class="btn-save" id="btn-save-id">저장</button>
			</div>
			
		</div>
	</div>
	
	<div class="background-gray2 modal-del" id="background-gray-id2">
		<div class="change-pw-modal" id="change-pw-modal-id">
		
			<div>비밀번호 변경<button class="change-pw-modal-btn" id="change-pw-modal-btn-id">✖</button></div>
			
			<div style="width:240px; height: 180px; padding-top:40px; margin:auto;">
				<p style="font-size:12px;">현재 비밀번호</p>
				<input class="pw-input" id="now-password" type="password" />
				<p style="font-size:12px;">새로운 비밀번호</p>
				<input class="pw-input" id="new-password" type="password" />
				<p style="font-size:12px;">새로운 비밀번호 확인</p>
				<input class="pw-input" id="new-password-confirm" type="password" />
				<p id="password-confirm-tag" style="color: #ff4040; margin-top: 5px; font-size: 14px;"></p>
			</div>
		
			<div style="float:right; margin-right:2px; width:120px; height:50px;">
				<button style="font-size:12px; margin-top: 30px;"  class="btn-save" id="btn-save-id2">비밀번호 재설정</button>
			</div>
			
		</div>
	</div>
	
	<div class="background-gray3 modal-del" id="background-gray-id3">
		<div class="member-secession-modal" id="member-secession-modal-id">
		
			<div>회원탈퇴<button class="member-secession-modal-btn" id="member-secession-modal-btn-id">✖</button></div>
			
			<div style="height:58px;">
				<p class="member-secession-div">회원 탈퇴를 하게 되면 보유하신 프로필, 업적 등이 모두 사라지며, 작성한 글, 댓글 등이 모두 사라집니다. 동의하십니까?</p>
			</div>

			<div style="float:right; width:90px; height:50px;">
				<button style="font-size:12px; border-radius: 2px;" class="btn-save" id="btn-save-id3">동의합니다</button>
			</div>
			
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
            	<a class="nav-items" href="notice/notice.html">공지사항</a>
                <a class="nav-items" href="rank.jsp">챔피언 랭킹</a>
                <a class="nav-items" href="community/build.html">빌드게시판</a>
                <a class="nav-items" href="community/free.html">자유게시판</a>
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
<<<<<<< HEAD
            			<form class="signin" action= "MypageServlet" >마이페이지</form>
		                <form class="login" action = "LogoutServlet" >로그아웃</form>     
            			<a class="signin" href="signin.jsp">마이페이지</a>
		                <a class="login" href="login.jsp">로그아웃</a>     
=======
            			<form action= "MypageServlet" >
            				<input class="signin" type="submit" value="마이페이지"/>
            			</form>
		                <form action = "LogoutServlet" >
		                	<input class="login" type="submit" value="로그아웃"/>
	                	</form>     
>>>>>>> branch 'main' of https://github.com/kuchic0918/LOL.ps.git
            	<%
            		}
            	%>
			</div>
		</div>
    </header>

    <div class="all-main">
        <div class="first-title">마이페이지</div>
        <div class="second-title">
			<form action="../MainStatisticsServlet" method="get" id="search_form" autocomplete="off">
				<input class="main-input" type="text" name="name" placeholder="챔피언 이름을 입력하세요">
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
      					<img class="my-image" src="Images/profile/anne1.jpg"/>
      				</div>
      				<div style="padding-left: 30px; width: 580px;">
      					<div style="padding-bottom: 10px; color: darkgray;">
      						한줄소개
      					</div>
      					<div class="introduce">
      						<div id="introduce-text"></div>
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
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne2.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/bro.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/dk.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/DRX.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/gen.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/hle.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/jhin.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/jinx1.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/jinx2.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/kaisa.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/KDF.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/nasus.jpg"/>
        			</div>
        			
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/anne1.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/anne1.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/anne1.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/anne1.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/anne1.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/anne1.jpg"/>
        			</div>
        			
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/anne1.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/anne1.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/anne1.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/anne1.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/anne1.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/anne1.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/anne1.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/anne1.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/anne1.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/anne1.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/anne1.jpg"/>
        			</div>
        			<div class="profile-settings fl">
	        			<img class="img" src="Images/profile/anne1.jpg"/>
	        			<img src="Images/icon_search2.webp"/>
	        			<div>프로필로<br/>설정</div>
	        			<img class="img" src="Images/profile/anne1.jpg"/>
        			</div>
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