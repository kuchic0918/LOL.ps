package mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yg_ac.dao.MemberDAO;
import com.yg_ac.dto.MemberDTO;

public class IntroduceSaveAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String introduce = request.getParameter("mypage-textarea");
		HttpSession session = request.getSession(false);
		MemberDTO member = (MemberDTO) session.getAttribute("memberInfo");
		
		MemberDAO mypageIntroduceDao = new MemberDAO();
		mypageIntroduceDao.updateMypageIntroduce(member.getMemberkey(), introduce);
		response.sendRedirect("my-page.jsp?mypage=profile");
	}
}
