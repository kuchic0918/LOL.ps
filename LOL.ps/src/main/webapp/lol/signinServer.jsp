<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.sql.*"%>
<%@ page import = "com.yg_ac.dao.*"%>
<%@ page import = "com.yg_ac.dto.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Css/all.css">
<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
<script src="Js/jquery-3.6.0.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class="member-body" style="height:1200px;">
<%
	request.setCharacterEncoding("UTF-8");
	Y_DBmanager y_dbmanager = new Y_DBmanager();
 	Connection conn  = y_dbmanager.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	MemberDAO memberdao = new MemberDAO();
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	String nickname = request.getParameter("nickname");
	MemberDTO member = new MemberDTO(0 , email ,password , nickname , null, null, null);


	try {
		if(memberdao.emailisOverlap(email,pstmt, conn,rs)) {
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
		if(memberdao.NickNameIsOverlap(nickname, pstmt,  conn, rs)) {
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
	if(memberdao.isVaildEmail(email, conn, pstmt, rs) == false && memberdao.NickNameIsOverlap(nickname, pstmt, conn, rs) == false) {
		memberdao.signin(member, conn, pstmt);
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