<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네이버 입력 중 . . .</title>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
  var naver_id_login = new naver_id_login("79hXuwg9931gTF0Q5VRD", "http://localhost:9090/LOL.ps/lol/naverLoginAction.jsp");
  // 접근 토큰 값 출력
  naver_id_login.get_naver_userprofile("naverSignInCallback()");
  // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
  function naverSignInCallback() {
    var email = (naver_id_login.getProfileData('email'));
    var nickname = (naver_id_login.getProfileData('nickname'));
    location.href = `naverLoginAction.jsp?email=\${email}&nickname=\${nickname}`;
  }
</script>
</head>
<body>

</body>
</html>