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
		
		if (mDao.loginvalid(email, password)) {
			MemberDTO member = mDao.findByEmailPwMemberInfo(email, password);	 	
			session.setAttribute("memberInfo", member);
			request.setAttribute("member", "alright");
			request.getRequestDispatcher("my-page.jsp").forward(request, response);
		} else if (mDao.isVaildEmail(email) == false && mDao.isVaildPW(password) == false) {
			response.sendRedirect("login.jsp?member=wrong");
			
		} else if (mDao.isVaildPW(password) == false) {
			response.sendRedirect("login.jsp?member=passwordWrong");
		} else if (mDao.isVaildEmail(email) == false) {
			response.sendRedirect("login.jsp?member=emailWrong");
		} else {
			response.sendRedirect("login.jsp?member=passwordWrong");
		}
	}
}
