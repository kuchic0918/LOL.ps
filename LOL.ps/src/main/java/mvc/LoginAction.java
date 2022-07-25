package mvc;

import java.io.IOException;	

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yg_ac.dao.MemberDAO;
import com.yg_ac.dto.MemberDTO;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDAO mDao = new MemberDAO();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		session.removeAttribute("memberInfo");
		if (mDao.loginvalid(email, password)) {	
			// 로그인 o
			MemberDTO member = mDao.findByEmailPwMemberInfo(email, password);	 	
			session.setAttribute("memberInfo", member);
			request.setAttribute("member", "alright");
			request.getRequestDispatcher("main.jsp").forward(request, response);
		} else if (mDao.isVaildEmail(email) == false && mDao.isVaildPW(password) == false) { 
			// 정보 x
			request.setAttribute("member", "wrong");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else if (mDao.isVaildPW(password) == false) {	
			// 비밀번호 x
			request.setAttribute("member", "passwordWrong");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else if (mDao.isVaildEmail(email) == false) { 
			// 이메일 x
			request.setAttribute("member", "emailWrong");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			// 비밀번호 x
			request.setAttribute("member", "passwordWrong");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
