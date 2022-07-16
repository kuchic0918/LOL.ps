package mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yg_ac.dao.MemberDAO;
import com.yg_ac.dto.MemberDTO;

public class PasswordChangeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String nowPW = request.getParameter("nowPw");
		String newPW= request.getParameter("newPw");
		HttpSession session = request.getSession(false);
		MemberDTO member = (MemberDTO) session.getAttribute("memberInfo");
		
		MemberDAO mypagePasswordChangeDao = new MemberDAO();
		if(nowPW.equals(member.getPw())) {
			mypagePasswordChangeDao.getMypagePasswordChangeDao(member.getMemberkey(), newPW);
			response.sendRedirect("login.jsp?mypage=passwordAlright");
		}else {
			response.sendRedirect("my-page.jsp?mypage=passwordWrong");
		}
	}
}
