package mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yg_ac.dao.MemberDAO;
import com.yg_ac.dto.MemberDTO;

public class NaverLoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = "naver/" + request.getParameter("email");
		String nickname = request.getParameter("nickname");
		HttpSession session = request.getSession();
		MemberDAO memberdao = new MemberDAO();
		MemberDTO member = new MemberDTO(0 , email , "naverPw" , nickname , null, null, null);
		
		if(memberdao.isVaildEmail(email)==false) {

			memberdao.snsSignIn(member);
			member = memberdao.findByEmailNicknameMemberInfo(email, nickname);
			session.setAttribute("memberInfo", member);
			request.setAttribute("member", "alright");
			request.getRequestDispatcher("my-page.jsp").forward(request, response);
	
		//이미 가입이 되어있으면 로그인 진행
		}
		else
		{
			member = memberdao.findByEmailNicknameMemberInfo(email, nickname);
			session.setAttribute("memberInfo", member);
			request.setAttribute("welcome", "welcome");
			request.getRequestDispatcher("my-page.jsp").forward(request, response);
	
		}
	}

}
