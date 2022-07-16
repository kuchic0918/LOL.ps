package mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yg_ac.dao.MemberDAO;
import com.yg_ac.dto.MemberDTO;

public class SecessionAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		MemberDTO member = (MemberDTO) session.getAttribute("memberInfo");
		MemberDAO mypageMemberSecessionDao = new MemberDAO();
		mypageMemberSecessionDao.getMypageMemberSecession(member.getMemberkey());
		session.removeAttribute("memberInfo");
		request.setAttribute("mypage", "secession");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
}
