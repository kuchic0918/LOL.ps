package mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yg_ac.dao.MemberDAO;
import com.yg_ac.dto.MemberDTO;

public class KakaoLoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO memberdao = new MemberDAO();
		HttpSession session = request.getSession();
		String kakaoEmail = "kakao/" + request.getParameter("kakaoEmail") ;
		String kakaoNickname =request.getParameter("kakaoNickname") ;
		MemberDTO member = new MemberDTO(0 , kakaoEmail , "snsAdmin" , kakaoNickname , null, null, null);
		System.out.println("email : " + kakaoEmail  + "  nickname :   " + kakaoNickname);
		// 이메일이 가입된 적이 없으면  . . .
		//회원가입 진행 
	

		if(memberdao.isVaildEmail(kakaoEmail)==false) {

			memberdao.snsSignIn(member);
			member = memberdao.findByEmailNicknameMemberInfo(kakaoEmail, kakaoNickname);
			session.setAttribute("memberInfo", member);
			request.setAttribute("welcome", "welcome");
			request.getRequestDispatcher("my-page.jsp").forward(request, response);
		//이미 가입이 되어있으면 로그인 진행
		}else {
			member = memberdao.findByEmailNicknameMemberInfo(kakaoEmail, kakaoNickname);
			session.setAttribute("memberInfo", member);
			request.setAttribute("welcome", "welcome");
			request.getRequestDispatcher("my-page.jsp").forward(request, response);
		}
	}

}
