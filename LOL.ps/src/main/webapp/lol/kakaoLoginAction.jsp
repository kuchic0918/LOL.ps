<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.sql.*"%>
<%@ page import="com.yg_ac.dao.*" %>
<%@ page import="com.yg_ac.dto.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카카오로그인 진행</title>
</head>
<body>
	<%		
		//풀 확인
		Y_DBmanager y_dbmanager = new Y_DBmanager();
 		Connection conn  = y_dbmanager.getConnection();
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
		MemberDAO memberdao = new MemberDAO();
			
		String kakaoEmail = "kakao/" + request.getParameter("kakaoEmail") ;
		String kakaoNickname =request.getParameter("kakaoNickname") ;
		MemberDTO member = new MemberDTO(0 , kakaoEmail , "snsAdmin" , kakaoNickname , null, null, null);
		System.out.println("email : " + kakaoEmail  + "  nickname :   " + kakaoNickname);
		// 이메일이 가입된 적이 없으면  . . .
		//회원가입 진행 
	

		if(memberdao.isVaildEmail(kakaoEmail, conn, pstmt , rs)==false) {

			memberdao.snsSignIn(member, conn, pstmt);
			member = memberdao.findByEmailNicknameMemberInfo(kakaoEmail, kakaoNickname, conn, pstmt, rs);
			session.setAttribute("memberInfo", member);
	%>
		<script>
			alert("환영합니다");
			location.href = "my-page.jsp";
		</script>
	<% 		
		//이미 가입이 되어있으면 로그인 진행
		}else {
			member = memberdao.findByEmailNicknameMemberInfo(kakaoEmail, kakaoNickname, conn, pstmt, rs);
			session.setAttribute("memberInfo", member);
	%>	
			<script>
				alert("환영합니다");
				location.href = "my-page.jsp";
			</script>
	<% 		
		}
	%>
	
		
</body>
</html>