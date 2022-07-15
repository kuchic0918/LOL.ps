<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*"%>
<%@ page import="com.yg_ac.dao.*"%>
<%@ page import="com.yg_ac.dto.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Css/all.css">
<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css'
	rel='stylesheet' type='text/css'>
<script src="Js/jquery-3.6.0.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class="member-body" style="height: 1200px;">
	<%
	request.setCharacterEncoding("UTF-8");
	MemberDAO memberdao = new MemberDAO();
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	String nickname = request.getParameter("nickname");
	MemberDTO member = new MemberDTO(0 , email ,password , nickname , null, null, null);


	try {
		if(memberdao.emailisOverlap(email)) {
		%>
		<script>
			$('#email-validate').css({display:'block'});
			alert("이미있는 아이디입니다.");
			location.href = "signin.jsp";
		</script>
		<% 	
	
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	
	try {
		if(memberdao.NickNameIsOverlap(nickname)) {
		%>
		<script>
			$('#nickname-validate').css({display:'block'});
			alert("이미있는 닉네임 입니다!");
			location.href = "signin.jsp";
		</script>
		<% 		
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	//email pw,  하고 닉네임이 중복되지 않으면 ...
	if(memberdao.isVaildEmail(email) == false && memberdao.NickNameIsOverlap(nickname) == false) {
		memberdao.signin(member);
		%>
		<script>
			alert("가입성공 !");
			location.href = "login.jsp";
		</script>
		<%
		}
	%>

</body>
</html>