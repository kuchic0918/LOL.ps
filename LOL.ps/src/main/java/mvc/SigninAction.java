package mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yg_ac.dao.MemberDAO;
import com.yg_ac.dto.MemberDTO;

public class SigninAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO memberdao = new MemberDAO();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		MemberDTO member = new MemberDTO(0 , email ,password , nickname , null, null, null);
		if(memberdao.isVaildEmail(email) == false && memberdao.NickNameIsOverlap(nickname) == false) {	
			// 회원가입 완료
			memberdao.signin(member);
			request.setAttribute("signin", "success");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else if(memberdao.emailisOverlap(email)) {
			// 이메일 중복
			request.setAttribute("signin", "emailoverlap");
			request.getRequestDispatcher("signin.jsp").forward(request, response);
		} else if(memberdao.NickNameIsOverlap(nickname)) {
			// 닉네임 중복
			request.setAttribute("signin", "nicknameoverlap");
			request.getRequestDispatcher("signin.jsp").forward(request, response);
		}
	}
}
