<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.sql.*"%>
<%@ page import="com.yg_ac.dao.*" %>
<%@ page import="com.yg_ac.dto.*" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../Css/all.css">
<link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
<script src="../Js/jquery-3.6.0.min.js"></script>
<meta charset="UTF-8">
<title>로그인 확인 중 . . .</title>
</head>
<body>
	<%
		Y_DBmanager y_dbmanager = new Y_DBmanager();
	 	Connection conn  = y_dbmanager.getConnection();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		MemberDAO memberdao = new MemberDAO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO member = memberdao.findByEmailPwMemberInfo(email, password, conn, pstmt, rs);	 	

		//멤버키 가져오기
		//memberdao.issueMemberkey(email, password, conn, pstmt, rs);
		//		session.setAttribute("yg" ,  123);
		//		session.setAttribute("memberInfo", 123);
		if (memberdao.loginvalid(email, password, conn, pstmt, rs)) {
			session.setAttribute("memberInfo", member);
			%>
			<script>
					console.log('<%=member%>');
					alert("환영합니다 !");
					location.href = "my-page.jsp";
			</script>
			<%
		} else if (memberdao.isVaildEmail(email, conn, pstmt, rs) == false
				&& memberdao.isVaildPW(password, conn, pstmt, rs) == false) {
			%>
			<script>
				alert("존재하지 않는 정보입니다");
				location.href = "login.jsp";
			</script>
			<%
		} else if (memberdao.isVaildPW(password, conn, pstmt, rs) == false) {
			%>
			<script>
				alert("비밀번호를 다시 확인해주시길 바랍니다.");
				location.href = "login.jsp";
			</script>
			<%
		} else if (memberdao.isVaildEmail(email, conn, pstmt, rs) == false) {
			%>
			<script>
					alert("아이디를 다시확인해주시길 바랍니다.");
					location.href = "login.jsp";
				</script>
			<%
		}
	%>
</body>
</html>