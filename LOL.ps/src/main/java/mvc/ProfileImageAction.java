package mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yg_ac.dao.MemberDAO;
import com.yg_ac.dto.MemberDTO;

public class ProfileImageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String image = request.getParameter("imageName");
		HttpSession session = request.getSession(false);
		MemberDTO member = (MemberDTO) session.getAttribute("memberInfo");
		
		MemberDAO mypageProfileChangeDao = new MemberDAO();
		mypageProfileChangeDao.getmypageProfileChange(member.getMemberkey(), image);
		response.sendRedirect("my-page.jsp?mypage=imageChange");
	}
}
